 
package eu.cloudscaleproject.env.overview.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.overview.wizard.CreateOverviewWizard;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(ResourceProvider rp, @Optional IEditorInputResource eir) {
		
		String id = rp.getID();
		
		IProject project = rp.getProject();
		CSToolResource tool = CSToolResource.getTool(id);
		
		if(CSToolResource.OVERVIEW.equals(tool)){
			CreateOverviewWizard createInputAltWizard = new CreateOverviewWizard();
			createInputAltWizard.setProject(project);
			
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createInputAltWizard);
			wizardDialog.open();
		}
	}
	
	@CanExecute
	public boolean canExecute(@Optional ResourceProvider rp){
		
		if(rp == null){
			return false;
		}
		
		String id = rp.getID();
		if(CSToolResource.OVERVIEW.getID().equals(id)){
			return true;
		}
		return false;
	}
		
}