package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusListener;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.ValidationStatus;

public class EditorInput implements IEditorInput, IValidationStatusProvider{
	
	//status variables
	private final  HashSet<IValidationStatus> statusSet = new HashSet<IValidationStatus>();
	private final Object statusLock = new Object();
	
	private IValidationStatus selfStatus = null;
	protected final String validatorID;
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final PropertyChangeSupport pcsStatus = new PropertyChangeSupport(this);

	
	//transient variables
	private String name = "No name";
	private HashMap<Object, Object> objects = new HashMap<Object, Object>();
	
	//wire status property change with the status provider property change support 
	private final IValidationStatusListener statusListener = new IValidationStatusListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			pcsStatus.firePropertyChange(evt);
		}
	};
	
	public EditorInput(String name) {
		this(name, null);
	}
	
	public EditorInput(String name, String validatorID){
		this.name = name;
		this.validatorID = validatorID;
	}
	
	@Override
	public IProject getProject() {
		return null;
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
		firePropertyChange(PROP_NAME, this.name, this.name = name);
	}
	
	public void addObject(Object key, Object value){
		objects.put(key, value);
	}
	
	public Object getObject(Object key){
		return objects.get(key);
	}
	
	protected IValidationStatus createSelfStatus(){
		return new ValidationStatus(this, validatorID);
	}
	
	public final void addStatus(IValidationStatus status){
		synchronized (statusLock) {
			status.addListener(statusListener);
			statusSet.add(status);
		}
		pcsStatus.firePropertyChange(PROP_STATUS_ADDED, null, status);
	}
	
	public final void removeStatus(IValidationStatus status){
		synchronized (statusLock) {
			status.removeListener(statusListener);
			statusSet.remove(status);
		}
		pcsStatus.firePropertyChange(PROP_STATUS_REMOVED, status, null);
	}
	
	@Override
	public final IValidationStatus getSelfStatus() {
		synchronized (statusLock) {
			if(this.selfStatus == null){
				
				this.selfStatus = createSelfStatus();
				this.selfStatus.setName(getName());
				
				//wire alternative name together with the self status name
				this.addPropertyChangeListener(PROP_NAME, new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						Object newName = evt.getNewValue();
						if(newName instanceof String){
							EditorInput.this.selfStatus.setName((String)evt.getNewValue());
						}
					}
				});
				
				this.selfStatus.addListener(statusListener);
			}
		}
		return selfStatus;
	}
	
	@Override
	public IValidationStatus[] getSubStatuses() {
		IValidationStatus[] out = null;
		synchronized (statusLock) {
			out = statusSet.toArray(new IValidationStatus[statusSet.size()]);
		}
		return out;
	}
	
	@Override
	public IValidationStatus[] getSubStatus(String id) {
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
		validate(null);
	}
	
	@Override
	public void validate(IProgressMonitor monitor) {
		
		if(monitor != null){
			monitor.subTask("Validating " + getName());
		}
		
		if(validatorID != null){
			StatusManager.getInstance().validate(null, this);
		}
	}
	
	protected void firePropertyChange(String name, Object oldValue, Object newValue){
		pcs.firePropertyChange(name, oldValue, newValue);
	}

	//property changes
	@Override
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
	@Override
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(propertyName, listener);
	}
	
	//status changes
	@Override
	public void addStatusChangeListener(String propertyName, PropertyChangeListener listener) {
		pcsStatus.addPropertyChangeListener(propertyName, listener);
	}
	@Override
	public void addStatusChangeListener(PropertyChangeListener listener) {
		pcsStatus.addPropertyChangeListener(listener);
	}
	@Override
	public void removeStatusChangeListener(PropertyChangeListener listener) {
		pcsStatus.removePropertyChangeListener(listener);
	}
	@Override
	public void removeStatusChangeListener(String propertyName, PropertyChangeListener listener) {
		pcsStatus.removePropertyChangeListener(propertyName, listener);
	}
	
}
