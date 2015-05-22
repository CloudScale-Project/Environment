package eu.cloudscaleproject.env.usageevolution.wizard;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.wizard.CreateAlternativeWizard;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative.Presets;
import eu.cloudscaleproject.env.usageevolution.wizard.pages.PresetSelectionPage;

public class CreatePredefinedUsageWizard extends CreateAlternativeWizard{
	private PresetSelectionPage presetSelectionPage = null;

	public CreatePredefinedUsageWizard(IProject project) {
		super(project, ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.USAGEEVOLUTION_ID));
		
		this.project = project;
		
		presetSelectionPage = new PresetSelectionPage();
	}
	
	@Override
	public void addPages() {
		
		super.addPages();
		addPage(presetSelectionPage);
	}
	@Override
	protected void initAlternative(IEditorInputResource alternative)
	{
		Presets selectedPreset = presetSelectionPage.getSelectedPreset();
		((UsageEvolutionAlternative)alternative).createPreset(selectedPreset);
		// TODO Auto-generated method stub
		super.initAlternative(alternative);
	}
}
