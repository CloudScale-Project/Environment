package eu.cloudscaleproject.env.toolchain;

import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.toolchain.resources.IResourceProviderFactory;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public interface ITool {
	
	public String getToolName();
	public String getToolID();
	
	public Image getToolImage();
	
	public IResourceProviderFactory getResourceProviderFactories();
}
