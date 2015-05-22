package eu.cloudscaleproject.env.analyser.validation;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.analyser.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class ResultValidator implements IResourceValidator {

	@Override
	public String getID() {
		return ToolchainUtils.ANALYSER_RES_ID;
	}

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status  = statusProvider.getSelfStatus();
		
		ResultAlternative alternative = (ResultAlternative)statusProvider;
		if(alternative.getEDP2Model() != null){
			status.setIsValid(true);
		}
		else{
			status.setIsValid(false);
		}
	}

}
