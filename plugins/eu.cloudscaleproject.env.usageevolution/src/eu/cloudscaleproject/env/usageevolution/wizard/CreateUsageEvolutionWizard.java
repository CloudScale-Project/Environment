package eu.cloudscaleproject.env.usageevolution.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.IWizard;

import tools.descartes.dlim.presentation.DlimModelWizard;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;
//import tools.descartes.dlim.generator.editor.wizards.CustomDlimModelWizard;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class CreateUsageEvolutionWizard extends DlimModelWizard{
	
	private WizardSelectionPage selectionPage;
	
	public CreateUsageEvolutionWizard(final IProject project) {

		WizardNode emptyNode = new WizardNode() {
			
			@Override
			public String getName() {
				return "New usage evolution";
			}
			
			@Override
			public String getDescription() {
				return "Create default usage evolution.";
			}
			
			@Override
			public IWizard createWizard() {
				return new CreateAlternativeWizard(project,
						ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.USAGEEVOLUTION_ID));
			}
		};
		
		WizardNode presetNode = new WizardNode() {
			
			@Override
			public String getName() {
				return "Predefined usage evolution";
			}
			
			@Override
			public String getDescription() {
				return "Create initial usage evolution from the predefined presets.";
			}
			
			@Override
			public IWizard createWizard() {
				return new CreatePredefinedUsageWizard(project);
			}
		};
		
		WizardNode customNode = new WizardNode() {
			
			@Override
			public String getName() {
				return "Custom usage evolution";
			}
			
			@Override
			public String getDescription() {
				return "Create customized arrival rate by specifiying seasonal and trend parts, or importing it from the external file.";
			}
			
			@Override
			public IWizard createWizard() {
				return new CreateCustomUsageWizard();
			}
		};
		
		List<WizardNode> nodes = new ArrayList<WizardNode>();
		nodes.add(emptyNode);
		nodes.add(presetNode);
		nodes.add(customNode);
		
		selectionPage = new WizardSelectionPage("Select usage evolution preset", 
												"Please select desired arrival rate preset.", nodes);
	}
	
	@Override
	public void addPages() {
		addPage(selectionPage);
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

}
