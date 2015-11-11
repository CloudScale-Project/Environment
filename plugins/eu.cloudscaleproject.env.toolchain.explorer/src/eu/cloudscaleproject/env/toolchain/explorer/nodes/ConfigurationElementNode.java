package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResources;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildren;

public class ConfigurationElementNode extends ExplorerNode{

	public ConfigurationElementNode(IEclipseContext context, IConfigurationElement el, IExplorerNodeChildren children) {
		super(context, el.getAttribute("id"), children);
			
		String name = el.getAttribute("name");
		String defaultAction = el.getAttribute("action");

		setName(name);
		setDefaultAction(defaultAction);
		
		Image icon = ExplorerResources.getImage(el, "icon", 16, 16);
		setIcon(icon, false);
	}

}
