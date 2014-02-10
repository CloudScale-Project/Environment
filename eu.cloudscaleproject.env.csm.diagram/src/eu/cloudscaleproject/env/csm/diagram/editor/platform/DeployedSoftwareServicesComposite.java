package eu.cloudscaleproject.env.csm.diagram.editor.platform;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
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
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.DeployableSoftwareService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareService;
import eu.cloudscaleproject.env.csm.architecture.SoftwareServiceContainer;
import eu.cloudscaleproject.env.csm.architecture.impl.ArchitectureFactoryImpl;
import eu.cloudscaleproject.env.csm.core.CorePackage.Literals;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.core.impl.CoreFactoryImpl;
import eu.cloudscaleproject.env.csm.diagram.SWTResourceManager;
import eu.cloudscaleproject.env.csm.diagram.Util;
import eu.cloudscaleproject.env.csm.diagram.editor.application.SoftwareServiceEditor;
import eu.cloudscaleproject.env.csm.diagram.editor.application.SoftwareServiceEditorInput;

public class DeployedSoftwareServicesComposite extends Composite {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private TransactionalEditingDomain editingDomain;
	private Table table;
	private TableViewer tableViewer;
	private TableColumn tblclmnReturnValue;
	private TableViewerColumn tableViewerColumn;
	private SoftwareServiceContainer softwareServiceContainer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DeployedSoftwareServicesComposite(Composite parent, SoftwareServiceContainer appServiceContainer) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		this.softwareServiceContainer = appServiceContainer;
		// TODO : for each module and for each interface
		
		ResourceSet rset = this.softwareServiceContainer.eResource().getResourceSet();  
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
		tblclmnReturnValue.setText("Deployed application services");
		
		m_bindingContext = initDataBindings();
		

		Button btnAdd = new Button(this, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						int postfix = DeployedSoftwareServicesComposite.this.softwareServiceContainer.getSoftwareServices().size()+1;
						
						final DeployableSoftwareService applicationService = ArchitectureFactoryImpl.eINSTANCE.createDeployableSoftwareService();
						applicationService.setName("App service "+postfix);
					
						OperationInterface operationInterface = CoreFactoryImpl.eINSTANCE.createOperationInterface();
						operationInterface.setName("I_AppService_"+postfix);
						applicationService.getProvidedInterfaces().add(operationInterface);
						
						// TODO Auto-generated method stub
						DeployedSoftwareServicesComposite.this.softwareServiceContainer.getSoftwareServices().add(applicationService);
						
						CloudEnvironment ce = (CloudEnvironment) DeployedSoftwareServicesComposite.this.softwareServiceContainer.eContainer().eContainer();
						ce.getSoftwareLayer().getServices().add(applicationService);
					}
				});
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
				
				final IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				if (!selection.isEmpty())
				{
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							// TODO Auto-generated method stub
							SoftwareService ss = (SoftwareService) selection.getFirstElement();
							softwareServiceContainer.getSoftwareServices().remove(ss);
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
					
					SoftwareService applicationService = (SoftwareService)selection.getFirstElement();
					
						Util.openEditor(new SoftwareServiceEditorInput(
								applicationService), SoftwareServiceEditor.ID);
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
		IObservableMap[] observeMaps = EMFEditObservables.observeMaps(editingDomain, listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.ENTITY__NAME});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList appServiceContainerApplicationServicesObserveList = EMFEditObservables.observeList(Realm.getDefault(), editingDomain, softwareServiceContainer, ArchitecturePackage.Literals.SOFTWARE_SERVICE_CONTAINER__SOFTWARE_SERVICES);
		tableViewer.setInput(appServiceContainerApplicationServicesObserveList);
		//
		return bindingContext;
	}
}
