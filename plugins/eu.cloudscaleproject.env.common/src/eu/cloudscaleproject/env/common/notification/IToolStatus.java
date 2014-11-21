package eu.cloudscaleproject.env.common.notification;

import java.util.Set;

public interface IToolStatus{
	
	public boolean hasMetRequirements();
	public boolean hasWarnings();
	public boolean isInProgress();
	public boolean isDirty();
	public boolean isDone();
	
	public void setInstanceName(String name);
	public void setMetRequirements(boolean metRequ);
	public void setIsDirtyNextRecursive(boolean dirty);
	public void setIsDirty(boolean dirty);
	
	public void setIsInProgress(boolean inProgress);
	public void setIsDone(boolean isDone);
	
	public String getWarningMessage(String id);
	public String getWarningCommand(String id);
	public String[] getWarningCommandParam(String id);
	public Set<String> getWarningIDs();
		
	public void addWarning(String id, String message);
	public void addWarning(String id, String message, String command);
	public void addWarning(String id, String message, String command, String... param);
	
	public void handleWarning(String id, boolean expression, boolean throwException, String message) throws IllegalStateException;
	public void handleWarning(String id, boolean expression, boolean throwException, String message, String command) throws IllegalStateException;
	public void handleWarning(String id, boolean expression, boolean throwException, String message, String command, String... param) throws IllegalStateException;
	
	public void removeWarning(String id);
	public void clearWarnings();
	
	public void addListener(IToolStatusListener listener);
	public void removeListener(IToolStatusListener listener);
}
