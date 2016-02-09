package eu.cloudscaleproject.env.common.wizard.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public abstract class AbstractProjectWizard extends Wizard implements IWorkbenchWizard{
		
	protected IProject project = null;	
	protected ProjectSelectionPage projectSelectionPage = null;
	
	public AbstractProjectWizard(IProject project){
		
		this.project = project;
		
		projectSelectionPage = new ProjectSelectionPage(){
			public void handleSelection(IProject p) {
				if(ExplorerProjectPaths.isCloudScaleProject(p)){
					setProject(p);
				}
			};
		};
		
	}

	public AbstractProjectWizard() {
				
		projectSelectionPage = new ProjectSelectionPage(){
			public void handleSelection(IProject p) {
				if(ExplorerProjectPaths.isCloudScaleProject(p)){
					setProject(p);
				}
			};
		};
		
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
		IProject project = ExplorerProjectPaths.getProject(selection);
		if(ExplorerProjectPaths.isCloudScaleProject(project)){
			setProject(project);
		}
	}
	
	public void setProject(IProject p){
		
		this.project = p;
	}
	
	@Override
	public void addPages() {
		if(this.project == null){
			addPage(projectSelectionPage);
		}
	}
	
	@Override
	public boolean canFinish()
	{
		if (getContainer().getCurrentPage() == getPages()[getPageCount()-1] 
				&& getContainer().getCurrentPage().isPageComplete())
			return true;

		return false;
	}
}
