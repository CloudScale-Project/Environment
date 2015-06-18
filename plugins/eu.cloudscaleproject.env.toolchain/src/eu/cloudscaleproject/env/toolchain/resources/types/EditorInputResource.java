package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.ResourceValidationStatus;
import eu.cloudscaleproject.env.common.notification.StatusManager;

public abstract class EditorInputResource implements IEditorInputResource{
	
	public static final String PROP_FIRE_DIRTY_STATE = "eu.cloudscaleproject.env.toolchain.resources.types.EditorInputResource.fireDirtyState";
	
	public static final String PROP_SAVED = "eu.cloudscaleproject.env.toolchain.resources.types.EditorInputResource.propSaved";
	public static final String PROP_LOADED = "eu.cloudscaleproject.env.toolchain.resources.types.EditorInputResource.propLoaded";
	public static final String PROP_DELETED = "eu.cloudscaleproject.env.toolchain.resources.types.EditorInputResource.propDeleted";
	
	private final Object statusLock = new Object();
	
	protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final  HashSet<IValidationStatus> statusSet = new HashSet<IValidationStatus>();
	
	private IValidationStatus selfStatus;
		
	public EditorInputResource() {
		this.pcs.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (EditorInputResource.this.getID() != null)
					StatusManager.getInstance().validateAsync(getProject(), EditorInputResource.this);
			}
		});
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
	public String getID() {
		//Override in derived classes
		return null;
	}
	
	public IProject getProject() {
		return getResource().getProject();
	}
	
	@Override
	public IValidationStatus getSelfStatus() {
		if(this.selfStatus == null){
			this.selfStatus = new ResourceValidationStatus(this, getID(), getResource());
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
		if(!isLoaded()){
			load();
		}
		StatusManager.getInstance().validate(getProject(), this);
	}
	
	public void fireDirtyState(){
		pcs.firePropertyChange(PROP_FIRE_DIRTY_STATE, null, isDirty());
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
