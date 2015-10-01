package eu.cloudscaleproject.env.toolchain.wizard;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class ProjectWizardExtension implements NewProjectExtension{

	@Override
	public void finalize(IProject p) {
		
		//create tool folders
		ResourceRegistry.getInstance().collectResourceProviders(p);
		
		/*
		 * Show dashboard when new project is created
		 */
		/*
		IFile projectFile = p.getFile(ExplorerProjectPaths.FILE_PROJECT_DASHBOARD);
		IEditorInput editorInput = new FileEditorInput(projectFile);
	    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	 
	    try {
	        ProjectEditor editor = (ProjectEditor)IDE.openEditor(page, editorInput, "eu.cloudscaleproject.env.toolchain.tooleditor");
	        editor.getSite().getPage().activate(editor);
	    } 
	    catch ( PartInitException e ) {
	        //Put your exception handler here if you wish to
	    }
	    catch ( IllegalArgumentException e ) {
	        //Put your exception handler here if you wish to
	    }
	    */
	}

}
