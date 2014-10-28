 
package eu.cloudscaleproject.env.analyser.handlers;

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

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class OpenRunConfiguration {
	
	@Execute
	public void execute() {

		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
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