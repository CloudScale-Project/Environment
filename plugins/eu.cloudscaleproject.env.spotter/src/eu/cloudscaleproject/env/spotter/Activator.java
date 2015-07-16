package eu.cloudscaleproject.env.spotter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import eu.cloudscaleproject.env.spotter.alternatives.providers.ConfigResourceProviderFactory;
import eu.cloudscaleproject.env.spotter.alternatives.providers.InputResourceProviderFactory;
import eu.cloudscaleproject.env.spotter.alternatives.providers.ResultResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin{

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.cloudscaleproject.env.spotter"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
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
		
		//register resource provider factories		
		ResourceRegistry.getInstance().registerFactory(CSTool.SPOTTER_DYN_INPUT, 
				new InputResourceProviderFactory());
		ResourceRegistry.getInstance().registerFactory(CSTool.SPOTTER_DYN_CONF, 
				new ConfigResourceProviderFactory());
		ResourceRegistry.getInstance().registerFactory(CSTool.SPOTTER_DYN_RES, 
				new ResultResourceProviderFactory());		
		
		
		/*
		 * TODO: Execute this in the Dashboard and in workflow diagram!
		 * Exception is thrown, because workspace is not initialized at this point.
		for(IProject p : ExplorerProjectPaths.getProjects()){
			ServerService.getInstance().fetchResults(p);
		}
		*/
		
		/*
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ServerLauncher.main(new String[]{"start"});
			}
		}).start();
		*/
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

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
