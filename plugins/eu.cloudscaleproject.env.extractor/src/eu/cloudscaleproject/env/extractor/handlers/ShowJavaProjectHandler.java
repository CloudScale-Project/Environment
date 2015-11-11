package eu.cloudscaleproject.env.extractor.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;

public class ShowJavaProjectHandler {
	
	@Execute
	public void execute(IResource resource){
		IProject project = (IProject)resource;
		System.out.println("Action executed on: " + project.getFullPath().toString());
		//TODO:
	}
	
	@CanExecute
	public boolean canExecute(@Optional IResource resource){
		
		if(resource instanceof IProject){
			IProject project = (IProject)resource;
			try {
				if(project.hasNature("org.eclipse.jdt.core.javanature")){
					return true;
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

}
