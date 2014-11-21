package eu.cloudscaleproject.env.common.notification;

import java.util.HashSet;
import java.util.Set;

class DummyToolStatus implements IToolStatus, IResourceStatus{

	@Override
	public boolean hasMetRequirements() {return true;}
	@Override
	public boolean hasWarnings() {return false;}
	@Override
	public boolean isInProgress() {return true;}
	@Override
	public boolean isDone() {return true;}
	@Override
	public void setMetRequirements(boolean metRequ) {}
	@Override
	public void setIsInProgress(boolean inProgress) {}
	@Override
	public void setIsDone(boolean isDone) {}
	@Override
	public void addWarning(String id, String message) {}
	@Override
	public void addWarning(String id, String message, String command) {}
	@Override
	public void addWarning(String id, String message, String command, String... param) {}
	@Override
	public void removeWarning(String id) {}
	@Override
	public void clearWarnings() {}
	@Override
	public void addListener(IToolStatusListener listener) {}
	@Override
	public void removeListener(IToolStatusListener listener) {}
	@Override
	public String getWarningMessage(String id) {return "";}
	@Override
	public String getWarningCommand(String id) {return null;}
	@Override
	public String[] getWarningCommandParam(String id) {return null;}
	@Override
	public Set<String> getWarningIDs() {return new HashSet<String>();}
	@Override
	public void handleWarning(String id, boolean expression,
			boolean throwException, String message)
			throws IllegalStateException {
		if(expression){
			if(throwException){
				throw new IllegalStateException();
			}
		}
	}
	@Override
	public void handleWarning(String id, boolean expression,
			boolean throwException, String message, String command)
			throws IllegalStateException {
		if(expression){
			if(throwException){
				throw new IllegalStateException();
			}
		}
	}
	@Override
	public void handleWarning(String id, boolean expression,
			boolean throwException, String message, String command,
			String... param) throws IllegalStateException {
		if(expression){
			if(throwException){
				throw new IllegalStateException();
			}
		}
	}
	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setIsDirty(boolean dirty) {
		// TODO Auto-generated method stub
	}
	@Override
	public void setIsDirtyNextRecursive(boolean dirty) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getResource() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setResource(Object o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setInstanceName(String name) {
		// TODO Auto-generated method stub
		
	}
}