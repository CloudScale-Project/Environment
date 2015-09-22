package eu.cloudscaleproject.env.toolchain.util;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import eu.cloudscaleproject.env.toolchain.ProjectEditorSelectionService;

public class PropertyPageComposite extends Composite{
	
	private final IPropertySheetPage page;
	
	private final ISelectionChangedListener selectionListener = new ISelectionChangedListener() {
		
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			page.selectionChanged(null, event.getSelection());					
		}
	};

	public PropertyPageComposite(Composite parent, int style, final IPropertySheetPage page) {
		super(parent, style);

		this.page = page;

		this.setLayout(new FillLayout());		
		page.createControl(this);
		
		ProjectEditorSelectionService.getInstance().addPostSelectionChangedListener(selectionListener);
		
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				ProjectEditorSelectionService.getInstance().removePostSelectionChangedListener(selectionListener);
				
			}
		});
	}

}
