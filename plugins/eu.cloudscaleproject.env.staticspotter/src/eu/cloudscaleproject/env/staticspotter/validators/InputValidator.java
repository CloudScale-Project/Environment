package eu.cloudscaleproject.env.staticspotter.validators;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.staticspotter.alternatives.GlobalInputAlternative;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class InputValidator implements IResourceValidator {

	@Override
	public String getID() {
		return ToolchainUtils.SPOTTER_STA_INPUT_ID;
	}
	

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		
		GlobalInputAlternative ia = (GlobalInputAlternative) statusProvider;
		ia.getProject();
				
		status.clearWarnings();
		status.setIsValid(true);

		if (GlobalInputAlternative.getInstance().getExtractorResults().isEmpty())
		{
			status.addWarning("", "Extractor results are missing in workspace.");
			status.setIsValid(false);
		}
	}
}
