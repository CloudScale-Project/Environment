package eu.cloudscaleproject.env.staticspotter;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ConfigPersistenceFolder extends EditorInputFolder {

	private static final long serialVersionUID = 1L;

	public static final String KEY_INPUT_ALTERNATIVE= "input_alternative";


	
	public ConfigPersistenceFolder(IProject project, IFolder folder) {
		// TODO Auto-generated constructor stub
		super (project, folder);
	}

	
	@Override
	public void create() {
		super.create();
		
		save();
	}

}
