 
package eu.cloudscaleproject.env.analyser.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.wizard.CreateInputSelectionWizard;
import eu.cloudscaleproject.env.analyser.wizard.config.CreateConfigAlternativeSelectionWizard;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(ResourceProvider rp, @Optional IEditorInputResource eir) {
		
		String id = ResourceRegistry.getInstance().getResourceProviderID(rp);
		
		IProject project = rp.getProject();
		CSTool tool = CSTool.getTool(id);
		
		if(CSTool.ANALYSER_INPUT.equals(tool)){
			CreateInputSelectionWizard createInputAltWizard = new CreateInputSelectionWizard(project);
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createInputAltWizard);
			wizardDialog.open();
		}
		else if(CSTool.ANALYSER_CONF.equals(tool)){
						
			CreateConfigAlternativeSelectionWizard createConfigAltWizard = new CreateConfigAlternativeSelectionWizard(project);
			
			if(eir instanceof InputAlternative){
				createConfigAltWizard.setInputAlternative((InputAlternative)eir);
			}
			
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createConfigAltWizard);
			wizardDialog.open();
		}
		
	}
	
	@CanExecute
	public boolean canExecute(@Optional ResourceProvider rp){
		
		if(rp == null){
			return false;
		}
		
		String id = ResourceRegistry.getInstance().getResourceProviderID(rp);
		if(CSTool.ANALYSER_INPUT.getID().equals(id)){
			return true;
		}
		if(CSTool.ANALYSER_CONF.getID().equals(id)){
			return true;
		}
		
		return false;
	}
		
}