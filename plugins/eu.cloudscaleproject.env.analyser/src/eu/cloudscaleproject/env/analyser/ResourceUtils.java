package eu.cloudscaleproject.env.analyser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ResourceUtils {
	
	public static final String ANALYSER_INPUT_GENERATED_RES_NAME = "overview.alt";
	public static final String ANALYSER_CONF_CAPACITY_ANALYSES = "Capacity analyses";

	
	public static InputAlternative getGeneratedResourceInput(IProject project){
		ResourceProvider resourceProvider = ResourceRegistry.getInstance()
												.getResourceProvider(project, CSTool.ANALYSER_INPUT);
		IEditorInputResource editorInput = resourceProvider.getResource(ANALYSER_INPUT_GENERATED_RES_NAME);
		
		if(editorInput == null){
			editorInput = resourceProvider.createNewResource(ANALYSER_INPUT_GENERATED_RES_NAME, "Generated input");
		}
		
		return (InputAlternative)editorInput;
	}
	
	/**
	 * 
	 * Retrieves Analyser capacity experiment configuration alternative resource for the specified project.
	 * If this alternative does not exist, it is created.
	 * 
	 * @param project Workbench project to retrieve <code>ResourceProvider</code> from.
	 * @return Default capacity experiment configuration alternative.
	 */
	/*
	public static ConfAlternative getCapacityResourceConf(IProject project){
		ResourceProvider resourceProvider = ResourceRegistry.getInstance()
												.getResourceProvider(project, ToolchainUtils.ANALYSER_CONF_ID);
		
		IEditorInputResource editorInput = resourceProvider.getResource(ANALYSER_CONF_CAPACITY_ANALYSES);
		
		if(editorInput == null){
			editorInput = createCapacityResourceConf(resourceProvider);
		}
		
		return (ConfAlternative)editorInput;
	}
	*/
	
	/**
	 * Creates capacity experiment configuration alternative resource inside specified <code>ResourceProvider</code>.
	 * If capacity experiment configuration alternative already exist, this method does nothing.
	 * 
	 * @param resourceProvider ResourceProvider specifies, where to create alternative resource. 
	 * @return Capacity experiment configuration alternative resource.
	 */
	/*
	public static ConfAlternative createCapacityResourceConf(ResourceProvider resourceProvider){
		IEditorInputResource editorInput = resourceProvider.getResource(ANALYSER_CONF_CAPACITY_ANALYSES);
		
		if(editorInput == null){
			IFolder folder = resourceProvider.getRootFolder().getFolder(ANALYSER_CONF_CAPACITY_ANALYSES);
			
			editorInput = new CapacityAlternative(resourceProvider.getRootFolder().getProject(), folder);
			editorInput.setProperty(KEY_TYPE, CapacityAlternative.class.getName());
			editorInput.save();
		}
		
		return (CapacityAlternative)editorInput;
	}
	*/
	
	public static List<ConfAlternative> getConfAlternatives(IProject project, InputAlternative inputAlt){
		
		ResourceProvider confResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, CSTool.ANALYSER_CONF);
		
		List<ConfAlternative> out = new ArrayList<ConfAlternative>();
		
		for(IEditorInputResource res : confResourceProvider.getResources()){
			if(res instanceof ConfAlternative){
				IFolder inputAltFolder = (IFolder)((ConfAlternative)res).getSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE);
				if(inputAltFolder == null){
					continue;
				}
				IEditorInputResource inputResource = ResourceRegistry.getInstance()
					.getResourceProvider(project, CSTool.ANALYSER_INPUT).getResource(inputAltFolder);
				if(inputResource == inputAlt){
					out.add((ConfAlternative)res);
				}
			}
		}
		
		return out;
	}
	
	/*
	public static void registerResourceFactories(){
		
		//register resource provider factories		
		ResourceRegistry.getInstance().registerFactory(CSTool.ANALYSER_INPUT, new IResourceProviderFactory(){

			@Override
			public ResourceProvider create(IFolder folder) {
				
				ResourceProvider resourceProvider = new ResourceProvider(folder, "Alternative") {
					
					@Override
					public boolean validateResource(IResource res) {
						if(res instanceof IFolder){
							if(((IFolder)res).getFile(EditorInputFolder.PROP_FILENAME).exists()){
								return true;
							}
						}
						return false;
					}
					
					@Override
					public IEditorInputResource createEditorInputResource(IResource res, String type) {
						
						InputAlternative eif = new InputAlternative(res.getProject(), (IFolder)res);
						return eif;
					}
					
					@Override
					public IResource createResource(String resourceName) {
						return getRootFolder().getFolder(resourceName);
					}
				};

				return resourceProvider;
			}
			
		});
		
		ResourceRegistry.getInstance().registerFactory(CSTool.ANALYSER_CONF, new IResourceProviderFactory(){

			@Override
			public ResourceProvider create(IFolder folder) {
				
				ResourceProvider resourceProvider = new ResourceProvider(folder, "Alternative") {
					
					@Override
					public boolean validateResource(IResource res) {
						if(res instanceof IFolder){
							if(((IFolder)res).getFile(EditorInputFolder.PROP_FILENAME).exists()){
								return true;
							}
						}
						return false;
					}
					
					@Override
					public IEditorInputResource createEditorInputResource(IResource res, String type) {
						
						if(type == null){
							type = ConfAlternative.Type.NORMAL.name();
						}
						
						ConfAlternative.Type typeEnum = ConfAlternative.Type.valueOf(type);
						ConfAlternative eif = new ConfAlternative(res.getProject(), (IFolder)res, typeEnum);
						return eif;
					}
					
					@Override
					public IResource createResource(String resourceName) {
						return getRootFolder().getFolder(resourceName);
					}
				};
				
				return resourceProvider;
			}
			
		});
		
		ResourceRegistry.getInstance().registerFactory(CSTool.ANALYSER_RES, new IResourceProviderFactory() {
			
			@Override
			public ResourceProvider create(IFolder folder) {
				
				ResourceProvider resourceProvider = new ResourceProvider(folder, "Result") {
					
					@Override
					public boolean validateResource(IResource res) {
						if(res instanceof IFolder){
							if(((IFolder)res).getFile(EditorInputFolder.PROP_FILENAME).exists()){
								return true;
							}
						}
						return false;
					}
					
					@Override
					public IEditorInputResource createEditorInputResource(IResource res, String type) {
						
						if(type == null){
							type = ConfAlternative.Type.NORMAL.name();
						}
						
						ConfAlternative.Type typeEnum = ConfAlternative.Type.valueOf(type);
						ResultAlternative eif = new ResultAlternative(res.getProject(), (IFolder)res, typeEnum);
						return eif;
					}
					
					@Override
					public IResource createResource(String resourceName) {
						return getRootFolder().getFolder(resourceName);
					}
				};
				
				return resourceProvider;
			}
		});
	}
	*/

}
