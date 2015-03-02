package eu.cloudscaleproject.env.analyser.validation;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;

import eu.cloudscaleproject.env.analyser.ResourceUtils;
import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.notification.IResourceStatus;
import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.StatusManager.Tool;
import eu.cloudscaleproject.env.common.notification.ToolValidator;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfValidator extends ToolValidator {
	
	private static final String ERR_MODEL_ERROR = "eu.cloudscaleproject.env.analyser.validation.PCMModelValidator.modelerror";
	private static final String ERR_MODEL_EMPTY = "eu.cloudscaleproject.env.analyser.validation.PCMModelValidator.modelempty";
	private static final String ERROR = "eu.cloudscaleproject.env.analyser.validation.ConfValidator.error";

	@Override
	public String getToolID() {
		return StatusManager.Tool.ANALYSER.getID();
	}

	@Override
	public IResource[] getDependantResources(IProject project) {
		return new IResource[]{ToolchainUtils.getToolFolder(project, ToolchainUtils.ANALYSER_CONF_ID)};
	}
	
	public boolean validateModels(IProject project, ConfAlternative ca) throws CoreException{
		
		boolean mp = false;
		boolean pms = false;
		boolean slo = false;
		
		if(ca != null){
			List<IResource> mpFiles = ca.getSubResources(ToolchainUtils.KEY_FILE_MESURPOINTS);
			List<IResource> pmsFiles = ca.getSubResources(ToolchainUtils.KEY_FILE_MONITOR);
			List<IResource> sloFiles = ca.getSubResources(ToolchainUtils.KEY_FILE_SLO);
			
			for(IResource file : mpFiles){
				mp &= validateModel(project, ca != null ? (IFile)file : null, "analyser_conf_measuringpoints");
			}
			for(IResource file : pmsFiles){
				pms &= validateModel(project, ca != null ? (IFile)file : null, "analyser_conf_pms");
			}
			for(IResource file : sloFiles){
				pms &= validateModel(project, ca != null ? (IFile)file : null, "analyser_conf_slo");
			}
		}

		return mp && pms && slo;
	}
	
	public boolean validateModel(IProject project, IFile file, String reqID) throws CoreException{
		IResourceStatus resStatus = statusManager.getResourceStatus(project, reqID);
		
		if(file == null || !file.exists()){
			resStatus.clearWarnings();
			resStatus.setIsDone(false);
			return false;
		}
		else{
			ResourceSet resSet = new ResourceSetImpl();
			URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			Resource emfRes = resSet.getResource(uri, true);
			
			//
			resStatus.setResource(file);
			return validateModel(resStatus, emfRes);
		}		
	}
	
	public boolean validateModel(IResourceStatus status, Resource resource){		
		// validate model
		
		status.handleWarning(ERR_MODEL_EMPTY, resource.getContents().isEmpty(),
				false,
				"Model is empty");
		
		if(resource.getContents().isEmpty()){
			status.setIsDone(false);
			return false;
		}
		
		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(resource.getContents().get(0));
		boolean modelValid = diagnostic.getSeverity() == Diagnostic.OK;
		
		status.handleWarning(ERR_MODEL_ERROR, !modelValid,
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
		
		//choose fist conf alternative appropriate for the selected input 
		IEditorInputResource selectedInputResource = ResourceRegistry.getInstance()
				.getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID)
				.getTaggedResource(ResourceProvider.TAG_SELECTED);
		if(selectedInputResource != null){
			List<ConfAlternative> list = ResourceUtils.getConfAlternatives(project, (InputAlternative)selectedInputResource );
			selectedResource = list.isEmpty() ? null : list.get(0);			
		}
				
		if(selectedResource == null || !selectedResource.getResource().exists()){
			status.setIsInProgress(false);
			status.clearWarnings();
			status.setIsDone(false);
			return false;
		}
		else{
			status.setIsInProgress(true);
		}
		
		status.setInstanceName(selectedResource.getName());				
		ConfAlternative ca = (ConfAlternative)selectedResource;
		
		//select used usage-evolution
		IEditorInputResource usageEvolutionAlt = null;
		{
			ResourceProvider usageEvolutionResourceProvider = ResourceRegistry.getInstance()
					.getResourceProvider(project, ToolchainUtils.USAGEEVOLUTION_ID);
			
			if(usageEvolutionResourceProvider != null){
				usageEvolutionAlt = usageEvolutionResourceProvider.getResource(
						ca.getSubResource(ToolchainUtils.KEY_FOLDER_USAGEEVOLUTION_ALT));
				usageEvolutionResourceProvider.tagResource(ResourceProvider.TAG_SELECTED, usageEvolutionAlt);
			}
		}
		
		try{
			IFolder folder = (IFolder)ca.getSubResource(ToolchainUtils.KEY_FOLDER_ANALYSER_INPUT_ALT);
			status.handleWarning(ERROR, 
					!inputResourceProvider.hasTag(ResourceProvider.TAG_SELECTED, 
							inputResourceProvider.getResource(folder)), 
					true, "Incomaptible input alternative for the selected configuration alternative.");
		}
		catch(IllegalStateException e){
			status.setIsDone(false);
			return false;
		}
		
		boolean valid = true;
		try {
			valid = validateModels(project, ca);
			if(usageEvolutionAlt != null){
				valid &= statusManager.validate(project, Tool.USAGE_EVOLUTION.getID());
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		status.setIsDone(valid);
		return valid;	
	}

}
