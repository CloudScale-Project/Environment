package eu.cloudscaleproject.env.csm.diagram.editor.extractor.wizard;

import java.io.File;
import java.io.FileInputStream;

import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;

import eu.cloudscaleproject.env.csm.architecture.DeployableApplicationService;
import eu.cloudscaleproject.env.csm.converter.ICsmConverter;

public class ImportModelWizard extends Wizard {
	
	private SelectModelPage selectPage;
	private DeployableApplicationService applicationService;

	public ImportModelWizard(DeployableApplicationService das ) {
		setWindowTitle("New Wizard");
		this.applicationService = das;
	}

	@Override
	public void addPages() {
		selectPage = new SelectModelPage();
		addPage(selectPage);
	}

	@Override
	public boolean performFinish() {
		
		File resourceFile = new File (selectPage.getResourceModel());
		File systemFile = new File (selectPage.getSystemModel());
		
		// Import files relative to CSM model 
		IFile csmFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(applicationService.eResource().getURI().toPlatformString(true)));
		Folder parent = (Folder)csmFile.getParent();
		
		try {
			
			// Folder for imported models  
			IFolder importFolder = parent.getFolder("pcm");
			if (!importFolder.exists()) importFolder.create(true, true, null);
			importFolder = importFolder.getFolder("imported");
			if (!importFolder.exists()) importFolder.create(true, true, null);
			importFolder = importFolder.getFolder(applicationService.getRuntimePlatformService().getName());
			if (!importFolder.exists()) importFolder.create(true, true, null);
			
			// Project files for imported models
			IFile resourceImportFile = importFolder.getFile(resourceFile.getName());
			IFile systemImportFile = importFolder.getFile(systemFile.getName());
			
			// Delete files if already exists
			if (resourceImportFile.exists()) resourceImportFile.delete(true, null);
			if (systemImportFile.exists()) systemImportFile.delete(true, null);
			
			// Copy selected files into project
			resourceImportFile.create(new FileInputStream(resourceFile), true, null);
			systemImportFile.create(new FileInputStream(systemFile), true, null);
			
			final URI resourceURI = URI.createPlatformResourceURI(resourceImportFile.getFullPath().toString(), true);
			final URI systemURI = URI.createPlatformResourceURI(systemImportFile.getFullPath().toString(), true);
			
			//
			// Write model location into CSM module
			//
			TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(applicationService.eResource().getResourceSet());
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					applicationService.getAvMap().put(ICsmConverter.KEY_RESOURCE_MODEL, resourceURI.toPlatformString(false));
					applicationService.getAvMap().put(ICsmConverter.KEY_SYSTEM_MODEL, systemURI.toPlatformString(false));
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
			
			// TODO Error dialog
			
			return false;
		}
		
		return true;
	}
	

}
