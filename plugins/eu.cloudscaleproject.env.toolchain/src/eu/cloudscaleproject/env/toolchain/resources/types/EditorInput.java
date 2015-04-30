package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class EditorInput implements IEditorInput{
			
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private String name = "";
	private HashMap<Object, Object> objects = new HashMap<Object, Object>();
	
	public EditorInput(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		pcs.firePropertyChange(PROP_NAME, this.name, this.name = name);
	}
	
	public void addObject(Object key, Object value){
		objects.put(key, value);
	}
	
	public Object getObject(Object key){
		return objects.get(key);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
