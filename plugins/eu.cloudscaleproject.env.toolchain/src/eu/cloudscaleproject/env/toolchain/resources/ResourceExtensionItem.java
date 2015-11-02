package eu.cloudscaleproject.env.toolchain.resources;

public class ResourceExtensionItem {

	private final String id;
	private final String editorId;
	private final IResourceProviderFactory factory;
	
	public ResourceExtensionItem(String id, String editorId, IResourceProviderFactory factory) {
		this.id = id;
		this.editorId = editorId;
		this.factory = factory;
	}
	
	public String getID(){
		return id;
	}
	
	public String getEditorID(){
		return editorId;
	}
	
	public IResourceProviderFactory getResourceProviderFactory(){
		return factory;
	}
	
}
