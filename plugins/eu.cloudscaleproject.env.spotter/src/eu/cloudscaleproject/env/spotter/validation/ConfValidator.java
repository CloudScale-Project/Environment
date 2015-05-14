package eu.cloudscaleproject.env.spotter.validation;

import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.shared.configuration.JobDescription;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.ValidationException;
import eu.cloudscaleproject.env.spotter.ServerService;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfValidator implements IResourceValidator {
	
	private final String ERROR_JOB = "eu.cloudscaleproject.env.spotter.validation.ConfValidator.error.job";
	private final String ERROR_CONF = "eu.cloudscaleproject.env.spotter.validation.ConfValidator.error.conf";
	private final String MESSAGE_PATTERN = "Alternative {0}: {1}";
	
	@Override
	public String getID() {
		return ToolchainUtils.SPOTTER_DYN_CONF_ID;
	}

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		IEditorInputResource selectedRes = (IEditorInputResource)statusProvider;
		
		try {
			EditorInputFolder editorInput = (EditorInputFolder)selectedRes;
			
			boolean jobCreationFailed = false;
			try {
				JobDescription job = ServerService.getInstance().createJobDescription(editorInput);
				
				status.check(ERROR_CONF,
						!job.getDynamicSpotterConfig().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration file is empty!"));
				
				status.check(ERROR_CONF,
						job.getMeasurementEnvironment().getInstrumentationController() != null, true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing instrumentation controller!"));
				status.check(ERROR_CONF,
						!job.getMeasurementEnvironment().getInstrumentationController().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing instrumentation controller!"));
				
				status.check(ERROR_CONF,
						job.getMeasurementEnvironment().getMeasurementController() != null, true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing measurement environment!"));
				status.check(ERROR_CONF,
						!job.getMeasurementEnvironment().getMeasurementController().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing measurement environment!"));
				
				status.check(ERROR_CONF,
						job.getMeasurementEnvironment().getWorkloadAdapter() != null, true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing workload adapter!"));
				status.check(ERROR_CONF,
						!job.getMeasurementEnvironment().getWorkloadAdapter().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing workload adapter!"));
				
				status.check(ERROR_CONF,
						!job.getHierarchy().getProblem().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing has incomplete hierarcy specification!"));

			} catch (UICoreException e) {
				jobCreationFailed = true;
			}
			
			status.check(ERROR_JOB, !jobCreationFailed, true, 
					MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Missing configuration!"));
			
			status.setIsValid(true);
			
		} catch (ValidationException e) {
			status.setIsValid(false);
		}
	
	}

}
