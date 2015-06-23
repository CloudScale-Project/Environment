package eu.cloudscaleproject.env.usageevolution;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.resource.Resource;

import tools.descartes.dlim.Sequence;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class UsageEvolutionAlternative extends EditorInputEMF
{

	public UsageEvolutionAlternative(IProject project, IFolder folder)
	{
		super(project, folder, ToolchainUtils.USAGEEVOLUTION_ID);
	}

	public void doCreate()
	{
		IFile limboFile = getResource().getFile("limbo.dlim");

		Resource res = ExplorerProjectPaths.getEmfResource(resSet, limboFile);
		Sequence sequence = DlimGenerator.createEmptySequence();
		res.getContents().add(sequence);

		setSubResource(ToolchainUtils.KEY_FILE_LIMBO, limboFile);
	}

	@Override
	protected void doLoad()
	{

		super.doLoad();

		try
		{
			loadModels();
		} catch (IOException e2)
		{
			e2.printStackTrace();
		}
	}

	private final void loadModels() throws IOException
	{
		for (IResource f : getSubResources(ModelType.LIMBO.getToolchainFileID()))
		{
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) f);
			res.unload();
			res.load(null);
		}
	}
}
