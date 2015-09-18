package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.resources.IFolder;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.children.ToolNodeChildren;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolNode extends ExplorerResourceNode{

	public ToolNode(IEclipseContext context, String toolID, IFolder folder) {
		super(context, toolID, folder, new ToolNodeChildren(folder.getProject(), toolID, false));
	}

	@Override
	public boolean canDelete() {
		return false;
	}
}
