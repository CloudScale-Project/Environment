package eu.cloudscaleproject.env.extractor.alternatives;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.somox.configuration.SoMoXConfiguration;

import eu.cloudscaleproject.env.extractor.util.ExtractorRunJob;
import eu.cloudscaleproject.env.extractor.util.SomoxConfigurationUtil;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;

public class ConfingAlternative extends AbstractConfigAlternative
{
	public static final String KEY_INPUT_PROJECT = "extracted_project";

	public static final String KEY_MODISCO_CONFIG = "modisco";

	private SoMoXConfiguration somoxConfiguration;

	public ConfingAlternative(IProject project, IFolder folder)
	{
		super(project, folder, null, CSTool.EXTRACTOR);

		initSomoxModel();
	}

	public SoMoXConfiguration getSomoxConfiguration()
	{
		return somoxConfiguration;
	}

	@Override
	public void doLoad(IProgressMonitor monitor)
	{
		super.doLoad(monitor);
		loadSomoxModel();
	}

	private void loadSomoxModel()
	{
		this.somoxConfiguration = SomoxConfigurationUtil.loadSomoxConfiguration(this);
	}

	private void initSomoxModel()
	{
		this.somoxConfiguration = SomoxConfigurationUtil.createDefaultSomoxConfiguration();
	}

	@Override
	protected IStatus doRun(IProgressMonitor monitor) throws CoreException
	{
		ExtractorRunJob job = new ExtractorRunJob(ConfingAlternative.this);
		job.run(monitor);

		return Status.OK_STATUS;
	}
}
