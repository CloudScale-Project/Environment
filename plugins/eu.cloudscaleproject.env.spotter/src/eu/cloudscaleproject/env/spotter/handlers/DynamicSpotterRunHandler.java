package eu.cloudscaleproject.env.spotter.handlers;

import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.spotter.eclipse.ui.util.DialogUtils;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public class DynamicSpotterRunHandler {
	
	private final static Logger logger = Logger.getLogger(DynamicSpotterRunHandler.class.getName());

	@Execute
	public void execute(@Active IProject project, @Optional IValidationStatusProvider statusProvider){
		
		//open dialog to select run alternative (confEditorInput)
		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		
		if(project == null){
			logger.severe("Can't open dialog! Current project can not be retrieved!");
			logger.severe("Exiting spotter run opration!");
			return;
		}
		
		if(statusProvider != null){
			if(!statusProvider.getSelfStatus().isDone()){
				String msg = "Can not run dynamic spotter analyses. Check and fix configuration!";
				logger.warning(msg);
				DialogUtils.openWarning("DynamicSpotter Diagnosis", msg);
				return;
			}
		}
		
		//RunAlternativeDialog dialog = new RunAlternativeDialog(project, shell);
		//dialog.open();
	}
	
}
