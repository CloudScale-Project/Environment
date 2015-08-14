 
package eu.cloudscaleproject.env.analyser.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class OpenIntroViewHandler {
	
	@Execute
	public void execute(EPartService partService) {
		partService.findPart("eu.cloudscaleproject.env.analyser.partdescriptor.analyserIntro");
	}
		
}