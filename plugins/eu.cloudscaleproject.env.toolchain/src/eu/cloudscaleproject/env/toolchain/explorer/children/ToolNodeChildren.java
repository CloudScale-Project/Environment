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

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolNodeChildren extends ExplorerNodeChildren{
		
	private final IProject project;

	public ToolNodeChildren(IProject project, boolean lazy) {
		super(lazy);
		this.project = project;
	}

	@Override
	public List<? extends Object> getKeys() {		
		List<IConfigurationElement> toolElements = Extensions.getInstance().getToolElements();
		return toolElements;
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IConfigurationElement tool = (IConfigurationElement)key;
		
		String id = tool.getAttribute("id");
		String name = tool.getAttribute("name");
		Image icon = ExplorerUtils.createImage(tool, "icon");
		
		IFolder folder = ToolchainUtils.getToolFolder(project, id);
		
		IExplorerNode node = new ExplorerResourceNode(id, folder, new ToolResourceProviderNodeChildren(project, id, false));
		node.setIcon(icon, true);
		node.setName(name);
		
		return node;
	}

}
