package eu.cloudscaleproject.env.toolchain.resources;

import org.eclipse.core.resources.IFolder;

public interface IResourceProviderFactory {
	
	public ResourceProvider create(String id, IFolder folder);
	
}
