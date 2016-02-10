package eu.cloudscaleproject.env.spotter.integration.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.spotter.integration.wizard.ImportResultsWizard;

public class ImportResultsHandler {

	@Execute
	public void execute(ESelectionService selectionService){
		
		Object selection = selectionService.getSelection(); 
		
		IWorkbenchWizard wizard = new ImportResultsWizard();
		wizard.init(PlatformUI.getWorkbench(), (selection instanceof IStructuredSelection) ? (IStructuredSelection)selection : null);
		
		WizardDialog wd = new  WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
		wd.setTitle(wizard.getWindowTitle());
		wd.open();
		
	}
	
}
