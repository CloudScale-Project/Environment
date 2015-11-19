package eu.cloudscaleproject.env.extractor.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class ShowJavaProjectHandler {
	
	@Execute
	public void execute(IResource resource){
		try {
			IProject project = (IProject)resource;
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IViewPart viewPart = page.findView("org.eclipse.ui.navigator.ProjectExplorer");
			page.showView("org.eclipse.ui.navigator.ProjectExplorer");
			ISelectionProvider selProvider = viewPart.getSite().getSelectionProvider();
			selProvider.setSelection(new StructuredSelection(project));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
