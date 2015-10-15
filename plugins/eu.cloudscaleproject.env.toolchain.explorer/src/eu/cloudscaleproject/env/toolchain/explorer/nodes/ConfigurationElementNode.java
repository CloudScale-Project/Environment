package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildren;

public class ConfigurationElementNode extends ExplorerNode{

	public ConfigurationElementNode(IEclipseContext context, String id, IExplorerNodeChildren children) {
		super(context, id, children);
	}

}
