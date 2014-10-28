 
package eu.cloudscaleproject.env.spotter.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.WorkbenchException;

public class DynamicSpotterPerspectiveHandler {
	@Execute
	public void execute(IWorkbenchWindow window, MApplication application, EModelService service, EPartService partService) {
		//using old e3 show perspective call
				try {
					window.getWorkbench().showPerspective("org.spotter.eclipse.ui.Perspective", window);
				} catch (WorkbenchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
		
}