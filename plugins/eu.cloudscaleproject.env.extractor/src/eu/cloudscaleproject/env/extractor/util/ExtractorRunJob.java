package eu.cloudscaleproject.env.extractor.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.modisco.infra.discovery.core.AbstractModelDiscoverer;
import org.eclipse.modisco.infra.discovery.core.IDiscoveryManager;
import org.eclipse.modisco.infra.discovery.core.exception.DiscoveryException;
import org.eclipse.modisco.infra.discovery.launch.LaunchConfiguration;
import org.eclipse.modisco.infra.discovery.launch.ParameterValue;
import org.somox.analyzer.simplemodelanalyzer.jobs.SaveSoMoXModelsJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SimpleModelAnalyzerJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.gast2seff.jobs.GAST2SEFFJob;
import org.somox.metrics.IMetric;
import org.somox.metrics.naming.NameResemblance;
import org.somox.metrics.registry.MetricsRegistry;
import org.somox.ui.runconfig.ModelAnalyzerConfiguration;

import de.uka.ipd.sdq.pcm.gmf.composite.edit.parts.ComposedProvidingRequiringEntityEditPart;
import de.uka.ipd.sdq.pcm.gmf.composite.part.PalladioComponentModelComposedStructureDiagramEditorPlugin;
import de.uka.ipd.sdq.pcm.gmf.repository.edit.parts.RepositoryEditPart;
import de.uka.ipd.sdq.pcm.gmf.repository.part.PalladioComponentModelRepositoryDiagramEditorPlugin;
import eu.cloudscaleproject.env.extractor.Activator;
import eu.cloudscaleproject.env.extractor.alternatives.ConfingAlternative;
import eu.cloudscaleproject.env.extractor.alternatives.InputAlternative;
import eu.cloudscaleproject.env.extractor.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class ExtractorRunJob 
{
	public static final String SOMOX_BASE_NAME = "internal_architecture_model";
	private IProject project;
	private ConfingAlternative configAlternative;
	private ResultAlternative resultAlternative;
	private InputAlternative inputAlternative;

	public ExtractorRunJob(ConfingAlternative configAlternative)
	{
		this.configAlternative = configAlternative;
		this.inputAlternative = (InputAlternative)configAlternative.getInputAlternative();
		this.project = configAlternative.getProject();
	}
	
	public IStatus run(IProgressMonitor monitor) throws CoreException
	{
		try
		{
			this.resultAlternative = createResultPersistenceFolder();
			this.resultAlternative.setConfigAlternative(configAlternative);
			runModisco(monitor);
			runSomox(monitor);
			initializeDiagrams();
			resultAlternative.save();
			resultAlternative.load();

		} catch (CoreException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			Status s = new Status(Status.ERROR, Activator.PLUGIN_ID, "Message : "+e.getMessage(), e);
			throw new CoreException(s);
		}

		return Status.OK_STATUS;
	}

	private SimpleDateFormat sdf_name = new SimpleDateFormat("hh:mm:ss");
	private ResultAlternative createResultPersistenceFolder() throws CoreException
	{
		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(this.configAlternative.getProject(),
				CSToolResource.EXTRACTOR_RES);

		String name = configAlternative.getName() + " [" + sdf_name.format(new Date()) + "]";
		return (ResultAlternative) resourceProvider.createNewResource(name, null);
	}


	private void runModisco(IProgressMonitor monitor) throws CoreException
	{
		LaunchConfiguration modiscoConfiguration = inputAlternative.getModiscoConfiguration();
		AbstractModelDiscoverer<?> discoverer = (AbstractModelDiscoverer<?>) IDiscoveryManager.INSTANCE.createDiscovererImpl(modiscoConfiguration.getDiscoverer().getId()); 
		
        IFolder modiscoFolder = (IFolder) resultAlternative.getSubResource(ResultAlternative.KEY_FOLDER_MODISCO);
        IFile java2kdmFile = modiscoFolder.getFile(inputAlternative.getExtractedProjectName()+"_java2kdm.xmi");
        IFile javaFile = modiscoFolder.getFile(inputAlternative.getExtractedProjectName()+"_java.xmi");
        IFile kdmFile = modiscoFolder.getFile(inputAlternative.getExtractedProjectName()+"_kdm.xmi");

        
		final URI resourceURI = URI.createPlatformResourceURI(java2kdmFile.getFullPath().toString(), true);
		discoverer.setTargetURI(resourceURI);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		EList<ParameterValue> parameterValues = modiscoConfiguration.getParameterValues();
		for (ParameterValue parameterValue : parameterValues)
		{
			if (parameterValue.getValue() != null)
			{
				parameters.put(parameterValue.getParameter().getId(), parameterValue.getValue());
			}
		}

		try
		{
			IDiscoveryManager.INSTANCE.discoverElement(discoverer, inputAlternative.getExtractedProject(), parameters, monitor);

			resultAlternative.setSubResource(ResultAlternative.KEY_FILE_MODISCO_JAVA2KDM, java2kdmFile);
			resultAlternative.setSubResource(ResultAlternative.KEY_FILE_MODISCO_JAVA, javaFile);
			resultAlternative.setSubResource(ResultAlternative.KEY_FILE_MODISCO_KDM, kdmFile);

		} catch (DiscoveryException e)
		{
			IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Please, check your discoverer configuration.", e); //$NON-NLS-1$
			throw new CoreException(status);
		}
	}

	private void runSomox(IProgressMonitor monitor) throws CoreException
	{
		SoMoXConfiguration somoxConfiguration = configAlternative.getSomoxConfiguration();

		IFile file = (IFile)resultAlternative.getSubResource(ResultAlternative.KEY_FILE_MODISCO_JAVA2KDM);
		somoxConfiguration.getFileLocations().setAnalyserInputFile(file.getFullPath().toString());

		somoxConfiguration.getFileLocations().setProjectName(this.project.getName());
		IFolder res = (IFolder) resultAlternative.getSubResource(ResultAlternative.KEY_FOLDER_SOMOX);
		somoxConfiguration.getFileLocations().setOutputFolder("/"+res.getProjectRelativePath().toString());

		_somoxNameResemblanceBugWorkaround();

		try
		{
			ModelAnalyzerConfiguration conf = new ModelAnalyzerConfiguration();
			conf.setSomoxConfiguration(somoxConfiguration);
			SoMoXBlackboard blackboard = new SoMoXBlackboard();

			SimpleModelAnalyzerJob job = new SimpleModelAnalyzerJob(conf);
			job.setBlackboard(blackboard);
			job.execute(monitor);
			
			GAST2SEFFJob gast2SeffJob = new GAST2SEFFJob(somoxConfiguration);
			gast2SeffJob.setBlackboard(blackboard);
			gast2SeffJob.execute(monitor);

			SaveSoMoXModelsJob saveJob = new SaveSoMoXModelsJob(somoxConfiguration);
			saveJob.setBlackboard(blackboard);
			saveJob.execute(monitor);

			IFolder somoxFolder = (IFolder) resultAlternative.getSubResource(ResultAlternative.KEY_FOLDER_SOMOX);
			IFile repositoryModelFile = somoxFolder.getFile(SOMOX_BASE_NAME+".repository");
			IFile systemModelFile = somoxFolder.getFile(SOMOX_BASE_NAME+".system");
			IFile sourceDecoratorFile = somoxFolder.getFile(SOMOX_BASE_NAME+".sourcecodedecorator");

			resultAlternative.setSubResource(ToolchainUtils.KEY_FILE_REPOSITORY, repositoryModelFile);
			resultAlternative.setSubResource(ToolchainUtils.KEY_FILE_SYSTEM, systemModelFile);
			resultAlternative.setSubResource(ToolchainUtils.KEY_FILE_SOURCEDECORATOR, sourceDecoratorFile);

		} catch (Exception e)
		{
			IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Please, check your discoverer and SoMoX configuration.", e); //$NON-NLS-1$
			throw new CoreException(status);
		}

	}

	private void initializeDiagrams()
	{
		IFile repositoryFile = (IFile)resultAlternative.getSubResource(ToolchainUtils.KEY_FILE_REPOSITORY);
		IFile systemFile = (IFile)resultAlternative.getSubResource(ToolchainUtils.KEY_FILE_SYSTEM);
		IFolder container = (IFolder)repositoryFile.getParent();

		final URI resourceURI = URI.createPlatformResourceURI(repositoryFile.getFullPath().toString(), true);
		final URI systemURI = URI.createPlatformResourceURI(systemFile.getFullPath().toString(), true);

		ResourceSet resSet = new ResourceSetImpl();
		final Resource systemRes = resSet.createResource(systemURI);
		final Resource repositoryRes = resSet.createResource(resourceURI);

		IFile repositoryDiagramFile = container.getFile(SOMOX_BASE_NAME+".repository_diagram");
		IFile systemDiagramFile = container.getFile(SOMOX_BASE_NAME+".system_diagram");

		final URI resourceDiagramURI = URI.createPlatformResourceURI(repositoryDiagramFile.getFullPath().toString(), true);
		final URI systemDiagramURI = URI.createPlatformResourceURI(systemDiagramFile.getFullPath().toString(), true);

		Resource repositoryDiagramResource = resSet.createResource(resourceDiagramURI);
		Resource systemDiagramResource = resSet.createResource(systemDiagramURI);

		try
		{
			systemRes.load(null);
			repositoryRes.load(null);
			
			Diagram repositoryDiagram = ViewService.createDiagram(repositoryRes.getContents().get(0), RepositoryEditPart.MODEL_ID,
					PalladioComponentModelRepositoryDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			repositoryDiagramResource.getContents().add(repositoryDiagram);
			repositoryDiagramResource.save(null);

			Diagram systemDiagram = ViewService.createDiagram(systemRes.getContents().get(0),
					ComposedProvidingRequiringEntityEditPart.MODEL_ID,
					PalladioComponentModelComposedStructureDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

			systemDiagramResource.getContents().add(systemDiagram);
			systemDiagramResource.save(null);

			resultAlternative.setSubResource(ResultAlternative.KEY_FILE_REPOSITORY_DIAGRAM, repositoryDiagramFile);
			resultAlternative.setSubResource(ResultAlternative.KEY_FILE_SYSTEM_DIAGRAM, systemDiagramFile);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		repositoryRes.unload();
		repositoryDiagramResource.unload();
		systemRes.unload();
		systemDiagramResource.unload();
	}

	private void _somoxNameResemblanceBugWorkaround()
	{
		// Clear Unmodifiable Map in NameResemblance metric

		for (IMetric metric : MetricsRegistry.getRegisteredMetrics().values())
		{
			if (metric instanceof NameResemblance)
			{
				NameResemblance nr = ((NameResemblance)metric);

		        try
				{
					Field field = NameResemblance.class.getDeclaredField("nameResemblanceMap");
					field.setAccessible(true);
					field.set(nr, new HashMap<>());
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
