package eu.cloudscaleproject.env.toolchain.resources.types;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.ResourceValidationStatus;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;

public class EditorInputFolder extends EditorInputResource{

	protected final IProject project;
	
	private EditorInputFile propertyInputFile;
	private final IFolder folder;
	
	private final String validationID;
	
	public static final String PROP_FILENAME = "prop.alt";
	public static final String PROP_CHANGED = EditorInputFolder.class.getName() + ".propChanged";
	public static final String PROP_SUB_RESOURCE_CHANGED = EditorInputFolder.class.getName() + ".propSubResChanged";
	
	public static final String DIRTY_CHANGED = EditorInputFolder.class.getName() + ".dirtyChanged";

	private boolean isLoaded = false;
	private boolean isDirty = false;
	
	private HashMap<String, List<IResource>> subResources = new HashMap<String, List<IResource>>();
		
	public EditorInputFolder(IProject project, IFolder folder) {
		this(project, folder, null);
	}
	
	public EditorInputFolder(IProject project, IFolder folder, String validationID) {
		this.project = project;
		this.folder = folder;
		this.validationID = validationID;

		IFile file = folder.getFile(PROP_FILENAME);
		this.propertyInputFile = new EditorInputFile(project, file);
	}
	
	@Override
	public String getID() {
		return validationID;
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
	
	public boolean isResourceInternal(IResource res){
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
		
		List<IResource> resources = subResources.get(key);
		if(resources == null){
			resources = new ArrayList<IResource>();
			subResources.put(key, resources);
		}
		resources.add(res);
		
		propertyInputFile.setProperty(key, path);
		isDirty = true;
		pcs.firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, key);
		
		updateStatusList();
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
	
	public void setSubResource(String key, IResource res){
		
		String path = "";
		
		if(isResourceInternal(res)){
			path = getInternalResourcePath(res);
		}
		else{
			path = getExternalResourcePath(res);
		}
		
		List<IResource> resources = subResources.get(key);
		if(resources == null){
			resources = new ArrayList<IResource>();
			subResources.put(key, resources);
		}
		resources.clear();
		resources.add(res);
		
		propertyInputFile.setProperty(key, path);
		isDirty = true;
		pcs.firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, key);
		
		updateStatusList();
	}
	
	public void setSubResources(String key, List<? extends IResource> resList){
		
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
		
		List<IResource> resources = subResources.get(key);
		if(resources == null){
			resources = new ArrayList<IResource>();
			subResources.put(key, resources);
		}
		resources.clear();
		resources.addAll(resList);
		
		propertyInputFile.setProperty(key, value);
		isDirty = true;
		pcs.firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, key);
		
		updateStatusList();
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
		
		pcs.firePropertyChange(PROP_SAVED, false, true);
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
		
		subResources.clear();
		for(String key : propertyInputFile.getKeys()){
			List<IResource> resources = getSubResources(key);
			if(!resources.isEmpty()){
				subResources.put(key, getSubResources(key));
			}
		}
		
		doLoad();
		
		isDirty = false;
		isLoaded = true;
		updateStatusList();
		
		pcs.firePropertyChange(PROP_LOADED, false, true);
	}
	
	protected void doLoad(){
		//override
	}
	
	@Override
	public boolean isLoaded() {
		return isLoaded;
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
		
		doDelete();
		
		if (folder.exists()) {
			try {
				folder.delete(true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pcs.firePropertyChange(PROP_DELETED, false, true);
		updateStatusList();
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
		IValidationStatus status = getSelfStatus();
		if(status != null){
			status.setName(name);
		}
	}
	
	public synchronized String getProperty(String key){
		return propertyInputFile.getProperty(key);
	}
	
	public synchronized final void setProperty(String key, String value){
		String old = getProperty(key);
		propertyInputFile.setProperty(key, value);
		pcs.firePropertyChange(PROP_CHANGED, old, value);
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
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
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
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyInputFile.removePropertyChangeListener(propertyName, listener);
		super.removePropertyChangeListener(propertyName, listener);
	}
	
	public void setDirty(boolean param){
		pcs.firePropertyChange(DIRTY_CHANGED, this.isDirty, this.isDirty = param);
	}

	@Override
	public boolean isDirty() {
		return isDirty || propertyInputFile.isDirty();
	}
	
	////////////////////////////////////////////////////////////////////
	// Validation
	//
	private void updateStatusList(){
		HashSet<IValidationStatus> oldList = new HashSet<IValidationStatus>(Arrays.asList(getStatus()));
		HashSet<IValidationStatus> newList = new HashSet<IValidationStatus>();
		
		if(folder.exists()){
			for(Entry<String, List<IResource>> entry : subResources.entrySet()){
				for(IResource res : entry.getValue()){
					IValidationStatus status = new ResourceValidationStatus(this, this.getID() + "." + entry.getKey(), res);
					newList.add(status);
				}
			}
		}
		
		SetDiff diff = Diffs.computeSetDiff(oldList, newList);
		
		for(Object removedStatus : diff.getRemovals()){
			removeStatus((IValidationStatus)removedStatus);
		}
		for(Object addedStatus : diff.getAdditions()){
			addStatus((IValidationStatus)addedStatus);
		}
		
		IValidationStatus status = getSelfStatus();
		if(status != null){
			status.setName(getName());
		}
	}
	
	@Override
	public IValidationStatus[] getStatus(String resourceKey) {
		// TODO Auto-generated method stub
		return super.getStatus(this.getID() + "." + resourceKey);
	}
	
	public IValidationStatus getStatus(IResource resource){
		List<IValidationStatus> out = new ArrayList<IValidationStatus>();
		IValidationStatus[] statuses = getStatus();
		
		for(IValidationStatus status : statuses){
			if(status instanceof ResourceValidationStatus){
				ResourceValidationStatus rvs = (ResourceValidationStatus)status;
				if(resource.equals(rvs.getResource())){
					out.add(status);
				}
			}
		}
		
		//only one status is permitted per resource
		assert(out.size()<=1);
		return out.isEmpty() ? null : out.get(0);
	}

}
