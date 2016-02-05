package eu.cloudscaleproject.env.extractor.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.wizard.util.ProjectSelectionPage;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class CreateConfigSelectionWizard extends Wizard implements IWorkbenchWizard{
	
	private IProject project;
	
	private ProjectSelectionPage projectSelectionPage;
	private WizardSelectionPage newInputSelectionPage;
	
	public CreateConfigSelectionWizard() {
		
	}
	
	public CreateConfigSelectionWizard(IProject p) {
		
		CloudscaleContext.inject(this);
		
		setWindowTitle("Extractor Configuration");
		
		this.project = p;
		
		List<WizardNode> nodes = new ArrayList<>();
		nodes.add(new CreateNewNode());
		nodes.add(new CreateImportNode());
		
		projectSelectionPage = new ProjectSelectionPage(){
			public void handleSelection(IProject p) {
				project = p;
			}
		};
		newInputSelectionPage = new WizardSelectionPage("Extractor configuration options",
														"Select one of the possible options.", nodes);
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
			return new CreateAlternativeWizard (project, 
					ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.EXTRACTOR_CONF));
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
