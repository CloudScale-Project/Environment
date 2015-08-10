package eu.cloudscaleproject.env.toolchain.explorer.factory;

import java.util.List;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.AlternativeProviderNode;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolResourceProviderNodeFactory extends ExplorerNodeFactory{

	private final IProject project;
	private final List<CSTool> tools;
	
	public ToolResourceProviderNodeFactory(IProject project, List<CSTool> tools) {
		this.project = project;
		this.tools = tools;
	}
	
	@Override
	public List<? extends Object> getKeys() {
		return tools;	
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		CSTool tool = (CSTool)key;
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, tool);
		AlternativeProviderNode node = new AlternativeProviderNode(tool, rp);
		return node;
		
	}

}
