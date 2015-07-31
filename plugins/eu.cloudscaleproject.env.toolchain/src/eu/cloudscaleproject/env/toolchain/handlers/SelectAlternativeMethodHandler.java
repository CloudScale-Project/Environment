package eu.cloudscaleproject.env.toolchain.handlers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.notification.MethodStatusContext;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.dialogs.ResourceSelectionDialog;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class SelectAlternativeMethodHandler
{

	@Execute
	public void execute(@Active IProject project, @Active MethodStatusContext context)
	{
		// TODO Your code goes here

		if (context == null || context.getId() == null || project == null)
		{
			Logger.getLogger(SelectAlternativeMethodHandler.class.getName()).log(Level.WARNING, "Project or ValidationContext ID is null.");
			return;
		}

		CSTool tool = CSTool.getTool(context.getId());


		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		String title = "Select Alternative";
		String message = "Select alternative for " + tool.getName()+".";


		ResourceSelectionDialog dialog = new ResourceSelectionDialog(shell, title, message, project, tool);
		dialog.open();

		IEditorInputResource selection = dialog.getSelection();
		if (selection != null)
		{
			selection.validate();

			ValidationDiagramService.showStatus(project, tool.getID(), selection);
			// TODO: dependencies : i.e. Analyser input/config/results...
		}
	}

}