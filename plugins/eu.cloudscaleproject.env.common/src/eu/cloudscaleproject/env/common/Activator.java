package eu.cloudscaleproject.env.common;

import java.util.logging.Logger;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeNotifier;
import eu.cloudscaleproject.env.common.notification.ResourceChangeReporter;

public class Activator extends AbstractUIPlugin
{

	IResourceChangeListener listener = null;

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.cloudscaleproject.env.common"; //$NON-NLS-1$

	// The shared instance
	public static Activator plugin;

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

		ResourcesPlugin.getWorkspace().addResourceChangeListener(ExplorerChangeNotifier.getInstance(), IResourceChangeEvent.POST_BUILD);

		if (listener == null)
		{
			try
			{
				//listener = CloudscaleContext.createInstance(ResourceChangeReporter.class);
				//ResourcesPlugin.getWorkspace().addResourceChangeListener(listener, IResourceChangeEvent.POST_BUILD);
			} catch (Exception e)
			{
				Logger.getLogger(getClass().getName()).severe(
						"Unable to use CloudScale Context functionality. Product will not work as predicted.");
				e.printStackTrace();
			}
		}
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

		ResourcesPlugin.getWorkspace().removeResourceChangeListener(ExplorerChangeNotifier.getInstance());

		if (listener != null)
		{
			ResourcesPlugin.getWorkspace().removeResourceChangeListener(listener);
			listener = null;
		}
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