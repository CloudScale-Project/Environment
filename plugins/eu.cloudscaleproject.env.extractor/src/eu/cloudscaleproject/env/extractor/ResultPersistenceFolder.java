package eu.cloudscaleproject.env.extractor;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ResultPersistenceFolder extends EditorInputFolder {
	
	private static final long serialVersionUID = 1L;

	public static final String KEY_IS_ALTERNATIVE = "is_alternative";
	public static final String KEY_TIMESTAMP = "timestamp";

	public static final String KEY_SOMOX_FOLDER = "somox";
	public static final String KEY_MODISCO_FOLDER = "modisco";
	public static final String KEY_SYSTEM_MODEL = "system_model";
	public static final String KEY_SYSTEM_DIAGRAM = "system_diagram";
	public static final String KEY_REPOSITORY_MODEL = "repository_model";
	public static final String KEY_REPOSITORY_DIAGRAM = "repositroy_diagram";
	public static final String KEY_SOURCEDECORATOR_MODEL = "sourcedecorator_model";


	public static final String RESULT_SOMOX_FOLDER = "somox/";
	public static final String RESULT_MODISCO_FOLDER = "./";
	public static final String RESULT_MODEL_NAME = "internal_architecture_model";
	public static final String RESULT_SYSTEM = RESULT_MODEL_NAME + ".system";
	public static final String RESULT_REPOSITORY = RESULT_MODEL_NAME + ".repository";
	public static final String RESULT_SOURCEDECORATOR = RESULT_MODEL_NAME + ".sourcedecorator";
	public static final String RESULT_SYSTEM_DIAGRAM = RESULT_MODEL_NAME + ".system_diagram";
	public static final String RESULT_REPOSITORY_DIAGRAM = RESULT_MODEL_NAME + ".repository_diagram";
	
	public ResultPersistenceFolder(IProject project, IFolder folder) {
		// TODO Auto-generated constructor stub
		super (project, folder);
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
		
		IFolder modiscoFolder = getResource().getFolder(RESULT_MODISCO_FOLDER);
		IFolder somoxFolder = getResource().getFolder(RESULT_SOMOX_FOLDER);
		
		try {
			//modiscoFolder.create(true, true, null);
			somoxFolder.create(true, true, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setResource(KEY_MODISCO_FOLDER, modiscoFolder);
		setResource(KEY_SOMOX_FOLDER, somoxFolder);
		
		save();
	}
}
