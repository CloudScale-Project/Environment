 
package eu.cloudscaleproject.env.analyser.handlers;

import javax.inject.Named;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.analyser.wizard.ImportModelWizard;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ImportModelWizardHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable) {

		IEditorInputResource alternative = (IEditorInputResource)adaptable.getAdapter(IEditorInputResource.class);

		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		
		if(alternative instanceof EditorInputEMF){
			
			EditorInputEMF eir = (EditorInputEMF)alternative;
			
			ImportModelWizard importModelWIzard = new ImportModelWizard(eir);
			WizardDialog wizardDialog = new WizardDialog(shell, importModelWIzard);
			wizardDialog.open();
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