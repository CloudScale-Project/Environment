package eu.cloudscaleproject.env.common.ui.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffVisitor;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.jface.databinding.swt.WidgetListProperty;
import org.eclipse.jface.internal.databinding.swt.WidgetListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import eu.cloudscaleproject.env.common.ui.CompositeContainer;
import eu.cloudscaleproject.env.common.ui.CompositeContainerChild;

public class CompositeContainerChildProperty extends WidgetListProperty {
		
	public Object getElementType() {
		return CompositeContainerChild.class;
	}

	@SuppressWarnings("rawtypes")
	protected void doSetList(Object source, List list, ListDiff diff) {
		doUpdateList(source, diff);
	}

	protected void doUpdateList(Object source, ListDiff diff) {
		doUpdateControlList((Control) source, diff);
	}
	
	@SuppressWarnings("rawtypes")
	protected List doGetList(Object source) {
		Composite[] list = doGetControlList((Control) source);
		return Arrays.asList(list);
	}

	public INativePropertyListener adaptListener(
			final ISimplePropertyListener listener) {
		return new WidgetListener(this, listener, new int[]{SWT.CHANGED}, new int[]{});
	}
	
	protected void doUpdateControlList(final Control control, ListDiff diff) {
		diff.accept(new ListDiffVisitor() {
			
			CompositeContainer composite = (CompositeContainer) control;

			public void handleAdd(int index, Object element) {
				
				assert(element instanceof CompositeContainerChild);
				CompositeContainerChild child = (CompositeContainerChild)element;
				composite.addChild(index, child);				
				composite.redraw();
				composite.layout(true, true);
			}

			public void handleRemove(int index, Object element) {
				
				assert(element instanceof CompositeContainerChild);
				CompositeContainerChild child = (CompositeContainerChild)element;
				composite.removeChild(index, child);
				composite.redraw();
				composite.layout(true, true);
			}

			public void handleReplace(int index, Object oldElement, Object newElement) {
				handleRemove(index, oldElement);
				handleAdd(index, newElement);
			}
		});
	}
	
	protected Composite[] doGetControlList(Control control) {
		CTabItem[] items = ((CTabFolder)control).getItems();		
		List<Composite> composites = new ArrayList<Composite>();
		
		for(int i=0; i<items.length; i++){
			if(items[i].isDisposed()){
				continue;
			}
			composites.add((Composite)items[i].getControl());
		}
		return composites.toArray(new Composite[composites.size()]);
	}
}
