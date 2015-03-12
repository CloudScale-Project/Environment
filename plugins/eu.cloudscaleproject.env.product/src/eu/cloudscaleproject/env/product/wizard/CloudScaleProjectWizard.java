package eu.cloudscaleproject.env.product.wizard;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
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

		boolean b = super.performFinish();

		if (b && getNewProject() != null) {
			IProject p = getNewProject();
			CloudScaleProjectSupport.createDefaultProject(p);
		}

		return b;
	}

	@Override
	protected void updatePerspective() {
		commandExecutor.execute("eu.cloudscaleproject.env.product.command.openCsPerspectiv");
	}

}
