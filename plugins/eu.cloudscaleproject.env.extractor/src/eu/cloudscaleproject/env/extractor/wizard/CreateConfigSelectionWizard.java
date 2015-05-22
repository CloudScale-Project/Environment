package eu.cloudscaleproject.env.extractor.wizard;

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

public class CreateConfigSelectionWizard extends Wizard{
	
	private WizardSelectionPage newInputSelectionPage;
	
	public CreateConfigSelectionWizard(IProject project) {
		
		CloudscaleContext.inject(this);
		
		setWindowTitle("Extractor Configuration");
		
		List<WizardNode> nodes = new ArrayList<>();
		
		nodes.add(new CreateNewNode(project));
		nodes.add(new CreateImportNode(project));
		
		newInputSelectionPage = new WizardSelectionPage("Extractor configuration options",
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

	
	private static class CreateImportNode extends WizardNode
	{
		@SuppressWarnings("unused")
		private final IProject project;
		

		public CreateImportNode (IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			throw new IllegalStateException("Not supported yet");
		}

		@Override
		public String getName() {
			return "Import project";
		}

		@Override
		public String getDescription() {
			return "Creates new cofig alternative from existing extractor configuration.";
		}
	}
	private static class CreateNewNode extends WizardNode
	{
		private final IProject project;
		

		public CreateNewNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new CreateAlternativeWizard (project, 
					ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.EXTRACTOR_CONF_ID));
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
