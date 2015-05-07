package eu.cloudscaleproject.env.csm2pcm.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.csm2pcm.wizard.TransformIntoNewAlternativeWizard;

public class TransformIntoNewAlternativeWizardHandler {
	
	@Execute
	public void execute(IProject project) {
		
		TransformIntoNewAlternativeWizard transformIntoNewAlternativeWizard = new TransformIntoNewAlternativeWizard(project);
		WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), transformIntoNewAlternativeWizard);
		wizardDialog.open();
		
	}

}
