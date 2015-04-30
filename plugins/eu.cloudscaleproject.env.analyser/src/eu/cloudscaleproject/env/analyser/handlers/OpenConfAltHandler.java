package eu.cloudscaleproject.env.analyser.handlers;

import java.util.logging.Logger;

import org.eclipse.e4.core.di.annotations.Execute;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.notification.StatusManager;

public class OpenConfAltHandler {

	private Logger logger = Logger.getLogger(OpenConfAltHandler.class.getName());
	
	@Execute
	public void execute(CommandExecutor ex, StatusManager statusManager){
		
		throw new UnsupportedOperationException("Reimplement this!");
		//TODO: reimpl 
		/*
				
		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		
		if(project == null){
			logger.warning("Can't open dialog! Current project can not be retrieved!");
			return;
		}
		
		IValidationStatus inputStatus = statusManager.getStatus(project, StatusManager.Tool.ANALYSER_INPUT.getID());
		if(!inputStatus.isDone()){
			DialogUtils.openInformation("Analyser workflow diagram", "Analyser input does not validate!");
			return;
		}
		
		final ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		final ResourceProvider confResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_CONF_ID);

		IEditorInputResource selectedInputResource = inputResourceProvider.getTaggedResource(ResourceProvider.TAG_SELECTED);
		if(selectedInputResource == null && !inputResourceProvider.getResources().isEmpty()){
			selectedInputResource = inputResourceProvider.getResources().get(0);
		}
		
		if(selectedInputResource == null){
			DialogUtils.openInformation("Analyser workflow diagram", "Please create and select input alternative first.");
		}
				
		List<ConfAlternative> alternatives = ResourceUtils.getConfAlternatives(project, (InputAlternative)selectedInputResource);
		
		if(alternatives.isEmpty()){
			
			NewConfigAlternativeDialog dialog = new NewConfigAlternativeDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
					new BasicCallback<String[]>() {
				
				@Override
				public void handle(String[] data) {
					if(confResourceProvider == null){
						throw new IllegalStateException("Sidebar resource provider not set!");
					}
					confResourceProvider.createNewResource(data[0], data[1]);
				}
			});
			dialog.open();
			return;
		}
		
		SelectConfAltDialog dialog = new SelectConfAltDialog(project, shell, 
				alternatives.toArray(new ConfAlternative[alternatives.size()]));
		CloudscaleContext.inject(dialog);
		dialog.open();
		*/
	}
	
}
