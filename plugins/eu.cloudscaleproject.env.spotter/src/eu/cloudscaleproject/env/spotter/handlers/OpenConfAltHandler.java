package eu.cloudscaleproject.env.spotter.handlers;

import org.eclipse.e4.core.di.annotations.Execute;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.spotter.editors.SpotterTabItemExtension;

public class OpenConfAltHandler {
	
	@Execute
	public void execute(CommandExecutor ex){
		
		ex.execute("eu.cloudscaleproject.env.toolchain.command.openprojecteditor",
				SpotterTabItemExtension.ID,
				SpotterTabItemExtension.ACTION_OPEN_RUN);
	}
	
}
