package eu.cloudscaleproject.env.toolchain.resources;

import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;

public class ResourceExtensions {
	
	private static final Logger logger = Logger.getLogger("eu.cloudscaleproject.env.toolchain.resources.ResourceExtensions"); 

	private static ResourceExtensions instance;
	public static ResourceExtensions getInstance(){
		if(instance == null){
			instance = new ResourceExtensions();
		}
		return instance;
	}
	
	private HashMap<String, ResourceExtensionItem> resourceExtensionItems = new HashMap<String, ResourceExtensionItem>();

	public ResourceExtensions() {
		
		//register factories from extension points
		for(IConfigurationElement el : ToolchainExtensions.getInstance().getResourceProviderFactoryElements()){
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
	
	public ResourceExtensionItem getResourceExtension(String id){
		return resourceExtensionItems.get(id);
	}
	
	public Collection<ResourceExtensionItem> getResourceExtensions(){
		return resourceExtensionItems.values();
	}
}
