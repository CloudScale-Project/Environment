package eu.cloudscaleproject.env.toolchain.resources;

import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class ResourceRegistry {
	
	private static final Logger logger = Logger.getLogger(ResourceRegistry.class.getName());
	
	private static final String FOLDER_RESOURCE_PROVIDER_ID = "eu.cloudscaleproject.env.toolchain.util.SidebarResourceRegistry.folder";
	private static final String FILE_RESOURCE_PROVIDER_ID = "eu.cloudscaleproject.env.toolchain.util.SidebarResourceRegistry.file";
	
	private static ResourceRegistry instance = null;
	public static ResourceRegistry getInstance(){
		if(instance == null){
			instance = new ResourceRegistry();
		}
		
		//dispose unused resource providers
		Iterator<IFolder> iter = instance.resourceProviders.keySet().iterator();
		while(iter.hasNext()){
			IFolder folder = iter.next();
			if(!folder.exists()){
				instance.resourceProviders.get(folder).dispose();
				iter.remove();
			}
		}
		
		return instance;
	}
	
	private HashMap<String, IResourceProviderFactory> resourceProviderFactories 
											= new HashMap<String, IResourceProviderFactory>();
	private HashMap<IFolder, ResourceProvider> resourceProviders 
											= new HashMap<IFolder, ResourceProvider>();
	
	public ResourceRegistry() {
		//register basic resource provider factories
		registerFactory(FOLDER_RESOURCE_PROVIDER_ID, new FolderResourceProviderFactory());
		registerFactory(FILE_RESOURCE_PROVIDER_ID, new FileResourceProviderFactory());
	}
	
	public void registerFactory(String id, IResourceProviderFactory factory){
		resourceProviderFactories.put(id, factory);
	}
	
	public ResourceProvider getResourceProvider(String id, IFolder folder){
		
		ResourceProvider resourceProvider = resourceProviders.get(folder);
		
		if(resourceProvider == null){
			IResourceProviderFactory resourceFactory = resourceProviderFactories.get(id);
			if(resourceFactory == null){
				logger.severe("Resource factory is not registered for the id:" + id);
			}
			else{
				resourceProvider = resourceFactory.create(folder);
				resourceProviders.put(folder, resourceProvider);
			}			
		}
		
		assert(resourceProvider != null);
		return resourceProvider;
	}
	
	public ResourceProvider getFolderResourceProvider(IFolder folder){
		return getResourceProvider(FOLDER_RESOURCE_PROVIDER_ID, folder);
	}
	
	public ResourceProvider getFileResourceProvider(IFolder folder){
		return getResourceProvider(FILE_RESOURCE_PROVIDER_ID, folder);
	}
	
	public ResourceProvider getResourceProvider(IProject project, String toolchainID){
		return getResourceProvider(toolchainID, ToolchainUtils.getToolFolder(project, toolchainID));
	}

}
