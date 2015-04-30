package eu.cloudscaleproject.env.common.dialogs;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.SelectionDialog;

public class ListSelectionDialog extends SelectionDialog{
	
	private ListViewer listViewer;
	private ILabelProvider labelProvider;
	
	private final List<? extends Object> content;

	public ListSelectionDialog(String title, String message, ILabelProvider labelProvider, List<? extends Object> content) {
		super(Display.getDefault().getActiveShell());
		
		this.content = content;
		this.labelProvider = labelProvider;
		
		setTitle(title);
        setMessage(message);
        
		this.setBlockOnOpen(true);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 1;
		
		container.setLayout(gridLayout);
		
		Composite message = new Composite(container, SWT.NONE);
		message.setLayout(new FillLayout());
		message.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		createMessageArea(message);
		
		listViewer = new ListViewer(container);
		listViewer.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		listViewer.setLabelProvider(labelProvider);
		listViewer.setContentProvider(new ArrayContentProvider());
		
		listViewer.setInput(content);
		
		listViewer.setSelection(new StructuredSelection(getInitialElementSelections()));
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)listViewer.getSelection();
				setResult(selection.toList());
			}
		});
		return container;
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
