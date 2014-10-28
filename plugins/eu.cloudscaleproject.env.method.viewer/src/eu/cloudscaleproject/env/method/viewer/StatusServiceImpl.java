package eu.cloudscaleproject.env.method.viewer;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IStatusService;
import eu.cloudscaleproject.env.common.notification.IToolStatus;

public class StatusServiceImpl implements IStatusService{
	
	static HashMap<IProject, ProjectStatusService> psList = new HashMap<IProject, ProjectStatusService>();
	
	public static ProjectStatusService getProjectStatusSrvice(IProject project){
		
		//dispose unexisting project statuses
		Iterator<IProject> iter = psList.keySet().iterator();
		while(iter.hasNext()){
			IProject p = iter.next();
			if(!p.exists()){
				iter.remove();
			}
		}
		
		ProjectStatusService pss = psList.get(project);
		if(pss == null){
			pss = new ProjectStatusService();
			psList.put(project, pss);
		}
		return pss;
	}
	
	@Override
	public IToolStatus getToolStatus(IProject project, String tool) {
		ProjectStatusService pss = getProjectStatusSrvice(project);
		return pss.getToolStatus(project, tool);
	}
}
