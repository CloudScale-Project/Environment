package eu.cloudscaleproject.env.common.notification.diagram;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public interface IValidationDiagram {
		
	public void show();
	
	public IProject getProject();
	public void setProject(IProject project);
	
	public void bindStatusProvider(IValidationStatusProvider statusProvider);
	public void unbindStatusProvider(IValidationStatusProvider statusProvider);

	public void bindStatus(IValidationStatus status);
	public void unbindStatus(IValidationStatus status);
	
	public abstract IValidationStatusProvider getActiveStatusProvider(String id);
}
