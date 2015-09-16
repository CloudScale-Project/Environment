package eu.cloudscaleproject.env.toolchain.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;
import eu.cloudscaleproject.env.toolchain.editors.ProjectEditor;

public class ProjectWizardExtension implements NewProjectExtension{

	@Override
	public void finalize(IProject p) {
		
		/*
		 * Show dashboard when new project is created
		 */
		IFile projectFile = p.getFile(ExplorerProjectPaths.FILE_PROJECT_PROPERTIES);
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
	}

}
