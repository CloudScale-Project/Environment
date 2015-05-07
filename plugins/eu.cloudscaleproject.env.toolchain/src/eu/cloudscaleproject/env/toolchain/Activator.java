package eu.cloudscaleproject.env.toolchain;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import eu.cloudscaleproject.env.common.CloudscaleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.cloudscaleproject.env.toolchain"; //$NON-NLS-1$

	// The shared instance
	public static Activator plugin;
	
	public static IEclipseContext context;
	public static IEclipseContext getToolchainContext(){
		if(context == null){
			context = CloudscaleContext.getCustomContext().createChild(Activator.class.getName());
		}
		return context;
	}
	
	/**
	 * The constructor
	 */
	public Activator() {
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;	
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
}
