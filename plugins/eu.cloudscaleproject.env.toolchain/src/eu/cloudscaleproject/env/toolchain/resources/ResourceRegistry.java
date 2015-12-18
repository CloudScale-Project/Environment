package eu.cloudscaleproject.env.toolchain.resources;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeListener;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeNotifier;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.services.IExplorerService;

public class ResourceRegistry {
	
	private static final Logger logger = Logger.getLogger(ResourceRegistry.class.getName());
	
	public static final String PROJECT_RESOURCE_REGISTRY_ADDED = "eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry.projectResourceRegistryAdded";
	public static final String PROJECT_RESOURCE_REGISTRY_REMOVED = "eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry.projectResourceRegistryRemoved";
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private static ResourceRegistry instance = null;
	public static ResourceRegistry getInstance(){
		if(instance == null){
			instance = new ResourceRegistry();
		}
		return instance;
	}
	
	private final ExplorerChangeListener ecl = new ExplorerChangeListener() {
		
		@Override
		public void resourceChanged(IResourceDelta delta) {
						
			for (IResourceDelta rootDelta : delta.getAffectedChildren())
			{
				IResource resource = rootDelta.getResource();
				
				if(rootDelta.getKind() == IResourceDelta.ADDED){
					if(resource instanceof IProject){
						
						IProject project = (IProject)resource;
						if(ExplorerProjectPaths.isCloudScaleProject(project)){
							addProjectResourceRegistry(project);
						}
						
					}
				}
				if(rootDelta.getKind() == IResourceDelta.REMOVED){
					if(resource instanceof IProject){
						
						IProject project = (IProject)resource;
						removeProjectResourceRegistry(project);
					}
				}

				if(rootDelta.getKind() == IResourceDelta.CHANGED){
					if(resource instanceof IProject){
						
						IProject project = (IProject)resource;
						if(ExplorerProjectPaths.isCloudScaleProject(project)){
							if(!projectResourceRegistryMap.containsKey(project)){
								addProjectResourceRegistry(project);
							}
						}
						
					}
				}
			}
		}
		
		@Override
		public IResource[] getResources() {
			return new IResource[]{ResourcesPlugin.getWorkspace().getRoot()};
		}
	};
	
	private ConcurrentHashMap<IProject, ProjectResourceRegistry> projectResourceRegistryMap 
															= new ConcurrentHashMap<IProject, ProjectResourceRegistry>();
	
	public ResourceRegistry() {
		
	}
	
	public void initialize(){
		
		for(IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()){
			if(ExplorerProjectPaths.isCloudScaleProject(project)){
				addProjectResourceRegistry(project);
			}
			
		}
		
		ExplorerChangeNotifier.getInstance().addListener(ecl);
	}
	
	private void addProjectResourceRegistry(IProject project){
		
		ProjectResourceRegistry prr = new ProjectResourceRegistry(project);
		ProjectResourceRegistry prrOld = projectResourceRegistryMap.get(project);
		
		projectResourceRegistryMap.put(project, prr);
		
		pcs.firePropertyChange(PROJECT_RESOURCE_REGISTRY_ADDED, prrOld, prr);
	}
	
	private void removeProjectResourceRegistry(IProject project){
		
		ProjectResourceRegistry prrOld = projectResourceRegistryMap.get(project);
		if(prrOld != null){
			prrOld.dispose();
		}
		projectResourceRegistryMap.remove(project);
		pcs.firePropertyChange(PROJECT_RESOURCE_REGISTRY_REMOVED, prrOld, null);
	}
	
	public List<ProjectResourceRegistry> getProjectResourceRegistries(){
		return new ArrayList<ProjectResourceRegistry>(projectResourceRegistryMap.values());
	}
	
	public ProjectResourceRegistry getProjectResourceRegistry(IProject project){
		return projectResourceRegistryMap.get(project);
	}
	
	public List<ResourceProvider> getResourceProviders(IProject project){
		
		ProjectResourceRegistry prr = projectResourceRegistryMap.get(project);
		return prr.getResourceProviders();
	}
	
	/**
	 * Retrieves 'ResourceProvider' from this registry, or creates it (if it does not exist jet) using registered 'IResourceProviderFactory'.
	 * If the 'ResourceProviderFactory' is not registered for the specified 'id', this method returns null.
	 * 
	 * @param project IProject used for retrieving/creating 'ResourceProvider' root folder.
	 * @param toolchainID String that should be specified in 'ToolchainUtils' class.
	 * @return ResourceProvider
	 */
	public ResourceProvider getResourceProvider(IProject project, String id){
		
		ProjectResourceRegistry prr = projectResourceRegistryMap.get(project);
		if(prr == null){
			return null;
		}
		
		return prr.getResourceProvider(id);
	}
	
	/**
	 * Retrieves 'ResourceProvider' from this registry, or creates it (if it does not exist jet) using registered 'IResourceProviderFactory'.
	 * If the 'ResourceProviderFactory' is not registered for the specified tool, this method returns null.
	 * 
	 * @param project IProject used for retrieving/creating 'ResourceProvider' root folder.
	 * @param tool
	 * @return ResourceProvider
	 */
	public ResourceProvider getResourceProvider(IProject project, CSToolResource tool){
		
		ProjectResourceRegistry prr = projectResourceRegistryMap.get(project);
		if(prr == null){
			return null;
		}
		
		return prr.getResourceProvider(tool);
	}

	public IEditorInputResource findResource(String resourcePath){
		IPath path = Path.fromPortableString(resourcePath);
		IResource resource = ResourcesPlugin.getWorkspace().getRoot().getFolder(path);
		if(!resource.exists()){
			resource = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		}
		IProject project = resource.getProject();
		
		if(project == null){
			logger.severe("Resource does not have project! Path: " + resourcePath);
		}
		
		ProjectResourceRegistry prr = projectResourceRegistryMap.get(resource.getProject());
		if(prr == null){
			return null;
		}
		
		return prr.findResource(resource);		
	}
	
	public IEditorInputResource findResource(IResource resource){

		if(resource == null){
			logger.warning("IResource is null!");
			return null;
		}

		if(!resource.exists()){
			logger.warning("IResource does not exist! Path: " + resource.getFullPath());
			return null;
		}
		
		ProjectResourceRegistry prr = projectResourceRegistryMap.get(resource.getProject());
		if(prr == null){
			return null;
		}
		
		return prr.findResource(resource);
	}
	
	public synchronized void openResourceEditor(final IEditorInputResource eir){

		if(eir == null){
			logger.severe("Can not open editor! Specified editor resource is NULL!");
			return;
		}
		
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				IExplorerService explorerService = CloudscaleContext.getGlobalContext().get(IExplorerService.class);
				explorerService.setSelection(eir);

				IEclipseContext staticContext = EclipseContextFactory.create();
				staticContext.set(IEditorInputResource.class, eir);
				CommandExecutor.getInstance().execute("eu.cloudscaleproject.env.toolchain.openAlternative", staticContext);
				staticContext.dispose();

			}
		});
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
