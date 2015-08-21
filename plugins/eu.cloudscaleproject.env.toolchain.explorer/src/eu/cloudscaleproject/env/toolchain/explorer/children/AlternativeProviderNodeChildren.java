package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.AlternativeNode;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeProviderNodeChildren extends ExplorerNodeChildren{
	
	private final String editorID;
	private final ResourceProvider resourceProvider;
	
	private final PropertyChangeListener resourceProviderListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			refresh();
		}
	};
	
	public AlternativeProviderNodeChildren(String editorID, ResourceProvider resourceProvider, boolean lazy) {
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
			children = new AlternativeNodeChildren((EditorInputEMF)resource, true);
		}
		
		AlternativeNode node = new AlternativeNode(getNode().getContext(), editorID, resource, children);				
		return node;
		
	}
	
	@Override
	public void dispose() {
		resourceProvider.removeListener(resourceProviderListener);
		super.dispose();
	}

}
