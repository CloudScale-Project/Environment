 
package eu.cloudscaleproject.env.analyser.handlers;

import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;

public class OpenRunConfiguration {
	
	private Logger logger = Logger.getLogger(OpenRunConfiguration.class.getName());
	
	@Execute
	public void execute(StatusManager statusManager) {

		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		if(project == null){
			logger.warning("Can't open dialog! Current project can not be retrieved!");
			return;
		}
		
		IToolStatus confStatus = statusManager.getStatus(project, StatusManager.Tool.ANALYSER.getID());
		if(!confStatus.isDone()){
			DialogUtils.openInformation("Analyser workflow diagram", "Analyser configuration in not complete. Can not run simulation.");
			return;
		}

		IFolder folder = ExplorerProjectPaths.getProjectFolder(project,
				ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
		IFolder folderConf = folder.getFolder(ExplorerProjectPaths.getProjectProperty(project,
				ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION));
		
		IFile file = null;
		try {
			for(IResource res : folderConf.members()){
				if(res.getFileExtension().equals("launch") && res instanceof IFile){
					file = (IFile)res;
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		if(file == null || !file.exists()){
			return;
		}
		
		ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfiguration launchConf = mgr.getLaunchConfiguration(file);

		DebugUITools.openLaunchConfigurationDialog(

		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				launchConf, DebugUITools.getLaunchGroup(launchConf, "run")
						.getIdentifier(), null);
	}

}