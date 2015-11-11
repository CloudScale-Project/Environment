package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResources;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildren;

public class ConfigurationElementResourceNode extends ExplorerResourceNode{

	public ConfigurationElementResourceNode(IEclipseContext context, IConfigurationElement el, IExplorerNodeChildren children) {
		super(context, el.getAttribute("id"), children);
			
		String name = el.getAttribute("name");
		String defaultAction = el.getAttribute("action");
		String resourcePath = el.getAttribute("resource");

		IResource resource = null;
		if(resourcePath != null && !resourcePath.isEmpty()){
			IProject project = context.get(IProject.class);
			if(project != null){
				resource = project.getFile(Path.fromPortableString(resourcePath));
			}
		}
		
		setResource(resource);

		setName(name);
		setDefaultAction(defaultAction);
		
		Image icon = ExplorerResources.getImage(el, "icon", 16, 16);
		setIcon(icon, false);
	}

	public ConfigurationElementResourceNode(IEclipseContext context, IConfigurationElement el, IResource resource, IExplorerNodeChildren children) {
		super(context, el.getAttribute("id"), children);
			
		String name = el.getAttribute("name");
		String defaultAction = el.getAttribute("action");

		setResource(resource);

		setName(name);
		setDefaultAction(defaultAction);
		
		Image icon = ExplorerResources.getImage(el, "icon", 16, 16);
		setIcon(icon, false);
	}

}
