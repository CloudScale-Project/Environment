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

	public static final String KEY_FOLDER_SOMOX = "folder_somox";
	public static final String KEY_FOLDER_MODISCO = "folder_modisco";

	public static final String KEY_FILE_MODISCO_JAVA2KDM = "file_modisco_java2kdm";
	public static final String KEY_FILE_MODISCO_JAVA = "file_modisco_java";
	public static final String KEY_FILE_MODISCO_KDM = "file_modisco_kdm";
	public static final String KEY_FILE_SYSTEM_DIAGRAM = "file_system_diagram";
	public static final String KEY_FILE_REPOSITORY_DIAGRAM = "file_repositroy_diagram";

	private static final String RESULT_FOLDER_SOMOX = "somox/";
	private static final String RESULT_FOLDER_MODISCO = "modisco/";

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
		IResource system = getSubResource(ToolchainUtils.KEY_FILE_SYSTEM);
		IResource sourceDecorator = getSubResource(ToolchainUtils.KEY_FILE_SOURCEDECORATOR);
		IResource java2kdm = getSubResource(ResultPersistenceFolder.KEY_FILE_MODISCO_JAVA2KDM);
		
		for (IResource res : new IResource[]{repository, system, sourceDecorator, java2kdm})
		{
			if (res != null)
			{
				Resource emfRes = ExplorerProjectPaths.getEmfResource(resSet, (IFile)res);
				emfRes.unload();
				emfRes.load(null);
			}
			
		}
	}
}
