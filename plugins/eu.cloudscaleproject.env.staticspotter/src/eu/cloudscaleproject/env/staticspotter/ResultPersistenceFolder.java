package eu.cloudscaleproject.env.staticspotter;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ResultPersistenceFolder extends EditorInputFolder {
	
	private static final long serialVersionUID = 1L;

	public static final String KEY_IS_ALTERNATIVE = "is_alternative";
	public static final String KEY_TIMESTAMP = "timestamp";

	public static final String KEY_PSA = "key_output";
	public static final String RESULT_PSA_FILE = "result.psa";

	public ResultPersistenceFolder(IProject project, IFolder folder) {
		// TODO Auto-generated constructor stub
		super (project, folder);
	}
	
	@Override
	public void create() {
		super.create();
	}
	
	
}
