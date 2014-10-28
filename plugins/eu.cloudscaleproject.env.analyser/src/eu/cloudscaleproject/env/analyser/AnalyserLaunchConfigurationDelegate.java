package eu.cloudscaleproject.env.analyser;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.pcm.configurations.AbstractPCMLaunchConfigurationDelegate;

public class AnalyserLaunchConfigurationDelegate extends
		AbstractPCMLaunchConfigurationDelegate<SimuComWorkflowConfiguration> {

	@Override
	protected IJob createWorkflowJob(SimuComWorkflowConfiguration arg0,
			ILaunch arg1) throws CoreException {
		
		//Launch l = new Launch(launchConfiguration, "debug", ISourceLocator)
		return null;
	}

	@Override
	protected SimuComWorkflowConfiguration deriveConfiguration(
			ILaunchConfiguration arg0, String arg1) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}
}
