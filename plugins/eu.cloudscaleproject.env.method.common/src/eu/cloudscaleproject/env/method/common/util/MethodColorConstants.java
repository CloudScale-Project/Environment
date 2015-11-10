package eu.cloudscaleproject.env.method.common.util; 
import org.eclipse.graphiti.util.IColorConstant;

public interface MethodColorConstants extends IColorConstant
{
	
	String STATUS_NODE = "f1c9d5"; // ~blue
	String STATUS_NODE_DISABLED = STATUS_NODE;//"cdcdcd"; // ~gray
	String STATUS_NODE_WARNING = "ffd073";  // ~orange
	String STATUS_NODE_ERROR = "ffaa92";  // ~orange
	String STATUS_NODE_DONE = "91e5a5";		// ~green

	String REQUIREMENT = STATUS_NODE;
	String REQUIREMENT_WARNING = STATUS_NODE_WARNING;
	String REQUIREMENT_ERROR = STATUS_NODE_ERROR;
	String REQUIREMENT_DONE = STATUS_NODE_DONE;

	String ACTION = "567092";//STATUS_NODE;
	String COMMAND = "567092";//STATUS_NODE;
	String CONTAINER = "567092";//STATUS_NODE;


}
