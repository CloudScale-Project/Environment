package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.common.interfaces.IContextProvider;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public interface IExplorerNode extends IAdaptable, IContextProvider{
	
	public static final String PROP_CHILD_ADDED = "eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode.childAdded";
	public static final String PROP_CHILD_REMOVED = "eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode.childRemoved";

	public static final String PROP_NAME = "eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode.name";
	public static final String PROP_ICON = "eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode.icon";
	
	public static final String PROP_REFRESH = "eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode.refresh";
	public static final String PROP_REFRESH_RECURSIVE = "eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode.refreshRecursive";

	
	public String getID();
	
	public void setName(String name);
	public String getName();
	
	public void setIcon(Image icon, boolean disposeable);
	public Image getIcon();
	
	public IEclipseContext getContext();
	
	public void addNodeChildren(final IExplorerNodeChildren children);
	public void removeNodeChildren(final IExplorerNodeChildren children);

	public IExplorerNode getParent();
	public boolean hasChildren();
	public IExplorerNode[] getChildren();
	
	public void setData(Object data);
	public void setDefaultAction(String commandID);
	
	public void onSelect();
	public void onDefaultAction();
	
	public void refresh();
	public void refreshRecursive();

	public void dispose();

	public void addPropertyChangeListener(PropertyChangeListener pcl);
	public void removePropertyChangeListener(PropertyChangeListener pcl);
	
	public void addPropertyChangeListener(String prop, PropertyChangeListener pcl);
	public void removePropertyChangeListener(String prop, PropertyChangeListener pcl);
}
