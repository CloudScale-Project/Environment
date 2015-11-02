package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerConstants;
import eu.cloudscaleproject.env.toolchain.explorer.children.ProjectNodeChildren;
import eu.cloudscaleproject.env.toolchain.resources.ProjectResourceRegistry;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ProjectNode extends ExplorerResourceNode{

	public ProjectNode(IEclipseContext context, ProjectResourceRegistry projectResourceRegistry) {
		super(context, projectResourceRegistry.getProject().getName(), 
					   projectResourceRegistry.getProject(), 
					   new ProjectNodeChildren(projectResourceRegistry, false));
		
		getContext().set(IExplorerConstants.NODE_DATA, projectResourceRegistry.getProject());
		getContext().set(IProject.class, projectResourceRegistry.getProject());
	}

}
