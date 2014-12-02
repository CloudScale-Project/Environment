package eu.cloudscaleproject.env.common.explorer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.ResourceUtil;

public class ExplorerProjectPaths {

	private static final Logger logger = Logger
			.getLogger(ExplorerProjectPaths.class.getName());

	// TODO: use those paths when creating files
	public static final String FILE_PROJECT_PROPERTIES = "project.cse";
	//

	// diagram and model used for notifications and project progress status
	public static final String FILE_METHOD = "project.method";
	public static final String FILE_METHOD_NEW = "project.method_new";
	public static final String FILE_METHOD_DIAGRAM = "project.method_diagram.";

	public static final String KEY_FOLDER_GENERATED = "generated-folder";
	public static final String KEY_FOLDER_SCALEDL = "scaledl-folder";
	public static final String KEY_FOLDER_IMPORT = "imported-folder";

	public static final String KEY_FOLDER_ANALYSER = "analyser-folder";
	public static final String KEY_FOLDER_EXTRACTOR = "extractor-folder";
	public static final String KEY_FOLDER_DYNAMIC_SPOTTER = "dynamicspotter-folder";
	public static final String KEY_FOLDER_STATIC_SPOTTER = "staticspotter-folder";

	public static final String KEY_FOLDER_INPUT = "input-folder";
	public static final String KEY_FOLDER_CONFIGURATION = "configuration-folder";
	public static final String KEY_FOLDER_RESULTS = "results-folder";

	public static final String KEY_FILE_OVERVIEW_MODEL = "scaledl-overview-model";
	public static final String KEY_FILE_OVERVIEW_DIAGRAM = "scaledl-overview-diagram";

	/**
	 * Retrieve all CloudScale projects from the Workspace
	 * 
	 * @return CloudScale IProject's
	 */
	public static IProject[] getProjects(){
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

		List<IProject> filtered = new ArrayList<IProject>();
		for(IProject p : projects){
			if(getPropertyFile(p).exists()){
				filtered.add(p);
			}
		}
		
		return filtered.toArray(new IProject[filtered.size()]);
	}
	
	/**
	 * 
	 * Check if specified project has property file. This method can be used
	 * during project initialization.
	 * 
	 * @param project
	 *            Project in question
	 * @return boolean
	 */
	public static boolean hasPropertyFile(IProject project) {
		IFile file = project.getFile(FILE_PROJECT_PROPERTIES);
		return file.exists();
	}

	/**
	 * 
	 * Retrieve project property file. Property file contains key-value pairs
	 * for storing various configurations,
	 * 
	 * @param project
	 * @return IFile resource
	 */
	public static IFile getPropertyFile(IProject project) {
		return project.getFile(FILE_PROJECT_PROPERTIES);
	}

	public static synchronized Properties getProjectProperties(IProject project) {
		IFile file = project.getFile(FILE_PROJECT_PROPERTIES);

		if (!file.exists()) {
			logger.severe("In project " + project.toString() + ": File " + FILE_PROJECT_PROPERTIES + " doesn't exist!");
			return null;
		}

		try {
			Properties p = new Properties();
			InputStream is = file.getContents(true);
			p.load(is);
			is.close();

			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * Retrieves property from project properties file. </br> If the requested
	 * property does not exist, specified default value is used </br> and saved
	 * into properties file. </br>
	 * 
	 * @param project
	 *            Project in explorer tree view.
	 * @param key
	 *            Key that specifies requested property.
	 * @param defaultValue
	 *            Default value to return (and save in properties file), if it
	 *            do not exist jet.
	 * @return Value of the specified key.
	 */
	public static synchronized String getProjectProperty(IProject project,
			String key, String defaultValue) {

		Properties p = getProjectProperties(project);
		String value = p.getProperty(key);

		if (value == null) {
			if (defaultValue != null) {
				setProjectProperty(project, key, defaultValue);
				value = defaultValue;

			} else {
				logger.warning("Project properties file '"
						+ FILE_PROJECT_PROPERTIES + "': unknown propertie: '"
						+ key + "'");
			}
		}

		return value;
	}

	public static synchronized void deleteProjectProperty(IProject project,
			String key) {
		setProjectProperty(project, key, null);
	}

	public static synchronized void setProjectProperty(IProject project,
			String key, String value) {
		IFile file = project.getFile(FILE_PROJECT_PROPERTIES);

		if (!file.exists()) {
			logger.severe("File " + FILE_PROJECT_PROPERTIES + " doesn't exist!");
			// create new properties file if it don't exist.
			return;
		}

		try {
			Properties p = getProjectProperties(project);

			p.remove(key);
			if (value != null)
				p.setProperty(key, value);


			// TODO: find out how to fix this nonsense!
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			p.store(bos, "");
			ByteArrayInputStream bais = new ByteArrayInputStream(
					bos.toByteArray());

			file.delete(true, null);
			file.create(bais, true, null);
			bais.close();
			//

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

	/**
	 * 
	 * Retrieves property from project properties file. </br>
	 * 
	 * @param project
	 *            Project in explorer tree view.
	 * @param key
	 *            Key that specifies requested property.
	 * @return Value of the specified key.
	 */
	public static String getProjectProperty(IProject project, String key) {
		return getProjectProperty(project, key, null);
	}

	/**
	 * Returns folder, which path is specified in project configuration file. If
	 * folder doesn't exist jet, empty folder is created.
	 * 
	 * @param project
	 *            Project that should contain desired folder
	 * @param folderKey
	 *            Key that identifies folder path in project configuration file
	 * @return Generated folder
	 */
	public static IFolder getProjectFolder(IProject project, String folderKey) {

		String folderpath = getProjectProperty(project, folderKey);

		if (folderpath == null) {
			logger.severe("Can't create or retieve folder. Specified folder key value does not exist!");
			return null;
		}

		IFolder folder = project.getFolder(folderpath);

		if (!folder.exists()) {
			try {
				folder.create(true, true, new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return folder;
	}
	
	/**
	 * Returns <code>IFolder</code>, located inside specified 'folder'.
	 * Name of the folder is retrieved from the project properties, by the specified 'folderKey'.
	 * If the folder doesn't exist jet, empty folder is created.
	 * 
	 * @param folder
	 *            Folder from where to retrieve/create child folder.
	 * @param folderKey
	 *            Key that identifies folder path in project configuration file
	 * @return Generated folder
	 */
	public static IFolder getProjectFolder(IFolder folder, String folderKey){
		String folederName = getProjectProperty(folder.getProject(), folderKey);
		
		if(!folder.exists()){
			throw new IllegalArgumentException("getProjectFolder(): Specified folder does not exist!");
		}
		
		IFolder f = folder.getFolder(folederName);
		
		if (!f.exists()) {
			try {
				f.create(true, true, new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return f;
	}
	
	/**
	 * Retrieves <code>IFolder</code> located inside tool folder. Both folders must be specified as keys defined in this class.
	 * Returned folder always has the following hierarchy "projectFolder/toolFolder/folder".
	 * 
	 * @param project Project to retrieve folder from.
	 * @param toolFolderKey Key that represents a tool folder, located inside the project.
	 * @param folderKey Key that represents a folder, located inside the tool folder.
	 * @return IFolder located in specified project, under the specified tool folder.
	 */
	public static IFolder getProjectToolFolder(IProject project, String toolFolderKey, String folderKey){
		IFolder toolFolder = ExplorerProjectPaths.getProjectFolder(project, toolFolderKey);
		IFolder folder = toolFolder.getFolder(ExplorerProjectPaths.getProjectProperty(project, folderKey)); 
		return folder;
	}

	/**
	 * 
	 * Retrieves <code>IFile</code> from project. File is retrieved by using
	 * file path, specified under 'fileKey' tag in project configuration file.<br>
	 * 
	 * @param project
	 *            Project that should contain file.
	 * @param fileKey
	 *            String that identified file path in project properties file.
	 * @return IFile or null, if the file can not be retrieved
	 */
	public static IFile getProjectFile(IProject project, String fileKey) {
		String filepath = getProjectProperty(project, fileKey);

		if (filepath == null) {
			logger.severe("Can't retieve file. File path is not specified in project properties file!");
			return null;
		}

		IFile file = project.getFile(filepath);

		if (!file.exists()) {
			return null;
		}

		return file;
	}
	
	/**
	 * Retrieves EMF resource from IFile, by using specified EMF resource set.
	 * If the resource does not exist jet, it is created.
	 * If the specified <code>IFile</code> exist, resource is loaded before it is returned.
	 * Returned resource is contained inside resource set. 
	 * 
	 * @param resSet ResourceSet used for creating EMF Resource.
	 * @param file IFile used to retrieve URI of the desired Resource.
	 * @return EMF Resource contained inside specified ResourceSet.
	 */
	public static Resource getEmfResource(ResourceSet resSet, IFile file){
		if(file == null){
			throw new NullPointerException("getEmfResource(): Specified file is null!");
		}
		URI uri = URI.createPlatformResourceURI(file.getFullPath()
				.toString(), true);
		
		Resource res = resSet.getResource(uri, false);
		
		if(res == null){
			res = resSet.createResource(uri);
		}
		
		if(file.exists()){
			try {
				res.load(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	
	/**
	 * Returns true if the specified 'resSet' contains resource, where resource URI equals 'file' URI.
	 * If the specified IFile is null, this method returns false.
	 * 
	 * @param resSet ResourceSet used for finding the Resource.
	 * @param file IFile used for finding the Resource in the specified ResourceSet.
	 * @return true if the resSet contains specified resource.
	 */
	public static boolean hasEmfResource(ResourceSet resSet, IFile file){
		if(file == null){
			return false;
		}
		URI uri = URI.createPlatformResourceURI(file.getFullPath()
				.toString(), true);
		
		Resource res = resSet.getResource(uri, false);
		if(res == null){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * Retrieves <code>Resource</code> from project. Resource is retrieved by
	 * using file path, specified under 'fileKey' tag in project configuration
	 * file.<br>
	 * 
	 * @param project
	 *            Project that should contain file.
	 * @param fileKey
	 *            String that identified file path in project properties file.
	 * @return Resource or null, if the resource can not be retrieved
	 */
	public static Resource getProjectEmfResource(IProject project,
			String fileKey) {
		Resource res = null;

		try {
			IFile file = getProjectFile(project, fileKey);
			if (file == null) {
				logger.warning("Can't retrieve EMF resource!");
				return null;
			}

			URI uri = URI.createPlatformResourceURI(file.getFullPath()
					.toString(), true);
			ResourceSet resSet = new ResourceSetImpl();
			res = resSet.getResource(uri, true);
		} catch (Exception e) {
			// ignore
		}
		return res;

	}

	public static IProject getProject(IEditorPart editor) {
		if (editor == null)
			return null;
		return ResourceUtil.getFile(editor.getEditorInput()).getProject();
	}

	public static IProject getProjectFromElement(Object element) {
		return ResourceUtil.getFile(element).getProject();
	}

	/**
	 * 
	 * @return IProject containing selected element (first if multiple) in
	 *         explorer
	 */
	public static IProject getProjectFromActiveEditor() {
		IEditorPart editorPart = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (editorPart == null) {
			return null;
		}

		return getProject(editorPart);
	}

	public static IProject getProjectFromExplorerSelection() {
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (window != null) {
			IStructuredSelection selection = (IStructuredSelection) window
					.getSelectionService().getSelection();
			if (selection == null || selection.getFirstElement() == null
					|| !(selection.getFirstElement() instanceof IResource))
				return null;

			Object firstElement = selection.getFirstElement();
			IProject project = ((IResource) firstElement).getProject();
			return project;
		}

		return null;
	}

	public static IFile getFileFromEmfResource(
			org.eclipse.emf.ecore.resource.Resource resource) {

		URI uri = resource.getURI();
		String platformString = uri.toPlatformString(true);
		if (platformString != null) {
			Path path = new Path(platformString);
			return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		}

		return null;
	}

	public static IFile getFileFromEmfURI(URI uri) {

		String platformString = uri.toPlatformString(true);
		if (platformString != null) {
			Path path = new Path(platformString);
			return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		}

		return null;
	}

	public static IProject getProjectFromEmfResource(
			org.eclipse.emf.ecore.resource.Resource resource) {
		IFile file = getFileFromEmfResource(resource);
		return file.getProject();
	}

}
