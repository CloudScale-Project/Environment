package eu.cloudscaleproject.env.analyser.wizard.config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.ExtensionRetriever;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class CreateConfigAlternativeSelectionWizard extends Wizard{
	
	@Inject 
	private ExtensionRetriever extensionRetriever;
	private WizardSelectionPage newInputSelectionPage;
		
	public CreateConfigAlternativeSelectionWizard(IProject project) {
		
		CloudscaleContext.inject(this);
		
		List<WizardNode> nodes = new ArrayList<>();
		
		nodes.add(new CreateNormalAltNode(project));
		nodes.add(new CreateCapacityAltNode(project));
		nodes.add(new CreateScalabilityAltNode(project));
		nodes.add(new ExternalImportNode(project));
		
		//retrieve nodes from extension point
		List<WizardNode> nodesFromExtensions = extensionRetriever.retrieveExtensionObjects(
				"eu.cloudscaleproject.env.analyser.wizard.newConfigAlternative",
				"class", WizardNode.class);
		
		nodes.addAll(nodesFromExtensions);
		
		
		newInputSelectionPage = new WizardSelectionPage("New config alternative selection page",
														"Create new config alternative", nodes);
		
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

	private static class CreateNormalAltNode extends WizardNode
	{
		private final IProject project;
		

		public CreateNormalAltNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new CreateConfigAlternativeWizard(project, ConfAlternative.Type.NORMAL);
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
	
	private static class CreateCapacityAltNode extends WizardNode
	{
		private final IProject project;
		

		public CreateCapacityAltNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new CreateConfigAlternativeWizard(project, ConfAlternative.Type.CAPACITY);
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
	
	private static class CreateScalabilityAltNode extends WizardNode
	{
		private final IProject project;
		

		public CreateScalabilityAltNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new CreateConfigAlternativeWizard(project, ConfAlternative.Type.SCALABILITY);
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
	
	private static class ExternalImportNode extends WizardNode
	{
		private final IProject project;
		

		public ExternalImportNode(IProject project)
		{
			this.project = project;
		}

		@Override
		public IWizard createWizard() {
			return new ImportAnalyserProjectWizard(project);
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