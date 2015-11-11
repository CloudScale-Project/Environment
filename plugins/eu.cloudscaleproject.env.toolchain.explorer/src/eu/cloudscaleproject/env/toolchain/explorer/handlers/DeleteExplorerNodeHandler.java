package eu.cloudscaleproject.env.toolchain.explorer.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.BatchExecutor;
import eu.cloudscaleproject.env.toolchain.explorer.Explorer;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IInputAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IResultAlternative;

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
			
			List<ExplorerResourceNode> nodes = new ArrayList<ExplorerResourceNode>();

			for(Object o : (Object[])selection){
				if(o instanceof ExplorerResourceNode){
					ExplorerResourceNode node = (ExplorerResourceNode)o;
					if(node.canDelete()){
						nodes.add(node);
					}
				}
			}
			
			boolean delete = MessageDialog.openConfirm(Display.getDefault().getActiveShell(), 
					"Delete", "Are you sure you want to delete "+ nodes.size() + " nodes?");
			
			if(delete){
				for(ExplorerResourceNode node : nodes){
					deleteNode(node);
				}
			}
		}
		else if(selection instanceof ExplorerResourceNode){

			final ExplorerResourceNode node = (ExplorerResourceNode)selection;

			boolean delete = MessageDialog.openConfirm(Display.getDefault().getActiveShell(), 
					"Delete", "Are you sure you want to delete '" + node.getName() + "' ?");
			
			if(delete){
				deleteNode(node);
			}
		}
		
	}
	
	@CanExecute
	public boolean canExecute(ESelectionService selectionService){
		
		Object selection = selectionService.getSelection();
		
		if(selection instanceof Object[]){
			for(Object o : (Object[])selection){
				if(!canDelete(o)){
					return false;
				}
			}
		}
		else if(!canDelete(selection)){
			return false;
		}
		
		return true;
	}
	
	private boolean canDelete(Object object){
		if(object instanceof ExplorerResourceNode){
			ExplorerResourceNode node = (ExplorerResourceNode)object;
			return node.canDelete();
		}
		return false;
	}
	
	private void deleteNode(final ExplorerResourceNode node){
		
		WorkspaceJob job = new WorkspaceJob("Deleting resource...") {
			
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				
				monitor.beginTask("Deleting resource...", IProgressMonitor.UNKNOWN);
				
				final IExplorerNode nodeSibling = getNodeSibling(node);
				node.dispose();

				BatchExecutor.getInstance().addUITask(DeleteExplorerNodeHandler.this, "updateSelection", new Runnable() {
					
					@Override
					public void run() {
						if(nodeSibling != null && !nodeSibling.isDisposed()){
							Explorer.getInstance().setSelection(nodeSibling);
						}
					}
				});

				IEditorInputResource eir = ResourceRegistry.getInstance().findResource(node.getResource());

				if(eir != null){
					
					if(eir.getResource() != node.getResource()){

						//deleting sub resource
						if(eir instanceof EditorInputFolder){
							EditorInputFolder eif = (EditorInputFolder)eir;
							eif.deleteSubResource(node.getResource());
						}
						else{
							node.getResource().delete(IProject.FORCE, null);
						}
						
					}
					else{
						if(eir instanceof IInputAlternative){
						
							IInputAlternative ia = (IInputAlternative) eir;

							for (IResultAlternative ra : ia.getResultAlternatives()) {
								ra.delete();
							}

							for (IConfigAlternative ca : ia.getConfigAlternatives()) {
								ca.delete();
							}
						}
						
						eir.delete();
					}
				}
				else{
					node.getResource().delete(IProject.FORCE, null);
				}
				
				/*
				if(eir.getResource() != node.getResource()){
					//deleting sub resource
					if(eir instanceof EditorInputFolder){
						EditorInputFolder eif = (EditorInputFolder)eir;
						eif.deleteSubResource(node.getResource());
					}
				}
				else{
					if (eir instanceof IInputAlternative)
					{
						IInputAlternative ia = (IInputAlternative) eir;

						for (IResultAlternative ra : ia.getResultAlternatives())
						{
							ra.delete();
						}

						for (IConfigAlternative ca : ia.getConfigAlternatives())
						{
							
							ca.delete();
						}
						
						ia.delete();
					}
					else
					{
						monitor.beginTask("Deleting resource '"+ node.getResource().getName() +"'", IProgressMonitor.UNKNOWN);
						node.getResource().delete(IProject.FORCE, null);
					}
				}
				*/
				
				monitor.done();
				
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule();
	}
	
	private IExplorerNode getNodeSibling(IExplorerNode node){
		
		IExplorerNode parent = node.getParent();
		if(parent == null){
			return null;
		}
		
		IExplorerNode nodeToSelect = parent;
		
		if(parent != null){
			
			List<IExplorerNode> nodes = Arrays.asList(parent.getChildren());
			if(nodes.size() > 1){
				//find sibling
				int indexToSelect = 1;
				int index = nodes.indexOf(node);
				if(index > 0){
					indexToSelect = index-1;
				}
				
				nodeToSelect = nodes.get(indexToSelect);
			}
		}
		
		return nodeToSelect;
	}
	
}
