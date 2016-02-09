package eu.cloudscaleproject.env.analyser.wizard;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.ExtensionRetriever;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.wizard.util.ProjectSelectionPage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class CreateInputAlternativeSelectionWizard extends Wizard implements IWorkbenchWizard{
	
	@Inject 
	private ExtensionRetriever extensionRetriever;
	
	private IProject project;
	
	private WizardSelectionPage newInputSelectionPage;
	private ProjectSelectionPage projectSelectionPage;
	
	public CreateInputAlternativeSelectionWizard() {
		this(null);
	}
	
	public CreateInputAlternativeSelectionWizard(IProject project) {
		
		CloudscaleContext.inject(this);
		
		this.project = project;
		
		List<WizardNode> nodes = new ArrayList<>();
		nodes.add(new CreateEmptyInputAltNode());
		nodes.add(new ExternalImportNode());
		nodes.add(new ExtractorImportNode());
		
		//retrieve nodes from extension point
		List<WizardNode> nodesFromExtensions = extensionRetriever.retrieveExtensionObjects(
				"eu.cloudscaleproject.env.analyser.wizard.newInputAlternative",
				"class", WizardNode.class);
		
		nodes.addAll(nodesFromExtensions);
		
		projectSelectionPage = new ProjectSelectionPage();
		newInputSelectionPage = new WizardSelectionPage("New input alternative selection page",
														"Create new input alternative", nodes);
		
		
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
		IProject project = ExplorerProjectPaths.getProject(selection);
		if(ExplorerProjectPaths.isCloudScaleProject(project)){
			this.project = project;
		}
		
		//If project is not found, the project selection page will be shown.
	}
	
	@Override
	public void addPages() {
		
		if(project == null){
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

	private class CreateEmptyInputAltNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			return new CreateEmptyInputWizard(project != null ? project : projectSelectionPage.getProject());
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
	
	private class ExternalImportNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			return new ExternalImportInputWizard(project != null ? project : projectSelectionPage.getProject());
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

	private class ExtractorImportNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			return new ExtractorImportInputWizard(project != null ? project : projectSelectionPage.getProject());
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
