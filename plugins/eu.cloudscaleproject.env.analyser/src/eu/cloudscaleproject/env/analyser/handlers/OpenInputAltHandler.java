package eu.cloudscaleproject.env.analyser.handlers;

import java.util.logging.Logger;

import org.eclipse.e4.core.di.annotations.Execute;

public class OpenInputAltHandler {
	
	private Logger logger = Logger.getLogger(OpenInputAltHandler.class.getName());
	
	@Execute
	public void execute(){
		
		throw new UnsupportedOperationException("Reimplement this!");
		//TODO: reimpl
		/*
		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		
		if(project != null){
			SelectInputAltDialog dialog = new SelectInputAltDialog(project, shell);
			CloudscaleContext.inject(dialog);
			dialog.open();
		}
		else{
			logger.warning("Can't open dialog! Current project can not be retrieved!");
		}
		*/
	}
	
}
