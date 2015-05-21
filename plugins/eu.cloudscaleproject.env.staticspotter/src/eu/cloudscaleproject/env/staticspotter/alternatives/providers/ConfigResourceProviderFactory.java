package eu.cloudscaleproject.env.staticspotter.alternatives.providers;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.staticspotter.alternatives.ConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfigResourceProviderFactory implements IResourceProviderFactory
{
	public static class ConfigResourceProvider extends ResourceProvider
	{
		public ConfigResourceProvider(IFolder folder, String defaultResName)
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
						ConfigAlternative cif = new ConfigAlternative(getRootFolder().getProject(), (IFolder) res);
						return cif;
					}

					@Override
					public IResource createResource(String name)
					{

						IFolder folder = getRootFolder().getFolder(name);
						ConfigAlternative cif = new ConfigAlternative(folder.getProject(), folder);
						cif.create();
						return folder;
					}

	}

	@Override
	public ResourceProvider create(final IFolder folder)
	{
		return new ConfigResourceProvider(folder, "Alternative");
	}

}
