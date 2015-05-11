package eu.cloudscaleproject.env.extractor.wizard.util;

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
import org.eclipse.core.runtime.jobs.Job;
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
import org.eclipse.modisco.infra.discovery.ui.Activator;
import org.somox.analyzer.simplemodelanalyzer.jobs.SaveSoMoXModelsJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SimpleModelAnalyzerJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.metrics.IMetric;
import org.somox.metrics.naming.NameResemblance;
import org.somox.metrics.registry.MetricsRegistry;
import org.somox.ui.runconfig.ModelAnalyzerConfiguration;

import de.uka.ipd.sdq.pcm.gmf.composite.edit.parts.ComposedProvidingRequiringEntityEditPart;
import de.uka.ipd.sdq.pcm.gmf.composite.part.PalladioComponentModelComposedStructureDiagramEditorPlugin;
import de.uka.ipd.sdq.pcm.gmf.repository.edit.parts.RepositoryEditPart;
import de.uka.ipd.sdq.pcm.gmf.repository.part.PalladioComponentModelRepositoryDiagramEditorPlugin;
import eu.cloudscaleproject.env.extractor.ConfigPersistenceFolder;
import eu.cloudscaleproject.env.extractor.ResultPersistenceFolder;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class ExtractorRunJob extends Job
{
	public static final String SOMOX_BASE_NAME = "internal_architecture_model";
	private IProject project;
	private ConfigPersistenceFolder configFolder;
	private IProject projectToExtract;
	private ResultPersistenceFolder resultsFolder;

	public ExtractorRunJob(ConfigPersistenceFolder configInputFolder)
	{
		super("Extractor run");

		this.configFolder = configInputFolder;
		this.project = configInputFolder.getProject();
		this.projectToExtract = configInputFolder.getExtractedProject();

		setUser(true);
	}
	

	@Override
	public IStatus run(IProgressMonitor monitor)
	{
		// Infinite progress: -1
		monitor.beginTask("Extracting PCM from source code.", -1);

		try
		{
			this.resultsFolder = createResultPersistenceFolder();
			runModisco(monitor);
			runSomox(monitor);
			initializeDiagrams();
			resultsFolder.save();

		} catch (CoreException e)
		{
			e.printStackTrace();
			return e.getStatus();
		}
		catch (Exception e)
		{
			return new Status(Status.ERROR, Activator.PLUGIN_ID, "Message : "+e.getMessage());
		}

		monitor.done();

		return Status.OK_STATUS;
	}

	private SimpleDateFormat sdf_name = new SimpleDateFormat("hh:mm:ss");
	private ResultPersistenceFolder createResultPersistenceFolder() throws CoreException
	{
		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(this.configFolder.getProject(),
				ToolchainUtils.EXTRACTOR_RES_ID);

		String name = configFolder.getName() + " [" + sdf_name.format(new Date()) + "]";
		return (ResultPersistenceFolder) resourceProvider.createNewResource(name, null);
	}


	private void runModisco(IProgressMonitor monitor) throws CoreException
	{
		LaunchConfiguration modiscoConfiguration = configFolder.getModiscoConfiguration();
		AbstractModelDiscoverer<?> discoverer = (AbstractModelDiscoverer<?>) IDiscoveryManager.INSTANCE.createDiscovererImpl(modiscoConfiguration.getDiscoverer().getId()); 
		
        IFolder modiscoFolder = (IFolder) resultsFolder.getSubResource(ResultPersistenceFolder.KEY_FOLDER_MODISCO);
        IFile java2kdmFile = modiscoFolder.getFile(projectToExtract.getName()+"_java2kdm.xmi");
        IFile javaFile = modiscoFolder.getFile(projectToExtract.getName()+"_java.xmi");
        IFile kdmFile = modiscoFolder.getFile(projectToExtract.getName()+"_kdm.xmi");

        
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
			IDiscoveryManager.INSTANCE.discoverElement(discoverer, projectToExtract, parameters, monitor);

			resultsFolder.setSubResource(ResultPersistenceFolder.KEY_FILE_MODISCO_JAVA2KDM, java2kdmFile);
			resultsFolder.setSubResource(ResultPersistenceFolder.KEY_FILE_MODISCO_JAVA, javaFile);
			resultsFolder.setSubResource(ResultPersistenceFolder.KEY_FILE_MODISCO_KDM, kdmFile);

		} catch (DiscoveryException e)
		{
			IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Please, check your discoverer configuration.", e); //$NON-NLS-1$
			throw new CoreException(status);
		}
	}

	private void runSomox(IProgressMonitor monitor) throws CoreException
	{
		SoMoXConfiguration somoxConfiguration = configFolder.getSomoxConfiguration();

		IFile file = (IFile)resultsFolder.getSubResource(ResultPersistenceFolder.KEY_FILE_MODISCO_JAVA2KDM);
		somoxConfiguration.getFileLocations().setAnalyserInputFile(file.getFullPath().toString());

		somoxConfiguration.getFileLocations().setProjectName(this.project.getName());
		IFolder res = (IFolder) resultsFolder.getSubResource(ResultPersistenceFolder.KEY_FOLDER_SOMOX);
		somoxConfiguration.getFileLocations().setOutputFolder("/"+res.getProjectRelativePath().toString());

		_somoxNameResemblanceBugWorkaround();

		try
		{
			ModelAnalyzerConfiguration conf = new ModelAnalyzerConfiguration();
			conf.setSomoxConfiguration(somoxConfiguration);
			SimpleModelAnalyzerJob job = new SimpleModelAnalyzerJob(conf);
			SoMoXBlackboard blackboard = new SoMoXBlackboard();
			job.setBlackboard(blackboard);

			job.execute(monitor);

			SaveSoMoXModelsJob saveJob = new SaveSoMoXModelsJob(somoxConfiguration);
			saveJob.setBlackboard(blackboard);

			saveJob.execute(monitor);

			IFolder somoxFolder = (IFolder) resultsFolder.getSubResource(ResultPersistenceFolder.KEY_FOLDER_SOMOX);
			IFile repositoryModelFile = somoxFolder.getFile(SOMOX_BASE_NAME+".repository");
			IFile systemModelFile = somoxFolder.getFile(SOMOX_BASE_NAME+".system");
			IFile sourceDecoratorFile = somoxFolder.getFile(SOMOX_BASE_NAME+".sourcecodedecorator");
			resultsFolder.setSubResource(ToolchainUtils.KEY_FILE_REPOSITORY, repositoryModelFile);
			resultsFolder.setSubResource(ToolchainUtils.KEY_FILE_SYSTEM, systemModelFile);
			resultsFolder.setSubResource(ToolchainUtils.KEY_FILE_SOURCEDECORATOR, sourceDecoratorFile);

		} catch (Exception e)
		{
			IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Please, check your discoverer and SoMoX configuration.", e); //$NON-NLS-1$
			throw new CoreException(status);
		}

	}

	private void initializeDiagrams()
	{
		IFile repositoryFile = (IFile)resultsFolder.getSubResource(ToolchainUtils.KEY_FILE_REPOSITORY);
		IFile systemFile = (IFile)resultsFolder.getSubResource(ToolchainUtils.KEY_FILE_SYSTEM);
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

			resultsFolder.setSubResource(ResultPersistenceFolder.KEY_FILE_REPOSITORY_DIAGRAM, repositoryDiagramFile);
			resultsFolder.setSubResource(ResultPersistenceFolder.KEY_FILE_SYSTEM_DIAGRAM, systemDiagramFile);
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