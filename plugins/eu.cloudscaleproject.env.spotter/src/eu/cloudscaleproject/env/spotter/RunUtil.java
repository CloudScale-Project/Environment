package eu.cloudscaleproject.env.spotter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.model.xml.HierarchyFactory;
import org.spotter.eclipse.ui.model.xml.MeasurementEnvironmentFactory;
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

public class RunUtil {
	
	public static final Logger logger = Logger.getLogger(RunUtil.class.getName());
	
	public static void run(IProject project, EditorInputFolder confEditorInput){
		
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
				IEditorInputResource resultEditorInput;
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
			JobDescription jobDescription = RunUtil.createJobDescription(confEditorInput);
			Long jobId = client.startDiagnosis(jobDescription);
			if (jobId != null && jobId != 0) {
				DynamicSpotterRunJob job = new DynamicSpotterRunJob(project, client, 
												inputEditorInput.getResource().getName(), jobId, System.currentTimeMillis());
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
	
	public static JobDescription createJobDescription(EditorInputFolder confEditorInput) throws UICoreException {
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
}
