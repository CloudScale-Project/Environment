 
package eu.cloudscaleproject.env.analyser.handlers;

import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.analyser.wizard.CreateInputSelectionWizard;
import eu.cloudscaleproject.env.analyser.wizard.config.CreateConfigAlternativeSelectionWizard;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable) {
		
		ResourceProvider rp = (ResourceProvider)adaptable.getAdapter(ResourceProvider.class);
		String id = ResourceRegistry.getInstance().getResourceProviderID(rp);
		IProject project = rp.getProject();

		if(rp != null){
			CSTool tool = CSTool.getTool(id);
			
			if(CSTool.ANALYSER_INPUT.equals(tool)){
				CreateInputSelectionWizard createInputAltWizard = new CreateInputSelectionWizard(project);
				WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createInputAltWizard);
				wizardDialog.open();
			}
			else if(CSTool.ANALYSER_CONF.equals(tool)){
				CreateConfigAlternativeSelectionWizard createInputAltWizard = new CreateConfigAlternativeSelectionWizard(project);
				WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createInputAltWizard);
				wizardDialog.open();
			}
		}
		
	}
	
	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable){
		
		ResourceProvider rp = (ResourceProvider)adaptable.getAdapter(ResourceProvider.class);
		if(rp != null){
			return true;
		}
		
		return false;
	}
		
}