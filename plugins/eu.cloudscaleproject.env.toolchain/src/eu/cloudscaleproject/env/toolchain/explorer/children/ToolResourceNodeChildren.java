package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerConstants;
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
	
	private final String editorID;
	private final ResourceProvider resourceProvider;
	
	private final PropertyChangeListener resourceProviderListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			refresh();
		}
	};
	
	public ToolResourceNodeChildren(String editorID, ResourceProvider resourceProvider, boolean lazy) {
		super(lazy);
		
		this.editorID = editorID;
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
			children = new EMFModelChildren((EditorInputEMF)resource, true);
		}
		
		ExplorerEditorNode node = new ExplorerEditorNode(resource.getID(), resource.getResource(), children);
		node.setName(resource.getName());
		node.setData(resource);
		
		node.getContext().set(IExplorerConstants.NODE_EDITOR_ID, editorID);
		
		return node;
		
	}
	
	@Override
	public void dispose() {
		resourceProvider.removeListener(resourceProviderListener);
		super.dispose();
	}

}
