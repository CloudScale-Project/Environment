package eu.cloudscaleproject.env.extractor;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class ResultPersistenceFolder extends EditorInputEMF {
	
	private static final long serialVersionUID = 1L;

	public static final String KEY_FOLDER_SOMOX = "somox";
	public static final String KEY_FOLDER_MODISCO = "modisco";

	public static final String KEY_FILE_SYSTEM_DIAGRAM = "system_diagram";
	public static final String KEY_FILE_REPOSITORY_DIAGRAM = "repositroy_diagram";


	public static final String RESULT_FOLDER_SOMOX = "somox/";
	public static final String RESULT_FOLDER_MODISCO = "modisco/";

	public static final String RESULT_MODEL_NAME = "internal_architecture_model";

	public static final String RESULT_FILE_SYSTEM = RESULT_MODEL_NAME + ".system";
	public static final String RESULT_FILE_REPOSITORY = RESULT_MODEL_NAME + ".repository";
	public static final String RESULT_FILE_SOURCEDECORATOR = RESULT_MODEL_NAME + ".sourcecodedecorator";
	public static final String RESULT_FILE_SYSTEM_DIAGRAM = RESULT_MODEL_NAME + ".system_diagram";
	public static final String RESULT_FILE_REPOSITORY_DIAGRAM = RESULT_MODEL_NAME + ".repository_diagram";
	

	public ResultPersistenceFolder(IProject project, IFolder folder, AdapterFactory factory){
		super(project, folder, factory);
	}
	
	@Override
	public void create() {
		super.create();
		
		//
		// Prepare folder structure
		//

		IFolder modiscoFolder = getResource().getFolder(RESULT_FOLDER_MODISCO);
		IFolder somoxFolder = getResource().getFolder(RESULT_FOLDER_SOMOX);
		
		try {
			modiscoFolder.create(true, true, null);
			somoxFolder.create(true, true, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSubResource(KEY_FOLDER_MODISCO, modiscoFolder);
		setSubResource(KEY_FOLDER_SOMOX, somoxFolder);
		
		save();
	}

	@Override
	protected void doLoad() {
		
		super.doLoad();
		
		try {
			loadModels();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final void loadModels() throws IOException {
		
		IResource repository = getSubResource(ToolchainUtils.KEY_FILE_REPOSITORY);
		IResource system = getSubResource(ToolchainUtils.KEY_FILE_REPOSITORY);
		
		if (repository != null)
		{
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)repository);
			res.unload();
			res.load(null);
		}

		if (system != null)
		{
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)system);
			res.unload();
			res.load(null);
		}
	}
}
