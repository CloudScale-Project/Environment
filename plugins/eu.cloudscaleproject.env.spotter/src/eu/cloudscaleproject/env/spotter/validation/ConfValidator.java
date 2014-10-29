package eu.cloudscaleproject.env.spotter.validation;

import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.shared.configuration.JobDescription;

import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.ToolValidator;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.spotter.Util;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfValidator extends ToolValidator {
	
	private final String ERROR_JOB = "eu.cloudscaleproject.env.spotter.validation.ConfValidator.error.job";
	private final String ERROR_CONF = "eu.cloudscaleproject.env.spotter.validation.ConfValidator.error.conf";
	private final String MESSAGE_PATTERN = "Alternative {0}: {1}";
	
	@Override
	public String getToolID() {
		return StatusManager.Tool.DYNAMIC_SPOTTER.getID();
	}

	@Override
	public IResource[] getDependantResources(IProject project) {
		return new IResource[]{ToolchainUtils.getToolchainFolder(project, ToolchainUtils.SPOTTER_DYN_CONF_ID)};
	}

	@Override
	public boolean doValidate(IProject project, IToolStatus status) {
		
		ResourceProvider runResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_CONF_ID);
		
		if(!runResourceProvider.getResources().isEmpty()){
			status.setIsInProgress(true);
		}
		else{
			status.setIsInProgress(false);
			status.setIsDone(false);
			return false;
		}
		
		try {
			for(IEditorInputResource resource : runResourceProvider.getResources()){
				EditorInputFolder editorInput = (EditorInputFolder)resource;
				
				boolean jobCreationFailed = false;
				try {
					JobDescription job = Util.createJobDescription(editorInput);
					
					status.handleWarning(ERROR_CONF, 
							job.getDynamicSpotterConfig().isEmpty(), true, 
							MessageFormat.format(MESSAGE_PATTERN, resource.getName(), "Configuration file is empty!"));					
					status.handleWarning(ERROR_CONF, 
							job.getMeasurementEnvironment().getInstrumentationController().isEmpty(), true, 
							MessageFormat.format(MESSAGE_PATTERN, resource.getName(), "Configuration missing instrumentation controller!"));
					status.handleWarning(ERROR_CONF, 
							job.getMeasurementEnvironment().getMeasurementController().isEmpty(), true, 
							MessageFormat.format(MESSAGE_PATTERN, resource.getName(), "Configuration missing measurement environment!"));
					status.handleWarning(ERROR_CONF, 
							job.getMeasurementEnvironment().getWorkloadAdapter().isEmpty(), true, 
							MessageFormat.format(MESSAGE_PATTERN, resource.getName(), "Configuration missing workload adapter!"));
					status.handleWarning(ERROR_CONF, 
							job.getHierarchy().getProblem().isEmpty(), true, 
							MessageFormat.format(MESSAGE_PATTERN, resource.getName(), "Configuration missing has incomplete hierarcy specification!"));

				} catch (UICoreException e) {
					jobCreationFailed = true;
				}
				
				status.handleWarning(ERROR_JOB, jobCreationFailed, true, 
						MessageFormat.format(MESSAGE_PATTERN, resource.getName(), "Missing configuration!"));
				
			}
			
			
		}catch (IllegalStateException e) {
			status.setIsDone(false);
			return false;
		}
	
		status.setIsDone(true);
		return true;
	}

}
