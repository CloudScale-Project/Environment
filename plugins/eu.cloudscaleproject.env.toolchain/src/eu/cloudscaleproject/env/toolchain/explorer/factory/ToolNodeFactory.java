package eu.cloudscaleproject.env.toolchain.explorer.factory;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeFactory;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ToolNodeFactory extends ExplorerNodeFactory{
	
	private final IProject project;

	public ToolNodeFactory(IProject project) {
		this.project = project;
	}

	@Override
	public List<? extends Object> getKeys() {
		
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint point = registry.getExtensionPoint("eu.cloudscaleproject.env.toolchain.tool");
		
		List<IConfigurationElement> toolElements = new ArrayList<IConfigurationElement>();
		
		for(IExtension extension : point.getExtensions()){
			for(IConfigurationElement el : extension.getConfigurationElements()){
				if(el.getName().equals("tool")){
					toolElements.add(el);
				}
			}
		}
		
		return toolElements;
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IConfigurationElement tool = (IConfigurationElement)key;
		
		String id = tool.getAttribute("toolID");
		String name = tool.getAttribute("toolName");
		//String iconResource = tool.getAttribute("toolIcon");
		
		List<CSTool> csTools = new ArrayList<CSTool>();
		
		for(IConfigurationElement resourceElement : tool.getChildren()){
			String resourceFactoryId = resourceElement.getAttribute("id");
			csTools.add(CSTool.getTool(resourceFactoryId));
		}
		
		IExplorerNode node = new ExplorerNode(id, name, new ToolResourceProviderNodeFactory(project, csTools));
		return node;
	}

}
