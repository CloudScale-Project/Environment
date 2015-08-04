package eu.cloudscaleproject.env.toolchain.explorer.factory;

import java.util.List;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolResourceNodeFactory extends ExplorerNodeFactory{
	
	private final ResourceProvider resourceProvider;
	
	public ToolResourceNodeFactory(IProject project, ResourceProvider resourceProvider) {
		this.resourceProvider = resourceProvider;
	}

	@Override
	public List<? extends Object> getKeys() {
		return resourceProvider.getResources();
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IEditorInputResource resource = (IEditorInputResource)key;
		
		ExplorerNode node = new ExplorerNode(resource.getID(), resource.getName(), null);
		return node;
		
	}

}
