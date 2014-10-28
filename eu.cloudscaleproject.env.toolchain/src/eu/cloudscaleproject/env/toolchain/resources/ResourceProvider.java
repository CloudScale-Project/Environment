package eu.cloudscaleproject.env.toolchain.resources;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public abstract class ResourceProvider{
	
	public static final String PROP_RESOURCE_CREATED = "create";
	public static final String PROP_RESOURCE_DELETED = "delete";
	public static final String PROP_RESOURCE_ADDED = "add";
	public static final String PROP_RESOURCE_REMOVED = "remove";

	private final IProject project;
	private final IFolder rootFolder;
	
	private final String defaultResName;
	
	private final LinkedHashMap<IResource, IEditorInputResource> resources 
			= new LinkedHashMap<IResource, IEditorInputResource>();
	
	public abstract boolean validateResource(IResource res);
	public abstract IResource createResource(String resourceName);
	public abstract IEditorInputResource loadResource(IResource res);

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public ResourceProvider(IProject project, IFolder folder, String defaultResName){
		this.project = project;
		this.rootFolder = folder;
		this.defaultResName = defaultResName;
		
		reloadResources();
	}
	
	public final void checkRootFolder(){
		if(!rootFolder.exists()){
			try {
				rootFolder.create(true, true, null);
				resources.clear();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	public final boolean reloadResources(){
		checkRootFolder();
		
		boolean modified = false;

		//remove missing
		Iterator<IResource> iter = resources.keySet().iterator();
		while(iter.hasNext()){
			IResource res = iter.next();
			if(!res.exists()){
				
				IEditorInputResource eir = resources.get(res);
				iter.remove();

				pcs.firePropertyChange(PROP_RESOURCE_REMOVED, null, eir);
				modified = true;
			}
		}
		
		//add new
		try {
			for(IResource res : rootFolder.members()){
				if(resources.containsKey(res)){
					continue;
				}
				
				if(validateResource(res)){
					IEditorInputResource prop = loadResource(res);
					
					resources.put(prop.getResource(), prop);
					pcs.firePropertyChange(PROP_RESOURCE_ADDED, null, prop);
					
					modified = true;
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return modified;
	}
	
	public IFolder getRootFolder(){
		return this.rootFolder;
	}
	
	public IProject getProject(){
		return this.project;
	}
	
	public List<IEditorInputResource> getResources(){
		return new ArrayList<IEditorInputResource>(resources.values());
	}
	
	public IEditorInputResource getResource(String resourceName){
		for(IEditorInputResource prop : resources.values()){
			if(resourceName.equals(prop.getResource().getName())){
				return prop;
			}
		}
		return null;
	}
	
	public boolean hasResource(String resourceName){
		IEditorInputResource prop = getResource(resourceName);
		return prop == null ? false : true;
	}
	
	private boolean isNewResourceNameValid(String newResourceName){
		if(hasResource(newResourceName)){
			return false;
		}
		try {
			for(IResource res : rootFolder.members()){
				if(newResourceName.equals(res.getName())){
					return false;
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public IEditorInputResource createNewResource(String name){
		
		checkRootFolder();
		
		String resourceName = defaultResName;
		
		//check and fix duplicated filenames
		int i = 1;			
		while(!isNewResourceNameValid(resourceName)){
			String resourceNameNew = resourceName.replaceFirst("[0-9]*", String.valueOf(i++));
			if(resourceName.equals(resourceNameNew)){
				resourceNameNew = "1" + resourceNameNew;
			}
			resourceName = resourceNameNew;
		}
		
		IResource res = createResource(resourceName);
		IEditorInputResource eir = loadResource(res);
		eir.setName(name);
		eir.save();
		
		pcs.firePropertyChange(PROP_RESOURCE_CREATED, null, eir);		
		return eir;
	}
	
	public void deleteResource(String resourceName){
		IEditorInputResource resource = getResource(resourceName);
		resource.delete();
		
		pcs.firePropertyChange(PROP_RESOURCE_DELETED, null, resource);
	}
	
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
}
