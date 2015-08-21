package eu.cloudscaleproject.env.toolchain.explorer.handlers;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class DeleteExplorerNodeHandler {
	
	@Execute
	public void execute(ESelectionService selectionService) {
		
		Object selection = selectionService.getSelection();
		
		if(selection instanceof Object[]){
			for(Object o : (Object[])selection){
				
				if(o instanceof ExplorerResourceNode){
					final ExplorerEditorNode node = (ExplorerEditorNode)o;					
					deleteNode(node);
				}
			}
		}
		else if(selection instanceof ExplorerResourceNode){
			final ExplorerResourceNode node = (ExplorerResourceNode)selection;
			deleteNode(node);
		}
		
	}
	
	@CanExecute
	public boolean canExecute(ESelectionService selectionService){
		
		Object selection = selectionService.getSelection();
		
		if(selection instanceof Object[]){
			for(Object o : (Object[])selection){			
				if(!(o instanceof ExplorerEditorNode)){
					return false;
				}
			}
		}
		else if(!(selection instanceof ExplorerEditorNode)){
			return false;
		}
		
		return true;
	}
	
	private void deleteNode(final ExplorerResourceNode node){
		WorkspaceJob job = new WorkspaceJob("Deleting resource...") {
			
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				
				monitor.beginTask("Deleting resource...", IProgressMonitor.UNKNOWN);
				
				Display.getDefault().syncExec(new Runnable() {
					
					@Override
					public void run() {
						node.dispose();
						try {
							node.getResource().delete(true, null);
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				});
				
				monitor.done();
				
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule();
	}
	
}
