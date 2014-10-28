package eu.cloudscaleproject.env.common.notification;

//TODO: this class is not used jet...
public class ToolValidatorException{

	private Object o;
	
	public ToolValidatorException(Object o){
		this.o = o;
	}
	
	public Object getObject(){
		return o;
	}
}
