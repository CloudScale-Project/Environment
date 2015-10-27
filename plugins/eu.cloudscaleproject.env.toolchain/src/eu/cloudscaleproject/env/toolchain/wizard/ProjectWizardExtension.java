package eu.cloudscaleproject.env.toolchain.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;
import eu.cloudscaleproject.env.toolchain.services.IExplorerService;

public class ProjectWizardExtension implements NewProjectExtension{

	@Override
	public void finalize(final IProject project) {
				
		//select and expand node in explorer
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				//delay for 500ms, so the node is created
				Display.getDefault().timerExec(500, new Runnable() {
					
					@Override
					public void run() {
						IExplorerService explorerService = CloudscaleContext.getGlobalContext().get(IExplorerService.class);
						explorerService.setSelection(project);
					}
				});
			}
		});
		
	}

}
