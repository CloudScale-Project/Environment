package eu.cloudscaleproject.env.csm2pcm.wizard.nodes;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.jface.wizard.IWizard;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.csm2pcm.wizard.TransformIntoNewAlternativeWizard;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;

public class TransformIntoNewWizardNode extends WizardNode implements IExecutableExtensionFactory{
		
	@Inject
	private IProject project;
	
	public TransformIntoNewWizardNode(){
		CloudscaleContext.inject(this);
	}
	
	public TransformIntoNewWizardNode(IProject project) {
		this.project = project; 
	}
	
	@Override
	public Object create() throws CoreException {
		return new TransformIntoNewWizardNode();
	}
	
	@Override
	public String getName() {
		return "Transfrom Overview model";
	}

	@Override
	public String getDescription() {
		return "Create a new Analyser input alternative from the transfomed Overview model";
	}

	@Override
	public IWizard createWizard() {
		return new TransformIntoNewAlternativeWizard(project);
	}
	
}
