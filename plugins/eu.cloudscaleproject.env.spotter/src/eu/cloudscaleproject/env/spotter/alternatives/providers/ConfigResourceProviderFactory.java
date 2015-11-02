package eu.cloudscaleproject.env.spotter.alternatives.providers;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.spotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfigResourceProviderFactory implements IResourceProviderFactory {
	
	public static class ConfigResourceProvider extends ResourceProvider {
		
		public ConfigResourceProvider(String id, IFolder folder, String defaultResName) {
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
			ConfigAlternative cif = new ConfigAlternative(getRootFolder()
					.getProject(), (IFolder) res);
			return cif;
		}

		@Override
		public IResource createResource(String name) {
			return getRootFolder().getFolder(name);
		}

	}

	@Override
	public ResourceProvider create(String id, final IFolder folder) {
		return new ConfigResourceProvider(id, folder, "Alternative");
	}

}
