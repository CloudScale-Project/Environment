package eu.cloudscaleproject.env.analyser.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.wizard.config.ImportAnalyserProjectWizard;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ImportAnalyserSelectionWizard extends Wizard{
		
	private WizardSelectionPage newInputSelectionPage;
	
	public ImportAnalyserSelectionWizard(IProject project) {
				
		List<WizardNode> nodes = new ArrayList<>();
		nodes.add(new ExternalImportProjectNode(project));
		nodes.add(new ExternalImportInputNode(project));
		
		newInputSelectionPage = new WizardSelectionPage("Import Palladio project selection page",
														"Import Palladio PCM models into the Analyser", nodes);
		
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

	private static class ExternalImportProjectNode extends WizardNode
	{
		private final IProject project;
		

		public ExternalImportProjectNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new ImportAnalyserProjectWizard(project);
		}

		@Override
		public String getName() {
			return "Import PCM models and configurations";
		}

		@Override
		public String getDescription() {
			return "Create new Input, Configuration and Limbo alternatives from the existing PCM models.";
		}

	}
	
	private static class ExternalImportInputNode extends WizardNode
	{
		private final IProject project;
		

		public ExternalImportInputNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new ExternalImportInputWizard(project);
		}

		@Override
		public String getName() {
			return "Import PCM models";
		}

		@Override
		public String getDescription() {
			return "Create new input alternative from the existing PCM models.";
		}

	}
}