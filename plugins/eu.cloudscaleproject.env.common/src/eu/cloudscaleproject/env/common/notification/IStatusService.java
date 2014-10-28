package eu.cloudscaleproject.env.common.notification;

import org.eclipse.core.resources.IProject;

public interface IStatusService {

	public IToolStatus getToolStatus(IProject project, String toolID);
}
