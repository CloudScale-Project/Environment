package eu.cloudscaleproject.env.usageevolution.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizard;

import eu.cloudscaleproject.env.common.wizard.util.AbstractProjectWizard;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;
//import tools.descartes.dlim.generator.editor.wizards.CustomDlimModelWizard;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardNode;
import eu.cloudscaleproject.env.toolchain.wizard.pages.WizardSelectionPage;

public class CreateUsageEvolutionWizard extends AbstractProjectWizard{
	
	private WizardSelectionPage selectionPage;
	
	public CreateUsageEvolutionWizard() {
		
		List<WizardNode> nodes = new ArrayList<WizardNode>();
		nodes.add(new EmptyNode());
		nodes.add(new CustomNode());
		nodes.add(new PresetNode());
		
		selectionPage = new WizardSelectionPage("Select usage evolution preset", 
												"Please select desired arrival rate preset.", nodes);
	}
	
	@Override
	public void addPages() {
		
		super.addPages();
		
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
	
	private class EmptyNode extends WizardNode{
		
		@Override
		public String getName() {
			return "New Usage Evolution";
		}
		
		@Override
		public String getDescription() {
			return "Create default usage evolution.";
		}
		
		@Override
		public IWizard createWizard() {
			return new CreateAlternativeWizard(
					ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.USAGEEVOLUTION));
		}
		
	}
	
	private class PresetNode extends WizardNode{
			
		@Override
		public String getName() {
			return "Usage Evolution templates";
		}
		
		@Override
		public String getDescription() {
			return "Create initial usage evolution from the predefined presets.";
		}
		
		@Override
		public IWizard createWizard() {
			return new CreatePredefinedUsageWizard(project);
		}
		
	}

	private class CustomNode extends WizardNode{
	
		@Override
		public String getName() {
			return "New Usage Evolution (advance)";
		}
		
		@Override
		public String getDescription() {
			return "Create customized arrival rate by specifiying seasonal and trend parts, or importing it from the external file.";
		}
		
		@Override
		public IWizard createWizard() {
			return new CreateCustomUsageWizard(project,
					ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.USAGEEVOLUTION));
		}
		
	}

}
