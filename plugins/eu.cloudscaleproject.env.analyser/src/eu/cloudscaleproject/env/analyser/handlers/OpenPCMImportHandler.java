package eu.cloudscaleproject.env.analyser.handlers;

import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.analyser.dialogs.ImportPCMDialog;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class OpenPCMImportHandler {
	
	private Logger logger = Logger.getLogger(OpenPCMImportHandler.class.getName());

	@Execute
	public void execute(){
		
		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		
		if(project != null){
			ImportPCMDialog dialog = new ImportPCMDialog(project, shell);
			dialog.open();
		}
		else{
			logger.warning("Can't open dialog! Current project can not be retrieved!");
		}
	}

}
