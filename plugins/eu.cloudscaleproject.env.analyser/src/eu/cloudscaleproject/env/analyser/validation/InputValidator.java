package eu.cloudscaleproject.env.analyser.validation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.palladiosimulator.pcm.system.impl.SystemImpl;
import org.scaledl.architecturaltemplates.repositories.cloudscale.black.ProfilesLibrary;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.notification.IResourceValidator;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.ValidationException;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ModelType;
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
		
		boolean resourceModelNeeded = true;
		boolean allocationModelNeeded = true;
		
		if(isSystemStereotypeApplyed(ia, "HadoopMapReduceSystem")){
			resourceModelNeeded = false;
			allocationModelNeeded = false;
		}
		else if(isSystemStereotypeApplyed(ia, "StaticAssemblyContextLoadbalancingSystem")){
			resourceModelNeeded = false;
			allocationModelNeeded = false;
		}
		
		if(!resourceModelNeeded){
			ia.getSelfStatus().checkError("Resource missing", true, false, "Resource model is missing!");
		}
		else{
			ia.getSelfStatus().checkError("Resource missing", !resFiles.isEmpty(), false, "Resource model is missing!");
		}	
		if(!allocationModelNeeded){
			ia.getSelfStatus().checkError("Allocation missing", true, false, "Allocation model is missing!");
		}
		else{
			ia.getSelfStatus().checkError("Allocation missing", !allFiles.isEmpty(), false, "Allocation model is missing!");
		}
		
		ia.getSelfStatus().checkError("Usage missing", !usaFiles.isEmpty(), false, "Usage model is missing!");
		
		boolean areModelsValid = true;
		
		Map<IFile, List<Diagnostic>> diagnostics = ia.validateModels();
		for(Entry<IFile, List<Diagnostic>> entry : diagnostics.entrySet()){
			
			final IFile file = entry.getKey();
			final IValidationStatus status = ia.getStatus(file);
			final ModelType modelType = ModelType.getModelType(file.getFileExtension());
			
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
			
			//TODO: System model validation is broken, when the AT's are applied
			if(modelType == ModelType.SYSTEM 
					&& isSystemStereotypeApplyed(ia, "HadoopMapReduceSystem")){
				
				status.setIsValid(true);
				continue;
			}
			
			//Representation model is not crucial for the simulation
			if(modelType == ModelType.REPRESENTATIONS ){
				status.setIsValid(true);
				continue;
			}
			
			//Set allocation and resource models status to valid, if they are not needed
			//TODO: Implement enabled/disabled status field and representation
			/*
			if(modelType == ModelType.RESOURCE && !resourceModelNeeded){
				status.setIsValid(true);
				continue;
			}
			if(modelType == ModelType.ALLOCATION && !allocationModelNeeded){
				status.setIsValid(true);
				continue;
			}
			*/
			
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
	
	public boolean isSystemStereotypeApplyed(InputAlternative ia, final String profileName){
		
		//return true;
		
		//TODO: The following code does not return correct value. 
		//		Another problem is, that it has to be executed in read/write transaction, which triggers a dirty state.
		
		List<IResource> sysFiles = ia.getSubResources(ToolchainUtils.KEY_FILE_SYSTEM);
		
		if(sysFiles.size() > 0){
			Resource sysRes = ia.getModelResource((IFile)sysFiles.get(0));
			
			for(EObject eo : sysRes.getContents()){
				
				if(eo instanceof SystemImpl){
					
					final SystemImpl sys = (SystemImpl)eo;
					final AtomicBoolean isProfileApplyed = new AtomicBoolean(false);
					
					ia.getEditingDomain().getCommandStack().execute(new RecordingCommand(ia.getEditingDomain()) {
						
						@Override
						protected void doExecute() {
							boolean isApplyed = ProfilesLibrary.isProfileApplied(sys, profileName);
							isProfileApplyed.set(isApplyed);
						}
					});
					
					return isProfileApplyed.get();
					
				}
				
			}
		}
		
		return false;
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