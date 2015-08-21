package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public interface IExplorerNodeChildren {

	public void initialize(IExplorerNode node);
	
	public boolean hasChildren();
	public List<IExplorerNode> getChildren();
	
	public void refresh();
	public void dispose();
	
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public void removePropertyChangeListener(PropertyChangeListener listener);

}
