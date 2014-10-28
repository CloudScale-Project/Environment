package eu.cloudscaleproject.env.spotter.validation;

import javax.inject.Inject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.model.xml.MeasurementEnvironmentFactory;
import org.spotter.shared.environment.model.XMeasurementEnvironment;

import eu.cloudscaleproject.env.common.DIExtension;
import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.IToolValidator;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class InputValidator extends DIExtension implements IToolValidator {

	@Inject
	private StatusManager statusManager;
	
	@Override
	public String getToolID() {
		return StatusManager.Tool.DYNAMIC_SPOTTER_INPUT.getID();
	}

	@Override
	public IResource[] getDependantResources(IProject project) {
		return new IResource[]{ToolchainUtils.getToolchainFolder(project, ToolchainUtils.SPOTTER_DYN_INPUT_ID)};
	}

	@Override
	public boolean validate(IProject project) {
		
		IToolStatus status = statusManager.getStatus(project, StatusManager.Tool.DYNAMIC_SPOTTER_INPUT.getID());
		
		ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_INPUT_ID);
		
		if(!inputResourceProvider.getResources().isEmpty()){
			status.setIsInProgress(true);
		}
		else{
			status.setIsInProgress(false);
		}

		//validate all input alternatives
		try {
			for(IEditorInputResource resource : inputResourceProvider.getResources()){
								
				// check connection
				ServiceClientWrapper client = Activator.getDefault().getClient(resource.getResource().getName());
				String hostname = resource.getProperty("hostname");
				String port = resource.getProperty("port");
				client.saveServiceClientSettings(hostname, port);
				status.handleWarning("clientError", !client.testConnection(false), true, "Connection to server can not be established!");
				
				// check input files
				IFolder folder = (IFolder)resource.getResource();
				IFile confFile = folder.getFile("mEnv.xml");
				status.handleWarning("fileMissing", !confFile.exists(), true, "Input file missing 'mEnv.xml'!");
				
				MeasurementEnvironmentFactory factory = MeasurementEnvironmentFactory.getInstance();
				try {
					XMeasurementEnvironment confEnv = factory.parseXMLFile(confFile.getLocation().toString());
					status.handleWarning("instrumentationError", confEnv.getInstrumentationController().isEmpty(), 
							true, "Instrumentation controller not specified!");					
					status.handleWarning("mesurementError", confEnv.getMeasurementController().isEmpty(), 
							true, "Measurement controller not specified!");
					
				} catch (UICoreException e) {
					e.printStackTrace();
				}
				
			}
		} catch (IllegalStateException e) {
			//skip validation
			return false;
		}
		
		status.setIsDone(true);
		
		return true;
	}

}
