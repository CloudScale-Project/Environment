package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeListener;

public interface IEditorInput{
	
	public static final String PROP_NAME = "eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput.name";
			
	public String getName();
	public void setName(String name);
	
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
	public void removePropertyChangeListener(PropertyChangeListener listener);
}
