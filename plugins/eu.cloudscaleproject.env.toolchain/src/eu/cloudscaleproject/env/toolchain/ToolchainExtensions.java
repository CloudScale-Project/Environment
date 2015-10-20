package eu.cloudscaleproject.env.toolchain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolchainExtensions {

	private static ToolchainExtensions instance = null;
	public static ToolchainExtensions getInstance(){
		if(instance == null){
			instance = new ToolchainExtensions();
		}
		return instance;
	}
	
	public static final String TOOL_EXTENSION_NAME = "Tool";
	
	public static final String NODE_EXTENSION_NAME = "Node";
	public static final String NODE_PROXY_EXTENSION_NAME = "NodeProxy";
	public static final String NODE_RESOURCE_EXTENSION_NAME = "NodeResource";
	public static final String NODE_DYNAMIC_EXTENSION_NAME = "NodeDynamic";
	
	private List<IConfigurationElement> toolElements = new ArrayList<IConfigurationElement>();
	private List<IConfigurationElement> resourceProviderFactoryElements = new ArrayList<IConfigurationElement>();
	
	private List<IConfigurationElement> nodes = new ArrayList<IConfigurationElement>();
	private List<IConfigurationElement> proxyNodes = new ArrayList<IConfigurationElement>();

	public List<IConfigurationElement> getToolElements(){
		return new ArrayList<IConfigurationElement>(toolElements);
	}
	
	public List<IConfigurationElement> getNodeElements(){
		return new ArrayList<IConfigurationElement>(nodes);
	}
	
	public List<IConfigurationElement> findProxyNodes(IConfigurationElement element){
		
		List<IConfigurationElement> out = new ArrayList<IConfigurationElement>();
		
		for(IConfigurationElement el : proxyNodes){
			if(element.getAttribute("id").equals(el.getAttribute("id"))){
				out.add(el);
			}
		}
		return out;
	}
	
	public IConfigurationElement findNodeElement(String id){
		return doFindNodeElement(nodes.toArray(new IConfigurationElement[nodes.size()]), id);		
	}
	
	private IConfigurationElement doFindNodeElement(IConfigurationElement[] elements, String id){
		for(IConfigurationElement element : elements){
			if(id.equals(element.getAttribute("id"))){
				return element;
			}
			
			IConfigurationElement el = doFindNodeElement(element.getChildren(), id);
			if(el != null){
				return el;
			}
		}
		return null;
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
		return new ArrayList<IConfigurationElement>(resourceProviderFactoryElements);
	}
	
	public IConfigurationElement getResourceProviderFactoryElement(String id){
		
		for(IConfigurationElement el : resourceProviderFactoryElements){			
			if(id.equals(el.getAttribute("id"))){
				return el;
			}
		}
		
		return null;
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
	
	public IConfigurationElement findResourceProviderFactoryElement (String id)
	{
		for (IConfigurationElement e : resourceProviderFactoryElements)
		{
			if (id.equals(e.getAttribute("id")))
			{
				return e;
			}
		}
		
		return null;
	}
	
	public void retrieveExtensions(){
		
		//retrieve tool extensions
		{
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IExtensionPoint point = registry.getExtensionPoint("eu.cloudscaleproject.env.toolchain.tool");
					
			for(IExtension extension : point.getExtensions()){
				for(IConfigurationElement el : extension.getConfigurationElements()){
					if(el.getName().equals(TOOL_EXTENSION_NAME)){
						
						toolElements.add(el);
						
						for(IConfigurationElement child : el.getChildren()){
							resourceProviderFactoryElements.add(child);
						}
											
					}
					if(el.getName().equals(NODE_EXTENSION_NAME)){
						nodes.add(el);
					}
					if(el.getName().equals(NODE_PROXY_EXTENSION_NAME)){
						proxyNodes.add(el);
					}
				}
			}
			
			Collections.sort(nodes, comparator);
			Collections.sort(resourceProviderFactoryElements, comparator);
			Collections.sort(toolElements, comparator);
		}
	}
	
	private Comparator<IConfigurationElement> comparator = new Comparator<IConfigurationElement>() {

		@Override
		public int compare(IConfigurationElement o1, IConfigurationElement o2) {
			
			String o1P = o1.getAttribute("position");
			String o2P = o2.getAttribute("position");

			try{
				int o1Pos = Integer.parseInt(o1P);
				int o2Pos = Integer.parseInt(o2P);
				return (int)Math.signum(o1Pos - o2Pos);
			}
			catch(NumberFormatException e){
				return 0;
			}

		}
	};
	
}
