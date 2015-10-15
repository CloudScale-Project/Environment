package eu.cloudscaleproject.env.extractor.alternatives;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

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
		public IEditorInputResource createEditorInputResource(IResource res, String type)
		{
			// TODO Auto-generated method stub
			InputAlternative ia = new InputAlternative(getRootFolder().getProject(), (IFolder) res);
			return ia;
		}

		@Override
		public IResource createResource(String name)
		{
			return getRootFolder().getFolder(name);
		}
	}

	@Override
	public ResourceProvider create(final IFolder folder)
	{
		return new InputResourceProvider(folder, "Alternative");
	}

}
