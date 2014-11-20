package eu.cloudscaleproject.env.analyser.validation;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;

import eu.cloudscaleproject.env.analyser.ConfAlternative;
import eu.cloudscaleproject.env.common.notification.IResourceStatus;
import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.ToolValidator;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfValidator extends ToolValidator {
	
	private static final String ERROR = "eu.cloudscaleproject.env.analyser.validation.ConfValidator.error";

	@Override
	public String getToolID() {
		return StatusManager.Tool.ANALYSER.getID();
	}

	@Override
	public IResource[] getDependantResources(IProject project) {
		return new IResource[]{ToolchainUtils.getToolchainFolder(project, ToolchainUtils.ANALYSER_CONF_ID)};
	}
	
	public boolean validateModels(IProject project, ConfAlternative ca) throws CoreException{
		
		boolean mp = false;
		boolean pms = false;
		boolean slo = false;
		
		mp = validateModel(project, ca != null ? ca.getModels("measuringpoint") : null, "analyser_conf_measuringpoints");
		pms = validateModel(project, ca != null ? ca.getModels("pms") : null, "analyser_conf_pms");
		slo = validateModel(project, ca != null ? ca.getModels("slo") : null, "analyser_conf_slo");

		return mp && pms && slo;
		
	}
	
	public boolean validateModel(IProject project, List<Resource> resources, String reqID) throws CoreException{
		IResourceStatus resStatus = statusManager.getResourceStatus(project, reqID);
		
		if(resources.isEmpty()){
			resStatus.clearWarnings();
			resStatus.setIsDone(false);
			return false;
		}
		
		resStatus.setResource(resources.get(0));
		
		boolean out = true;
		for(Resource res : resources){
			out &= validateModel(resStatus, res);
		}
		return out;
	}
	
	public boolean validateModel(IToolStatus status, Resource resource){		
		
		// validate model
		status.handleWarning(ERROR, resource.getContents().isEmpty(),
				false,
				"Model is empty");
		
		if(resource.getContents().isEmpty()){
			status.setIsDone(false);
			return false;
		}
		
		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(resource.getContents().get(0));
		boolean modelValid = diagnostic.getSeverity() == Diagnostic.OK;
		
		status.handleWarning(ERROR, !modelValid,
				false,
				diagnostic.getMessage());
		
		if(modelValid){
			status.setIsDone(true);
		}
		
		return modelValid;
	}

	@Override
	protected boolean doValidate(IProject project, IToolStatus status) {
		
		IEditorInputResource selectedResource = null;
		
		ResourceProvider confResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, ToolchainUtils.ANALYSER_CONF_ID);
		ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		
		selectedResource = confResourceProvider.getTaggedResource(ResourceProvider.TAG_SELECTED);
		
		if(selectedResource == null){
			status.setIsInProgress(false);
			status.setIsDone(false);
			return false;
		}
		else{
			status.setIsInProgress(true);
		}
		
		ConfAlternative ca = (ConfAlternative)selectedResource;
		try{
			status.handleWarning(ERROR, !inputResourceProvider.hasTag(ResourceProvider.TAG_SELECTED, ca.getInput()), 
					true, "Incomaptible input alternative for the selected configuration alternative.");
		}
		catch(IllegalStateException e){
			status.setIsDone(false);
			return false;
		}
		
		boolean valid = true;
		try {
			valid = validateModels(project, ca);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		status.setIsDone(valid);
		return valid;	
	}

}
