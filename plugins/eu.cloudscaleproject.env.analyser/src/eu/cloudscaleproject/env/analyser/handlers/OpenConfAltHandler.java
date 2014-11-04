package eu.cloudscaleproject.env.analyser.handlers;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.cloudscaleproject.env.analyser.ConfAlternative;
import eu.cloudscaleproject.env.analyser.InputAlternative;
import eu.cloudscaleproject.env.analyser.ResourceUtils;
import eu.cloudscaleproject.env.analyser.dialogs.SelectConfAltDialog;
import eu.cloudscaleproject.env.analyser.editors.AnalyserTabItemExtension;
import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
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
		
		IToolStatus inputStatus = statusManager.getStatus(project, StatusManager.Tool.ANALYSER_INPUT.getID());
		if(!inputStatus.isDone()){
			DialogUtils.openInformation("Analyser workflow diagram", "Analyser input does not validate!");
			return;
		}
		
		ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		ResourceProvider confResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_CONF_ID);

		IEditorInputResource selectedInputResource = inputResourceProvider.getTaggedResource(ResourceProvider.TAG_SELECTED);
		if(selectedInputResource == null && !inputResourceProvider.getResources().isEmpty()){
			selectedInputResource = inputResourceProvider.getResources().get(0);
		}
		
		if(selectedInputResource == null){
			DialogUtils.openInformation("Analyser workflow diagram", "Please create and select input alternative first.");
		}
				
		List<ConfAlternative> alternatives = ResourceUtils.getConfAlternatives(project, (InputAlternative)selectedInputResource);
		
		if(alternatives.isEmpty()){
			//TODO: ask user to create new conf alternative
			boolean create = DialogUtils.openConfirm("Analyser workflow diagram", 
					"Configuration alternative for the specified input alternative does not exist! Create new configuration alternative?");
			
			if(create){
				ConfAlternative newConfAlt = (ConfAlternative)confResourceProvider.createNewResource(selectedInputResource.getName() + " conf.");
				newConfAlt.setInput((InputAlternative)selectedInputResource);
				newConfAlt.save();
				
				ex.execute("eu.cloudscaleproject.env.toolchain.command.openprojecteditor",
						AnalyserTabItemExtension.ID, AnalyserTabItemExtension.ACTION_OPEN_RUN);
			}
			return;
		}
		
		SelectConfAltDialog dialog = new SelectConfAltDialog(project, shell, alternatives);
		CloudscaleContext.inject(dialog);
		dialog.open();
		
	}
	
}
