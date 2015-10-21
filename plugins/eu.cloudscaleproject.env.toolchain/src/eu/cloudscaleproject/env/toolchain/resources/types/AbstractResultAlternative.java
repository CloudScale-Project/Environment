package eu.cloudscaleproject.env.toolchain.resources.types;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public abstract class AbstractResultAlternative extends EditorInputEMF implements IResultAlternative
{
	public AbstractResultAlternative(IProject project, IFolder folder, ModelType[] modelTypes, String resultID)
	{

		super(project, folder, modelTypes, resultID);
	}
	
	@Override
	public IInputAlternative getInputAlternative()
	{
		IResource res = getSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE);

		if (res == null) return null;
		return (IInputAlternative)ResourceRegistry.getInstance().getResource(res);
	}

	@Override
	public IConfigAlternative getConfigAlternative() {
		IResource res = getSubResource(ToolchainUtils.KEY_CONFIG_ALTERNATIVE);

		if (res == null) return null;
		return (IConfigAlternative)ResourceRegistry.getInstance().getResource(res);
	}
	
	public void setConfigAlternative(IConfigAlternative config)
	{
		setSubResource(ToolchainUtils.KEY_CONFIG_ALTERNATIVE, config.getResource());		
		setSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE, config.getInputAlternative().getResource());		
	}

}
