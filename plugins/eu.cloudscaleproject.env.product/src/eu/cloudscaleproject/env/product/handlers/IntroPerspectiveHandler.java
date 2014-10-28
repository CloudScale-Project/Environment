 
package eu.cloudscaleproject.env.product.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class IntroPerspectiveHandler {
	
	@Inject private Logger logger;
	
	@Execute
	public void execute(MApplication application, EModelService service, EPartService partService) {
		try {
			
			MPerspective p = (MPerspective) service.find("eu.cloudscaleproject.env.product.perspective.intro", application);
			partService.switchPerspective(p);	
		} 
		catch (ClassCastException cce)
		{
			logger.warn("IGNORING ClassCastExeption when switching perspective : "+cce.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}