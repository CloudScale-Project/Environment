package eu.cloudscaleproject.env.common.notification;

import java.beans.PropertyChangeListener;

public interface IValidationStatusProvider {
	
	public static final String PROP_STATUS_ADDED = "eu.cloudscaleproject.env.common.notification.IValidationStatusProvider.added";
	public static final String PROP_STATUS_REMOVED = "eu.cloudscaleproject.env.common.notification.IValidationStatusProvider.removed";

	public String getID();
	
	public IValidationStatus getSelfStatus();
	public IValidationStatus[] getStatus();
	public IValidationStatus[] getStatus(String id);
	
	public void validate();
	
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public void removePropertyChangeListener(PropertyChangeListener listener);
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
}
