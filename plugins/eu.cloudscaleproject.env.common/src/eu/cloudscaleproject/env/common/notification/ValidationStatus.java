package eu.cloudscaleproject.env.common.notification;

import java.beans.PropertyChangeSupport;
import java.util.LinkedHashMap;
import java.util.Set;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.interfaces.IProjectProvider;

public class ValidationStatus implements IValidationStatus, IProjectProvider{

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private String name;
	private final String id;
	
	private boolean isDirty = false;
	private boolean isValid = false;	
	private LinkedHashMap<String, Warning> warnings = new LinkedHashMap<String, Warning>();
	
	private final IValidationStatusProvider provider;
	
	public ValidationStatus(IValidationStatusProvider provider, String id) {
		this.provider = provider;
		this.id = id;
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
	
	@Override
	public String getID() {
		return id;
	}
	
	@Override
	public IProject getProject() {
		if(provider instanceof IProjectProvider){
			IProjectProvider pp = (IProjectProvider)provider;
			return pp.getProject();
		}
		return null;
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
	
	public Warning[] getWarnings(){
		return warnings.values().toArray(new Warning[warnings.values().size()]);
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
	public int getWarningType(String id) {
		Warning w = warnings.get(id);
		if(w != null){
			return w.severity;
		}
		return SEVERITY_ERROR;
	};

	@Override
	public Set<String> getWarningIDs() {
		return warnings.keySet();
	}

	@Override
	public void addWarning(String id, int severity, String message) {
		Warning w = new Warning();
		w.severity = severity;
		w.message = message;
		warnings.put(id, w);
		setIsValid(false);
		
		pcs.firePropertyChange(PROP_WARNING_ADD, null, id);
	}

	@Override
	public void addWarning(String id, int severity, String message, BasicCallback<Object> handler) {
		Warning w = new Warning();
		w.severity = severity;
		w.message = message;
		w.handler = handler;
		warnings.put(id, w);
		setIsValid(false);
		
		pcs.firePropertyChange(PROP_WARNING_ADD, null, id);
	}
	
	@Override
	public void checkError(String id, boolean expression, boolean throwException, 
						   String message) throws ValidationException{
		if(expression){
			removeWarning(id);
		}
		else{
			addWarning(id, SEVERITY_ERROR, message);
			if(throwException){
				throw new ValidationException(id);
			}
		}
	}
	
	@Override
	public void check(String id, boolean expression, boolean throwException, 
					  int severity, String message) throws ValidationException{
		if(expression){
			removeWarning(id);
		}
		else{
			addWarning(id, severity, message);
			if(throwException){
				throw new ValidationException(id);
			}
		}
	}

	@Override
	public void check(String id, boolean expression, boolean throwException, 
					  int severity, String message, BasicCallback<Object> handler) throws ValidationException{
		if(expression){
			removeWarning(id);
		}
		else{
			addWarning(id, severity, message, handler);
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
		for (Object id : warnings.keySet().toArray()){
			removeWarning((String)id);
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
				+ ((provider == null) ? 0 : provider.hashCode());
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
		ValidationStatus other = (ValidationStatus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		return true;
	}
	
}
