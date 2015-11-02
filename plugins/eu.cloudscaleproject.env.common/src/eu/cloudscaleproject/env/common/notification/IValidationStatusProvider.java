package eu.cloudscaleproject.env.common.notification;

import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IProgressMonitor;

import eu.cloudscaleproject.env.common.interfaces.IProjectProvider;

public interface IValidationStatusProvider extends IProjectProvider{
	
	public static final String PROP_VALIDATED = "eu.cloudscaleproject.env.common.notification.IValidationStatusProvider.validated";
	public static final String PROP_STATUS_ADDED = "eu.cloudscaleproject.env.common.notification.IValidationStatusProvider.added";
	public static final String PROP_STATUS_REMOVED = "eu.cloudscaleproject.env.common.notification.IValidationStatusProvider.removed";

	public String getID();
	
	public boolean isValidated();
	public void setValidated(boolean validated);
	
	public IValidationStatus getSelfStatus();
	public IValidationStatus[] getSubStatuses();
	public IValidationStatus[] getSubStatus(String id);
		
	public void validate(IProgressMonitor monitor);
	public void validate();
	
	public void addStatusChangeListener(String propertyName, PropertyChangeListener listener);
	public void addStatusChangeListener(PropertyChangeListener listener);
	public void removeStatusChangeListener(PropertyChangeListener listener);
	public void removeStatusChangeListener(String propertyName, PropertyChangeListener listener);
}
