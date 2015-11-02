 
package eu.cloudscaleproject.env.usageevolution.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.usageevolution.wizard.CreateUsageEvolutionWizard;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(ResourceProvider rp) {
		
		IProject project = rp.getProject();
		
		CreateUsageEvolutionWizard createInputAltWizard = new CreateUsageEvolutionWizard(project);
		WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createInputAltWizard);
		wizardDialog.open();
	}
	
	@CanExecute
	public boolean canExecute(@Optional ResourceProvider rp){
		
		if(rp == null){
			return false;
		}
		
		String id = rp.getID();
		if(CSToolResource.USAGEEVOLUTION.getID().equals(id)){
			return true;
		}
		
		return false;
	}
		
}