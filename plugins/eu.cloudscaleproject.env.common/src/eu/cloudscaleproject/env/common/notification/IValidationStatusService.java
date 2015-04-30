package eu.cloudscaleproject.env.common.notification;


public interface IValidationStatusService {
	
	public IValidationStatus[] getValidationStatus(String validatorId);
	
}
