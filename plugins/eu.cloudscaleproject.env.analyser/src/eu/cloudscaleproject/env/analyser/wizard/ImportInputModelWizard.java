package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.wizard.pages.ImportAlternativeOptions;
import eu.cloudscaleproject.env.analyser.wizard.pages.ImportModelSelectionPage;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class ImportInputModelWizard extends Wizard{
	
	private EditorInputEMF input = null;
	
	private ImportModelSelectionPage modelSelectionPage;
	private ImportAlternativeOptions importOptionsPage;

	public ImportInputModelWizard(EditorInputEMF input){
		this.input = input;
		
		modelSelectionPage = new ImportModelSelectionPage("Select model to import");
		importOptionsPage = new ImportAlternativeOptions("Import options");
		
		addPage(modelSelectionPage);
		addPage(importOptionsPage);
	}
	
	@Override
	public boolean performFinish() {
		
		Resource[] selectedResources = modelSelectionPage.getSelectedResources();

		if (importOptionsPage.getCopyIntoProjectParam())
		{
			ExplorerProjectPaths.copyEMFResources(input.getResource(), selectedResources);
		}

		for (Resource resource : selectedResources)
		{
			IFile f = ExplorerProjectPaths.getFileFromEmfResource(resource);
			input.addSubResourceModel(f);
		}

		input.save();
		input.load();
		
		return true;
	}

}
