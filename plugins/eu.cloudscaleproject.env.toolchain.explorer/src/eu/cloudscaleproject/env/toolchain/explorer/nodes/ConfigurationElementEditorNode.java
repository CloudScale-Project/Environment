package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResources;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildren;

public class ConfigurationElementEditorNode extends ExplorerEditorNode{

	public ConfigurationElementEditorNode(IEclipseContext context, IConfigurationElement el, IExplorerNodeChildren children) {
		super(context, el.getAttribute("id"), el.getAttribute("editor"), children);

		String name = el.getAttribute("name");
		String resourcePath = el.getAttribute("resource");
		String defaultAction = el.getAttribute("action");

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

}
