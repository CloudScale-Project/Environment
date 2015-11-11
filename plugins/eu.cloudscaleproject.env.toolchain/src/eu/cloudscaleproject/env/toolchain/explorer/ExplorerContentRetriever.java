package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import eu.cloudscaleproject.env.toolchain.resources.IExplorerContentRetriever;

public abstract class ExplorerContentRetriever implements IExplorerContentRetriever{

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	protected void refresh(){
		pcs.firePropertyChange(PROP_CHILDREN_CHANGED, null, this);
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
