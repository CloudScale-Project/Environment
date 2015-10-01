package eu.cloudscaleproject.env.product.wizard;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.product.Activator;

public class CloudScaleProjectWizard extends BasicNewProjectResourceWizard {

	@Inject
	private CommandExecutor commandExecutor;

	public CloudScaleProjectWizard() {
		super();

		IEclipseContext context = EclipseContextFactory
				.getServiceContext(Activator.getContext());
		ContextInjectionFactory.inject(this, context);
	}

	@Override
	public void addPages() {
		// Add main page
		super.addPages();
	}

	@Override
	public boolean performFinish() {

		final boolean b = super.performFinish();

		WorkspaceJob job = new WorkspaceJob("Configuring Cloudscale project") {
			
			@Override
			public IStatus runInWorkspace(IProgressMonitor arg0) throws CoreException {
				if (b && getNewProject() != null) {
					IProject p = getNewProject();
					CloudScaleProjectSupport.createDefaultProject(p);
				}
				
				return Status.OK_STATUS;
			}
		};
		
		job.setUser(true);
		job.schedule();

		return b;
	}

	@Override
	protected void updatePerspective() {
		//commandExecutor.execute("eu.cloudscaleproject.env.product.command.openCsPerspectiv");
		
		commandExecutor.execute("eu.cloudscaleproject.env.product.command.openperspective", 
								"eu.cloudscaleproject.env.product.perspective.main");

	}

}
