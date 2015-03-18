package eu.cloudscaleproject.env.extractor.wizard.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.modisco.infra.discovery.core.IDiscoverer;
import org.eclipse.modisco.infra.discovery.core.IDiscoveryManager;
import org.eclipse.modisco.infra.discovery.core.exception.DiscoveryException;
import org.eclipse.modisco.infra.discovery.launch.LaunchConfiguration;
import org.eclipse.modisco.infra.discovery.launch.ParameterValue;
import org.eclipse.modisco.infra.discovery.ui.Activator;
import org.somox.analyzer.simplemodelanalyzer.jobs.SaveSoMoXModelsJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SimpleModelAnalyzerJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.configuration.SoMoXConfiguration;
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
	private IProject project;
	private ConfigPersistenceFolder configInputFolder;
	private IProject projectToExtract;

	public ExtractorRunJob(ConfigPersistenceFolder configInputFolder)
	{
		super("Extractor run");

		this.configInputFolder = configInputFolder;
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
			runModisco(monitor);
			runSomox(monitor);

			copyFilesToResultFolder(projectToExtract);

		} catch (CoreException e)
		{
			e.printStackTrace();
			return new Status(Status.ERROR, "", "Extraction failed...");
		}

		monitor.done();

		return Status.OK_STATUS;
	}

	private void runModisco(IProgressMonitor monitor) throws CoreException
	{
		LaunchConfiguration modiscoConfiguration = configInputFolder.getModiscoConfiguration();
		IDiscoverer<?> discoverer = IDiscoveryManager.INSTANCE.createDiscovererImpl(modiscoConfiguration.getDiscoverer().getId());

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
		} catch (DiscoveryException e)
		{
			IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Please, check your discoverer configuration.", e); //$NON-NLS-1$
			throw new CoreException(status);
		}

	}

	private void runSomox(IProgressMonitor monitor)
	{
		SoMoXConfiguration somoxConfiguration = configInputFolder.getSomoxConfiguration();

		somoxConfiguration.getFileLocations().setProjectName(projectToExtract.getName());
		somoxConfiguration.getFileLocations().setAnalyserInputFile(
				"/" + projectToExtract.getName() + "/" + projectToExtract.getName() + "_java2kdm.xmi");
		somoxConfiguration.getFileLocations().setOutputFolder("/model");

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
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private SimpleDateFormat sdf_name = new SimpleDateFormat("hh:mm:ss");

	private ResultPersistenceFolder createResultPersistenceFolder() throws CoreException
	{
		ResourceProvider resourceProvider = ResourceRegistry.getInstance().getResourceProvider(this.configInputFolder.getProject(),
				ToolchainUtils.EXTRACTOR_RES_ID);

		String name = configInputFolder.getName() + " [" + sdf_name.format(new Date()) + "]";
		return (ResultPersistenceFolder) resourceProvider.createNewResource(name, name, null);
	}

	private void copyFilesToResultFolder(IProject outputProject)
	{
		try
		{
			ResultPersistenceFolder rif = createResultPersistenceFolder();

			IPath projectPath = new Path("/" + project.getName());

			//
			// Modisco output
			//
			IFolder modiscoFolder = (IFolder) rif.getSubResource(ResultPersistenceFolder.KEY_FOLDER_MODISCO);

			// Java2kdmFraments folder
			String fragments = "java2kdmFragments";
			IFolder srcFragFolder = outputProject.getFolder(fragments);
			IFolder destFragFolder = modiscoFolder.getFolder(fragments);
			srcFragFolder.copy(projectPath.append(destFragFolder.getProjectRelativePath()), true, null);
			srcFragFolder.delete(true, null);

			// XMI files
			String prefix = outputProject.getName();
			String kdm = prefix + "_kdm.xmi";
			String java = prefix + "_java.xmi";
			String java2kdm = prefix + "_java2kdm.xmi";

			for (String fileName : new String[] { kdm, java, java2kdm })
			{
				IFile file = outputProject.getFile(fileName);
				IFile dest = modiscoFolder.getFile(fileName);
				file.copy(projectPath.append(dest.getProjectRelativePath()), true, null);
				file.delete(true, null);
			}

			//
			// Somox output
			//
			IFolder somoxFolder = (IFolder) rif.getSubResource(ResultPersistenceFolder.KEY_FOLDER_SOMOX);

			IFolder modelFolder = outputProject.getFolder("model");

			for (IResource r : modelFolder.members())
			{
				if (r instanceof IFile)
				{
					IFile srcFile = (IFile) r;
					IFile dstFile = somoxFolder.getFile(srcFile.getName());
					srcFile.copy(projectPath.append(dstFile.getProjectRelativePath()), true, null);
				}
			}

			modelFolder.delete(true, null);

			initializeDiagrams(somoxFolder);

			IFile repositoryModelFile = somoxFolder.getFile(ResultPersistenceFolder.RESULT_FILE_REPOSITORY);
			IFile systemModelFile = somoxFolder.getFile(ResultPersistenceFolder.RESULT_FILE_SYSTEM);
			IFile repositoryDiagramFile = somoxFolder.getFile(ResultPersistenceFolder.RESULT_FILE_REPOSITORY_DIAGRAM);
			IFile systemDiagramFile = somoxFolder.getFile(ResultPersistenceFolder.RESULT_FILE_SYSTEM_DIAGRAM);

			rif.setSubResource(ToolchainUtils.KEY_FILE_REPOSITORY, repositoryModelFile);
			rif.setSubResource(ToolchainUtils.KEY_FILE_SYSTEM, systemModelFile);
			rif.setSubResource(ResultPersistenceFolder.KEY_FILE_REPOSITORY_DIAGRAM, repositoryDiagramFile);
			rif.setSubResource(ResultPersistenceFolder.KEY_FILE_SYSTEM_DIAGRAM, systemDiagramFile);

			IFile sourceDecoratorFile = somoxFolder.getFile(ResultPersistenceFolder.RESULT_FILE_SOURCEDECORATOR);
			rif.setSubResource(ToolchainUtils.KEY_FILE_SOURCEDECORATOR, sourceDecoratorFile);

			rif.save();

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initializeDiagrams(IFolder somoxFolder)
	{
		IFile repositoryFile = somoxFolder.getFile(ResultPersistenceFolder.RESULT_FILE_REPOSITORY);
		IFile systemFile = somoxFolder.getFile(ResultPersistenceFolder.RESULT_FILE_SYSTEM);

		final URI resourceURI = URI.createPlatformResourceURI(repositoryFile.getFullPath().toString(), true);
		final URI systemURI = URI.createPlatformResourceURI(systemFile.getFullPath().toString(), true);

		ResourceSet resSet = new ResourceSetImpl();
		final Resource systemRes = resSet.createResource(systemURI);
		final Resource repositoryRes = resSet.createResource(resourceURI);

		IFile repositoryDiagramFile = somoxFolder.getFile(ResultPersistenceFolder.RESULT_FILE_REPOSITORY_DIAGRAM);
		IFile systemDiagramFile = somoxFolder.getFile(ResultPersistenceFolder.RESULT_FILE_SYSTEM_DIAGRAM);

		final URI resourceDiagramURI = URI.createPlatformResourceURI(repositoryDiagramFile.getFullPath().toString(), true);
		final URI systemDiagramURI = URI.createPlatformResourceURI(systemDiagramFile.getFullPath().toString(), true);

		Resource repositoryDiagramResource = resSet.createResource(resourceDiagramURI);
		Resource systemDiagramResource = resSet.createResource(systemDiagramURI);

		try
		{
			systemRes.load(null);
			repositoryRes.load(null);
			// repositoryDiagramResource.load(null);
			// systemDiagramResource.load(null);

			Diagram repositoryDiagram = ViewService.createDiagram(repositoryRes.getContents().get(0), RepositoryEditPart.MODEL_ID,
					PalladioComponentModelRepositoryDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			repositoryDiagramResource.getContents().add(repositoryDiagram);
			repositoryDiagramResource.save(null);

			Diagram systemDiagram = ViewService.createDiagram(systemRes.getContents().get(0),
					ComposedProvidingRequiringEntityEditPart.MODEL_ID,
					PalladioComponentModelComposedStructureDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

			systemDiagramResource.getContents().add(systemDiagram);
			systemDiagramResource.save(null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		repositoryRes.unload();
		repositoryDiagramResource.unload();
		systemRes.unload();
		systemDiagramResource.unload();

	}
}
