package eu.cloudscaleproject.env.toolchain.explorer.style;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public abstract class AbstractLabelStyle implements IStyledLabelProvider{

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void removeListener(ILabelProviderListener listener) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void dispose() {}
}
