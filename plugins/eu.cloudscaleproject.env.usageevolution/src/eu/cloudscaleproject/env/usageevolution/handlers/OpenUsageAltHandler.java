package eu.cloudscaleproject.env.usageevolution.handlers;

import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Execute;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.dialogs.ResourceSelectionDialog;

public class OpenUsageAltHandler {
	
	private Logger logger = Logger.getLogger(OpenUsageAltHandler.class.getName());
	
	@Execute
	public void execute(){
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		
		if(project != null){
			ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.USAGEEVOLUTION_ID);
			ResourceSelectionDialog.openDialog(rp, StatusManager.Tool.USAGE_EVOLUTION);
		}
		else{
			logger.warning("Can't open dialog! Current project can not be retrieved!");
		}
	}
	
}
