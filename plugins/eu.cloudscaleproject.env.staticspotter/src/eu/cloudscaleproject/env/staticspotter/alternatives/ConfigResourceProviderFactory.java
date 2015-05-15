package eu.cloudscaleproject.env.staticspotter.alternatives;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.notify.AdapterFactory;

import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.CustomAdapterFactory;

public class ConfigResourceProviderFactory implements IResourceProviderFactory
{
	private static final AdapterFactory factory = new CustomAdapterFactory();

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
						ConfigAlternative cif = new ConfigAlternative(getRootFolder().getProject(), (IFolder) res, factory);
						return cif;
					}

					@Override
					public IResource createResource(String name)
					{

						IFolder folder = getRootFolder().getFolder(name);
						ConfigAlternative cif = new ConfigAlternative(folder.getProject(), folder, factory);
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
