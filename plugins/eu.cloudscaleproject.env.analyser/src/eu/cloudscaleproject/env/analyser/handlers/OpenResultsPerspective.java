 
package eu.cloudscaleproject.env.analyser.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import eu.cloudscaleproject.env.product.branding.CloudScaleBranding;

public class OpenResultsPerspective {
	
	// !! Tried parameterized command for main and results perspective but couldn't make it to work
	// Duplication ftw

	@Execute
	public void execute() {
		
		
		//using old e3 show perspective call
		try {
			PlatformUI.getWorkbench().showPerspective("org.palladiosimulator.edp2.ui.perspective", 
					PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			CloudScaleBranding.initProjectExplorer();
		} catch (WorkbenchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

		
}