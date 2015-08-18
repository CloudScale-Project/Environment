package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerUtils;
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
		return ToolchainExtensions.getInstance().getResourceProviderFactoryElements(toolID);	
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IConfigurationElement element = (IConfigurationElement)key;
		
		String id = element.getAttribute("id");
		String name = element.getAttribute("name");
		String editor = element.getAttribute("editor");

		Image icon = ExplorerUtils.createImage(element, "icon");
		
		IFolder folder = ToolchainUtils.getToolFolder(project, id);
		final ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(id, folder);
		
		AlternativeProviderNode node = new AlternativeProviderNode(id, editor, rp);		
		node.setName(name);
		node.setIcon(icon, true);
				
		return node;	
	}

}
