package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.ProjectNode;
import eu.cloudscaleproject.env.toolchain.resources.ProjectResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class RootNodeChildren extends ExplorerNodeChildren{
	
	private final List<ProjectResourceRegistry> projectResourceRegistries = new ArrayList<ProjectResourceRegistry>();
	
	private final PropertyChangeListener resourceRegistryListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if(ResourceRegistry.PROJECT_RESOURCE_REGISTRY_ADDED.equals(evt.getPropertyName())){
				projectResourceRegistries.add((ProjectResourceRegistry)evt.getNewValue());
			}
			if(ResourceRegistry.PROJECT_RESOURCE_REGISTRY_REMOVED.equals(evt.getPropertyName())){
				projectResourceRegistries.remove((ProjectResourceRegistry)evt.getOldValue());
			}
			
			refreshNow();
		}
	};
	
	public RootNodeChildren(boolean lazy) {
		super(lazy);
		projectResourceRegistries.addAll(ResourceRegistry.getInstance().getProjectResourceRegistries());
		ResourceRegistry.getInstance().addPropertyChangeListener(resourceRegistryListener);
	}

	@Override
	public List<? extends Object> getKeys() {
		return projectResourceRegistries;
	}
	
	@Override
	public IExplorerNode getChild(Object key) {
		
		ProjectResourceRegistry resourceRegistry = (ProjectResourceRegistry)key;
		IProject project = resourceRegistry.getProject();
		
		IExplorerNode node;
		
		if(ExplorerProjectPaths.isCloudScaleProject(project)){
			node = new ProjectNode(getNode().getContext(), resourceRegistry);
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
		ResourceRegistry.getInstance().removePropertyChangeListener(resourceRegistryListener);
		super.dispose();
	}
	
}
