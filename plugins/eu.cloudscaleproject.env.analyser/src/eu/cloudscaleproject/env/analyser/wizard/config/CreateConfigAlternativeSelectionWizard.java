package eu.cloudscaleproject.env.analyser.wizard.config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.ExtensionRetriever;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.wizard.util.ProjectSelectionPage;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeSelectionPage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class CreateConfigAlternativeSelectionWizard extends Wizard implements IWorkbenchWizard{
	
	@Inject 
	private ExtensionRetriever extensionRetriever;
	
	private ProjectSelectionPage projectSelectionPage;
	private AlternativeSelectionPage inputAlternativeSelectionPage;
	private WizardSelectionPage newInputSelectionPage;
	
	private IProject project;
	private InputAlternative inputAlternative;
	
		
	public CreateConfigAlternativeSelectionWizard() {
		
		CloudscaleContext.inject(this);
				
		List<WizardNode> nodes = new ArrayList<>();
		nodes.add(new CreateNormalAltNode());
		nodes.add(new CreateCapacityAltNode());
		nodes.add(new CreateScalabilityAltNode());
		nodes.add(new ExternalImportNode());
		
		//retrieve nodes from extension point
		List<WizardNode> nodesFromExtensions = extensionRetriever.retrieveExtensionObjects(
				"eu.cloudscaleproject.env.analyser.wizard.newConfigAlternative",
				"class", WizardNode.class);
		
		nodes.addAll(nodesFromExtensions);
		
		newInputSelectionPage = new WizardSelectionPage("New config alternative selection page",
				"Create new config alternative", nodes);
		
		inputAlternativeSelectionPage = new AlternativeSelectionPage("Analyser alternative selection", "Please select the desired Analyser input alternative."){
			public boolean handleSelection(IEditorInputResource eir) {
				if(eir instanceof InputAlternative){
					inputAlternative = (InputAlternative)eir;
					return true;
				}
				return false;
			};
		};
		
		projectSelectionPage = new ProjectSelectionPage(){
			public boolean handleSelection(IProject project) {
				if(project != null){
					ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.ANALYSER_INPUT);
					inputAlternativeSelectionPage.setResourceProvider(rp);
					return true;
				}
				return false;
			};
		};
		
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
		IProject project = ExplorerProjectPaths.getProject(selection);
		if(ExplorerProjectPaths.isCloudScaleProject(project)){
			setProject(project);
		}
		
		//If project is not found, the project selection page will be shown.
	}
	
	public void setInputAlternative(InputAlternative ia){
		
		inputAlternative = ia;
	}
	
	public void setProject(IProject project){
		
		this.project = project;
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.ANALYSER_INPUT);
		if(rp != null){
			inputAlternativeSelectionPage.setResourceProvider(rp);
		}
	}
	
	@Override
	public void addPages() {
		
		if(project == null){
			addPage(projectSelectionPage);
		}
		if(inputAlternative == null){
			addPage(inputAlternativeSelectionPage);
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

	private class CreateNormalAltNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			return new CreateConfigAlternativeWizard(
					project != null ? project : projectSelectionPage.getProject(), 
					ConfAlternative.Type.NORMAL, inputAlternative);
		}

		@Override
		public String getName() {
			return "New normal alternative";
		}

		@Override
		public String getDescription() {
			return "Create new normal configuration alternative.";
		}

	}
	
	private class CreateCapacityAltNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			return new CreateConfigAlternativeWizard(
					project != null ? project : projectSelectionPage.getProject(), 
					ConfAlternative.Type.CAPACITY, inputAlternative);
		}

		@Override
		public String getName() {
			return "New capacity alternative";
		}

		@Override
		public String getDescription() {
			return "Create new capacity measurement alternative.";
		}

	}
	
	private class CreateScalabilityAltNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			return new CreateConfigAlternativeWizard(
					project != null ? project : projectSelectionPage.getProject(), 
					ConfAlternative.Type.SCALABILITY, inputAlternative);
		}

		@Override
		public String getName() {
			return "New scalability alternative";
		}

		@Override
		public String getDescription() {
			return "Create new scalability measurement alternative.";
		}

	}
	
	private class ExternalImportNode extends WizardNode
	{

		@Override
		public IWizard createWizard() {
			return new ImportAnalyserProjectWizard(project != null ? project : projectSelectionPage.getProject());
		}

		@Override
		public String getName() {
			return "Import external experiment";
		}

		@Override
		public String getDescription() {
			return "Create new configuration alternative from the existing Analyser project.";
		}

	}

}