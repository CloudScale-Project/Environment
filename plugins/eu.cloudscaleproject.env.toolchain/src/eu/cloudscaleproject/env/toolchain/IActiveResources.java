package eu.cloudscaleproject.env.toolchain;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public interface IActiveResources{
	
	public IProject getProject();
	public IEditorInputResource getActiveAlternative();
	public IValidationStatusProvider getActiveStatusProvider();

}
