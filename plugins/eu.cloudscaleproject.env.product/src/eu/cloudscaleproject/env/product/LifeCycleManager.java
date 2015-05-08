package eu.cloudscaleproject.env.product;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;

import eu.cloudscaleproject.env.common.CloudscaleContext;

public class LifeCycleManager {
	
	@PostContextCreate
	public void configureContext(IEclipseContext applicationContext){
		CloudscaleContext.initialize(applicationContext);
	}

}
