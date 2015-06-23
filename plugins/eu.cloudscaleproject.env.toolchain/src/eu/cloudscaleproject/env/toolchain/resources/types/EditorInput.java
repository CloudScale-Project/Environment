package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.ValidationStatus;

public class EditorInput implements IEditorInput, IValidationStatusProvider{
	
	//status variables
	private final  HashSet<IValidationStatus> statusSet = new HashSet<IValidationStatus>();
	private final Object statusLock = new Object();
	
	protected IValidationStatus selfStatus;
	protected final String validatorID;
	
	protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	//transient variables
	private String name = "";
	private HashMap<Object, Object> objects = new HashMap<Object, Object>();
	
	public EditorInput(String name) {
		this(name, null);
	}
	
	public EditorInput(String name, String validatorID){
		this.name = name;
		this.validatorID = validatorID;
	}
	
	@Override
	public String getID() {
		return validatorID;
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
	
	public void addStatus(IValidationStatus status){
		synchronized (statusLock) {
			statusSet.add(status);
		}
		pcs.firePropertyChange(PROP_STATUS_ADDED, null, status);
	}
	
	public void removeStatus(IValidationStatus status){
		synchronized (statusLock) {
			statusSet.remove(status);
		}
		pcs.firePropertyChange(PROP_STATUS_REMOVED, status, null);
	}
	
	@Override
	public IValidationStatus getSelfStatus() {
		if(this.selfStatus == null){
			this.selfStatus = new ValidationStatus(this, validatorID);
		}
		return selfStatus;
	}
	
	@Override
	public IValidationStatus[] getStatus() {
		IValidationStatus[] out = null;
		synchronized (statusLock) {
			out = statusSet.toArray(new IValidationStatus[statusSet.size()]);
		}
		return out;
	}
	
	@Override
	public IValidationStatus[] getStatus(String id) {
		List<IValidationStatus> out = new ArrayList<IValidationStatus>();
		
		synchronized (statusLock) {
			for(IValidationStatus status : statusSet){
				if(id.equals(status.getID())){
					out.add(status);
				}
			}
		}
		
		return out.toArray(new IValidationStatus[out.size()]);
	}
	
	@Override
	public void validate() {
		if(validatorID != null){
			StatusManager.getInstance().validate(null, this);
		}
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(propertyName, listener);
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(propertyName, listener);
	}
}
