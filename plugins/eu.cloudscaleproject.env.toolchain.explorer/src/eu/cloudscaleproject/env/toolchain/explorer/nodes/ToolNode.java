package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.resources.IFolder;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.children.ToolNodeChildren;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolNode extends ExplorerResourceNode{

	public ToolNode(String toolID, IFolder folder) {
		super(toolID, folder, new ToolNodeChildren(folder.getProject(), toolID, false));
	}

}
