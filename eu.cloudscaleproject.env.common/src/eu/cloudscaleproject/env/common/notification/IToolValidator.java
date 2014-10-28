package eu.cloudscaleproject.env.common.notification;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

public interface IToolValidator {
	
	public String getToolID();
	public IResource[] getDependantResources(IProject project);
	
	public boolean validate(IProject project);

}
