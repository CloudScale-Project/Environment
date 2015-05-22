package eu.cloudscaleproject.env.spotter.alternatives.providers;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.spotter.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.CustomAdapterFactory;

public class ResultResourceProviderFactory implements IResourceProviderFactory
{

	public static class ResultResourceProvider extends ResourceProvider
	{
		static final CustomAdapterFactory adapterFactory = new CustomAdapterFactory();

		public ResultResourceProvider(IFolder folder, String defaultResName)
		{
			super(folder, defaultResName);
			// TODO Auto-generated constructor stub
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
			// TODO Auto-generated method stub
			ResultAlternative rif = new ResultAlternative(getProject(), (IFolder) res);
			rif.load();
			return rif;
		}

		@Override
		public IResource createResource(String name)
		{

			IFolder folder = getRootFolder().getFolder(name);
			ResultAlternative rif = new ResultAlternative(getProject(), folder);
			rif.create();
			rif.save();
			return folder;
		}

	}

	@Override
	public ResourceProvider create(final IFolder folder)
	{
		return new ResultResourceProvider(folder, "Alternative");
	}

}
