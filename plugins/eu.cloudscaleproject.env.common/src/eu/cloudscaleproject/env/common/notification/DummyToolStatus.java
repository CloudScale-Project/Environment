package eu.cloudscaleproject.env.common.notification;

import java.util.HashSet;
import java.util.Set;

import eu.cloudscaleproject.env.common.BasicCallback;

class DummyToolStatus implements IValidationStatus{
	
	@Override
	public String getID() {
		return null;
	}
	
	@Override
	public String getName() {
		return "Dummy status";
	}
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean hasWarnings() {return false;}
	@Override
	public boolean isDone() {return true;}
	@Override
	public void setIsValid(boolean isDone) {}
	@Override
	public void addWarning(String id, int severity, String message) {}
	@Override
	public void removeWarning(String id) {}
	@Override
	public void clearWarnings() {}
	@Override
	public void addListener(IValidationStatusListener listener) {}
	@Override
	public void removeListener(IValidationStatusListener listener) {}
	@Override
	public String getWarningMessage(String id) {return "";}
	@Override
	public int getWarningType(String id){return SEVERITY_ERROR;};
	@Override
	public Set<String> getWarningIDs() {return new HashSet<String>();}
	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isValid() {
		return true;
	}
	
	@Override
	public void setIsDirty(boolean dirty) {
		// TODO Auto-generated method stub
	}
	@Override
	public void addWarning(String id, int severity, String message,
			BasicCallback<Object> handle) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void handleWarning(String id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void checkError(String id, boolean expression,
			boolean throwException, String message) throws ValidationException {
		// TODO Auto-generated method stub	
	}

	@Override
	public void check(String id, boolean expression, boolean throwException,
			 int severity, String message) throws ValidationException {
		// TODO Auto-generated method stub
	}

	@Override
	public void check(String id, boolean expression, boolean throwException,
			 int severity, String message, BasicCallback<Object> handler)
			throws ValidationException {
		// TODO Auto-generated method stub
	}

	@Override
	public IValidationStatusProvider getProvider() {
		return null;
	}
}