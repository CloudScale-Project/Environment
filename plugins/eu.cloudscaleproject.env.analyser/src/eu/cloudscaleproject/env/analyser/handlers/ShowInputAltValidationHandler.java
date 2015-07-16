package eu.cloudscaleproject.env.analyser.handlers;

import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.dialogs.ResourceSelectionDialog;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ShowInputAltValidationHandler {
	
	private Logger logger = Logger.getLogger(ShowInputAltValidationHandler.class.getName());
	
	@Execute
	public void execute(@Active IProject project){
				
		if(project != null){
			Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
			String title = "Show Analyser input alternative validation status";
			String message = "Please select Analyser input alternative.";
			
			ResourceSelectionDialog dialog = new ResourceSelectionDialog(shell, title, message, project, CSTool.ANALYSER_INPUT);
			dialog.open();
			
			IEditorInputResource selection = dialog.getSelection();
			if(selection != null){
				selection.validate();
				
				ValidationDiagramService.showStatus(project, CSTool.ANALYSER_RES.getID(), null);
				ValidationDiagramService.showStatus(project, CSTool.ANALYSER_CONF.getID(), null);
				ValidationDiagramService.showStatus(project, CSTool.ANALYSER_INPUT.getID(), selection);
			}
		}
		else{
			logger.warning("Can't open dialog! Current project can not be retrieved!");
		}
	}
	
}
