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
			status.checkError(ERROR_CONF, controller.isConnected(), true, 
					MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(),"Spotter client not initialized - check Server configuration!"));
			
			boolean jobCreationFailed = false;
			try {
				JobDescription job = Util.createJobDescription(editorInput);
				
				status.checkError(ERROR_CONF,
						!job.getDynamicSpotterConfig().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration file is empty!"));
				
				status.checkError(ERROR_CONF,
						job.getMeasurementEnvironment().getInstrumentationController() != null, true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing instrumentation controller!"));
				status.checkError(ERROR_CONF,
						!job.getMeasurementEnvironment().getInstrumentationController().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing instrumentation controller!"));
				
				status.checkError(ERROR_CONF,
						job.getMeasurementEnvironment().getMeasurementController() != null, true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing measurement environment!"));
				status.checkError(ERROR_CONF,
						!job.getMeasurementEnvironment().getMeasurementController().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing measurement environment!"));
				
				status.checkError(ERROR_CONF,
						job.getMeasurementEnvironment().getWorkloadAdapter() != null, true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing workload adapter!"));
				status.checkError(ERROR_CONF,
						!job.getMeasurementEnvironment().getWorkloadAdapter().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing workload adapter!"));
				
				status.checkError(ERROR_CONF,
						!job.getHierarchy().getProblem().isEmpty(), true, 
						MessageFormat.format(MESSAGE_PATTERN, selectedRes.getName(), "Configuration missing has incomplete hierarcy specification!"));

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
