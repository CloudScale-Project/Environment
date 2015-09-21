package eu.cloudscaleproject.env.toolchain.explorer;

import org.eclipse.core.expressions.PropertyTester;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerPropertyTester extends PropertyTester {

	private static final String PROP_NODE_ID = "id";
	private static final String PROP_NODE_PARENT_ID = "idParent";

	
	public ExplorerPropertyTester() {
	}

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		
		if(receiver instanceof IExplorerNode){
			IExplorerNode node = (IExplorerNode)receiver;
						
			if(PROP_NODE_ID.equals(property)){
				if(expectedValue.equals(node.getID())){
					return true;
				}
			}
			else if(PROP_NODE_PARENT_ID.equals(property)){
				IExplorerNode parentNode = node.getParent();
				if(parentNode != null && expectedValue.equals(parentNode.getID())){
					return true;
				}
			}
		}
		
		return false;
	}

}
