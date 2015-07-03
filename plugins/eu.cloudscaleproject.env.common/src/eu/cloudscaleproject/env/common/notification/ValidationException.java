package eu.cloudscaleproject.env.common.notification;

public class ValidationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private Object id;
	private String message;
	
	public ValidationException(Object id, String message){
		this.id = id;
		this.message = message;
	}
	
	public String getObject(){
		return this.id.toString() + " Message: "+ message;
	}
}
