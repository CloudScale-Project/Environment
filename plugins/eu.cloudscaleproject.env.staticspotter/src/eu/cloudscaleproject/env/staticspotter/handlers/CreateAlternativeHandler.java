 
package eu.cloudscaleproject.env.staticspotter.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.staticspotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.staticspotter.wizard.InputSelectionWizard;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.wizard.CreateConfigAlternativeWizard;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(ResourceProvider rp, @Optional IEditorInputResource eir) {
				
		String id = rp.getID();
		IProject project = rp.getProject();

		CSToolResource tool = CSToolResource.getTool(id);
		
		if(CSToolResource.SPOTTER_STA_INPUT.equals(tool)){
			InputSelectionWizard createInputAltWizard = new InputSelectionWizard(project);
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createInputAltWizard);
			wizardDialog.open();
		}
		else if(CSToolResource.SPOTTER_STA_CONF.equals(tool)){
			CreateConfigAlternativeWizard createlternativeWizard;
			
			if(eir instanceof InputAlternative){
				createlternativeWizard = new CreateConfigAlternativeWizard(project, rp, (InputAlternative)eir);			
			}
			else{
				ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.SPOTTER_STA_INPUT);
				createlternativeWizard = new CreateConfigAlternativeWizard(project, rp, inputResourceProvider);
			}
			
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createlternativeWizard);
			wizardDialog.open();
		}
	
	}
	
	@CanExecute
	public boolean canExecute(@Optional ResourceProvider rp){
		
		if(rp == null){
			return false;
		}
		
		String id = rp.getID();
		if(CSToolResource.SPOTTER_STA_INPUT.getID().equals(id)){
			return true;
		}
		if(CSToolResource.SPOTTER_STA_CONF.getID().equals(id)){
			return true;
		}
		
		return false;
	}
		
}