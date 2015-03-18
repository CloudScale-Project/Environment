package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;

public class EditorInputFolder extends PropertyChangeSupport implements IEditorInputResource{
	private static final long serialVersionUID = 1L;

	protected final IProject project;

	private EditorInputFile propertyInputFile;

	private final IFolder folder;
	
	public static final String PROP_FILENAME = "prop.alt";
	
	public static final String PROP_SAVED = EditorInputFolder.class.getName() + ".propSaved";
	public static final String PROP_LOADED = EditorInputFolder.class.getName() + ".propLoaded";
	public static final String PROP_DELETED = EditorInputFolder.class.getName() + ".propDeleted";

	public static final String PROP_CHANGED = EditorInputFolder.class.getName() + ".propChanged";
	public static final String PROP_SUB_RESOURCE_CHANGED = EditorInputFolder.class.getName() + ".propSubResChanged";
	
	public static final String DIRTY_CHANGED = EditorInputFolder.class.getName() + ".dirtyChanged";

	private boolean isDirty = false;
		
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
	
	@Override
	public String getType() {
		return getProperty(ResourceProvider.PROP_TYPE);
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
	
	public void setSubResource(String key, IResource res){
		
		String oldPath = propertyInputFile.getProperty(key);
		String path = "";
		
		if(isResourceInternal(res)){
			path = getInternalResourcePath(res);
		}
		else{
			path = getExternalResourcePath(res);
		}
		
		propertyInputFile.setProperty(key, path);
		isDirty = true;
		firePropertyChange(PROP_SUB_RESOURCE_CHANGED, oldPath, path);
	}
	
	public void addSubResource(String key, IResource res){
		String oldPath = propertyInputFile.getProperty(key);
		String path = "";
		
		if(isResourceInternal(res)){
			path = getInternalResourcePath(res);
		}
		else{
			path = getExternalResourcePath(res);
		}
		
		if(oldPath != null){
			String oldPathTrimed = oldPath.trim();
			if(!oldPathTrimed.isEmpty()){
				path = oldPathTrimed + "," + path;
			}
		}
		
		propertyInputFile.setProperty(key, path);
		isDirty = true;
		firePropertyChange(PROP_SUB_RESOURCE_CHANGED, oldPath, path);
	}
	
	public void removeSubResource(String key, IResource res){
		
		List<IResource> resources = getSubResources(key);
		IResource toRemove = null;
		
		for(IResource r : resources){
			if(r.equals(res)){
				toRemove = r;
			}
		}
		
		if(toRemove != null){
			resources.remove(toRemove);
		}
		setSubResources(key, resources);
	}
	
	public void setSubResources(String key, List<? extends IResource> resList){
		
		String oldValue = propertyInputFile.getProperty(key);
		String value = "";
		
		Iterator<? extends IResource> iter = resList.iterator();
		
		while(iter.hasNext()){
			IResource res = iter.next();
			
			if(isResourceInternal(res)){
				value += getInternalResourcePath(res);
			}
			else{
				value += getExternalResourcePath(res);
			}
			
			if(iter.hasNext()){
				//TODO: find a better way to specify "multi-resource" key value!
				value += ":";
			}
		}
		
		propertyInputFile.setProperty(key, value);
		isDirty = true;
		firePropertyChange(PROP_SUB_RESOURCE_CHANGED, oldValue, value);
	}
	
	private String getInternalResourcePath(IResource file)
	{
		if(file == null){
			return "";
		}
		
		// File path must be relative to alternative folder
		IPath relativePath = getRelativePath(file);
		return relativePath.toPortableString();
	}
	
	private String getExternalResourcePath(IResource file)
	{
		if(file == null){
			return "";
		}
		
		// File must be relative to project folder
		IPath path = file.getProjectRelativePath();
		return path.toPortableString();
	}

	public IResource getSubResource(String key)
	{
		String relPath = propertyInputFile.getProperty(key);
		if(relPath == null || relPath.isEmpty()){
			return null;
		}
		
		//check if this is multi-resource key
		if(relPath.contains(":")){
			relPath = relPath.split(":")[0];
		}
		
		IResource res = getResource().findMember(relPath);
		
		//TODO: This could cause problems!
		//try project relative path
		if(res == null){
			res = getResource().getProject().findMember(relPath);
		}
		
		return res;
	}
	
	public List<IResource> getSubResources(String key)
	{
		ArrayList<IResource> resources = new ArrayList<IResource>();

		String relPath = propertyInputFile.getProperty(key);
		if(relPath == null || relPath.isEmpty()){
			return resources;
		}
				
		//check if this is multi-resource key
		if(relPath.contains(":")){
			for(String path : relPath.split(":")){
				
				IResource res = getResource().findMember(path);
				
				//TODO: This could cause problems!
				//try project relative path
				if(res == null){
					res = getResource().getProject().findMember(relPath);
				}
				
				if (res instanceof IFile){
					resources.add((IFile)res);
				}
			}
		}
		else{
			IResource res = getSubResource(key);
			if(res != null){
				resources.add(res);
			}
		}
		
		return resources;
	}
	
	@Override
	public synchronized void save() {

		if(!folder.exists()){
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		propertyInputFile.save();
		doSave();
		isDirty = false;
		firePropertyChange(PROP_SAVED, false, true);
	}
	
	protected void doSave(){
		//override
	}

	@Override
	public synchronized void load() {

		if(!folder.exists()){
			throw new IllegalStateException("Can't load resource. Root folder does not exist: " + folder.getLocation().toString());
		}
		
		propertyInputFile.load();
		doLoad();
				
		isDirty = false;
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
	
	public void setDirty(boolean param){
		firePropertyChange(DIRTY_CHANGED, this.isDirty, this.isDirty = param);
	}

	@Override
	public boolean isDirty() {
		return isDirty || propertyInputFile.isDirty();
	}
}
