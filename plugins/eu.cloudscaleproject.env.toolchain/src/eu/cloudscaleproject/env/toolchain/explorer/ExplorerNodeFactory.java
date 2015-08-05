package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffEntry;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public abstract class ExplorerNodeFactory {
	
	public static final String PROP_CHILD_ADDED = "eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory,childAdded";
	public static final String PROP_CHILD_REMOVED = "eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory,childAdded";
	
	private IExplorerNode node;
	
	private final List<Object> keys = new ArrayList<Object>();
	private final List<IExplorerNode> nodes = new ArrayList<IExplorerNode>();
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public ExplorerNodeFactory() {
		
	}
	
	public void initialize(IExplorerNode node){
		this.node = node;
		keys.clear();
		refresh();
	}
	
	public IExplorerNode getNode(){
		return node;
	}
	
	public void refresh(){
		List<? extends Object> currentKeys = getKeys();
		ListDiff diff = Diffs.computeListDiff(keys, currentKeys);
		
		for(ListDiffEntry entry : diff.getDifferences()){
			if(entry.isAddition()){
				
				IExplorerNode node = getChild(entry.getElement());
				nodes.add(entry.getPosition(), node);
				
				pcs.fireIndexedPropertyChange(PROP_CHILD_ADDED, entry.getPosition(), null, node);
			}
			else{
				
				IExplorerNode node = nodes.get(entry.getPosition());
				nodes.remove(entry.getPosition());
				
				pcs.fireIndexedPropertyChange(PROP_CHILD_REMOVED, entry.getPosition(), node, null);
			}
		}
		
		diff.applyTo(keys);
	}
	
	public void dispose(){
		//override
	}

	public abstract List<? extends Object> getKeys();
	public abstract IExplorerNode getChild(Object key);

	public void addPropertyChangeListener(PropertyChangeListener pcl){
		pcs.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl){
		pcs.removePropertyChangeListener(pcl);
	}
	
	public void addPropertyChangeListener(String propName, PropertyChangeListener pcl){
		pcs.addPropertyChangeListener(propName, pcl);
	}
	
	public void removePropertyChangeListener(String propName, PropertyChangeListener pcl){
		pcs.removePropertyChangeListener(propName, pcl);
	}
	
}
