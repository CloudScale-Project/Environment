package eu.cloudscaleproject.env.spotter.alternatives.providers;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.spotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class InputResourceProviderFactory implements IResourceProviderFactory {
	
	public static class InputResourceProvider extends ResourceProvider {
		
		public InputResourceProvider(String id, IFolder folder, String defaultResName) {
			super(id, folder, defaultResName);
		}

		@Override
		public boolean validateResource(IResource res) {
			if (res instanceof IFolder) {
				return true;
			}
			return false;
		}

		@Override
		public IEditorInputResource createEditorInputResource(IResource res, String type) {
			InputAlternative ia = new InputAlternative(getRootFolder()
					.getProject(), (IFolder) res);
			return ia;
		}

		@Override
		public IResource createResource(String name) {
			return getRootFolder().getFolder(name);
		}

	}

	@Override
	public ResourceProvider create(String id, final IFolder folder) {
		return new InputResourceProvider(id, folder, "Alternative");
	}

}
