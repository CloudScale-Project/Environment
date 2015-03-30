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

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IResourceStatus;
import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.ToolValidator;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class InputValidator extends ToolValidator {
	
	private static final String ERR_MODEL_ERROR = "eu.cloudscaleproject.env.analyser.validation.PCMModelValidator.modelerror";
	private static final String ERR_MODEL_EMPTY = "eu.cloudscaleproject.env.analyser.validation.PCMModelValidator.modelempty";
	
	@Override
	public IResource[] getDependantResources(IProject project) {
		IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(project,
				ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
		
		IFolder analyserInputFolder = analyserFolder.getFolder(
				ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_INPUT));		
		return new IResource[]{analyserInputFolder};
	}

	@Override
	public String getToolID() {
		return StatusManager.Tool.ANALYSER_INPUT.getID();
	}
	
	public boolean validateModels(IProject project, InputAlternative ia) throws CoreException{
		
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
			rep = validateModel(project, ia != null ? (IFile)file : null, "analyser_input_repository");
			if(rep == false){break;}
		}
		for(IResource file : sysFiles){
			sys = validateModel(project, ia != null ? (IFile)file : null, "analyser_input_system");
			if(sys == false){break;}
		}
		for(IResource file : allFiles){
			all = validateModel(project, ia != null ? (IFile)file : null, "analyser_input_allocation");
			if(all == false){break;}
		}
		for(IResource file : resFiles){
			res = validateModel(project, ia != null ? (IFile)file : null, "analyser_input_resource");
			if(res == false){break;}
		}
		for(IResource file : usaFiles){
			usa = validateModel(project, ia != null ? (IFile)file : null, "analyser_input_usage");
			if(usa == false){break;}
		}

		return rep && sys && all && res && usa;
		
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
	public boolean doValidate(IProject project, IToolStatus status) {
		boolean valid = true;
		
		IEditorInputResource selectedResource = null;
		ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		selectedResource = inputResourceProvider.getTaggedResource(ResourceProvider.TAG_SELECTED);
		
		if(selectedResource == null){
			selectedResource = inputResourceProvider.getResources().isEmpty() ? 
					null : inputResourceProvider.getResources().get(0);
		}
		
		if(selectedResource == null || !selectedResource.getResource().exists()){
			status.setIsInProgress(false);
			status.clearWarnings();
			status.setIsDone(false);
			return false;
		}
		else{
			inputResourceProvider.tagResource(ResourceProvider.TAG_SELECTED, selectedResource);
			selectedResource.load();
			status.setIsInProgress(true);
		}
		
		status.setInstanceName(selectedResource.getName());
		
		try {
			valid = validateModels(project, (InputAlternative)selectedResource);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		status.setIsDone(valid);
		return valid;
	}
}