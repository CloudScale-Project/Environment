package eu.cloudscaleproject.env.staticspotter.alternatives;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class InputAlternative extends EditorInputEMF
{

	public InputAlternative(IProject project, IFolder folder)
	{
		super(project, folder, new ModelType[]{ModelType.SOURCECODEDECORATOR, 
											   ModelType.REPOSITORY, 
											   ModelType.SYSTEM}, ToolchainUtils.SPOTTER_STA_INPUT_ID);
	}

	public void addSubResourceModel(IResource res)
	{
		String key = getToolchainKey(res);

		if (key == null)
		{
			pcs.firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, "");
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
