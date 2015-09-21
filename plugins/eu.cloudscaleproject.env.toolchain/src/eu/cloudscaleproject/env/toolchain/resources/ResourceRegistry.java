package eu.cloudscaleproject.env.toolchain.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ToolchainExtensions;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ResourceRegistry {
	
	private static final Logger logger = Logger.getLogger(ResourceRegistry.class.getName());
	
	private static final String FOLDER_RESOURCE_PROVIDER_ID 
		= "eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry.defaultFolderResourceFactory";
	private static final String FILE_RESOURCE_PROVIDER_ID 
		= "eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry.defaultFileResourceFactory";
	
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
	
	private static class ResourceExtensionItem{
		
		@SuppressWarnings("unused")
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
		//register basic resource provider factories
		//registerFactory(FOLDER_RESOURCE_PROVIDER_ID, new FolderResourceProviderFactory(FOLDER_RESOURCE_PROVIDER_ID));
		//registerFactory(FILE_RESOURCE_PROVIDER_ID, new FileResourceProviderFactory(FILE_RESOURCE_PROVIDER_ID));
		
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
	
	public synchronized void openResourceEditor(final IEditorInputResource eir){
		
		if(eir == null){
			logger.severe("Can not open editor! Specified editor resource is NULL!");
			return;
		}
		
		for(ResourceProvider rp : resourceProviders.values()){
			if(rp.getResources().contains(eir)){
				final String editorPartID = resourceExtensionItems.get(resourceProviderIds.get(rp)).editorId;
				if(editorPartID != null && !editorPartID.isEmpty()){
					Display.getDefault().asyncExec(new Runnable() {
						
						@Override
						public void run() {
							doOpenResourceEditor(eir, editorPartID);
						}
					});
				}
			}
		}
	}
	
	private void doOpenResourceEditor(IEditorInputResource eir, String editorPartID){
		
		MApplication application = CloudscaleContext.getGlobalContext().get(MApplication.class);
		EModelService modelService = CloudscaleContext.getGlobalContext().get(EModelService.class);
		EPartService partService = CloudscaleContext.getGlobalContext().get(EPartService.class);
		
		MPart part = partService.findPart(editorPartID);
		
		if(part == null){
			MPartStack stack = (MPartStack)modelService.find("org.eclipse.e4.primaryDataStack", application);
			if(stack != null){
				part = partService.createPart(editorPartID);
				stack.getChildren().add(part);
			}
		}
		
		partService.showPart(part, PartState.ACTIVATE);
		
		//fill in context data
		IEclipseContext context = part.getContext();
		context.set(eir.getClass().getName(), eir);
		
		/*
		 * TODO: find out where this values are used and how can this code be avoided!
		 * 
		 * ExplorerEditorNode.class : used for editor <-> explorer link action
		 * IResource.class : ??
		 * 
		IResource resource = (IResource)getContext().get(IExplorerConstants.NODE_RESOURCE);
		if(resource != null){
			context.set(IResource.class, resource);
		}
		
		context.set(ExplorerEditorNode.class, this);
		*/
	}
	
	public synchronized List<ResourceProvider> getResourceProviders(IProject project){
		
		List<ResourceProvider> out = new ArrayList<ResourceProvider>();
		
		for(Entry<IFolder, ResourceProvider> entry : resourceProviders.entrySet()){
			if(project.equals(entry.getKey().getProject())){
				out.add(entry.getValue());
			}
		}
		
		return out;
	}
	
	public synchronized String getResourceProviderID(ResourceProvider rp){
		return resourceProviderIds.get(rp);
	}
	
	/**
	 * Retrieves 'ResourceProvider' from this registry, or creates it (if it does not exist jet),
	 * using registered 'IResourceProviderFactory'.
	 * If the 'ResourceProviderFactory' is not registered for the specified id, this method returns null.
	 * Returned 'ResourceProvider' root folder is equal to the specified attribute 'folder'. 
	 * 
	 * @param id ResourceProviderFactory id.
	 * @param folder The root folder of the returned 'ResourceProvider'. 
	 * @return ResourceProvider
	 */
	public synchronized ResourceProvider getResourceProvider(String id, IFolder folder){
		
		ResourceProvider resourceProvider = resourceProviders.get(folder);
		
		if(resourceProvider == null){
			ResourceExtensionItem resourceExtension = resourceExtensionItems.get(id);
			if(resourceExtension == null){
				//resource factory is not registered for the specified tool ID
				return null;
			}
			else{
				resourceProvider = resourceExtension.factory.create(folder);
				addProvider(id, resourceProvider);
			}			
		}
		
		//resource factory should not return null resource
		//note: this method can return null resource provider, if the resource factory is not registered for the specified ID!
		assert(resourceProvider != null);
		return resourceProvider;
	}
	
	/**
	 * Returns 'ResourceProvider', created from default 'FolderResourceProviderFactory'
	 * This is convenient method for creating/retrieving 'ResourceProvider'.  
	 * 
	 * @param folder The root folder of the returned 'ResourceProvider'.
	 * @return ResourceProvider, with root folder equal to specified attribute 'folder' 
	 */
	public synchronized ResourceProvider getFolderResourceProvider(IFolder folder){
		return getResourceProvider(FOLDER_RESOURCE_PROVIDER_ID, folder);
	}
	
	/**
	 * Returns 'ResourceProvider', created from default 'FileResourceProviderFactory'
	 * This is convenient method for creating/retrieving 'ResourceProvider'.  
	 * 
	 * @param folder The root folder of the returned 'ResourceProvider'.
	 * @return ResourceProvider, with root folder equal to specified attribute 'folder' 
	 */
	public synchronized ResourceProvider getFileResourceProvider(IFolder folder){
		return getResourceProvider(FILE_RESOURCE_PROVIDER_ID, folder);
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
		
		//Check if the resource extension is registered for the specified toolchainID
		//If it is not, we don't want to call method 'ToolchainUtils.getToolFolder(project, toolchainID)',
		//because this method creates folders if they do not exist jet!
		ResourceExtensionItem rei = resourceExtensionItems.get(id);
		if(rei == null){
			return null;
		}
		
		return getResourceProvider(id, ToolchainUtils.getToolFolder(project, id));
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
		
		//Check if the resource extension is registered for the specified toolchainID
		//If it is not, we don't want to call method 'ToolchainUtils.getToolFolder(project, toolchainID)',
		//because this method creates folders if they do not exist jet!
		ResourceExtensionItem rei = resourceExtensionItems.get(tool.getID());
		if(rei == null){
			return null;
		}
		
		return getResourceProvider(tool.getID(), ToolchainUtils.getToolFolder(project, tool.getID()));
	}
	
}
