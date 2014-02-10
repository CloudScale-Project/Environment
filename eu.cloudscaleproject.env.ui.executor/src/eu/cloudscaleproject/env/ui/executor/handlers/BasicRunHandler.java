 
package eu.cloudscaleproject.env.ui.executor.handlers;

import org.eclipse.e4.core.di.annotations.Execute;

import eu.cloudscaleproject.env.toolchain.definition.IAnalyser;

public class BasicRunHandler {
	
	@Execute
	public void execute(IAnalyser analyser) {
		analyser.analyse();
	}
}