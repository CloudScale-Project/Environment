package eu.cloudscaleproject.env.analyser.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;

public class OpenPalladioHandler {
	
	private static final String palladioPerspective = "de.uka.ipd.sdq.pcmbench.perspectives.palladio";
	
	@Execute
	public void execute(IWorkbench workbench, IWorkbenchWindow window, MApplication application, EModelService service, EPartService partService) {
		try {
			MPerspective p = (MPerspective) service.find(palladioPerspective, application);
			if (p != null)
			{
				partService.switchPerspective(p);
				return;
			}
			//workbench.getPerspectiveRegistry().findPerspectiveWithId("de.uka.ipd.sdq.pcmbench.perspectives.palladio");
			workbench.showPerspective(palladioPerspective, window);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
