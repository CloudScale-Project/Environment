 
package eu.cloudscaleproject.env.extractor.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.extractor.alternatives.InputAlternative;
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
		IProject project = rp.getProject();

		CSToolResource tool = CSToolResource.getTool(id);
		
		if(CSToolResource.EXTRACTOR_INPUT.equals(tool)){
			CreateAlternativeWizard createInputAltWizard = new CreateAlternativeWizard(rp){
				@Override
				protected void initAlternative(IEditorInputResource inputAlternative)
				{
					ResourceProvider configResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.EXTRACTOR_CONF);
					AbstractConfigAlternative alternative = (AbstractConfigAlternative)configResourceProvider.createNewResource("Basic configuration", null);
					alternative.setInputAlternative((AbstractInputAlternative)inputAlternative);
					alternative.save();
				}
			};
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createInputAltWizard);
			wizardDialog.open();
		}
		else if(CSToolResource.EXTRACTOR_CONF.equals(tool)){
			CreateConfigAlternativeWizard createlternativeWizard;
			
			if(eir instanceof InputAlternative){
				createlternativeWizard = new CreateConfigAlternativeWizard(rp, (InputAlternative)eir);			
			}
			else{
				ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.EXTRACTOR_INPUT);
				createlternativeWizard = new CreateConfigAlternativeWizard(rp, inputResourceProvider);
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
		if(CSToolResource.EXTRACTOR_INPUT.getID().equals(id)){
			return true;
		}
		if(CSToolResource.EXTRACTOR_CONF.getID().equals(id)){
			return true;
		}
		
		return false;
	}
		
}