
package eu.cloudscaleproject.env.product.handlers;

import java.util.HashMap;
import java.util.List;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.product.branding.CloudScaleBranding;

public class OpenPerspectiveHandler
{

	// Second time CSE perspective is not found anymore
	private static HashMap<String, MPerspective> _WORKAROUND_MAP = new HashMap<String, MPerspective>();

	@Execute
	public void execute(@Named("PerspectiveId") String perspectiveId, EModelService service, final MApplication application,
			final EPartService partService)
	{

		MPerspective element = (MPerspective) service.find(perspectiveId, application);

		if (element == null)
			element = _WORKAROUND_MAP.get(perspectiveId);
		else
			_WORKAROUND_MAP.put(perspectiveId, element);

		// Switch perspective
		if (element != null)
		{

			final MPerspective fElement = element;
			BusyIndicator.showWhile(Display.getDefault(), new Runnable()
			{
				@Override
				public void run()
				{
					switchPerspective(application, partService, fElement);
				}
			});
		} else
		{
			throw new IllegalArgumentException("Perspective with perspectiveId: " + perspectiveId + " does not exist!");
		}

	}

	public void switchPerspective(MApplication application, final EPartService partService, final MPerspective element)
	{

		try
		{
			partService.switchPerspective(element);
		} catch (Exception e)
		{

			// workaround: Application does not have an active window
			// Exception....
			List<MWindow> windows = application.getChildren();
			final MWindow mWindow = windows.get(0);

			Display.getDefault().syncExec(new Runnable()
			{
				@Override
				public void run()
				{
					// get the main window and make it active
					// somehow this solves the problem
					mWindow.getContext().activate();
					partService.switchPerspective(element);
				}
			});
		}

		CloudScaleBranding.initProjectExplorer();

	}
}