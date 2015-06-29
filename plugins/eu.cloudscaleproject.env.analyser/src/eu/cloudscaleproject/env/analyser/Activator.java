package eu.cloudscaleproject.env.analyser;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.palladiosimulator.edp2.models.measuringpoint.impl.MeasuringpointPackageImpl;
import org.palladiosimulator.experimentautomation.experiments.impl.ExperimentsPackageImpl;
import org.palladiosimulator.monitorrepository.impl.MonitorRepositoryPackageImpl;
import org.palladiosimulator.pcm.allocation.impl.AllocationPackageImpl;
import org.palladiosimulator.pcm.repository.impl.RepositoryPackageImpl;
import org.palladiosimulator.pcm.resourceenvironment.impl.ResourceenvironmentPackageImpl;
import org.palladiosimulator.pcm.system.impl.SystemPackageImpl;
import org.palladiosimulator.pcm.usagemodel.impl.UsagemodelPackageImpl;
import org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl;
import org.scaledl.usageevolution.impl.UsageevolutionPackageImpl;


/**
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 * 
 * The activator class controls the plug-in life cycle
 * 
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.cloudscaleproject.env.analyser"; //$NON-NLS-1$

	// The shared instance
	public static Activator plugin;
	public static BundleContext bundleContext;
	
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
		bundleContext = context;

		long time = System.currentTimeMillis();
		RepositoryPackageImpl.init();
		SystemPackageImpl.init();
		ResourceenvironmentPackageImpl.init();
		AllocationPackageImpl.init();
		UsagemodelPackageImpl.init();
		UsageevolutionPackageImpl.init();
		
		ExperimentsPackageImpl.init();
		MonitorRepositoryPackageImpl.init();
		ServicelevelObjectivePackageImpl.init();
		MeasuringpointPackageImpl.init();

		System.out.println("INIT TIME: "+(System.currentTimeMillis()-time));

		ResourceUtils.registerResourceFactories();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		bundleContext = null;
		super.stop(context);
	}
	
	public static BundleContext getContext(){
		return bundleContext;
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
