package eu.cloudscaleproject.env.method.viewer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

import eu.cloudscaleproject.env.common.notification.IResourceStatus;
import eu.cloudscaleproject.env.common.notification.IStatusService;
import eu.cloudscaleproject.env.common.notification.IToolStatus;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.StatusNode;

public class StatusServiceImpl implements IStatusService{
	
	static HashMap<IProject, ProjectStatusService> psList = new HashMap<IProject, ProjectStatusService>();
	
	public static ProjectStatusService getProjectStatusSrvice(IProject project){
		
		//dispose unexisting project statuses
		Iterator<Entry<IProject, ProjectStatusService>> iter = psList.entrySet().iterator();
		while(iter.hasNext()){
			Entry<IProject, ProjectStatusService> entry = iter.next();
			if(!entry.getKey().exists()){
				entry.getValue().dispose();
				iter.remove();
			}
		}
		
		ProjectStatusService pss = psList.get(project);
		if(pss == null){
			pss = new ProjectStatusService(project);
			psList.put(project, pss);
		}
		return pss;
	}
	
	/**
	 * 
	 * Retrieve chached <code>IToolStatus</code> from this status service, based on the specified Node ID.
	 * This is a workaround to obtain <code>IToolStatus</code> with exactly the same StatusNode instance, based on the node ID.
	 * 
	 * TODO: Fix this "workaround". Find a more appropriate way.
	 * 
	 * @param node: <code>Node</code> to retrieve cached <code>Node</code>
	 * @return Cached instance of the specified <code>Node</code>
	 */
	public static IToolStatus getStatus(StatusNode node){
		
		if(node == null){
			throw new NullPointerException("StatusServiceImpl.getStatus(Node node): node is NULL!");
		}
		if(node.eResource() == null){
			throw new IllegalStateException("StatusServiceImpl.getStatus(Node node): "
					+ "Node is not contained in the resource! Node ID: " + node.getId());
		}
		
		URI uri = node.eResource().getURI();
		String platformString = uri.toPlatformString(true);
		Path path = new Path(platformString);
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getFile(path).getProject();
		ProjectStatusService pss = getProjectStatusSrvice(project);
		
		//order of if's matters here (Requirement implements StatusNode)! 
		if(node instanceof Requirement){
			return pss.getResourceStatus(node.getId());
		}
		
		return pss.getToolStatus(node.getId());	
	}
	
	/**
	 * 
	 * Retrieve chached <code>Node</code> using this status service, based on Node ID.
	 * This is a workaround to obtain exactly the same Node instance from EMF model, based on the node ID.
	 * 
	 * TODO: Fix this "workaround". Find a more appropriate way.
	 * 
	 * @param node: <code>Node</code> to retrieve cached <code>Node</code>
	 * @return Cached instance of the specified <code>Node</code>
	 */
	@SuppressWarnings("unchecked")
	public static <T extends StatusNode> T getStatusNode(T node){
		
		if(node == null){
			throw new NullPointerException("StatusServiceImpl.getNode(T node): node is NULL!");
		}
		if(node.eResource() == null){
			throw new IllegalStateException("StatusServiceImpl.getNode(T node): "
					+ "Node is not contained in the resource! Node ID: " + node.getId());
		}
		
		URI uri = node.eResource().getURI();
		String platformString = uri.toPlatformString(true);
		Path path = new Path(platformString);
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getFile(path).getProject();
		ProjectStatusService pss = getProjectStatusSrvice(project);
		
		//order of if's matters here (Requirement implements StatusNode)! 
		if(node instanceof Requirement){
			ResourceStatusImpl rs = pss.getResourceStatus(node.getId());
			if(rs == null){return null;}
			return (T)rs.getStatusNode();
		}
		
		ToolStatusImpl ts = pss.getToolStatus(node.getId());
		if(ts == null){return null;}
		return (T)ts.getStatusNode();
		
	}
	
	@Override
	public IToolStatus getToolStatus(IProject project, String tool) {
		ProjectStatusService pss = getProjectStatusSrvice(project);
		return pss.getToolStatus(tool);
	}

	@Override
	public IResourceStatus getResourceStatus(IProject project, String resID) {
		ProjectStatusService pss = getProjectStatusSrvice(project);
		return pss.getResourceStatus(resID);
	}
}
