package eu.cloudscaleproject.env.toolchain.explorer.children;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNodeChildren;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.AlternativeNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.AlternativeResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.ConfigurationElementEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.nodes.ConfigurationElementNode;
import eu.cloudscaleproject.env.toolchain.resources.IExplorerContentRetriever;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfigurationElementNodeChildren extends ExplorerNodeChildren{
	
	private final PropertyChangeListener contentRetrieverListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(IExplorerContentRetriever.PROP_CHILDREN_CHANGED.equals(evt.getPropertyName())){
				refreshNow();
			}
		}
	};
		
	private IConfigurationElement element;
	private HashMap<IConfigurationElement, IExplorerContentRetriever> contentRetrievers 
													= new HashMap<IConfigurationElement, IExplorerContentRetriever>();
	
	public ConfigurationElementNodeChildren(IConfigurationElement element) {
		super(true);
		this.element = element;
	}
	
	private class Key {
		
		public final IConfigurationElement element;
		public final Object value;
		
		public Key(IConfigurationElement el, Object key) {
			this.element = el;
			this.value = key;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((element == null) ? 0 : element.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			
			if(obj instanceof Key){
				Key other = (Key)obj;
				
				return (value == null ? (other.value == null) : (value.equals(other.value))) 
						&& (element == null ? (other.element == null) : (element.equals(other.element)));
			}
			
			return false;
		}
	}

	@Override
	protected List<Object> getKeys() {
		
		List<Object> keys = new ArrayList<Object>();

		List<IConfigurationElement> proxyElements = ToolchainExtensions.getInstance().findExplorerProxyNodes(element);
		
		List<IConfigurationElement> children = new ArrayList<IConfigurationElement>();
		Collections.addAll(children, element.getChildren());
		
		for(IConfigurationElement proxy : proxyElements){
			Collections.addAll(children, proxy.getChildren());
		}
		
		for(IConfigurationElement el : children){
			
			if(ToolchainExtensions.NODE_EXTENSION_NAME.equals(el.getName())){
				keys.add(new Key(el, null));
			}
			
			if(ToolchainExtensions.NODE_RESOURCE_EXTENSION_NAME.equals(el.getName())){
				keys.add(new Key(el, null));
			}
			
			if(ToolchainExtensions.NODE_DYNAMIC_EXTENSION_NAME.equals(el.getName())){
				try {
					IExplorerContentRetriever contentRetriever = contentRetrievers.get(el);
					if(contentRetriever == null){
						contentRetriever = (IExplorerContentRetriever)el.createExecutableExtension("class");
						contentRetriever.initialize(el.getAttribute("id"), getNode().getContext());
						contentRetriever.addPropertyChangeListener(contentRetrieverListener);
						contentRetrievers.put(el, contentRetriever);
					}
					
					if(contentRetriever != null){
						
						List<Object> contentList = contentRetriever.getChildren();
						if(contentList.isEmpty()){
							
							String emptyNodeID = el.getAttribute("empty");
							if(emptyNodeID != null && !emptyNodeID.isEmpty()){
								keys.add(new Key(el, null));
							}
							
						}
						else{
							for(Object content : contentRetriever.getChildren()){
								keys.add(new Key(el, content));
							}
						}
						
					}
					
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
			
		return keys;
	}

	@Override
	public IExplorerNode getChild(Object key) {
		
		IConfigurationElement el = ((Key)key).element;
		Object value = ((Key)key).value;
		
		IExplorerNode node = null;
		
		if(ToolchainExtensions.NODE_EXTENSION_NAME.equals(el.getName())){

			IExplorerNodeChildren children = new ConfigurationElementNodeChildren(el);
			node = new ConfigurationElementNode(getNode().getContext(), el, children);
		}
		
		if(ToolchainExtensions.NODE_RESOURCE_EXTENSION_NAME.equals(el.getName())){

			IExplorerNodeChildren children = new ConfigurationElementNodeChildren(el);
			node = new ConfigurationElementEditorNode(getNode().getContext(), el, children);
		}
		
		if(ToolchainExtensions.NODE_DYNAMIC_EXTENSION_NAME.equals(el.getName())){
			
			if(value == null){
				String empty = el.getAttribute("empty");
				IConfigurationElement emptyElement = ToolchainExtensions.getInstance().findNodeElement(empty);
				if(emptyElement != null){
					ExplorerNodeChildren children = new ConfigurationElementNodeChildren(emptyElement);
					node = getChild(new Key(emptyElement, children));
				}
			}
			
			ExplorerNodeChildren children = new ConfigurationElementNodeChildren(el);
			
			if(value instanceof IEditorInputResource){
				node = new AlternativeNode(getNode().getContext(), el.getAttribute("editor"), (IEditorInputResource)value, children);
			}
			if(value instanceof IFile){
				node = new AlternativeResourceNode(getNode().getContext(), (IFile)value);
			}
			if(value instanceof IProject){
				node = new ConfigurationElementNode(getNode().getContext(), el, children); 
				node.setName(((IProject)value).getName());
			}

		}
		
		return node;
	}
	
	@Override
	public synchronized void dispose() {
		
		super.dispose();
		
		for(IExplorerContentRetriever cr : contentRetrievers.values()){
			cr.dispose();
		}
		contentRetrievers.clear();
	}

}
