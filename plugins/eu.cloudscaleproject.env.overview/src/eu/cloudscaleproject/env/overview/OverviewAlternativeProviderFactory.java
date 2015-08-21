package eu.cloudscaleproject.env.overview;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class OverviewAlternativeProviderFactory implements IResourceProviderFactory{
	
	@Override
	public ResourceProvider create(final IFolder folder) {
		
		ResourceProvider resourceProvider = new ResourceProvider(folder, "Alternative") {
			
			@Override
			protected boolean validateResource(IResource res) {
				if(res instanceof IFolder){
					if(((IFolder)res).getFile(EditorInputFolder.PROP_FILENAME).exists()){
						return true;
					}
				}
				return false;
			}
			
			@Override
			protected IEditorInputResource createEditorInputResource(IResource res, String type) {
				return new OverviewAlternative(res.getProject(), (IFolder)res);
			}
			
			@Override
			protected IResource createResource(String resourceName) {
				
				return folder.getFolder(resourceName);
			}
		};
		
		return resourceProvider;
	}
	
}
