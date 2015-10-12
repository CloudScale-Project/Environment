package eu.cloudscaleproject.env.toolchain.handlers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.dialogs.ResourceSelectionDialog;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class SelectAlternativeMethodHandler
{

	@Execute
	public void execute(@Active final IProject project, @Optional IValidationDiagramService diagramService)
	{
		// TODO Your code goes here

		if (diagramService == null)
		{
			Logger.getLogger(SelectAlternativeMethodHandler.class.getName()).log(Level.WARNING, 
					"IValidationDiagramService is null! Can not open alternative selection dialog.");
			return;
		}

		CSTool tool = CSTool.getTool(diagramService.getSelectedNodeID());

		if(tool == null){
			Logger.getLogger(SelectAlternativeMethodHandler.class.getName()).log(Level.WARNING, 
					"Cloudscale tool with ID: " + diagramService.getSelectedNodeID() + " does not exist!");
			return;
		}

		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		String title = "Select Alternative";
		String message = "Select alternative for " + tool.getName()+".";


		ResourceSelectionDialog dialog = new ResourceSelectionDialog(shell, title, message, project, tool);
		dialog.open();

		final IEditorInputResource selection = dialog.getSelection();
		if (selection != null)
		{
			BusyIndicator.showWhile(Display.getDefault(), new Runnable() {
				
				@Override
				public void run() {
					if(!selection.isLoaded()){
						selection.load();
					}
					selection.validate();
					
					IValidationDiagramService diagramService = CloudscaleContext.getActiveContext().get(IValidationDiagramService.class);
					if(diagramService != null){
						diagramService.showStatus(project, selection);
					}
				}
			});
			
		}
	}

}