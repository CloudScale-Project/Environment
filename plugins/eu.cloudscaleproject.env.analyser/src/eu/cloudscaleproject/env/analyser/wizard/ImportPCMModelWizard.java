package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.wizard.pages.ImportAlternativeOptionsPage;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.wizard.pages.ExternalModelsSelectionPage;

public class ImportPCMModelWizard extends Wizard{
	
	private EditorInputEMF alternative = null;
	
	private ExternalModelsSelectionPage importModelSelectionPage;
	private ImportAlternativeOptionsPage importOptionsPage;

	public ImportPCMModelWizard(EditorInputEMF alternative){
		this(alternative, null);
	}
	
	public ImportPCMModelWizard(EditorInputEMF alternative, IFolder from){
		
		this.alternative = alternative;
		setWindowTitle("Analyser - Import wizard");
		
		importModelSelectionPage = new ExternalModelsSelectionPage(from, ExternalModelsSelectionPage.PCM_EXTENSIONS);
		importOptionsPage = new ImportAlternativeOptionsPage();
	}
	
	@Override
	public void addPages() {
		addPage(importModelSelectionPage);
		addPage(importOptionsPage);
	}
	
	@Override
	public boolean performFinish() {
		
		Resource[] selectedResources = importModelSelectionPage.getSelectedResources();

		if (importOptionsPage.getCopyIntoProjectParam())
		{
			ExplorerProjectPaths.copyEMFResources(alternative.getResource(), selectedResources);
		}

		for (Resource resource : selectedResources)
		{
			IFile f = ExplorerProjectPaths.getFileFromEmfResource(resource);
			alternative.addSubResourceModel(f);
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
