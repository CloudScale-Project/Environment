package eu.cloudscaleproject.env.analyser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.analyser.experiments.CapacityExperiment;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.FolderResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ResourceUtils {
	
	public static final String ANALYSER_INPUT_GENERATED_RES_NAME = "overview.alt";
	public static final String ANALYSER_CONF_CAPACITY_ANALYSES = "Capacity analyses";

	
	public static InputAlternative getGeneratedResourceInput(IProject project){
		ResourceProvider resourceProvider = ResourceRegistry.getInstance()
												.getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		IEditorInputResource editorInput = resourceProvider.getResource(ANALYSER_INPUT_GENERATED_RES_NAME);
		
		if(editorInput == null){
			editorInput = resourceProvider.createNewResource(ANALYSER_INPUT_GENERATED_RES_NAME, "Generated input");
		}
		
		return (InputAlternative)editorInput;
	}
	
	public static ConfAlternative getCapacityResourceConf(IProject project){
		ResourceProvider resourceProvider = ResourceRegistry.getInstance()
												.getResourceProvider(project, ToolchainUtils.ANALYSER_CONF_ID);
		IEditorInputResource editorInput = resourceProvider.getResource(ANALYSER_CONF_CAPACITY_ANALYSES);
		
		if(editorInput == null){
			editorInput = resourceProvider.createNewResource(ANALYSER_CONF_CAPACITY_ANALYSES, "Capacity analyses");
			try {
				CapacityExperiment.init((ConfAlternative)editorInput);
				editorInput.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return (ConfAlternative)editorInput;
	}
	
	public static List<ConfAlternative> getConfAlternatives(IProject project, InputAlternative inputAlt){
		
		ResourceProvider confResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, ToolchainUtils.ANALYSER_CONF_ID);
		
		List<ConfAlternative> out = new ArrayList<ConfAlternative>();
		
		for(IEditorInputResource res : confResourceProvider.getResources()){
			if(res instanceof ConfAlternative){
				InputAlternative ia = ((ConfAlternative)res).getInput();
				if(ia == inputAlt){
					out.add((ConfAlternative)res);
				}
			}
		}
		
		return out;
	}
	
	public static void registerResourceFactories(){
		
		//register resource provider factories		
		ResourceRegistry.getInstance().registerFactory(ToolchainUtils.ANALYSER_INPUT_ID, new IResourceProviderFactory(){

			@Override
			public ResourceProvider create(IFolder folder) {
				return new ResourceProvider(folder, "input.alt") {
					
					@Override
					public boolean validateResource(IResource res) {
						if(res instanceof IFile){
							if(((IFile)res).getFileExtension().equals("alt")){
								return true;
							}
						}
						return false;
					}
					
					@Override
					public IEditorInputResource loadResource(IResource res) {
						return new InputAlternative(res.getProject(), (IFile)res);
					}
					
					@Override
					public IResource createResource(String resourceName) {
						return getRootFolder().getFile(resourceName);
					}
				};
			}
			
		});
		
		ResourceRegistry.getInstance().registerFactory(ToolchainUtils.ANALYSER_CONF_ID, new IResourceProviderFactory(){

			@Override
			public ResourceProvider create(IFolder folder) {
				return new ResourceProvider(folder, "Alternative") {
					
					@Override
					public boolean validateResource(IResource res) {
						//TODO: validate resource
						return true;
					}
					
					@Override
					public IEditorInputResource loadResource(IResource res) {
						return new ConfAlternative(res.getProject(), (IFolder)res);
					}
					
					@Override
					public IResource createResource(String resourceName) {
						return getRootFolder().getFolder(resourceName);
					}
				};
			}
			
		});
		
		ResourceRegistry.getInstance().registerFactory(ToolchainUtils.ANALYSER_RES_ID, new FolderResourceProviderFactory());
	}

}
