package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeListener;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeNotifier;
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
	
	private static final Logger logger = Logger.getLogger(ProjectNodeChildren.class.getName());
	
	private final IProject project;
	
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
			return new IResource[]{project};
		}
	};
	
	public ProjectNodeChildren(IProject project, boolean lazy) {
		super(lazy);
		this.project = project;
		ExplorerChangeNotifier.getInstance().addListener(ecl);
	}

	@Override
	public List<? extends Object> getKeys() {
		
		// Check if the project property file exists. 
		// If not skip child creation and wait for the resource change listener event to trigger refresh,
		// until properties file is created.
		IFile projectProp = ExplorerProjectPaths.getPropertyFile(project);
		if(projectProp == null || !projectProp.exists()){
			return null;
		}
		
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
		if(folder == null || !folder.exists()){
			logger.severe("Tool folder can not be resolved! Project: " + project.getName()+ ", Tool ID: " + id);
			return null;
		}
		
		ToolNode node = new ToolNode(getNode().getContext(), id, folder);		
		node.setIcon(icon, false);
		node.setName(name);
		
		return node;
	}
	
	@Override
	public void dispose() {
		ExplorerChangeNotifier.getInstance().removeListener(ecl);
		super.dispose();
	}
	
}
