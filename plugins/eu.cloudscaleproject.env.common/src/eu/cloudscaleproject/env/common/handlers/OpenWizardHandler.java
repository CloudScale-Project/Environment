 
package eu.cloudscaleproject.env.common.handlers;

import javax.inject.Named;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;

public class OpenWizardHandler {
	
	@Execute
	public void execute(@Named("id") String wizardID) {
		
		IWizardDescriptor descriptor = PlatformUI.getWorkbench().getNewWizardRegistry()
				.findWizard(wizardID);
		
		if(descriptor != null){
			try {
				IWorkbenchWizard wizard = (IWorkbenchWizard)descriptor.createWizard();
				wizard.init(PlatformUI.getWorkbench(), null);
				
				WizardDialog wd = new  WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
				wd.setTitle(wizard.getWindowTitle());
				wd.open();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@CanExecute
	public boolean canExecute(@Optional @Named("id") String wizardID){
		
		if(wizardID == null){
			return false;
		}
		
		IWizardDescriptor descriptor = PlatformUI.getWorkbench().getNewWizardRegistry()
				.findWizard(wizardID);
		
		if(descriptor != null){
			return true;
		}
		return false;
	}
		
}