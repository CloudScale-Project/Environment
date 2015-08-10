package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.explorer.factory.ToolResourceNodeFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeProviderNode extends ExplorerResourceNode{
	
	private final ResourceProvider resourceProvider;

	public AlternativeProviderNode(CSTool tool, ResourceProvider resourceProvider) {
		super(tool.getID(), tool.getName(), new ToolResourceNodeFactory(tool, resourceProvider));
		
		this.resourceProvider = resourceProvider;
		getContext().set(ResourceProvider.class, resourceProvider);
	}
	
	public ResourceProvider getAlternativeProvider(){
		return this.resourceProvider;
	}

}
