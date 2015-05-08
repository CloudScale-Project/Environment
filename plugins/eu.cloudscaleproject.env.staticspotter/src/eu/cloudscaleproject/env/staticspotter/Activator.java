package eu.cloudscaleproject.env.staticspotter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.CustomAdapterFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin
{

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.cloudscaleproject.env.staticspotter"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;

		final AdapterFactory factory = new CustomAdapterFactory();

		ResourceRegistry.getInstance().registerFactory(ToolchainUtils.SPOTTER_STA_CONF_ID, new IResourceProviderFactory()
		{
			@Override
			public ResourceProvider create(final IFolder folder)
			{
				return new ResourceProvider(folder, "Alternative")
				{

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
						ConfigPersistenceFolder cif = new ConfigPersistenceFolder(folder.getProject(), (IFolder) res, factory);
						return cif;
					}

					@Override
					public IResource createResource(String name)
					{

						IFolder folder = getRootFolder().getFolder(name);
						ConfigPersistenceFolder cif = new ConfigPersistenceFolder(folder.getProject(), folder, factory);
						cif.create();
						return folder;
					}

				};
			}
		});

		ResourceRegistry.getInstance().registerFactory(ToolchainUtils.SPOTTER_STA_INPUT_ID, new IResourceProviderFactory()
		{

			@Override
			public ResourceProvider create(final IFolder folder)
			{
				return new ResourceProvider(folder, "Alternative.alt")
				{

					@Override
					public boolean validateResource(IResource res)
					{
						if (res instanceof IFile)
						{
							return true;
						}
						return false;
					}

					@Override
					public IEditorInputResource loadResource(IResource resource, String type)
					{

						InputPersitenceFile eif = new InputPersitenceFile(folder.getProject(), (IFile) resource);
						if (!resource.exists())
							eif.save();
						return eif;
					}

					@Override
					public IResource createResource(String resourceName)
					{
						IFile file = getRootFolder().getFile(resourceName);
						InputPersitenceFile ipf = new InputPersitenceFile(folder.getProject(), file);
						ipf.save();
						return file;
					}
				};
			}
		});

		ResourceRegistry.getInstance().registerFactory(ToolchainUtils.SPOTTER_STA_RES_ID, new IResourceProviderFactory()
		{

			@Override
			public ResourceProvider create(final IFolder folder)
			{
				return new ResourceProvider(folder, "Result")
				{

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
						ResultPersistenceFolder rif = new ResultPersistenceFolder(getProject(), (IFolder) res);
						rif.load();
						return rif;
					}

					@Override
					public IResource createResource(String name)
					{

						IFolder folder = getRootFolder().getFolder(name);
						ResultPersistenceFolder rif = new ResultPersistenceFolder(getProject(), folder);
						rif.create();
						rif.save();
						return folder;
					}

				};
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault()
	{
		return plugin;
	}

}
