 
package eu.cloudscaleproject.env.staticspotter.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class OpenAnnotationView {
	@Execute
	public void execute() {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
			showView("org.reclipse.ui.views.annotations");
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}