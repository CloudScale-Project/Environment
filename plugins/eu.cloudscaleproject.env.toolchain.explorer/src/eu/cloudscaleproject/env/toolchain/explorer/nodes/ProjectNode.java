package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.children.ProjectNodeChildren;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ProjectNode extends ExplorerResourceNode{

	public ProjectNode(IEclipseContext context, IProject project) {
		super(context, project.getName(), project, new ProjectNodeChildren(project, false));
		
		getContext().set(IProject.class, project);
	}

}
