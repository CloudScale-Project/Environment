package eu.cloudscaleproject.env.analyser.validation;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.ValidationException;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class InputValidator implements IResourceValidator {
	
	private static final String ERR_MODEL_ERROR = "eu.cloudscaleproject.env.analyser.validation.PCMModelValidator.modelerror";
	private static final String ERR_MODEL_EMPTY = "eu.cloudscaleproject.env.analyser.validation.PCMModelValidator.modelempty";
	
	@Override
	public String getID() {
		return ToolchainUtils.ANALYSER_INPUT_ID;
	}
	
	public boolean validateModels(IProject project, InputAlternative ia) throws CoreException, ValidationException{
		
		boolean rep = false;
		boolean sys = false;
		boolean all = false;
		boolean res = false;
		boolean usa = false;
		
		List<IResource> repFiles = ia.getSubResources(ToolchainUtils.KEY_FILE_REPOSITORY);
		List<IResource> sysFiles = ia.getSubResources(ToolchainUtils.KEY_FILE_SYSTEM);
		List<IResource> allFiles = ia.getSubResources(ToolchainUtils.KEY_FILE_ALLOCATION);
		List<IResource> resFiles = ia.getSubResources(ToolchainUtils.KEY_FILE_RESOURCEENV);
		List<IResource> usaFiles = ia.getSubResources(ToolchainUtils.KEY_FILE_USAGE);

		for(IResource file : repFiles){
			rep = validateModel(ia, (IFile)file);
			if(rep == false){break;}
		}
		for(IResource file : sysFiles){
			sys = validateModel(ia, (IFile)file);
			if(sys == false){break;}
		}
		for(IResource file : allFiles){
			all = validateModel(ia, (IFile)file);
			if(all == false){break;}
		}
		for(IResource file : resFiles){
			res = validateModel(ia, (IFile)file);
			if(res == false){break;}
		}
		for(IResource file : usaFiles){
			usa = validateModel(ia, (IFile)file);
			if(usa == false){break;}
		}

		return rep && sys && all && res && usa;
		
	}
	
	public boolean validateModel(InputAlternative alternative, IFile file) throws CoreException, ValidationException{
		
		IValidationStatus status = alternative.getStatus(file);
		
		if(status == null){
			return true;
		}
		
		if(file == null || !file.exists()){
			status.clearWarnings();
			status.setIsValid(false);
			return false;
		}
		else{
			URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			Resource emfRes = alternative.getResourceSet().getResource(uri, true);
			return validateModel(status, emfRes);
		}		
	}
	
	public boolean validateModel(IValidationStatus status, Resource resource) throws ValidationException{		
		
		status.check(ERR_MODEL_EMPTY, !resource.getContents().isEmpty(), false, "Model is empty");
		
		if(resource.getContents().isEmpty()){
			status.setIsValid(false);
			return false;
		}
		
		EcoreUtil.resolveAll(resource);
		
		EObject eObject = resource.getContents().get(0);
		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject);
		boolean modelValid = diagnostic.getSeverity() == Diagnostic.OK;
		
		String message = String.format("Model validation failed : %s [%s]", 
				eObject.eClass().getName(),
				resource.getURI().lastSegment());

		status.check(ERR_MODEL_ERROR, modelValid, false, message);
		status.setIsValid(modelValid);
		
		return modelValid;
	}

	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		boolean valid = true;
		
		InputAlternative alternative = (InputAlternative)statusProvider;
		IValidationStatus status = statusProvider.getSelfStatus();
				
		try {
			valid = validateModels(alternative.getProject(), alternative);
			status.setIsValid(valid);
		} 
		catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ValidationException e) {
			status.setIsValid(false);
		}
		
		status.setIsDirty(alternative.isDirty());
	}
}