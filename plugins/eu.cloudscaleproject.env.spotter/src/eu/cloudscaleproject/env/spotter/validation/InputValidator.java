package eu.cloudscaleproject.env.spotter.validation;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.model.xml.MeasurementEnvironmentFactory;
import org.spotter.shared.environment.model.XMeasurementEnvironment;

import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.ToolValidator;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class InputValidator extends ToolValidator {
	
	private final String ERROR = "eu.cloudscaleproject.env.spotter.validation.InputValidator.error.input";
	private final String MESSAGE_PATTERN = "Alternative {0}: {1}";
	
	@Override
	public String getToolID() {
		return StatusManager.Tool.DYNAMIC_SPOTTER_INPUT.getID();
	}

	@Override
	public IResource[] getDependantResources(IProject project) {
		return new IResource[]{ToolchainUtils.getToolchainFolder(project, ToolchainUtils.SPOTTER_DYN_INPUT_ID)};
	}

	@Override
	public boolean doValidate(IProject project, IToolStatus status) {
				
		ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_INPUT_ID);
		
		if(!inputResourceProvider.getResources().isEmpty()){
			status.setIsInProgress(true);
		}
		else{
			status.setIsInProgress(false);
			status.setIsDone(false);
			return false;
		}

		//validate all input alternatives
		try {
			for(IEditorInputResource resource : inputResourceProvider.getResources()){
								
				// check connection
				ServiceClientWrapper client = Activator.getDefault().getClient(resource.getResource().getName());
				String hostname = resource.getProperty("hostname");
				String port = resource.getProperty("port");
				
				status.handleWarning(ERROR, hostname == null || hostname.isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, resource.getName(),"Connection hostname is not specified!"));
				status.handleWarning(ERROR, port == null || port.isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, resource.getName(),"Connection port is not specified!"));
				
				client.saveServiceClientSettings(hostname, port);
				
				status.handleWarning(ERROR, !client.testConnection(false), true, 
						MessageFormat.format(MESSAGE_PATTERN, resource.getName(),"Connection to server can not be established!"));
				
				// check input files
				IFolder folder = (IFolder)resource.getResource();
				IFile confFile = folder.getFile("mEnv.xml");
				status.handleWarning(ERROR, !confFile.exists(), true, 
						MessageFormat.format(MESSAGE_PATTERN, resource.getName(),"Instrumentation controller and Measurement controller not specified!"));
				
				MeasurementEnvironmentFactory factory = MeasurementEnvironmentFactory.getInstance();
				try {
					XMeasurementEnvironment confEnv = factory.parseXMLFile(confFile.getLocation().toString());
					status.handleWarning(ERROR, confEnv == null, true, 
							MessageFormat.format(MESSAGE_PATTERN, resource.getName(),"Invalid input configuration!"));
					
					List<?> ic = confEnv.getInstrumentationController();
					List<?> mc = confEnv.getMeasurementController();
					
					status.handleWarning(ERROR, ic == null || ic.isEmpty(), true, 
							MessageFormat.format(MESSAGE_PATTERN, resource.getName(),"Instrumentation controller not specified!"));					
					status.handleWarning(ERROR, mc == null || mc.isEmpty(), true,
							MessageFormat.format(MESSAGE_PATTERN, resource.getName(),"Measurement controller not specified!"));
					
				} catch (UICoreException e) {
					e.printStackTrace();
				}
				
			}
		} catch (IllegalStateException e) {
			status.setIsDone(false);
			return false;
		}
		
		status.setIsDone(true);
		
		return true;
	}

}
