package eu.cloudscaleproject.env.usageevolution.wizard;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.DIExtension;
import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;

public class NewProjectWizard extends DIExtension implements NewProjectExtension{

	@Override
	public void finalize(IProject p) {
		//create or retrieve ScaleDL folder
		//String folder = ExplorerProjectPaths.getProjectProperty(p, ExplorerProjectPaths.KEY_FOLDER_SCALEDL);
		//nothing to do for now..
	}

}
