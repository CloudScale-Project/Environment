package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.wizard.pages.NewInputSelectionPage;

public class CreateInputAltWizard extends Wizard{
	
	private NewInputSelectionPage newInputSelectionPage;
	
	public CreateInputAltWizard(IProject project) {		
		newInputSelectionPage = new NewInputSelectionPage(project);
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
