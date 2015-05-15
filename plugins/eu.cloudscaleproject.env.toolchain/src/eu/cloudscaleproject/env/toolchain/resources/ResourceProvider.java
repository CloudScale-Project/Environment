package eu.cloudscaleproject.env.toolchain.resources;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

	private final LinkedHashMap<IResource, IEditorInputResource> resources = new LinkedHashMap<IResource, IEditorInputResource>();

	// private transient HashMap<String, IEditorInputResource> taggedResources =
	// new HashMap<String, IEditorInputResource>();

	protected abstract boolean validateResource(IResource res);

	protected abstract IResource createResource(String resourceName);

	protected abstract IEditorInputResource loadResource(IResource res, String type);

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private final ExplorerChangeListener ecl = new ExplorerChangeListener()
	{

		@Override
		public void resourceChanged(IResourceDelta delta)
		{
			boolean modified = ResourceProvider.this.reloadResources();
			if (!modified)
			{
				synchronized (ResourceProvider.this)
				{
					for (IResource res : resources.keySet())
					{
						if (delta.findMember(res.getFullPath()) != null)
						{
							firePropertyChange(PROP_RESOURCE_MODIFIED, null, resources.get(res));
						}
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

		reloadResources();
		ExplorerChangeNotifier.getInstance().addListener(ecl);
	}

	public synchronized void dispose()
	{
		ExplorerChangeNotifier.getInstance().removeListener(ecl);
	}

	public synchronized void tagResource(String tag, IEditorInputResource resource)
	{
		IProject project = rootFolder.getProject();

		// save tags into project persisted storage
		QualifiedName key = new QualifiedName(rootFolder.getLocationURI().toString(), tag);
		try
		{
			project.setPersistentProperty(key, resource != null ? resource.getResource().getName() : "");
		} catch (CoreException e)
		{
			e.printStackTrace();
		}
	}

	public synchronized boolean hasTag(String tag, IEditorInputResource resource)
	{
		IProject project = rootFolder.getProject();

		QualifiedName key = new QualifiedName(rootFolder.getLocationURI().toString(), tag);
		try
		{
			String tagedResourceName = project.getPersistentProperties().get(key);
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

	public synchronized IEditorInputResource getTaggedResource(String tag)
	{
		IProject project = rootFolder.getProject();

		QualifiedName key = new QualifiedName(rootFolder.getLocationURI().toString(), tag);
		try
		{
			String tagedResourceName = project.getPersistentProperties().get(key);
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

	public synchronized final boolean reloadResources()
	{

		boolean modified = checkRootFolder();

		// remove missing
		Iterator<IResource> iter = resources.keySet().iterator();
		while (iter.hasNext())
		{
			IResource res = iter.next();
			if (!res.exists())
			{

				IEditorInputResource eir = resources.get(res);
				if (eir instanceof IValidationStatusProvider)
				{
					StatusManager.getInstance().removeStatusProvider((IValidationStatusProvider) eir);
				}
				iter.remove();

				firePropertyChange(PROP_RESOURCE_REMOVED, eir, null);
				modified = true;
			}
		}

		if (!rootFolder.exists())
		{
			return modified;
		}

		// add new
		try
		{
			for (IResource res : rootFolder.members())
			{
				if (resources.containsKey(res))
				{
					continue;
				}

				if (res.exists() && validateResource(res))
				{
					addResource(res);
					modified = true;
				}
			}
		} catch (CoreException e)
		{
			e.printStackTrace();
		}

		return modified;
	}

	private void addResource(IResource res)
	{
		String type = null;
		if (res instanceof IFile && "prop".equals(res.getFileExtension()))
		{
			EditorInputFile tmp = new EditorInputFile(res.getProject(), (IFile) res);
			tmp.load();
			type = tmp.getProperty(PROP_TYPE);
		} else if (res instanceof IFolder)
		{
			EditorInputFile tmp = new EditorInputFile(res.getProject(), ((IFolder) res).getFile(EditorInputFolder.PROP_FILENAME));
			tmp.load();
			type = tmp.getProperty(PROP_TYPE);
		}

		IEditorInputResource prop = loadResource(res, type);

		resources.put(prop.getResource(), prop);
		if (prop instanceof IValidationStatusProvider)
		{
			StatusManager.getInstance().addStatusProvider(prop.getResource().getProject(), (IValidationStatusProvider) prop);
		}

		firePropertyChange(PROP_RESOURCE_ADDED, null, prop);
	}

	public IFolder getRootFolder()
	{
		return this.rootFolder;
	}

	public IProject getProject()
	{
		return this.rootFolder.getProject();
	}

	public synchronized List<IEditorInputResource> getResources()
	{
		return new ArrayList<IEditorInputResource>(resources.values());
	}

	public synchronized IEditorInputResource getResource(String resourceName)
	{

		if (resourceName == null)
		{
			return null;
		}

		for (IEditorInputResource prop : resources.values())
		{
			if (resourceName.equals(prop.getResource().getName()))
			{
				return prop;
			}
		}
		return null;
	}

	public synchronized IEditorInputResource getResourceByName(String name)
	{
		for (IEditorInputResource prop : resources.values())
		{
			if (name.equals(prop.getName()))
			{
				return prop;
			}
		}
		return null;
	}

	public synchronized IEditorInputResource getResource(IResource resource)
	{
		if (resource == null)
		{
			return null;
		}

		for (IEditorInputResource prop : resources.values())
		{
			if (resource.getName().equals(prop.getResource().getName()))
			{
				return prop;
			}
		}
		return null;
	}

	public synchronized boolean hasResource(String resourceName)
	{
		IEditorInputResource prop = getResource(resourceName);
		return prop == null ? false : true;
	}

	public synchronized IEditorInputResource createNewResource(String resourceName, String name, String type)
	{

		checkRootFolder();

		IResource res = createResource(resourceName);
		IEditorInputResource eir = loadResource(res, type);
		eir.setProperty(PROP_TYPE, type);
		eir.setName(name);


		eir.save();

		addResource(res);
		firePropertyChange(PROP_RESOURCE_CREATED, null, eir);

		return eir;
	}

	private final SimpleDateFormat result_postix_format = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

	public synchronized IEditorInputResource createNewResource(String name, String type)
	{
		checkRootFolder();

		String resourceName = defaultResName + "_" + result_postix_format.format(new Date());
		return createNewResource(resourceName, name, type);
	}

	public synchronized void deleteResource(String resourceName)
	{
		IEditorInputResource resource = getResource(resourceName);
		resource.delete();

		firePropertyChange(PROP_RESOURCE_DELETED, resource, null);
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
		Display.getDefault().asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				pcs.firePropertyChange(prop, oldValue, newValue);
			}
		});
	}
}
