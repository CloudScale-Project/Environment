package eu.cloudscaleproject.env.csm.diagram.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class CloudScaleProjectNature implements IProjectNature {
	 
    public static final String NATURE_ID = "eu.cloudscaleproject.env.projectnature";
     
    @Override
    public void configure() throws CoreException {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void deconfigure() throws CoreException {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public IProject getProject() {
        // TODO Auto-generated method stub
        return null;
    }
 
    @Override
    public void setProject(IProject arg0) {
        // TODO Auto-generated method stub
 
    }
 
}
