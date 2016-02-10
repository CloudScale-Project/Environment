package eu.cloudscaleproject.env.spotter.integration;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;
	
	public static String STATIC_SPOTTER_PROBLEM_EXTENSION = 
			"org.spotter.ext.detection.staticspotter.olb.StaticSpotterSynchronizedMethodDetectionController";
	public static String STATIC_SPOTTER_PROBLEM_DETECTIBLE = 
			"org.spotter.detection.detectable";
	public static String STATIC_SPOTTER_PROBLEM_FILE = 
			"static_spotter_export_file";

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
