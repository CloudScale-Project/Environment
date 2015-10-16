package eu.cloudscaleproject.env.toolchain.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(ResourceProvider rp) {
		
		CreateAlternativeWizard createlternativeWizard = new CreateAlternativeWizard(rp.getProject(), rp);
		WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createlternativeWizard);
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
