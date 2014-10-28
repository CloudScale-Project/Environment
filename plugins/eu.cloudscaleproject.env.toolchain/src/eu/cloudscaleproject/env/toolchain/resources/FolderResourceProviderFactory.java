package eu.cloudscaleproject.env.toolchain.resources;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class FolderResourceProviderFactory implements IResourceProviderFactory{

	@Override
	public ResourceProvider create(final IFolder folder) {
		
		ResourceProvider inputResources = new ResourceProvider(folder.getProject(), folder, "Alternative") {
			
			@Override
			public boolean validateResource(IResource res) {
				if(res instanceof IFolder && ((IFolder) res).getFile(EditorInputFolder.PROP_FILENAME).exists()){
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
			public IEditorInputResource loadResource(IResource res) {
				return new EditorInputFolder(folder.getProject(), (IFolder)res);
			}
		};
		
		return inputResources;
	}
}