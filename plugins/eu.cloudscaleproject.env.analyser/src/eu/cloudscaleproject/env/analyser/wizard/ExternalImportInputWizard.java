package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.wizard.pages.ImportAlternativeOptionsPage;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.ExternalModelsSelectionPage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.NameSelectionPage;

public class ExternalImportInputWizard extends Wizard{

	private IProject project;

	private NameSelectionPage nameSelectionPage;
	private ExternalModelsSelectionPage importModelSelectionPage;
	private ImportAlternativeOptionsPage optionsPage;
	
	public ExternalImportInputWizard(IProject project) {
		
		this.project = project;
		
		setWindowTitle("Analyser - External project import");
		
		nameSelectionPage = new NameSelectionPage();

		importModelSelectionPage = new ExternalModelsSelectionPage(null,ModelType.GROUP_PCM_EXTENDED);
		
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
		
		ValidationDiagramService.showStatus(project, alternative);
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
