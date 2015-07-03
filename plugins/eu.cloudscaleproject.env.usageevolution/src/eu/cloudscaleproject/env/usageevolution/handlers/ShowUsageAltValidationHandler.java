package eu.cloudscaleproject.env.usageevolution.handlers;

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

public class ShowUsageAltValidationHandler {
	
	private Logger logger = Logger.getLogger(ShowUsageAltValidationHandler.class.getName());
	
	@Execute
	public void execute(@Active IProject project){
		
		if(project != null){
			
			Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
			String title = "Show usage evolution alternative validation status";
			String message = "Please select usage evolution alternative.";
			
			ResourceSelectionDialog dialog = new ResourceSelectionDialog(shell, title, message, project, CSTool.USAGEEVOLUTION);
			dialog.open();
			
			IEditorInputResource resource = dialog.getSelection();
			resource.validate();
			ValidationDiagramService.showStatus(project, resource);
			
		}
		else{
			logger.warning("Can't open dialog! Current project can not be retrieved!");
		}
	}
	
}
