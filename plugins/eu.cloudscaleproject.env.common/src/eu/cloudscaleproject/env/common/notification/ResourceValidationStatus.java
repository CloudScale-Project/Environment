package eu.cloudscaleproject.env.common.notification;

import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.interfaces.IProjectProvider;

public class ResourceValidationStatus implements IValidationStatus, IProjectProvider{

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private String name;
	private final String id;
	private IResource resource;
	
	private boolean isDirty = false;
	private boolean isValid = false;	
	private HashMap<String, Warning> warnings = new HashMap<String, Warning>();
	
	private final IValidationStatusProvider provider;
	
	private class Warning{
		public String message;
		BasicCallback<Object> handler;
	}
	
	public ResourceValidationStatus(IValidationStatusProvider provider, String id) {
		this(provider, id, null);
	}
	
	public ResourceValidationStatus(IValidationStatusProvider provider, String id, IResource resource) {
		this.provider = provider;
		this.id = id;
		this.resource = resource;
	}
	
	@Override
	public void setName(String name) {
		pcs.firePropertyChange(PROP_NAME, this.name, this.name = name);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public IValidationStatusProvider getProvider() {
		return provider;
	}
	
	public IResource getResource(){
		return this.resource;
	}
	
	public IProject getProject(){
		if(resource != null){
			return resource.getProject();
		}
		if(provider instanceof IProjectProvider){
			return ((IProjectProvider)provider).getProject();
		}
		return null;
	}
	
	@Override
	public String getID() {
		return id;
	}

	@Override
	public boolean hasWarnings() {
		return !warnings.isEmpty();
	}

	@Override
	public boolean isDirty() {
		return isDirty;
	}

	@Override
	public boolean isValid() {
		return isValid;
	}
	
	@Override
	public boolean isDone() {
		return isValid && !isDirty && !hasWarnings();
	}

	@Override
	public void setIsDirty(boolean dirty) {
		pcs.firePropertyChange(PROP_DIRTY, this.isDirty, this.isDirty = dirty);
	}

	@Override
	public void setIsValid(boolean isDone) {
		pcs.firePropertyChange(PROP_VALID, this.isValid, this.isValid = isDone);
	}

	@Override
	public String getWarningMessage(String id) {
		Warning w = warnings.get(id);
		if(w != null){
			return w.message;
		}
		return null;
	}

	@Override
	public Set<String> getWarningIDs() {
		return warnings.keySet();
	}

	@Override
	public void addWarning(String id, String message) {
		Warning w = new Warning();
		w.message = message;
		warnings.put(id, w);
		setIsValid(false);
		
		pcs.firePropertyChange(PROP_WARNING_ADD, null, id);
	}

	@Override
	public void addWarning(String id, String message, BasicCallback<Object> handler) {
		Warning w = new Warning();
		w.message = message;
		w.handler = handler;
		warnings.put(id, w);
		setIsValid(false);
		
		pcs.firePropertyChange(PROP_WARNING_ADD, null, id);
	}
	
	@Override
	public void check(String id, boolean expression, boolean throwException, String message) throws ValidationException{
		if(expression){
			removeWarning(id);
		}
		else{
			addWarning(id, message);
			if(throwException){
				throw new ValidationException(id);
			}
		}
	}

	@Override
	public void check(String id, boolean expression, boolean throwException, String message, BasicCallback<Object> handler) throws ValidationException{
		if(expression){
			removeWarning(id);
		}
		else{
			addWarning(id, message, handler);
			if(throwException){
				throw new ValidationException(id);
			}
		}
	}
	
	@Override
	public void handleWarning(String id) {
		Warning w = warnings.get(id);
		if(w != null){
			w.handler.handle(this);
		}
	}

	@Override
	public void removeWarning(String id) {
		warnings.remove(id);
		pcs.firePropertyChange(PROP_WARNING_REMOVE, id, null);
	}

	@Override
	public void clearWarnings() {
		for (String id : warnings.keySet())
		{
			removeWarning(id);
		}
	}

	@Override
	public void addListener(IValidationStatusListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void removeListener(IValidationStatusListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceValidationStatus other = (ResourceValidationStatus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		return true;
	}
}
