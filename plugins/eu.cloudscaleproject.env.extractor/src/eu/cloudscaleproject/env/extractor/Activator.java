package eu.cloudscaleproject.env.extractor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.uka.ipd.sdq.pcm.allocation.util.AllocationAdapterFactory;
import de.uka.ipd.sdq.pcm.repository.util.RepositoryAdapterFactory;
import de.uka.ipd.sdq.pcm.seff.util.SeffAdapterFactory;
import de.uka.ipd.sdq.pcm.system.util.SystemAdapterFactory;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class Activator extends AbstractUIPlugin {

	private static BundleContext context;

	// The shared instance
	public static Activator plugin;

	public static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		plugin = this;

		ResourceRegistry.getInstance().registerFactory(
				ToolchainUtils.EXTRACTOR_CONF_ID,
				new IResourceProviderFactory() {
					@Override
					public ResourceProvider create(final IFolder folder) {
						return new ResourceProvider(folder, "Alternative") {

							@Override
							public boolean validateResource(IResource res) {
								if (res instanceof IFolder) {
									return true;
								}
								return false;
							}

							@Override
							public IEditorInputResource loadResource(
									IResource res, String type) {
								// TODO Auto-generated method stub
								ConfigPersistenceFolder cif = new ConfigPersistenceFolder(
										folder.getProject(),
										(IFolder) res);
								return cif;
							}

							@Override
							public IResource createResource(String name) {

								IFolder folder = getRootFolder()
										.getFolder(name);
								ConfigPersistenceFolder cif = new ConfigPersistenceFolder(
										folder.getProject(), folder);
								cif.create();
								return folder;
							}

						};
					}
				});

		ResourceRegistry.getInstance().registerFactory(
				ToolchainUtils.EXTRACTOR_INPUT_ID,
				new IResourceProviderFactory() {

					@Override
					public ResourceProvider create(final IFolder folder) {
						return new ResourceProvider(folder, "Alternative.alt") {

							@Override
							public boolean validateResource(IResource res) {
								if (res instanceof IFile) {
									return true;
								}
								return false;
							}

							@Override
							public IEditorInputResource loadResource(
									IResource resource, String type) {

								InputPersitenceFile eif = new InputPersitenceFile(
										folder.getProject(), (IFile) resource);
								if (!resource.exists())
									eif.save();
								return eif;
							}

							@Override
							public IResource createResource(String resourceName) {
								IFile file = getRootFolder().getFile(
										resourceName);
								InputPersitenceFile ipf = new InputPersitenceFile(
										folder.getProject(), file);
								ipf.save();
								return file;
							}
						};
					}
				});
		
		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
				
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
        adapterFactory.addAdapterFactory(new RepositoryAdapterFactory());
        adapterFactory.addAdapterFactory(new SystemAdapterFactory());
		adapterFactory.addAdapterFactory(new AllocationAdapterFactory());
		adapterFactory.addAdapterFactory(new SeffAdapterFactory());
        adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        adapterFactory.addAdapterFactory(new EcoreAdapterFactory());

		ResourceRegistry.getInstance().registerFactory(
				ToolchainUtils.EXTRACTOR_RES_ID,
				new IResourceProviderFactory() {
					@Override
					public ResourceProvider create(final IFolder folder) {
						return new ResourceProvider(folder, "Result") {
							
							@Override
							public boolean validateResource(IResource res) {
								if(res instanceof IFolder){
									return true;
								}
								return false;
							}

							@Override
							public IEditorInputResource loadResource(IResource res, String type) {
								// TODO Auto-generated method stub
								ResultPersistenceFolder rif = new ResultPersistenceFolder(folder.getProject(), (IFolder)res, adapterFactory);
								rif.load();
								return rif;
							}
							
							@Override
							public IResource createResource(String name) {

								IFolder folder = getRootFolder().getFolder(name); 
								ResultPersistenceFolder rif = new ResultPersistenceFolder(folder.getProject(), folder, adapterFactory);
								rif.create();
								return folder;
							}
							
						};
					}
				}
				);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		plugin = null;
		Activator.context = null;
	}

	public static Activator getDefault() {
		return plugin;
	}

}
