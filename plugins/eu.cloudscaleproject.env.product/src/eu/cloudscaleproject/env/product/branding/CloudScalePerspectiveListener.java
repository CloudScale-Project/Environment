package eu.cloudscaleproject.env.product.branding;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class CloudScalePerspectiveListener implements IPerspectiveListener{

	private final String CMD_CREATE_PROJECT = "eu.cloudscaleproject.env.product.command.newProject";
	private final String CMD_OPEN_EXAMPLE = "eu.cloudscaleproject.env.product.command.newExampleProject";
	private final String PERSPECTIVE_MAIN = "eu.cloudscaleproject.env.product.perspective.main";
 
	@Override
	public void perspectiveActivated(IWorkbenchPage page, final IPerspectiveDescriptor perspective) {
		
		if (!perspective.getId().startsWith(PERSPECTIVE_MAIN)) return;
		if (ExplorerProjectPaths.getCloudScaleProjects().length > 0) return;

		Display.getDefault().timerExec(500, new Runnable() {
			
			@Override
			public void run() {
				MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), 
						"Welcome to CloudScale Environment", 
						null,
					    "Workspace is empyt at the moment. \n"
					    + "To create new project chose one of the options below.", 
					    MessageDialog.INFORMATION, 
					    new String[] { "Create new porject", "Open example"}, 0);
				int result = dialog.open();
				
				switch (result) {
					case 0: CommandExecutor.getInstance().execute(CMD_CREATE_PROJECT); break;
					case 1: CommandExecutor.getInstance().execute(CMD_OPEN_EXAMPLE); break;
					default: break;
				}
			}
		});
	}

	@Override
	public void perspectiveChanged(IWorkbenchPage page, final IPerspectiveDescriptor perspective, String changeId) {
		
	}

}
