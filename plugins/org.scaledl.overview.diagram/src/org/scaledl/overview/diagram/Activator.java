package org.scaledl.overview.diagram;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.scaledl.overview.OverviewPackage;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.scaledl.overview.diagram"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
		
	/**
	 * The constructor
	 */
	public Activator() {
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		OverviewPackage.eINSTANCE.eClass();
		plugin = this;
		
		ResourceRegistry.getInstance().registerFactory(ToolchainUtils.OVERVIEW_ID, new IResourceProviderFactory(){

			@Override
			public ResourceProvider create(final IFolder folder) {
				
				ResourceProvider resourceProvider = new ResourceProvider(folder, "overview.sdlo") {
					
					@Override
					protected boolean validateResource(IResource res) {
						if(res.exists() && res instanceof IFile){
							IFile file = (IFile)res;
							if("sdlo".equals(file.getFileExtension())){
								return true;
							}
						}
						return false;
					}
					
					@Override
					protected IEditorInputResource loadResource(IResource res, String type) {
						return new OverviewResource((IFile)res);
					}
					
					@Override
					protected IResource createResource(String resourceName) {
						
						return folder.getFile(resourceName);
					}
				};
				
				return resourceProvider;
			}
			
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
