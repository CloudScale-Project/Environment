package eu.cloudscaleproject.env.extractor.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizard;

import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.wizard.CreateConfigAlternativeWizard;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class CreateConfigWizard extends CreateConfigAlternativeWizard {
	
	private WizardSelectionPage newInputSelectionPage;
	
	public CreateConfigWizard() {
		
		super(CSToolResource.EXTRACTOR_CONF, CSToolResource.EXTRACTOR_INPUT);
		
		setWindowTitle("Extractor Configuration");
		
		List<WizardNode> nodes = new ArrayList<>();
		nodes.add(new CreateNewNode());
		nodes.add(new CreateImportNode());
		
		newInputSelectionPage = new WizardSelectionPage("Extractor configuration options",
														"Select one of the possible options.", nodes);
	}
	
	@Override
	public void addPages() {
		
		if(this.project == null){
			addPage(projectSelectionPage);
		}
		
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

	private class CreateImportNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			throw new IllegalStateException("Not supported yet");
		}

		@Override
		public String getName() {
			return "Import configuration";
		}

		@Override
		public String getDescription() {
			return "Creates new cofig alternative from existing extractor configuration.";
		}
	}
	
	private class CreateNewNode extends WizardNode
	{
		@Override
		public IWizard createWizard() {
			CreateConfigAlternativeWizard confAlternativeWizard = new CreateConfigAlternativeWizard(CSToolResource.EXTRACTOR_CONF, CSToolResource.EXTRACTOR_INPUT);
			confAlternativeWizard.setProject(project);
			return confAlternativeWizard;					
		}

		@Override
		public String getName() {
			return "New configuration";
		}

		@Override
		public String getDescription() {
			return "Creates new default configuration alternative.";
		}
	}

}
