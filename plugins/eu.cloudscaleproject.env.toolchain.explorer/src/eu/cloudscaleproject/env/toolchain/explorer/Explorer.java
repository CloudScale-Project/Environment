package eu.cloudscaleproject.env.toolchain.explorer;

import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.toolchain.explorer.children.RootNodeChildren;

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
		
		IEclipseContext context = CloudscaleContext.getGlobalContext();
		
		rootNode = new ExplorerNode(context, 
									"eu.cloudscaleproject.env.toolchain.explorer.rootNode", 
									new RootNodeChildren(false));
		
		rootNode.setName("Explorer root node");
	}
	
	public IExplorerNode getRoot(){
		return rootNode;
	}
	
	public void refreshAll(){
		rootNode.refreshRecursive();
	}

}
