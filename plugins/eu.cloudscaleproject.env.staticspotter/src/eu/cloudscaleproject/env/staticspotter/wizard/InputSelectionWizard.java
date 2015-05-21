package eu.cloudscaleproject.env.staticspotter.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class InputSelectionWizard extends Wizard{
	
	private WizardSelectionPage newInputSelectionPage;
	
	public InputSelectionWizard(IProject project) {
		
		CloudscaleContext.inject(this);
		
		List<WizardNode> nodes = new ArrayList<>();
		
		nodes.add(new CreateExternalNode(project));
		nodes.add(new CreateImportExtractorNode(project));
		
		newInputSelectionPage = new WizardSelectionPage("New input alternative selection page",
														"Create new input alternative", nodes);
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

	
	private static class CreateImportExtractorNode extends WizardNode
	{
		private final IProject project;
		

		public CreateImportExtractorNode (IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new ImportExtractorResultWizard(project);
		}

		@Override
		public String getName() {
			return "Import Extractor result";
		}

		@Override
		public String getDescription() {
			return "Creates new input alternative from extractor result.";
		}
	}
	private static class CreateExternalNode extends WizardNode
	{
		private final IProject project;
		

		public CreateExternalNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new ImportExternalWizard(project);
		}

		@Override
		public String getName() {
			return "Create from external";
		}

		@Override
		public String getDescription() {
			return "Creates new input alternative from external sourcecode decorator model.";
		}
	}


}
