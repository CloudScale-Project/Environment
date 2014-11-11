package eu.cloudscaleproject.env.spotter.handlers;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.spotter.ResourceUtils;
import eu.cloudscaleproject.env.spotter.dialogs.SelectConfAltDialog;
import eu.cloudscaleproject.env.spotter.editors.SpotterTabItemExtension;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class OpenConfAltHandler {

	private Logger logger = Logger.getLogger(OpenConfAltHandler.class.getName());
	
	@Execute
	public void execute(CommandExecutor ex, StatusManager statusManager){
				
		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		
		if(project == null){
			logger.warning("Can't open dialog! Current project can not be retrieved!");
			return;
		}
		
		IToolStatus inputStatus = statusManager.getStatus(project, StatusManager.Tool.DYNAMIC_SPOTTER.getID());
		if(!inputStatus.isDone()){
			DialogUtils.openInformation("Analyser workflow diagram", "Analyser input does not validate!");
			return;
		}
		
		ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_INPUT_ID);
		ResourceProvider confResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_CONF_ID);

		IEditorInputResource selectedInputResource = inputResourceProvider.getTaggedResource(ResourceProvider.TAG_SELECTED);
		if(selectedInputResource == null && !inputResourceProvider.getResources().isEmpty()){
			selectedInputResource = inputResourceProvider.getResources().get(0);
		}
		
		if(selectedInputResource == null){
			DialogUtils.openInformation("Analyser workflow diagram", "Please create and select input alternative first.");
		}
				
		List<EditorInputFolder> alternatives = ResourceUtils.getConfAlternatives(project, (EditorInputFolder)selectedInputResource);
		
		if(alternatives.isEmpty()){
			//TODO: ask user to create new conf alternative
			boolean create = DialogUtils.openConfirm("Analyser workflow diagram", 
					"Configuration alternative for the specified input alternative does not exist! Create new configuration alternative?");
			
			if(create){
				EditorInputFolder newConfAlt = (EditorInputFolder)confResourceProvider.createNewResource(selectedInputResource.getName() + " conf.");
				ResourceUtils.bindEditorInputs((EditorInputFolder)selectedInputResource, newConfAlt);
				newConfAlt.save();
				
				ex.execute("eu.cloudscaleproject.env.toolchain.command.openprojecteditor",
						SpotterTabItemExtension.ID, SpotterTabItemExtension.ACTION_OPEN_RUN);
			}
			return;
		}
		
		SelectConfAltDialog dialog = new SelectConfAltDialog(project, shell, alternatives);
		CloudscaleContext.inject(dialog);
		dialog.open();
		
	}
	
}
