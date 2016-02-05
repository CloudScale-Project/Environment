package eu.cloudscaleproject.env.toolchain.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IInputAlternative;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;
import eu.cloudscaleproject.env.toolchain.wizard.CreateConfigAlternativeWizard;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(ResourceProvider rp, @Optional IEditorInputResource resource) {
		
		IWizard wizard = null;
		
		if(resource instanceof IInputAlternative){
			wizard = new CreateConfigAlternativeWizard(rp, (IInputAlternative)resource);
		}
		else{
			wizard = new CreateAlternativeWizard(rp);
		}
		
		WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
		wizardDialog.open();
	}
	
	@CanExecute
	public boolean canExecute(@Optional ResourceProvider rp) {
		
		if(rp == null){
			return false;
		}
		
		return true;
	}
}
