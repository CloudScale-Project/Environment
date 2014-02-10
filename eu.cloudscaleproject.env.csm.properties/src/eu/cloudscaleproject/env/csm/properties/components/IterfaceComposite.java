package eu.cloudscaleproject.env.csm.properties.components;

import java.util.Arrays;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.ui.internal.parts.directedit.TextCellEditor;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
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

import eu.cloudscaleproject.env.csm.core.CoreFactory;
import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.core.CorePackage.Literals;
import eu.cloudscaleproject.env.csm.core.Operation;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.TypeEnum;
import eu.cloudscaleproject.env.csm.diagram.SWTResourceManager;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class IterfaceComposite extends Composite {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private TransactionalEditingDomain editingDomain;
	private Table table;
	private TableViewer tableViewer;
	private TableColumn tblclmnReturnValue;
	private TableViewerColumn tableViewerColumn;
	private TableColumn tblclmnName;
	private TableViewerColumn tableViewerColumn_1;
	private TableColumn tblclmnParameters;
	private TableViewerColumn tableViewerColumn_2;
	private Button btnRemove;

	private OperationInterface providedInterface;
	private Button btnAddOperation;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public IterfaceComposite(Composite parent, OperationInterface opInterface) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		this.providedInterface = opInterface;
		
		ResourceSet rset = this.providedInterface.eResource().getResourceSet();  
		this.editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(rset);
		
		
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		FormData fd_table = new FormData();
		fd_table.left = new FormAttachment(0, 10);
		fd_table.right = new FormAttachment(100, -47);
		fd_table.bottom = new FormAttachment(100, -10);
		fd_table.top = new FormAttachment(0, 10);
		table.setLayoutData(fd_table);
		
		
		tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnReturnValue = tableViewerColumn.getColumn();
		tblclmnReturnValue.setWidth(100);
		tblclmnReturnValue.setText("Return value");
		
		tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnName = tableViewerColumn_1.getColumn();
		tblclmnName.setWidth(133);
		tblclmnName.setText("Name");
		
		tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnParameters = tableViewerColumn_2.getColumn();
		tblclmnParameters.setWidth(100);
		tblclmnParameters.setText("Parameters");
		
		tableViewerColumn.setEditingSupport(new ReturnValueEditingSupport(tableViewer));
		tableViewerColumn_1.setEditingSupport(new MethodNameEditingSupport(tableViewer));
		tableViewerColumn_2.setEditingSupport(new ParametersEditingSupport(tableViewer));
		
		// No ADD and REMOVE functionality for external models
		if (!CsmUtil.hasExternalModel(opInterface))
		{
			btnAddOperation = new Button(this, SWT.NONE);
			FormData fd_btnAddOperation = new FormData();
			fd_btnAddOperation.right = new FormAttachment(100, -10);
			btnAddOperation.setLayoutData(fd_btnAddOperation);
			btnAddOperation.setText("+");
			fd_btnAddOperation.top = new FormAttachment(table, 0, SWT.TOP);
			fd_btnAddOperation.left = new FormAttachment(table, 6);
			
			btnRemove = new Button(this, SWT.NONE);
			btnRemove.setText("-");
			FormData fd_btnRemove = new FormData();
			fd_btnRemove.left = new FormAttachment(btnAddOperation, 0, SWT.LEFT);
			fd_btnRemove.top = new FormAttachment(btnAddOperation, 6);
			fd_btnRemove.right = new FormAttachment(btnAddOperation, 0, SWT.RIGHT);
			btnRemove.setLayoutData(fd_btnRemove);
			
			
			//
			// Add / Remove support
			//
			btnAddOperation.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Operation operation = CoreFactory.eINSTANCE.createOperation();
					editingDomain.getCommandStack().execute(
							AddCommand.create(editingDomain, providedInterface, 
									CorePackage.OPERATION_INTERFACE__OPERATIONS, operation));
				}
			});
			
			btnRemove.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) { 
					IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
					if (!selection.isEmpty())
					{
						Operation operation = (Operation) selection.getFirstElement();
						
						editingDomain.getCommandStack().execute(
								RemoveCommand.create(editingDomain, providedInterface, 
										CorePackage.OPERATION_INTERFACE__OPERATIONS, operation));
					}
				}
			});
		}
		//
		// Double click to edit support
		//
		ColumnViewerEditorActivationStrategy activationSupport = new ColumnViewerEditorActivationStrategy(tableViewer) {
		    protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
		        // Enable editor only with mouse double click
		        if (event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION) {
		        	
		        	// Do not allow edit, if using external model
		        	if (CsmUtil.hasExternalModel(providedInterface)) return false;
		        		
		            EventObject source = event.sourceEvent;
		            if (source instanceof MouseEvent && ((MouseEvent)source).button == 3)
		                return false;

		            return true;
		        }

		        return false;
		    }
		};
		
		m_bindingContext = initDataBindings();
		TableViewerEditor.create(tableViewer, activationSupport, ColumnViewerEditor.TABBING_HORIZONTAL | 
		    ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR | 
		    ColumnViewerEditor.TABBING_VERTICAL |
		    ColumnViewerEditor.KEYBOARD_ACTIVATION);
		
		
		table.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				
				// Problem on windows machines - last column is empty
				// Works! - I don't care...
			    int width = table.getClientArea().width;
		        for ( TableColumn column : table.getColumns())
		        {
		            column.setWidth( width/3 - 10 );
		        }
			}
		});
	}
	
	public OperationInterface getProvidedInterface() {
		return providedInterface;
	}
	
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = EMFEditObservables.observeMaps(editingDomain, listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.OPERATION__RETURN_VALUE, Literals.OPERATION__METHOD_NAME, Literals.OPERATION__PARAMETERS});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList providedInterfaceOperationsObserveList = EMFEditObservables.observeList(Realm.getDefault(), editingDomain, providedInterface, Literals.OPERATION_INTERFACE__OPERATIONS);
		tableViewer.setInput(providedInterfaceOperationsObserveList);
		//
		
		return bindingContext;
	}
	
	
	/*
	 * 
	 * EDITING SUPORT CLASSES
	 * 
	 */
	
	private class ReturnValueEditingSupport extends EditingSupport {

		  private final TableViewer viewer;

		  public ReturnValueEditingSupport(TableViewer viewer) {
		    super(viewer);
		    this.viewer = viewer;
		  }

		  @Override
		  protected CellEditor getCellEditor(Object element) {
			
			List<?> list = Arrays.asList(TypeEnum.values());
		    return new ExtendedComboBoxCellEditor(viewer.getTable(), list, new LabelProvider(){
		    	@Override
		    	public String getText(Object element) {
		    		TypeEnum te = (TypeEnum) element;
		    		return te.getLiteral();
		    	}
		    });
		  }

		  @Override
		  protected boolean canEdit(Object element) {
		    return true;
		  }

		  @Override
		  protected Object getValue(Object element) {
			  Operation operation = (Operation) element;
			  return operation.getReturnValue();
		  }

		  @Override
		  protected void setValue(final Object element, final Object value) {
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						// TODO Auto-generated method stub
						((Operation)element).setReturnValue((TypeEnum)value);
					}
				});
		  }
		} 
	private class MethodNameEditingSupport extends EditingSupport {

		  private final TableViewer viewer;

		  public MethodNameEditingSupport(TableViewer viewer) {
		    super(viewer);
		    this.viewer = viewer;
		  }

		  @Override
		  protected CellEditor getCellEditor(Object element) {
		    return new TextCellEditor(viewer.getTable());
		  }

		  @Override
		  protected boolean canEdit(Object element) {
		    return true;
		  }

		  @Override
		  protected Object getValue(Object element) {
			  Operation o = (Operation) element;
			  if (o.getMethodName() == null) return "";
			  else return o.getMethodName();
		  }

		  @Override
		  protected void setValue(final Object element, final Object value) {
			  
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						// TODO Auto-generated method stub
						((Operation)element).setMethodName(String.valueOf(value));
					}
				});
		  }
		} 
	
	private class ParametersEditingSupport extends EditingSupport {

		  private final TableViewer viewer;

		  public ParametersEditingSupport(TableViewer viewer) {
		    super(viewer);
		    this.viewer = viewer;
		  }

		  @Override
		  protected CellEditor getCellEditor(Object element) {
			
		    return new TextCellEditor(viewer.getTable());
		  }

		  @Override
		  protected boolean canEdit(Object element) {
		    return true;
		  }

		  @Override
		  protected Object getValue(Object element) {
			  Operation operation = (Operation) element;
			  
			  List<TypeEnum> parameters = operation.getParameters();
			  
			  StringBuilder builder = new StringBuilder("");
			  
			  for (TypeEnum p : parameters) builder.append(p.getLiteral()).append(", ");
			  if (parameters.size() > 0) builder.deleteCharAt(builder.length() - 1);
			  
			  return builder.toString();
		  }

		  @Override
		  protected void setValue(final Object element, final Object value) {
			  
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						// TODO Auto-generated method stub
						  String s = value.toString();
						  String[] split = s.split(",");
						((Operation)element).getParameters().clear();
						  for (String typeString : split) {
							  typeString = typeString.trim();
							  TypeEnum te = TypeEnum.get(typeString.toUpperCase());
							  if (te != null)
							  {
								((Operation)element).getParameters().add(te);
							  }
						   }
			  
					}
				});
		  }
		} 
}
