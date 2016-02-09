package eu.cloudscaleproject.env.overview.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizard;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.wizard.util.AbstractProjectWizard;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class ImportSelectionWizard extends AbstractProjectWizard{
	
	private WizardSelectionPage newInputSelectionPage;
	
	public ImportSelectionWizard() {
		
		CloudscaleContext.inject(this);
		
		setWindowTitle("Overview model wizard");
		
		List<WizardNode> nodes = new ArrayList<>();
		
		nodes.add(new CreateImportNewNode());
		nodes.add(new CreateImportExistingNode());
		
		newInputSelectionPage = new WizardSelectionPage("Import options",
														"Select one of the possible options.", nodes);
	}
	
	@Override
	public void addPages() {
		
		super.addPages();
		
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
	
	public class CreateImportNewNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			return new ImportWizard(project, false);
		}

		@Override
		public String getName() {
			return "Transform PCM (New Overview)";
		}

		@Override
		public String getDescription() {
			return "Creates new Overview alternative from existing extractor configuration.";
		}
	}

	public class CreateImportExistingNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			return new ImportWizard(project, true);
		}

		@Override
		public String getName() {
			return "Tranfrorm PCM (Existing Overview)";
		}

		@Override
		public String getDescription() {
			return "Imports PCM into existing overview alternative.";
		}
	}
}
