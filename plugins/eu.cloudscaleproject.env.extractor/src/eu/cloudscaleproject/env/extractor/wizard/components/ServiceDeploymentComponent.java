package eu.cloudscaleproject.env.extractor.wizard.components;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.scaledl.overview.Overview;
import org.scaledl.overview.deployment.ClusteredComputingEnvironment;
import org.scaledl.overview.deployment.ComputingEnvironment;
import org.scaledl.overview.deployment.DeploymentFactory;
import org.scaledl.overview.deployment.DeploymentPackage;
import org.scaledl.overview.deployment.DeploymentPackage.Literals;
import org.scaledl.overview.deployment.LoadBalancer;
import org.scaledl.overview.deployment.RuntimeDeployment;
import org.scaledl.overview.deployment.ScalableComputingEnvironment;
import org.scaledl.overview.deployment.ScalingCapacityType;
import org.scaledl.overview.deployment.ScalingManager;
import org.scaledl.overview.deployment.ScalingPolicy;
import org.scaledl.overview.deployment.ScalingPolicyType;
import org.scaledl.overview.deployment.SchedulingPolicy;
import org.scaledl.overview.specification.ComputingInfrastructureServiceDescriptor;
import org.scaledl.overview.specification.ComputingResourceDescriptor;
import org.scaledl.overview.specification.SpecificationFactory;
import org.scaledl.overview.specification.SpecificationPackage;
import org.scaledl.overview.util.OverviewSpecificationUtil;

import eu.cloudscaleproject.env.extractor.wizard.util.SwtUtil;


public class ServiceDeploymentComponent extends Composite {
	
	private Text txtInitialSize;
	private Text txtMinSize;
	private Text txtMaxSize;

	private Text txtCpu;
	private Text txtCores;
	private Text txtMemory;
	private Text txtStorage;
	private Button rbScalable;
	private Button rbClustered;
	private Button rbSingle;
	private Group cCluster;
	private Group cScalability;
	private Group cResource;
	private ComboViewer cvComputingResource;

	private ComboViewer cvLoadBalancer;
	private Label lblGb;

	public ServiceDeploymentComponent(Composite parent, int style) {
		super(parent, style);

		setLayout(new GridLayout(2, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 2, 1));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		rbSingle = new Button(composite_1, SWT.RADIO);
		rbSingle.setText("Single");
		
		rbClustered = new Button(composite_1, SWT.RADIO);
		rbClustered.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		rbClustered.setText("Clustered");
		
		rbScalable = new Button(composite_1, SWT.RADIO);
		rbScalable.setText("Replicable");
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		
		cResource = new Group(composite_2, SWT.NONE);
		cResource.setText("Resource configuraiton");
		cResource.setLayout(new GridLayout(6, false));
		cResource.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cResource.setSize(64, 64);
		
		Label lblNewLabel = new Label(cResource, SWT.NONE);
		lblNewLabel.setBounds(0, 0, 125, 15);
		lblNewLabel.setText("Computing Resource");
		
		cvComputingResource = new ComboViewer(cResource, SWT.NONE);
		Combo comboComputingResource = cvComputingResource.getCombo();
		comboComputingResource.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1));
		comboComputingResource.setBounds(0, 0, 35, 27);
		
		Label lblCpu = new Label(cResource, SWT.NONE);
		lblCpu.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCpu.setText("CPU");
		
		txtCpu = new Text(cResource, SWT.BORDER);
		
		Label lblMhz = new Label(cResource, SWT.NONE);
		lblMhz.setText("MHz     ");
		
		Label lblCore = new Label(cResource, SWT.NONE);
		lblCore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCore.setText("Cores");
		
		txtCores = new Text(cResource, SWT.BORDER);
		
		Label lblNum = new Label(cResource, SWT.NONE);
		lblNum.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblNum.setText("#");
		
		Label lblMemory = new Label(cResource, SWT.NONE);
		lblMemory.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMemory.setText("Memory");
		
		txtMemory = new Text(cResource, SWT.BORDER);
		
		Label lblMb = new Label(cResource, SWT.NONE);
		lblMb.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblMb.setText("MB");
		
		Label lblStorage = new Label(cResource, SWT.NONE);
		lblStorage.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStorage.setText("Storage");
		
		txtStorage = new Text(cResource, SWT.BORDER);
		
		lblGb = new Label(cResource, SWT.NONE);
		lblGb.setText("GB");
		
		cCluster = new Group(composite_2, SWT.NONE);
		cCluster.setText("Loadbalancing configuration");
		cCluster.setLayout(new GridLayout(2, false));
		cCluster.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cCluster.setBounds(0, 0, 64, 64);
		
		Label lblInitialSizew = new Label(cCluster, SWT.NONE);
		lblInitialSizew.setText("Initial size");
		lblInitialSizew.setBounds(0, 0, 125, 15);
		
		txtInitialSize = new Text(cCluster, SWT.BORDER);
		txtInitialSize.setText("2");
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.minimumWidth = 100;
		txtInitialSize.setLayoutData(gd_text);
		
		Label lblLoadBalancer = new Label(cCluster, SWT.NONE);
		lblLoadBalancer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLoadBalancer.setText("Load balancer");
		
		cvLoadBalancer = new ComboViewer(cCluster, SWT.NONE);
		Combo comboLoadBalancer = cvLoadBalancer.getCombo();
		GridData gd_comboLoadBalancer = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_comboLoadBalancer.minimumWidth = 200;
		comboLoadBalancer.setLayoutData(gd_comboLoadBalancer);
		
		cScalability = new Group(composite_2, SWT.NONE);
		cScalability.setText("Scaling configuration");
		cScalability.setLayout(new GridLayout(6, false));
		cScalability.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cScalability.setBounds(0, 0, 64, 64);
		
		Label lblMinimum = new Label(cScalability, SWT.NONE);
		lblMinimum.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMinimum.setText("Minimum");
		
		txtMinSize = new Text(cScalability, SWT.BORDER);
		txtMinSize.setText("2");
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text_1.minimumWidth = 50;
		txtMinSize.setLayoutData(gd_text_1);
		
		Label lblMaximum = new Label(cScalability, SWT.NONE);
		lblMaximum.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMaximum.setText("Maximum");
		
		txtMaxSize = new Text(cScalability, SWT.BORDER);
		txtMaxSize.setText("2");
		txtMaxSize.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(cScalability, SWT.NONE);
		new Label(cScalability, SWT.NONE);
		
		Button btnCreateScallingPolicy = new Button(cScalability, SWT.NONE);
		btnCreateScallingPolicy.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		btnCreateScallingPolicy.setText("Create scalling policy ...");
		new Label(cScalability, SWT.NONE);
		new Label(cScalability, SWT.NONE);

		initBindings();
	}
	
	public void setComputingInfrastructureServiceDescriptor (ComputingInfrastructureServiceDescriptor cisd)
	{
		if (cisd != null && cisd.getComputingResourceDescriptors().isEmpty())
		{
			ComputingInfrastructureServiceDescriptor copyCisd = EcoreUtil.copy(cisd);
			ComputingResourceDescriptor crd = SpecificationFactory.eINSTANCE.createComputingResourceDescriptor();
			crd.setName("Custom computing resource");
			copyCisd.getComputingResourceDescriptors().add(crd);
			cisd = copyCisd;
		}

		valueComputingInfrastructureServiceDescriptor.setValue(cisd);
	}
	
	private EMFDataBindingContext bindingContext;
	private void initBindings ()
	{
		initCustomBindings();
		
		bindingContext = new EMFDataBindingContext();
		
		bindComputingResources(bindingContext);
		bindComputingResourceDescriptor(bindingContext);
		bindClusteredComputingEnvironment(bindingContext);
		bindScalableComputingEnvironment(bindingContext);
	}
	
	private void initCustomBindings()
	{
		// Radio buttons - Single, Clustered, Scalable
		SelectionListener rbListener = new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {

                //recursiveSetEnabled(cResource, true);
                
                if (rbSingle.getSelection())
                	valueComputingEnvironment.setValue(DeploymentFactory.eINSTANCE.createComputingEnvironment());

                if (rbClustered.getSelection())
                {
                    ClusteredComputingEnvironment clusteredComputingEnvironment = DeploymentFactory.eINSTANCE.createClusteredComputingEnvironment();
                    clusteredComputingEnvironment.setLoadBalancer(getDefaultLoadbalancer());
                    clusteredComputingEnvironment.getLoadBalancer().setSchedulingPolicy(org.scaledl.overview.deployment.SchedulingPolicy.ROUND_ROBIN);
                    clusteredComputingEnvironment.setSize(2);
                	valueComputingEnvironment.setValue(clusteredComputingEnvironment);
                }
                if (rbScalable.getSelection())
                {
                    ScalableComputingEnvironment scalableComputingEnvironment = DeploymentFactory.eINSTANCE.createScalableComputingEnvironment();
                    scalableComputingEnvironment.setScalingManager(getDefaultScalingManager());
                    scalableComputingEnvironment.setLoadBalancer(getDefaultLoadbalancer());
                    scalableComputingEnvironment.setSize(2);                    
                	valueComputingEnvironment.setValue(scalableComputingEnvironment);
                }
                
                bindingContext.updateTargets();
                updateComponentEnable();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) { }
		};

		rbSingle.addSelectionListener(rbListener);
		rbClustered.addSelectionListener(rbListener);
		rbScalable.addSelectionListener(rbListener);
		
		updateComponentEnable();
	}
	
	private LoadBalancer getDefaultLoadbalancer()
	{
		LoadBalancer lb = DeploymentFactory.eINSTANCE.createLoadBalancer();
        lb.setSchedulingPolicy(org.scaledl.overview.deployment.SchedulingPolicy.ROUND_ROBIN);
        return lb;
	}
	
	private ScalingManager getDefaultScalingManager()
	{
		 //Default scaling manager
        ScalingManager sm = DeploymentFactory.eINSTANCE.createScalingManager();
        
        sm.setMinimumSize(1);
        sm.setMaximumSize(4);
        
        ScalingPolicy sp = DeploymentFactory.eINSTANCE.createScalingPolicy();
        sp.setAdjustment(2);
        sp.setCooldown(300);
        sp.setScalingPolicyType(ScalingPolicyType.SCALING_UP_POLICY);
        sp.setScalingCapacityType(ScalingCapacityType.CHANGE_IN_CAPACITY);
        sp.setTrigger("avg(CPU) > 0.8 AND avg(MEM) > 0.9 [5min] ");
        
        ScalingPolicy spDown = DeploymentFactory.eINSTANCE.createScalingPolicy();
        spDown.setAdjustment(1);
        spDown.setCooldown(300);
        spDown.setScalingPolicyType(ScalingPolicyType.SCALING_DOWN_POLICY);
        spDown.setScalingCapacityType(ScalingCapacityType.CHANGE_IN_CAPACITY);
        spDown.setTrigger("avg(CPU) < 0.6 AND avg(MEM) < 0.7 [5min] ");
        
        sm.getScalingPolicy().add(sp);
        sm.getScalingPolicy().add(spDown);
        
        return sm;
	}
	
	private void updateComponentEnable ()
	{
        SwtUtil.setEnabledRecursive(cResource, rbSingle.getSelection() || rbClustered.getSelection() || rbScalable.getSelection());
        SwtUtil.setEnabledRecursive(cCluster, rbClustered.getSelection() || rbScalable.getSelection());

        SwtUtil.setEnabledRecursive(cScalability, rbScalable.getSelection());
        
        cvComputingResource.getCombo().setEnabled(rbSingle.getSelection() || rbClustered.getSelection() || rbScalable.getSelection());
        cvLoadBalancer.getCombo().setEnabled(rbClustered.getSelection() || rbScalable.getSelection());
	}
	
	private void updateComputingResourceFields (ComputingResourceDescriptor crd)
	{
		boolean enableFields = false;

		if (crd != null && (crd.getProviderID() == null || crd.getProviderID().isEmpty()))
		{
			enableFields = true;
		}

		txtCpu.setEnabled(enableFields);
		txtCores.setEnabled(enableFields);
		txtMemory.setEnabled(enableFields);
		txtStorage.setEnabled(enableFields);
		
	}

	private WritableValue valueComputingInfrastructureServiceDescriptor = new WritableValue(null, ComputingInfrastructureServiceDescriptor.class);
	private WritableValue valueComputingEnvironment = new WritableValue(null, ComputingEnvironment.class);
	private WritableValue valueComputingResourceDescriptor = new WritableValue(null, ComputingResourceDescriptor.class);
	

	private void bindComputingResources (DataBindingContext bindingContext)
	{
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), ComputingResourceDescriptor.class, "name");
		cvComputingResource.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		cvComputingResource.setContentProvider(listContentProvider);
		//
		IObservableList computingResourceDescriptorsCisdObserveList = PojoProperties.list("computingResourceDescriptors").observeDetail(valueComputingInfrastructureServiceDescriptor);
		cvComputingResource.setInput(computingResourceDescriptorsCisdObserveList);
		//
		IObservableValue observeSingleSelectionCvComputingResource = ViewerProperties.singleSelection().observe(cvComputingResource);
		IObservableValue computingEnvironmentinstanceDescriptorRuntimeDeploymentObserveValue = PojoProperties.value("instanceDescriptor").observe(valueComputingEnvironment);
		bindingContext.bindValue(observeSingleSelectionCvComputingResource, computingEnvironmentinstanceDescriptorRuntimeDeploymentObserveValue, null, null);
		
		cvComputingResource.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ComputingResourceDescriptor crd = (ComputingResourceDescriptor) ((IStructuredSelection)event.getSelection()).getFirstElement();
				valueComputingResourceDescriptor.setValue(crd);
				
				updateComputingResourceFields(crd);
				
			}
		});;
	}
	
	private void bindClusteredComputingEnvironment (DataBindingContext bindingContext)
	{
		bindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observe(txtInitialSize), 
                EMFProperties.value(DeploymentPackage.Literals.CLUSTERED_COMPUTING_ENVIRONMENT__SIZE).observeDetail(valueComputingEnvironment)
        );
		
		// Scheduling policy (enum!)
		cvLoadBalancer.setContentProvider(ArrayContentProvider.getInstance());
		cvLoadBalancer.setInput(SchedulingPolicy.VALUES);
		
		bindingContext.bindValue(
                ViewerProperties.singleSelection().observe(cvLoadBalancer),
                EMFProperties.value(DeploymentPackage.Literals.CLUSTERED_COMPUTING_ENVIRONMENT__LOAD_BALANCER).observeDetail(valueComputingEnvironment)
        );
	}
	
	private void bindScalableComputingEnvironment (EMFDataBindingContext bindingContext)
	{
		bindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observe(txtMinSize), 
                EMFProperties.value(FeaturePath.fromList(Literals.SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER, Literals.SCALING_MANAGER__MINIMUM_SIZE)).observeDetail(valueComputingEnvironment)
        );

		bindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observe(txtMaxSize), 
                EMFProperties.value(FeaturePath.fromList(Literals.SCALABLE_COMPUTING_ENVIRONMENT__SCALING_MANAGER, Literals.SCALING_MANAGER__MAXIMUM_SIZE)).observeDetail(valueComputingEnvironment)
        );
	}
	
	ScalableComputingEnvironment sce;

	private void bindComputingResourceDescriptor (EMFDataBindingContext bindingContext) {

		bindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observe(txtCpu), 
                EMFProperties.value(SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__CPU).observeDetail(valueComputingResourceDescriptor)
        );

		bindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observe(txtCores), 
                EMFProperties.value(SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__CPU_UNITS).observeDetail(valueComputingResourceDescriptor)
        );

		bindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observe(txtMemory), 
                EMFProperties.value(SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__MEMORY).observeDetail(valueComputingResourceDescriptor)
        );

		bindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observe(txtStorage), 
                EMFProperties.value(SpecificationPackage.Literals.COMPUTING_RESOURCE_DESCRIPTOR__STORAGE).observeDetail(valueComputingResourceDescriptor)
        );
	}

	public RuntimeDeployment createAndInsertDeployment(Overview overview) {

		RuntimeDeployment runtimeDeployment = DeploymentFactory.eINSTANCE.createRuntimeDeployment();

		// Replace computing resource descriptor
		ComputingResourceDescriptor crd_org = (ComputingResourceDescriptor) valueComputingResourceDescriptor.getValue();
        ComputingResourceDescriptor crd = OverviewSpecificationUtil.getSystemDescriptor(overview, crd_org);
        
        ComputingEnvironment computingEnvironment = (ComputingEnvironment) valueComputingEnvironment.getValue();
        runtimeDeployment.setComputingEnvironment(computingEnvironment);
        
        runtimeDeployment.getComputingEnvironment().setInstanceDescriptor(crd);
        
        overview.getDeployment().getServiceDeployments().add(runtimeDeployment);

		return runtimeDeployment;
	}
	

	public void getComplete()
	{
		
	}
	public void isTra()
	{

		
	}
	public boolean isComplete() {
		// TODO Auto-generated method stub
		if (valueComputingEnvironment.getValue() == null ||
			valueComputingInfrastructureServiceDescriptor.getValue() == null ||
			valueComputingResourceDescriptor.getValue() == null)
			return false;
		
		ComputingEnvironment ce = (ComputingEnvironment) valueComputingEnvironment.getValue();
		ComputingResourceDescriptor csd = (ComputingResourceDescriptor) valueComputingResourceDescriptor.getValue();
		
		if (csd.getCpu() <=0 || csd.getMemory() <=0 || csd.getCpuUnits() <=0 || csd.getStorage()<0)
			return false;
		
		if (ce instanceof ClusteredComputingEnvironment)
		{
			if (((ClusteredComputingEnvironment)ce).getSize() <= 0)
                return false;
		}
		
		if (ce instanceof ScalableComputingEnvironment)
		{
			ScalableComputingEnvironment sce = (ScalableComputingEnvironment) ce;
			if (sce.getScalingManager().getMinimumSize()<0 ||
					sce.getScalingManager().getMaximumSize()<=sce.getScalingManager().getMinimumSize()||
					sce.getSize() <=0)
				return false;
		}
		
		return true;
	}
}
