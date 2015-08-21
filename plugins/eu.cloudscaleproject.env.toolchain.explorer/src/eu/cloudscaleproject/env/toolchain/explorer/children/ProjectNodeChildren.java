package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResources;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.ToolNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ProjectNodeChildren extends ExplorerNodeChildren{
		
	private final IProject project;

	public ProjectNodeChildren(IProject project, boolean lazy) {
		super(lazy);
		this.project = project;
	}

	@Override
	public List<? extends Object> getKeys() {		
		List<IConfigurationElement> toolElements = ToolchainExtensions.getInstance().getToolElements();
		return toolElements;
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IConfigurationElement tool = (IConfigurationElement)key;
		
		String id = tool.getAttribute("id");
		String name = tool.getAttribute("name");
		Image icon = ExplorerResources.getImage(tool, "icon", 16, 16);
		
		IFolder folder = ToolchainUtils.getToolFolder(project, id);
		
		ToolNode node = new ToolNode(id, folder);		
		node.setIcon(icon, false);
		node.setName(name);
		
		return node;
	}

}
