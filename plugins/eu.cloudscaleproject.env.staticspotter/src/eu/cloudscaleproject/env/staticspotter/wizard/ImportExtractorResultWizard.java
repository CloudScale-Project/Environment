package eu.cloudscaleproject.env.staticspotter.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.staticspotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeSelectionPage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.NameSelectionPage;

public class ImportExtractorResultWizard extends Wizard
{

	private IProject project;

	private NameSelectionPage nameSelectionPage;
	private AlternativeSelectionPage alternativeSelectionPage;

	public ImportExtractorResultWizard(IProject project)
	{

		this.project = project;

		nameSelectionPage = new NameSelectionPage();
		nameSelectionPage.setDescription("Please type in name for the new alternative");

		alternativeSelectionPage = new AlternativeSelectionPage(ResourceRegistry.getInstance().getResourceProvider(project,
				ToolchainUtils.EXTRACTOR_RES_ID));
		alternativeSelectionPage.setDescription("Please select extractor result to be imported into Static Spotter");

	}

	@Override
	public void addPages()
	{
		addPage(nameSelectionPage);
		addPage(alternativeSelectionPage);
	}

	@Override
	public boolean performFinish()
	{

		String altName = nameSelectionPage.getName();

		// Prepare Input Alternative
		ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_STA_INPUT_ID);
		InputAlternative alternative = (InputAlternative) provider.createNewResource(altName, null);

		// Copy all models from Extractor Result
		EditorInputEMF selection = (EditorInputEMF) alternativeSelectionPage.getSelection();
		ResourceSetImpl rs = new ResourceSetImpl();

		for (ModelType type : ModelType.GROUP_SOURCEDECORATOR)
		{
			for (IFile file : ExplorerProjectPaths.findResources(selection.getResource(), type.getFileExtension()))
			{
				ExplorerProjectPaths.getEmfResource(rs, file);
			}
		}

		Resource[] resources = rs.getResources().toArray(new Resource[0]);

		ExplorerProjectPaths.copyEMFResources(alternative.getResource(), resources);

		for (Resource resource : resources)
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
		if (getContainer().getCurrentPage() == getPages()[getPageCount() - 1])
			return true;

		return false;
	}

}
