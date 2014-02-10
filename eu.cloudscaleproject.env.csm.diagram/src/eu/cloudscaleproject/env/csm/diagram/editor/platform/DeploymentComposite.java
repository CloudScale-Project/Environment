package eu.cloudscaleproject.env.csm.diagram.editor.platform;

import java.util.HashMap;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
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

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.architecture.DeployablePlatformService;
import eu.cloudscaleproject.env.csm.architecture.InfrastructureService;
import eu.cloudscaleproject.env.csm.definition.CloudDefinition;
import eu.cloudscaleproject.env.csm.diagram.SWTResourceManager;
import eu.cloudscaleproject.env.csm.diagram.Util;
import eu.cloudscaleproject.env.csm.diagram.editor.system.SystemEditor;
import eu.cloudscaleproject.env.csm.diagram.editor.system.SystemEditorInput;

public class DeploymentComposite extends Composite {
	private Text txtCpu;
	private Text txtCpuUnits;
	private Text txtMemory;
	private DeployablePlatformService dpService;
	private Combo cbInstances;
	private HashMap<String, ComputingInfrastructureService> mapInstances;
	private CLabel label;
	private Text txtStorage;

	private TransactionalEditingDomain editingDomain;

	private CloudEnvironment cloudEnvironment;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DeploymentComposite(Composite parent, DeployablePlatformService dpService, CloudDefinition cloudDefinition) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		this.dpService = dpService;
		this.cloudEnvironment = (CloudEnvironment)dpService.eContainer().eContainer();
		
		ResourceSet rset = this.dpService.eResource().getResourceSet();  
		this.editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(rset);
		
		CLabel lblInstance = new CLabel(this, SWT.NONE);
		lblInstance.setText("Instance");
		FormData fd_lblInstance = new FormData();
		lblInstance.setLayoutData(fd_lblInstance);
		
		CLabel lblCpu = new CLabel(this, SWT.NONE);
		fd_lblInstance.left = new FormAttachment(lblCpu, 0, SWT.LEFT);
		lblCpu.setLeftMargin(10);
		lblCpu.setText("CPU (MHz)");
		FormData fd_lblCpu = new FormData();
		fd_lblCpu.top = new FormAttachment(lblInstance, 17);
		fd_lblCpu.left = new FormAttachment(0, 10);
		lblCpu.setLayoutData(fd_lblCpu);
		
		CLabel lblCpuUnits = new CLabel(this, SWT.NONE);
		fd_lblCpu.right = new FormAttachment(lblCpuUnits, 0, SWT.RIGHT);
		lblCpuUnits.setLeftMargin(10);
		lblCpuUnits.setText("CPU (Units)");
		FormData fd_lblCpuUnits = new FormData();
		fd_lblCpuUnits.left = new FormAttachment(0, 10);
		fd_lblCpuUnits.top = new FormAttachment(lblCpu, 6);
		lblCpuUnits.setLayoutData(fd_lblCpuUnits);
		
		CLabel lblMemory = new CLabel(this, SWT.NONE);
		lblMemory.setLeftMargin(10);
		lblMemory.setText("Memory (MB)");
		FormData fd_lblMemory = new FormData();
		fd_lblMemory.left = new FormAttachment(0, 10);
		fd_lblMemory.top = new FormAttachment(lblCpuUnits, 6);
		lblMemory.setLayoutData(fd_lblMemory);
		
		txtCpu = new Text(this, SWT.BORDER);
		txtCpu.setEditable(false);
		FormData fd_txtCpu = new FormData();
		fd_txtCpu.left = new FormAttachment(lblCpu, 40);
		fd_txtCpu.bottom = new FormAttachment(lblCpu, 0, SWT.BOTTOM);
		fd_txtCpu.top = new FormAttachment(lblCpu, 0, SWT.TOP);
		txtCpu.setLayoutData(fd_txtCpu);
		
		txtCpuUnits = new Text(this, SWT.BORDER);
		txtCpuUnits.setEditable(false);
		fd_txtCpu.right = new FormAttachment(txtCpuUnits, 0, SWT.RIGHT);
		FormData fd_txtCpuUnits = new FormData();
		fd_txtCpuUnits.left = new FormAttachment(lblCpuUnits, 40);
		fd_txtCpuUnits.top = new FormAttachment(lblCpuUnits, 0, SWT.TOP);
		fd_txtCpuUnits.bottom = new FormAttachment(lblCpuUnits, 0, SWT.BOTTOM);
		txtCpuUnits.setLayoutData(fd_txtCpuUnits);
		
		txtMemory = new Text(this, SWT.BORDER);
		txtMemory.setEditable(false);
		fd_txtCpuUnits.right = new FormAttachment(txtMemory, 0, SWT.RIGHT);
		FormData fd_txtMemory = new FormData();
		fd_txtMemory.bottom = new FormAttachment(lblMemory, 0, SWT.BOTTOM);
		fd_txtMemory.right = new FormAttachment(100, -159);
		fd_txtMemory.left = new FormAttachment(lblMemory, 27);
		fd_txtMemory.top = new FormAttachment(lblMemory, 0, SWT.TOP);
		txtMemory.setLayoutData(fd_txtMemory);
		
		this.cbInstances = new Combo(this, SWT.NONE);
		fd_lblInstance.bottom = new FormAttachment(cbInstances, 0, SWT.BOTTOM);
		FormData fd_cbInstances = new FormData();
		fd_cbInstances.top = new FormAttachment(0, 10);
		fd_cbInstances.right = new FormAttachment(100, -159);
		fd_cbInstances.left = new FormAttachment(lblInstance, 32);
		cbInstances.setLayoutData(fd_cbInstances);
		
		label = new CLabel(this, SWT.NONE);
		label.setText("Storage (GB)");
		label.setLeftMargin(10);
		FormData fd_label = new FormData();
		fd_label.left = new FormAttachment(0, 10);
		fd_label.top = new FormAttachment(lblMemory, 6);
		label.setLayoutData(fd_label);
		
		txtStorage = new Text(this, SWT.BORDER);
		txtStorage.setEditable(false);
		txtStorage.setText("0");
		FormData fd_txtStorage = new FormData();
		fd_txtStorage.right = new FormAttachment(100, -159);
		fd_txtStorage.left = new FormAttachment(label, 35);
		fd_txtStorage.top = new FormAttachment(txtMemory, 6);
		txtStorage.setLayoutData(fd_txtStorage);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		fd_txtStorage.bottom = new FormAttachment(btnNewButton, -24);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Csm csm = (Csm)EcoreUtil.getRootContainer(DeploymentComposite.this.dpService);
				SystemEditorInput input = new SystemEditorInput(csm);
				input.setSelection(DeploymentComposite.this.dpService.getComputingInfrastructureService());
				Util.openEditor(input, SystemEditor.ID);
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.right = new FormAttachment(0, 159);
		fd_btnNewButton.top = new FormAttachment(label, 24);
		fd_btnNewButton.left = new FormAttachment(0, 10);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("View instance");
		
		initData();
		
		cbInstances.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				RecordingCommand rc = new RecordingCommand(editingDomain) {
					
					@Override
					protected void doExecute() {
						
						ComputingInfrastructureService cis = mapInstances.get(cbInstances.getText());
						cis.getPlatformServices().add(DeploymentComposite.this.dpService);
						initInstance();
					}
				};
				
				editingDomain.getCommandStack().execute(rc);
			}
		});
	}
	
	private void initData ()
	{
		// txtOs.setText(node.getOsDescriptor().getOs_name() + " : "+node.getOsDescriptor().getOs_version());
		
		this.mapInstances = new HashMap<String, ComputingInfrastructureService>();
		
		int idx=0;
		int selection = -1;
		for (InfrastructureService is : cloudEnvironment.getInfrastructureLayer().getServices()) {
			
			if (is instanceof ComputingInfrastructureService)
			{
				cbInstances.add(is.getName());
				this.mapInstances.put(is.getName(), (ComputingInfrastructureService)is);
				
				if (dpService.getComputingInfrastructureService() == is)
				{
					selection=idx;
				}
				idx++;
			}
		}
		
		cbInstances.select(selection);
		
		initInstance();
	}
	
	private void initInstance ()
	{
		if (dpService.getComputingInfrastructureService()!= null)
		{
			txtCpu.setText(dpService.getComputingInfrastructureService().getDescriptor().getCpu()+"");
			txtCpuUnits.setText(dpService.getComputingInfrastructureService().getDescriptor().getCpuUnits()+"");
			txtMemory.setText(dpService.getComputingInfrastructureService().getDescriptor().getMemory()+"");
			txtStorage.setText(dpService.getComputingInfrastructureService().getDescriptor().getStorage()+"");
		}
		else
		{
			txtCpu.setText("");
			txtCpuUnits.setText("");
			txtMemory.setText("");
			txtStorage.setText("");
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
