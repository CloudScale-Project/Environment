package eu.cloudscaleproject.env.analyser.alternatives.factory;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ConfAlternativeFactory implements IResourceProviderFactory{
	
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
}
