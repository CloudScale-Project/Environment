package eu.cloudscaleproject.env.analyser.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.WorkbenchException;

import eu.cloudscaleproject.env.product.branding.CloudScaleBranding;

public class OpenPerspectiveHandler {
	
	@Execute
	public void execute(@Optional @Named("eu.cloudscaleproject.env.command.openPerspective.perspectiveId") String perspectiveId,
			IWorkbenchWindow window, MApplication application, EModelService service, EPartService partService) {
		
		//using old e3 show perspective call
		try {
			window.getWorkbench().showPerspective(perspectiveId, window);
			CloudScaleBranding.initProjectExplorer();
		} catch (WorkbenchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
