package eu.cloudscaleproject.env.staticspotter;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ConfigPersistenceFolder extends EditorInputFolder {

	private static final long serialVersionUID = 1L;

	private static final String PLUGIN_FOLDER = "resources/configurations/";

	public static final String KEY_INPUT_ALTERNATIVE= "input_alternative";

	public static final String KEY_SOMOX_CONFIG= "somox";
	public static final String KEY_MODISCO_CONFIG= "modisco";

	private static final String FILE_SOMOX_CONFIG= "somox.launch";
	private static final String FILE_MODISCO_CONFIG= "modisco.launch";
	
	public ConfigPersistenceFolder(IProject project, IFolder folder) {
		// TODO Auto-generated constructor stub
		super (project, folder);
	}
	
	public IFile getModiscoConfigFile ()
	{
		return getFileResource(KEY_MODISCO_CONFIG);
	}
	
	public IFile getSomoxConfigFile()
	{
		return getFileResource(KEY_SOMOX_CONFIG);
	}
	
	@Override
	public void create() {
		super.create();

		try {
			IFile somoxConfig = getResource().getFile(FILE_SOMOX_CONFIG);
			if (!somoxConfig.exists()) {
				
				InputStream in = getClass().getClassLoader().getResourceAsStream(PLUGIN_FOLDER + FILE_SOMOX_CONFIG);
				somoxConfig.create(in, false, null);
				in.close();
			}
			

			IFile modiscoConfig = getResource().getFile(FILE_MODISCO_CONFIG);
			if (!modiscoConfig.exists()) {

				InputStream in = getClass().getClassLoader().getResourceAsStream(PLUGIN_FOLDER + FILE_MODISCO_CONFIG);
				modiscoConfig.create(in, false, null);
				in.close();
			}
			
			
			setResource(KEY_SOMOX_CONFIG, somoxConfig);
			setResource(KEY_MODISCO_CONFIG, modiscoConfig);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		save();
	}

}
