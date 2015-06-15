 
package org.scaledl.overview.diagram.handlers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class OpenDiagramHandler {
	@Execute
	public void execute() {
		
		//TODO: reimplement
		/*
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		IFile file = ExplorerProjectPaths.getProjectFile(project, ExplorerProjectPaths.KEY_FILE_OVERVIEW_DIAGRAM);
		
		if (!file.exists())
		{
			
		}

		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			IDE.openEditor(page, file);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
		
}