package eu.cloudscaleproject.env.staticspotter.alternatives;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractInputAlternative;

public class InputAlternative extends AbstractInputAlternative
{

	public InputAlternative(IProject project, IFolder folder)
	{
		super(project, folder, new ModelType[]{ModelType.SOURCECODEDECORATOR, 
											   ModelType.REPOSITORY, 
											   ModelType.SYSTEM}, CSTool.SPOTTER_STA_INPUT.getID(), CSTool.SPOTTER_STA_CONF.getID());
	}

	public void addSubResourceModel(IResource res)
	{
		String key = getToolchainKey(res);

		if (key == null)
		{
			firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, "");
			return;
		}

		ExplorerProjectPaths.getEmfResource(resSet, (IFile) res);
		super.addSubResource(key, res);
	}

	public String getToolchainKey(IResource res)
	{
		switch (res.getFileExtension())
		{
		case "sourcecodedecorator":
			return ToolchainUtils.KEY_FILE_SOURCEDECORATOR;
		case "system":
			return ToolchainUtils.KEY_FILE_SYSTEM;
		case "repository":
			return ToolchainUtils.KEY_FILE_REPOSITORY;
		default:
			return null;
		}
	}

}
