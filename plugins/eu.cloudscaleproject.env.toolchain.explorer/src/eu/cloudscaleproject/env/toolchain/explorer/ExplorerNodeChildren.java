package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffVisitor;

import eu.cloudscaleproject.env.common.BatchExecutor;

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
	
	protected boolean isInitialized = false;
	protected boolean isDisposed = false;
	
	//prevents multiple threads to access refresh method simultaneously
	//do not use it in other methods!
	private final ReentrantLock refreshLock = new ReentrantLock();
	//private final Object refreshLock = new Object();
	
	//lock used for locking nodes array
	private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
	
	//Property change events stack
	private final Object eventsLock = new Object();
	private Stack<PropertyChangeEvent> events = new Stack<PropertyChangeEvent>();
		
	public ExplorerNodeChildren(boolean lazyLoad) {
		this.lazyLoad = lazyLoad;
	}
	
	protected IExplorerNode getNode(){
		return node;
	}
	
	public void initialize(IExplorerNode node){
		this.node = node;
		isDisposed = false;

		if(!lazyLoad){
			doInitialize();
		}
	}
	
	private void doInitialize(){
		isInitialized = true;
		isDisposed = false;

		try{
			lock.writeLock().lock();
			keys.clear();
			nodes.clear();
		}
		finally{
			lock.writeLock().unlock();
		}
		
		doRefresh();
	}
	
	public boolean hasChildren(){
		
		if(isDisposed){
			return false;
		}
		
		if(!isInitialized){
			doInitialize();
		}
		
		List<? extends Object> keys = getKeys();
		if(keys == null){
			return false;
		}
		
		return !keys.isEmpty();
	}
	
	public List<IExplorerNode> getChildren(){
		
		List<IExplorerNode> out = new ArrayList<IExplorerNode>();
		
		if(isDisposed){
			return out;
		}
		
		if(!isInitialized){
			doInitialize();
		}
		
		try{
			lock.readLock().lock();
			for(IExplorerNode node : nodes){
				if(node != null){
					out.add(node);
				}
			}
		}
		finally{
			lock.readLock().unlock();
		}
		
		return out;
	}
	
	public void refresh(){
		
		if(isDisposed){
			return;
		}
		
		BatchExecutor.getInstance().addTask(this, "refresh",  new Runnable() {
			
			@Override
			public void run() {
				doRefresh();
			}
		});
	}
	
	public void refreshNow(){
		doRefresh();
	}
	
	private void doRefresh(){
		
		if(isDisposed){
			return;
		}
		
		if(isInitialized){
			
			//Forces threads to execute the following code sequentially
			//If the lock can not be acquired, delayed refresh is triggered 
			if(refreshLock.tryLock()){
				try{
					
					final List<IExplorerNode> nodesCopy;
					try{
						lock.writeLock().lock();
						nodesCopy = new ArrayList<IExplorerNode>(nodes);
					}
					finally{
						lock.writeLock().unlock();
					}
						
					//try to resolve null nodes
					for(int i=0; i<nodesCopy.size(); i++){
						IExplorerNode node = nodesCopy.get(i);
						if(node == null){
							node = getChild(keys.get(i));
							if(node != null){
								nodesCopy.set(i, node);
								addIndexedPropertyChange(PROP_CHILD_ADDED, i, null, node);
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
							nodesCopy.add(index, node);
							keys.add(index, element);
							
							if(node != null){
								addIndexedPropertyChange(PROP_CHILD_ADDED, index, null, node);
							}
						}
						
						@Override
						public void handleRemove(int index, Object element) {
							
							IExplorerNode node = nodesCopy.get(index);
							nodesCopy.remove(index);
							keys.remove(index);
							
							if(node != null){
								node.dispose();
								addIndexedPropertyChange(PROP_CHILD_REMOVED, index, node, null);
							}
						}
						
					});
					
				
					try{
						lock.writeLock().lock();
						nodes.clear();
						nodes.addAll(nodesCopy);
					}
					finally{
						lock.writeLock().unlock();
					}
					
					flushPropertyChange();
				
				}
				finally{
					refreshLock.unlock();
				}
			}
			else{
				//refresh lock can not be acquired
				//in this case trigger batched refresh request
				refresh();
			}
		
		}
		
	}
	
	public void dispose(){
		
		try{
			refreshLock.lock();

			try{
				lock.readLock().lock();
				for(IExplorerNode node : nodes){
					if(node != null){
						node.dispose();
					}
				}
			}
			finally{
				lock.readLock().unlock();
			}
			
			try{
				lock.writeLock().lock();
				keys.clear();
				nodes.clear();
			}
			finally{
				lock.writeLock().unlock();
			}
			
			isDisposed = true;
		}
		finally{
			refreshLock.unlock();
		}
		
	}

	protected abstract List<? extends Object> getKeys();
	public abstract IExplorerNode getChild(Object key);

	public void addPropertyChangeListener(PropertyChangeListener pcl){
		pcs.addPropertyChangeListener(pcl);
	}
	
	private void addIndexedPropertyChange(String name, int index, Object oldValue, Object newValue){
		synchronized (eventsLock) {
			IndexedPropertyChangeEvent evt = new IndexedPropertyChangeEvent(this, name, oldValue, newValue, index);
			events.push(evt);
		}
	}
	
	@SuppressWarnings("unused")
	private void addPropertyChange(String name, int index, Object oldValue, Object newValue){
		synchronized (eventsLock) {
			PropertyChangeEvent evt = new PropertyChangeEvent(this, name, oldValue, newValue);
			events.push(evt);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void flushPropertyChange(){
		
		Stack<PropertyChangeEvent> currentStack;
		
		synchronized (eventsLock) {
			currentStack = (Stack<PropertyChangeEvent>)events.clone();
			events.clear();
		}
			
		while (!currentStack.isEmpty()) {
			PropertyChangeEvent evt = currentStack.pop();
			pcs.firePropertyChange(evt);
		}
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
