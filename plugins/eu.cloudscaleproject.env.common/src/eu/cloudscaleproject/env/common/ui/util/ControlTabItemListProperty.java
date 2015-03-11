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

public class ControlTabItemListProperty extends WidgetListProperty {
		
	public Object getElementType() {
		return Composite.class;
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
		return new WidgetListener(this, listener, new int[]{1 << 2}, new int[]{});
	}
	
	protected void doUpdateControlList(final Control control, ListDiff diff) {
		diff.accept(new ListDiffVisitor() {
			
			CTabFolder tf = (CTabFolder) control;

			public void handleAdd(int index, Object element) {
				CTabItem item = new CTabItem(tf, SWT.CLOSE);
				item.setText("Neki");
				item.setControl((Composite)element);
				tf.redraw();
				tf.layout(true, true);
			}

			public void handleRemove(int index, Object element) {
				for(CTabItem item : tf.getItems()){
					if(element != null && element.equals(item.getControl())){
						item.getControl().dispose();
						item.dispose();
					}
				}
				
				tf.redraw();
				tf.layout(true, true);
			}

			public void handleReplace(int index, Object oldElement, Object newElement) {
				handleRemove(0, oldElement);
				handleAdd(0, newElement);
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
