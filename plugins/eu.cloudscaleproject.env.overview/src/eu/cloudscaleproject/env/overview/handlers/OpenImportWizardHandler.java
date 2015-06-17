 
package eu.cloudscaleproject.env.overview.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.overview.wizard.ImportWizard;

public class OpenImportWizardHandler {
	
	@Execute
	public void execute(IProject project, CommandExecutor commandExecutor) {

		ImportWizard importWizard = new ImportWizard(project);
		importWizard.init(PlatformUI.getWorkbench(), null);
		WizardDialog wd = new  WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), importWizard);
		wd.setTitle(importWizard.getWindowTitle());
		wd.open();
	}
}