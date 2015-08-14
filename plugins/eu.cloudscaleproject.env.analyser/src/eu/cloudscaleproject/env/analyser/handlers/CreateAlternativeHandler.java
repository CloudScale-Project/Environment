 
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
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerUtils;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

public class CreateAlternativeHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable) {
		
		IExplorerNode node = (IExplorerNode)adaptable.getAdapter(IExplorerNode.class);
		IProject project = ExplorerUtils.getProject(node);
		IExplorerNode rpNode = ExplorerUtils.getResourceProviderNode(node);
		
		CSTool tool = CSTool.getTool(rpNode.getID());
		
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
	
	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable){
		
		IExplorerNode node = (IExplorerNode)adaptable.getAdapter(IExplorerNode.class);
		if(node == null){
			return false;
		}
		
		IProject project = ExplorerUtils.getProject(node);
		if(project == null){
			return false;
		}
		
		IExplorerNode rpNode = ExplorerUtils.getResourceProviderNode(node);
		
		if(rpNode == null){
			return false;
		}
		
		return true;
	}
		
}