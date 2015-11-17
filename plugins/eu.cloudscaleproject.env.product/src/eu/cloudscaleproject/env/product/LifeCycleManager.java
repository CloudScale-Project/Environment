package eu.cloudscaleproject.env.product;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.IWindowCloseHandler;

import eu.cloudscaleproject.env.common.CloudscaleContext;

public class LifeCycleManager {
	
	@PostContextCreate
	public void configureContext(IEclipseContext applicationContext){
		CloudscaleContext.initialize(applicationContext);
	}


	@ProcessAdditions
	void processAdditions(MApplication app, EModelService modelService) {
		
	}
	
	@Inject
    @Optional
    public void startupComplete(@UIEventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) MApplication application,
            																			 EModelService modelService){

        MWindow window = (MWindow) modelService.find("eu.cloudscaleproject.env.product.trimmedwindow.0", application);
        window.getContext().set(IWindowCloseHandler.class, new WindowCloseHandler());
    }

	@PreSave
	public void preSave() {

	}

	private static class WindowCloseHandler implements IWindowCloseHandler {

		@Override
		public boolean close(MWindow window) {
			
			EPartService partService = window.getContext().get(EPartService.class);
			partService.saveAll(true);

			return true;
		}
	}
}
