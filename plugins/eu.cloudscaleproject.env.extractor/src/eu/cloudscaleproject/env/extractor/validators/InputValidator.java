package eu.cloudscaleproject.env.extractor.validators;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.extractor.alternatives.GlobalInputAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;

public class InputValidator implements IResourceValidator {
	
	private static final String INFO_NO_JAVA_PROJECT = InputValidator.class.getName() + ".NoJavaProject";

	@Override
	public String getID() {
		return CSTool.EXTRACTOR_INPUT.getID();
	}
	

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		
		GlobalInputAlternative ia = (GlobalInputAlternative) statusProvider;
		ia.getProject();
				
		status.clearWarnings();
		if (GlobalInputAlternative.getInstance().getJavaProjects().isEmpty())
		{
			status.addWarning(INFO_NO_JAVA_PROJECT, IValidationStatus.SEVERITY_INFO, "No java project in workspace.");
			status.setIsValid(false);
		}
		else
		{
			status.setIsValid(true);
		}
	}
}
