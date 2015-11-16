 
package eu.cloudscaleproject.env.toolchain.explorer.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import eu.cloudscaleproject.env.toolchain.explorer.Explorer;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class SelectInExplorerHandler {
	
	public static IEditorInputResource eir;

	@Execute
	public void execute() {
		
		if (eir == null) return;

		IExplorerNode en = Explorer.getInstance().findNode(eir);

		if (en != null) Explorer.getInstance().setSelection(en);
	}
	
	@CanExecute
	public boolean canExecute() {

		return (eir != null);
	}
}