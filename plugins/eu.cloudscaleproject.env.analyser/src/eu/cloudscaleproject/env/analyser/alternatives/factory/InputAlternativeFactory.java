package eu.cloudscaleproject.env.analyser.alternatives.factory;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class InputAlternativeFactory implements IResourceProviderFactory{

	@Override
	public ResourceProvider create(String id, IFolder folder) {
		
		ResourceProvider resourceProvider = new ResourceProvider(id, folder, "Alternative") {
			
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
	
}
