 
package eu.cloudscaleproject.env.staticspotter.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.staticspotter.editors.StaticSpotterTabItemExtension;

public class StaticSpotterEditorHandler {

	@Execute
	public void execute(CommandExecutor ex,
						@Optional @Named("eu.cloudscaleproject.env.staticspotter.commandparameter.action") String action) {
		
		ex.execute("eu.cloudscaleproject.env.toolchain.command.openprojecteditor", StaticSpotterTabItemExtension.ID, action);
	}
		
}