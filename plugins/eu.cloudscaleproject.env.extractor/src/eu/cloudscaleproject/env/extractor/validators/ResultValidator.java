package eu.cloudscaleproject.env.extractor.validators;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.extractor.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class ResultValidator implements IResourceValidator {

	@Override
	public String getID() {
		return ToolchainUtils.EXTRACTOR_RES_ID;
	}
	

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		
		ResultAlternative ra = (ResultAlternative) statusProvider;
		ra.getProject();
				
		status.clearWarnings();
		status.setIsValid(true);
		
		for (IValidationStatus s : statusProvider.getStatus())
		{
			s.clearWarnings();
			s.setIsValid(true);
		}
	}
}
