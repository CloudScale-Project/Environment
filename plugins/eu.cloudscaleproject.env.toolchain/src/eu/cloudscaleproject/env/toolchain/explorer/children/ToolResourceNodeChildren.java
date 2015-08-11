package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolResourceNodeChildren extends ExplorerNodeChildren{
	
	private final ResourceProvider resourceProvider;
	
	private final PropertyChangeListener resourceProviderListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			refresh();
		}
	};
	
	public ToolResourceNodeChildren(ResourceProvider resourceProvider, boolean lazy) {
		super(lazy);
		this.resourceProvider = resourceProvider;
		this.resourceProvider.addListener(resourceProviderListener);
	}

	@Override
	public List<? extends Object> getKeys() {
		return resourceProvider.getResources();
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IEditorInputResource resource = (IEditorInputResource)key;
		
		IExplorerNodeChildren children = null;
		
		if(resource instanceof EditorInputEMF){
			children = new AlternativeChildren((EditorInputEMF)resource, true);
		}
		
		ExplorerResourceNode node = new ExplorerResourceNode(resource.getID(), resource.getResource(), children);
		node.setName(resource.getName());
		node.setData(resource);
		
		return node;
		
	}
	
	@Override
	public void dispose() {
		resourceProvider.removeListener(resourceProviderListener);
		super.dispose();
	}

}
