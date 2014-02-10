package eu.cloudscaleproject.env.csm.properties.components;


import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import eu.cloudscaleproject.env.csm.Csm;
import eu.cloudscaleproject.env.csm.architecture.OperationInterfaceContainer;
import eu.cloudscaleproject.env.csm.core.CoreFactory;
import eu.cloudscaleproject.env.csm.core.OperationInterface;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class ProvidedInterfaceComposite extends Composite {
	
	private CTabFolder tabFolder;
	private Image edit = AbstractUIPlugin.imageDescriptorFromPlugin("eu.cloudscaleproject.env.csm.properties", "icons/add.png").createImage();

	//private OperationInterfaceContainer opInterfaceContainer;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProvidedInterfaceComposite(Composite parent) {
		super(parent, SWT.NONE);
		
		FillLayout fl = new FillLayout();
		fl.type = SWT.HORIZONTAL;
		this.setLayout(fl);
		
	}
	
	public void setInput(final OperationInterfaceContainer opInterfaceContainer){
		//this.opInterfaceContainer = opInterfaceContainer;
		
		if (tabFolder!=null) 
			tabFolder.dispose();
		
		createTabFolder(opInterfaceContainer);
		
		for(OperationInterface opInterface : opInterfaceContainer.getProvidedInterfaces())
		{
			addTab(opInterface);
		}
	
		if (tabFolder.getTabList().length > 0)
			tabFolder.setSelection(0);
		
		
		
		tabFolder.layout();
		this.layout();
	}
	
	private void createTabFolder (final OperationInterfaceContainer opInterfaceContainer)
	{
		int style = SWT.BORDER;
		
		// If external model, do not show CLOSE button on tabs
		if(!CsmUtil.hasExternalModel(opInterfaceContainer)){
			style |= SWT.CLOSE; 
		}
		
		
		tabFolder = new CTabFolder(this, style );
		tabFolder.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		// If external model don't add ADD button
		if (!CsmUtil.hasExternalModel(opInterfaceContainer))
		{
			CLabel lblNewLabel = new CLabel(tabFolder, SWT.NONE);
			lblNewLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			lblNewLabel.setImage(edit);
			tabFolder.setTopRight(lblNewLabel, SWT.RIGHT );
			
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					addNewOperationInterface(opInterfaceContainer);
				}
			});
			
		    tabFolder.addCTabFolder2Listener(new CTabFolder2Adapter() {
				@Override
				public void close(CTabFolderEvent event) {
					// TODO Auto-generated method stub
					event.getSource();
					CTabItem ti = (CTabItem) event.item;
					IterfaceComposite ic = (IterfaceComposite) ti.getControl();
					OperationInterface providedInterface = ic.getProvidedInterface();
					removeOperationInterface(providedInterface);
				}
			});
		}
		
	}
	
	private CTabItem addTab (final OperationInterface opInterface)
	{
		CTabItem item = new CTabItem(tabFolder, SWT.NONE);
		item.setText(opInterface.getName());
		
		IterfaceComposite opIterfaceComposite = new IterfaceComposite(tabFolder, opInterface);
		item.setControl(opIterfaceComposite);
		tabFolder.setSelection(item);
		return item;
	}
	
	private void removeOperationInterface (final OperationInterface opInterface)
	{
		TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(opInterface.eResource().getResourceSet());
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				if (CsmUtil.hasExternalModel(opInterface))
				{
					Csm csm  = (Csm) EcoreUtil.getRootContainer(opInterface);
					csm.getArchitecture().getUnresolvedOperationInterfaces().add(opInterface);
				}
				else
				{
					EcoreUtil.remove(opInterface);
				}
			}
		});
	}
	
	private void addNewOperationInterface (final OperationInterfaceContainer container)
	{
		TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(container.eResource().getResourceSet());
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				OperationInterface opInterface = CoreFactory.eINSTANCE.createOperationInterface();
				opInterface.setName("newOperationInterface");
				container.getProvidedInterfaces().add(opInterface);
				addTab(opInterface);
				
				
			}
		});
	}
	
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
