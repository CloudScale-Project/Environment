 
package eu.cloudscaleproject.env.overview.handlers;


import org.eclipse.e4.core.di.annotations.Execute;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.overview.editors.OverviewTabItemExtension;

public class OpenOverviewEditorHandler {

	@Execute
	public void execute(CommandExecutor ex) {
		
		ex.execute("eu.cloudscaleproject.env.toolchain.command.openprojecteditor", OverviewTabItemExtension.ID);
	}
}	