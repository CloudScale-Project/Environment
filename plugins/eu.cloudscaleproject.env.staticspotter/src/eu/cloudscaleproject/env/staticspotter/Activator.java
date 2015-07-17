package eu.cloudscaleproject.env.staticspotter;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import eu.cloudscaleproject.env.staticspotter.alternatives.providers.ConfigResourceProviderFactory;
import eu.cloudscaleproject.env.staticspotter.alternatives.providers.InputResourceProviderFactory;
import eu.cloudscaleproject.env.staticspotter.alternatives.providers.ResultResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin
{

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.cloudscaleproject.env.staticspotter"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;

		ResourceRegistry.getInstance().registerFactory(CSTool.SPOTTER_STA_INPUT, new InputResourceProviderFactory());
		ResourceRegistry.getInstance().registerFactory(CSTool.SPOTTER_STA_CONF, new ConfigResourceProviderFactory());
		ResourceRegistry.getInstance().registerFactory(CSTool.SPOTTER_STA_RES, new ResultResourceProviderFactory());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault()
	{
		return plugin;
	}

}
