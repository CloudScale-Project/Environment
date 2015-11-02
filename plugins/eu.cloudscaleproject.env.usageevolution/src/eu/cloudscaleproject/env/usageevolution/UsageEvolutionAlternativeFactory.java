package eu.cloudscaleproject.env.usageevolution;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class UsageEvolutionAlternativeFactory implements IResourceProviderFactory{

	@Override
	public ResourceProvider create(String id, final IFolder folder) {
		
		ResourceProvider inputResources = new ResourceProvider(id, folder, "Alternative") {
			
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
			public IEditorInputResource createEditorInputResource(IResource res, String type) {
				return new UsageEvolutionAlternative(folder.getProject(), (IFolder)res);
			}
		};
		
		return inputResources;
	}
}