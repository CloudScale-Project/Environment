package eu.cloudscaleproject.env.csm2pcm.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.common.wizard.util.WizardNode;
import eu.cloudscaleproject.env.common.wizard.util.WizardSelectionPage;
import eu.cloudscaleproject.env.csm2pcm.wizard.nodes.TransformIntoNewWizardNode;

public class TransformWizard extends Wizard{

	private WizardSelectionPage wizardSelectionPage;
	
	public TransformWizard(IProject project) {
		
		List<WizardNode> nodes = new ArrayList<WizardNode>();
		
		//fill nodes
		nodes.add(new TransformIntoNewWizardNode(project));
		//nodes.add(new TransformIntoExistingWizardNode());
		
		wizardSelectionPage = new WizardSelectionPage("Overview transformation selection", 
													  "Please select transformation type",
													  nodes);
	}
	
	@Override
	public void addPages() {
		addPage(wizardSelectionPage);
		setForcePreviousAndNextButtons(true);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public boolean canFinish()
	{
		return true;
	}
	
}
