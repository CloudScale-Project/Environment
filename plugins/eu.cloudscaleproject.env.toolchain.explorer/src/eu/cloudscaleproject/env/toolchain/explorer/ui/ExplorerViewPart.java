package eu.cloudscaleproject.env.toolchain.explorer.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
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

import eu.cloudscaleproject.env.common.BatchExecutor;
import eu.cloudscaleproject.env.toolchain.explorer.Explorer;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerConstants;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

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
	private MPart part;
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
	public void postConstruct(Composite parent) {
		
		Explorer explorer = Explorer.getInstance();
		IExplorerNode rootNode = explorer.getRoot();
		rootNode.getContext().setParent(part.getContext());
		
		rootNode.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				final IExplorerNode node = (IExplorerNode)evt.getSource();
						
				if(IExplorerNode.PROP_REFRESH.equals(evt.getPropertyName())){
					BatchExecutor.getInstance().addUITask(this, "refresh", new Runnable() {
						
						@Override
						public void run() {
							if(!treeViewer.getTree().isDisposed()){
								treeViewer.refresh(node);
							}
						}
					});
				}
				if(IExplorerNode.PROP_CHILD_ADDED.equals(evt.getPropertyName())){
					BatchExecutor.getInstance().addUITask(this, "refresh", new Runnable() {
						
						@Override
						public void run() {
							if(!treeViewer.getTree().isDisposed()){
								treeViewer.refresh(node);
							}
						}
					});
				}
				if(IExplorerNode.PROP_CHILD_REMOVED.equals(evt.getPropertyName())){
					BatchExecutor.getInstance().addUITask(this, "refresh", new Runnable() {
						
						@Override
						public void run() {
							if(!treeViewer.getTree().isDisposed()){
								treeViewer.refresh(node);
							}
						}
					});
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
	
	@Inject
	@Optional
	private void linkWithEditor(MApplication application, 
								EModelService modelService, 
								@Named(IExplorerConstants.LINK_WITH_EDITOR) Boolean enable){
		
		this.linkWithEditor = enable;
		if(enable){
			MPartStack stack = (MPartStack)modelService.find("org.eclipse.e4.primaryDataStack", application);
			MStackElement element = stack.getSelectedElement();
			
			if(element instanceof MPart){
				MPart part = (MPart)element;
				ExplorerEditorNode node = part.getContext().get(ExplorerEditorNode.class);
				if(node != null){
					if(treeViewer != null && !treeViewer.getTree().isDisposed()){
						treeViewer.setSelection(new StructuredSelection(node), true);
					}
				}
			}
			
		}
	}
	
	/*
	@Inject
	@Optional
	private void activeContext(@Active IEclipseContext context){
		
	}
	
	@Inject
	@Optional
	private void activeAlternative(MPart part, @Active IEditorInputResource alternative){
		part.getContext().set(IEditorInputResource.class, alternative);
	}
	
	@Inject
	@Optional
	private void activeValidationStatusProvider(MPart part, @Active IValidationStatusProvider vp){
		part.getContext().set(IValidationStatusProvider.class, vp);

	}
	
	@Inject
	@Optional
	private void activeProject(MPart part, @Active IProject project){
		part.getContext().set(IProject.class, project);
	}
	*/
	
	@PreDestroy
	public void preDestroy() {
		this.partService.removePartListener(partServiceListener);
		this.selectionService.removePostSelectionListener(selectionListener);
	}
	
	@Focus
	public void onFocus(){
	}

}
