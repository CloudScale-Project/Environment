package eu.cloudscaleproject.env.csm.diagram.editor.extractor.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.converter.ConverterService;
import eu.cloudscaleproject.env.csm.converter.ICsmConverter;
import eu.cloudscaleproject.env.csm.diagram.wizard.CloudScaleProjectWizard;

public class ImportModelWizard extends Wizard {
	
	private SelectModelPage selectPage;
	private DeployableSoftwareService applicationService;

	public ImportModelWizard(DeployableSoftwareService das ) {
		setWindowTitle("Import PCM model wizard");
		this.applicationService = das;
	}

	@Override
	public void addPages() {
		selectPage = new SelectModelPage();
		addPage(selectPage);
	}

	@Override
	public boolean performFinish() {
		
		File repositoryFile = new File (selectPage.getResourceModel());
		File systemFile = new File (selectPage.getSystemModel());
		
		// Import files relative to CSM model 
		IFile csmFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(applicationService.eResource().getURI().toPlatformString(true)));
		Folder parent = (Folder)csmFile.getParent();
		
		try {
			
			IFolder importFolder = getImportedFolder(csmFile.getProject());
			
			// Just in case
			if (importFolder == null)
			{
				importFolder = parent.getFolder("pcm");
				if (!importFolder.exists()) importFolder.create(true, true, null);
				importFolder = importFolder.getFolder("imported");
				if (!importFolder.exists()) importFolder.create(true, true, null);
			}
			
			importFolder = importFolder.getFolder(applicationService.getName());
			if (!importFolder.exists()) importFolder.create(true, true, null);
			
			// Project files for imported models
			IFile repositoryImportFile = importFolder.getFile(repositoryFile.getName());
			IFile systemImportFile = importFolder.getFile(systemFile.getName());
			
			// Delete files if already exists
			if (repositoryImportFile.exists()) repositoryImportFile.delete(true, null);
			if (systemImportFile.exists()) systemImportFile.delete(true, null);
			
			// Copy selected files into project
			repositoryImportFile.create(new FileInputStream(repositoryFile), true, null);
			systemImportFile.create(new FileInputStream(systemFile), true, null);
			
			final URI resourceURI = URI.createPlatformResourceURI(repositoryImportFile.getFullPath().toString(), true);
			final URI systemURI = URI.createPlatformResourceURI(systemImportFile.getFullPath().toString(), true);
			
			ResourceSet resSet = new ResourceSetImpl();
			final Resource systemRes = resSet.createResource(systemURI);
			final Resource repositoryRes = resSet.createResource(resourceURI);
			
			repositoryRes.unload();
			systemRes.unload();
			repositoryRes.load(null);
			systemRes.load(null);
			
			TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(applicationService.eResource().getResourceSet());
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					EObject sys = systemRes.getContents().get(0);
					EObject rep = repositoryRes.getContents().get(0);
					applicationService.getAeMap().put(ICsmConverter.KEY_PCM_SYSTEM, sys );
					applicationService.getAeMap().put(ICsmConverter.KEY_PCM_REPOSITORY, rep );
				}
			});
			
			new Job("Import models") {
				
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					//
					// Write model location into CSM module
					//
							
					if(systemRes.getContents().isEmpty() || repositoryRes.getContents().isEmpty()){
						return Status.CANCEL_STATUS;
					}
					
					EObject sys = systemRes.getContents().get(0);
					EObject rep = repositoryRes.getContents().get(0);
					List<EObject> toImport = new ArrayList<EObject>();
					toImport.add(rep);
					toImport.add(sys);
					
					ConverterService.getInstance().addExternalModel(applicationService, toImport, null);
					
					return Status.OK_STATUS;
				}
			}.schedule();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	private IFolder getImportedFolder (IProject project) {
		try
		{
			IFile file = project.getFile(CloudScaleProjectWizard.PROJECT_FILE);
			Properties p = new Properties();
			p.load(file.getContents(true));
			String genFolderName = p.getProperty(CloudScaleProjectWizard.KEY_FOLDER_IMPORT);
			IFolder folder = project.getFolder(genFolderName);
			if (!folder.exists())
				folder.create(true, true, new NullProgressMonitor());
				
			return folder;
		}
		catch (Exception e)
		{
			System.out.println("Exception durring finding generated models folder: "+e.getMessage());
		}
		
		return null;
	}

}
