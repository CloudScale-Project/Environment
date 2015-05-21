package eu.cloudscaleproject.env.common.notification;

import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Set;

import eu.cloudscaleproject.env.common.BasicCallback;

public class ValidationStatus implements IValidationStatus{
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private String name;
	private final String id;
	
	private boolean isDirty = false;
	private boolean isValid = false;	
	private HashMap<String, Warning> warnings = new HashMap<String, Warning>();
	
	private class Warning{
		public String message;
		BasicCallback<Object> handler;
	}
	
	public ValidationStatus(String id) {
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
	public void setIsValid(boolean isValid) {
		pcs.firePropertyChange(PROP_VALID, this.isValid, this.isValid = isValid);
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
}
