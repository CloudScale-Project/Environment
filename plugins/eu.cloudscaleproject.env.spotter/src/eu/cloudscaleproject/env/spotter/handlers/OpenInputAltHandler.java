package eu.cloudscaleproject.env.spotter.handlers;

import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.spotter.dialogs.SelectInputAltDialog;

public class OpenInputAltHandler {
	
	private Logger logger = Logger.getLogger(OpenInputAltHandler.class.getName());
	
	@Execute
	public void execute(){
		
		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		
		if(project != null){
			SelectInputAltDialog dialog = new SelectInputAltDialog(project, shell);
			CloudscaleContext.inject(dialog);
			dialog.open();
		}
		else{
			logger.warning("Can't open dialog! Current project can not be retrieved!");
		}
	}
	
}
