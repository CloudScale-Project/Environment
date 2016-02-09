package eu.cloudscaleproject.env.overview.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizard;

import eu.cloudscaleproject.env.common.wizard.util.AbstractProjectWizard;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class CreateOverviewWizard extends AbstractProjectWizard{
	
	private WizardSelectionPage newInputSelectionPage;
	
	public CreateOverviewWizard() {
				
		setWindowTitle("Overview model wizard");
		
		List<WizardNode> nodes = new ArrayList<>();
		
		nodes.add(new CreateNewNode());
		//TODO:
		//nodes.add(new CreateTemplateNode());
		nodes.add(new CreateImportNewNode());
		nodes.add(new CreateImportExistingNode());
		
		newInputSelectionPage = new WizardSelectionPage("Overview model options",
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

	public class CreateNewNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			return new CreateAlternativeWizard (ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.OVERVIEW));
		}

		@Override
		public String getName() {
			return "New Overview model";
		}

		@Override
		public String getDescription() {
			return "Creates new Overview model alternative.";
		}
	}

	public static class CreateTemplateNode extends WizardNode
	{		
		@Override
		public IWizard createWizard() {
			throw new IllegalStateException("Not yet implemented");
		}

		@Override
		public String getName() {
			return "Overview model templates";
		}

		@Override
		public String getDescription() {
			return "Creates new alternative with built-in templates.";
		}
	}

}
