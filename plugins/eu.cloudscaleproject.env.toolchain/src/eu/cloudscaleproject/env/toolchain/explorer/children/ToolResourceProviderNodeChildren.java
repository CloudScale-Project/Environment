package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.toolchain.Extensions;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerUtils;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolResourceProviderNodeChildren extends ExplorerNodeChildren{

	private final IProject project;
	private final String toolID;
	
	public ToolResourceProviderNodeChildren(IProject project, String toolID, boolean lazy) {
		super(lazy);
		this.project = project;
		this.toolID = toolID;
	}
	
	@Override
	public List<? extends Object> getKeys() {
		return Extensions.getInstance().getResourceProviderFactoryElements(toolID);	
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IConfigurationElement element = (IConfigurationElement)key;
		
		String id = element.getAttribute("id");
		String name = element.getAttribute("name");
		Image icon = ExplorerUtils.createImage(element, "icon");
		
		IFolder folder = ToolchainUtils.getToolFolder(project, id);
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(id, folder);
		
		ToolResourceNodeChildren children = new ToolResourceNodeChildren(rp, true);
		ExplorerResourceNode node = new ExplorerResourceNode(id, rp.getRootFolder(), children);
		node.setName(name);
		node.setIcon(icon, true);
		node.setData(rp);
		
		return node;	
	}

}
