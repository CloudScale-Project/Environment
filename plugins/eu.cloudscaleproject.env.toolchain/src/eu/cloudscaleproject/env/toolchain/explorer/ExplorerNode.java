package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.graphics.Image;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerNode implements IExplorerNode{
	
	public static final String PROP_CHILD_ADDED = "eu.cloudscaleproject.env.toolchain.explorer.childAdded";
	public static final String PROP_CHILD_REMOVED = "eu.cloudscaleproject.env.toolchain.explorer.childRemoved";


	private final String id;
	private final String name;
	
	private Image icon;
	
	private final IEclipseContext context;
	private final ExplorerNodeFactory childFactory;
	
	protected IExplorerNode parent;
	private final List<IExplorerNode> children = new ArrayList<IExplorerNode>();
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private final PropertyChangeListener factoryListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(ExplorerNodeFactory.PROP_CHILD_ADDED.equals(evt.getPropertyName())){
				ExplorerNode.this.addChild((IExplorerNode)evt.getNewValue());
			}
			if(ExplorerNodeFactory.PROP_CHILD_REMOVED.equals(evt.getPropertyName())){
				ExplorerNode.this.removeChild((IExplorerNode)evt.getOldValue());
			}
		}
	};
	
	private final PropertyChangeListener childListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			//forward events to the parent nodes
			pcs.firePropertyChange(evt);
		}
	};
	
	public ExplorerNode(String id, String name, ExplorerNodeFactory childFactory) {
		this.id = id;
		this.name = name;
		
		this.context = EclipseContextFactory.create();
		
		this.childFactory = childFactory;
		
		if(this.childFactory != null){
			this.childFactory.addPropertyChangeListener(factoryListener);
			this.childFactory.initialize(this);
		}
	}
	
	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Image getIcon() {
		return icon;
	}

	public IEclipseContext getContext() {
		return context;
	}

	@Override
	public IExplorerNode getParent() {
		return this.parent;
	}

	@Override
	public IExplorerNode[] getChildren() {
		return children.toArray(new IExplorerNode[children.size()]);
	}

	private void addChild(IExplorerNode node) {
		children.add(node);
		((ExplorerNode)node).parent = this;
		
		node.getContext().setParent(this.getContext());
		node.addPropertyChangeListener(childListener);
		
		pcs.firePropertyChange(PROP_CHILD_ADDED, null, node);
	}

	private void removeChild(IExplorerNode node) {
		children.remove(node);
		((ExplorerNode)node).parent = null;
		
		node.getContext().setParent(null);
		node.removePropertyChangeListener(childListener);
		
		pcs.firePropertyChange(PROP_CHILD_REMOVED, node, null);
	}
	
	@Override
	public void dispose() {
		
		disposeChildren();
		
		ExplorerNode en = (ExplorerNode)parent;
		en.removeChild(this);
		
		if(this.childFactory != null){
			this.childFactory.removePropertyChangeListener(factoryListener);
			this.childFactory.dispose();
		}
		
		this.context.dispose();
	}
	
	private void disposeChildren(){
		for(IExplorerNode node : getChildren()){
			((ExplorerNode)node).disposeChildren();
			node.dispose();
		}
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);
	}

	@Override
	public void addPropertyChangeListener(String prop, PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(prop, pcl);
	}

	@Override
	public void removePropertyChangeListener(String prop, PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(prop, pcl);
	}
}
