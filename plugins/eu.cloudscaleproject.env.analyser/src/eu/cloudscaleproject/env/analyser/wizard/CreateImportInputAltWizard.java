package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.wizard.pages.ImportAlternativeOptionsPage;
import eu.cloudscaleproject.env.analyser.wizard.pages.ImportModelSelectionPage;
import eu.cloudscaleproject.env.analyser.wizard.pages.NameSelectionPage;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class CreateImportInputAltWizard extends Wizard{

	private IProject project;
	
	private NameSelectionPage nameSelectionPage;
	private ImportModelSelectionPage importModelSelectionPage;
	private ImportAlternativeOptionsPage optionsPage;
	
	public CreateImportInputAltWizard(IProject project) {
		
		this.project = project;
		
		nameSelectionPage = new NameSelectionPage("New input alternative name");
		nameSelectionPage.setDescription("Please type in name for the new alternative");
		importModelSelectionPage = new ImportModelSelectionPage("Import existing models");
		importModelSelectionPage.setDescription("Please select project and then desired PCM models to import");
		
		optionsPage = new ImportAlternativeOptionsPage();
	}
	
	@Override
	public void addPages() {
		addPage(nameSelectionPage);
		addPage(importModelSelectionPage);
		addPage(optionsPage);
	}

	@Override
	public boolean performFinish() {
		
		String altName = nameSelectionPage.getName();
		Resource[] selectedResources = importModelSelectionPage.getSelectedResources();
		boolean copyIntoProject = optionsPage.getCopyIntoProjectParam();
		
		ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
		InputAlternative alternative = (InputAlternative)provider.createNewResource(altName, null);
		
		if (copyIntoProject)
		{
			ExplorerProjectPaths.copyEMFResources(alternative.getResource(), selectedResources);
		}

		for (Resource resource : selectedResources)
		{
			IFile f = ExplorerProjectPaths.getFileFromEmfResource(resource);
			alternative.addSubResourceModel(f);
		}
		alternative.save();
		
		return true;
	}

}
