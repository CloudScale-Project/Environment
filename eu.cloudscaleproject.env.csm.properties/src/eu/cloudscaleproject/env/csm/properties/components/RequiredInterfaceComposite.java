package eu.cloudscaleproject.env.csm.properties.components;

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
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
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

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.core.CorePackage.Literals;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.diagram.SWTResourceManager;
import eu.cloudscaleproject.env.csm.properties.components.dialogs.DialogResolveInterface;
import eu.cloudscaleproject.env.csm.properties.components.dialogs.DialogSelectInterface;

public class RequiredInterfaceComposite extends Composite {

	private DataBindingContext m_bindingContext;
	private TransactionalEditingDomain editingDomain;
	private Table table;
	private TableViewer tableViewer;
	private TableColumn tblclmnReturnValue;
	private TableViewerColumn tableViewerColumn;
	private TableColumn tblclmnProvider;
	private TableViewerColumn tableViewerColumn_1;
	
	private OperationInterfaceContainer opInterfaceContainer;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	
	public void setInput(OperationInterfaceContainer opInterfaceContainer){
		this.opInterfaceContainer = opInterfaceContainer;

		ResourceSet rset = this.opInterfaceContainer.eResource().getResourceSet();  
		this.editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(rset);

		if(m_bindingContext != null){
			m_bindingContext.dispose();
		}
		m_bindingContext = initDataBindings();
	}
	
	public RequiredInterfaceComposite(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		setBounds(10, 10, 350, 250);
		
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
				if(opInterfaceContainer != null){
					DialogSelectInterface d = new DialogSelectInterface(getShell(), RequiredInterfaceComposite.this.opInterfaceContainer);
					d.open();
				}
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
					
					if(editingDomain != null && opInterfaceContainer != null){
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								RequiredInterfaceComposite.this.opInterfaceContainer.getRequiredInterfaces().remove(operationInterface);
							}
						});
					}
				}
			}
		});
		btnRemove.setText("-");
		FormData fd_btnRemove = new FormData();
		fd_btnRemove.right = new FormAttachment(btnAdd, 0, SWT.RIGHT);
		fd_btnRemove.top = new FormAttachment(btnAdd, 6);
		fd_btnRemove.left = new FormAttachment(table, 6);
		btnRemove.setLayoutData(fd_btnRemove);
		
		table.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				
				// Problem on windows machines - last column is empty
				// Works! - I don't care...
			    int width = table.getClientArea().width;
		        for ( TableColumn column : table.getColumns())
		        {
		            column.setWidth( width/2 - 10 );
		        }
			}
		});
		
		
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (!event.getSelection().isEmpty())
				{
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					
					OperationInterface operationInterface = (OperationInterface)selection.getFirstElement();
					resolveInterface(operationInterface);
				}
				
			}
		});
	}
	
	private void resolveInterface (OperationInterface oi)
	{
		DialogResolveInterface d = new DialogResolveInterface(getShell(), RequiredInterfaceComposite.this.opInterfaceContainer, oi);
		d.open();
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = EMFEditObservables.observeMaps(editingDomain, listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.ENTITY__NAME, Literals.OPERATION_INTERFACE__PROVIDING_CONTAINER});
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
		IObservableList applicationServiceRequiredInterfacesObserveList = EMFEditObservables.observeList(Realm.getDefault(), editingDomain, opInterfaceContainer, ArchitecturePackage.Literals.OPERATION_INTERFACE_CONTAINER__REQUIRED_INTERFACES);
		tableViewer.setInput(applicationServiceRequiredInterfacesObserveList);
		//
		return bindingContext;
	}
}
