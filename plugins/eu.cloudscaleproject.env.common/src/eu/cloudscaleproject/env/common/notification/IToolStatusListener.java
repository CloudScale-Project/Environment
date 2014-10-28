package eu.cloudscaleproject.env.common.notification;

public interface IToolStatusListener {
	
	public static final String PROP_REQ = "eu.cloudscaleproject.env.common.notification.toolstatus.req";
	public static final String PROP_VALID = "eu.cloudscaleproject.env.common.notification.toolstatus.valid";
	public static final String PROP_PROGRESS = "eu.cloudscaleproject.env.common.notification.toolstatus.progress";
	public static final String PROP_DONE = "eu.cloudscaleproject.env.common.notification.toolstatus.done";
	
	public static final String PROP_WARNING_ADD = "eu.cloudscaleproject.env.common.notification.toolstatus.warning.add";
	public static final String PROP_WARNING_REMOVE = "eu.cloudscaleproject.env.common.notification.toolstatus.warning.remove";
	public static final String PROP_WARNING_CHANGED = "eu.cloudscaleproject.env.common.notification.toolstatus.warning.change";
	
	public static final String PROP_UPDATE_ALL = "eu.cloudscaleproject.env.common.notification.toolstatus.updateall";
	public static final String PROP_REQUIREMENT_UPDATE = "eu.cloudscaleproject.env.common.notification.toolstatus.updatereq";
	
	public void notifie(String prop, IToolStatus status);

}
