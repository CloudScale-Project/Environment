package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import eu.cloudscaleproject.env.common.CloudScaleConstants;
import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeListener;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeNotifier;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ProjectNodeChildren extends ExplorerNodeChildren{
	
	private final ExplorerChangeListener ecl = new ExplorerChangeListener() {
		
		@Override
		public void resourceChanged(IResourceDelta delta) {
			
			boolean refresh = false;
			
			for (IResourceDelta projectDelta : delta.getAffectedChildren())
			{
				if(projectDelta.getKind() == IResourceDelta.ADDED){
					refresh = true;
				}
				if(projectDelta.getKind() == IResourceDelta.REMOVED){
					refresh = true;
				}
			}
			
			if(refresh){
				refresh();
			}
		}
		
		@Override
		public IResource[] getResources() {
			return new IResource[]{ResourcesPlugin.getWorkspace().getRoot()};
		}
	};
	
	public ProjectNodeChildren(boolean lazy) {
		super(lazy);
		ExplorerChangeNotifier.getInstance().addListener(ecl);
	}

	@Override
	public List<? extends Object> getKeys() {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		
		List<IProject> cseProjects = new ArrayList<IProject>();
		for(IProject project : projects){
			try {
				IProjectNature pn = project.getNature(CloudScaleConstants.PROJECT_NATURE_ID);
				if(pn != null){
					cseProjects.add(project);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return cseProjects;
	}
	
	@Override
	public IExplorerNode getChild(Object key) {
		if(key instanceof IProject){
			IProject project = (IProject)key;
			ExplorerResourceNode node = new ExplorerResourceNode(project.getName(), 
																 project,
																 new ToolNodeChildren(project, false));
			node.setName(project.getName());
			node.setIcon(CommonResources.PROJECT_16, false);
			return node;
		}
		return null;
	}
	
	@Override
	public void dispose() {
		ExplorerChangeNotifier.getInstance().removeListener(ecl);
		super.dispose();
	}
	
}
