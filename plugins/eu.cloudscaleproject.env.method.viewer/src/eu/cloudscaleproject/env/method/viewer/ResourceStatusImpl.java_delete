package eu.cloudscaleproject.env.method.viewer;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IResourceStatus;
import eu.cloudscaleproject.env.method.common.method.Requirement;
import eu.cloudscaleproject.env.method.common.method.StatusNode;

public class ResourceStatusImpl extends ToolStatusImpl implements IResourceStatus{

	public ResourceStatusImpl(IProject project, StatusNode statusNode) {
		super(project, statusNode);
	}

	@Override
	public Object getResource() {
		if(this.statusNode instanceof Requirement){
			Requirement req = (Requirement)this.statusNode;
			return req.getResource();
		}
		return null;
	}

	@Override
	public void setResource(Object o) {
		if(this.statusNode instanceof Requirement){
			Requirement req = (Requirement)this.statusNode;
			req.setResource(o);
		}
	}

}
