package eu.cloudscaleproject.env.spotter.handlers;

import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.spotter.eclipse.ui.util.DialogUtils;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.spotter.dialogs.RunAlternativeDialog;

public class DynamicSpotterRunHandler {
	
	private final static Logger logger = Logger.getLogger(DynamicSpotterRunHandler.class.getName());

	@Execute
	public void execute(StatusManager statusManager){
		
		//open dialog to select run alternative (confEditorInput)
		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		
		if(project == null){
			logger.severe("Can't open dialog! Current project can not be retrieved!");
			logger.severe("Exiting spotter run opration!");
			return;
		}
		
		if(statusManager != null){
			IToolStatus status  = statusManager.getStatus(project, StatusManager.Tool.DYNAMIC_SPOTTER.getID());
			if(!status.isDone()){
				String msg = "Can not run dynamic spotter analyses. Check and fix configuration!";
				logger.warning(msg);
				DialogUtils.openWarning("DynamicSpotter Diagnosis", msg);
				return;
			}
		}
		
		RunAlternativeDialog dialog = new RunAlternativeDialog(project, shell);
		dialog.open();
		
	}
	
}
