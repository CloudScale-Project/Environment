package eu.cloudscaleproject.env.toolchain.wizard;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;

public class ProjectWizardExtension implements NewProjectExtension{

	@Override
	public void finalize(IProject p) {
		// TODO Auto-generated method stub
		
		/*
		 * Show dashboard when new project is created
		 * 
		IFile projectFile = p.getFile(ExplorerProjectPaths.FILE_PROJECT_PROPERTIES);
		IEditorInput editorInput = new FileEditorInput(projectFile);
	    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	 
	    try {
	        ProjectEditor editor = (ProjectEditor)IDE.openEditor(page, editorInput, "eu.cloudscaleproject.env.toolchain.tooleditor");
	        editor.getSite().getPage().activate(editor);
	    } catch ( PartInitException e ) {
	        //Put your exception handler here if you wish to
	    }
	    */
	}

}
