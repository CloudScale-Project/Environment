package eu.cloudscaleproject.env.toolchain.resources.validation;

import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IResourceStatus;
import eu.cloudscaleproject.env.common.notification.IStatusService;
import eu.cloudscaleproject.env.common.notification.IToolStatus;

public class ResourceStatusService implements IStatusService{
	
	private static ConcurrentHashMap<String, ResourceStatus> resourceStatusSet = new ConcurrentHashMap<String, ResourceStatus>();

	public static void addResourceStatus(ResourceStatus status){
		resourceStatusSet.put(status.getResource().getFullPath().toString(), status);
	}
	
	public static void removeResourceStatus(ResourceStatus status){
		resourceStatusSet.remove(status.getResource().getFullPath().toString());
	}
	
	@Override
	public IToolStatus getToolStatus(IProject project, String toolID) {
		return null;
	}

	@Override
	public IResourceStatus getResourceStatus(IProject project, String resourceID) {
		return resourceStatusSet.get(resourceID);
	}

}
