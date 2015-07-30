package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.ModelUtils;
import eu.cloudscaleproject.env.analyser.wizard.pages.ModelSelectionPage;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class NewModelWizard extends Wizard{

	private final EditorInputEMF alternative;
	
	private ModelSelectionPage modelSelectionPage;
	
	public NewModelWizard(EditorInputEMF alternative, ModelType[] types) {
		this.alternative = alternative;
		
		modelSelectionPage = new ModelSelectionPage("Empty model creation", alternative.getAdapterFactory(), types);
		modelSelectionPage.setDescription("Please select desired PCM model types");
	}
	
	@Override
	public void addPages() {
		addPage(modelSelectionPage);
	}

	@Override
	public boolean performFinish() {
		
		final ModelType[] selectedModels = modelSelectionPage.getSelectedModelTypes();
		ModelUtils.executeCreateModels(alternative, selectedModels);
		
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
