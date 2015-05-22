package eu.cloudscaleproject.env.usageevolution.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.NameSelectionPage;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative.Presets;
import eu.cloudscaleproject.env.usageevolution.wizard.pages.PresetSelectionPage;

public class CreatePredefinedUsageWizard extends Wizard{

	private final IProject project;
	
	private NameSelectionPage nameSelectionPage = null;
	private PresetSelectionPage presetSelectionPage = null;

	public CreatePredefinedUsageWizard(IProject project) {
		
		this.project = project;
		
		nameSelectionPage = new NameSelectionPage();
		presetSelectionPage = new PresetSelectionPage();
	}
	
	@Override
	public void addPages() {
		
		addPage(nameSelectionPage);
		addPage(presetSelectionPage);
	}

	@Override
	public boolean performFinish() {
		
		String name = nameSelectionPage.getName();
		Presets selectedPreset = presetSelectionPage.getSelectedPreset();
		
		ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.USAGEEVOLUTION_ID);
		UsageEvolutionAlternative resource = (UsageEvolutionAlternative)provider.createNewResource(name, null);
		resource.createPreset(selectedPreset);
		resource.save();
		
		ValidationDiagramService.showStatus(project, resource);
		OpenAlternativeUtil.openAlternative(resource);
		
		return true;
	}
	
	@Override
	public boolean canFinish()
	{
		if (getContainer().getCurrentPage() == getPages()[getPageCount()-1] 
				&& getContainer().getCurrentPage().isPageComplete())
			return true;

		return false;
	}

}
