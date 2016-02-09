 
package eu.cloudscaleproject.env.spotter.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractInputAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;
import eu.cloudscaleproject.env.toolchain.wizard.CreateConfigAlternativeWizard;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(ResourceProvider rp, @Optional IEditorInputResource eir) {
				
		String id = rp.getID();

		CSToolResource tool = CSToolResource.getTool(id);
		
		if(CSToolResource.SPOTTER_DYN_INPUT.equals(tool)){
			CreateAlternativeWizard createInputAltWizard = new CreateAlternativeWizard(rp){
				@Override
				protected void initAlternative(IEditorInputResource inputAlternative)
				{
					ResourceProvider configResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.SPOTTER_DYN_CONF);
					AbstractConfigAlternative alternative = (AbstractConfigAlternative)configResourceProvider.createNewResource("Basic configuration", null);
					alternative.setInputAlternative((AbstractInputAlternative)inputAlternative);
					alternative.save();
				}
			};
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createInputAltWizard);
			wizardDialog.open();
		}
		else if(CSToolResource.SPOTTER_DYN_CONF.equals(tool)){
			CreateConfigAlternativeWizard createAlternativeWizard;
			createAlternativeWizard = new CreateConfigAlternativeWizard(rp, (IEditorInputResource)eir);
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createAlternativeWizard);
			wizardDialog.open();
		}
		
	}
	
	@CanExecute
	public boolean canExecute(@Optional ResourceProvider rp){
		
		if(rp == null){
			return false;
		}
		
		String id = rp.getID();
		if(CSToolResource.SPOTTER_DYN_INPUT.getID().equals(id)){
			return true;
		}
		if(CSToolResource.SPOTTER_DYN_CONF.getID().equals(id)){
			return true;
		}
		
		return false;
	}
		
}