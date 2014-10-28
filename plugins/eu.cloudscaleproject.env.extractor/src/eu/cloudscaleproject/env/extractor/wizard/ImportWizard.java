package eu.cloudscaleproject.env.extractor.wizard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
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
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ExternalConnection;
import org.scaledl.overview.architecture.UsageProxy;
import org.scaledl.overview.converter.IOverviewConverter;

import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.system.System;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.extractor.wizard.util.IWizardPageControll;
import eu.cloudscaleproject.env.extractor.wizard.util.WizardData;

public class ImportWizard extends Wizard implements IWorkbenchWizard {

	private WizardData data = new WizardData();
	private SelectModelWizardPage selectModelPage = new SelectModelWizardPage(data);
	private DeploymentWizardPage deploymentWizardPage = new DeploymentWizardPage(data);
	private InterfacesWizardPage interfacesWizardPage = new InterfacesWizardPage(data);
	//private FinishWizardPage finishWizardPage = new FinishWizardPage(data);
	private WizardDialog dialog;
	private IWizardPageControll currentPage;


	public ImportWizard() {
		setWindowTitle("ScaleDL Overview Import Wizard");
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		if (project == null)
			project = ExplorerProjectPaths.getProjectFromExplorerSelection();
		
		if (project == null)
			throw new IllegalStateException("Import not possible if project is not known.");

		data.setProject(project);
	}

	@Override
	public void addPages() {
		this.addPage(selectModelPage);
		this.addPage(deploymentWizardPage);
		this.addPage(interfacesWizardPage);
		//this.addPage(finishWizardPage);
		
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
		IProject project = data.getProject();

		Repository repositoryModel = data.getRepositoryModel();
		System systemModel = data.getSystemModel();
		
		URI repURI_external = repositoryModel.eResource().getURI();
		URI sysURI_external = systemModel.eResource().getURI();
		
		IPath repLocation = null;
		IPath sysLocation = null;
		IFile repImportFile = null;
		IFile sysImportFile = null;

		if (repURI_external.isPlatform() && sysURI_external.isPlatform())
		{
			repLocation = new Path(repURI_external.toPlatformString(true));
			sysLocation = new Path(sysURI_external.toPlatformString(true));
			repImportFile = ResourcesPlugin.getWorkspace().getRoot().getFile(repLocation);
			sysImportFile = ResourcesPlugin.getWorkspace().getRoot().getFile(sysLocation);
		}
		else
		{
			IWorkspace workspace= ResourcesPlugin.getWorkspace();    

			repLocation = Path.fromOSString(repURI_external.toFileString()); 
			sysLocation = Path.fromOSString(sysURI_external.toFileString()); 
			repImportFile= workspace.getRoot().getFileForLocation(repLocation);
			sysImportFile= workspace.getRoot().getFileForLocation(sysLocation);
		}


		IFolder scaledlFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_SCALEDL);
		IFolder importFolder = scaledlFolder.getFolder("Import");
		if (!importFolder.exists()) importFolder.create(true, true, null);

		importFolder = importFolder.getFolder(data.getSoftwareService().getName());
		if (!importFolder.exists()) importFolder.create(true, true, null);
		
		//
		// Create model fiels and copy external model files into it
		//
		IFile repositoryFile = importFolder.getFile(repImportFile.getName());
		IFile systemFile = importFolder.getFile(sysImportFile.getName());
		
		// Delete files if already exists
		if (repositoryFile.exists()) repositoryFile.delete(true, null);
		if (systemFile.exists()) systemFile.delete(true, null);
		
		// Copy 
		IPath projectPath = new Path("/"+project.getName());
		repImportFile.copy(projectPath.append(repositoryFile.getProjectRelativePath()), true, null);
		sysImportFile.copy(projectPath.append(systemFile.getProjectRelativePath()), true, null);

		//repositoryFile.create(new FileInputStream(repositoryFile_external), true, null);
		//systemFile.create(new FileInputStream(systemFile_external), true, null);

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
	
	private void createUsageProxy ()
	{
		// Lastly create UsageProxy and connect imported app service
		UsageProxy usageProxy = ArchitectureFactory.eINSTANCE.createUsageProxy();
		Overview overview = this.data.getOverviewModel();

		overview.getArchitecture().getProxies().add(usageProxy);
		ExternalConnection connection = ArchitectureFactory.eINSTANCE.createExternalConnection();
		connection.setTarget(this.data.getSoftwareService());
		connection.setSource(usageProxy);
		usageProxy.getRequiredInterfaces().addAll(this.data.getSoftwareService().getProvidedInterfaces());
		overview.getArchitecture().getExternalConnections().add(connection);
	}
	
	private void mergeOverviewModel () throws Exception
	{
		Overview overviewToMerge = data.getOverviewModel();
		URI overviewURI = getOverviewModelURI();
		ResourceSet resSet = new ResourceSetImpl();
		final Resource overviewRes = resSet.createResource(overviewURI);
		overviewRes.load(null);
		Overview overview = (Overview) overviewRes.getContents().get(0);

		// UsageProxy 
		// If empty just copy it, otherwise copy contents and external connection
		if (overview.getArchitecture().getProxies().isEmpty())
		{
			overview.getArchitecture().getProxies().add(overviewToMerge.getArchitecture().getProxies().get(0));
			overview.getArchitecture().getExternalConnections().addAll (overviewToMerge.getArchitecture().getExternalConnections());
		}
		else
		{
			UsageProxy usageProxy = (UsageProxy) overview.getArchitecture().getProxies().get(0);
			UsageProxy usageProxyToMerge = (UsageProxy) overviewToMerge.getArchitecture().getProxies().get(0);
			for (OperationInterface oi : new LinkedList<OperationInterface>(usageProxyToMerge.getRequiredInterfaces()))
			{
				oi.getRequiringContainer().clear();
			}

			usageProxy.getRequiredInterfaces().addAll(this.data.getSoftwareService().getProvidedInterfaces());


			ExternalConnection externalConnection = overviewToMerge.getArchitecture().getExternalConnections().get(0);
			externalConnection.setSource(usageProxy);
			overview.getArchitecture().getExternalConnections().add(externalConnection);
		}

		// // // // // // // // //
		// Other stuff
		overview.getDataTypes().getTypes().addAll(overviewToMerge.getDataTypes().getTypes());
		overview.getDefinition().getDescriptors().addAll(overviewToMerge.getDefinition().getDescriptors());
		overview.getDeployment().getServiceDeployments().addAll(overviewToMerge.getDeployment().getServiceDeployments());

		// // // // // // // // //
		// CloudEnvironment
		CloudEnvironment ceToMerge = overviewToMerge.getArchitecture().getCloudEnvironments().get(0);
		CloudEnvironment ce = overview.getArchitecture().getCloudEnvironments().get(0);
			
		ce.getSoftwareLayer().getServices().addAll(ceToMerge.getSoftwareLayer().getServices());
		ce.getPlatformLayer().getServices().addAll(ceToMerge.getPlatformLayer().getServices());
		ce.getInfrastructureLayer().getServices().addAll(ceToMerge.getInfrastructureLayer().getServices());
			
		ce.getInternalConnections().addAll(ceToMerge.getInternalConnections());

		
		overviewRes.save(null);
		
	}
	
	private IFile getOverviewFile()
	{
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		String overviewFilePath = ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FILE_OVERVIEW_MODEL);
		IFile overviewFile = project.getFile(overviewFilePath);
		return overviewFile;
	}
	
	private IFile getOverviewDiagramFile()
	{
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		String overviewFilePath = ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FILE_OVERVIEW_DIAGRAM);
		IFile overviewDiagramFile = project.getFile(overviewFilePath);
		return overviewDiagramFile;
	}

	private URI getOverviewModelURI()
	{
		IFile overviewFile = getOverviewFile();
		URI overviewURI = URI.createPlatformResourceURI(overviewFile.getFullPath().toString(), true);

		return overviewURI;
	}
	
	private URI getOverviewDiagramURI()
	{
		IFile overviewFile = getOverviewDiagramFile();
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

			createUsageProxy();
			
			if (getOverviewFile().exists())
			{
				mergeOverviewModel();
			}
			else
			{
				saveOverviewModel();
			}
			
			if (!getOverviewDiagramFile().exists())
			{
				createOverviewDiagram();
			}
			
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
