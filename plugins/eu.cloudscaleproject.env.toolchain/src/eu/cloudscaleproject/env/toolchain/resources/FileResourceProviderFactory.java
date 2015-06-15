package eu.cloudscaleproject.env.toolchain.resources;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFile;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class FileResourceProviderFactory implements IResourceProviderFactory{
	
	private final String validationID;
	
	public FileResourceProviderFactory(String validationID) {
		this.validationID = validationID;
	}

	@Override
	public ResourceProvider create(final IFolder folder) {
		
		ResourceProvider inputResources = new ResourceProvider(folder, "Alternative") {
			
			@Override
			public boolean validateResource(IResource res) {
				if(res instanceof IFolder && ((IFolder) res).getFile(EditorInputFolder.PROP_FILENAME).exists()){
					return true;
				}
				return false;
			}
			
			@Override
			public IFile createResource(String name) {
				IFile file = getRootFolder().getFile(name);
				if(!file.exists()){
				}
				return file;
			}

			@Override
			public IEditorInputResource createEditorInputResource(IResource res, String type) {
				return new EditorInputFile(folder.getProject(), (IFile)res, validationID);
			}
		};
		
		return inputResources;
	}
}