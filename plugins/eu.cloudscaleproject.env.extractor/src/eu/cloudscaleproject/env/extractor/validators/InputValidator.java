package eu.cloudscaleproject.env.extractor.validators;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.extractor.alternatives.GlobalInputAlternative;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class InputValidator implements IResourceValidator {

	@Override
	public String getID() {
		return ToolchainUtils.EXTRACTOR_INPUT_ID;
	}
	

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		
		GlobalInputAlternative ia = (GlobalInputAlternative) statusProvider;
		ia.getProject();
				
		status.clearWarnings();
		if (GlobalInputAlternative.getJavaProjects().isEmpty())
		{
			status.addWarning("", "No Java projects present in workspace.");
			status.setIsValid(false);
		}
		else
		{
			status.setIsValid(true);
		}
	}
}
