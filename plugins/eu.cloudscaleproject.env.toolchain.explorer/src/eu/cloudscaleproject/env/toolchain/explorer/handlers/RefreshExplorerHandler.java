 
package eu.cloudscaleproject.env.toolchain.explorer.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import eu.cloudscaleproject.env.toolchain.explorer.Explorer;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

public class RefreshExplorerHandler {
	
	@Execute
	public void execute(ESelectionService selectionService) {
		
		Explorer.getInstance().getRoot().refresh();
		
		Object selection = selectionService.getSelection();
		if(selection instanceof Object[]){
			for(Object o : (Object[])selection){
				
				if(o instanceof IExplorerNode){
					IExplorerNode node = (IExplorerNode)o;					
					node.refresh();
				}
			}
		}
		else if(selection instanceof IExplorerNode){
			IExplorerNode node = (IExplorerNode)selection;
			node.refresh();
		}
		
	}
		
}