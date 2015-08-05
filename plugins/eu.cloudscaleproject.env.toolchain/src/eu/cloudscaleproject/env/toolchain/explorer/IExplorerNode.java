package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeListener;

import org.eclipse.e4.core.contexts.IEclipseContext;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public interface IExplorerNode {
	
	public static final String PROP_CHILD_ADDED = "eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode.childAdded";
	public static final String PROP_CHILD_REMOVED = "eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode.childRemoved";

	public static final String PROP_ICON = "eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode.icon";
	
	public String getID();
	public String getName();
	
	public IEclipseContext getContext();
	
	public IExplorerNode getParent();	
	public IExplorerNode[] getChildren();
	
	public void onSelect();
	
	public void dispose();

	public void addPropertyChangeListener(PropertyChangeListener pcl);
	public void removePropertyChangeListener(PropertyChangeListener pcl);
	
	public void addPropertyChangeListener(String prop, PropertyChangeListener pcl);
	public void removePropertyChangeListener(String prop, PropertyChangeListener pcl);
}
