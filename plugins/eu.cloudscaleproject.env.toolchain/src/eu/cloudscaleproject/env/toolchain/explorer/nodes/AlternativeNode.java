package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeNode extends ExplorerResourceNode{
	
	private final CSTool tool;
	
	public AlternativeNode(CSTool tool, IEditorInputResource alternative) {
		super(alternative.getResource().getName(), alternative.getName(), null);
		
		this.tool = tool;
		setResource(alternative.getResource());
		
		getContext().set(IEditorInputResource.class, alternative);
	}
	
	public IEditorInputResource getAlternative(){
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(getResource().getProject(), tool);
		return rp.getResource(getResource());
	}

}
