package eu.cloudscaleproject.env.common.explorer.notification;

import org.eclipse.core.resources.IResource;

public interface ExplorerChangeListener {

	public IResource[] getResources();
	public void resourceChanged();
	
}
