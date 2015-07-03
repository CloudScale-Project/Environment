package eu.cloudscaleproject.env.analyser.handlers;

import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.dialogs.ResourceSelectionDialog;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ShowConfAltValidationHandler {

	private Logger logger = Logger.getLogger(ShowConfAltValidationHandler.class.getName());
	
	@Execute
	public void execute(@Active IProject project){
		
		if(project != null){
			Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
			String title = "Show Analyser configuration alternative validation status";
			String message = "Please select Analyser configuration alternative.";
			
			ResourceSelectionDialog dialog = new ResourceSelectionDialog(shell, title, message, project, CSTool.ANALYSER_CONF);
			dialog.open();
			
			IEditorInputResource selection = dialog.getSelection();
			if(selection != null){
				
				ConfAlternative confAlternative = (ConfAlternative)selection;
				InputAlternative inputAlternative = confAlternative.getInputAlternative();
				
				if(inputAlternative != null){
					inputAlternative.validate();
					ValidationDiagramService.showStatus(project, inputAlternative);
				}
				else{
					ValidationDiagramService.clearStatus(project, CSTool.ANALYSER_INPUT.getID());
				}
				
				selection.validate();
				
				ValidationDiagramService.clearStatus(project, CSTool.ANALYSER_RES.getID());
				ValidationDiagramService.showStatus(project, selection);
			}
		}
		else{
			logger.warning("Can't open dialog! Current project can not be retrieved!");
		}
	}
	
}
