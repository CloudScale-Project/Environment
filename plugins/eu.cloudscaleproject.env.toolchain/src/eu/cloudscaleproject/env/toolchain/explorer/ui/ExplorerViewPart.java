package eu.cloudscaleproject.env.toolchain.explorer.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.explorer.Explorer;
import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerViewPart {
	
	private Composite composite;
	private TreeViewer treeViewer;
	
	@Inject
	private MPart part;
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		
		Explorer explorer = Explorer.getInstance();
		IExplorerNode rootNode = explorer.getRoot();
		rootNode.getContext().setParent(part.getContext());
		
		rootNode.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				IExplorerNode node = (IExplorerNode)evt.getSource();
				
				if(IExplorerNode.PROP_CHILD_ADDED.equals(evt.getPropertyName())){
					treeViewer.refresh(node);
				}
				if(IExplorerNode.PROP_CHILD_REMOVED.equals(evt.getPropertyName())){
					treeViewer.refresh(node);
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
				IExplorerNode node = (IExplorerNode)selection.getFirstElement();
				node.onSelect();
			}
		});
		
		this.treeViewer.setContentProvider(new ExplorerContentProvider());
		this.treeViewer.setLabelProvider(new ExplorerLabelProvider());
		
		this.treeViewer.setInput(explorer.getRoot());
		
	}
	
	@PreDestroy
	public void preDestroy() {
	}
	
	@Focus
	public void onFocus(){
	}

}
