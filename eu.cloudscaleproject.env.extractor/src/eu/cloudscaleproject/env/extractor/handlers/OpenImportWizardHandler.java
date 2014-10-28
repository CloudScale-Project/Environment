 
package eu.cloudscaleproject.env.extractor.handlers;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class OpenImportWizardHandler {
	
	@Execute
	public void execute(CommandExecutor commandExecutor) {

		if (ExplorerProjectPaths.getProjectFromActiveEditor() == null 
				|| ExplorerProjectPaths.getProjectFromActiveEditor() == null)
		{
			Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
			MessageBox dialog = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
			dialog.setText("Import not possible");
			dialog.setMessage("Please select project in 'Project Explorer' view.");
			dialog.open();
			
			return;
		}

		
		IWizardDescriptor descriptor = PlatformUI.getWorkbench().getNewWizardRegistry()
				.findWizard("eu.cloudscaleproject.env.product.wizard.import");
		
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