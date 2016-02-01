package eu.cloudscaleproject.env.toolchain.resources.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.ResourceValidationStatus;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;

public class EditorInputFolder extends EditorInputResource{

	private static final Logger logger = Logger.getLogger(EditorInputFolder.class.getName());
	
	private static final String DEFAULT_KEY = "sub_resources";
	private static final String MULTIPATH_SEPARATOR = ",";
	
	protected final IProject project;
	
	private EditorInputFile propertyInputFile;
	private final IFolder folder;
		
	public static final String PROP_FILENAME = "prop.alt";
	public static final String PROP_SUB_RESOURCE_CHANGED = EditorInputFolder.class.getName() + ".propSubResChanged";
		
	private final Object subResourcesLock = new Object();
	private HashMap<String, List<IResource>> subResources = new HashMap<String, List<IResource>>();
		
	public EditorInputFolder(IProject project, IFolder folder) {
		this(project, folder, null);
	}
	
	public EditorInputFolder(IProject project, IFolder folder, String validationID) {
		
		super(folder, validationID);
		
		this.project = project;
		this.folder = folder;

		IFile file = folder.getFile(PROP_FILENAME);
		
		//pre-load properties
		this.propertyInputFile = new EditorInputFile(project, file){
			//do not save internal properties status
			protected void handleSaveStatus() {};
		};
		
		//fetch sub-resources
		synchronized (subResourcesLock) {
			subResources.clear();
			for(String key : propertyInputFile.getKeys()){
				List<IResource> resources = getSubResourcesFromWorkspace(key);
				if(!resources.isEmpty()){
					subResources.put(key, resources);
				}
			}
		}
		
		//initialize validation listener
		if(validationID != null){
			initializeValidationListener();
		}
	}
	
	@Override
	public IProject getProject()
	{
		return project;
	}

	@Override
	public IFolder getResource(){
		return folder;
	}
	
	@Override
	public void dispose() {
		this.propertyInputFile.dispose();
		super.dispose();
	}
	
	@Override
	public String getType() {
		return getProperty(ResourceProvider.PROP_TYPE);
	}
	
	///////////////////////////////////////////////////////////////////
	// Sub resource add methods
	
	public void addSubResource(IResource res){
		addSubResource(DEFAULT_KEY, res);
	}
	
	public void addSubResource(String key, IResource res){
		
		synchronized (subResourcesLock) {
			doAddSubResource(key, res);
		}
		
		setDirty(true);
		firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, key);
		updateStatusList();
	}
	
	protected void doAddSubResource(String key, IResource res){

		String path = "";
		String oldPath = propertyInputFile.getProperty(key);
		
		if(isResourceInternal(res)){
			path = getInternalResourcePath(res);
		}
		else{
			path = getExternalResourcePath(res);
		}
		
		if(oldPath != null){
			String oldPathTrimed = oldPath.trim();
			if(!oldPathTrimed.isEmpty()){
				path = oldPathTrimed + MULTIPATH_SEPARATOR + path;
			}
		}
		
		List<IResource> resources = subResources.get(key);
		if(resources == null){
			resources = new ArrayList<IResource>();
			subResources.put(key, resources);
		}
		resources.add(res);
		propertyInputFile.setProperty(key, path);
	}

	///////////////////////////////////////////////////////////////////
	// Sub resource remove methods
	
	public void removeSubResource(IResource res){
		removeSubResource(DEFAULT_KEY, res);
	}
	
	public void removeSubResource(String key, IResource res){
				
		synchronized (subResourcesLock) {
			doRemoveSubResource(key, res);
		}
		
		setDirty(true);
		firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, key);
		
		updateStatusList();
	}
	
	protected void doRemoveSubResource(String key, IResource res){

		List<IResource> resources = getSubResourcesFromWorkspace(key);
		IResource resource = null;
		
		for(IResource r : resources){
			if(r.equals(res)){
				resource = r;
			}
		}
		
		if(resource != null){
			subResources.remove(resource);
			resources.remove(resource);
		}
		
		doSetSubResources(key, resources);		
	}
	
	public void removeSubResources(){
		removeSubResources(DEFAULT_KEY);
	}
	
	public void removeSubResources(String key){
		
		synchronized (subResourcesLock) {
			doRemoveSubResources(key);
		}
		
		setDirty(true);
		firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, key);
		updateStatusList();
	}
	
	protected void doRemoveSubResources(String key){
		List<IResource> resources = getSubResources(key);
		resources.clear();
		doSetSubResources(key, resources);
	}

	///////////////////////////////////////////////////////////////////
	// Sub resource delete methods
	
	/**
	 * Deletes specified internal sub resource from the workbench and removes it from the internal cache.
	 * 
	 * If the resource is not contained under this EditorInput resource folder (external), 
	 * the specified resource is only unregistered and removed from the internal cache.
	 * 
	 * @param resource The IResource to delete or remove, depending on the internal/external type (read the description above).
	 */
	public void deleteSubResource(IResource resource){
		deleteSubResource(DEFAULT_KEY, resource);
	}
	
	/**
	 * Deletes specified internal sub resource from the workbench and removes it from the internal cache.
	 * 
	 * If the resource is not contained under this EditorInput resource folder (external), 
	 * the specified resource is only unregistered and removed from the internal cache.
	 * 
	 * @param key The key under which the resource path is saved.
	 * @param resource The IResource to delete or remove, depending on the internal/external type (read the description above).
	 */
	public void deleteSubResource(String key, IResource resource){
		
		boolean isInternal = isResourceInternal(resource);
		if(!isInternal){
			logger.warning("Resource '" + resource.getFullPath() + "' is not internal! It won't be deleted!");
			removeSubResource(key, resource);
			return;
		}
		
		synchronized (subResourcesLock) {
			try{
				deleteInProgress = true;
				doDeleteSubResource(key, resource);
			}
			finally{
				deleteInProgress = false;
			}
		}

		setDirty(true);
		firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, key);
		updateStatusList();
	}
	
	protected void doDeleteSubResource(String key, IResource resource){
		
		doRemoveSubResource(key, resource);

		try {
			resource.delete(true, null);
		}
		catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete resources, stored under the default key (EditorInputFolder.DEFAULT_KEY)
	 * It behaves exactly like deleteSubResources(EditorInputFolder.DEFAULT_KEY).
	 */
	public void deleteSubResources(){
		deleteSubResources(DEFAULT_KEY);
	}
	
	/**
	 * Deletes all sub resources, stored under specified key, from the workbench and removes it from the internal cache.
	 * 
	 * If the resource is not contained under this EditorInput resource folder (external), 
	 * the resource is only unregistered and removed from the internal cache.
	 * 
	 * @param key The key under which the resource path is saved.
	 */
	public void deleteSubResources(String key){
		
		synchronized (subResourcesLock) {
			try{
				deleteInProgress = true;
				doDeleteSubResources(key);
			}
			finally{
				deleteInProgress = false;
			}
		}

		setDirty(true);
		firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, key);
		updateStatusList();
	}
	
	protected void doDeleteSubResources(String key){

		List<IResource> resources = getSubResourcesFromWorkspace(key);
		for(IResource resource : resources){
			
			boolean isInternal = isResourceInternal(resource);
			if(!isInternal){
				logger.warning("Resource '" + resource.getFullPath() + "' is not internal! It won't be deleted!");
				removeSubResource(key, resource);
				continue;
			}
			doDeleteSubResource(key, resource);
		}
	}

	///////////////////////////////////////////////////////////////////
	// Sub resource set methods
	
	public void setSubResource(String key, IResource res){
		
		synchronized (subResourcesLock) {
			doSetSubResource(key, res);
		}
		
		setDirty(true);
		
		firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, key);
		updateStatusList();
	}
	
	private void doSetSubResource(String key, IResource res){
		String path = "";
		
		if(isResourceInternal(res)){
			path = getInternalResourcePath(res);
		}
		else{
			path = getExternalResourcePath(res);
		}
		
		synchronized (subResourcesLock) {
			List<IResource> resources = subResources.get(key);
			if(resources == null){
				resources = new ArrayList<IResource>();
				subResources.put(key, resources);
			}
			resources.clear();
			resources.add(res);
			
			propertyInputFile.setProperty(key, path);
		}
	}
	
	public void setSubResources(String key, List<? extends IResource> resList){
		
		doSetSubResources(key, resList);
		
		setDirty(true);
		
		firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, key);
		updateStatusList();
	}
	
	private void doSetSubResources(String key, List<? extends IResource> resList){
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
				value += MULTIPATH_SEPARATOR;
			}
		}
		
		List<IResource> resources = null;
		
		synchronized (subResourcesLock) {
			resources = subResources.get(key);
			if(resources == null){
				resources = new ArrayList<IResource>();
				subResources.put(key, resources);
			}
		
			resources.clear();
			resources.addAll(resList);
		
			propertyInputFile.setProperty(key, value);
		}
	}

	///////////////////////////////////////////////////////////////////
	// Sub resource helpers
	
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
		IPath path = file.getFullPath();
		return path.toPortableString();
	}

	public boolean isResourceInternal(IResource res){
		
		return getResource().getFullPath().isPrefixOf(res.getFullPath());
	}
	
	private IPath getRelativePath(IResource res){
		IPath relative = res.getFullPath().makeRelativeTo(getResource().getFullPath());
		if(relative.equals(res.getFullPath())){
			throw new IllegalArgumentException("Resource path can not be made relative to EditorInputFolder! Path: " 
												+ res.getFullPath().toString());
		}
		return relative;
	}

	//////////////////////////////////////////////////////////////////////
	// Public methods for retrieving loaded resources 
	
	public IResource getSubResource(String key)
	{
		synchronized (subResourcesLock) {
			List<IResource> resList = subResources.get(key);
			if(resList != null && !resList.isEmpty()){
				return resList.get(0);
			}
		}
		
		return null;
	}
	
	public List<IResource> getSubResources(String key)
	{
		ArrayList<IResource> resources = new ArrayList<IResource>();
		
		synchronized (subResourcesLock) {
			List<IResource> resList = subResources.get(key);
			if(resList != null){
				resources.addAll(resList);
			}
		}
		
		return resources;
	}
	
	public List<IResource> getSubResources()
	{
		ArrayList<IResource> resources = new ArrayList<IResource>();
		
		synchronized (subResourcesLock) {
			for(List<IResource> resList : subResources.values()){
				for(IResource res : resList){
					resources.add(res);
				}
			}
		}
		
		return resources;
	}
	
	//////////////////////////////////////////////////////////////////////

	protected List<IResource> getSubResourcesFromWorkspace(String key)
	{
		ArrayList<IResource> resources = new ArrayList<IResource>();
		
		String relPath = propertyInputFile.getProperty(key);
		if(relPath == null || relPath.isEmpty()){
			return resources;
		}
				
		//check if this is multi-resource key
		if(relPath.contains(MULTIPATH_SEPARATOR)){
			for(String path : relPath.split(MULTIPATH_SEPARATOR)){
				
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
			//first try to locate alternative internal resource if it exists
			IResource res = getResource().findMember(relPath);
			//if the internal resource does not exist, try project relative path
			if(res == null){
				res = getResource().getProject().getParent().findMember(relPath);
			}			
			if(res != null){
				resources.add(res);
			}
		}
		
		return resources;
	}
	
	protected final void handleCreate(IProgressMonitor monitor) {
		if(!folder.exists()){
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		propertyInputFile.handleCreate(monitor);
		doCreate(monitor);
		updateStatusList();
	}
	
	protected void doCreate(IProgressMonitor monitor) {
		//override
	}
	
	protected void handleSaveStatus(){
		
		if(hasStatusEntry(IValidationStatus.SEVERITY_ERROR)){
			propertyInputFile.doSetProperty(KEY_STATUS, VALUE_STATUS_ERROR);
		}
		else if(hasStatusEntry(IValidationStatus.SEVERITY_WARNING)){
			propertyInputFile.doSetProperty(KEY_STATUS, VALUE_STATUS_WARNING);
		}
		else{
			propertyInputFile.doRemoveProperty(KEY_STATUS);
		}
	}
	
	protected final void handleSave(IProgressMonitor monitor) {

		if(!folder.exists()){
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		handleSaveStatus();
		propertyInputFile.handleSave(monitor);
		doSave(monitor);
		
		setDirty(false);
	}
	
	protected void doSave(IProgressMonitor monitor){
		//override
	}

	protected final void handleLoad(IProgressMonitor monitor) {
		
		propertyInputFile.handleLoad(monitor);
		
		synchronized (subResourcesLock) {
			subResources.clear();
			for(String key : propertyInputFile.getKeys()){
				List<IResource> resources = getSubResourcesFromWorkspace(key);
				if(!resources.isEmpty()){
					subResources.put(key, resources);
				}
			}
		}
		
		doLoad(monitor);
		updateStatusList();
	}
	
	protected void doLoad(IProgressMonitor monitor){
		//override
	}

	@Override
	public final void handleDelete(IProgressMonitor monitor) {
		
		doDelete(monitor);
		
			synchronized (subResourcesLock) {
				propertyInputFile.handleDelete(monitor);
				subResources.clear();
			}
		
		if (folder.exists()) {
			try {
				folder.delete(true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void doDelete(IProgressMonitor monitor){
		//override
	}
	
	@Override
	public void copyFrom(IResource src){
		copyFrom(src, null);
	}
	
	public void copyFrom(IResource src, IProgressMonitor monitor) {
		
		synchronized (saveLoadLock) {
			if (!src.exists() || !(src instanceof IFolder))
				throw new IllegalStateException();
			
			try {
				saveInProgress = true;
				if (this.folder.exists())
					this.folder.delete(true, null);

				src.copy(folder.getFullPath(), true, null);
				
			} 
			catch (CoreException e) {
				e.printStackTrace();
			}
			finally{
				saveInProgress = false;
			}
		}
		
		load();
	}

	@Override
	public String getName(){
		String name = getProperty(EditorInputFile.KEY_NAME);
		if (name == null)
		{
			return getResource().getName();
		}

		return name;
	}
	
	@Override
	public final void setName(String name){
		String old = getProperty(KEY_NAME);
		propertyInputFile.setProperty(KEY_NAME, name);

		setDirty(true);
		firePropertyChange(PROP_NAME, old, name);
	}
	
	@Override
	public String getPersistedStatus() {
		return getProperty(KEY_STATUS);
	}
	
	public String getProperty(String key){
		return propertyInputFile.getProperty(key);
	}
	
	public String[] getProperties(String key){
		return propertyInputFile.getProperties(key);
	}
	
	public final void setProperty(String key, String value){
		String old = getProperty(key);
		propertyInputFile.setProperty(key, value);

		setDirty(true);
		firePropertyChange(key, old, value);
	}
	
	public final void setProperties(String key, String[] value){
		String[] old = getProperties(key);
		propertyInputFile.setProperties(key, value);

		setDirty(true);
		firePropertyChange(key, old, value);
	}
	
	@Override
	public boolean isDirty() {
		return super.isDirty() || propertyInputFile.isDirty();	
	}
	
	@Override
	public void setJobInProgress(boolean enable) {
		propertyInputFile.setJobInProgress(true);
		super.setJobInProgress(enable);
	}

	////////////////////////////////////////////////////////////////////
	// Validation
	//
	private void updateStatusList(){
		/*
		BatchExecutor.getInstance().addTask(this, "updateStatusList", new Runnable() {
			
			@Override
			public void run() {
				doUpdateStatusList();
			}
		});
		*/

		doUpdateStatusList();
	}
	
	private void doUpdateStatusList(){
		HashSet<IValidationStatus> oldList = new HashSet<IValidationStatus>(Arrays.asList(getSubStatuses()));
		HashSet<IValidationStatus> newList = new HashSet<IValidationStatus>();
		
		if(folder.exists()){
			for(Entry<String, List<IResource>> entry : subResources.entrySet()){
				for(IResource res : entry.getValue()){
					IValidationStatus status = new ResourceValidationStatus(this, this.getID() + "." + entry.getKey(), res);
					status.setName(res.getName());
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
	public IValidationStatus[] getSubStatus(String resourceKey) {
		// TODO Auto-generated method stub
		return super.getSubStatus(this.getID() + "." + resourceKey);
	}
	
	public IValidationStatus getStatus(IResource resource){
		List<IValidationStatus> out = new ArrayList<IValidationStatus>();
		IValidationStatus[] statuses = getSubStatuses();
		
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
