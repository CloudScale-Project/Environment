 
package eu.cloudscaleproject.env.analyser.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;

import eu.cloudscaleproject.env.analyser.editors.AnalyserTabItemExtension;
import eu.cloudscaleproject.env.common.CommandExecutor;

public class OpenAnalyserEditorHandler {
	
	@Execute
	public void execute(CommandExecutor ex,
						@Optional @Named("eu.cloudscaleproject.env.analyser.commandparameter.action") String action) {
		
		ex.execute("eu.cloudscaleproject.env.toolchain.command.openprojecteditor",AnalyserTabItemExtension.ID, action);
	}
		
}