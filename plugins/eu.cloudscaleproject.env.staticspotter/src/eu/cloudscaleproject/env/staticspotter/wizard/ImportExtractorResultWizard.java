package eu.cloudscaleproject.env.staticspotter.wizard;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.staticspotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractInputAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeSelectionPage;

public class ImportExtractorResultWizard extends Wizard
{

	private IProject project;

	private AlternativeNamePage nameSelectionPage;
	private AlternativeSelectionPage alternativeSelectionPage;

	public ImportExtractorResultWizard(IProject project)
	{

		this.project = project;

		nameSelectionPage = new AlternativeNamePage(ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.SPOTTER_STA_INPUT));
		nameSelectionPage.setDescription("Please type in name for the new alternative");

		alternativeSelectionPage = new AlternativeSelectionPage(ResourceRegistry.getInstance().getResourceProvider(project,
				CSToolResource.EXTRACTOR_RES));
		alternativeSelectionPage.setDescription("Please select extractor result to be imported into Static Spotter");

	}

	@Override
	public void addPages()
	{
		addPage(nameSelectionPage);
		addPage(alternativeSelectionPage);
	}

	public static final String KEY_FOLDER_SOMOX = "folder_somox";
	public static final String KEY_FOLDER_MODISCO = "folder_modisco";
	public static final String KEY_FILE_MODISCO_JAVA2KDM = "file_modisco_java2kdm";

	@Override
	public boolean performFinish()
	{

		String altName = nameSelectionPage.getName();

		// Prepare Input Alternative
		ResourceProvider provider = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.SPOTTER_STA_INPUT);
		InputAlternative alternative = (InputAlternative) provider.createNewResource(altName, null);

		// Copy all models from Extractor Result
		EditorInputEMF selection = (EditorInputEMF) alternativeSelectionPage.getSelection();
		
		
		try {

			String modiscoFolder = selection.getProperty(ToolchainUtils.KEY_FOLDER_MODISCO);
			String somoxFolder = selection.getProperty(ToolchainUtils.KEY_FOLDER_SOMOX);

			IFolder modisco = selection.getResource().getFolder(modiscoFolder);
			IFolder somox = selection.getResource().getFolder(somoxFolder);

			modisco.copy(alternative.getResource().getFullPath().append(modiscoFolder), true, null);
			somox.copy(alternative.getResource().getFullPath().append(somoxFolder), true, null);
			
			alternative.setProperty(ToolchainUtils.KEY_FOLDER_MODISCO, modiscoFolder);
			alternative.setProperty(ToolchainUtils.KEY_FOLDER_SOMOX, somoxFolder);

			alternative.setProperty(ToolchainUtils.KEY_FILE_MODISCO_JAVA2KDM, selection.getProperty(ToolchainUtils.KEY_FILE_MODISCO_JAVA2KDM));
			alternative.setProperty(ToolchainUtils.KEY_FILE_MODISCO_JAVA, selection.getProperty(ToolchainUtils.KEY_FILE_MODISCO_JAVA));
			alternative.setProperty(ToolchainUtils.KEY_FILE_MODISCO_KDM, selection.getProperty(ToolchainUtils.KEY_FILE_MODISCO_KDM));

			alternative.setProperty(ToolchainUtils.KEY_FILE_REPOSITORY, selection.getProperty(ToolchainUtils.KEY_FILE_REPOSITORY));
			alternative.setProperty(ToolchainUtils.KEY_FILE_SYSTEM, selection.getProperty(ToolchainUtils.KEY_FILE_SYSTEM));
			alternative.setProperty(ToolchainUtils.KEY_FILE_SOURCEDECORATOR, selection.getProperty(ToolchainUtils.KEY_FILE_SOURCEDECORATOR));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		alternative.save();
		alternative.load();
		OpenAlternativeUtil.openAlternative(alternative);

		ResourceProvider configResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.SPOTTER_STA_CONF);
		AbstractConfigAlternative confAlternative = (AbstractConfigAlternative)configResourceProvider.createNewResource("Basic configuration", null);
		confAlternative.setInputAlternative((AbstractInputAlternative)alternative);
		confAlternative.save();

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
