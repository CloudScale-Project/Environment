package eu.cloudscaleproject.env.overview.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class ImportSelectionWizard extends Wizard{
	
	private WizardSelectionPage newInputSelectionPage;
	
	public ImportSelectionWizard(IProject project) {
		
		CloudscaleContext.inject(this);
		
		setWindowTitle("Overview model wizard");
		
		List<WizardNode> nodes = new ArrayList<>();
		
		nodes.add(new OverviewSelectionWizard.CreateImportNewNode(project));
		nodes.add(new OverviewSelectionWizard.CreateImportExistingNode(project));
		
		newInputSelectionPage = new WizardSelectionPage("Import options",
														"Select one of the possible options.", nodes);
	}
	
	@Override
	public void addPages() {
		addPage(newInputSelectionPage);
		setForcePreviousAndNextButtons(true);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public boolean canFinish()
	{
		return false;
	}
}
