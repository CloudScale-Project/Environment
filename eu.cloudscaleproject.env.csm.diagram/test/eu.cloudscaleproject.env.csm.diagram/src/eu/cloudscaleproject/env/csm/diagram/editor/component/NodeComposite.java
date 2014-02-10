package eu.cloudscaleproject.env.csm.diagram.editor.component;

import java.util.HashMap;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService;
import eu.cloudscaleproject.env.csm.definition.CloudDefinition;
import eu.cloudscaleproject.env.csm.definition.ComputingInfrastructureServiceDescriptor;
import eu.cloudscaleproject.env.csm.diagram.Util;
import eu.cloudscaleproject.env.csm.diagram.editor.system.SystemEditor;
import eu.cloudscaleproject.env.csm.diagram.editor.system.SystemEditorInput;
import eu.cloudscaleproject.env.csm.diagram.util.CsmUtil;

public class NodeComposite extends Composite {
	
	private static final String CUSTOM_INSTANCE = "Custom instance";
	
	private Text txtOs;
	private Text txtCpu;
	private Text txtCpuUnits;
	private Text txtMemory;
	private DeployablePlatformService dpService;
	private Combo cbInstances;
	private CloudDefinition cloudDefinition;
	private HashMap<String, ComputingInfrastructureServiceDescriptor> mapInstances;
	private CLabel label;
	private Text txtStorage;

	@SuppressWarnings("unused")
	private TransactionalEditingDomain editingDomain;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public NodeComposite(Composite parent, DeployablePlatformService dpService, CloudDefinition cloudDefinition) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		this.dpService = dpService;
		ResourceSet rset = this.dpService.eResource().getResourceSet();  
		this.editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(rset);
		this.cloudDefinition = cloudDefinition;
		
		CLabel lblNewLabel = new CLabel(this, SWT.NONE);
		
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 10);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Operating system");
		
		CLabel lblInstance = new CLabel(this, SWT.NONE);
		lblInstance.setText("Instance");
		FormData fd_lblInstance = new FormData();
		fd_lblInstance.top = new FormAttachment(lblNewLabel, 25);
		fd_lblInstance.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblInstance.setLayoutData(fd_lblInstance);
		
		CLabel lblCpu = new CLabel(this, SWT.NONE);
		lblCpu.setLeftMargin(10);
		lblCpu.setText("CPU (MHz)");
		FormData fd_lblCpu = new FormData();
		fd_lblCpu.top = new FormAttachment(lblInstance, 20);
		fd_lblCpu.right = new FormAttachment(lblNewLabel, -10, SWT.RIGHT);
		fd_lblCpu.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblCpu.setLayoutData(fd_lblCpu);
		
		CLabel lblCpuUnits = new CLabel(this, SWT.NONE);
		lblCpuUnits.setLeftMargin(10);
		lblCpuUnits.setText("CPU (Units)");
		FormData fd_lblCpuUnits = new FormData();
		fd_lblCpuUnits.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		fd_lblCpuUnits.top = new FormAttachment(lblCpu, 6);
		lblCpuUnits.setLayoutData(fd_lblCpuUnits);
		
		CLabel lblMemory = new CLabel(this, SWT.NONE);
		lblMemory.setLeftMargin(10);
		lblMemory.setText("Memory (MB)");
		FormData fd_lblMemory = new FormData();
		fd_lblMemory.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		fd_lblMemory.top = new FormAttachment(lblCpuUnits, 6);
		lblMemory.setLayoutData(fd_lblMemory);
		
		txtOs = new Text(this, SWT.NONE);
		FormData fd_txtOs = new FormData();
		fd_txtOs.bottom = new FormAttachment(0, 37);
		fd_txtOs.top = new FormAttachment(0, 10);
		fd_txtOs.left = new FormAttachment(lblNewLabel, 6);
		fd_txtOs.right = new FormAttachment(100, -10);
		txtOs.setLayoutData(fd_txtOs);
		
		txtCpu = new Text(this, SWT.NONE);
		FormData fd_txtCpu = new FormData();
		fd_txtCpu.bottom = new FormAttachment(lblCpu, 0, SWT.BOTTOM);
		fd_txtCpu.left = new FormAttachment(0, 141);
		txtCpu.setLayoutData(fd_txtCpu);
		
		txtCpuUnits = new Text(this, SWT.NONE);
		fd_txtCpu.right = new FormAttachment(txtCpuUnits, 0, SWT.RIGHT);
		FormData fd_txtCpuUnits = new FormData();
		fd_txtCpuUnits.right = new FormAttachment(100, -150);
		fd_txtCpuUnits.left = new FormAttachment(lblCpuUnits, 42);
		fd_txtCpuUnits.bottom = new FormAttachment(lblCpuUnits, 0, SWT.BOTTOM);
		txtCpuUnits.setLayoutData(fd_txtCpuUnits);
		
		txtMemory = new Text(this, SWT.NONE);
		FormData fd_txtMemory = new FormData();
		fd_txtMemory.right = new FormAttachment(100, -150);
		fd_txtMemory.left = new FormAttachment(lblMemory, 27);
		fd_txtMemory.bottom = new FormAttachment(lblMemory, 0, SWT.BOTTOM);
		txtMemory.setLayoutData(fd_txtMemory);
		
		this.cbInstances = new Combo(this, SWT.NONE);
		FormData fd_cbInstances = new FormData();
		fd_cbInstances.left = new FormAttachment(lblInstance, 69);
		fd_cbInstances.right = new FormAttachment(100, -54);
		fd_cbInstances.top = new FormAttachment(lblInstance, 0, SWT.TOP);
		cbInstances.setLayoutData(fd_cbInstances);
		
		label = new CLabel(this, SWT.NONE);
		label.setText("Storage (GB)");
		label.setLeftMargin(10);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(lblMemory, 6);
		fd_label.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		label.setLayoutData(fd_label);
		
		txtStorage = new Text(this, SWT.NONE);
		txtStorage.setText("0");
		txtStorage.setEnabled(false);
		FormData fd_txtStorage = new FormData();
		fd_txtStorage.right = new FormAttachment(100, -150);
		fd_txtStorage.left = new FormAttachment(label, 34);
		fd_txtStorage.bottom = new FormAttachment(label, 0, SWT.BOTTOM);
		txtStorage.setLayoutData(fd_txtStorage);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Csm csm = (Csm)EcoreUtil.getRootContainer(NodeComposite.this.dpService);
				SystemEditorInput input = new SystemEditorInput(csm);
				input.setSelection(NodeComposite.this.dpService.getComputingInfrastructureService());
				Util.openEditor(input, SystemEditor.ID);
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(100, -10);
		fd_btnNewButton.right = new FormAttachment(txtCpu, -157, SWT.RIGHT);
		fd_btnNewButton.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("View instance");

		
		/*txtCpu.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (!txtCpu.isEnabled()) return;
				NodeComposite.this.node.getInstance().getDescriptor().setCpu(Integer.parseInt(txtCpu.getText()));
				FIXME- HACKNodeComposite.this.node.setName(NodeComposite.this.node.getName());
			}
		});
		
		txtCpuUnits.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (!txtCpuUnits.isEnabled()) return;
				NodeComposite.this.node.getInstance().getDescriptor().setCpuUnits(Integer.parseInt(txtCpuUnits.getText()));
				FIXME- HACKNodeComposite.this.node.setName(NodeComposite.this.node.getName());
			}
		});
		
		txtMemory.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (!txtMemory.isEnabled()) return;
				NodeComposite.this.node.getInstance().getDescriptor().setMemory(Integer.parseInt(txtMemory.getText()));
				FIXME- HACKNodeComposite.this.node.setName(NodeComposite.this.node.getName());
			}
		});
		
		txtStorage.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (!txtStorage.isEnabled()) return;
				NodeComposite.this.node.getInstance().getDescriptor().setStorage(Integer.parseInt(txtStorage.getText()));
				FIXME- HACKNodeComposite.this.node.setName(NodeComposite.this.node.getName());
			}
		});
		
		
		
		cbInstances.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				RecordingCommand rc = new RecordingCommand(editingDomain) {
					
					@Override
					protected void doExecute() {
						InstanceDescriptor instanceDescriptor = CsmUtil.getSystemDescriptor(NodeComposite.this.node, mapInstances.get(cbInstances.getText()));
						NodeComposite.this.node.getInstance().setDescriptor(instanceDescriptor);
						initInstance();
					}
				};
				
				editingDomain.getCommandStack().execute(rc);
			}
		});*/
		
		initData();
	}
	
	private void initData ()
	{
		// txtOs.setText(node.getOsDescriptor().getOs_name() + " : "+node.getOsDescriptor().getOs_version());
		
		initInstance();
		
		this.mapInstances = new HashMap<String, ComputingInfrastructureServiceDescriptor>();
		int selectionIndex = -1;
		int i = 0;
		for (ComputingInfrastructureServiceDescriptor cisDescriptor : CsmUtil.getComputingInfrastructureServicesDescriptors(cloudDefinition)) {
			cbInstances.add(cisDescriptor.getName());
			this.mapInstances.put(cisDescriptor.getName(), EcoreUtil.copy(cisDescriptor));
			
			if (dpService.getComputingInfrastructureService().getDescriptor().getName().equals(cisDescriptor.getName()))
			{
				selectionIndex = i;
			}
			
			i++;
		}
		
		
		if (mapInstances.isEmpty())
		{
			// Add custom instance
			ComputingInfrastructureServiceDescriptor id = dpService.getComputingInfrastructureService().getDescriptor();
			mapInstances.put(id.getName(), id);
			cbInstances.add(id.getName());
			selectionIndex = 0;
		}
		
		cbInstances.select(selectionIndex);
	}
	
	private void initInstance ()
	{
		txtCpu.setText(dpService.getComputingInfrastructureService().getDescriptor().getCpu()+"");
		txtCpuUnits.setText(dpService.getComputingInfrastructureService().getDescriptor().getCpuUnits()+"");
		txtMemory.setText(dpService.getComputingInfrastructureService().getDescriptor().getMemory()+"");
		txtStorage.setText(dpService.getComputingInfrastructureService().getDescriptor().getStorage()+"");
		
		boolean isCustom = dpService.getComputingInfrastructureService().getDescriptor().getName().equals(CUSTOM_INSTANCE); 
		txtCpu.setEnabled(isCustom);
		txtCpuUnits.setEnabled(isCustom);
		txtMemory.setEnabled(isCustom);
		txtStorage.setEnabled(isCustom);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
