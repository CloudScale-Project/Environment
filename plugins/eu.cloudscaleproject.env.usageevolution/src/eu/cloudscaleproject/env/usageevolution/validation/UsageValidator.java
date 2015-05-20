package eu.cloudscaleproject.env.usageevolution.validation;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;

public class UsageValidator implements IResourceValidator{
	
	@Override
	public String getID() {
		return ToolchainUtils.USAGEEVOLUTION_ID;
	}
	
	public boolean validateModels(IProject project, UsageEvolutionAlternative ueAlt) throws CoreException{
		
		boolean limbo = false;
		boolean usage = false;
		
		limbo = validateModel(project, ueAlt != null ? ueAlt.getModels("dlim") : null);

		return limbo && usage;
		
	}
	
	public boolean validateModel(IProject project, List<Resource> resources) throws CoreException{
		
		boolean out = true;
		for(Resource res : resources){
			out &= validateModel(res);
		}
		return out;
	}
	
	public boolean validateModel(Resource resource){		
		
		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(resource.getContents().get(0));
		boolean modelValid = diagnostic.getSeverity() == Diagnostic.OK;
		
		return modelValid;
	}

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		IValidationStatus status = statusProvider.getSelfStatus();
		
		UsageEvolutionAlternative ueAlt = (UsageEvolutionAlternative)statusProvider;
		
		boolean valid = true;
		try {
			valid = validateModels(ueAlt.getProject(), ueAlt);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		status.setIsValid(valid);
	}

}
