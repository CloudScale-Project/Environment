package eu.cloudscaleproject.env.method.common.util; 
import org.eclipse.graphiti.util.IColorConstant;

public interface MethodColorConstants extends IColorConstant
{
	
	String STATUS_NODE = "a1c9d5"; // ~blue
	String STATUS_NODE_DISABLED = "cdcdcd"; // ~gray
	String STATUS_NODE_WARNING = "ffd073";  // ~orange
	String STATUS_NODE_ERROR = "ffaa92";  // ~orange
	String STATUS_NODE_DONE = "91e5a5";		// ~green

	String REQUIREMENT = STATUS_NODE;
	String REQUIREMENT_WARNING = STATUS_NODE_WARNING;
	String REQUIREMENT_ERROR = STATUS_NODE_ERROR;
	String REQUIREMENT_DONE = STATUS_NODE_DONE;

	String ACTION = STATUS_NODE;
	String COMMAND = STATUS_NODE;
	String CONTAINER = STATUS_NODE;


}
