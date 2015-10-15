 
package eu.cloudscaleproject.env.staticspotter.handlers;

import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.staticspotter.wizard.InputSelectionWizard;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.wizard.CreateConfigAlternativeWizard;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable) {
		
		ResourceProvider rp = (ResourceProvider)adaptable.getAdapter(ResourceProvider.class);
		String id = ResourceRegistry.getInstance().getResourceProviderID(rp);
		IProject project = rp.getProject();

		ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.SPOTTER_STA_INPUT);
		ResourceProvider confResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.SPOTTER_STA_CONF);


		if(rp != null){
			CSTool tool = CSTool.getTool(id);
			
			if(CSTool.SPOTTER_STA_INPUT.equals(tool)){
				InputSelectionWizard createInputAltWizard = new InputSelectionWizard(project);
				WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createInputAltWizard);
				wizardDialog.open();
			}
			else if(CSTool.SPOTTER_STA_CONF.equals(tool)){
				CreateConfigAlternativeWizard createlternativeWizard = new CreateConfigAlternativeWizard(project, confResourceProvider, null, inputResourceProvider);
				WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createlternativeWizard);
				wizardDialog.open();
			}
		}
		
	}
	
	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable){
		
		if(adaptable == null){
			return false;
		}
		
		ResourceProvider rp = (ResourceProvider)adaptable.getAdapter(ResourceProvider.class);
		if(rp != null){
			return true;
		}
		
		return false;
	}
		
}