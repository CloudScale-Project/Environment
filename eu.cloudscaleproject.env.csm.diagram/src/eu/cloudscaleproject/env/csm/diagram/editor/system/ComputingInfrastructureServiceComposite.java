package eu.cloudscaleproject.env.csm.diagram.editor.system;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import eu.cloudscaleproject.env.csm.DefinitionProviderService;
import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureLayer;
import eu.cloudscaleproject.env.csm.architecture.PlatformService;
import eu.cloudscaleproject.env.csm.core.CorePackage.Literals;
import eu.cloudscaleproject.env.csm.definition.CloudDefinition;
import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.definition.DefinitionPackage;
import eu.cloudscaleproject.env.csm.diagram.SWTResourceManager;
import eu.cloudscaleproject.env.csm.diagram.Util;
import eu.cloudscaleproject.env.csm.diagram.editor.platform.PlatformServiceEditor;
import eu.cloudscaleproject.env.csm.diagram.editor.platform.PlatformServiceEditorInput;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class ComputingInfrastructureServiceComposite extends Composite {
	private DataBindingContext m_bindingContext;
	
	private static final String CUSTOM_INSTANCE = "Custom instance";
	
	private Text txtCpu;
	private Text txtCpuUnits;
	private Text txtMemory;
	private Text txtStorage;
	private Text textOs;
	private ComputingInfrastructureService computingInfrastructureService;
	private CloudEnvironment cloudProvider;

	private TransactionalEditingDomain editingDomain;
	private Table table;
	private TableViewer tableViewer;

	private CloudDefinition cloudDefinition;
	private ComboViewer comboViewer;

	private InfrastructureLayer infrastructureLayer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ComputingInfrastructureServiceComposite(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		Label lblTitle = new Label(this, SWT.NONE);
		lblTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTitle.setFont(SWTResourceManager.getFont("Ubuntu", 13, SWT.NORMAL));
		FormData fd_lblTitle = new FormData();
		fd_lblTitle.bottom = new FormAttachment(0, 21);
		fd_lblTitle.top = new FormAttachment(0);
		fd_lblTitle.left = new FormAttachment(0);
		lblTitle.setLayoutData(fd_lblTitle);
		lblTitle.setText("Instance name:");
		
		Composite composite = new Composite(this, SWT.BORDER);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(lblTitle, 210, SWT.BOTTOM);
		fd_composite.top = new FormAttachment(lblTitle, 6);
		fd_composite.left = new FormAttachment(lblTitle, 10, SWT.LEFT);
		fd_composite.right = new FormAttachment(100, -11);
		composite.setLayoutData(fd_composite);
		
		CLabel label_1 = new CLabel(composite, SWT.NONE);
		label_1.setText("Instance");
		label_1.setBounds(10, 36, 115, 23);
		
		CLabel label_2 = new CLabel(composite, SWT.NONE);
		label_2.setText("CPU (MHz)");
		label_2.setLeftMargin(10);
		label_2.setBounds(10, 71, 115, 23);
		
		CLabel label_3 = new CLabel(composite, SWT.NONE);
		label_3.setText("CPU (Units)");
		label_3.setLeftMargin(10);
		label_3.setBounds(10, 100, 89, 23);
		
		CLabel label_4 = new CLabel(composite, SWT.NONE);
		label_4.setText("Memory (MB)");
		label_4.setLeftMargin(10);
		label_4.setBounds(10, 129, 104, 23);
		
		txtCpu = new Text(composite, SWT.BORDER);
		txtCpu.setText("0");
		// txtCpu.setEnabled(false);
		txtCpu.setBounds(141, 73, 70, 21);
		
		txtCpuUnits = new Text(composite, SWT.BORDER);
		txtCpuUnits.setText("0");
		// txtCpuUnits.setEnabled(false);
		txtCpuUnits.setBounds(141, 102, 70, 21);
		
		txtMemory = new Text(composite, SWT.BORDER);
		txtMemory.setText("0");
		// txtMemory.setEnabled(false);
		txtMemory.setBounds(141, 131, 70, 21);
		
		CLabel label_5 = new CLabel(composite, SWT.NONE);
		label_5.setText("Storage (GB)");
		label_5.setLeftMargin(10);
		label_5.setBounds(10, 158, 97, 23);
		
		txtStorage = new Text(composite, SWT.BORDER);
		txtStorage.setText("0");
		// txtStorage.setEnabled(false);
		txtStorage.setBounds(141, 160, 70, 21);
		
		Label lblHardware = new Label(composite, SWT.NONE);
		lblHardware.setBounds(10, 10, 311, 17);
		lblHardware.setText("Hardware specification");
		
		Composite composite_1 = new Composite(this, SWT.BORDER);
		composite_1.setLayout(new FormLayout());
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(100, -10);
		fd_composite_1.top = new FormAttachment(composite, 6);
		fd_composite_1.right = new FormAttachment(100, -11);
		fd_composite_1.left = new FormAttachment(0, 10);
		
		comboViewer = new ComboViewer(composite, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		combo.setBounds(141, 36, 180, 29);
		composite_1.setLayoutData(fd_composite_1);
		
		Label lblComponents = new Label(composite_1, SWT.NONE);
		FormData fd_lblComponents = new FormData();
		fd_lblComponents.right = new FormAttachment(0, 88);
		fd_lblComponents.top = new FormAttachment(0, 10);
		fd_lblComponents.left = new FormAttachment(0, 10);
		lblComponents.setLayoutData(fd_lblComponents);
		lblComponents.setText("Software");
		
		CLabel label = new CLabel(composite_1, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 45);
		fd_label.left = new FormAttachment(0, 10);
		label.setLayoutData(fd_label);
		label.setText("Operating system");
		
		textOs = new Text(composite_1, SWT.BORDER);
		FormData fd_textOs = new FormData();
		fd_textOs.bottom = new FormAttachment(0, 72);
		fd_textOs.right = new FormAttachment(0, 321);
		fd_textOs.top = new FormAttachment(0, 45);
		fd_textOs.left = new FormAttachment(0, 141);
		textOs.setLayoutData(fd_textOs);
		textOs.setText("Debian 7.1 Linux");
		textOs.setEditable(false);
		
		CLabel lblComponents_1 = new CLabel(composite_1, SWT.NONE);
		FormData fd_lblComponents_1 = new FormData();
		fd_lblComponents_1.left = new FormAttachment(0, 10);
		fd_lblComponents_1.right = new FormAttachment(100, -106);
		fd_lblComponents_1.top = new FormAttachment(0, 91);
		lblComponents_1.setLayoutData(fd_lblComponents_1);
		lblComponents_1.setText("Deployed platform services");
		
		tableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				if (!event.getSelection().isEmpty())
				{
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					PlatformService platformService = (PlatformService)selection.getFirstElement();
					Util.openEditor(new PlatformServiceEditorInput(platformService), PlatformServiceEditor.ID);
				}
			}
		});
		table = tableViewer.getTable();
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -10);
		fd_table.top = new FormAttachment(lblComponents_1, 6);
		fd_table.left = new FormAttachment(0, 10);
		fd_table.right = new FormAttachment(100, -10);
		table.setLayoutData(fd_table);
		
		txtName = new Text(this, SWT.BORDER);
		txtName.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		FormData fd_txtName = new FormData();
		fd_txtName.top = new FormAttachment(lblTitle, 0, SWT.TOP);
		fd_txtName.bottom = new FormAttachment(composite, -6);
		fd_txtName.right = new FormAttachment(100, -11);
		fd_txtName.left = new FormAttachment(lblTitle, 6);
		txtName.setLayoutData(fd_txtName);
		
		// Should not be called -- the call is auto-generated
		if (computingInfrastructureService != null) m_bindingContext = initDataBindings();

	}
	
	public void setComputingInfrastructureService (ComputingInfrastructureService cis)
	{
		changeSupport.firePropertyChange("instance", this.computingInfrastructureService, this.computingInfrastructureService = cis);
		if (cis == null)
		{
			this.editingDomain = null;
			this.infrastructureLayer = null;
			this.cloudProvider = null;
		    this.cloudDefinition = null;
		    this.setVisible(false);
		}
		else
		{
			this.editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(computingInfrastructureService.eResource().getResourceSet());
			this.infrastructureLayer = (InfrastructureLayer)computingInfrastructureService.eContainer();
			this.cloudProvider = (CloudEnvironment)infrastructureLayer.eContainer();
		    this.cloudDefinition = DefinitionProviderService.getInstance().
		    		getCloudDefinition(cloudProvider.getCloudProviderDescriptor().getProviderID());
		    this.setVisible(true);
		    
		    boolean enableChanges = cloudDefinition.getInfrastructureDescriptors().isEmpty();
		    txtCpu.setEditable(enableChanges);
		    txtCpuUnits.setEditable(enableChanges);
		    txtMemory.setEditable(enableChanges);
		    txtStorage.setEditable(enableChanges);
		}
	    
		if (m_bindingContext != null)
		{
			m_bindingContext.dispose();
		}
		m_bindingContext = initDataBindings();
	}
	
	public ComputingInfrastructureService getComputingInfrastructureService() {
		return computingInfrastructureService;
	}
	
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	private Text txtName;
	public void addPropertyChangeListener (PropertyChangeListener pcl)
	{
		changeSupport.addPropertyChangeListener(pcl);
	}
	
	
	private class UpdateDescriptorStrategy extends UpdateValueStrategy
	{
		@Override
		public Object convert(Object fromObject) {
			
			ComputingInfrastructureServiceDescriptor cisd = (ComputingInfrastructureServiceDescriptor)fromObject;
			List<ComputingInfrastructureServiceDescriptor> cisdList = CsmUtil.getComputingInfrastructureServicesDescriptors(cloudDefinition);
			
			if (cisd.getName().equals(CUSTOM_INSTANCE)) return cisd;
			
			if (cisdList.contains(cisd))
			{
				return CsmUtil.getSystemDescriptor(computingInfrastructureService, cisd);
			}
			else
			{
				ComputingInfrastructureServiceDescriptor model = cisd;
				
				for (ComputingInfrastructureServiceDescriptor id : cisdList)
				{
					if (id.getName().equals(model.getName()))
					{
						return id;
					}
				}
			}
			// TODO Auto-generated method stub
			return null;
		}
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = EMFEditObservables.observeMaps(editingDomain, listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.ENTITY__NAME});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList instanceNodesObserveList = EMFEditObservables.observeList(Realm.getDefault(), editingDomain, computingInfrastructureService, ArchitecturePackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE__PLATFORM_SERVICES);
		tableViewer.setInput(instanceNodesObserveList);
		//
		ObservableListContentProvider listContentProvider_1 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_1 = EMFEditObservables.observeMaps(editingDomain, listContentProvider_1.getKnownElements(), new EStructuralFeature[]{Literals.ENTITY__NAME});
		comboViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps_1));
		comboViewer.setContentProvider(listContentProvider_1);
		//
		IObservableList cloudDefinitionInstanceDescriptorsObserveList = EMFEditObservables.observeList(Realm.getDefault(), editingDomain, cloudDefinition, DefinitionPackage.Literals.CLOUD_DEFINITION__INFRASTRUCTURE_DESCRIPTORS);
		comboViewer.setInput(cloudDefinitionInstanceDescriptorsObserveList);
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboViewer);
		IObservableValue instanceDescriptorObserveValue = EMFEditObservables.observeValue(editingDomain, computingInfrastructureService, ArchitecturePackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR);
		bindingContext.bindValue(observeSingleSelectionComboViewer, instanceDescriptorObserveValue, new UpdateDescriptorStrategy(), new UpdateDescriptorStrategy());
		//
		IObservableValue observeTextTxtCpuUnitsObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtCpuUnits);
		IObservableValue instanceCpuUnitsObserveValue = EMFEditProperties.value(editingDomain, FeaturePath.fromList(ArchitecturePackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR, DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU_UNITS)).observe(computingInfrastructureService);
		bindingContext.bindValue(observeTextTxtCpuUnitsObserveWidget, instanceCpuUnitsObserveValue, new EMFUpdateValueStrategy(), new EMFUpdateValueStrategy());
		//
		IObservableValue observeTextTxtCpuObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtCpu);
		IObservableValue instanceCpuObserveValue = EMFEditProperties.value(editingDomain, FeaturePath.fromList(ArchitecturePackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR, DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__CPU)).observe(computingInfrastructureService);
		bindingContext.bindValue(observeTextTxtCpuObserveWidget, instanceCpuObserveValue, new EMFUpdateValueStrategy(), new EMFUpdateValueStrategy());
		//
		IObservableValue observeTextTxtMemoryObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtMemory);
		IObservableValue instanceStorageObserveValue = EMFEditProperties.value(editingDomain, FeaturePath.fromList(ArchitecturePackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR, DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__MEMORY)).observe(computingInfrastructureService);
		bindingContext.bindValue(observeTextTxtMemoryObserveWidget, instanceStorageObserveValue, new EMFUpdateValueStrategy(), new EMFUpdateValueStrategy());
		//
		IObservableValue observeTextTxtStorageObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtStorage);
		IObservableValue instanceStorageObserveValue_1 = EMFEditProperties.value(editingDomain, FeaturePath.fromList(ArchitecturePackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE__DESCRIPTOR, DefinitionPackage.Literals.COMPUTING_INFRASTRUCTURE_SERVICE_DESCRIPTOR__STORAGE)).observe(computingInfrastructureService);
		bindingContext.bindValue(observeTextTxtStorageObserveWidget, instanceStorageObserveValue_1, new EMFUpdateValueStrategy(), new EMFUpdateValueStrategy());
		//
		IObservableValue observeTextTxtNameObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtName);
		IObservableValue instanceNameObserveValue = EMFEditObservables.observeValue(editingDomain, computingInfrastructureService, Literals.ENTITY__NAME);
		bindingContext.bindValue(observeTextTxtNameObserveWidget, instanceNameObserveValue, null, null);
		//
		return bindingContext;
	}
}
