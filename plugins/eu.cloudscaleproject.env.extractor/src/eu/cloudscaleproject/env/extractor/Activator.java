package eu.cloudscaleproject.env.extractor;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import eu.cloudscaleproject.env.extractor.alternatives.ConfigResourceProviderFactory;
import eu.cloudscaleproject.env.extractor.alternatives.ResultResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class Activator extends AbstractUIPlugin {

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

		ResourceRegistry.getInstance().registerFactory(
				ToolchainUtils.EXTRACTOR_CONF_ID,
				new ConfigResourceProviderFactory()
		);

		ResourceRegistry.getInstance().registerFactory(
				ToolchainUtils.EXTRACTOR_RES_ID,
				new ResultResourceProviderFactory()
		);
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
