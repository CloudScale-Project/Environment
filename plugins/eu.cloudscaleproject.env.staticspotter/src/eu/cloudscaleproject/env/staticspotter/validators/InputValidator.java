package eu.cloudscaleproject.env.staticspotter.validators;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.staticspotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class InputValidator implements IResourceValidator {

	@Override
	public String getID() {
		return CSTool.SPOTTER_STA_INPUT.getID();
	}
	

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		
		InputAlternative ia = (InputAlternative) statusProvider;
		ia.getProject();
				
		status.clearWarnings();
		status.setIsValid(true);
		
		if (!validateSourcecodeDecorator(ia))
		{
			status.setIsValid(false);
		}
	}

	private boolean validateSourcecodeDecorator (InputAlternative ia)
	{
		IValidationStatus selfStatus = ia.getSelfStatus();

		IResource catalog = ia.getSubResource(ToolchainUtils.KEY_FILE_SOURCEDECORATOR);
		
		if (catalog == null)
		{
			selfStatus.addWarning("", IValidationStatus.SEVERITY_ERROR, "Sourcecode Decorator is missing.");
			return false;
		}
		
		return true;
	}
}
