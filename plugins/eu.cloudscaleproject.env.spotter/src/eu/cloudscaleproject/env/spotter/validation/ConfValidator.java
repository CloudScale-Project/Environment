package eu.cloudscaleproject.env.spotter.validation;

import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.shared.configuration.JobDescription;

import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.ToolValidator;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.spotter.RunUtil;
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
		return new IResource[]{ToolchainUtils.getToolFolder(project, ToolchainUtils.SPOTTER_DYN_CONF_ID)};
	}

	@Override
	public boolean doValidate(IProject project, IToolStatus status) {
		
		ResourceProvider runResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_CONF_ID);
		
		IEditorInputResource selectedRes = runResourceProvider.getTaggedResource(ResourceProvider.TAG_SELECTED);
		
		if(selectedRes == null){
			status.setIsInProgress(false);
			status.setIsDone(false);
			return false;
		}
		
		status.setInstanceName(selectedRes.getName());
		status.setIsInProgress(true);
		
		try {
			EditorInputFolder editorInput = (EditorInputFolder)selectedRes;
			
			boolean jobCreationFailed = false;
			try {
				JobDescription job = RunUtil.createJobDescription(editorInput);
				
				status.handleWarning(ERROR_CONF, 
						job.getDynamicSpotterConfig().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration file is empty!"));					
				status.handleWarning(ERROR_CONF, 
						job.getMeasurementEnvironment().getInstrumentationController().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing instrumentation controller!"));
				status.handleWarning(ERROR_CONF, 
						job.getMeasurementEnvironment().getMeasurementController().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing measurement environment!"));
				status.handleWarning(ERROR_CONF, 
						job.getMeasurementEnvironment().getWorkloadAdapter().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing workload adapter!"));
				status.handleWarning(ERROR_CONF, 
						job.getHierarchy().getProblem().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing has incomplete hierarcy specification!"));

			} catch (UICoreException e) {
				jobCreationFailed = true;
			}
			
			status.handleWarning(ERROR_JOB, jobCreationFailed, true, 
					MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Missing configuration!"));
			
			status.setIsDone(true);
			
		} catch (IllegalStateException e) {
			status.setIsDone(false);
			return false;
		}
	
		return true;
	}

}
