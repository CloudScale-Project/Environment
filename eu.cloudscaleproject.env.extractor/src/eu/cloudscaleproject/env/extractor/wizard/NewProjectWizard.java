package eu.cloudscaleproject.env.extractor.wizard;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;

public class NewProjectWizard implements NewProjectExtension{

	@Override
	public void finalize(IProject p) {
		// TODO Auto-generated method stub
		
	    try {
	    	//Util.copyConfigurationFiles(p, "Alternative 1");
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	        //Put your exception handler here if you wish to
	    }
	}

}
