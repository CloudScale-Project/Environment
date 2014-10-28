package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

public class EditorInputFolder extends PropertyChangeSupport implements IEditorInputResource{
	private static final long serialVersionUID = 1L;

	protected final IProject project;

	private EditorInputFile propertyInputFile;

	private IFolder folder;
	
	public static final String PROP_FILENAME = "prop.alt";
		
	public EditorInputFolder(IProject project, IFolder folder ) {
		super(folder);
		this.project = project;
		this.folder = folder;
		

		IFile file = folder.getFile(PROP_FILENAME);
		this.propertyInputFile = new EditorInputFile(project, file);
	}
	
	public IProject getProject()
	{
		return project;
	}

	@Override
	public IFolder getResource(){
		return folder;
	}
	
	public void setFileResource(String key, IFile file)
	{
		if (!file.exists()) throw new IllegalStateException();

		// File must be relative to resource folder
		IPath path = file.getLocation();
		IPath relativePath = file.getLocation().makeRelativeTo(getResource().getLocation());
		if (path.equals(relativePath)) throw new IllegalStateException();

		propertyInputFile.setProperty(key, relativePath.toPortableString());
	}

	public IFile getFileResource(String key)
	{
		String relPath = propertyInputFile.getProperty(key);
		IResource res = getResource().findMember(relPath);
		if (res instanceof IFile)
			return (IFile) res;
		else
			return null;
	}
	
	public void setFolderResource(String key, IFolder folder)
	{
		if (!folder.exists() || !getResource().equals(folder.getParent()))
			throw new IllegalStateException();

		setProperty(key, folder.getName());
	}

	public IFolder getFolderResource(String key)
	{
		String foldername = getProperty(key);
		if (foldername != null)
		{
			return getResource().getFolder(foldername);
		}
		else
		{
			return null;
		}
	}

	
	
	@Override
	public synchronized void save() {

		if(!folder.exists()){
			if(!folder.exists()){
				try {
					folder.create(true, true, null);
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		
		propertyInputFile.save();
	}

	@Override
	public synchronized  void load() {

		propertyInputFile.load();
	}

	public void create() {
		if(!folder.exists()){
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		propertyInputFile.save();
	}

	@Override
	public synchronized  void delete() {
		if (folder.exists()) {
			try {
				folder.delete(true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized  String getName(){
		String name = getProperty(EditorInputFile.KEY_NAME);
		if (name == null)
		{
			return getResource().getName();
		}

		return name;
	}
	
	@Override
	public synchronized  void setName(String name){
		propertyInputFile.setName(name);
	}
	
	public synchronized String getProperty(String key){
		return propertyInputFile.getProperty(key);
	}
	
	public synchronized  void setProperty(String key, String value){
		propertyInputFile.setProperty(key, value);
	}
	
	
	@Override
	public synchronized void copyFrom(IResource src) {

		if (!src.exists() || !(src instanceof IFolder))
			throw new IllegalStateException();
		
		try {
			if (this.folder.exists())
				this.folder.delete(true, null);

			src.copy(folder.getFullPath(), true, null);
			
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		load();
	}


	////////////////////////////////////////////////////////////////////
	// Properties wiring 
	//
	@Override
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyInputFile.addPropertyChangeListener(propertyName, listener);
		super.addPropertyChangeListener(propertyName, listener);
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyInputFile.addPropertyChangeListener(listener);
		super.addPropertyChangeListener(listener);
	}
	
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyInputFile.removePropertyChangeListener(listener);
		super.removePropertyChangeListener(listener);
	}
	@Override
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyInputFile.removePropertyChangeListener(propertyName, listener);
		super.removePropertyChangeListener(propertyName, listener);
	}
}
