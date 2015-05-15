package eu.cloudscaleproject.env.staticspotter.alternatives;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ResultAlternative extends EditorInputFolder {
	
	public static final String KEY_PSA = "key_output";
	public static final String RESULT_PSA_FILE = "result.psa";

	public ResultAlternative(IProject project, IFolder folder) {
		// TODO Auto-generated constructor stub
		super (project, folder);
	}
	
	@Override
	public void create() {
		super.create();
	}
	
	
}
