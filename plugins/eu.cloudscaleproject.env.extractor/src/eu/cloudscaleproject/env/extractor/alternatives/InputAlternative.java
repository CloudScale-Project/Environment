package eu.cloudscaleproject.env.extractor.alternatives;

import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.modisco.infra.discovery.catalog.DiscovererDescription;
import org.eclipse.modisco.infra.discovery.core.IDiscoveryManager;
import org.eclipse.modisco.infra.discovery.launch.LaunchConfiguration;
import org.eclipse.modisco.infra.discovery.ui.internal.util.LaunchModelUtils;

import eu.cloudscaleproject.env.extractor.util.ProjectListener;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractInputAlternative;

public class InputAlternative extends AbstractInputAlternative {
	private LaunchConfiguration modiscoConfiguration;

	public static final String KEY_INPUT_PROJECT = "extracted_project";

	public static final String KEY_MODISCO_CONFIG = "modisco";

	public InputAlternative(IProject project, IFolder folder) {
		super(project, folder, new ModelType[] {}, CSTool.EXTRACTOR_INPUT.getID(), CSTool.EXTRACTOR_CONF.getID());

		initModiscoModel();
		initProjectListener();
	}

	public String getExtractedProjectName() {
		return getProperty(KEY_INPUT_PROJECT);
	}

	public IProject getExtractedProject() {
		String projectName = getExtractedProjectName();
		if (projectName != null) {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			return root.getProject(projectName);
		}

		return null;
	}

	public void setExtractedProjectName(String projectName) {
		setProperty(KEY_INPUT_PROJECT, projectName);
	}

	private void initModiscoModel() {
		String ID_DISCOVERER = "org.eclipse.modisco.java.composition.discoverer.fromProject";
		this.modiscoConfiguration = LaunchModelUtils.createLaunchConfigurationModel();
		this.modiscoConfiguration.setOpenModelAfterDiscovery(false);

		DiscovererDescription discoverer = IDiscoveryManager.INSTANCE.getDiscovererDescription(ID_DISCOVERER);
		this.modiscoConfiguration.setDiscoverer(discoverer);

		LaunchModelUtils.setDiscoveryParameterValue(this.modiscoConfiguration,
				discoverer.getParameterDefinition("SERIALIZE_TARGET"), Boolean.TRUE);
		LaunchModelUtils.setDiscoveryParameterValue(this.modiscoConfiguration,
				discoverer.getParameterDefinition("DEEP_ANALYSIS"), Boolean.TRUE);

		/*
		 * IFile modisco = getResource().getFile("modisco.conf"); Resource
		 * emfResource = ExplorerProjectPaths.getEmfResource(getResourceSet(),
		 * modisco); emfResource.getContents().add(modiscoConfiguration);
		 * emfResource.getContents().add(discoverer);
		 * 
		 * try { emfResource.save(Collections.EMPTY_MAP); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * setSubResource(KEY_MODISCO_CONFIG, modisco);
		 */
	}

	public LaunchConfiguration getModiscoConfiguration() {
		return modiscoConfiguration;
	}

	private void initProjectListener() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.addResourceChangeListener(new ProjectListener() {
			@Override
			public void projectsUpdated(Set<IProject> projects) {
				InputAlternative.this.validate();
			}
		});
	}

}
