package eu.cloudscaleproject.env.product.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class ExitHandler {

	@Execute
	public void execute(IWorkbench workbench, EPartService partService){
		
		partService.saveAll(true);
		workbench.close();
	}
	
}
