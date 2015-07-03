package eu.cloudscaleproject.env.csm2pcm.wizard;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.csm2pcm.wizard.pages.TransformWizardPage;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.ExternalModelsSelectionPage;

public class TransformIntoNewAlternativeWizard extends Wizard{
	
	private IProject project;
	
	private AlternativeNamePage nameSelectionPage;
	private TransformWizardPage transformPage;
	private ExternalModelsSelectionPage importSelectionPage;

	private IFolder folder;
	
	public TransformIntoNewAlternativeWizard(IProject project) {
		this.project = project;
		
		folder = createTransformOutputFolder(project);
		nameSelectionPage = new AlternativeNamePage(ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_INPUT));
		transformPage = new TransformWizardPage(project, folder);
		importSelectionPage = new ExternalModelsSelectionPage(
									"Transformed models selection", 
									"Please select desired models to import into the new alternative.", folder, ModelType.GROUP_PCM_EXTENDED, null);
	}
	
	private IFolder createTransformOutputFolder(IProject project){
		IFolder generatedFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_GENERATED);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String date = df.format(new Date(System.currentTimeMillis()));
		
		IFolder outputFolder = ExplorerProjectPaths.getNonexistingSubFolder(generatedFolder, "PCM_"+ date);
		try {
			outputFolder.create(true, true, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return outputFolder;
	}
	
	@Override
	public void addPages() {
		addPage(nameSelectionPage);
		addPage(transformPage);
		addPage(importSelectionPage);
	}
	
	public boolean performFinish(){
		
		String name = nameSelectionPage.getName();

		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_INPUT);
		final InputAlternative newInputAlternative = (InputAlternative)rp.createNewResource(name, null);
		
		newInputAlternative.createSubResources(new Runnable() {
			
			@Override
			public void run() {
				Resource[] selectedResources = importSelectionPage.getSelectedResources();
				Resource[] selectedDiagramResources = importSelectionPage.getSelectedDiagramResources();

				ExplorerProjectPaths.copyEMFResources(newInputAlternative.getResource(), selectedResources);
				ExplorerProjectPaths.copyEMFResources(newInputAlternative.getResource(), selectedDiagramResources);
				
				for (Resource resource : selectedResources)
				{
					IFile f = ExplorerProjectPaths.getFileFromEmfResource(resource);
					newInputAlternative.addSubResourceModel(f);
				}
				
			}
		});
		
		newInputAlternative.save();
		
		OpenAlternativeUtil.openAlternative(newInputAlternative);
		//ValidationDiagramService.showDiagram(project);
		//ValidationDiagramService.showStatus(project, newInputAlternative);
		
		return true;
	}
	
	@Override
	public boolean performCancel()
	{
		if (folder.exists())
		{
			try
			{
				folder.delete(true, null);
			} catch (CoreException e)
			{
				e.printStackTrace();
			}
		}

		return super.performCancel();
	}
	
	/*
	@Override
	public boolean performFinish() {
		
		final String name = nameSelectionPage.getName();
		
		//execute transformation
		TransformHandler runHandler = new TransformHandler();
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.OVERVIEW_ID);
		IFile file = (IFile)rp.getResources().get(0).getResource();
		
		runHandler.execute(file, new BasicCallback<IFolder>() {
			
			@Override
			public void handle(final IFolder outputFolder) {
				
				Display.getDefault().syncExec(new Runnable() {
					
					@Override
					public void run() {
						
						ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID);
						InputAlternative newInputAlternative = (InputAlternative)rp.createNewResource(name, null);
						
						newInputAlternative.importFromFolder(outputFolder);
						newInputAlternative.save();
						
						ImportPCMModelWizard importPCMModelWizard = new ImportPCMModelWizard(newInputAlternative, outputFolder);
						WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), importPCMModelWizard);
						wizardDialog.open();
					}
				});
			}
		});
		
		return true;
	}
	*/
	
	@Override
	public boolean canFinish()
	{
		if (getContainer().getCurrentPage() == getPages()[getPageCount()-1])
			return true;

		return false;
	}
	
}
