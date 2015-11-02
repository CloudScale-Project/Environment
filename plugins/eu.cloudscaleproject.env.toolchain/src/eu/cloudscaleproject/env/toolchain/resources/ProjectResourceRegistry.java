package eu.cloudscaleproject.env.toolchain.resources;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ProjectResourceRegistry {
	
	private static final Logger logger = Logger.getLogger(ProjectResourceRegistry.class.getName());
	
	public static final String PROP_RESOURCE_PROVIDER_ADDED = "eu.cloudscaleproject.env.toolchain.resources.ProjectResourceRegistry.add"; 
	public static final String PROP_RESOURCE_PROVIDER_REMOVED = "eu.cloudscaleproject.env.toolchain.resources.ProjectResourceRegistry.remove"; 
	
	private HashMap<IFolder, ResourceProvider> resourceProviders = new HashMap<IFolder, ResourceProvider>();
	private HashMap<String, ResourceProvider> resourceProviderIds = new HashMap<String, ResourceProvider>();

	private final Object lock = new Object();
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private final IProject project;
	
	public ProjectResourceRegistry(IProject project) {
		this.project = project;
		
		for(ResourceExtensionItem re : ResourceExtensions.getInstance().getResourceExtensions()){
			String id = re.getID();
			
			final ResourceProvider rp = createProvider(id);
			if(rp != null){
				addResourceProvider(rp);
			}
		}
	}
	
	public IProject getProject(){
		return project;
	}
	
	private final void addResourceProvider(ResourceProvider rp){

		IFolder folder = rp.getRootFolder();
		ResourceProvider old;

		synchronized (lock) {
			old = resourceProviderIds.get(rp.getID());

			resourceProviders.put(folder, rp);
			resourceProviderIds.put(rp.getID(), rp);
		}
		
		pcs.firePropertyChange(PROP_RESOURCE_PROVIDER_ADDED, old, rp);
	}

	private final void removeResourceProvider(ResourceProvider rp){

		ResourceProvider old;

		synchronized (lock) {
			old = resourceProviderIds.get(rp.getID());

			resourceProviders.remove(rp.getRootFolder());
			resourceProviderIds.remove(rp.getID());
		}
		
		pcs.firePropertyChange(PROP_RESOURCE_PROVIDER_REMOVED, old, null);
	}
	
	private final ResourceProvider createProvider(String id){
		
		ResourceProvider resourceProvider = null;
		final IFolder folder = ToolchainUtils.getResourceProviderFolder(project, id);
		
		WorkspaceJob job = new WorkspaceJob("Create resource provider folder") {
			
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				if(!folder.exists()){
					try {
						ExplorerProjectPaths.prepareFolder(folder);
					} catch (CoreException e) {
						e.printStackTrace();
						return new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage());
					}
				}
				return Status.OK_STATUS;
			}
		};
		job.schedule();
		
		ResourceExtensionItem resourceExtension = ResourceExtensions.getInstance().getResourceExtension(id);
		if(resourceExtension != null){
			resourceProvider = resourceExtension.getResourceProviderFactory().create(id, folder);
		}
						
		return resourceProvider;
	}
	
	public List<ResourceProvider> getResourceProviders(){
		return new ArrayList<ResourceProvider>(resourceProviders.values());
	}
	
	/**
	 * Retrieves 'ResourceProvider' from this registry.
	 * 
	 * @param project IProject used for retrieving/creating 'ResourceProvider' root folder.
	 * @param toolchainID String that should be specified in 'ToolchainUtils' class.
	 * @return ResourceProvider
	 */
	public synchronized ResourceProvider getResourceProvider(String id){
		
		if(project == null || id == null){
			return null;
		}
		
		return resourceProviderIds.get(id);
	}
	
	/**
	 * Retrieves 'ResourceProvider' from this registry.
	 * 
	 * @param project IProject used for retrieving/creating 'ResourceProvider' root folder.
	 * @param tool
	 * @return ResourceProvider
	 */
	public synchronized ResourceProvider getResourceProvider(CSToolResource tool){
		
		if(project == null || tool == null){
			return null;
		}
		
		return getResourceProvider(tool.getID());
	}

	public IEditorInputResource getResource(String resourcePath){
		IPath path = Path.fromPortableString(resourcePath);
		IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
		
		if(resource == null){
			return null;
		}
		
		return getResource(resource);
	}
	
	public IEditorInputResource getResource(IResource resource){

		if(resource == null){
			logger.warning("IResource is null!");
			return null;
		}

		if(!resource.exists()){
			logger.warning("IResource does not exist! Path: " + resource.getFullPath());
			return null;
		}
		
		List<ResourceProvider> providers = getResourceProviders();
		for(ResourceProvider rp : providers){
			
			if(!rp.getRootFolder().getFullPath().isPrefixOf(resource.getFullPath())){
				continue;
			}
			return rp.getResource(resource);
		}

		logger.warning("IEditorInputResource for the specified path can not be retrieved! Path: " + resource.getFullPath());
		return null;
	}

	public void dispose(){
		
		for(ResourceProvider rp : new LinkedList<ResourceProvider>(resourceProviderIds.values())){
			removeResourceProvider(rp);
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);
	}

	public void addPropertyChangeListener(String prop, PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(prop, pcl);
	}

	public void removePropertyChangeListener(String prop, PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(prop, pcl);
	}
}
