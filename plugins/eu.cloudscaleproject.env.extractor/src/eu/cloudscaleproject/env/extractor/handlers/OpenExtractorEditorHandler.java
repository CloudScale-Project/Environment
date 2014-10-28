 
package eu.cloudscaleproject.env.extractor.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.extractor.editors.ExtractorTabItemExtension;

public class OpenExtractorEditorHandler {

	@Execute
	public void execute(CommandExecutor ex,
						@Optional @Named("eu.cloudscaleproject.env.extractor.commandparameter.action") String action) {
		
		ex.execute("eu.cloudscaleproject.env.toolchain.command.openprojecteditor", ExtractorTabItemExtension.ID, action);
	}
}	