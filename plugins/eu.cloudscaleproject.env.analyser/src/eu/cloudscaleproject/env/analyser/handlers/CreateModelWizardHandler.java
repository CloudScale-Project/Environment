 
package eu.cloudscaleproject.env.analyser.handlers;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.analyser.wizard.NewModelWizard;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class CreateModelWizardHandler {
	
	@Execute
	public void execute(@Active IEditorInputResource eir) {

		if(eir instanceof EditorInputEMF){
			NewModelWizard newModelWizard = new NewModelWizard((EditorInputEMF)eir, ModelType.GROUP_PCM);
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), newModelWizard);
			wizardDialog.open();
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