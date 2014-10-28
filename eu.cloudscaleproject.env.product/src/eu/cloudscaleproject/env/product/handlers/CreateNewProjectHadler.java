 
package eu.cloudscaleproject.env.product.handlers;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;

import eu.cloudscaleproject.env.common.CommandExecutor;

public class CreateNewProjectHadler {
	
	@Execute
	public void execute(CommandExecutor commandExecutor) {
		
		IWizardDescriptor descriptor = PlatformUI.getWorkbench().getNewWizardRegistry()
				.findWizard("eu.cloudscaleproject.env.product.wizard.newproject");
		
		if(descriptor != null){
			try {
				IWorkbenchWizard wizard = (IWorkbenchWizard)descriptor.createWizard();
				wizard.init(PlatformUI.getWorkbench(), null);
				
				WizardDialog wd = new  WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
				wd.setTitle(wizard.getWindowTitle());
				wd.open();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
}