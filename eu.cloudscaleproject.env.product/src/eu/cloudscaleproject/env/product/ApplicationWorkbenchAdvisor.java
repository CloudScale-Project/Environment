package eu.cloudscaleproject.env.product;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.osgi.framework.Bundle;

import eu.cloudscaleproject.env.product.branding.CloudScaleBranding;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "eu.cloudscaleproject.env.product.perspective"; //$NON-NLS-1$

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
	
	@Override
	public void postStartup() {

		super.postStartup();
		
		CloudScaleBranding.initialize();
	}
	
	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		super.initialize(configurer);
		
		 // here's some of my code that does some typical RCP type configuration
	     configurer.setSaveAndRestore(true);
	     PlatformUI.getPreferenceStore().setValue(
	            IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, false);
	     
		// inserted: register workbench adapters
		IDE.registerAdapters();

		// inserted: register images for rendering explorer view
		final String ICONS_PATH = "icons/full/";
		final String PATH_OBJECT = ICONS_PATH + "obj16/";
		Bundle ideBundle = Platform.getBundle(IDEWorkbenchPlugin.IDE_WORKBENCH);
		declareWorkbenchImage(configurer, ideBundle,
			IDE.SharedImages.IMG_OBJ_PROJECT, PATH_OBJECT + "prj_obj.gif", true);
		declareWorkbenchImage(configurer, ideBundle,
			IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED, PATH_OBJECT + "cprj_obj.gif", true);
	}

	private void declareWorkbenchImage(IWorkbenchConfigurer configurer_p,
			Bundle ideBundle, String symbolicName, String path, boolean shared) {
		URL url = ideBundle.getEntry(path);
		ImageDescriptor desc = ImageDescriptor.createFromURL(url);
		configurer_p.declareImage(symbolicName, desc, shared);
	}
}
