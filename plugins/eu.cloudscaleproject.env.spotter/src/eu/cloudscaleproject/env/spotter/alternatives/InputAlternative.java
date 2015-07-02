package eu.cloudscaleproject.env.spotter.alternatives;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class InputAlternative extends EditorInputEMF
{

	public InputAlternative(IProject project, IFolder folder)
	{
		super(project, folder, null, ToolchainUtils.SPOTTER_DYN_INPUT_ID);
	}
}
