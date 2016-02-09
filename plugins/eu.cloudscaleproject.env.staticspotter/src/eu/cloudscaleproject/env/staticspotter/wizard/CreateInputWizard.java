package eu.cloudscaleproject.env.staticspotter.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizard;

import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class CreateInputWizard extends CreateAlternativeWizard{
	
	private WizardSelectionPage newInputSelectionPage;
	
	public CreateInputWizard() {
		
		super(CSToolResource.SPOTTER_STA_INPUT);
		
		setWindowTitle("StaticSpotter Import");
		
		List<WizardNode> nodes = new ArrayList<>();
		nodes.add(new CreateImportExtractorNode());
		nodes.add(new CreateExternalNode());
		
		newInputSelectionPage = new WizardSelectionPage("Import options",
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

	
	private class CreateImportExtractorNode extends WizardNode
	{

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
	private class CreateExternalNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			return new ImportExternalWizard(project);
		}

		@Override
		public String getName() {
			return "Import external project";
		}

		@Override
		public String getDescription() {
			return "Creates new input alternative from external sourcecode decorator model.";
		}
	}


}
