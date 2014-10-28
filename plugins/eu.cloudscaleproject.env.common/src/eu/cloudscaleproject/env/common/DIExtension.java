package eu.cloudscaleproject.env.common;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;

/**
 * 
 * Base class used by extension point classes.
 * It adds dependency injection support.
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public abstract class DIExtension implements IExecutableExtensionFactory{

	@Override
	public Object create() throws CoreException {
		return CloudscaleContext.createInstance(this.getClass());
	}

}
