package eu.cloudscaleproject.env.common.notification;

import org.eclipse.emf.validation.internal.modeled.model.validation.SeverityEnum;

import eu.cloudscaleproject.env.common.BasicCallback;

public interface IValidationStatus{
	
	public static final String PROP_NAME = "eu.cloudscaleproject.env.common.notification.toolstatus.name";
	public static final String PROP_VALID = "eu.cloudscaleproject.env.common.notification.toolstatus.done";
	public static final String PROP_DIRTY = "eu.cloudscaleproject.env.common.notification.toolstatus.dirty";
	public static final String PROP_SOURCE = "eu.cloudscaleproject.env.common.notification.toolstatus.source";

	public static final String PROP_WARNING_ADD = "eu.cloudscaleproject.env.common.notification.toolstatus.warning.add";
	public static final String PROP_WARNING_REMOVE = "eu.cloudscaleproject.env.common.notification.toolstatus.warning.remove";
	public static final String PROP_WARNING_CHANGED = "eu.cloudscaleproject.env.common.notification.toolstatus.warning.change";
	
	public static final String PROP_UPDATE_ALL = "eu.cloudscaleproject.env.common.notification.toolstatus.updateall";
	
	public static final int SEVERITY_INFO = SeverityEnum.INFO_VALUE;
	public static final int SEVERITY_WARNING = SeverityEnum.WARNING_VALUE;
	public static final int SEVERITY_ERROR = SeverityEnum.ERROR_VALUE;
	
	public String getName();
	public String getID();
	public IValidationStatusProvider getProvider();
	
	public boolean hasWarnings();
	public boolean isDirty();
	public boolean isValid();
	public boolean isDone();
	
	public void setName(String name);
	public void setIsDirty(boolean dirty);	
	public void setIsValid(boolean isDone);
	
	public class Warning{
		public String message;
		public int severity;
		BasicCallback<Object> handler;
	}
	
	public Warning[] getWarnings();
	public String getWarningMessage(String id);
	public int getWarningType(String id);
	public String[] getWarningIDs();
		
	public void addWarning(String id, int severity, String message);
	public void addWarning(String id, int severity, String message, BasicCallback<Object> handle);
	public void handleWarning(String id);
	
	public void checkError(String id, boolean expression, boolean throwException, String message) throws ValidationException;
	public void check(String id, boolean expression, boolean throwException, int severity, String message) throws ValidationException;
	public void check(String id, boolean expression, boolean throwException, int severity, String message, BasicCallback<Object> handler) throws ValidationException;
	
	public void removeWarning(String id);
	public void clearWarnings();
	
	public void addListener(IValidationStatusListener listener);
	public void removeListener(IValidationStatusListener listener);
}
