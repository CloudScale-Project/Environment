 
package eu.cloudscaleproject.env.analyser.handlers;

import javax.inject.Named;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;

import eu.cloudscaleproject.env.analyser.ModelUtils;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class CreateModelHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable, 
						@Named("eu.cloudscaleproject.env.analyser.commandparameter.fileextension") String extension) {

		IEditorInputResource alternative = (IEditorInputResource)adaptable.getAdapter(IEditorInputResource.class);

		if(alternative instanceof EditorInputEMF){
			ModelUtils.executeCreateModels((EditorInputEMF)alternative, new ModelType[]{ModelType.getModelType(extension)});
		}
	}
	
	@CanExecute
	public boolean canExecute(@Named("org.eclipse.ui.selection") IAdaptable adaptable){
		IEditorInputResource alternative = (IEditorInputResource)adaptable.getAdapter(IEditorInputResource.class);

		if(alternative instanceof EditorInputEMF){
			
			//TODO: check if the model has already been created!
			return true;
		}
		
		return false;
	}
		
}