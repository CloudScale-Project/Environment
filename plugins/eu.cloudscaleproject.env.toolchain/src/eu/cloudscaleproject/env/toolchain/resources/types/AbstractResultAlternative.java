package eu.cloudscaleproject.env.toolchain.resources.types;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public abstract class AbstractResultAlternative extends EditorInputEMF implements IResultAlternative
{
	
	private ResourceProvider configResourceProvider;

	public AbstractResultAlternative(IProject project, IFolder folder, ModelType[] modelTypes, 
			String resultID, String configID)
	{

		super(project, folder, modelTypes, resultID);

		this.configResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, configID);
	}
	
	@Override
	public IConfigAlternative getConfigAlternative() {
		IResource res = getSubResource(ToolchainUtils.KEY_CONFIG_ALTERNATIVE);
		if (configResourceProvider != null && res != null)
			return (IConfigAlternative)configResourceProvider.getResource(res);
		else
			return null;
	}
	
	public void setConfigAlternative(IConfigAlternative config)
	{
		setSubResource(ToolchainUtils.KEY_CONFIG_ALTERNATIVE, config.getResource());		
	}

	@Override
	public IInputAlternative getInputAlternative()
	{
		return getConfigAlternative().getInputAlternative();
	}
}
