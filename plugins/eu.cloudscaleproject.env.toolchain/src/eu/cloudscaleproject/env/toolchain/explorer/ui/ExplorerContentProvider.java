package eu.cloudscaleproject.env.toolchain.explorer.ui;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerContentProvider implements ITreeContentProvider{

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof IExplorerNode){
			IExplorerNode node = (IExplorerNode)inputElement;
			return node.getChildren();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return getElements(parentElement);
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof IExplorerNode){
			IExplorerNode node = (IExplorerNode)element;
			return node.getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof IExplorerNode){
			IExplorerNode node = (IExplorerNode)element;
			return node.getChildren().length > 0;
		}
		return false;
	}
	
	@Override
	public void dispose() {
		
	}

}
