package eu.cloudscaleproject.env.staticspotter;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ConfigPersistenceFolder extends EditorInputFolder {

	private static final long serialVersionUID = 1L;

	public static final String KEY_INPUT_ALTERNATIVE= "input_alternative";
	
	public static final String PLUGIN_FILE_DEFAULT_CATALOG = "resources/catalog/default.psc";
	public static final String PLUGIN_FILE_DEFAULT_ENGINES = "resources/catalog/default.psc.ecore";

	public static final String FILE_CATALOG = "catalog.pcs";
	public static final String FILE_ENGINES = "catalog.pcs.ecore";

	public static final String KEY_CATALOG = "catalog";
	public static final String KEY_ENGINES = "engines_ecore";


	
	public ConfigPersistenceFolder(IProject project, IFolder folder) {
		// TODO Auto-generated constructor stub
		super (project, folder);
	}

	
	@Override
	public void create() {
		super.create();

		try {
			IFile catalog = getResource().getFile(FILE_CATALOG);
			if (!catalog.exists()) {
				
				InputStream in = getClass().getClassLoader().getResourceAsStream(PLUGIN_FILE_DEFAULT_CATALOG);
				catalog.create(in, false, null);
				in.close();
			}

			IFile engines = getResource().getFile(FILE_ENGINES);
			if (!engines.exists()) {
				
				InputStream in = getClass().getClassLoader().getResourceAsStream(PLUGIN_FILE_DEFAULT_ENGINES);
				engines.create(in, false, null);
				in.close();
			}
			
			setSubResource(KEY_CATALOG, catalog);
			setSubResource(KEY_ENGINES, engines);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		save();
	}

}
