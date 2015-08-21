package eu.cloudscaleproject.env.toolchain.explorer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerExtensions {

	private static ExplorerExtensions instance = null;
	public static ExplorerExtensions getInstance(){
		if(instance == null){
			instance = new ExplorerExtensions();
		}
		return instance;
	}
	
	private List<IExplorerNodeChildrenProvider> nodeChildrenProviders = new ArrayList<IExplorerNodeChildrenProvider>();
	
	public List<IExplorerNodeChildrenProvider> getNodeChildrenProviders(){
		return nodeChildrenProviders;
	}
	
	public List<IConfigurationElement> getResourceProviderFactoryElements(String toolID){
		
		List<IConfigurationElement> elements = new ArrayList<IConfigurationElement>();
		List<IConfigurationElement> resProviderFactoryElements = ToolchainExtensions.getInstance().getResourceProviderFactoryElements();
		
		for(IConfigurationElement el : resProviderFactoryElements){
			IConfigurationElement parent = (IConfigurationElement)el.getParent();
			
			String id = parent.getAttribute("id");
			if(toolID.equals(id)){
				elements.add(el);
			}
		}
		return elements;
	}
	
	public void retrieveExtensions(){
		
		//retrieve explorer extensions
		{
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IExtensionPoint point = registry.getExtensionPoint("eu.cloudscaleproject.env.toolchain.explorer");
					
			for(IExtension extension : point.getExtensions()){
				for(IConfigurationElement el : extension.getConfigurationElements()){
					if(el.getName().equals("nodes")){
						
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
