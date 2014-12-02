package eu.cloudscaleproject.env.spotter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.lpe.common.util.LpeFileUtils;
import org.lpe.common.util.LpeStreamUtils;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.jobs.DynamicSpotterRunJob;
import org.spotter.eclipse.ui.jobs.JobsContainer;
import org.spotter.eclipse.ui.model.xml.HierarchyFactory;
import org.spotter.eclipse.ui.model.xml.MeasurementEnvironmentFactory;
import org.spotter.eclipse.ui.navigator.ISpotterProjectElement;
import org.spotter.eclipse.ui.util.DialogUtils;
import org.spotter.eclipse.ui.util.SpotterProjectSupport;
import org.spotter.shared.configuration.FileManager;
import org.spotter.shared.configuration.JobDescription;
import org.spotter.shared.environment.model.XMeasurementEnvironment;
import org.spotter.shared.hierarchy.model.XPerformanceProblem;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ServerService {
	
	private static final Logger logger = Logger.getLogger(ServerService.class.getName());
	
	private static ServerService instance = null;
	
	public static ServerService getInstance(){
		if(instance == null){
			instance = new ServerService();
		}
		return instance;
	}
	
	public void fetchResults(IProject project){
		ResourceProvider rp = 
				ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_RES_ID);
		for(IEditorInputResource res : rp.getResources()){
			if(res instanceof EditorInputFolder){
				synchronizeRunResults(res.getResource().getProject(), ((EditorInputFolder)res).getResource());
			}
		}
	}
	
	public void runSimulation(final IProject project, EditorInputFolder confEditorInput){
		
		String inputResourceName = confEditorInput.getProperty(ResourceUtils.KEY_PARENT_EDITOR_RESOURCE);
		if(inputResourceName == null || inputResourceName.isEmpty()){
			logger.severe("run(): Input resource not set for the specified configuration resource: " + confEditorInput.getName());
			logger.severe("run(): Editing run operation!");
			return;
		}
		
		IEditorInputResource inputEditorInput = ResourceRegistry.getInstance().getResourceProvider(
												project, ToolchainUtils.SPOTTER_DYN_INPUT_ID).getResource(inputResourceName);
		
		if(inputEditorInput == null){
			logger.severe("run(): Input resource can not be retrieved for specified configuration resource: " + confEditorInput.getName());
			logger.severe("run(): Editing run operation!");
			return;
		}
		
		IEditorInputResource resultEditorInput = null;
		
		//prepare configuration for run 
		IFile fileConf = confEditorInput.getResource().getFile(FileManager.SPOTTER_CONFIG_FILENAME);
		if(fileConf.exists()){
			Properties confProp = new Properties();
			
			try {
				confProp.load(fileConf.getContents());
				
				IPath confEditorInputLocation = confEditorInput.getResource().getLocation();
				
				String hierarchyPath = confEditorInputLocation.append(FileManager.HIERARCHY_FILENAME).toString();
				String envPath = confEditorInputLocation.append(FileManager.ENVIRONMENT_FILENAME).toString();
				
				//create/retrieve results entry
				String runEditorInputName = confEditorInput.getResource().getName();
				
				ResourceProvider resultResourceProvider = ResourceRegistry.getInstance().getResourceProvider(
														  project, ToolchainUtils.SPOTTER_DYN_RES_ID);
				
				if(!resultResourceProvider.hasResource(runEditorInputName)){
					resultEditorInput = resultResourceProvider.createNewResource(confEditorInput.getResource().getName());
				}
				else{
					resultEditorInput = resultResourceProvider.getResource(runEditorInputName);
				}
				resultEditorInput.setName(confEditorInput.getName());
				resultEditorInput.setProperty(ResourceUtils.KEY_PARENT_EDITOR_RESOURCE, confEditorInput.getResource().getName());
				resultEditorInput.save();
				
				confProp.setProperty("org.spotter.conf.problemHierarchyFile", hierarchyPath);
				confProp.setProperty("org.spotter.measurement.environmentDescriptionFile", envPath);
				confProp.setProperty("org.spotter.resultDir", resultEditorInput.getResource().getLocation().toString());
				
				File f = fileConf.getLocation().toFile();
				try(OutputStream os = new FileOutputStream(f)){
					confProp.store(os, "");
				}
				catch(IOException ex){
					ex.printStackTrace();
				}
				
			} catch (IOException | CoreException e1) {
				e1.printStackTrace();
			}
		}
		
		try {
			fileConf.refreshLocal(IResource.DEPTH_ZERO, null);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		ServiceClientWrapper client = Activator.getDefault().getClient(inputEditorInput.getResource().getName());
		
		try {
			JobDescription jobDescription = createJobDescription(confEditorInput);
			Long jobId = client.startDiagnosis(jobDescription);
			
			if (jobId != null && jobId != 0) {
				DynamicSpotterRunJob job = new DynamicSpotterRunJob(project, jobId, System.currentTimeMillis()){
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						IStatus status = super.run(monitor);
						fetchResults(project);
						return status;
					}
				};
				//resultEditorInput.setProperty(ResourceUtils.KEY_JOB, String.valueOf(jobId));
				job.schedule();
				
			} else {
				String msg = String.format("Error occured during diagnosis: %s", "Could not retrieve a valid job id!");
				logger.warning(msg);
				DialogUtils.openError("DynamicSpotter Diagnosis", msg);
			}
			
		} catch (UICoreException e) {
			String message = "Unable to read and parse all configuration files!";
			DialogUtils.handleError(message, e);
			return;
		}
	}
	
	public JobDescription createJobDescription(EditorInputFolder confEditorInput) throws UICoreException {
		JobDescription jobDescription = new JobDescription();

		IFile spotterFile = confEditorInput.getResource().getFile(FileManager.SPOTTER_CONFIG_FILENAME);
		Properties dynamicSpotterConfig = SpotterProjectSupport.getSpotterConfig(spotterFile);
		jobDescription.setDynamicSpotterConfig(dynamicSpotterConfig);

		MeasurementEnvironmentFactory envFactory = MeasurementEnvironmentFactory.getInstance();
		String envFile = confEditorInput.getResource().getFile(FileManager.ENVIRONMENT_FILENAME).getLocation().toString();
		XMeasurementEnvironment measurementEnvironment = envFactory.parseXMLFile(envFile);
		jobDescription.setMeasurementEnvironment(measurementEnvironment);

		HierarchyFactory hierFactory = HierarchyFactory.getInstance();
		String hierFile = confEditorInput.getResource().getFile(FileManager.HIERARCHY_FILENAME).getLocation().toString();
		XPerformanceProblem hierarchy = hierFactory.parseHierarchyFile(hierFile);
		jobDescription.setHierarchy(hierarchy);

		return jobDescription;
	}
	
	private ISpotterProjectElement[] synchronizeRunResults(IProject iProject, IFolder folder) {
		File res = new File(folder.getLocation().toString());
		List<ISpotterProjectElement> elements = new ArrayList<>();
		ServiceClientWrapper client = Activator.getDefault().getClient(iProject.getName());

		if (!res.isDirectory()) {
			DialogUtils.openWarning("The project's '" + folder.getProjectRelativePath().toString()
					+ "' folder is missing or corrupted!");
		} else {
			boolean connected = client.testConnection(false);
			JobsContainer jobsContainer = JobsContainer.readJobsContainer(iProject);

			Long[] jobIds = jobsContainer.getJobIds();
			if (!connected && jobIds.length > 0) {
				DialogUtils
						.openAsyncWarning("No connection to DS service! New results cannot be fetched from the server.");
			}

			for (Long jobId : jobIds) {
				processJobId(jobId, connected, client, jobsContainer,
						folder, iProject);
			}
		}

		return elements.toArray(new ISpotterProjectElement[elements.size()]);
	}

	private void processJobId(Long jobId, boolean connected, ServiceClientWrapper client,
			JobsContainer jobsContainer, IFolder folder, IProject project) {
		if (connected && client.isRunning(true) && jobId.equals(client.getCurrentJobId())) {
			// Ignore this job because it is currently running
			return;
		}

		Long timestamp = jobsContainer.getTimestamp(jobId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_HH-mm-ss-SSS");
		String formattedTimestamp = dateFormat.format(new Date(timestamp));

		String runFolderName = folder.getLocation().toString() + "/" + formattedTimestamp;
		File runFolder = new File(runFolderName);

		// if the folder already exists, assume data has already been fetched
		boolean success = runFolder.exists();

		if (!success && connected) {
			// try to fetch data from server
			InputStream resultsZipStream = fetchResultsZipStream(client, jobId.toString());

			if (resultsZipStream != null) {
				String zipFileName = runFolderName + "/" + jobId + ".tmp";
				success = unpackFromInputStream(jobId.toString(), runFolder, zipFileName, resultsZipStream);
			}
		}

		if (success) {
			String projectRelativePath = folder.getProjectRelativePath().append(formattedTimestamp).toString();
			IFolder resFolder = project.getFolder(projectRelativePath);
			try {
				if (!resFolder.isSynchronized(IResource.DEPTH_INFINITE)) {
					resFolder.refreshLocal(IResource.DEPTH_INFINITE, null);
				}
				if (resFolder.members().length == 0) {
					JobsContainer.removeJobId(project, jobId);
				}
			} catch (CoreException e1) {
				logger.warning("An error occured while looking up folder members");
				e1.printStackTrace();
				try {
					// in this case delete the folder so it can be fetched
					// normally again next time
					resFolder.delete(true, null);
				} catch (CoreException e2) {
					logger.warning("An error occured while deleting folder");
					e2.printStackTrace();
				}
			}
		}
	}

	/*
	 * Unpacks the data from the input stream to the given run folder.
	 */
	private boolean unpackFromInputStream(String jobId, File runFolder, String zipFileName, InputStream resultsZipStream) {
		try {
			runFolder.mkdir();
			FileOutputStream fos = new FileOutputStream(zipFileName);
			LpeStreamUtils.pipe(resultsZipStream, fos);

			resultsZipStream.close();

			File zipFile = new File(zipFileName);
			LpeFileUtils.unzip(zipFile, runFolder);
			zipFile.delete();

			return true;
		} catch (Exception e) {
			String message = "Error while saving fetched results for job " + jobId + "!";
			DialogUtils.handleError(message, e);
			logger.severe(message);
			e.printStackTrace();
		}

		return false;
	}

	/*
	 * Tries to fetch the input stream from the server, but returns null if it's
	 * empty.
	 */
	private InputStream fetchResultsZipStream(ServiceClientWrapper client, String jobId) {
		InputStream resultsZipStream = client.requestResults(jobId);
		boolean isEmptyStream = true;

		try {
			if (resultsZipStream != null && resultsZipStream.available() > 0) {
				isEmptyStream = false;
			} else {
				String msg = "Received empty input stream for job " + jobId + ", skipping job!";
				DialogUtils.openWarning(msg);
			}
		} catch (IOException e) {
			String message = "An error occured while reading from input stream for job " + jobId + ", skipping job!";
			DialogUtils.handleError(message, e);
			logger.severe(message);
			e.printStackTrace();
		}

		return isEmptyStream ? null : resultsZipStream;
	}

}
