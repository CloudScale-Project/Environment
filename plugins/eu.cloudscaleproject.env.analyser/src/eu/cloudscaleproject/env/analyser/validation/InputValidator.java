package eu.cloudscaleproject.env.analyser.validation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.ValidationException;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class InputValidator implements IResourceValidator {

	private static final Logger logger = Logger.getLogger(InputValidator.class.getName());
	
	@Override
	public String getID() {
		return CSToolResource.ANALYSER_INPUT.getID();
	}

	public void validateModels(IProject project, InputAlternative ia) throws CoreException, ValidationException{
		
		List<IResource> repFiles = ia.getSubResources(ToolchainUtils.KEY_FILE_REPOSITORY);
		List<IResource> sysFiles = ia.getSubResources(ToolchainUtils.KEY_FILE_SYSTEM);
		List<IResource> allFiles = ia.getSubResources(ToolchainUtils.KEY_FILE_ALLOCATION);
		List<IResource> resFiles = ia.getSubResources(ToolchainUtils.KEY_FILE_RESOURCEENV);
		List<IResource> usaFiles = ia.getSubResources(ToolchainUtils.KEY_FILE_USAGE);
		
		ia.getSelfStatus().checkError("Repository missing", !repFiles.isEmpty(), false, "Repository model is missing!");
		ia.getSelfStatus().checkError("System missing", !sysFiles.isEmpty(), false, "System model is missing!");
		ia.getSelfStatus().checkError("Allocation missing", !allFiles.isEmpty(), false, "Allocation model is missing!");
		ia.getSelfStatus().checkError("Resource missing", !resFiles.isEmpty(), false, "Resource model is missing!");
		ia.getSelfStatus().checkError("Usage missing", !usaFiles.isEmpty(), false, "Usage model is missing!");
		
		boolean areModelsValid = true;
		
		Map<IFile, List<Diagnostic>> diagnostics = ia.validateModels();
		for(Entry<IFile, List<Diagnostic>> entry : diagnostics.entrySet()){
			
			final IFile file = entry.getKey();
			IValidationStatus status = ia.getStatus(file);
			
			BasicCallback<Object> handle = new BasicCallback<Object>() {

				@Override
				public void handle(Object object) {
					try {
						IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
						IDE.openEditor(page, file);
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}
			};
			
			if(status == null){
				logger.warning("IValidationStatus for the resource does not exist! Resource: " + entry.getKey().toString());
				continue;
			}
			
			status.clearWarnings();
			
			for(Diagnostic d : entry.getValue()){
				
				if(d.getSeverity() == Diagnostic.OK){
					status.setIsValid(true);
				}
				if(d.getSeverity() == Diagnostic.WARNING){
					status.addWarning(d.getSource(), IValidationStatus.SEVERITY_WARNING, d.getMessage(), handle);
					status.setIsValid(false);
					areModelsValid &= false;
				}
				if(d.getSeverity() == Diagnostic.ERROR){
					status.addWarning(d.getSource(), IValidationStatus.SEVERITY_ERROR, d.getMessage(), handle);
					status.setIsValid(false);
					areModelsValid &= false;
				}
				if(d.getSeverity() == Diagnostic.INFO){
					status.addWarning(d.getSource(), IValidationStatus.SEVERITY_INFO, d.getMessage(), handle);
					status.setIsValid(false);
					areModelsValid &= true;
				}
				if(d.getSeverity() == Diagnostic.CANCEL){
					status.addWarning(d.getSource(), IValidationStatus.SEVERITY_ERROR, d.getMessage(), handle);
					status.setIsValid(false);
					areModelsValid &= false;
				}
			}
		}
		
		ia.getSelfStatus().check("Models are not valid", areModelsValid, false, 
				IValidationStatus.SEVERITY_ERROR, "Alternative models are not valid!");
		ia.getSelfStatus().setIsValid(areModelsValid);
		
	}
	
	@Override
	public void validate(IProject project, IValidationStatusProvider statusProvider) {
		
		InputAlternative alternative = (InputAlternative)statusProvider;
		IValidationStatus status = statusProvider.getSelfStatus();
				
		try {
			validateModels(alternative.getProject(), alternative);
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