package eu.cloudscaleproject.env.extractor.wizard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabFolderListener;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.scaledl.overview.Overview;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.ComputingInfrastructureService;
import org.scaledl.overview.architecture.InternalConnection;
import org.scaledl.overview.architecture.Service;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.specification.NetworkInfrastructureServiceDescriptor;
import org.scaledl.overview.util.OverviewSpecificationUtil;

import eu.cloudscaleproject.env.extractor.wizard.components.ProvidedServiceComposite;
import eu.cloudscaleproject.env.extractor.wizard.util.IWizardPageControll;
import eu.cloudscaleproject.env.extractor.wizard.util.SwtUtil;
import eu.cloudscaleproject.env.extractor.wizard.util.OverviewHelper;
import eu.cloudscaleproject.env.extractor.wizard.util.WizardData;

public class InterfacesWizardPage extends WizardPage implements IWizardPageControll, Listener{

	private WizardData data;
	private SoftwareService softwareService;
	private ListViewer listViewer;
	private java.util.List<Service> createdServices = new LinkedList<Service>();
	private CTabFolder tabFolder;
	private Button btnAdd;
	private Button btnRemove;
	private Button btnNew;

	/**
	 * Create the wizard.
	 */
	public InterfacesWizardPage(WizardData data) {
		super("wizardPage");
		setTitle("Support services");
		setDescription("Configure support services.");
		
		this.data = data;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	@SuppressWarnings("deprecation")
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(3, false));

		listViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_list.widthHint = 200;
		gd_list.minimumWidth = 200;
		list.setLayoutData(gd_list);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.VERTICAL));
		
		btnNew = new Button(composite, SWT.NONE);
		btnNew.setLayoutData(new RowData(35, 35));
		btnNew.setText("+");
		
		btnAddAll = new Button(composite, SWT.NONE);
		btnAddAll.setLayoutData(new RowData(35, 35));
		btnAddAll.setText(">>");
		
		btnAdd = new Button(composite, SWT.NONE);
		btnAdd.setLayoutData(new RowData(35, 35));
		btnAdd.setText(">");
		
		btnRemove = new Button(composite, SWT.NONE);
		btnRemove.setLayoutData(new RowData(35, 35));
		btnRemove.setText("<");
		
		btnRemoveAll = new Button(composite, SWT.NONE);
		btnRemoveAll.setLayoutData(new RowData(35, 35));
		btnRemoveAll.setText("<<");
		
		
		
		tabFolder = new CTabFolder(container, SWT.BORDER | SWT.CLOSE);
		tabFolder.setTabHeight(30);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tabFolder.minimumWidth = 600;
		tabFolder.setLayoutData(gd_tabFolder);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		

		SelectionListener btnListener = new SelectionListener()
		{
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (e.getSource() == btnAdd) addSelectedOperationInterface();
				else if (e.getSource() == btnRemove) removeSelectedOperationInterface();
				else if (e.getSource() == btnNew) createServiceComponent();
				else if (e.getSource() == btnAddAll) addAllOpearationInterfaces();
				else if (e.getSource() == btnRemoveAll) removeAllOperationInterfaces();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) { }
			
		};

		btnNew.addSelectionListener(btnListener);
		btnAdd.addSelectionListener(btnListener);
		btnRemove.addSelectionListener(btnListener);
		btnAddAll.addSelectionListener(btnListener);
		btnRemoveAll.addSelectionListener(btnListener);
		
		
		// Non-deprecated CTabFolder2Listener is not working!?
		// Using deprecated one - could be problematic on other OSes
		tabFolder.addCTabFolderListener(new CTabFolderListener() {
			@Override
			public void itemClosed(CTabFolderEvent event) {
				removeServiceComponent((CTabItem) event.item);
			}
		});
		
		// Create initial component

		SwtUtil.addListenerReursive(getControl(), this);
	}
	
	private int counter = 1;
	private Map<CTabItem, ProvidedServiceComposite> mapProvidedServcies = new HashMap<CTabItem, ProvidedServiceComposite>();
	private void createServiceComponent ()
	{
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setText("Service "+ counter++);
		
        ProvidedServiceComposite psc = new ProvidedServiceComposite(tabFolder, this.data.getCloudSpecification());
		mapProvidedServcies.put(tabItem, psc);

		tabItem.setControl(psc);
		tabFolder.setSelection(tabItem);
		
		SwtUtil.addListenerReursive(psc, this);
		checkComplete();
	}
	
	private void removeServiceComponent (CTabItem item)
	{
		ProvidedServiceComposite psc = mapProvidedServcies.remove(item);
		SwtUtil.removeListenerReursive(psc, this);

		this.oiInput.addAll(psc.getOperationInterfaces());
		checkComplete();
	}
	
	private void removeAllOperationInterfaces ()
	{
        if (tabFolder.getSelection() == null) return;
		ProvidedServiceComposite providedServiceComposite = mapProvidedServcies.get(tabFolder.getSelection());
		
		this.oiInput.addAll(providedServiceComposite.getOperationInterfaces());
		providedServiceComposite.clearOperationInterfaces();
	}
	
	private void addAllOpearationInterfaces ()
	{
        if (tabFolder.getSelection() == null) return;
		ProvidedServiceComposite providedServiceComposite = mapProvidedServcies.get(tabFolder.getSelection());
		for (Object o : oiInput)
		{
			providedServiceComposite.addOperationInterface((OperationInterface)o);
		}
		
		oiInput.clear();
	}
	
	private void addSelectedOperationInterface ()
	{
        if (tabFolder.getSelection() == null) return;
		OperationInterface selection = (OperationInterface) ((IStructuredSelection)listViewer.getSelection()).getFirstElement();

		if (selection != null)
		{
                ProvidedServiceComposite providedServiceComposite = mapProvidedServcies.get(tabFolder.getSelection());
                providedServiceComposite.addOperationInterface(selection);
                this.oiInput.remove(selection);
		}
	}

	
	private void removeSelectedOperationInterface()
	{
        if (tabFolder.getSelection() == null) return;
		ProvidedServiceComposite providedServiceComposite = mapProvidedServcies.get(tabFolder.getSelection());
		OperationInterface selection = providedServiceComposite.getSelectedOperationInterface();
		
		if (selection != null)
		{
			providedServiceComposite.removeOperationInterface(selection);
			this.oiInput.add(selection);
		}
	}
	
	LinkedList<OperationInterface> operationInterfaces = new LinkedList<OperationInterface>();
	WritableList oiInput = new WritableList(operationInterfaces, OperationInterface.class);
	private Button btnAddAll;
	private Button btnRemoveAll;

	private void initBindings ()
	{
		operationInterfaces.clear();
		operationInterfaces.addAll(this.softwareService.getRequiredInterfaces());

		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), OperationInterface.class, "name");
		listViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		listViewer.setContentProvider(listContentProvider);
		//
		//IObservableList selfList = Properties.selfList(OperationInterface.class).observe(operationInterfaces);
		listViewer.setInput(oiInput);
	}
	
	private void checkComplete ()
	{
		boolean complete = true;
		if (this.softwareService != null && this.softwareService.getRequiredInterfaces().isEmpty())
		{
			setPageComplete(true);
			return;
		}

		if (!oiInput.isEmpty())
			complete = false;
		
		for (ProvidedServiceComposite psc : mapProvidedServcies.values())
		{
			complete &= psc.isComplete();
		}
		
		setPageComplete(complete);
	}

	@Override
	public void performNext() {
		if (this.softwareService.getRequiredInterfaces().isEmpty())
			return;

		for (ProvidedServiceComposite psc : mapProvidedServcies.values())
		{
			
			Service service = psc.createAndInsertService(this.data.getOverviewModel());
			if (service instanceof ComputingInfrastructureService)
			{
				service = ((ComputingInfrastructureService)service).getPlatformServices().get(0);
			}
			
			OperationInterfaceContainer oic = null;
			
			if (service instanceof OperationInterfaceContainer)
			{
				oic = (OperationInterfaceContainer) service;
				
			}
			else
			{
				throw new IllegalStateException("Service is not operation interface container.");
			}
			
			// Create connection between these services 
			InternalConnection internalConnection = ArchitectureFactory.eINSTANCE.createInternalConnection();
			internalConnection.setSource(softwareService);
			internalConnection.setTarget(oic);
			
			CloudEnvironment cloudEnvironment = (CloudEnvironment) softwareService.eContainer().eContainer(); 
			cloudEnvironment.getInternalConnections().add(internalConnection);
			
			java.util.List<NetworkInfrastructureServiceDescriptor> networkInfrastructureDescriptors = OverviewHelper.getNetworkInfrastructureDescriptors(this.data.getCloudSpecification());
			if (!networkInfrastructureDescriptors.isEmpty())
			{
				NetworkInfrastructureServiceDescriptor nisd_org = networkInfrastructureDescriptors.get(0);
				NetworkInfrastructureServiceDescriptor nisd = OverviewSpecificationUtil.getSystemDescriptor(cloudEnvironment, nisd_org);
				internalConnection.setDescriptor(nisd);
			}

			
			// Remember service for possible deletion if user comes back to this page
			createdServices.add(service);
		}
	}

	@Override
	public void performBack() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void performUpdate() {
		// TODO Auto-generated method stub
		if (this.softwareService == data.getSoftwareService())
			return;

		Overview overviewModel = data.getOverviewModel();
		this.softwareService = data.getSoftwareService();
		System.out.println("Model: "+overviewModel.getArchitecture().getCloudEnvironments().get(0).getSoftwareLayer().getServices().get(0).getName());
		System.out.println("Software Service: "+this.softwareService.getName());
		
		initBindings();
		createServiceComponent();
		checkComplete();
		
	}

	@Override
	public void handleEvent(Event event) {
		checkComplete();
		
	}
}
