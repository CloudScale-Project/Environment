package eu.cloudscaleproject.env.method.viewer.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;
import eu.cloudscaleproject.env.method.viewer.Util;

public class ProjectWizard implements NewProjectExtension {

	public ProjectWizard() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void finalize(IProject p) {
		String path = ExplorerProjectPaths.getProjectProperty(p, ExplorerProjectPaths.FILE_METHOD_NEW, "method.workflow");
		IFile file = p.getFile(path);
		Util.createMethod(file);
	}

}
