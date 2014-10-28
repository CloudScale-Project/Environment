package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class EditorInput extends PropertyChangeSupport implements IEditorInput{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name = "";
	private HashMap<Object, Object> objects = new HashMap<Object, Object>();
	
	public EditorInput(String name) {
		super(new Object());
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public void addObject(Object key, Object value){
		objects.put(key, value);
	}
	
	public Object getObject(Object key){
		return objects.get(key);
	}
}
