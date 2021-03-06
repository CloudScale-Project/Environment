package org.scaledl.overview.diagram.editor.basic;

import java.util.LinkedList;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.util.OverviewArchitectureUtil;

public class SelectInterfaceDialog extends Dialog {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private Table table;
	private OperationInterfaceContainer applicationService;
	private LinkedList<OperationInterface> possibleInterfaces;
	private TableViewer tableViewer;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SelectInterfaceDialog(Shell parentShell, OperationInterfaceContainer applicationService) {
		super(parentShell);
		
		this.applicationService = applicationService;
		initPossibleInterfaces();
		this.possibleInterfaces.removeAll(applicationService.getRequiredInterfaces());
	}
	

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		tableViewer = new TableViewer(container, SWT.MULTI | SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setWidth(200);
		tableColumn.setText("Required interface");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_1 = tableViewerColumn_1.getColumn();
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("Provider");

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		m_bindingContext = initDataBindings();
	}
	
	@Override
	protected void okPressed() {
		
		final LinkedList<OperationInterface> selectedInterfaces = new LinkedList<OperationInterface>();
		for (TableItem tableItem : tableViewer.getTable().getItems()) {
			
			if (tableItem.getChecked())
			{
				System.out.println("CHECK: "+tableItem.getData());
				selectedInterfaces.add((OperationInterface)tableItem.getData());
			}
		}
		
		if (!selectedInterfaces.isEmpty())
		{
			TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(applicationService.eResource().getResourceSet());
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					applicationService.getRequiredInterfaces().addAll(selectedInterfaces);
				}
			});
		}
		super.okPressed();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(688, 469);
	}
	
	private void initPossibleInterfaces()
	{   
	    this.possibleInterfaces = new LinkedList<OperationInterface>(
	    		OverviewArchitectureUtil.collectPotentialyRequiredInterfaces(applicationService));
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), OperationInterface.class, new String[]{"name", "providingContainer.name"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList selfList = Properties.selfList(OperationInterface.class).observe(possibleInterfaces);
		tableViewer.setInput(selfList);
		//
		return bindingContext;
	}
}
