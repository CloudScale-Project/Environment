package eu.cloudscaleproject.env.analyser.handlers;

import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.analyser.wizard.ImportAnalyserSelectionWizard;

public class OpenAnalyserImportHandler {
	
	private Logger logger = Logger.getLogger(OpenAnalyserImportHandler.class.getName());

	@Execute
	public void execute(@Active IProject project){
		
		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		
		if(project != null){
			ImportAnalyserSelectionWizard createInputAltWizard = new ImportAnalyserSelectionWizard(project);
			WizardDialog wizardDialog = new WizardDialog(shell, createInputAltWizard);
			wizardDialog.open();
		}
		else{
			logger.warning("Can't open dialog! Current project can not be retrieved!");
		}
	}

}
