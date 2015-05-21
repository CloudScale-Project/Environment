package eu.cloudscaleproject.env.staticspotter.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.staticspotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.wizard.pages.ExternalModelsSelectionPage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.NameSelectionPage;

public class ImportExternalWizard extends Wizard{

	private IProject project;
	
	private NameSelectionPage nameSelectionPage;
	private ExternalModelsSelectionPage importModelSelectionPage;
	
	public ImportExternalWizard(IProject project) {
		
		this.project = project;
		
		nameSelectionPage = new NameSelectionPage();
		importModelSelectionPage = new ExternalModelsSelectionPage(null, ModelType.GROUP_SOURCEDECORATOR);
	}
	
	@Override
	public void addPages() {
		addPage(nameSelectionPage);
		addPage(importModelSelectionPage);
	}

	@Override
	public boolean performFinish() {
		
		String altName = nameSelectionPage.getName();

		ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_STA_INPUT_ID);

		InputAlternative alternative = (InputAlternative)provider.createNewResource(altName, null);
		
		Resource[] selectedResources = importModelSelectionPage.getSelectedResources();
		ExplorerProjectPaths.copyEMFResources(alternative.getResource(), selectedResources);

		for (Resource resource : selectedResources)
		{
			IFile f = ExplorerProjectPaths.getFileFromEmfResource(resource);
			alternative.addSubResourceModel(f);
		}

		alternative.save();
		
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
