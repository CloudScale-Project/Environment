package eu.cloudscaleproject.env.toolchain;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildrenProvider;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class Extensions {

	private static Extensions instance = null;
	public static Extensions getInstance(){
		if(instance == null){
			instance = new Extensions();
		}
		return instance;
	}
	
	private List<IConfigurationElement> toolElements = new ArrayList<IConfigurationElement>();
	private List<IConfigurationElement> resourceProviderFactoryElements = new ArrayList<IConfigurationElement>();
	
	private List<IExplorerNodeChildrenProvider> nodeChildrenProviders = new ArrayList<IExplorerNodeChildrenProvider>();
	
	public List<IConfigurationElement> getToolElements(){
		return toolElements;
	}
	
	public IConfigurationElement getToolElement(String id){
		for(IConfigurationElement el : toolElements){
			if(id.equals(el.getAttribute("id"))){
				return el;
			}
		}
		return null;
	}

	public List<IConfigurationElement> getResourceProviderFactoryElements(){
		return resourceProviderFactoryElements;
	}
	
	public IConfigurationElement getResourceProviderFactoryElement(String id){
		for(IConfigurationElement el : resourceProviderFactoryElements){
			if(id.equals(el.getAttribute("id"))){
				return el;
			}
		}
		return null;
	}
	
	public List<IExplorerNodeChildrenProvider> getNodeChildrenProviders(){
		return nodeChildrenProviders;
	}
	
	public List<IConfigurationElement> getResourceProviderFactoryElements(String toolID){
		
		List<IConfigurationElement> elements = new ArrayList<IConfigurationElement>();
		
		for(IConfigurationElement el : resourceProviderFactoryElements){
			IConfigurationElement parent = (IConfigurationElement)el.getParent();
			
			String id = parent.getAttribute("id");
			if(toolID.equals(id)){
				elements.add(el);
			}
		}
		return elements;
	}
	
	public void retrieveExtensions(){
		
		
		//retrieve tool extensions
		{
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IExtensionPoint point = registry.getExtensionPoint("eu.cloudscaleproject.env.toolchain.tool");
					
			for(IExtension extension : point.getExtensions()){
				for(IConfigurationElement el : extension.getConfigurationElements()){
					if(el.getName().equals("tool")){
						
						toolElements.add(el);
						
						for(IConfigurationElement child : el.getChildren()){
							if(child.getName().equals("resource")){
	
								resourceProviderFactoryElements.add(child);
								
							}
						}
											
					}
				}
			}
		}
		
		//retrieve explorer extensions
		{
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IExtensionPoint point = registry.getExtensionPoint("eu.cloudscaleproject.env.toolcahin.explorer");
					
			for(IExtension extension : point.getExtensions()){
				for(IConfigurationElement el : extension.getConfigurationElements()){
					if(el.getName().equals("children")){
						
						try {
							Object o = el.createExecutableExtension("class");
							if(o instanceof IExplorerNodeChildrenProvider){
								nodeChildrenProviders.add((IExplorerNodeChildrenProvider)o);
							}
						} catch (CoreException e) {
							e.printStackTrace();
						}
						
					}
				}
			}
		}
		
	}
	
}
