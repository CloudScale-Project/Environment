package eu.cloudscaleproject.env.extractor;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.somox.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl;

public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.cloudscaleproject.env.extractor"; //$NON-NLS-1$

	private static BundleContext context;

	// The shared instance
	public static Activator plugin;

	public static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		plugin = this;
		
		try
		{
			SourceCodeDecoratorPackageImpl.init();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		plugin = null;
		Activator.context = null;
	}

	public static Activator getDefault() {
		return plugin;
	}

}
