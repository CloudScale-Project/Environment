package eu.cloudscaleproject.env.toolchain.explorer.ui;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.explorer.Explorer;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerViewPart {
	
	private Composite composite;
	private TreeViewer treeViewer;
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		
		Explorer explorer = Explorer.getInstance();
		
		this.composite = new Composite(parent, SWT.NONE);
		this.composite.setLayout(new FillLayout());
		
		this.treeViewer = new TreeViewer(composite);
		
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
