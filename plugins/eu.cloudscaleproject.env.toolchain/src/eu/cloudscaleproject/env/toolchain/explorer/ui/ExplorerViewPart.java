package eu.cloudscaleproject.env.toolchain.explorer.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.BatchExecutor;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.explorer.Explorer;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerEditorNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerResourceNode;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerUtils;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerViewPart {
	
	private static final String POPUP_MENU_ELEMENT = "eu.cloudscaleproject.env.toolchain.popupmenu.explorer";
	
	private Composite composite;
	private TreeViewer treeViewer;
	
	@Inject
	private MPart part;
	
	@Inject
	private EMenuService menuService;
	
	@Inject
	private ESelectionService selectionService;
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		
		Explorer explorer = Explorer.getInstance();
		IExplorerNode rootNode = explorer.getRoot();
		rootNode.getContext().setParent(part.getContext());
		
		rootNode.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				final IExplorerNode node = (IExplorerNode)evt.getSource();
								
				if(IExplorerNode.PROP_CHILD_ADDED.equals(evt.getPropertyName())){
					BatchExecutor.getInstance().addUITask(this, new Runnable() {
						
						@Override
						public void run() {
							if(!treeViewer.getTree().isDisposed()){
								treeViewer.refresh(node);
							}
						}
					});
				}
				if(IExplorerNode.PROP_CHILD_REMOVED.equals(evt.getPropertyName())){
					BatchExecutor.getInstance().addUITask(this, new Runnable() {
						
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
			
			ExplorerResourceNode lastProjectNode = null;
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				
				if(selection == null){
					return;
				}
				
				IExplorerNode node = (IExplorerNode)selection.getFirstElement();
				
				final ExplorerResourceNode projectNode = ExplorerUtils.getProjectNode(node);
				if(projectNode != null && lastProjectNode != projectNode){
					
					BusyIndicator.showWhile(Display.getDefault(), new Runnable() {
						@Override
						public void run() {
							ValidationDiagramService.showDiagram((IProject)projectNode.getResource());
						}
					});
					
				}
				lastProjectNode = projectNode;
				
				if(node != null){
					node.onSelect();
				}
				
				selectionService.setSelection(node);
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
			}
		});
		
		this.treeViewer.setContentProvider(new ExplorerContentProvider());
		this.treeViewer.setLabelProvider(new ExplorerLabelProvider());
		
		this.treeViewer.setInput(explorer.getRoot());
		
		menuService.registerContextMenu(this.treeViewer.getControl(), POPUP_MENU_ELEMENT);
	}
	
	@PreDestroy
	public void preDestroy() {
	}
	
	@Focus
	public void onFocus(){
	}

}
