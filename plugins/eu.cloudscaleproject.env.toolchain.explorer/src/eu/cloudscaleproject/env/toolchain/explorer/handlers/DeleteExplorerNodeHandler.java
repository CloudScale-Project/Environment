package eu.cloudscaleproject.env.toolchain.explorer.handlers;

import javax.inject.Named;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;

import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class DeleteExplorerNodeHandler {

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable) {
		
		//TODO: implement
		//IExplore rNode node = (IExplorerNode)adaptable.getAdapter(IExplorerNode.class);
		
		
	}
	
}
