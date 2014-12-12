package eu.cloudscaleproject.env.usageevolution.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.usageevolution.editors.ScaleDLTabItemExtension;

public class OpenDashboardHandler {

	@Execute
	public void execute(CommandExecutor ex,
						@Optional @Named("eu.cloudscaleproject.env.analyser.commandparameter.action") String action) {
		
		ex.execute("eu.cloudscaleproject.env.toolchain.command.openprojecteditor",ScaleDLTabItemExtension.ID, action);
	}
}
