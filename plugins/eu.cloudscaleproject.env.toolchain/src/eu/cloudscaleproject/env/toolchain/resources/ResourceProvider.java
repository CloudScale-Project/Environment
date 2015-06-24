package eu.cloudscaleproject.env.toolchain.resources;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.BatchExecutor;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeListener;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeNotifier;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFile;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public abstract class ResourceProvider
{

	public static final String PROP_RESOURCE_CREATED = "eu.cloudscaleproject.env.toolchain.resources.ResourceProvider.create";
	public static final String PROP_RESOURCE_DELETED = "eu.cloudscaleproject.env.toolchain.resources.ResourceProvider.delete";

	public static final String PROP_RESOURCE_ADDED = "eu.cloudscaleproject.env.toolchain.resources.ResourceProvider.add";
	public static final String PROP_RESOURCE_REMOVED = "eu.cloudscaleproject.env.toolchain.resources.ResourceProvider.remove";
	public static final String PROP_RESOURCE_MODIFIED = "eu.cloudscaleproject.env.toolchain.resources.ResourceProvider.modified";

	public static final String TAG_SELECTED = "eu.cloudscaleproject.env.toolchain.resources.ResourceProvider.selected";

	public static final String PROP_TYPE = "resourceType";
	public static final String PROP_ID = "providerId";

	private final IFolder rootFolder;
	private final String defaultResName;
	
	private final Object propLock = new Object();
	private final Object resourcesLock = new Object();
		
	private final LinkedHashMap<IResource, IEditorInputResource> resources = new LinkedHashMap<IResource, IEditorInputResource>();

	private final SimpleDateFormat result_postix_format = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
	
	protected abstract boolean validateResource(IResource res);
	protected abstract IResource createResource(String resourceName);
	protected abstract IEditorInputResource createEditorInputResource(IResource res, String type);
	
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final ExplorerChangeListener ecl = new ExplorerChangeListener()
	{

		@Override
		public void resourceChanged(IResourceDelta delta)
		{
			
			synchronized (resourcesLock){				
 				
				for (IResourceDelta alternativeDelta : delta.getAffectedChildren())
				{
					
					if (alternativeDelta.getKind() == IResourceDelta.ADDED)
					{
						loadResource(alternativeDelta.getResource());
					}
					
					if (alternativeDelta.getKind() == IResourceDelta.REMOVED)
					{
						IEditorInputResource r = resources.get(alternativeDelta.getResource());
						if (r != null)
						{
							removeResource(r);
							firePropertyChange(PROP_RESOURCE_DELETED, r, null);
						}
					}
					
					if (alternativeDelta.getKind() == IResourceDelta.CHANGED)
					{
						final IEditorInputResource r = resources.get(alternativeDelta.getResource());
						
						if (r == null){
							return;
						}
						
						// do not trigger re-load, if the property change is 
						// triggered from create/save/delete operations 
						if(r.isCreateInProgress() || r.isDeleteInProgress() || r.isSaveInProgress()){
							return;
						}
						
						BatchExecutor.getInstance().addTask(alternativeDelta.getResource(), new Runnable() {
							
							@Override
							public void run() {
								r.load();
								firePropertyChange(PROP_RESOURCE_MODIFIED, null, r);
							}
						});
						
					}
					
				}
			}
			
		}

		@Override
		public IResource[] getResources()
		{
			return new IResource[] { ResourceProvider.this.getRootFolder() };
		}
	};

	public ResourceProvider(IFolder folder, String defaultResName)
	{
		this.rootFolder = folder;
		this.defaultResName = defaultResName;

		initialize();
		ExplorerChangeNotifier.getInstance().addListener(ecl);
	}
	
	public final void initialize()
	{
		checkRootFolder();

		synchronized (resourcesLock) {
			try
			{
				for (IResource res : rootFolder.members())
				{
					if (res.exists())
					{
						loadResource(res);
					}
				}
			}
			catch (CoreException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void dispose()
	{
		ExplorerChangeNotifier.getInstance().removeListener(ecl);
	}

	public void tagResource(String tag, IEditorInputResource resource)
	{
		IProject project = rootFolder.getProject();

		// save tags into project persisted storage
		QualifiedName key = new QualifiedName(rootFolder.getLocationURI().toString(), tag);
		try
		{
			synchronized (propLock) {
				project.setPersistentProperty(key, resource != null ? resource.getResource().getName() : "");				
			}
		} catch (CoreException e)
		{
			e.printStackTrace();
		}
	}

	public boolean hasTag(String tag, IEditorInputResource resource)
	{
		IProject project = rootFolder.getProject();

		QualifiedName key = new QualifiedName(rootFolder.getLocationURI().toString(), tag);
		try
		{
			String tagedResourceName;
			synchronized (propLock) {
				tagedResourceName = project.getPersistentProperties().get(key);
			}
			
			if (tagedResourceName == null || tagedResourceName.isEmpty())
			{
				return false;
			}

			IEditorInputResource res = getResource(tagedResourceName);
			if (res == null || !res.getResource().exists())
			{
				return false;
			}

			if (res.equals(resource))
			{
				return true;
			}

		} catch (CoreException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	public IEditorInputResource getTaggedResource(String tag)
	{
		IProject project = rootFolder.getProject();

		QualifiedName key = new QualifiedName(rootFolder.getLocationURI().toString(), tag);
		try
		{
			String tagedResourceName;
			synchronized (propLock) {
				tagedResourceName = project.getPersistentProperties().get(key);
			}
			
			if (tagedResourceName == null || tagedResourceName.isEmpty())
			{
				return null;
			}

			IEditorInputResource res = getResource(tagedResourceName);
			if (res == null || !res.getResource().exists())
			{
				return null;
			}

			return res;

		} catch (CoreException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public synchronized final boolean checkRootFolder()
	{
		boolean modified = false;
		try
		{
			if (!rootFolder.exists())
			{
				if (rootFolder.getParent().exists())
				{
					rootFolder.create(true, true, null);
					modified = true;
				}
			}
		} catch (CoreException e)
		{
			e.printStackTrace();
		}

		return modified;
	}
	
	public IFolder getRootFolder()
	{
		return this.rootFolder;
	}

	public IProject getProject()
	{
		return this.rootFolder.getProject();
	}

	public List<IEditorInputResource> getResources()
	{
		synchronized (resourcesLock) {
			return new ArrayList<IEditorInputResource>(resources.values());			
		}
	}

	public IEditorInputResource getResource(String resourceName)
	{

		if (resourceName == null)
		{
			return null;
		}

		synchronized (resourcesLock) {
			for (IEditorInputResource prop : resources.values())
			{
				if(prop == null){
					continue;
				}
				
				if (resourceName.equals(prop.getResource().getName()))
				{
					return prop;
				}
			}
		}
		
		return null;
	}

	public IEditorInputResource getResourceByName(String name)
	{
		synchronized (resourcesLock) {
			for (IEditorInputResource prop : resources.values())
			{
				if (name.equals(prop.getName()))
				{
					return prop;
				}
			}
		}
		
		return null;
	}

	public IEditorInputResource getResource(IResource resource)
	{
		if (resource == null)
		{
			return null;
		}

		synchronized (resourcesLock) {
			for (IEditorInputResource prop : resources.values())
			{
				if (resource.getName().equals(prop.getResource().getName()))
				{
					return prop;
				}
			}
		}
		
		return null;
	}

	public boolean hasResource(String resourceName)
	{
		IEditorInputResource prop = getResource(resourceName);
		return prop == null ? false : true;
	}
		
	public IEditorInputResource createNewResource(String name, String type)
	{
		checkRootFolder();

		String resourceNameBase = defaultResName + "_" + result_postix_format.format(new Date());
		String resourceName = resourceNameBase;
		
		int c = 1;
		while (getResource(resourceName) != null)
		{
			resourceName = resourceNameBase +"("+c+")";
			++c;
		}
		
		return createNewResource(resourceName, name, type);
	}

	public IEditorInputResource createNewResource(String resourceName, String name, String type)
	{
		checkRootFolder();

		IResource res = createResource(resourceName);
		
		//add null value
		synchronized (resourcesLock) {
			resources.put(res, null);
		}
		
		IEditorInputResource eir = createEditorInputResource(res, type);
		eir.setProperty(PROP_TYPE, type);
		eir.setName(name);
		eir.create();

		synchronized (resourcesLock) {
			resources.remove(res);
			addResource(eir);
		}
		
		firePropertyChange(PROP_RESOURCE_CREATED, null, eir);
		return eir;
	}
	
	private void loadResource(IResource res)
	{			
		synchronized (resourcesLock) {
			if(resources.containsKey(res)){
				return;
			}
		}
		
		boolean valid = validateResource(res);
		if (!valid) return;
		
		String type = null;
		
		EditorInputFile preloadedResource = null;
		
		if (res instanceof IFile && "prop".equals(res.getFileExtension()))
		{
			preloadedResource = new EditorInputFile(res.getProject(), (IFile) res);
			preloadedResource.load();
			type = preloadedResource.getProperty(PROP_TYPE);
		} else if (res instanceof IFolder)
		{
			preloadedResource = new EditorInputFile(res.getProject(), ((IFolder) res).getFile(EditorInputFolder.PROP_FILENAME));
			preloadedResource.load();
			type = preloadedResource.getProperty(PROP_TYPE);
		}
		
		IEditorInputResource eir = createEditorInputResource(res, type);
		
		if(preloadedResource != null){
			eir.setName(preloadedResource.getName());
			eir.setDirty(false);
		}
		
		addResource(eir);
	}
	
	private void addResource(final IEditorInputResource eir)
	{
		synchronized (resourcesLock) {
			if(resources.containsKey(eir.getResource())){
				return;
			}
			resources.put(eir.getResource(), eir);			
		}
		
		eir.addPropertyChangeListener(IEditorInputResource.PROP_DELETED, new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				removeResource(eir);
				firePropertyChange(PROP_RESOURCE_DELETED, eir, null);
			}
		});
		
		if (eir instanceof IValidationStatusProvider)
		{
			StatusManager.getInstance().addStatusProvider(eir.getResource().getProject(), (IValidationStatusProvider) eir);
		}

		firePropertyChange(PROP_RESOURCE_ADDED, null, eir);
	}

	private void removeResource(IEditorInputResource eir)
	{	
		//let the editor input listener to delete and clean the resource instead of workspace change listener 
		if(eir.isDeleteInProgress()){
			return;
		}
		
		synchronized (resourcesLock) {
			if(!resources.containsKey(eir.getResource())){
				return;
			}
			resources.remove(eir.getResource());			
		}
		
		if (eir instanceof IValidationStatusProvider)
		{
			StatusManager.getInstance().removeStatusProvider((IValidationStatusProvider) eir);
		}
				
		firePropertyChange(PROP_RESOURCE_REMOVED, eir, null);
	}

	public synchronized void addListener(PropertyChangeListener listener)
	{
		pcs.addPropertyChangeListener(listener);
	}

	public synchronized void removeListener(PropertyChangeListener listener)
	{
		pcs.removePropertyChangeListener(listener);
	}

	private void firePropertyChange(final String prop, final Object oldValue, final Object newValue)
	{
		Display.getDefault().syncExec(new Runnable()
		{
			@Override
			public void run()
			{
				pcs.firePropertyChange(prop, oldValue, newValue);
			}
		});
	}
}
