package eu.cloudscaleproject.env.toolchain.explorer.ui;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import eu.cloudscaleproject.env.toolchain.explorer.IExplorerNode;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ExplorerLabelProvider implements ILabelProvider{

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof IExplorerNode){
			IExplorerNode node = (IExplorerNode)element;
			return node.getName();
		}
		return element.toString();
	}
	
	@Override
	public void addListener(ILabelProviderListener listener) {		
	}
	
	@Override
	public void removeListener(ILabelProviderListener listener) {
	}
	
	@Override
	public void dispose() {
	}

}
