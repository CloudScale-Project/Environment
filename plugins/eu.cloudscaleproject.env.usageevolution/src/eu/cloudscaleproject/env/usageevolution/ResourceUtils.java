package eu.cloudscaleproject.env.usageevolution;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ResourceUtils {
	
	public static void registerResourceFactories(){
		ResourceRegistry.getInstance().registerFactory(ToolchainUtils.USAGEEVOLUTION_ID, new ResourceUtils.UsageProviderFactory());
	}
	
	private static class UsageProviderFactory implements IResourceProviderFactory{

		@Override
		public ResourceProvider create(final IFolder folder) {
			
			ResourceProvider inputResources = new ResourceProvider(folder, "Alternative") {
				
				@Override
				public boolean validateResource(IResource res) {
					if(res instanceof IFolder && ((IFolder) res).getFile(EditorInputFolder.PROP_FILENAME).exists()){
						
						//TODO: create dummy Usage evolution and limbo models if they don't exist!
						
						return true;
					}
					return false;
				}
				
				@Override
				public IFolder createResource(String name) {
					IFolder folder = getRootFolder().getFolder(name);
					if(!folder.exists()){
					}
					return folder;
				}

				@Override
				public IEditorInputResource loadResource(IResource res, String type) {
					return new UsageEvolutionAlternative(folder.getProject(), (IFolder)res);
				}
			};
			
			return inputResources;
		}
	}

}
