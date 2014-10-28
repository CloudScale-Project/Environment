package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeListener;

public interface IEditorInput {
			
	public String getName();
	public void setName(String name);
	
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public void removePropertyChangeListener(PropertyChangeListener listener);
}
