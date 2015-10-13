package eu.cloudscaleproject.env.common.explorer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.ResourceUtil;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.BatchExecutor;
import eu.cloudscaleproject.env.common.CloudScaleConstants;

public class ExplorerProjectPaths {

	private static final Logger logger = Logger.getLogger(ExplorerProjectPaths.class.getName());

	public static final String FILE_PROJECT_DASHBOARD = "project.cse";
	
	public static final String FOLDER_GENERATED_KEY = "eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths.generatedFolder";
	public static final String FOLDER_GENERATED_DEFAULT = "Generated models";

	/**
	 * Retrieve all CloudScale projects from the Workspace
	 * 
	 * @return CloudScale IProject's
	 */
	public static IProject[] getProjects(){
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

		List<IProject> filtered = new ArrayList<IProject>();
		for(IProject p : projects){
			try {
				IProjectNature pn = p.getNature(CloudScaleConstants.PROJECT_NATURE_ID);
				if(pn != null){
					filtered.add(p);
				}
			} catch (CoreException e) {
				e.printStackTrace();
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
	public static boolean hasDashboardFile(IProject project) {
		IFile file = project.getFile(FILE_PROJECT_DASHBOARD);
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
	public static IFile getDashboardFile(IProject project) {
		return project.getFile(FILE_PROJECT_DASHBOARD);
	}

	public static synchronized Preferences getProjectProperties(IProject project) {
		
		ProjectScope scope = new ProjectScope(project);
		return scope.getNode("eu.cloudscaleproject.env.common").node("explorer");
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
	public static synchronized String getProjectProperty(IProject project, String key, String defaultValue) {
		
		final Preferences preferences = getProjectProperties(project);
		String value = preferences.get(key, null);
		if(value == null && defaultValue != null){
			preferences.put(key, defaultValue);
			
			BatchExecutor.getInstance().addTask(preferences, "flush", new Runnable() {
				@Override
				public void run() {
					try {
						preferences.flush();
					} 
					catch (BackingStoreException e) {
						e.printStackTrace();
					}
				}
			});
			
			value = defaultValue;
		}
		return value;
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
	 * 
	 * Removes specified property key from the project properties file.
	 * 
	 * @param project 
	 * 			Project in explorer tree view.
	 * @param key 
	 * 			Key to remove from the properties.
	 */
	public static synchronized void deleteProjectProperty(IProject project, String key) {
		
		final Preferences preferences = getProjectProperties(project);
		preferences.remove(key);
		
		BatchExecutor.getInstance().addTask(preferences, "flush", new Runnable() {
			@Override
			public void run() {
				try {
					preferences.flush();
				} 
				catch (BackingStoreException e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * 
	 * Sets project property under specified key.
	 * 
	 * @param project
	 * 			Project in explorer tree view.
	 * @param key
	 * 			Key under which to store specified property.
	 * @param value
	 * 			Value to be stored.
	 */
	public static synchronized void setProjectProperty(IProject project, String key, String value) {
		
		final Preferences preferences = getProjectProperties(project);
		preferences.put(key, value);
		
		BatchExecutor.getInstance().addTask(preferences, "flush", new Runnable() {
			@Override
			public void run() {
				try {
					preferences.flush();
				} 
				catch (BackingStoreException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/**
	 * Create all parent folders including this folder if the resources does not exist jet.
	 * 
	 * @param folder Folder to create, including all parent folders.
	 * @throws CoreException if the folder hierarchy can not be created.
	 */
	public static synchronized void prepareFolder(IFolder folder) throws CoreException {
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder) {
			prepareFolder((IFolder)parent);
		}
		
		// QUICK WORKAROUND: exists() and than create() does not work when importing porject
		((Folder)folder).ensureExists(new NullProgressMonitor());
	}

	/**
	 * Returns folder, which path is specified in project configuration file. If
	 * folder doesn't exist jet, empty folder or folder hierarchy is created.
	 * 
	 * @param project
	 *            Project that should contain desired folder
	 * @param folderKey
	 *            Key that identifies folder path in project configuration file
	 * @return Generated folder
	 */
	public static IFolder getProjectFolder(IProject project, String folderKey) {

		String folderpath = getProjectProperty(project, folderKey);

		if (folderpath == null || folderpath.isEmpty()) {
			logger.severe("Can't create or retieve folder. \n"
					+ "Specified folder key value does not exist in the project configuration file! \n"
					+ "Folder key: " + folderKey);
			return null;
		}
		
		IFolder folder = project.getFolder(folderpath);

		IStatus status = folder.getWorkspace().validateProjectLocation(project, folder.getFullPath());
		if(!status.isOK()){
			logger.severe("Can't create or retieve folder. \n"
					+ "Specified folder key value is invalid in the project configuration file! \n"
					+ "Folder path value: " + folder.getFullPath().toString());
			return null;
		}
		
		try {
			prepareFolder(folder);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}

		return folder;
	}
	
	/**
	 * Returns <code>IFolder</code>, located on the path inside specified 'folder'.
	 * Path of the folder is retrieved from the project properties, by the specified 'folderKey'.
	 * If the folder doesn't exist jet, empty folder or folder hierarchy is created.
	 * 
	 * @param folder
	 *            Folder from where to retrieve/create child folder. 
	 *            This folder must already exist, or the IllegalArgumentException is thrown.
	 * @param folderKey
	 *            Key that identifies folder path in project configuration file
	 * @return Generated folder
	 */
	public static IFolder getProjectFolder(IFolder folder, String folderKey){
		String folderpath = getProjectProperty(folder.getProject(), folderKey);
		
		if(!folder.exists()){
			throw new IllegalArgumentException("Specified parent folder does not exist!");
		}
		
		if (folderpath == null || folderpath.isEmpty()) {
			logger.severe("Can't create or retieve folder. \n"
					+ "Specified folder key value does not exist in the project configuration file! \n"
					+ "Folder key: " + folderKey);
			return null;
		}
		
		IFolder f = folder.getFolder(folderpath);
		
		IStatus status = folder.getWorkspace().validateProjectLocation(folder.getProject(), folder.getFullPath());
		if(!status.isOK()){
			logger.severe("Can't create or retieve folder. \n"
					+ "Specified folder key value is invalid in the project configuration file! \n"
					+ "Folder path value: " + folder.getFullPath().toString());
			return null;
		}
		
		try {
			prepareFolder(f);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
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
	 * Removes file extension.
	 * In other words, it removes last dot followed by one or more characters.
	 * 
	 * @param filename String from which to remove file extension.
	 * @return Filename string without extension.
	 */
	public static String removeFileExtension(String filename){
		return filename.replaceFirst("[.][^.]+$", "");
	}
	
	/**
	 * Search for <code>IFile</code>'s with the specified extension under specified <code>IFolder</code> hierarchy.
	 * 
	 * @param folder Base folder to search in.
	 * @param extension File extension to look for.
	 * @param recursive If true all sub-folders are searched as well.
	 * 
	 * @return List of files with the matching extension
	 */
	public static List<IFile> findFiles(IContainer folder, String name, String extension, boolean recursive){
		
		List<IFile> files = new ArrayList<IFile>();
		
		if(!folder.exists()){
			return files;
		}
		
		try {
			for(IResource r : folder.members()){
				if(r instanceof IContainer){
					if(recursive){
						IContainer f = (IContainer)r;
						files.addAll(findFiles(f, name, extension, recursive));
					}
				}
				if(r instanceof IFile){
					IFile f = (IFile)r;
					if(f.getName().equals(name + "." + extension)){
						files.add(f);
					}
				}
			}
		}
		catch (CoreException e) {
			e.printStackTrace();
		}
		
		return files;
	}
	
	/**
	 * Search for <code>IFile</code>'s with the specified extension under specified <code>IFolder</code> hierarchy.
	 * 
	 * @param folder Base folder to search in.
	 * @param extension File extension to look for.
	 * @param recursive If true all sub-folders are searched as well.
	 * 
	 * @return List of files with the matching extension
	 */
	public static List<IFile> findFilesByExtension(IContainer folder, String extension, boolean recursive){
		
		List<IFile> files = new ArrayList<IFile>();
		
		if(!folder.exists()){
			return files;
		}
		
		try {
			for(IResource r : folder.members()){
				if(r instanceof IContainer){
					if(recursive){
						IContainer f = (IContainer)r;
						files.addAll(findFilesByExtension(f, extension, recursive));
					}
				}
				if(r instanceof IFile){
					IFile f = (IFile)r;
					if(f.getFileExtension().equals(extension)){
						files.add(f);
					}
				}
			}
		}
		catch (CoreException e) {
			e.printStackTrace();
		}
		
		return files;
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
		return getEmfResource(resSet, file, true);
	}
	public static Resource getEmfResource(ResourceSet resSet, IFile file, boolean load){
		if(file == null){
			throw new NullPointerException("getEmfResource(): Specified file is null!");
		}
		URI uri = URI.createPlatformResourceURI(file.getFullPath()
				.toString(), true);
		
		Resource res = null;
		synchronized (resSet) {
			res = resSet.getResource(uri, false);
			if(res == null && load){
				res = resSet.createResource(uri);
			}
		}
		
		if(res != null && file.exists()){
			try {
				res.load(null);
			} catch (IOException e) {
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
		
		Resource res = null;
		synchronized (resSet) {
			res = resSet.getResource(uri, false);
		}
		
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
	public static Resource getProjectEmfResource(IProject project, ResourceSet resSet, String fileKey) {
		Resource res = null;

		try {
			IFile file = getProjectFile(project, fileKey);
			if (file == null) {
				logger.warning("Can't retrieve EMF resource!");
				return null;
			}

			URI uri = URI.createPlatformResourceURI(file.getFullPath()
					.toString(), true);
			
			synchronized (resSet) {
				res = resSet.getResource(uri, true);
			}
			
		} catch (Exception e) {
			// ignore
		}
		return res;

	}
	
	public static IFolder getNonexistingSubFolder(IFolder folder, String name){
		
		IFolder out = folder.getFolder(name);
		
		int counter = 1;
		while(out.exists()){
			out = folder.getFolder(name + " " +counter);
			counter++;
		}
		return out;
	}
	
	public static IFile getNonexistingSubFile(IFolder folder, String name, String extension){
		
		IFile out = folder.getFile(name + "." + extension);
		
		int counter = 1;
		while(out.exists()){
			out = folder.getFile(name + "_" +counter + "." + extension);
			counter++;
		}
		return out;
	}
	
	public static List<IFile> findResources (IContainer folder, String[] extensions)
	{
		List<IFile> files = new ArrayList<IFile>();
		for (String ext : extensions)
		{
			files.addAll(findResources(folder, ext));
		}
		
		return files;
	}

	public static List<IFile> findResources(IContainer folder, String extension){
		
		List<IFile> files = new ArrayList<IFile>();
		
		if(!folder.exists()){
			return files;
		}
		
		try {
			for(IResource r : folder.members()){
				if(r instanceof IContainer){
					IContainer f = (IContainer)r;
					files.addAll(findResources(f, extension));
				}
				if(r instanceof IFile){
					IFile f = (IFile)r;
					if(f.getFileExtension().equals(extension)){
						files.add(f);
					}
				}
			}
		}
		catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return files;
	}
	/*
	public static void copyEMFResources(IContainer folder, Resource[] resources)
	{
		for (Resource res : resources)
		{
			EcoreUtil.resolveAll(res);
		}

		for (Resource resource : resources)
		{
			String[] segments = resource.getURI().segments();
			String segment = segments[segments.length - 1];
			IFile file = folder.getFile(new Path(segment));

			URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			resource.setURI(uri);
		}

		for (Resource resource : resources)
		{
			TreeIterator<Object> allContents = EcoreUtil.getAllContents(resource, false);
			while (allContents.hasNext())
			{
				Object object = (Object) allContents.next();

				if (object instanceof InternalEObject)
				{
					InternalEObject eobj = (InternalEObject) object;
					if (eobj.eIsProxy())
					{
						eobj.eSetProxyURI(null);
					}
				}
			}
		}

		for (Resource resource : resources)
		{
			try
			{
				resource.save(null);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	*/
	
	public static void copyEMFResources(final IContainer folder, Resource[] resources, IProgressMonitor monitor)
	{
		copyEMFResources(resources, new BasicCallback<Resource>() {
			@Override
			public void handle(Resource resource) {
				String[] segments = resource.getURI().segments();
				String segment = segments[segments.length - 1];
				IFile file = folder.getFile(new Path(segment));

				URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
				resource.setURI(uri);
			}
		}, monitor);
	}
	
	public static void copyEMFResources(Resource[] resources, BasicCallback<Resource> setUri, IProgressMonitor monitor)
	{
		if(monitor != null){
			monitor.subTask("Resolving resources");
		}
		
		for (Resource res : resources)
		{
			EcoreUtil.resolveAll(res);
			if(monitor != null){
				monitor.worked(1);
			}
		}
		
		if(monitor != null){
			monitor.subTask("Configuring new URIs");
		}

		for (Resource resource : resources)
		{
			//String[] segments = resource.getURI().segments();
			//String segment = segments[segments.length - 1];
			//IFile file = folder.getFile(new Path(segment));

			setUri.handle(resource);
			//URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			//resource.setURI(uri);
		}

		for (Resource resource : resources)
		{
			TreeIterator<Object> allContents = EcoreUtil.getAllContents(resource, false);
			while (allContents.hasNext())
			{
				Object object = (Object) allContents.next();

				if (object instanceof InternalEObject)
				{
					InternalEObject eobj = (InternalEObject) object;
					if (eobj.eIsProxy())
					{
						eobj.eSetProxyURI(null);
					}
				}
			}
			if(monitor != null){
				monitor.worked(1);
			}
		}
		
		if(monitor != null){
			monitor.subTask("Saving resources");
		}

		for (Resource resource : resources)
		{
			try
			{
				resource.save(null);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			
			if(monitor != null){
				monitor.worked(1);
			}
		}
	}

	public static IProject getProject(IEditorPart editor) {
		if (editor == null || editor.getEditorInput() == null){
			return null;
		}
		
		IFile editorFile = ResourceUtil.getFile(editor.getEditorInput());
		if(editorFile == null){
			return null;
		}
		
		return editorFile.getProject();
	}

	public static IProject getProjectFromElement(Object element) {
		if(element == null){
			return null;
		}
		IFile file = ResourceUtil.getFile(element);
		if(file != null){
			return file.getProject();
		}
		
		return null;
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
