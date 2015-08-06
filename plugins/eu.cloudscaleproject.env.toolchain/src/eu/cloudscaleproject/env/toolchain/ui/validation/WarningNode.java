package eu.cloudscaleproject.env.toolchain.ui.validation;

import eu.cloudscaleproject.env.common.notification.IValidationStatus.Warning;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class WarningNode {
	
	private final Warning warning;
	private Object parent;

	public WarningNode(Warning warning) {
		this.warning = warning;
	}
	
	public Warning getWarning(){
		return this.warning;
	}
	
	public void setParent(Object status){
		this.parent = status;
	}
	
	public Object getParent(){
		return this.parent;
	}
}
