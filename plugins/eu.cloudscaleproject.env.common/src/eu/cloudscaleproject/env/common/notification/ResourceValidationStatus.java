package eu.cloudscaleproject.env.common.notification;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.common.interfaces.IProjectProvider;

public class ResourceValidationStatus extends ValidationStatus implements IProjectProvider{

	private IResource resource;
	
	public ResourceValidationStatus(IValidationStatusProvider provider, String id, IResource resource) {
		super(provider, id);
		this.resource = resource;
	}
	
	public IResource getResource(){
		return this.resource;
	}
	
	public IProject getProject(){
		if(resource != null){
			return resource.getProject();
		}
		if(provider instanceof IProjectProvider){
			return ((IProjectProvider)provider).getProject();
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceValidationStatus other = (ResourceValidationStatus) obj;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		return true;
	}
}
