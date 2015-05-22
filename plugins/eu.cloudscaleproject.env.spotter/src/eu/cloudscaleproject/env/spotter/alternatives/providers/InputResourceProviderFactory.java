package eu.cloudscaleproject.env.spotter.alternatives.providers;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.spotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class InputResourceProviderFactory implements IResourceProviderFactory
{
	public static class InputResourceProvider extends ResourceProvider
	{
		public InputResourceProvider(IFolder folder, String defaultResName)
		{
			super(folder, defaultResName);
		}

					@Override
					public boolean validateResource(IResource res)
					{
						if (res instanceof IFolder)
						{
							return true;
						}
						return false;
					}

					@Override
					public IEditorInputResource loadResource(IResource res, String type)
					{
						InputAlternative ia = new InputAlternative(getRootFolder().getProject(), (IFolder) res);
						return ia;
					}

					@Override
					public IResource createResource(String name)
					{

						IFolder folder = getRootFolder().getFolder(name);
						InputAlternative ia = new InputAlternative(folder.getProject(), folder);
						ia.create();
						return folder;
					}

	}

	@Override
	public ResourceProvider create(final IFolder folder)
	{
		return new InputResourceProvider(folder, "Alternative");
	}

}
