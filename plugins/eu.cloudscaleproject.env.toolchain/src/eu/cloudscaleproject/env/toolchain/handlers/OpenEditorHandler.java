 
package eu.cloudscaleproject.env.toolchain.handlers;

import javax.inject.Named;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;

public class OpenEditorHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable) {
		
		final ExplorerEditorNode node = (ExplorerEditorNode)adaptable.getAdapter(ExplorerEditorNode.class);
		if(node != null){
			BusyIndicator.showWhile(Display.getDefault(), new Runnable() {
				
				@Override
				public void run() {
					node.openEditor();
				}
			});
		}
		
	}
	
	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) IAdaptable adaptable){
		ExplorerEditorNode node = (ExplorerEditorNode)adaptable.getAdapter(ExplorerEditorNode.class);
		if(node != null){
			return true;
		}
		return false;
	}
		
}