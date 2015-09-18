package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffVisitor;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public abstract class ExplorerNodeChildren implements IExplorerNodeChildren{
	
	public static final String PROP_REFRESH = "eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory.refresh";
	public static final String PROP_CHILD_ADDED = "eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory.childAdded";
	public static final String PROP_CHILD_REMOVED = "eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory.childRemoved";
	
	private IExplorerNode node;
	
	private final List<Object> keys = new ArrayList<Object>();
	
	//Nodes in the following list can be NULL!
	private final List<IExplorerNode> nodes = new ArrayList<IExplorerNode>();
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private final boolean lazyLoad;
	protected boolean initialized = false;

	public ExplorerNodeChildren(boolean lazyLoad) {
		this.lazyLoad = lazyLoad;
	}
	
	protected IExplorerNode getNode(){
		return node;
	}
	
	public void initialize(IExplorerNode node){
		this.node = node;
		if(!lazyLoad){
			doInitialize();
		}
	}
	
	private void doInitialize(){
		initialized = true;
		
		keys.clear();
		nodes.clear();
		
		refresh();
	}
	
	public boolean hasChildren(){
		
		if(!initialized){
			doInitialize();
		}
		
		List<? extends Object> keys = getKeys();
		if(keys == null){
			return false;
		}
		
		return !keys.isEmpty();
	}
	
	public List<IExplorerNode> getChildren(){
		
		if(!initialized){
			doInitialize();
		}
		
		List<IExplorerNode> out = new ArrayList<IExplorerNode>();
		for(IExplorerNode node : nodes){
			if(node != null){
				out.add(node);
			}
		}
		return out;
	}
	
	public synchronized void refresh(){
		
		if(!initialized){
			return;
		}
		
		//try to resolve null nodes
		for(int i=0; i<nodes.size(); i++){
			IExplorerNode node = nodes.get(i);
			if(node == null){
				node = getChild(keys.get(i));
				if(node != null){
					nodes.set(i, node);
					pcs.fireIndexedPropertyChange(PROP_CHILD_ADDED, i, null, node);
				}
			}
		}
		
		//create nodes for the new keys
		List<? extends Object> currentKeys = getKeys();
		if(currentKeys == null){
			return;
		}
		
		ListDiff diff = Diffs.computeListDiff(keys, currentKeys);
		
		diff.accept(new ListDiffVisitor() {
			
			@Override
			public void handleAdd(int index, Object element) {
				
				IExplorerNode node = getChild(element);
				nodes.add(index, node);
				keys.add(index, element);
				
				if(node != null){
					pcs.fireIndexedPropertyChange(PROP_CHILD_ADDED, index, null, node);
				}
			}
			
			@Override
			public void handleRemove(int index, Object element) {
				
				IExplorerNode node = nodes.get(index);
				nodes.remove(index);
				keys.remove(index);
				
				if(node != null){
					node.dispose();
					pcs.fireIndexedPropertyChange(PROP_CHILD_REMOVED, index, node, null);
				}
			}
			
		});
		
	}
	
	public synchronized void dispose(){
		for(IExplorerNode node : nodes){
			if(node != null){
				node.dispose();
			}
		}
		
		keys.clear();
		nodes.clear();
	}

	protected abstract List<? extends Object> getKeys();
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
