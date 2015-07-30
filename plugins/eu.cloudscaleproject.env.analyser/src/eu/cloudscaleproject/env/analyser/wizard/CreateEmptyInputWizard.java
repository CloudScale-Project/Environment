package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.ModelUtils;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.wizard.pages.ModelSelectionPage;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.util.CustomAdapterFactory;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;

public class CreateEmptyInputWizard extends Wizard{
	
	private final IProject project;
	
	private final ModelType[] types = ModelType.GROUP_PCM;
	
	private final AlternativeNamePage nameSelectionPage;
	private final ModelSelectionPage modelSelectionPage;
	
	public CreateEmptyInputWizard(IProject project) {
		this.project = project;
				
		nameSelectionPage = new AlternativeNamePage(ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_INPUT));
		
		modelSelectionPage = new ModelSelectionPage("Select PCM models", new CustomAdapterFactory(), types);
		modelSelectionPage.selectAll();
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
		final ModelType[] selectedModels = modelSelectionPage.getSelectedModelTypes();
		
		ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_INPUT);
		final InputAlternative alternative = (InputAlternative)provider.createNewResource(name, null);
		
		ModelUtils.executeCreateModels(alternative, selectedModels);
		
		ValidationDiagramService.showStatus(project, CSTool.ANALYSER_INPUT.getID(), alternative);
		OpenAlternativeUtil.openAlternative(alternative);
		
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
