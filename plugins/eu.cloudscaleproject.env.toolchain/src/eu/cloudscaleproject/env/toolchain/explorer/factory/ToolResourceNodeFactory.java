package eu.cloudscaleproject.env.toolchain.explorer.factory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.AlternativeNode;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolResourceNodeFactory extends ExplorerNodeFactory{
	
	private final CSTool tool;
	private final ResourceProvider resourceProvider;
	
	private final PropertyChangeListener resourceProviderListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			refresh();
		}
	};
	
	public ToolResourceNodeFactory(CSTool tool, ResourceProvider resourceProvider) {
		this.tool = tool;
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
		
		AlternativeNode node = new AlternativeNode(tool, resource);
		return node;
		
	}
	
	@Override
	public void dispose() {
		resourceProvider.removeListener(resourceProviderListener);
		super.dispose();
	}

}
