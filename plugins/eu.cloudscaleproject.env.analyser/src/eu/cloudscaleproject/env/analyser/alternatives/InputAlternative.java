package eu.cloudscaleproject.env.analyser.alternatives;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractInputAlternative;

public class InputAlternative extends AbstractInputAlternative
{

	public InputAlternative(IProject project, IFolder folder)
	{
		super(project, folder, ModelType.GROUP_PCM_EXTENDED, CSTool.ANALYSER);
	}

}
