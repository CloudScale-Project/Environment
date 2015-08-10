package eu.cloudscaleproject.env.toolchain.explorer;

import eu.cloudscaleproject.env.toolchain.explorer.factory.ProjectNodeFactory;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.ExplorerNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class Explorer {
	
	public static Explorer instance;
	public static Explorer getInstance(){
		
		if(instance == null){
			instance = new Explorer();
		}
		return instance;
	}
	
	private final IExplorerNode rootNode;
	
	public Explorer() {
		rootNode = new ExplorerNode("eu.cloudscaleproject.env.toolchain.explorer.rootNode", "Root node", new ProjectNodeFactory());		
	}
	
	public IExplorerNode getRoot(){
		return rootNode;
	}
}
