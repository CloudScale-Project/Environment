package eu.cloudscaleproject.env.analyser.wizard;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.ExtensionRetriever;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class CreateInputSelectionWizard extends Wizard{
	
	@Inject 
	private ExtensionRetriever extensionRetriever;
	
	private WizardSelectionPage newInputSelectionPage;
	
	public CreateInputSelectionWizard(IProject project) {
		
		CloudscaleContext.inject(this);
		
		List<WizardNode> nodes = new ArrayList<>();
		
		nodes.add(new CreateEmptyInputAltNode(project));
		nodes.add(new ExternalImportNode(project));
		nodes.add(new ExtractorImportNode(project));
		
		//retrieve nodes from extension point
		List<WizardNode> nodesFromExtensions = extensionRetriever.retrieveExtensionObjects(
				"eu.cloudscaleproject.env.analyser.wizard.newInputAlternative",
				"class", WizardNode.class);
		
		nodes.addAll(nodesFromExtensions);
		
		
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

	private static class CreateEmptyInputAltNode extends WizardNode
	{
		private final IProject project;
		

		public CreateEmptyInputAltNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new CreateEmptyInputWizard(project);
		}

		@Override
		public String getName() {
			return "New Analyser input";
		}

		@Override
		public String getDescription() {
			return "Create new input alternative with empty PCM models.";
		}

	}
	
	private static class ExternalImportNode extends WizardNode
	{
		private final IProject project;
		

		public ExternalImportNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new ExternalImportInputWizard(project);
		}

		@Override
		public String getName() {
			return "Import external models";
		}

		@Override
		public String getDescription() {
			return "Creates new input alternative from existing PCM models";
		}

	}

	private static class ExtractorImportNode extends WizardNode
	{
		private final IProject project;
		

		public ExtractorImportNode (IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new ExtractorImportInputWizard(project);
		}

		@Override
		public String getName() {
			return "Import Extractor result";
		}

		@Override
		public String getDescription() {
			return "Creates new input alternative from existing Extractor result";
		}

	}


}
