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
	public IProject getActiveProject() {
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
	
}