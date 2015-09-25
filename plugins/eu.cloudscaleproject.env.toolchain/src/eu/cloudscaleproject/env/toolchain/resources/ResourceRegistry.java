package eu.cloudscaleproject.env.toolchain.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ResourceRegistry {
	
	private static final Logger logger = Logger.getLogger(ResourceRegistry.class.getName());
	
	private static ResourceRegistry instance = null;
	public static ResourceRegistry getInstance(){
		if(instance == null){
			instance = new ResourceRegistry();
		}
		
		//dispose unused resource providers
		List<IFolder> folders = new ArrayList<IFolder>(instance.resourceProviders.keySet());
		for(IFolder folder : folders){
			if(!folder.exists()){
				ResourceProvider rp = instance.resourceProviders.get(folder);
				instance.removeProvider(rp);
			}
		}
		
		return instance;
	}
	
	public static class ResourceExtensionItem{
		
		public final String id;
		public final String editorId;
		public final IResourceProviderFactory factory;
		
		public ResourceExtensionItem(String id, String editorId, IResourceProviderFactory factory) {
			this.id = id;
			this.editorId = editorId;
			this.factory = factory;
		}
		
	}
	
	private HashMap<String, ResourceExtensionItem> resourceExtensionItems 
											= new HashMap<String, ResourceExtensionItem>();
	
	private HashMap<IFolder, ResourceProvider> resourceProviders 
											= new HashMap<IFolder, ResourceProvider>();
	private HashMap<ResourceProvider, String> resourceProviderIds
											= new HashMap<ResourceProvider, String>();
	
	public ResourceRegistry() {
		
		//register factories from extension points
		for(IConfigurationElement el : ToolchainExtensions.getInstance().getToolChildElements()){
			if(el.getName().equals("resource")){
				try {
					String id = el.getAttribute("id");
					String editorId = el.getAttribute("editor");
					Object o = el.createExecutableExtension("class");
					
					ResourceExtensionItem rei = new ResourceExtensionItem(id, editorId, (IResourceProviderFactory)o);
					resourceExtensionItems.put(id, rei);
					
					logger.info("Resource extension registered uder toolchainID: " + id);

				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	private synchronized void addProvider(String id, ResourceProvider rp){
		IFolder folder = rp.getRootFolder();
		resourceProviders.put(folder, rp);
		resourceProviderIds.put(rp, id);
	}
	
	private synchronized void removeProvider(ResourceProvider rp){
		IFolder folder = rp.getRootFolder();
		resourceProviders.remove(folder);
		resourceProviderIds.remove(rp);
	}
	
	private synchronized ResourceProvider createProvider(IProject project, String id){
		
		ResourceProvider resourceProvider = null;
		IFolder folder = ToolchainUtils.getToolFolder(project, id);
		
		ResourceExtensionItem resourceExtension = resourceExtensionItems.get(id);
		if(resourceExtension != null){
			resourceProvider = resourceExtension.factory.create(folder);
		}
				
		return resourceProvider;
	}
	
	private synchronized void collectResourceProviders(IProject project){
		for(Entry<String, ResourceExtensionItem> entry : resourceExtensionItems.entrySet()){
			String id = entry.getKey();
			
			if(resourceProviderIds.values().contains(id)){
				continue;
			}
			
			ResourceProvider rp = createProvider(project, id);
			if(rp != null){
				addProvider(id, rp);
			}
		}
	}
	
	public List<ResourceExtensionItem> getResourceExtensionItems(){
		return new ArrayList<ResourceExtensionItem>(resourceExtensionItems.values());
	}
	
	public ResourceExtensionItem getResourceExtensionItem(String id){
		return resourceExtensionItems.get(id);
	}
	
	public synchronized List<ResourceProvider> getResourceProviders(IProject project){
		
		collectResourceProviders(project);
		
		List<ResourceProvider> out = new ArrayList<ResourceProvider>();
		
		for(Entry<IFolder, ResourceProvider> entry : resourceProviders.entrySet()){
			if(project.equals(entry.getKey().getProject())){
				out.add(entry.getValue());
			}
		}
		
		return out;
	}
	
	public synchronized String getResourceProviderID(ResourceProvider rp){
		collectResourceProviders(rp.getProject());
		return resourceProviderIds.get(rp);
	}
	
	/**
	 * Retrieves 'ResourceProvider' from this registry, or creates it (if it does not exist jet) using registered 'IResourceProviderFactory'.
	 * If the 'ResourceProviderFactory' is not registered for the specified 'id', this method returns null.
	 * 
	 * @param project IProject used for retrieving/creating 'ResourceProvider' root folder.
	 * @param toolchainID String that should be specified in 'ToolchainUtils' class.
	 * @return ResourceProvider
	 */
	public synchronized ResourceProvider getResourceProvider(IProject project, String id){
				
		IFolder folder = ToolchainUtils.getToolFolder(project, id);
		if(folder == null){
			String msg = "ResourceProvider root folder can not be retrieved! ID: " + id;
			logger.severe(msg);
			throw new IllegalArgumentException(msg);
		}

		ResourceProvider resourceProvider = resourceProviders.get(folder);
		
		if(resourceProvider == null){
			resourceProvider = createProvider(project, id);
			
			if(resourceProvider == null){
				String msg = "ResourceProviderFactory returned NULL! ID: " + id;
				logger.severe(msg);
				throw new IllegalArgumentException(msg);
			}
			
			addProvider(id, resourceProvider);
		}
		
		//resource factory should not return null resource
		//note: this method can return null resource provider, if the resource factory is not registered for the specified ID!
		assert(resourceProvider != null);
		return resourceProvider;		
	}
	
	/**
	 * Retrieves 'ResourceProvider' from this registry, or creates it (if it does not exist jet) using registered 'IResourceProviderFactory'.
	 * If the 'ResourceProviderFactory' is not registered for the specified tool, this method returns null.
	 * 
	 * @param project IProject used for retrieving/creating 'ResourceProvider' root folder.
	 * @param tool
	 * @return ResourceProvider
	 */
	public synchronized ResourceProvider getResourceProvider(IProject project, CSTool tool){
		return getResourceProvider(project, tool.getID());
	}
	
	public IEditorInputResource getResource(String resourcePath){
		IPath path = Path.fromPortableString(resourcePath);
		IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
		if(resource == null){
			logger.warning("IResource for the specified path does not exist! Path: " + resourcePath);
			return null;
		}
		
		List<ResourceProvider> providers = ResourceRegistry.getInstance().getResourceProviders(resource.getProject());
		for(ResourceProvider rp : providers){
			
			if(!rp.getRootFolder().getFullPath().isPrefixOf(path)){
				continue;
			}
			return rp.getResource(resource);
		}
		logger.warning("IEditorInputResource for the specified path can not be retrieved! Path: " + resourcePath);
		return null;
	}
	
	public synchronized void openResourceEditor(final IEditorInputResource eir){
		
		if(eir == null){
			logger.severe("Can not open editor! Specified editor resource is NULL!");
			return;
		}
		
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				IEclipseContext staticContext = EclipseContextFactory.create();
				staticContext.set(IEditorInputResource.class, eir);
				CommandExecutor.getInstance().execute("eu.cloudscaleproject.env.toolchain.openAlternative", staticContext);
				staticContext.dispose();
			}
		});
	}
	
}
