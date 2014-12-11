package eu.cloudscaleproject.env.method.viewer.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;
import eu.cloudscaleproject.env.method.viewer.Util;

public class ProjectWizardExtension implements NewProjectExtension {

	public ProjectWizardExtension() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void finalize(IProject p) {
		String path = ExplorerProjectPaths.getProjectProperty(p, ExplorerProjectPaths.FILE_METHOD_NEW, "method.workflow");
		IFile file = p.getFile(path);
		Util.createMethod(file);
	    
		//open workflow diagram
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			IDE.openEditor(page, file);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
