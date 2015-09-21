 
package eu.cloudscaleproject.env.extractor.handlers;

import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.extractor.wizard.CreateConfigSelectionWizard;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable) {
		
		ResourceProvider rp = (ResourceProvider)adaptable.getAdapter(ResourceProvider.class);
		IProject project = rp.getProject();
		
		CreateConfigSelectionWizard createInputAltWizard = new CreateConfigSelectionWizard(project);
		WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createInputAltWizard);
		wizardDialog.open();
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