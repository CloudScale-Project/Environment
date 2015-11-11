package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.ConfigurationElementNode;
import eu.cloudscaleproject.env.toolchain.resources.ProjectResourceRegistry;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ProjectNodeChildren extends ExplorerNodeChildren{
	
	@SuppressWarnings("unused")
	private final ProjectResourceRegistry resourceRegistry;
	
	public ProjectNodeChildren(ProjectResourceRegistry resourceRegistry, boolean lazy) {
		super(lazy);
		this.resourceRegistry = resourceRegistry;
	}

	@Override
	public List<? extends Object> getKeys() {
		return ToolchainExtensions.getInstance().getExplorerElements();
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IConfigurationElement tool = (IConfigurationElement)key;
		
		ConfigurationElementNodeChildren children = new ConfigurationElementNodeChildren(tool);
		ConfigurationElementNode node = new ConfigurationElementNode(getNode().getContext(), tool, children);		
		
		return node;
	}
	
}
