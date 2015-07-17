package eu.cloudscaleproject.env.analyser.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeSelectionPage;

public class ExtractorImportInputWizard extends Wizard
{

	private IProject project;

	private AlternativeNamePage nameSelectionPage;
	private AlternativeSelectionPage alternativeSelectionPage;

	public ExtractorImportInputWizard(IProject project)
	{
		this.project = project;
		
		setWindowTitle("Analyser - Extractor result import");

		nameSelectionPage = new AlternativeNamePage(ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_INPUT));
		nameSelectionPage.setDescription("Please type in name for the new alternative");

		alternativeSelectionPage = new AlternativeSelectionPage(ResourceRegistry.getInstance().getResourceProvider(project,CSTool.EXTRACTOR_RES));
		alternativeSelectionPage.setDescription("Please select extractor result to be imported into Analyser");
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
		ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_INPUT);
		InputAlternative alternative = (InputAlternative) provider.createNewResource(altName, null);

		// Copy all models from Extractor Result
		EditorInputEMF selection = (EditorInputEMF) alternativeSelectionPage.getSelection();
		ResourceSetImpl rs = new ResourceSetImpl();

		for (ModelType type : ModelType.GROUP_PCM)
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

		ValidationDiagramService.showStatus(project, CSTool.ANALYSER_INPUT.getID(), alternative);
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
