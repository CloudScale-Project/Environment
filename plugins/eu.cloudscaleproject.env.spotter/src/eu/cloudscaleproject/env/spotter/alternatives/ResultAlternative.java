package eu.cloudscaleproject.env.spotter.alternatives;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractResultAlternative;

public class ResultAlternative extends AbstractResultAlternative {
	
	public ResultAlternative(IProject project, IFolder folder) {
		// TODO Auto-generated constructor stub
		super (project, folder, ModelType.GROUP_NONE, CSTool.SPOTTER_DYN_RES.getID());
	}
	
}
