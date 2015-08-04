package eu.cloudscaleproject.env.toolchain.explorer.factory;

import java.util.List;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.ExtensionRetriever;
import eu.cloudscaleproject.env.toolchain.ITool;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolNodeFactory extends ExplorerNodeFactory{
	
	private final IProject project;
	private ExtensionRetriever extensionRetriever = new ExtensionRetriever();

	public ToolNodeFactory(IProject project) {
		this.project = project;
	}

	@Override
	public List<? extends Object> getKeys() {
		return extensionRetriever.retrieveExtensionObjects("eu.cloudscaleproject.env.toolchain.tool", "class", ITool.class);
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		ITool tool = (ITool)key;		
		return new ExplorerNode(tool.getToolID(), 
								tool.getToolName(), 
								new ToolContainerFactory(project, tool));
	}

}
