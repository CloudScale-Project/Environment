package eu.cloudscaleproject.env.common.notification;

public class MethodStatusContext
{
	private final String id; 
	private final IValidationStatus validationStatus;
	public MethodStatusContext(String id, IValidationStatus validationStatus)
	{
		super();
		this.id = id;
		this.validationStatus = validationStatus;
	}
	
	public String getId()
	{
		return id;
	}
	
	public IValidationStatus getValidationStatus()
	{
		return validationStatus;
	}
}
