package eu.cloudscaleproject.env.extractor.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.scaledl.overview.Overview;
import org.scaledl.overview.converter.IOverviewConverter;

import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.system.System;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.extractor.wizard.pages.FinishWizardPage;
import eu.cloudscaleproject.env.extractor.wizard.pages.SelectModelWizardPage;
import eu.cloudscaleproject.env.extractor.wizard.util.IWizardPageControll;
import eu.cloudscaleproject.env.extractor.wizard.util.WizardData;

public class PcmImportWizard extends Wizard implements IWorkbenchWizard {

	private WizardData data = new WizardData();
	private SelectModelWizardPage selectModelPage = new SelectModelWizardPage(data);
	private FinishWizardPage finishWizardPage = new FinishWizardPage(data);
	private WizardDialog dialog;
	private IWizardPageControll currentPage;

	public PcmImportWizard() {
		setWindowTitle("New Wizard");
		
	}

	@Override
	public void addPages() {
		this.addPage(selectModelPage);
		this.addPage(finishWizardPage);
		
	}
	
	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		// TODO Auto-generated method stub
		java.lang.System.out.println("getPreviousPage() : "+page.getClass().getName());
		return super.getPreviousPage(page);
	}
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		
		if (page == selectModelPage && page.isPageComplete())
		{
            java.lang.System.out.println("getNextPage() : "+page.getClass().getName());
			//selectModelPage.performPageComplete();
		}

		return super.getNextPage(page);
	}
	
	
	
	
	@Override
	public void setContainer(IWizardContainer wizardContainer) {
		// TODO Auto-generated method stub
		
        super.setContainer(wizardContainer);
		if (wizardContainer == null)
		{
			if (this.dialog != null)
			{
				this.dialog = null;
			}
			return;
		}

		this.dialog = (WizardDialog)wizardContainer;
		this.dialog.addPageChangingListener(new IPageChangingListener() {
			

			@Override
			public void handlePageChanging(PageChangingEvent event) {
				// TODO Auto-generated method stub
				Object current = event.getCurrentPage();
				Object target = event.getTargetPage();
				
				List<IWizardPage> list = Arrays.asList(getPages());
				int idxCurrent = list.indexOf(current);
				int idxTarget = list.indexOf(target);
				
				java.lang.System.out.println("Current: "+current.getClass().getName()+"  ;;  Target: "+target.getClass().getName());
				if (current instanceof IWizardPageControll)
				{
                        if (idxCurrent < idxTarget)
                        {
                                // perform Next
                                ((IWizardPageControll)current).performNext();
                        }
                        else
                        {
                                // perform Back
                                ((IWizardPageControll)current).performBack();
                        }
				}
				
				if (target instanceof IWizardPageControll)
				{
					((IWizardPageControll)target).performUpdate();
					currentPage = (IWizardPageControll)target;
				}
				else
				{
					currentPage = null;
				}


				
			}
		});
	}
	
	private void saveImportedModels() throws Exception
	{
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();

		Repository repositoryModel = data.getRepositoryModel();
		System systemModel = data.getSystemModel();
		
		URI repURI_external = repositoryModel.eResource().getURI();
		URI sysURI_external = systemModel.eResource().getURI();
		File repositoryFile_external = new File (repURI_external.toFileString());
		File systemFile_external = new File (sysURI_external.toFileString());

		IFolder importFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_IMPORT);

		importFolder = importFolder.getFolder(data.getSoftwareService().getName());
		if (!importFolder.exists()) importFolder.create(true, true, null);
		
		//
		// Create model fiels and copy external model files into it
		//
		IFile repositoryFile = importFolder.getFile(repositoryFile_external.getName());
		IFile systemFile = importFolder.getFile(systemFile_external.getName());
		
		// Delete files if already exists
		if (repositoryFile.exists()) repositoryFile.delete(true, null);
		if (systemFile.exists()) systemFile.delete(true, null);
		
		// Copy 
		repositoryFile.create(new FileInputStream(repositoryFile_external), true, null);
		systemFile.create(new FileInputStream(systemFile_external), true, null);

		final URI repURI = URI.createPlatformResourceURI(repositoryFile.getFullPath().toString(), true);
		final URI sysURI = URI.createPlatformResourceURI(systemFile.getFullPath().toString(), true);


		//
		// Get models (system and repository) and link it to newly created software service
		//
		ResourceSet resSet = new ResourceSetImpl();
		final Resource systemRes = resSet.createResource(sysURI);
		final Resource repositoryRes = resSet.createResource(repURI);
		// !? !? !? 
		repositoryRes.unload();
		systemRes.unload();
		repositoryRes.load(null);
		systemRes.load(null);

		EObject sys = systemRes.getContents().get(0);
		EObject rep = repositoryRes.getContents().get(0);
		this.data.getSoftwareService().getAeMap().put(IOverviewConverter.KEY_PCM_SYSTEM, sys );
		this.data.getSoftwareService().getAeMap().put(IOverviewConverter.KEY_PCM_REPOSITORY, rep );
		
	}
	
	private void saveOverviewModel () throws Exception
	{
		Overview overview = data.getOverviewModel();
		URI overviewURI = getOverviewModelURI();
		ResourceSet resSet = new ResourceSetImpl();
		Resource overviewRes = resSet.createResource(overviewURI);
		overviewRes.getContents().add(overview);
		overviewRes.save(null);
	}
	
	private URI getOverviewModelURI()
	{
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		String overviewFilePath = ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FILE_OVERVIEW_MODEL);
		IFile overviewFile = project.getFile(overviewFilePath);
		URI overviewURI = URI.createPlatformResourceURI(overviewFile.getFullPath().toString(), true);

		return overviewURI;
		
	}
	
	private URI getOverviewDiagramURI()
	{
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		String overviewFilePath = ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FILE_OVERVIEW_DIAGRAM);
		IFile overviewFile = project.getFile(overviewFilePath);
		URI overviewDiagramURI = URI.createPlatformResourceURI(overviewFile.getFullPath().toString(), true);
		return overviewDiagramURI;
	}
	
	private void createOverviewDiagram ()
	{
		URI overviewModelURI = getOverviewModelURI();
		URI overviewDiagramURI = getOverviewDiagramURI();
		
		org.scaledl.overview.diagram.Util.createDiagram(overviewDiagramURI, overviewModelURI);
		
	}
	
	@Override
	public boolean performFinish() {

		if (this.currentPage != null)
		{
			currentPage.performNext();
		}
		try {
			saveImportedModels();

			saveOverviewModel();
			
			createOverviewDiagram();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
		
		return true;
	}
	
	@Override
	public boolean canFinish() {
		if (currentPage == getPages()[getPageCount()-1])
		{
			return true;
		}
		return false;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
	}
}
