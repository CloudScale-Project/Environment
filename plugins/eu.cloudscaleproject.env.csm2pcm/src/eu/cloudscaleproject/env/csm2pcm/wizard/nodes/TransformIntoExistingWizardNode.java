package eu.cloudscaleproject.env.csm2pcm.wizard.nodes;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.jface.wizard.IWizard;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.csm2pcm.wizard.TransformIntoExistingAlternativeWizard;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;

public class TransformIntoExistingWizardNode extends WizardNode implements IExecutableExtensionFactory{
	
	@Inject
	private IProject project;
	
	public TransformIntoExistingWizardNode(){
		CloudscaleContext.inject(this);
	}
	
	public TransformIntoExistingWizardNode(IProject project) {
		this.project = project; 
	}
	
	@Override
	public Object create() throws CoreException {
		return new TransformIntoExistingWizardNode();
	}

	@Override
	public String getName() {
		return "Merge to existing";
	}

	@Override
	public String getDescription() {
		return "Merge transformed Overview model into existing Analyser input alternative";
	}

	@Override
	public IWizard createWizard() {
		return new TransformIntoExistingAlternativeWizard(project);
	}
	
}