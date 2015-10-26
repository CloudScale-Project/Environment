package eu.cloudscaleproject.env.extractor.alternatives;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.modisco.infra.discovery.catalog.DiscovererDescription;
import org.eclipse.modisco.infra.discovery.core.IDiscoveryManager;
import org.eclipse.modisco.infra.discovery.launch.LaunchConfiguration;
import org.eclipse.modisco.infra.discovery.ui.internal.util.LaunchModelUtils;

import eu.cloudscaleproject.env.extractor.util.ProjectListener;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractInputAlternative;

public class InputAlternative extends AbstractInputAlternative
{
	private LaunchConfiguration modiscoConfiguration;

	public static final String KEY_INPUT_PROJECT = "extracted_project";

	public static final String KEY_MODISCO_FILE = "modisco.conf";

	public InputAlternative(IProject project, IFolder folder)
	{
		super(project, folder, new ModelType[] {}, CSTool.EXTRACTOR);
	}

	public String getExtractedProjectName()
	{
		return getProperty(KEY_INPUT_PROJECT);
	}

	public IProject getExtractedProject()
	{
		String projectName = getExtractedProjectName();
		if (projectName != null) {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			return root.getProject(projectName);
		}

		return null;
	}

	public void setExtractedProjectName(String projectName)
	{
		setProperty(KEY_INPUT_PROJECT, projectName);
	}
	
	@Override
	protected void doCreate(IProgressMonitor monitor)
	{
		super.doCreate(monitor);
		
		initModiscoModel();
	}

	@Override
	protected void doLoad(IProgressMonitor monitor)
	{
		super.doLoad(monitor);

		loadModiscoConfiguration();
		
		initModiscoListener();
		initProjectListener();
	}

	@Override
	protected void doSave(IProgressMonitor monitor)
	{
		super.doSave(monitor);
		saveModiscoConfiguration();
	}

	public LaunchConfiguration getModiscoConfiguration()
	{
		return modiscoConfiguration;
	}

	/** Serialize the given launch model in the given launch configuration. */
	private void saveModiscoConfiguration()
	{
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			this.modiscoConfiguration.eResource().save(outStream, null);

			ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());

			IFile modisco = getResource().getFile(KEY_MODISCO_FILE);
			if (modisco.exists())
				modisco.delete(false, null);
			modisco.create(inStream, true, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initModiscoModel()
	{
		String ID_DISCOVERER = "org.eclipse.modisco.java.composition.discoverer.fromProject";
		this.modiscoConfiguration = LaunchModelUtils.createLaunchConfigurationModel();
		this.modiscoConfiguration.setOpenModelAfterDiscovery(false);

		DiscovererDescription discoverer = IDiscoveryManager.INSTANCE.getDiscovererDescription(ID_DISCOVERER);
		this.modiscoConfiguration.setDiscoverer(discoverer);

		LaunchModelUtils.setDiscoveryParameterValue(this.modiscoConfiguration,
				discoverer.getParameterDefinition("SERIALIZE_TARGET"), Boolean.TRUE);
		LaunchModelUtils.setDiscoveryParameterValue(this.modiscoConfiguration,
				discoverer.getParameterDefinition("DEEP_ANALYSIS"), Boolean.TRUE);

		saveModiscoConfiguration();
	}


	private void loadModiscoConfiguration()
	{
		try {
			IFile file = getResource().getFile(KEY_MODISCO_FILE);
			if (file.exists())
			{
				InputStream contents = file.getContents();

				Resource launchResource = LaunchModelUtils.createLaunchResource();
				launchResource.load(contents, null);
				this.modiscoConfiguration = (LaunchConfiguration) launchResource.getContents().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (this.modiscoConfiguration == null) {
			initModiscoModel();
		}
	}

	private void initProjectListener()
	{
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.addResourceChangeListener(new ProjectListener()
		{
			@Override
			public void projectsUpdated(Set<IProject> projects)
			{
				InputAlternative.this.validate();
			}
		});
	}

	private void initModiscoListener()
	{
		this.modiscoConfiguration.eAdapters().add(new EContentAdapter(){
			@Override
			public void notifyChanged(Notification msg)
			{
				if (msg.getEventType() == Notification.RESOLVE) return;
				setDirty(true);
			}
		});
	}
}
