package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResources;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.AlternativeProviderNode;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolNodeChildren extends ExplorerNodeChildren{

	private final IProject project;
	private final String toolID;
	
	public ToolNodeChildren(IProject project, String toolID, boolean lazy) {
		super(lazy);
		this.project = project;
		this.toolID = toolID;
	}
	
	@Override
	public List<? extends Object> getKeys() {
		return ToolchainExtensions.getInstance().getToolChildElements(toolID);	
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IConfigurationElement element = (IConfigurationElement)key;
		
		IExplorerNode node = null;
		
		if(element.getName().equals("resource")){
			node = createResourceNode(element);
		}
		if(element.getName().equals("view")){
			node = createViewNode(element);
		}
				
		return node;	
	}
	
	private final IExplorerNode createResourceNode(IConfigurationElement element){
		String id = element.getAttribute("id");
		String name = element.getAttribute("name");
		String editor = element.getAttribute("editor");

		Image icon = ExplorerResources.getImage(element, "icon", 16, 16);		
		final ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, id);
		
		AlternativeProviderNode node = new AlternativeProviderNode(getNode().getContext(), id, editor, rp);		
		node.setName(name);
		node.setIcon(icon, false);
				
		return node;
	}
	
	private final IExplorerNode createViewNode(IConfigurationElement element){
		String id = element.getAttribute("id");
		String name = element.getAttribute("name");
		Image icon = ExplorerResources.getImage(element, "icon", 16, 16);
		
		ExplorerEditorNode node = new ExplorerEditorNode(getNode().getContext(), id, id, null, null);		
		node.setName(name);
		node.setIcon(icon, false);
				
		return node;
	}

}
