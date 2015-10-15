 
package eu.cloudscaleproject.env.extractor.handlers;

import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;
import eu.cloudscaleproject.env.toolchain.wizard.CreateConfigAlternativeWizard;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable) {
		
		
		ResourceProvider rp = (ResourceProvider)adaptable.getAdapter(ResourceProvider.class);
		String id = ResourceRegistry.getInstance().getResourceProviderID(rp);
		IProject project = rp.getProject();

		ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.EXTRACTOR_INPUT);
		ResourceProvider confResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.EXTRACTOR_CONF);


		if(rp != null){
			CSTool tool = CSTool.getTool(id);
			
			if(CSTool.EXTRACTOR_INPUT.equals(tool)){
				CreateAlternativeWizard createInputAltWizard = new CreateAlternativeWizard(project, inputResourceProvider);
				WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createInputAltWizard);
				wizardDialog.open();
			}
			else if(CSTool.EXTRACTOR_CONF.equals(tool)){
				CreateConfigAlternativeWizard createlternativeWizard = new CreateConfigAlternativeWizard(project, confResourceProvider, null, inputResourceProvider);
				WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), createlternativeWizard);
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