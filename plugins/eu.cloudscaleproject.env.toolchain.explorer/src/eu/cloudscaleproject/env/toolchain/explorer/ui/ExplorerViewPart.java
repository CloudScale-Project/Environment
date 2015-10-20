package eu.cloudscaleproject.env.toolchain.explorer.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.IPartListener;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.explorer.Explorer;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerConstants;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerViewPart {
	
	private static final String POPUP_MENU_ELEMENT = "eu.cloudscaleproject.env.toolchain.explorer.popupmenu";
	
	private Composite composite;
	private TreeViewer treeViewer;
	
	@Inject
	private EMenuService menuService;
	@Inject
	private ESelectionService selectionService;
	@Inject
	private EPartService partService;
	
	private boolean linkWithEditor = false;
	
	private final IPartListener partServiceListener = new IPartListener() {
		
		@Override
		public void partVisible(MPart part) {}
		@Override
		public void partHidden(MPart part) {}
		@Override
		public void partDeactivated(MPart part) {}
		@Override
		public void partBroughtToTop(MPart part) {}
		
		@Override
		public void partActivated(MPart part) {
			if(linkWithEditor){
				ExplorerEditorNode node = part.getContext().get(ExplorerEditorNode.class);
				if(node != null){
					//selectionService.setSelection(node);
					treeViewer.setSelection(new StructuredSelection(node));
				}
			}
		}
	};
	
	private final ISelectionListener selectionListener = new ISelectionListener() {
		
		@Override
		public void selectionChanged(MPart part, Object selection) {
			if(linkWithEditor){
				if(selection instanceof ExplorerEditorNode){
					ExplorerEditorNode node = (ExplorerEditorNode)selection;
					node.openEditor();
				}
			}
		}
	};
	
	@PostConstruct
	public void postConstruct(final MPart part, Composite parent) {
		
		part.getContext().declareModifiable(IEditorInputResource.class);
		part.getContext().declareModifiable(IValidationStatusProvider.class);
		part.getContext().declareModifiable(IProject.class);
		part.getContext().declareModifiable(IExplorerNode.class);
		
		final Explorer explorer = Explorer.getInstance();
		IExplorerNode rootNode = explorer.getRoot();
		rootNode.getContext().setParent(part.getContext());
		
		rootNode.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				final IExplorerNode node = (IExplorerNode)evt.getSource();
						
				if(IExplorerNode.PROP_REFRESH.equals(evt.getPropertyName())){
					refresh(node);
				}
				if(IExplorerNode.PROP_CHILD_ADDED.equals(evt.getPropertyName())){
					refresh(node);
				}
				if(IExplorerNode.PROP_CHILD_REMOVED.equals(evt.getPropertyName())){
					refresh(node);
				}
				
			}
		});
		
		this.composite = new Composite(parent, SWT.NONE);
		this.composite.setLayout(new FillLayout());
		
		this.treeViewer = new TreeViewer(composite);
		this.treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
						
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				
				if(selection == null){
					return;
				}
				
				IExplorerNode node = (IExplorerNode)selection.getFirstElement();
				if(node != null){
					node.onSelect();
					
					part.getContext().set(IEditorInputResource.class, explorer.findTreeContextData(node, IEditorInputResource.class));
					part.getContext().set(ResourceProvider.class, explorer.findTreeContextData(node, ResourceProvider.class));
					part.getContext().set(IValidationStatusProvider.class, explorer.findTreeContextData(node, IValidationStatusProvider.class));
					part.getContext().set(IProject.class, explorer.findTreeContextData(node, IProject.class));
				}
				
				selectionService.setSelection(selection.size() == 1 ? node : selection.toArray());
			}
		});
		
		this.treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				
				if(selection == null){
					return;
				}
				
				IExplorerNode node = (IExplorerNode)selection.getFirstElement();
				if(node instanceof ExplorerEditorNode){
					
					final ExplorerEditorNode editorNode = (ExplorerEditorNode)node;
					BusyIndicator.showWhile(Display.getDefault(), new Runnable() {
						
						@Override
						public void run() {
							editorNode.openEditor();
						}
					});
					
				}
				else if(node.hasChildren()){
					boolean expanded = treeViewer.getExpandedState(node);
					treeViewer.setExpandedState(node, !expanded);
				}
			}
		});
		
		this.treeViewer.setContentProvider(new ExplorerContentProvider());
		this.treeViewer.setLabelProvider(
				new org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider(new ExplorerLabelProvider()));
		
		this.treeViewer.setInput(explorer.getRoot());
		
		menuService.registerContextMenu(this.treeViewer.getControl(), POPUP_MENU_ELEMENT);
		
		this.partService.addPartListener(partServiceListener);
		this.selectionService.addPostSelectionListener(part.getElementId(), selectionListener);
	}
	
	private void refresh(final IExplorerNode node){
		/*
		BatchExecutor.getInstance().addUITask(node, "refresh", new Runnable() {
			
			@Override
			public void run() {
				if(!treeViewer.getTree().isDisposed()){
					treeViewer.refresh(node);
				}
			}
		});
		*/
		
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				if(treeViewer != null && !treeViewer.getTree().isDisposed() && !treeViewer.isBusy()){
					treeViewer.refresh(node);
				}
			}
		});
	}
	
	@Inject
	@Optional
	private void linkWithEditor(MApplication application, 
								EModelService modelService, 
								@Named(IExplorerConstants.LINK_WITH_EDITOR) Boolean enable,
								@Active MPart part){
		
		this.linkWithEditor = enable;
		if(enable){
			ExplorerEditorNode node = part.getContext().get(ExplorerEditorNode.class);
			if(node != null){
				if(treeViewer != null && !treeViewer.getTree().isDisposed()){
					treeViewer.setSelection(new StructuredSelection(node), true);
				}
			}
		}
	}
	
	@Inject
	@Optional
	private void selectNode(IExplorerNode node){
		if(treeViewer == null || treeViewer.getTree().isDisposed()){
			return;
		}
		
		treeViewer.expandToLevel(node, TreeViewer.ALL_LEVELS);
		treeViewer.setSelection(new StructuredSelection(node), true);
	}
	
	@PreDestroy
	public void preDestroy() {
		this.partService.removePartListener(partServiceListener);
		this.selectionService.removePostSelectionListener(selectionListener);
	}
	
	@Focus
	public void onFocus(){
	}

}
