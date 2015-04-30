package eu.cloudscaleproject.env.analyser.validation;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.ValidationException;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfValidator implements IResourceValidator {
	
	private static final String ERR_MODEL_ERROR = "eu.cloudscaleproject.env.analyser.validation.ConfValidator.modelerror";
	private static final String ERR_MODEL_EMPTY = "eu.cloudscaleproject.env.analyser.validation.ConfValidator.modelempty";
	private static final String ERR_INPUT_NOT_SET = "eu.cloudscaleproject.env.analyser.validation.ConfValidator.inputnotset";
	private static final String ERR_USAGE_NOT_DOME = "eu.cloudscaleproject.env.analyser.validation.ConfValidator.usageNotDone";


	@Override
	public String getID() {
		return ToolchainUtils.ANALYSER_CONF_ID;
	}
	
	public boolean validateModels(IProject project, ConfAlternative ca) throws CoreException, ValidationException{
		
		boolean mp = true;
		boolean pms = true;
		boolean slo = true;
		
		boolean hasMp = false;
		boolean hasMonitor = false;
		boolean hasSlo = false;
		
		List<IResource> mpFiles = ca.getSubResources(ToolchainUtils.KEY_FILE_MESURPOINTS);
		List<IResource> pmsFiles = ca.getSubResources(ToolchainUtils.KEY_FILE_MONITOR);
		List<IResource> sloFiles = ca.getSubResources(ToolchainUtils.KEY_FILE_SLO);
		
		for(IResource file : mpFiles){
			hasMp = true;
			mp &= validateModel(ca, (IFile)file);
		}
		for(IResource file : pmsFiles){
			hasMonitor = true;
			pms &= validateModel(ca, (IFile)file);
		}
		for(IResource file : sloFiles){
			hasSlo = true;
			pms &= validateModel(ca, (IFile)file);
		}

		return mp && pms && slo && hasMp && hasMonitor && hasSlo;
	}
	
	public boolean validateModel(ConfAlternative alt, IFile file) throws CoreException, ValidationException{
		
		IValidationStatus status  = alt.getStatus(file);
		
		if(file == null || !file.exists()){
			status.clearWarnings();
			status.setIsValid(false);
			return false;
		}
		else{
			URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			Resource emfRes = alt.getResourceSet().getResource(uri, true);
			return validateModel(status, emfRes);
		}		
	}
	
	public boolean validateModel(IValidationStatus status, Resource resource) throws ValidationException{		
		// validate model
		
		if(status == null){
			return true;
		}
		
		status.check(ERR_MODEL_EMPTY, !resource.getContents().isEmpty(), false, "Model is empty");
		
		if(resource.getContents().isEmpty()){
			status.setIsValid(false);
			return false;
		}
		
		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(resource.getContents().get(0));
		boolean modelValid = diagnostic.getSeverity() == Diagnostic.OK;
		
		status.check(ERR_MODEL_ERROR, modelValid, false, diagnostic.getMessage());
		
		if(modelValid){
			status.setIsValid(true);
		}
		
		return modelValid;
	}
	
	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		ConfAlternative alternative = (ConfAlternative)statusProvider;
		IValidationStatus status = statusProvider.getSelfStatus();
		
		InputAlternative inputAlternative = alternative.getInputAlternative();
		inputAlternative.validate();
		
		try {
			status.check(ERR_INPUT_NOT_SET, inputAlternative != null, true, "Input alternative is not set!");
			boolean valid = validateModels(alternative.getProject(), alternative);
			
			IEditorInputResource usageAlternative = alternative.getUsageAlternative();
			
			if(usageAlternative != null){
				usageAlternative.validate();
				IValidationStatus usageStatus = usageAlternative.getSelfStatus();
				
				status.check(ERR_USAGE_NOT_DOME, usageStatus.isDone(), true, "Usage evolution is incomplete!");
			}
			else{
				status.removeWarning(ERR_USAGE_NOT_DOME);
			}
			
			status.setIsValid(valid);
		} 
		catch (CoreException e) {
			e.printStackTrace();
		}
		catch (ValidationException e) {
			status.setIsValid(false);
		}
		
		status.setIsDirty(alternative.isDirty());
	}

}
