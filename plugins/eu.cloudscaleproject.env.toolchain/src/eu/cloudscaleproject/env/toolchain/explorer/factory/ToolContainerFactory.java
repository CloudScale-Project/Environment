package eu.cloudscaleproject.env.toolchain.explorer.factory;

import java.util.List;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.ITool;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolContainerFactory extends ExplorerNodeFactory{

	private final IProject project;
	private final ITool tool;
	
	public ToolContainerFactory(IProject project, ITool tool) {
		this.project = project;
		this.tool = tool;
	}
	
	@Override
	public List<? extends Object> getKeys() {
		return tool.getResourceProviderFactories().keySet();		
	}

	@Override
	public IExplorerNode getChild(Object key) {
		IResourceProviderFactory rpf = (IResourceProviderFactory)key;
		rpf.
	}

}
