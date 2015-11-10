package eu.cloudscaleproject.env.toolchain.resources.types;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public abstract class AbstractResultAlternative extends EditorInputEMF implements IResultAlternative
{
	private final CSTool tool;

	public AbstractResultAlternative(IProject project, IFolder folder, ModelType[] modelTypes, CSTool tool)
	{

		super(project, folder, modelTypes, tool.getResult().getID());
		
		this.tool = tool;
	}
	
	@Override
	public CSTool getTool()
	{
		return tool;
	}
	
	@Override
	public IInputAlternative getInputAlternative()
	{
		IResource res = getSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE);

		if (res == null) return null;
		return (IInputAlternative)ResourceRegistry.getInstance().findResource(res);
	}

	@Override
	public IConfigAlternative getConfigAlternative() {
		IResource res = getSubResource(ToolchainUtils.KEY_CONFIG_ALTERNATIVE);

		if (res == null) return null;
		return (IConfigAlternative)ResourceRegistry.getInstance().findResource(res);
	}
	
	public void setConfigAlternative(IConfigAlternative config)
	{
		setSubResource(ToolchainUtils.KEY_CONFIG_ALTERNATIVE, config.getResource());		
		setSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE, config.getInputAlternative().getResource());		
	}

}
