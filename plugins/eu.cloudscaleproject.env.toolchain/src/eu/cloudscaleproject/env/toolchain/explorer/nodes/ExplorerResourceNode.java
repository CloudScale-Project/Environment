package eu.cloudscaleproject.env.toolchain.explorer.nodes;

import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerResourceNode extends ExplorerNode{
	
	public static String PROP_RESOURCE = "eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode.resource";

	private IResource resource = null;
	
	public ExplorerResourceNode(String id, String name, ExplorerNodeFactory childFactory) {
		super(id, name, childFactory);
	}
	
	public void setResource(IResource resource){
		firePropertyChange(PROP_RESOURCE, this.resource, this.resource = resource);
	}
	
	public IResource getResource(){
		return this.resource;
	}

}
