package eu.cloudscaleproject.env.usageevolution.validation;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;

import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;

public class UsageValidator implements IResourceValidator{
	
	@Override
	public String getID() {
		return ToolchainUtils.USAGEEVOLUTION_ID;
	}
	
	private boolean validateDlimModel (UsageEvolutionAlternative alternative)
	{
		List<Resource> resources = alternative.getModelResources(ToolchainUtils.KEY_FILE_LIMBO);
		
		if (resources.isEmpty())
		{
			alternative.getSelfStatus().addWarning("", IValidationStatus.SEVERITY_ERROR, "DLIM file is not registered in alternative.");
			return false;
		}
		
		Resource resource = resources.get(0);

		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(resource.getContents().get(0));
		
		boolean modelValid = diagnostic.getSeverity() == Diagnostic.OK;
		
		return modelValid;
		
	}

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		
		UsageEvolutionAlternative ueAlt = (UsageEvolutionAlternative)statusProvider;
		
		boolean valid = validateDlimModel(ueAlt);
		
		status.setIsValid(valid);
	}

}
