 
package eu.cloudscaleproject.env.analyser.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class OpenIntroHandler {
	
	private static final String introID = "eu.cloudscaleproject.env.analyser.perspective.intro";
	@Execute
	public void execute(MApplication application, EModelService service, EPartService partService) {
		MPerspective p = (MPerspective) service.find(introID, application);
		partService.switchPerspective(p);
	}
		
}