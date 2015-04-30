package eu.cloudscaleproject.env.common.notification;

public class ValidationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String error;
	
	public ValidationException(String error){
		this.error = error;
	}
	
	public String getObject(){
		return error;
	}
}
