package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.toolchain.Extensions;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerNode extends PlatformObject implements IExplorerNode{

	private final String id;
	
	private String name = "Unknown";
	
	private boolean iconDisposeable = false;
	private Image icon = null;
	
	protected final IEclipseContext context;
	private final List<IExplorerNodeChildren> nodeChildren = new ArrayList<IExplorerNodeChildren>();
	
	protected IExplorerNode parent;
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private final PropertyChangeListener factoryListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(ExplorerNodeChildren.PROP_CHILD_ADDED.equals(evt.getPropertyName())){
				ExplorerNode.this.addChild((IExplorerNode)evt.getNewValue());
			}
			if(ExplorerNodeChildren.PROP_CHILD_REMOVED.equals(evt.getPropertyName())){
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
	
	public ExplorerNode(String id, IExplorerNodeChildren children) {
		this.id = id;		
		this.context = EclipseContextFactory.create(name);
		
		if(children != null){
			addNodeChildren(children);
		}
		
		//retrieve children from extension points
		for(IExplorerNodeChildrenProvider childrenProvider : Extensions.getInstance().getNodeChildrenProviders()){
			if(childrenProvider.canCreate(this)){
				addNodeChildren(childrenProvider.create(this));
			}
		}
	}
	
	private void addNodeChildren(final IExplorerNodeChildren children){
		
		if(children == null){
			return;
		}
		
		this.nodeChildren.add(children);
		children.addPropertyChangeListener(factoryListener);
		children.initialize();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class adapter) {
		
		Object o = this.context.get(IExplorerConstants.NODE_DATA);

		if(this.getClass().equals(adapter)){
			return this;
		}
		if(adapter.isAssignableFrom(o.getClass())){
			return o;
		}
		
		return super.getAdapter(adapter);
	}
	
	public String getID() {
		return id;
	}

	public void setName(String name){
		pcs.firePropertyChange(PROP_NAME, this.name, this.name = name);
	}
	
	public String getName() {
		return name;
	}
	
	public void setData(Object data){
		this.context.set(IExplorerConstants.NODE_DATA, data);
	}
	
	public void setIcon(Image icon, boolean disposeable){
		
		if(icon != null && iconDisposeable){
			icon.dispose();
		}
		
		this.iconDisposeable = disposeable;
		pcs.firePropertyChange(PROP_ICON, this.icon, this.icon = icon);
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
		List<IExplorerNode> out = new ArrayList<IExplorerNode>();
		
		for(IExplorerNodeChildren children : nodeChildren){
			out.addAll(children.getChildren());
		}
		
		return out.toArray(new IExplorerNode[out.size()]);
	}

	private void addChild(IExplorerNode node) {
		
		if(node == null){
			return;
		}
		
		((ExplorerNode)node).parent = this;
		
		node.getContext().setParent(this.getContext());
		node.addPropertyChangeListener(childListener);
		
		pcs.firePropertyChange(PROP_CHILD_ADDED, null, node);
	}

	private void removeChild(IExplorerNode node) {
		
		if(node == null){
			return;
		}
		
		((ExplorerNode)node).parent = null;
		
		node.getContext().setParent(null);
		node.removePropertyChangeListener(childListener);
		
		pcs.firePropertyChange(PROP_CHILD_REMOVED, node, null);
	}
	
	@Override
	public void onSelect() {
		context.activateBranch();
	}
	
	@Override
	public void dispose() {
		
		disposeChildren();
		
		ExplorerNode en = (ExplorerNode)parent;
		en.removeChild(this);
		
		for(IExplorerNodeChildren children : this.nodeChildren){
			children.removePropertyChangeListener(factoryListener);
			children.dispose();
		}
		
		if(icon != null && iconDisposeable){
			icon.dispose();
		}
		
		this.nodeChildren.clear();
		this.context.dispose();
	}
	
	private void disposeChildren(){
		for(IExplorerNode node : getChildren()){
			((ExplorerNode)node).disposeChildren();
			node.dispose();
		}
	}
	
	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue){
		this.pcs.firePropertyChange(propertyName, oldValue, newValue);
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
