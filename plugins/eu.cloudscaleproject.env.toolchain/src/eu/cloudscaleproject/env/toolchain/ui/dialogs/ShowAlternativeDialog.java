package eu.cloudscaleproject.env.toolchain.ui.dialogs;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ShowAlternativeDialog extends Dialog{
	
	private List<? extends IEditorInputResource> alternatives;
	private ListViewer listViewer;
	private IEditorInputResource selection;

	public ShowAlternativeDialog(List<? extends IEditorInputResource> alternatives) {
		super(Display.getDefault().getActiveShell());
		this.setBlockOnOpen(true);		
		
		this.alternatives = alternatives;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		listViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		org.eclipse.swt.widgets.List list = listViewer.getList();
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selection = (IEditorInputResource)((StructuredSelection)listViewer.getSelection()).getFirstElement();
			}
		});
		
		initDataBindings();
		
		return container;
	}
	
	@Override
	protected void okPressed() {
		
		super.okPressed();
		ResourceRegistry.getInstance().openResourceEditor(selection);
	}
	
	
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Show alternative");
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(550, 425);
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = Properties.observeEach(listContentProvider.getKnownElements(), PojoProperties.values(new String[]{"name"}))[0];
		listViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		listViewer.setContentProvider(listContentProvider);
		//
		IObservableList selfList = Properties.selfList(IEditorInputResource.class).observe(alternatives);
		listViewer.setInput(selfList);
		//
		return bindingContext;
	}
}