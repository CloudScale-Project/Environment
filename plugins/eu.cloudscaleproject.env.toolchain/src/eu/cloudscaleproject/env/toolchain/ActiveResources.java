package eu.cloudscaleproject.env.toolchain;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ActiveResources implements IActiveResources{

	private IProject project = null;
	private IEditorInputResource alternative = null;
	private IValidationStatusProvider statusProvider = null;
	
	public void setProject(IProject project){
		this.project = project;
	}
	
	public void setAlternative(IEditorInputResource alternative){
		this.alternative = alternative;
	}
	
	public void setStatusProvideer(IValidationStatusProvider statusProvider){
		this.statusProvider = statusProvider;
	}
	
	@Override
	public IProject getProject() {
		return this.project;
	}

	@Override
	public IEditorInputResource getActiveAlternative() {
		return this.alternative;
	}

	@Override
	public IValidationStatusProvider getActiveStatusProvider() {
		return this.statusProvider;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alternative == null) ? 0 : alternative.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((statusProvider == null) ? 0 : statusProvider.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActiveResources other = (ActiveResources) obj;
		if (alternative == null) {
			if (other.alternative != null)
				return false;
		} else if (!alternative.equals(other.alternative))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (statusProvider == null) {
			if (other.statusProvider != null)
				return false;
		} else if (!statusProvider.equals(other.statusProvider))
			return false;
		return true;
	}
	
}