
package eu.cloudscaleproject.env.product.addons;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimBar;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimElement;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.swt.widgets.Display;
import org.osgi.service.event.Event;

public class BottomTrimbarAddon
{

	@Inject
	@Optional
	public void applicationStarted(@EventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event, final MApplication app) {
		
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				showProgressBar(app);
			}
		});
		
	}
	
	private void showProgressBar(MApplication app){
		MTrimmedWindow window = (MTrimmedWindow) app.getChildren().get(0);
		
		for(MTrimBar trimbar : window.getTrimBars()){
			for(MTrimElement element : trimbar.getChildren()){
				if("org.eclipse.ui.ProgressBar".equals(element.getElementId())){
					element.setToBeRendered(true);
					element.setVisible(true);
				}

			}
		}
	}

}
