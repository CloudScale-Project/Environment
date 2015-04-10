package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.analyser.wizard.pages.ModelSelectionPage;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class CreatePCMModelWizard extends Wizard{

	private final EditorInputEMF alternative;
	
	private ModelSelectionPage modelSelectionPage;
	
	public CreatePCMModelWizard(EditorInputEMF alternative, PCMModelType[] types) {
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
		
		PCMModelType[] selectedModels = modelSelectionPage.getSelectedModelTypes();
		
		PCMResourceSet resSet = new PCMResourceSet(alternative.getResource());
		resSet.createAll(selectedModels);
		
		for(PCMModelType type : selectedModels){
			IFile file = resSet.getModelFile(type);
			EObject root = resSet.getModelRootObject(type);
			
			alternative.getResourceSet().getResources().add(root.eResource());
			alternative.setSubResource(type.getToolchainFileID(), file);
		}
		
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
