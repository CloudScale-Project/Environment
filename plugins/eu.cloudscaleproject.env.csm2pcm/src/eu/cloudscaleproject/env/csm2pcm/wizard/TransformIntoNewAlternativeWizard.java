package eu.cloudscaleproject.env.csm2pcm.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.csm2pcm.Activator;
import eu.cloudscaleproject.env.csm2pcm.OverviewConverterNew;
import eu.cloudscaleproject.env.overview.OverviewAlternative;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeNamePage;
import eu.cloudscaleproject.env.toolchain.wizard.pages.AlternativeSelectionPage;

public class TransformIntoNewAlternativeWizard extends Wizard{
	
	private IProject project;
	
	private AlternativeNamePage nameSelectionPage;
	private AlternativeSelectionPage overviewSelectionPage;
	//private TransformWizardPage transformPage;
	//private ExternalModelsSelectionPage importSelectionPage;

	//private IFolder folder;
	
	public TransformIntoNewAlternativeWizard(IProject project) {
		this.project = project;
		
		//folder = createTransformOutputFolder(project);
		nameSelectionPage = new AlternativeNamePage(ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER.getInput().getID()));
		
		ResourceProvider overviewResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.OVERVIEW);
		
		overviewSelectionPage = new AlternativeSelectionPage("Overview alternative selection", 
				"Please select Overview alternative to transform into the Analyser input alternative PCM models.", overviewResourceProvider);
		//transformPage = new TransformWizardPage(project, folder);
		
		/*
		importSelectionPage = new ExternalModelsSelectionPage(
									"Transformed models selection", 
									"Please select desired models to import into the new alternative.", folder, ModelType.GROUP_PCM_EXTENDED, null);
									*/
	}
	
	//OLD Staff
	/*
	private IFolder createTransformOutputFolder(IProject project){
		
		try {
			String generatedFolderName = ExplorerProjectPaths.getProjectProperty(project, 
					ExplorerProjectPaths.FOLDER_GENERATED_KEY, 
					ExplorerProjectPaths.FOLDER_GENERATED_DEFAULT);
	
			IFolder generatedFolder = project.getFolder(generatedFolderName);
			ExplorerProjectPaths.prepareFolder(generatedFolder);
	
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			String date = df.format(new Date(System.currentTimeMillis()));
			
			IFolder outputFolder = ExplorerProjectPaths.getNonexistingSubFolder(generatedFolder, "PCM_"+ date);
			return outputFolder;
		} 
		catch (CoreException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	*/
	
	@Override
	public void addPages() {
		addPage(nameSelectionPage);
		addPage(overviewSelectionPage);
		//addPage(importSelectionPage);
	}
	
	public boolean performFinish(){
		
		String name = nameSelectionPage.getName();

		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.ANALYSER_INPUT);
		final InputAlternative newInputAlternative = (InputAlternative)rp.createNewResource(name, null);
		
		final OverviewAlternative overviewAlternaitve = (OverviewAlternative)overviewSelectionPage.getSelection();
		
		EditorInputJob job = new EditorInputJob("Creating new input alternative", newInputAlternative) {
			
			@Override
			public IStatus execute(IProgressMonitor monitor) {
				
				//OLD Staff
				/*
				Resource[] selectedResources = importSelectionPage.getSelectedResources();
				Resource[] selectedDiagramResources = importSelectionPage.getSelectedDiagramResources();

				monitor.beginTask("Copying models into the new alternative", selectedResources.length * 3 
																		   + selectedDiagramResources.length * 3
																		   + newInputAlternative.getSaveWork());
				
				ExplorerProjectPaths.copyEMFResources(newInputAlternative.getResource(), selectedResources, monitor);
				ExplorerProjectPaths.copyEMFResources(newInputAlternative.getResource(), selectedDiagramResources, monitor);
				
				for (Resource resource : selectedResources)
				{
					IFile f = ExplorerProjectPaths.getFileFromEmfResource(resource);
					newInputAlternative.addSubResourceModel(f);
				}
				*/
				
				OverviewConverterNew.getInstance().transform(overviewAlternaitve, newInputAlternative);
				
				newInputAlternative.save(monitor);
				
				Display.getDefault().syncExec(new Runnable() {
					
					@Override
					public void run() {
						OpenAlternativeUtil.openAlternative(newInputAlternative);
					}
				});
				
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "New alternative has been created.");
			}
		};
		
		job.setUser(true);
		job.schedule();
		
		return true;
	}
	
	@Override
	public boolean canFinish()
	{
		if(overviewSelectionPage.getSelection() == null){
			return false;
		}
		if (getContainer().getCurrentPage() == getPages()[getPageCount()-1]){
			return true;
		}

		return false;
	}
	
}
