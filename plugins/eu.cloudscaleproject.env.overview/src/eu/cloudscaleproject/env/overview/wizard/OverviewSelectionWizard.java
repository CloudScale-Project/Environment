package eu.cloudscaleproject.env.overview.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class OverviewSelectionWizard extends Wizard{
	
	private WizardSelectionPage newInputSelectionPage;
	
	public OverviewSelectionWizard(IProject project) {
		
		CloudscaleContext.inject(this);
		
		setWindowTitle("Overview model wizard");
		
		List<WizardNode> nodes = new ArrayList<>();
		
		nodes.add(new CreateNewNode(project));
		nodes.add(new CreateTemplateNode());
		nodes.add(new CreateImportNewNode(project));
		nodes.add(new CreateImportExistingNode(project));
		
		newInputSelectionPage = new WizardSelectionPage("Overview model options",
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

	
	public static class CreateImportNewNode extends WizardNode
	{
		private final IProject project;
		

		public CreateImportNewNode (IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new ImportWizard(project, false);
		}

		@Override
		public String getName() {
			return "Transform PCM (new)";
		}

		@Override
		public String getDescription() {
			return "Creates new alternative from existing extractor configuration.";
		}
	}

	public static class CreateImportExistingNode extends WizardNode
	{
		private final IProject project;
		

		public CreateImportExistingNode (IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new ImportWizard(project, true);
		}

		@Override
		public String getName() {
			return "Tranfrorm PCM (import)";
		}

		@Override
		public String getDescription() {
			return "Imports PCM into existing overview alternative.";
		}
	}

	public static class CreateNewNode extends WizardNode
	{
		private final IProject project;
		

		public CreateNewNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new CreateAlternativeWizard (project, 
					ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.OVERVIEW_ID));
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
