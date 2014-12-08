package eu.cloudscaleproject.env.usageevolution.validation;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;

import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.ToolValidator;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;

public class UsageValidator extends ToolValidator{
	
	@Override
	public String getToolID() {
		return StatusManager.Tool.USAGE_EVOLUTION.getID();
	}

	@Override
	public IResource[] getDependantResources(IProject project) {
		return new IResource[]{ToolchainUtils.getToolFolder(project, ToolchainUtils.USAGEEVOLUTION_ID)};
	}
	
	public boolean validateModels(IProject project, UsageEvolutionAlternative ueAlt) throws CoreException{
		
		boolean limbo = false;
		boolean usage = false;
		
		limbo = validateModel(project, ueAlt != null ? ueAlt.getModels("dlim") : null);
		usage = validateModel(project, ueAlt != null ? ueAlt.getModels("usageevolution") : null);

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
	protected boolean doValidate(IProject project, IToolStatus status) {
		
		IEditorInputResource selectedResource = ToolchainUtils.getToolSelectedResource(project, ToolchainUtils.USAGEEVOLUTION_ID);
		if(selectedResource == null || !selectedResource.getResource().exists()){
			status.setIsInProgress(false);
			status.clearWarnings();
			status.setIsDone(false);
			return false;
		}
		else{
			selectedResource.load();
			status.setIsInProgress(true);
		}
		
		UsageEvolutionAlternative ueAlt = (UsageEvolutionAlternative)selectedResource;
		
		boolean valid = true;
		try {
			valid = validateModels(project, ueAlt);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		status.setIsDone(valid);
		return valid;	
	}

}
