package org.scaledl.overview.diagram.editor.basic;

import java.util.EventObject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.scaledl.overview.diagram.SWTResourceManager;
import org.scaledl.overview.diagram.Util;
import org.scaledl.overview.diagram.editor.application.SoftwareServiceEditor;
import org.scaledl.overview.diagram.editor.application.SoftwareServiceEditorInput;
import org.scaledl.overview.diagram.editor.platform.PlatformServiceEditor;
import org.scaledl.overview.diagram.editor.platform.PlatformServiceEditorInput;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.core.CorePackage.Literals;
import org.scaledl.overview.application.ApplicationPackage;
import org.scaledl.overview.application.OperationInterface;

public class RequiredInterfaceComposite extends Composite {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private SoftwareService softwareService;
	private TransactionalEditingDomain editingDomain;
	private Table table;
	private TableViewer tableViewer;
	private TableColumn tblclmnReturnValue;
	private TableViewerColumn tableViewerColumn;
	private TableColumn tblclmnProvider;
	private TableViewerColumn tableViewerColumn_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RequiredInterfaceComposite(Composite parent, SoftwareService applicationService) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		this.softwareService = applicationService;
		// TODO : for each module and for each interface
		
		ResourceSet rset = this.softwareService.eResource().getResourceSet();  
		this.editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(rset);
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(0, 10);
		fd_table.left = new FormAttachment(0, 10);
		fd_table.right = new FormAttachment(100, -49);
		fd_table.bottom = new FormAttachment(100, -10);
		table.setLayoutData(fd_table);
		
		
		tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnReturnValue = tableViewerColumn.getColumn();
		tblclmnReturnValue.setWidth(200);
		tblclmnReturnValue.setText("Required interface");
		
		
		tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnProvider = tableViewerColumn_1.getColumn();
		tblclmnProvider.setWidth(100);
		tblclmnProvider.setText("Provider");
		
		m_bindingContext = initDataBindings();
		
		//
		// Double click to edit support
		//
		ColumnViewerEditorActivationStrategy activationSupport = new ColumnViewerEditorActivationStrategy(tableViewer) {
		    protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
		        // Enable editor only with mouse double click
		        if (event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION) {
		            EventObject source = event.sourceEvent;
		            if (source instanceof MouseEvent && ((MouseEvent)source).button == 3)
		                return false;

		            return true;
		        }

		        return false;
		    }
		};

		TableViewerEditor.create(tableViewer, activationSupport, ColumnViewerEditor.TABBING_HORIZONTAL | 
		    ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR | 
		    ColumnViewerEditor.TABBING_VERTICAL |
		    ColumnViewerEditor.KEYBOARD_ACTIVATION);
		
		Button btnAdd = new Button(this, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SelectInterfaceDialog d = new SelectInterfaceDialog(getShell(), RequiredInterfaceComposite.this.softwareService);
				d.open();
			}
		});
		btnAdd.setText("+");
		FormData fd_btnAdd = new FormData();
		fd_btnAdd.right = new FormAttachment(table, 39, SWT.RIGHT);
		fd_btnAdd.top = new FormAttachment(0, 10);
		fd_btnAdd.left = new FormAttachment(table, 6);
		btnAdd.setLayoutData(fd_btnAdd);
		
		Button btnRemove = new Button(this, SWT.NONE);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				if (!selection.isEmpty())
				{
					final OperationInterface operationInterface = (OperationInterface) selection.getFirstElement();
					
					// First solution does not work properly !??
					/*editingDomain.getCommandStack().execute(
							RemoveCommand.create(editingDomain, RequiredInterfaceComposite.this.applicationService, 
									ArchitecturePackage.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES, operationInterface));*/
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							RequiredInterfaceComposite.this.softwareService.getRequiredInterfaces().remove(operationInterface);
						}
					});
				}
			}
		});
		btnRemove.setText("-");
		FormData fd_btnRemove = new FormData();
		fd_btnRemove.right = new FormAttachment(btnAdd, 0, SWT.RIGHT);
		fd_btnRemove.top = new FormAttachment(btnAdd, 6);
		fd_btnRemove.left = new FormAttachment(table, 6);
		btnRemove.setLayoutData(fd_btnRemove);
		
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				if (!event.getSelection().isEmpty())
				{
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					
					OperationInterface operationInterface = (OperationInterface)selection.getFirstElement();
					
					if (operationInterface.getProvidingContainer() instanceof SoftwareService)
					{
						Util.openEditor(new SoftwareServiceEditorInput(
								(SoftwareService)operationInterface.getProvidingContainer()), 
								SoftwareServiceEditor.ID);
					}
					else 
					{
						Util.openEditor(new PlatformServiceEditorInput(
								(PlatformService)operationInterface.getProvidingContainer()), 
								PlatformServiceEditor.ID);
					}
				}
			}
		});
		
	}
	
	
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = EMFEditObservables.observeMaps(editingDomain, listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.ENTITY__NAME, ApplicationPackage.Literals.OPERATION_INTERFACE__PROVIDING_CONTAINER});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps){
			@Override
			public String getColumnText(Object element, int columnIndex) {
				// TODO Auto-generated method stub
				if (columnIndex == 1)
				{
					EObject container = ((OperationInterface)element).getProvidingContainer();
					if(container != null){
						return ((OperationInterface)element).getProvidingContainer().getName();
					}
					else{
						return "Unresolved Operation Interface.";
					}
				}
				return super.getColumnText(element, columnIndex);
			}
		});
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList applicationServiceRequiredInterfacesObserveList = EMFEditObservables.observeList(Realm.getDefault(), editingDomain, softwareService, ApplicationPackage.Literals.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES);
		tableViewer.setInput(applicationServiceRequiredInterfacesObserveList);
		//
		return bindingContext;
	}
}
