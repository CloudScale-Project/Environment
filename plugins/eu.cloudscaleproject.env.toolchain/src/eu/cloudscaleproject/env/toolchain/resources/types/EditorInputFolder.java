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
	
	public static final String PROP_SAVED = EditorInputFolder.class.getName() + ".propSaved";
	public static final String PROP_LOADED = EditorInputFolder.class.getName() + ".propLoaded";
	public static final String PROP_DELETED = EditorInputFolder.class.getName() + ".propDeleted";

	public static final String PROP_CHANGED = EditorInputFolder.class.getName() + ".propChanged";
	public static final String PROP_RESOURCE_CHANGED = EditorInputFolder.class.getName() + ".propResChanged";

		
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
	
	private boolean isResourceInternal(IResource res){
		IPath relative = res.getFullPath().makeRelativeTo(getResource().getFullPath());
		if(relative.equals(res.getFullPath())){
			return false;
		}
		return true;
	}
	
	private IPath getRelativePath(IResource res){
		IPath relative = res.getFullPath().makeRelativeTo(getResource().getFullPath());
		if(relative.equals(res.getFullPath())){
			throw new IllegalArgumentException("Resource path can not be made relative to EditorInputFolder! Path: " 
												+ res.getFullPath().toString());
		}
		return relative;
	}
	
	public void setResource(String key, IResource res){
		if(isResourceInternal(res)){
			setInternalResource(key, res);
		}
		else{
			setExternalResource(key, res);
		}
	}
	
	private void setInternalResource(String key, IResource file)
	{
		String old = propertyInputFile.getProperty(key);
		
		if(file == null){
			propertyInputFile.setProperty(key, "");
			firePropertyChange(PROP_RESOURCE_CHANGED, old, "");
			return;
		}
		
		IPath relativePath = getRelativePath(file);
		propertyInputFile.setProperty(key, relativePath.toPortableString());
		
		firePropertyChange(PROP_RESOURCE_CHANGED, old, relativePath.toPortableString());
	}
	
	private void setExternalResource(String key, IResource file)
	{
		String old = propertyInputFile.getProperty(key);

		if(file == null){
			propertyInputFile.setProperty(key, "");
			firePropertyChange(PROP_RESOURCE_CHANGED, old, "");
			return;
		}
		
		// File must be relative to project folder
		IPath path = file.getProjectRelativePath();
		propertyInputFile.setProperty(key, path.toPortableString());
		firePropertyChange(PROP_RESOURCE_CHANGED, old, path.toPortableString());
	}

	public IFile getFileResource(String key)
	{
		String relPath = propertyInputFile.getProperty(key);
		if(relPath == null || relPath.isEmpty()){
			return null;
		}
		
		IResource res = getResource().findMember(relPath);
		
		//TODO: This could cause problems!
		//try project relative path
		if(res == null){
			res = getResource().getProject().findMember(relPath);
		}
		
		if (res instanceof IFile){
			return (IFile) res;
		}
		else{
			return null;
		}
	}
	
	public IFolder getFolderResource(String key)
	{
		String relPath = propertyInputFile.getProperty(key);
		if(relPath == null || relPath.isEmpty()){
			return null;
		}
		
		IResource res = getResource().findMember(relPath);
		
		//TODO: This could cause problems!
		//try project relative path
		if(res == null){
			res = getResource().getProject().findMember(relPath);
		}
		
		if (res instanceof IFolder){
			return (IFolder) res;
		}
		else{
			return null;
		}
	}
	
	@Override
	public synchronized final void save() {

		if(!folder.exists()){
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		propertyInputFile.save();
		doSave();
		firePropertyChange(PROP_SAVED, false, true);
	}
	
	protected void doSave(){
		//override
	}

	@Override
	public synchronized final void load() {

		if(!folder.exists()){
			throw new IllegalStateException("Can't load resource. Root folder does not exist: " + folder.getLocation().toString());
		}
		
		propertyInputFile.load();
		doLoad();
		firePropertyChange(PROP_LOADED, false, true);
	}
	
	protected void doLoad(){
		//override
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
	public synchronized final void delete() {
		if (folder.exists()) {
			try {
				folder.delete(true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		doDelete();
		firePropertyChange(PROP_DELETED, false, true);
	}
	
	protected void doDelete(){
		//override
	}

	@Override
	public synchronized String getName(){
		String name = getProperty(EditorInputFile.KEY_NAME);
		if (name == null)
		{
			return getResource().getName();
		}

		return name;
	}
	
	@Override
	public synchronized final void setName(String name){
		propertyInputFile.setName(name);
	}
	
	public synchronized String getProperty(String key){
		return propertyInputFile.getProperty(key);
	}
	
	public synchronized final void setProperty(String key, String value){
		String old = getProperty(key);
		propertyInputFile.setProperty(key, value);
		firePropertyChange(PROP_CHANGED, old, value);
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
