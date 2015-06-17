package eu.cloudscaleproject.env.overview.wizard.pages;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.scaledl.overview.Overview;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.architecture.ArchitectureFactory;
import org.scaledl.overview.architecture.ComputingInfrastructureService;
import org.scaledl.overview.architecture.InfrastructureService;
import org.scaledl.overview.architecture.PlatformService;
import org.scaledl.overview.architecture.ProvidedService;
import org.scaledl.overview.architecture.ProvidedSoftwareService;
import org.scaledl.overview.architecture.Service;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.deployment.DeploymentFactory;
import org.scaledl.overview.deployment.RuntimeDeployment;
import org.scaledl.overview.deployment.ServiceDeployment;
import org.scaledl.overview.specification.CloudSpecification;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.InfrastructureServiceDescriptor;
import org.scaledl.overview.specification.PlatformRuntimeServiceDescriptor;
import org.scaledl.overview.specification.PlatformServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformRuntimeServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformServiceDescriptor;
import org.scaledl.overview.specification.ProvidedPlatformSupportServiceDescriptor;
import org.scaledl.overview.specification.ProvidedSoftwareServiceDescriptor;
import org.scaledl.overview.util.OverviewSpecificationUtil;

import eu.cloudscaleproject.env.overview.wizard.util.OverviewHelper;

public class ProvidedServiceComposite extends Composite {
	//private DataBindingContext m_bindingContext;
	private CloudSpecification cloudSpecification;

	private Button rbExternal;
	private Button rbSaaS;
	private Button rbPaaS;
	private Button rbIaaS;

	private ServiceDeploymentComponent serviceDeploymentComposite;
	private Composite stackedComposite;
	private Composite cIaaS;
	private Composite cPaaS;
	private Composite cSaaS;
	private Composite cExternal;
	private Label lblPlatformService;
	private Combo combo_1;
	private ComboViewer cvIaaSPlatform;
	private Label lblInfrastructureService;
	private Combo combo_2;
	private ComboViewer cvIaaS;
	private Label lblNewLabel;
	private Combo combo_3;
	private ComboViewer cvPaaS;
	private Label lblSoftwareService;
	private Combo combo_4;
	private ComboViewer cvSaaS;
	private Group grpServiceSelection;
	private Group grpDeploymentConfiguraiton;
	private org.eclipse.swt.widgets.List list;
	private ListViewer lvOperations;
	private Group grpOperationInterface;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProvidedServiceComposite(Composite parent, CloudSpecification cloudSpecification) {
		super(parent, SWT.NONE);
		setLayout(new GridLayout(1, false));
		
		this.cloudSpecification = cloudSpecification;
		
		grpServiceSelection = new Group(this, SWT.NONE);
		grpServiceSelection.setText("Service selection");
		grpServiceSelection.setLayout(new FillLayout(SWT.HORIZONTAL));
		grpServiceSelection.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Composite composite = new Composite(grpServiceSelection, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		composite_2.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		rbIaaS = new Button(composite_2, SWT.RADIO);
		rbIaaS.setText("IaaS");
		
		rbPaaS = new Button(composite_2, SWT.RADIO);
		rbPaaS.setText("PaaS");
		
		rbSaaS = new Button(composite_2, SWT.RADIO);
		rbSaaS.setText("SaaS");
		
		rbExternal = new Button(composite_2, SWT.RADIO);
		rbExternal.setText("External");
		
		stackedComposite = new Composite(composite, SWT.NONE);
		stackedComposite.setLayout(new StackLayout());
		stackedComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		cIaaS = new Composite(stackedComposite, SWT.NONE);
		cIaaS.setLayout(new GridLayout(2, false));
		
		lblPlatformService = new Label(cIaaS, SWT.NONE);
		lblPlatformService.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPlatformService.setText("Platform service");
		
		cvIaaSPlatform = new ComboViewer(cIaaS, SWT.NONE);
		combo_1 = cvIaaSPlatform.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblInfrastructureService = new Label(cIaaS, SWT.NONE);
		lblInfrastructureService.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInfrastructureService.setText("Infrastructure service");
		
		cvIaaS = new ComboViewer(cIaaS, SWT.NONE);
		combo_2 = cvIaaS.getCombo();
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		cPaaS = new Composite(stackedComposite, SWT.NONE);
		cPaaS.setLayout(new GridLayout(2, false));
		
		lblNewLabel = new Label(cPaaS, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Platform service");
		
		cvPaaS = new ComboViewer(cPaaS, SWT.NONE);
		combo_3 = cvPaaS.getCombo();
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		cSaaS = new Composite(stackedComposite, SWT.NONE);
		cSaaS.setLayout(new GridLayout(2, false));
		
		lblSoftwareService = new Label(cSaaS, SWT.NONE);
		lblSoftwareService.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSoftwareService.setText("Software service");
		
		cvSaaS = new ComboViewer(cSaaS, SWT.NONE);
		combo_4 = cvSaaS.getCombo();
		combo_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		cExternal = new Composite(stackedComposite, SWT.NONE);
		
		grpOperationInterface = new Group(this, SWT.NONE);
		grpOperationInterface.setLayout(new GridLayout(1, false));
		grpOperationInterface.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpOperationInterface.setText("Operation interfaces");
		
		lvOperations = new ListViewer(grpOperationInterface, SWT.BORDER | SWT.V_SCROLL);
		list = lvOperations.getList();
		GridData gd_list = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_list.widthHint = 283;
		list.setLayoutData(gd_list);
		
		grpDeploymentConfiguraiton = new Group(this, SWT.NONE);
		grpDeploymentConfiguraiton.setText("Deployment configuraiton");
		grpDeploymentConfiguraiton.setLayout(new FillLayout(SWT.HORIZONTAL));
		grpDeploymentConfiguraiton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		serviceDeploymentComposite = new ServiceDeploymentComponent(grpDeploymentConfiguraiton, SWT.NONE);
		
		initComponents();
		initBindings();
	}
	
	List<OperationInterface> operationInterfaces = new LinkedList<OperationInterface>();
	WritableList oiInput = new WritableList(operationInterfaces, OperationInterface.class);

	private DataBindingContext bindingContext;
	
	public void addOperationInterface (OperationInterface operationInterface)
	{
		oiInput.add(operationInterface);
	}
	
	public void removeOperationInterface (OperationInterface operationInterface)
	{
		oiInput.remove(operationInterface);
	}
	
	public void clearOperationInterfaces() {
		oiInput.clear();
	}

	public OperationInterface getSelectedOperationInterface ()
	{
		OperationInterface oi = (OperationInterface) ((IStructuredSelection)lvOperations.getSelection()).getFirstElement();
		
		return oi;
	}
	
	public List<OperationInterface> getOperationInterfaces ()
	{
		return new LinkedList<OperationInterface>(operationInterfaces);
	}

	private void initBindings ()
	{
		this.bindingContext = new DataBindingContext();
		initOperationInterfacesBindings(bindingContext);

		SelectionListener l = new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 2 events - selection / de-selection 
				boolean isSelected = ((Button)e.getSource()).getSelection();
				if (isSelected)
				{
					updateServiceComopsite();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		};
		
		rbIaaS.addSelectionListener(l);
		rbPaaS.addSelectionListener(l);
		rbSaaS.addSelectionListener(l);
		rbExternal.addSelectionListener(l);

		
		ISelectionChangedListener cvListener = new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				updateDeploymentComposite();
			}
		};

		cvIaaS.addSelectionChangedListener(cvListener);
		cvPaaS.addSelectionChangedListener(cvListener);
		cvSaaS.addSelectionChangedListener(cvListener);

		rbIaaS.setSelection(true);
		updateServiceComopsite();
	}

	protected void initOperationInterfacesBindings(DataBindingContext bindingContext) {

		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), OperationInterface.class, "name");
		lvOperations.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		lvOperations.setContentProvider(listContentProvider);
		//
		//IObservableList selfList = Properties.selfList(OperationInterface.class).observe(operationInterfaces);
		lvOperations.setInput(oiInput);
	}
	
	private void updateServiceComopsite()
	{
        StackLayout layout = (StackLayout)stackedComposite.getLayout();
        if (rbIaaS.getSelection())
        {
                layout.topControl = cIaaS;
        }
        if (rbPaaS.getSelection())
        {
                layout.topControl = cPaaS;
        }
        if (rbSaaS.getSelection())
        {
                layout.topControl = cSaaS;
                
        }
        if (rbExternal.getSelection())
        {
                layout.topControl = cExternal;
        }

        stackedComposite.layout();
		updateDeploymentComposite();
	}
	
	private void updateDeploymentComposite ()
	{
        ComputingInfrastructureServiceDescriptor cisd = getChosenInfrastructureDescriptor();
        if (cisd != null)
        {
                serviceDeploymentComposite.setComputingInfrastructureServiceDescriptor(cisd);
                serviceDeploymentComposite.setVisible(true);
        }
        else
        {
                serviceDeploymentComposite.setComputingInfrastructureServiceDescriptor(null);
                serviceDeploymentComposite.setVisible(false);
        }
	}
	
	private ComputingInfrastructureServiceDescriptor getChosenInfrastructureDescriptor()
	{
		if (rbIaaS.getSelection())
		{
			return (ComputingInfrastructureServiceDescriptor) ((StructuredSelection)cvIaaS.getSelection()).getFirstElement();
		}
		else if (rbPaaS.getSelection())
		{
			// Some provided platform services has it's own infrastructure on which it is possible to configure deployment information 
			// of chosen platform services ; i.e. AWS RDS and BeanStalk
			ProvidedPlatformServiceDescriptor prsd = (ProvidedPlatformServiceDescriptor)((StructuredSelection)cvPaaS.getSelection()).getFirstElement();
			if (prsd != null)
			return prsd.getInfrastructureServiceDescriptor();
		}
		
		return null;
	}
	

	private void initComponents()
	{
		//
		// IaaS
		//
		// Generic platform service
		{
                ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
                IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), PlatformService.class, "name");
                cvIaaSPlatform.setLabelProvider(new ObservableMapLabelProvider(observeMap));
                cvIaaSPlatform.setContentProvider(listContentProvider);
                //
                //IObservableList selfList = Properties.selfList(InfrastructureServiceDescriptor.class).observe(Util.getIaaSRuntimeDescriptors(cloudSpecification));
                //cvIaaSPlatform.setInput(selfList);
		}

		// Infrastructure services
		{
                ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
                IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), InfrastructureServiceDescriptor.class, "name");
                cvIaaS.setLabelProvider(new ObservableMapLabelProvider(observeMap));
                cvIaaS.setContentProvider(listContentProvider);
                //
                List<ComputingInfrastructureServiceDescriptor> iaaSRuntimeDescriptors = OverviewHelper.getIaaSRuntimeDescriptors(cloudSpecification);
                IObservableList selfList = Properties.selfList(InfrastructureServiceDescriptor.class).observe(iaaSRuntimeDescriptors);
                cvIaaS.setInput(selfList);
                if (!iaaSRuntimeDescriptors.isEmpty())
                {
                        //cvIaaS.setSelection(new StructuredSelection(iaaSRuntimeDescriptors.get(0)));
                }
                else
                {
                        rbIaaS.setEnabled(false);
                }
		}
		
		//
		// PaaS
		//
		// Platform services
		{
                ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
                IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), ProvidedPlatformServiceDescriptor.class, "name");
                cvPaaS.setLabelProvider(new ObservableMapLabelProvider(observeMap));
                cvPaaS.setContentProvider(listContentProvider);
                //
                List<ProvidedPlatformSupportServiceDescriptor> paaSSuportDescriptors = OverviewHelper.getPaaSSuportDescriptors(cloudSpecification);
                IObservableList selfList = Properties.selfList(InfrastructureServiceDescriptor.class).observe(paaSSuportDescriptors);
                cvPaaS.setInput(selfList);
                if (!paaSSuportDescriptors.isEmpty())
                {
                        //cvPaaS.setSelection(new StructuredSelection(paaSSuportDescriptors.get(0)));
                }
                else
                {
                        rbPaaS.setEnabled(false);
                }
		}

		//
		// SaaS
		//
		// Software services
		{
                ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
                IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), ProvidedSoftwareService.class, "name");
                cvSaaS.setLabelProvider(new ObservableMapLabelProvider(observeMap));
                cvSaaS.setContentProvider(listContentProvider);
                //
                List<ProvidedSoftwareService> saaSDescriptors = OverviewHelper.getSaaSDescriptors(cloudSpecification); 
                IObservableList selfList = Properties.selfList(InfrastructureServiceDescriptor.class).observe(saaSDescriptors);
                cvSaaS.setInput(selfList);
                if (!saaSDescriptors.isEmpty())
                {
                        //cvSaaS.setSelection(new StructuredSelection(saaSDescriptors.get(0)));
                }
                else
                {
                        rbSaaS.setEnabled(false);
                }
		}
			
		
		//
		// External
		//
		rbExternal.setEnabled(false);
	}
	
	public boolean isComplete ()
	{
		if (serviceDeploymentComposite.isVisible() && !serviceDeploymentComposite.isComplete())
			return false;

		if (rbIaaS.getSelection())
		{
			if (cvIaaS.getSelection().isEmpty())
				return false;
			
		}
		if (rbPaaS.getSelection())
		{
			if (cvPaaS.getSelection().isEmpty())
				return false;
		}
		if (rbSaaS.getSelection())
		{
			if (cvSaaS.getSelection().isEmpty())
				return false;
		}
		
		return true;
	}

	public Service createAndInsertService (Overview overview)
	{
		Service s = null;
		if (rbIaaS.getSelection())
			s = createAndInsertInfrastructureService(overview);
		if (rbPaaS.getSelection())
			s = createAndInsertPlatformService(overview);
		if (rbSaaS.getSelection())
			s = createAndInsertSoftwareService(overview);
		
		// Add operation interfaces
		if (s != null)
		{
			OperationInterfaceContainer oic;

			if (s instanceof InfrastructureService)
				 oic = (OperationInterfaceContainer)((ComputingInfrastructureService)s).getPlatformServices().get(0);
			else
				oic = (OperationInterfaceContainer)s;
			
			oic.getProvidedInterfaces().addAll(operationInterfaces);
		}

		return s;
	}
	
	private InfrastructureService createAndInsertInfrastructureService(Overview overview)
	{
		//PlatformServiceDescriptor psd_org = (PlatformServiceDescriptor) ((StructuredSelection)cvIaaSPlatform.getSelection()).getFirstElement();
		ComputingInfrastructureServiceDescriptor cisd_org = (ComputingInfrastructureServiceDescriptor) ((StructuredSelection)cvIaaS.getSelection()).getFirstElement();
		
		
        PlatformServiceDescriptor psd = null;//OverviewSpecificationUtil.getSystemDescriptor(overview, psd_org);
        ComputingInfrastructureServiceDescriptor cisd = OverviewSpecificationUtil.getSystemDescriptor(overview, cisd_org);

		//
		// Infrastructure service
		//
		ComputingInfrastructureService cis = ArchitectureFactory.eINSTANCE.createComputingInfrastructureService();
		cis.setDescriptor(cisd);
		overview.getArchitecture().getCloudEnvironments().get(0).getInfrastructureLayer().getServices().add(cis);

        //
        // Platform service
        //
		PlatformService ps; 
		if (psd instanceof PlatformRuntimeServiceDescriptor)
		{
			ps = ArchitectureFactory.eINSTANCE.createPlatformRuntimeService();
			ps.setDescriptor(psd);
			ps.setName("TODO: descriptor");
		}
		else
		{
			ps = ArchitectureFactory.eINSTANCE.createPlatformSupportService();
			ps.setDescriptor(psd);
			ps.setName("TODO: descriptor");
		}
		
		cis.getPlatformServices().add(ps);
		overview.getArchitecture().getCloudEnvironments().get(0).getPlatformLayer().getServices().add(ps);
		
		//
		// Deployment configuration
		//
		RuntimeDeployment runtimeDeployment = serviceDeploymentComposite.createAndInsertDeployment(overview);
		cis.setDeployment(runtimeDeployment);
		
		return cis;
	}
	
	private PlatformService createAndInsertPlatformService(Overview overview)
	{
		ProvidedPlatformServiceDescriptor psd_org = (ProvidedPlatformServiceDescriptor) ((StructuredSelection)cvPaaS.getSelection()).getFirstElement();
        ProvidedPlatformServiceDescriptor psd = OverviewSpecificationUtil.getSystemDescriptor(overview, psd_org);
        ComputingInfrastructureServiceDescriptor cisd_org = psd.getInfrastructureServiceDescriptor();

        //
        // Platform service
        //
		PlatformService ps; 
		if (psd instanceof ProvidedPlatformRuntimeServiceDescriptor)
		{
			ps = ArchitectureFactory.eINSTANCE.createProvidedPlatformRuntimeService();
		}
		else
		{
			ps = ArchitectureFactory.eINSTANCE.createProvidedPlatformSupportService();
		}

		ps.setDescriptor(psd);
		ps.setName(psd.getName());

		overview.getArchitecture().getCloudEnvironments().get(0).getPlatformLayer().getServices().add(ps);

		//
		// Deployment configuration
		//
		if (cisd_org != null)
		{
                RuntimeDeployment runtimeDeployment = serviceDeploymentComposite.createAndInsertDeployment(overview);
                ((ProvidedService)ps).setDeployment(runtimeDeployment);
		}

		return ps;
	}
	
	private SoftwareService createAndInsertSoftwareService(Overview overview)
	{
		ProvidedSoftwareServiceDescriptor pssd_org = (ProvidedSoftwareServiceDescriptor) ((StructuredSelection)cvSaaS.getSelection()).getFirstElement();
        ProvidedSoftwareServiceDescriptor pssd = OverviewSpecificationUtil.getSystemDescriptor(overview, pssd_org);
        
        //
        // Create Software service
        //
        ProvidedSoftwareService pss = ArchitectureFactory.eINSTANCE.createProvidedSoftwareService();
        pss.setDescriptor(pssd);
        overview.getArchitecture().getCloudEnvironments().get(0).getSoftwareLayer().getServices().add(pss);
        ServiceDeployment serviceDeployment = DeploymentFactory.eINSTANCE.createServiceDeployment();
        overview.getDeployment().getServiceDeployments().add(serviceDeployment);
		
		return pss;
	}

}
