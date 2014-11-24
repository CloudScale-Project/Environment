package eu.cloudscaleproject.env.usageevolution.validation;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.ToolValidator;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class UsageValidator extends ToolValidator{

	@Override
	public String getToolID() {
		return StatusManager.Tool.USAGE_EVOLUTION.getID();
	}

	@Override
	public IResource[] getDependantResources(IProject project) {
		return new IResource[]{ToolchainUtils.getToolFolder(project, ToolchainUtils.USAGEEVOLUTION_ID)};
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
		
		return false;
	}

}
