package eu.cloudscaleproject.env.spotter.validation;

import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.shared.configuration.JobDescription;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.ValidationException;
import eu.cloudscaleproject.env.spotter.SpotterClientController;
import eu.cloudscaleproject.env.spotter.Util;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfValidator implements IResourceValidator {
	
	private final String ERROR_JOB = "eu.cloudscaleproject.env.spotter.validation.ConfValidator.error.job";
	private final String ERROR_CONF = "eu.cloudscaleproject.env.spotter.validation.ConfValidator.error.conf";
	private final String MESSAGE_PATTERN = "Alternative {0}: {1}";
	
	@Override
	public String getID() {
		return CSToolResource.SPOTTER_DYN_CONF.getID();
	}

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		IEditorInputResource selectedRes = (IEditorInputResource)statusProvider;
		
		try {
			EditorInputFolder editorInput = (EditorInputFolder)selectedRes;

			SpotterClientController controller = SpotterClientController.getController(project);
			status.check(ERROR_CONF, controller.isConnected(), false, IValidationStatus.SEVERITY_INFO, MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Spotter client not initialized - check Server configuration!"));
			
			boolean jobCreationFailed = false;
			try {
				JobDescription job = Util.createJobDescription(editorInput);
				
				status.checkError(ERROR_CONF,
						!job.getDynamicSpotterConfig().isEmpty(), false, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration file is empty!"));
				
				status.checkError(ERROR_CONF,
						job.getMeasurementEnvironment().getWorkloadAdapter() != null && !job.getMeasurementEnvironment().getWorkloadAdapter().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing workload adapter!"));
				
				status.checkError(ERROR_CONF,
						job.getHierarchy() != null, true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Hierarcy specification is missing!"));
				status.checkError(ERROR_CONF,
						job.getHierarchy().getProblem() != null && !job.getHierarchy().getProblem().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Hierarcy specification is empty!"));

			} catch (UICoreException e) {
				jobCreationFailed = true;
			}
			
			status.checkError(ERROR_JOB, !jobCreationFailed, true, 
					MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Missing configuration!"));
			
			status.setIsValid(true);
			
		} catch (ValidationException e) {
			status.setIsValid(false);
		}
	
	}

}
