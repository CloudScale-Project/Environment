package eu.cloudscaleproject.env.spotter.alternatives;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;

public class ConfigAlternative extends AbstractConfigAlternative {

	public ConfigAlternative(IProject project, IFolder folder) {
		super (project, folder, ToolchainUtils.SPOTTER_DYN_CONF_ID,
				ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_INPUT_ID),
				ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_RES_ID)
				);
	}

	@Override
	public void create() {
		super.create();
	}
	
	@Override
	protected void doLoad() {
		
		super.doLoad();
	}
	
	@Override
	protected IStatus doRun(IProgressMonitor m)
	{
		throw new IllegalStateException();
	}

	
}
