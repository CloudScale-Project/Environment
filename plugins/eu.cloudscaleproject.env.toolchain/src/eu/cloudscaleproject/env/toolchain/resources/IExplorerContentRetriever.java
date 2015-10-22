package eu.cloudscaleproject.env.toolchain.resources;

import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.e4.core.contexts.IEclipseContext;

public interface IExplorerContentRetriever{
	
	public static final String PROP_CHILDREN_CHANGED = "eu.cloudscaleproject.env.toolchain.resources.IExplorerContentRetriever.childrenChanged";
	
	public void initialize(String nodeID, IEclipseContext context);
	public List<Object> getChildren();
	
	public void dispose();

	public void addPropertyChangeListener(PropertyChangeListener pcl);
	public void removePropertyChangeListener(PropertyChangeListener pcl);
}
