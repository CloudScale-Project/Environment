package eu.cloudscaleproject.env.extractor.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IPreferenceConstants;
import org.eclipse.ui.internal.WorkbenchPlugin;

public class OpenExtractorHandler {
	
	private static final String archimetrixPerspective = "org.archimetrix.commons.extension.ui.perspectives.ArchimetrixPerspective";
	
	@Execute
	public void execute(IWorkbench workbench, IWorkbenchWindow window, MApplication application, EModelService service, EPartService partService) {
		try {
			
			//Don't touch this code, if you don't want to break this or any other perspective view!/////////////
			
			MPerspective p = (MPerspective) service.find(archimetrixPerspective, application);
			if (p != null)
			{
				partService.switchPerspective(p);
				return;
			}
			
			//I don't know why the @^&!%# is this necessary! /////////////////////////////////////////////////////
			IPreferenceStore store = WorkbenchPlugin.getDefault().getPreferenceStore();
			int mode = store.getInt(IPreferenceConstants.OPEN_PERSP_MODE);
			IWorkbenchPage page = window.getActivePage();
			IPerspectiveDescriptor targetPerspective = workbench.getPerspectiveRegistry().findPerspectiveWithId(
					archimetrixPerspective);

			if (IPreferenceConstants.OPM_NEW_WINDOW != mode) {
				window.getShell().open();
				if (page == null) {
					page = window.openPage(archimetrixPerspective, null);
				} else {
					page.setPerspective(targetPerspective);
				}
			}
			//////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//how it should be!
			//workbench.showPerspective(archimetrixPerspective, window);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
