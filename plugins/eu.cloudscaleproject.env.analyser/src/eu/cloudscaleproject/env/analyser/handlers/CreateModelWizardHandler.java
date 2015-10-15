 
package eu.cloudscaleproject.env.analyser.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;

import eu.cloudscaleproject.env.analyser.ModelUtils;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class CreateModelWizardHandler {
	
	@Execute
	public void execute(@Active IEditorInputResource eir, 
						@Named("eu.cloudscaleproject.env.analyser.commandparameter.fileextension") String extension) {

		if(eir instanceof EditorInputEMF){
			ModelUtils.executeCreateModels((EditorInputEMF)eir, new ModelType[]{ModelType.getModelType(extension)});
		}
	}
	
	@CanExecute
	public boolean canExecute(@Optional @Active IEditorInputResource eir){
		if(eir instanceof EditorInputEMF){			
			return true;
		}
		return false;
	}
}