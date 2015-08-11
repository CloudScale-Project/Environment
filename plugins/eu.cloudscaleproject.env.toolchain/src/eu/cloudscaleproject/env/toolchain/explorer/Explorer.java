package eu.cloudscaleproject.env.toolchain.explorer;

import eu.cloudscaleproject.env.toolchain.explorer.children.ProjectNodeChildren;

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
		rootNode = new ExplorerNode("eu.cloudscaleproject.env.toolchain.explorer.rootNode", new ProjectNodeChildren(false));
		rootNode.setName("Explorer root node");
	}
	
	public IExplorerNode getRoot(){
		return rootNode;
	}
}
