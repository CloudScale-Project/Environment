package eu.cloudscaleproject.env.toolchain.explorer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.CloudscaleContext;

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
	
	public ExplorerNode(IEclipseContext context, String id, IExplorerNodeChildren children) {
		
		this.id = id;
		this.context = EclipseContextFactory.create(this.getClass().getSimpleName() + "("+ id + ")");
		
		if(context != null){
			this.context.setParent(context);
			CloudscaleContext.inject(this, this.context);
		}
		
		
		if(children != null){
			addNodeChildren(children);
		}
		
		//retrieve children from extension points
		for(IExplorerNodeChildrenProvider childrenProvider : ExplorerExtensions.getInstance().getNodeChildrenProviders()){
			if(childrenProvider.canCreate(this)){
				addNodeChildren(childrenProvider.create(this));
			}
		}
	}
	
	public void addNodeChildren(final IExplorerNodeChildren children){
		
		if(children == null){
			return;
		}
		
		this.nodeChildren.add(children);
		children.addPropertyChangeListener(factoryListener);
		children.initialize(this);
	}
	
	public void removeNodeChildren(final IExplorerNodeChildren children){
		
		if(children == null){
			return;
		}
		
		children.removePropertyChangeListener(factoryListener);
		this.nodeChildren.remove(children);
		children.dispose();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class adapter) {
		
		Object o = this.context.get(IExplorerConstants.NODE_DATA);
		Object contextObject = getContext().get(adapter);

		if(adapter.isAssignableFrom(this.getClass())){
			return this;
		}
		if(o != null && adapter.isAssignableFrom(o.getClass())){
			return o;
		}
		if(contextObject != null){
			return contextObject;
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
	public boolean hasChildren() {
		for(IExplorerNodeChildren children : nodeChildren){
			if(children.hasChildren()){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public IExplorerNode[] getChildren() {
		List<IExplorerNode> out = new ArrayList<IExplorerNode>();
		
		for(IExplorerNodeChildren children : nodeChildren){
			out.addAll(children.getChildren());
		}
		
		return out.toArray(new IExplorerNode[out.size()]);
	}

	private void addChild(final IExplorerNode node) {
		
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				doAddChild(node);
			}
		});
		pcs.firePropertyChange(PROP_CHILD_ADDED, null, node);
	}
	
	private void doAddChild(IExplorerNode node) {
		if(node == null){
			return;
		}
		
		((ExplorerNode)node).parent = this;		
		ContextInjectionFactory.inject(node, this.getContext());
		
		node.addPropertyChangeListener(childListener);		
	}

	private void removeChild(final IExplorerNode node) {
		
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				doRemoveChild(node);
			}
		});
		pcs.firePropertyChange(PROP_CHILD_REMOVED, node, null);
	}
	
	private void doRemoveChild(IExplorerNode node) {
		if(node == null){
			return;
		}
		node.removePropertyChangeListener(childListener);
		node.getContext().setParent(null);
		((ExplorerNode)node).parent = null;
	}
	
	@Override
	public void onSelect() {
		
		//deactivate child
		IEclipseContext activeChild = context.getActiveChild();
		if(activeChild != null){
			activeChild.deactivate();
		}
		
		context.activateBranch();
		
	}
	
	@Override
	public void refresh() {
		for(IExplorerNodeChildren children : nodeChildren){
			children.refresh();
		}
		
		pcs.firePropertyChange(PROP_REFRESH, false, true);
	}
	
	@Override
	public void refreshRecursive() {
		doRefreshRecursive();
		pcs.firePropertyChange(PROP_REFRESH_RECURSIVE, false, true);
	}
	
	protected void doRefreshRecursive() {
		for(IExplorerNodeChildren children : nodeChildren){
			children.refresh();
		}
		
		for(IExplorerNode node : getChildren()){
			((ExplorerNode)node).doRefreshRecursive();
		}		
	}
	
	@Override
	public void dispose(){
		
		for(IExplorerNodeChildren children : this.nodeChildren){
			children.removePropertyChangeListener(factoryListener);
			children.dispose();
		}
		this.nodeChildren.clear();
		
		if(icon != null && iconDisposeable){
			icon.dispose();
		}
		
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				ExplorerNode.this.context.dispose();
			}
		});
		
		if(parent != null){
			ExplorerNode en = (ExplorerNode)parent;
			en.removeChild(this);
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
