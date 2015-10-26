package eu.cloudscaleproject.env.usageevolution;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;

import tools.descartes.dlim.Sequence;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class UsageEvolutionAlternative extends EditorInputEMF
{

	public UsageEvolutionAlternative(IProject project, IFolder folder)
	{
		super(project, folder, new ModelType[]{ModelType.LIMBO}, CSToolResource.USAGEEVOLUTION.getID());
	}

	@Override
	public void doCreate(IProgressMonitor monitor)
	{
		IFile limboFile = getResource().getFile("limbo.dlim");

		Resource res = ExplorerProjectPaths.getEmfResource(resSet, limboFile);
		Sequence sequence = DlimGenerator.createEmptySequence();
		res.getContents().add(sequence);

		setSubResource(ToolchainUtils.KEY_FILE_LIMBO, limboFile);
	}
}
