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
	
	public<T> T findTreeContextData(IExplorerNode node, Class<T> clazz){
		return doFindTreeContextData(node, clazz);
	}
	
	private<T> T doFindTreeContextData(IExplorerNode node, Class<T> clazz){
		
		if(node == null){
			return null;
		}
		
		T data = node.getContext().getLocal(clazz);
		if(data != null){
			return data;
		}
		
		return doFindTreeContextData(node.getParent(), clazz);
	}
	
	public void setSelection(IExplorerNode node){
		if(node != null){
			node.getContext().modify(IExplorerNode.class, node);
		}
	}
	
	public IExplorerNode findNode(Object data){
		return doFindNode(rootNode, data);
	}
	
	private IExplorerNode doFindNode(IExplorerNode node, Object data){
		
		Object nodeData = node.getContext().getLocal(IExplorerConstants.NODE_DATA);
		if(nodeData == data){
			return node;
		}
		
		for(IExplorerNode child : node.getChildren()){
			IExplorerNode foundNode = doFindNode(child, data);
			if(foundNode != null){
				return foundNode;
			}
		}
		
		return null;
	}

}
