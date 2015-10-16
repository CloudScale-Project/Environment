package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeListener;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeNotifier;
import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResources;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.ConfigurationElementNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ProjectNodeChildren extends ExplorerNodeChildren{
	
	@SuppressWarnings("unused")
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
		return ToolchainExtensions.getInstance().getNodeElements();
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IConfigurationElement tool = (IConfigurationElement)key;
		
		String id = tool.getAttribute("id");
		String name = tool.getAttribute("name");
		Image icon = ExplorerResources.getImage(tool, "icon", 16, 16);
		
		ConfigurationElementNodeChildren children = new ConfigurationElementNodeChildren(tool);
		ConfigurationElementNode node = new ConfigurationElementNode(getNode().getContext(), id, children);		
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
