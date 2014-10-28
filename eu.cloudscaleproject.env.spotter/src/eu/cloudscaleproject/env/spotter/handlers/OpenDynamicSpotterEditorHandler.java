 
package eu.cloudscaleproject.env.spotter.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;

import eu.cloudscaleproject.env.common.CommandExecutor;

public class OpenDynamicSpotterEditorHandler{

	@Execute
	public void execute(CommandExecutor ex,
						@Optional @Named("eu.cloudscaleproject.env.dynamicspotter.commandparameter.action") String action) {
		
		ex.execute("eu.cloudscaleproject.env.toolchain.command.openprojecteditor", 
				   new String[]{"eu.cloudscaleproject.env.dynamicspotter.tabitemextension", action});
	}
}	