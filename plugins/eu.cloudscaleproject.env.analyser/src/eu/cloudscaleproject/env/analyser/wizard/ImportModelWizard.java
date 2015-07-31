package eu.cloudscaleproject.env.analyser.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.ModelUtils;
import eu.cloudscaleproject.env.analyser.wizard.pages.ImportAlternativeOptionsPage;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.wizard.pages.ExternalModelsSelectionPage;

public class ImportModelWizard extends Wizard{
	
	private EditorInputEMF alternative = null;
	
	private ExternalModelsSelectionPage importModelSelectionPage;
	private ImportAlternativeOptionsPage importOptionsPage;

	public ImportModelWizard(EditorInputEMF alternative){
		this(alternative, null);
	}
	
	public ImportModelWizard(EditorInputEMF alternative, IFolder from){
		
		this.alternative = alternative;
		setWindowTitle("Analyser - Import wizard");
		
		List<ModelType> modelTypes = new ArrayList<ModelType>();
		for(ModelType mt : ModelType.GROUP_PCM_EXTENDED){
			
			//allow multiple repository models
			if(mt == ModelType.REPOSITORY){
				modelTypes.add(mt);
				continue;
			}
			//allow multiple usage models
			if(mt == ModelType.USAGE){
				modelTypes.add(mt);
				continue;
			}
			
			if(alternative.getModelRoot(mt.getToolchainFileID()).isEmpty()){
				modelTypes.add(mt);
			}
		}
		
		importModelSelectionPage = new ExternalModelsSelectionPage(
				from, 
				modelTypes.toArray(new ModelType[modelTypes.size()]));
		
		importOptionsPage = new ImportAlternativeOptionsPage();
	}
	
	@Override
	public void addPages() {
		addPage(importModelSelectionPage);
		addPage(importOptionsPage);
	}
	
	@Override
	public boolean performFinish() {
		
		final Resource[] selectedResources = importModelSelectionPage.getSelectedResources();
		ModelUtils.executeImportModels(alternative, selectedResources, importOptionsPage.getCopyIntoProjectParam());
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
