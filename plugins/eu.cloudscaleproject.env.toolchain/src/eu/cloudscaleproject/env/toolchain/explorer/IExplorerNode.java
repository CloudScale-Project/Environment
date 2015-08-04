package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeListener;

import org.eclipse.e4.core.contexts.IEclipseContext;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public interface IExplorerNode {
	
	public String getID();
	public String getName();
	
	public IEclipseContext getContext();
	
	public IExplorerNode getParent();	
	public IExplorerNode[] getChildren();
	
	public void dispose();

	public void addPropertyChangeListener(PropertyChangeListener pcl);
	public void removePropertyChangeListener(PropertyChangeListener pcl);
	
	public void addPropertyChangeListener(String prop, PropertyChangeListener pcl);
	public void removePropertyChangeListener(String prop, PropertyChangeListener pcl);
}
