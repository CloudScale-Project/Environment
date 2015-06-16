package eu.cloudscaleproject.env.extractor.alternatives;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.modisco.infra.discovery.catalog.DiscovererDescription;
import org.eclipse.modisco.infra.discovery.core.IDiscoveryManager;
import org.eclipse.modisco.infra.discovery.launch.LaunchConfiguration;
import org.eclipse.modisco.infra.discovery.ui.internal.util.LaunchModelUtils;
import org.somox.configuration.SoMoXConfiguration;

import eu.cloudscaleproject.env.extractor.util.ExtractorRunJob;
import eu.cloudscaleproject.env.extractor.util.SomoxConfigurationUtil;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;

public class ConfingAlternative extends AbstractConfigAlternative
{
	public static final String KEY_INPUT_ALTERNATIVE = "input_alternative";

	public static final String KEY_INPUT_PROJECT = "extracted_project";

	public static final String KEY_MODISCO_CONFIG = "modisco";

	private SoMoXConfiguration somoxConfiguration;
	private LaunchConfiguration modiscoConfiguration;

	public ConfingAlternative(IProject project, IFolder folder)
	{
		// TODO Auto-generated constructor stub
		super(project, folder, ToolchainUtils.EXTRACTOR_CONF_ID,
				ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.EXTRACTOR_INPUT_ID),
				ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.EXTRACTOR_RES_ID)
				);
		
		_temp_init();
	}

	private void _temp_init()
	{
		// Should be persisted
		this.somoxConfiguration = SomoxConfigurationUtil.createDefaultSomoxConfiguration();

		this.modiscoConfiguration = LaunchModelUtils.createLaunchConfigurationModel();
		this.modiscoConfiguration.setOpenModelAfterDiscovery(false);

		String ID_DISCOVERER = "org.eclipse.modisco.java.composition.discoverer.fromProject";
		DiscovererDescription discoverer = IDiscoveryManager.INSTANCE.getDiscovererDescription(ID_DISCOVERER);
		this.modiscoConfiguration.setDiscoverer(discoverer);

		LaunchModelUtils.setDiscoveryParameterValue(this.modiscoConfiguration, 
				discoverer.getParameterDefinition("SERIALIZE_TARGET"),
				Boolean.TRUE);
		LaunchModelUtils.setDiscoveryParameterValue(this.modiscoConfiguration, 
				discoverer.getParameterDefinition("DEEP_ANALYSIS"),
				Boolean.TRUE);
	}

	public SoMoXConfiguration getSomoxConfiguration()
	{
		return somoxConfiguration;
	}

	public LaunchConfiguration getModiscoConfiguration()
	{
		return modiscoConfiguration;
	}

	public IProject getExtractedProject()
	{
		String projectName = getProperty(KEY_INPUT_PROJECT);

		if (projectName != null)
		{
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			return root.getProject(projectName);
		}

		return null;
	}

	public void setExtractedProject(IProject project)
	{
		setProperty(KEY_INPUT_PROJECT, project.getName());
	}

	@Override
	public synchronized void load()
	{
		// TODO Auto-generated method stub
		super.load();
	}

	@Override
	protected IStatus doRun(IProgressMonitor monitor)
	{
		ExtractorRunJob job = new ExtractorRunJob(this);
		return job.run(monitor);
	}
}
