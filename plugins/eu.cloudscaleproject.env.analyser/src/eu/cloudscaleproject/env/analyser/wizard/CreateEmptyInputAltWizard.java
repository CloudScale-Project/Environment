package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.wizard.pages.ModelSelectionPage;
import eu.cloudscaleproject.env.analyser.wizard.pages.NameSelectionPage;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.util.CustomAdapterFactory;

public class CreateEmptyInputAltWizard extends Wizard{
	
	private final IProject project;
	
	private final PCMModelType[] types = new PCMModelType[]{
			PCMModelType.REPOSITORY,
			PCMModelType.SYSTEM,
			PCMModelType.ALLOCATION,
			PCMModelType.RESOURCE,
			PCMModelType.USAGE};
	
	private final NameSelectionPage nameSelectionPage;
	private final ModelSelectionPage modelSelectionPage;
	
	public CreateEmptyInputAltWizard(IProject project) {
		this.project = project;
				
		nameSelectionPage = new NameSelectionPage("New input alternative name");
		nameSelectionPage.setDescription("Please type in name for the new alternative");
		modelSelectionPage = new ModelSelectionPage("Select PCM models", new CustomAdapterFactory(), types);
		modelSelectionPage.setDescription("Please select PCM models to start with");
	}
	
	@Override
	public void addPages() {
		addPage(nameSelectionPage);
		addPage(modelSelectionPage);
	}

	@Override
	public boolean performFinish() {
		
		String name = nameSelectionPage.getName();
		PCMModelType[] types = modelSelectionPage.getSelectedModelTypes();
		
		ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		InputAlternative resource = (InputAlternative)provider.createNewResource(name, null);
		resource.createEmpty(types);
		resource.save();
		
		return true;
	}

	@Override
	public boolean canFinish()
	{
		if (getContainer().getCurrentPage() == getPages()[getPageCount()-1])
			return true;

		return false;
	}

}
