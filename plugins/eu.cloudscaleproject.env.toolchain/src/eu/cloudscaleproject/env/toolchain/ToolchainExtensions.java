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
	
	private List<IConfigurationElement> toolElements = new ArrayList<IConfigurationElement>();
	private List<IConfigurationElement> toolChildElements = new ArrayList<IConfigurationElement>();
		
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

	public List<IConfigurationElement> getToolChildElements(){
		return toolChildElements;
	}
	
	public List<IConfigurationElement> getToolChildElements(String toolID){
		
		List<IConfigurationElement> elements = new ArrayList<IConfigurationElement>();
		
		for(IConfigurationElement el : toolChildElements){
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
							toolChildElements.add(child);
						}
											
					}
				}
			}
			
			Collections.sort(toolChildElements, comparator);
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
