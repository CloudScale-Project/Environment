package eu.cloudscaleproject.env.common.notification;

import org.eclipse.core.resources.IProject;

public interface IResourceValidator{
		
	public String getID();		
	public void validate(IProject project, IValidationStatusProvider statusProvider);

}
