package eu.cloudscaleproject.env.staticspotter.alternatives;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.resource.Resource;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class InputAlternative extends EditorInputEMF
{

	public InputAlternative(IProject project, IFolder folder)
	{
		super(project, folder, ToolchainUtils.SPOTTER_STA_INPUT_ID);
	}

	@Override
	protected void doLoad()
	{

		super.doLoad();

		try
		{
			loadModels();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void addSubResourceModel(IResource res)
	{

		ExplorerProjectPaths.getEmfResource(resSet, (IFile) res);

		String key = getToolchainKey(res);
		if (key == null)
		{
			pcs.firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, "");
			return;
		}

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

	private final void loadModels() throws IOException
	{

		IResource sourcedecorator = getSubResource(ToolchainUtils.KEY_FILE_SOURCEDECORATOR);
		IResource system = getSubResource(ToolchainUtils.KEY_FILE_SYSTEM);
		IResource repository = getSubResource(ToolchainUtils.KEY_FILE_REPOSITORY);

		for (IResource res : new IResource[] { sourcedecorator, system, repository })
		{
			if (res != null)
			{
				Resource emfRes = ExplorerProjectPaths.getEmfResource(resSet, (IFile) res);
				emfRes.unload();
				emfRes.load(null);
			}
		}
	}
}
