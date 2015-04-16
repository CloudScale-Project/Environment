package eu.cloudscaleproject.env.toolchain.resources.validation;

import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.common.notification.IResourceStatus;
import eu.cloudscaleproject.env.common.notification.IToolStatusListener;

public class ResourceStatus implements IResourceStatus{
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private Object resource;
	
	private boolean isDirty = false;
	private boolean isDone = false;
	private boolean isInProgress = true;
	
	private HashMap<String, Warning> warnings = new HashMap<String, Warning>();
	
	private class Warning{
		public String message;
		public String command;
		public String[] commandParam;
	}
	
	@Override
	public boolean hasMetRequirements() {
		return true;
	}

	@Override
	public boolean hasWarnings() {
		return !warnings.isEmpty();
	}

	@Override
	public boolean isInProgress() {
		return isInProgress;
	}

	@Override
	public boolean isDirty() {
		return isDirty;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	@Override
	public void setInstanceName(String name) {}
	@Override
	public void setMetRequirements(boolean metRequ) {}
	@Override
	public void setIsDirtyNextRecursive(boolean dirty) {}

	@Override
	public void setIsDirty(boolean dirty) {
		pcs.firePropertyChange(IToolStatusListener.PROP_DIRTY, this.isDirty, this.isDirty = dirty);
		
	}

	@Override
	public void setIsInProgress(boolean inProgress) {
		pcs.firePropertyChange(IToolStatusListener.PROP_PROGRESS, this.isInProgress, this.isInProgress = inProgress);
	}

	@Override
	public void setIsDone(boolean isDone) {
		pcs.firePropertyChange(IToolStatusListener.PROP_DIRTY, this.isDone, this.isDone = isDone);
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
	public String getWarningCommand(String id) {
		Warning w = warnings.get(id);
		if(w != null){
			return w.command;
		}
		return null;
	}

	@Override
	public String[] getWarningCommandParam(String id) {
		Warning w = warnings.get(id);
		if(w != null){
			return w.commandParam;
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
	}

	@Override
	public void addWarning(String id, String message, String command) {
		Warning w = new Warning();
		w.message = message;
		w.command = command;
		warnings.put(id, w);
	}

	@Override
	public void addWarning(String id, String message, String command,
			String... param) {
		Warning w = new Warning();
		w.message = message;
		w.command = command;
		w.commandParam = param;
		warnings.put(id, w);
	}

	@Override
	public void handleWarning(String id, boolean expression,
			boolean throwException, String message)
			throws IllegalStateException {
		
		if(expression){
			warnings.remove(id);
		}
		else{
			addWarning(id, message);
			if(throwException){
				throw new IllegalArgumentException();
			}
		}
	}

	@Override
	public void handleWarning(String id, boolean expression,
			boolean throwException, String message, String command)
			throws IllegalStateException {
		
		if(expression){
			warnings.remove(id);
		}
		else{
			addWarning(id, message, command);
			if(throwException){
				throw new IllegalArgumentException();
			}
		}
	}

	@Override
	public void handleWarning(String id, boolean expression,
			boolean throwException, String message, String command,
			String... param) throws IllegalStateException {
		
		if(expression){
			warnings.remove(id);
		}
		else{
			addWarning(id, message, command, param);
			if(throwException){
				throw new IllegalArgumentException();
			}
		}
	}

	@Override
	public void removeWarning(String id) {
		warnings.remove(id);
	}

	@Override
	public void clearWarnings() {
		warnings.clear();
	}

	@Override
	public void addListener(IToolStatusListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void removeListener(IToolStatusListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	@Override
	public void setResource(Object o) {
		if(o instanceof IResource){
			this.resource = o;
		}
		else{
			throw new IllegalArgumentException("Resource must implement org.eclipse.core.resources.IResource!");
		}
	}

	@Override
	public IResource getResource() {
		return (IResource)this.resource;
	}

	

}
