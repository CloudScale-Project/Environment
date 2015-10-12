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
import eu.cloudscaleproject.env.toolchain.explorer.nodes.ProjectNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class RootNodeChildren extends ExplorerNodeChildren{
	
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
				if(projectDelta.getKind() == IResourceDelta.CHANGED){
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
	
	private static class ProjectKey{
		
		private final IProject project;
		private final IProjectNature nature;
		
		public ProjectKey(IProject project, IProjectNature nature) {
			this.project = project;
			this.nature = nature;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((nature == null) ? 0 : nature.hashCode());
			result = prime * result + ((project == null) ? 0 : project.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ProjectKey other = (ProjectKey) obj;
			if (nature == null) {
				if (other.nature != null)
					return false;
			} else if (!nature.equals(other.nature))
				return false;
			if (project == null) {
				if (other.project != null)
					return false;
			} else if (!project.equals(other.project))
				return false;
			return true;
		}
		
	}
	
	public RootNodeChildren(boolean lazy) {
		super(lazy);
		ExplorerChangeNotifier.getInstance().addListener(ecl);
	}

	@Override
	public List<? extends Object> getKeys() {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		
		List<ProjectKey> csProjects = new ArrayList<ProjectKey>();
		List<ProjectKey> importedProjects = new ArrayList<ProjectKey>();
		
		for(IProject project : projects){
			try {
				
				if(project.isAccessible()){
					if(project.isNatureEnabled(CloudScaleConstants.PROJECT_NATURE_ID)){
						IProjectNature pn = project.getNature(CloudScaleConstants.PROJECT_NATURE_ID);
						csProjects.add(new ProjectKey(project, pn));
					}
					else{
						importedProjects.add(new ProjectKey(project, null));
					}
				}
				
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		csProjects.addAll(importedProjects);
		return csProjects;
	}
	
	@Override
	public IExplorerNode getChild(Object key) {
			
		IProject project = ((ProjectKey)key).project;
		
		boolean isCloudscale = false;
		try{
			IProjectNature pn = project.getNature(CloudScaleConstants.PROJECT_NATURE_ID);
			if(pn != null){
				isCloudscale = true;
			}
		}
		catch (CoreException e) {
			//ignore
		}
		
		IExplorerNode node;
		
		if(isCloudscale){
			node = new ProjectNode(getNode().getContext(), project);
			node.setName(project.getName());
			node.setIcon(CommonResources.PROJECT_16, false);
		}
		else{
			node = new ExplorerResourceNode(getNode().getContext(), project.getName(), project, null);
			node.setName(project.getName());
			node.setIcon(CommonResources.OPERATION, false);
		}
		
		return node;
	}
	
	@Override
	public void dispose() {
		ExplorerChangeNotifier.getInstance().removeListener(ecl);
		super.dispose();
	}
	
}
