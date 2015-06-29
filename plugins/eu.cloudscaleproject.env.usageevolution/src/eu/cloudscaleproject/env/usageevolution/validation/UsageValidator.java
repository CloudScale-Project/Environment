package eu.cloudscaleproject.env.usageevolution.validation;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.ValidationException;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;

public class UsageValidator implements IResourceValidator{
	
	private static final String ERROR_DLIM_NOT_REGISTERED = "DLIM file is not registered in alternative.";
	private static final String ERROR_DLIM_EMPTY = "DLIM file is empty.";

	
	@Override
	public String getID() {
		return ToolchainUtils.USAGEEVOLUTION_ID;
	}
	
	private boolean validateDlimModel (UsageEvolutionAlternative alternative) throws ValidationException
	{
		List<Resource> resources = alternative.getModelResources(ToolchainUtils.KEY_FILE_LIMBO);
		alternative.getSelfStatus().checkError(ERROR_DLIM_NOT_REGISTERED, !resources.isEmpty(), true, ERROR_DLIM_NOT_REGISTERED);
		
		Resource resource = resources.get(0);
		alternative.getSelfStatus().checkError(ERROR_DLIM_EMPTY, resource != null, true, ERROR_DLIM_EMPTY);

		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(resource.getContents().get(0));
		
		boolean modelValid = diagnostic.getSeverity() == Diagnostic.OK;
		
		return modelValid;
		
	}

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		
		UsageEvolutionAlternative ueAlt = (UsageEvolutionAlternative)statusProvider;
		
		boolean valid = true;
		try{
			valid = validateDlimModel(ueAlt);
		}
		catch(ValidationException e){
			valid = false;
		}
		
		status.setIsValid(valid);
	}

}
