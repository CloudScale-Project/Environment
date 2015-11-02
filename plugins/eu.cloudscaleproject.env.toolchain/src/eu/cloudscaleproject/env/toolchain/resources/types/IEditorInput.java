package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeListener;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public interface IEditorInput extends IValidationStatusProvider{
	
	public static final String PROP_NAME = "eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput.name";
			
	public String getName();
	public void setName(String name);
	
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
	public void removePropertyChangeListener(PropertyChangeListener listener);
}
