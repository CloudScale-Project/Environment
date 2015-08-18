package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.children.ProjectNodeChildren;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ProjectNode extends ExplorerResourceNode{

	public ProjectNode(IProject project) {
		super(project.getName(), project, new ProjectNodeChildren(project, false));
	}

}
