package eu.cloudscaleproject.env.extractor;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.somox.configuration.SoMoXConfiguration;

import eu.cloudscaleproject.env.extractor.wizard.util.SomoxConfigurationUtil;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ConfigPersistenceFolder extends EditorInputFolder {

	private static final long serialVersionUID = 1L;

	private static final String PLUGIN_FOLDER = "resources/configurations/";

	public static final String KEY_INPUT_ALTERNATIVE= "input_alternative";

	public static final String KEY_INPUT_PROJECT= "extracted_project";

	public static final String KEY_MODISCO_CONFIG= "modisco";
	private static final String FILE_MODISCO_CONFIG= "modisco.launch";

	private SoMoXConfiguration somoxConfiguration;
	
	public ConfigPersistenceFolder(IProject project, IFolder folder) {
		// TODO Auto-generated constructor stub
		super (project, folder);

		this.somoxConfiguration = SomoxConfigurationUtil.createDefaultSomoxConfiguration();
	}
	
	public SoMoXConfiguration getSomoxConfiguration()
	{
		return somoxConfiguration;
	}
	
	public IProject getExtractedProject ()
	{
		String projectName = getProperty(KEY_INPUT_PROJECT);

		if (projectName != null)
		{
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			return root.getProject(projectName);
		}

        return null;
	}
	
	public void setExtractedProject (IProject project)
	{
		setProperty(KEY_INPUT_PROJECT, project.getName());
	}
	
	public IFile getModiscoConfigFile ()
	{
		return (IFile)getSubResource(KEY_MODISCO_CONFIG);
	}

	@Override
	public synchronized void load()
	{
		// TODO Auto-generated method stub
		super.load();
	}
	
	@Override
	public void create() {
		super.create();

		try {
			IFile modiscoConfig = getResource().getFile(FILE_MODISCO_CONFIG);
			if (!modiscoConfig.exists()) {

				InputStream in = getClass().getClassLoader().getResourceAsStream(PLUGIN_FOLDER + FILE_MODISCO_CONFIG);
				modiscoConfig.create(in, false, null);
				in.close();
			}
			
			setSubResource(KEY_MODISCO_CONFIG, modiscoConfig);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		save();
	}

}
