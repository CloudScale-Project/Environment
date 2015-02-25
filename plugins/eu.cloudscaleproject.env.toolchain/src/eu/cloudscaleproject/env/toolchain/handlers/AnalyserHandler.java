 
package eu.cloudscaleproject.env.toolchain.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;

public class AnalyserHandler {
	
	@Execute
	public void execute(ProjectEditor editor) {
		//TODO Your code goes here
	}
	
	
	@CanExecute
	public boolean canExecute() {
		//TODO Your code goes here
		return true;
	}
		
}