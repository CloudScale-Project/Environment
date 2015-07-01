package eu.cloudscaleproject.env.toolchain.util;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class EMFPopupMenuSupport implements IMenuListener{
	
	private final Object root;
	private final EditingDomain editingDomain;
	private StructuredViewer viewer;
			
	public EMFPopupMenuSupport(Object root, EditingDomain ed){
		this.root = root;
		this.editingDomain = ed;		
	}
	
	public void setViewer(IEditorSite site, StructuredViewer viewer){
		this.viewer = viewer;
		
		MenuManager mm = createContextMenuFor(viewer);
		if(site != null){
			site.registerContextMenu(mm, new UnwrappingSelectionProvider(viewer));
		}
	}

	private final MenuManager createContextMenuFor(StructuredViewer viewer) {
		
		MenuManager contextMenu = new MenuManager("#PopUp", "eu.cloudscale.env.toolcahin.treeview.menumanager");
		contextMenu.add(new Separator("additions"));
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		Menu menu = contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance(), LocalSelectionTransfer.getTransfer(), FileTransfer.getInstance() };
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(editingDomain, viewer));
		
		return contextMenu;
	}
	
	public void menuAboutToShow(IMenuManager menuManager) {
		
		Collection<?> newChildDescriptors = null;
		Collection<?> newSiblingDescriptors = null;
		
		IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
		
		if (selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1) {
			Object object = ((IStructuredSelection)selection).getFirstElement();

			newChildDescriptors = editingDomain.getNewChildDescriptors(object, null);
			newSiblingDescriptors = editingDomain.getNewChildDescriptors(null, object);
		}
		else if(root != null){
			selection = new StructuredSelection(root);
			newChildDescriptors = editingDomain.getNewChildDescriptors(root, null);
			newSiblingDescriptors = editingDomain.getNewChildDescriptors(null, root);
		}
		
		Collection<IAction> createChildActions = generateCreateChildActions(newChildDescriptors, selection);
		Collection<IAction> createSiblingActions = generateCreateSiblingActions(newSiblingDescriptors, selection);

		Collection<IAction> defaultActions = generateDefaultActions(selection);
		
		IMenuManager createChildMenuManager = new MenuManager("New child");
		IMenuManager createSiblingMenuManager = new MenuManager("New sibling");

		for(IAction action : createChildActions){
			createChildMenuManager.add(action);
		}
		for(IAction action : createSiblingActions){
			createSiblingMenuManager.add(action);
		}
		
		menuManager.add(createChildMenuManager);
		menuManager.add(createSiblingMenuManager);

		menuManager.add(new Separator("default"));
		
		for(IAction action : defaultActions){
			menuManager.add(action);
		}
	}
	
	protected Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			for (Object descriptor : descriptors) {
				actions.add(new CreateChildAction(editingDomain, selection, descriptor));
			}
		}
		return actions;
	}
	
	protected Collection<IAction> generateCreateSiblingActions(Collection<?> descriptors, ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			for (Object descriptor : descriptors) {
				actions.add(new CreateSiblingAction(editingDomain, selection, descriptor));
			}
		}
		return actions;
	}
	
	protected Collection<IAction> generateDefaultActions(ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
	    ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
	    IStructuredSelection strucSelection = (StructuredSelection)selection;
	    
	    {
			UndoAction action = new UndoAction(editingDomain);
			action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
			actions.add(action);
		}
		{
			RedoAction action = new RedoAction(editingDomain);
			action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
			actions.add(action);
		}

		boolean isRootObject = false;
		Object selected = strucSelection.getFirstElement();
		if (selected instanceof EObject) {
			if (EcoreUtil.getRootContainer((EObject)selected) == selected) {
				isRootObject = true;
			}
		}
		
		if(!isRootObject){
		    {
		    	CopyAction action = new CopyAction(editingDomain);
				action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
				action.updateSelection(strucSelection);
				actions.add(action);
			}
		    {
		    	CutAction action = new CutAction(editingDomain);
				action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
				action.updateSelection(strucSelection);
				actions.add(action);
			}
		    {
		    	PasteAction action = new PasteAction(editingDomain);
				action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
				action.updateSelection(strucSelection);
				actions.add(action);
			}
			{
				DeleteAction action = new DeleteAction(editingDomain, true);
				action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
				action.updateSelection(strucSelection);
				actions.add(action);
			}
		}
		else{
			//TODO: find better solution
			//Hack - injecting editing domain into action
			ValidateAction action = new ValidateAction(){
				public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart){
					this.domain = editingDomain;
				}
			};
			action.setActiveWorkbenchPart(null);
			
			action.notifyResult(true);
			action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_OBJS_WARN_TSK));
			action.updateSelection(strucSelection);
			
			actions.add(action);
		}
		
		return actions;
	}
	
}
