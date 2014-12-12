package eu.cloudscaleproject.env.common.explorer.notification;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;

public interface ExplorerChangeListener {

	public IResource[] getResources();
	public void resourceChanged(IResourceDelta delta);
	
}
