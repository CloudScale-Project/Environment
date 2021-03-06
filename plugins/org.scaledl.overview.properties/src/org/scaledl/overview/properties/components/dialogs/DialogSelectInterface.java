package org.scaledl.overview.properties.components.dialogs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.scaledl.overview.application.OperationInterface;
import org.scaledl.overview.application.OperationInterfaceContainer;
import org.scaledl.overview.util.OverviewArchitectureUtil;

public class DialogSelectInterface extends Dialog {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private Table table;
	private OperationInterfaceContainer opInterfaceContainer;
	private TableViewer tableViewer;

	private final List<OperationInterface> possibleInterfaces;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DialogSelectInterface(Shell parentShell, OperationInterfaceContainer opInterfaceContainer) {
		super(parentShell);
		
		this.opInterfaceContainer = opInterfaceContainer;
		this.possibleInterfaces = new ArrayList<OperationInterface>();
		this.possibleInterfaces.addAll(OverviewArchitectureUtil.collectPotentialyRequiredInterfaces(opInterfaceContainer));
		this.possibleInterfaces.removeAll(opInterfaceContainer.getRequiredInterfaces());
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Require interface dialog");
	}
	

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		tableViewer = new TableViewer(container, SWT.MULTI | SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setWidth(200);
		tableColumn.setText("Required interface");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_1 = tableViewerColumn_1.getColumn();
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("Provider");

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		m_bindingContext = initDataBindings();
	}
	
	@Override
	protected void okPressed() {
		
		final LinkedList<OperationInterface> selectedInterfaces = new LinkedList<OperationInterface>();
		for (TableItem tableItem : tableViewer.getTable().getItems()) {
			
			if (tableItem.getChecked())
			{
				System.out.println("CHECK: "+tableItem.getData());
				selectedInterfaces.add((OperationInterface)tableItem.getData());
			}
		}
		
		if (!selectedInterfaces.isEmpty())
		{
			TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(opInterfaceContainer.eResource().getResourceSet());
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					opInterfaceContainer.getRequiredInterfaces().addAll(selectedInterfaces);
				}
			});
		}
		super.okPressed();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(688, 469);
	}
	
		/*
	private void initPossibleInterfaces()
	{
		PlatformService platformService = null;
		if (opInterfaceContainer instanceof DeployableSoftwareService)
		{
			platformService = (PlatformService)((DeployableSoftwareService)opInterfaceContainer).getRuntimePlatformService();
		}
		else 
		{
			throw new UnsupportedOperationException();
		}
		
	    Collection<Setting> find = EcoreUtil.UsageCrossReferencer.find(platformService, platformService.eResource());
	    
	    LinkedList<OperationInterface> interfaces = new LinkedList<OperationInterface>();
	    LinkedList<PlatformService> providerServices = new LinkedList<PlatformService>();
	    
	    for (Setting setting : find) {
			
	    	EObject obj = setting.getEObject();
	    	if (obj instanceof Connection)
	    	{
	    		Connection c = (Connection) obj;
	    		
	    		if (c instanceof InternalConnection)
	    		{
	    			InternalConnection ic = (InternalConnection) c;
	    			
	    			if (ic.getDestinationPlatformService() == platformService)
	    			{
	    				providerServices.add(ic.getSourcePlatformService());
	    			}
	    		}
	    		else if (c instanceof HybridConnection)
	    		{
	    			HybridConnection hc = (HybridConnection) c;
	    			
	    			if (hc.getTargetPlatformService() == platformService)
	    			{
	    				providerServices.add(hc.getSourcePlatformService());
	    			}
	    		}
	    		
	    		// Todo : External Connectors
	    	}
		}
	    
	    for (PlatformService provider : providerServices) {
	    	if (provider instanceof SoftwareServiceContainer)
	    	{
	    		for (SoftwareService as : ((SoftwareServiceContainer)provider).getSoftwareServices()) {
	    			interfaces.addAll(as.getProvidedInterfaces());
				}
	    	}
	    	else if (provider instanceof OperationInterfaceContainer)
	    	{
	    		interfaces.addAll( ((OperationInterfaceContainer)provider).getProvidedInterfaces());
	    	}
		}
		
	    this.possibleInterfaces = new LinkedList<OperationInterface>(interfaces);
	}
	    */

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), OperationInterface.class, new String[]{"name", "providingContainer.name"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList selfList = Properties.selfList(OperationInterface.class).observe(possibleInterfaces);
		tableViewer.setInput(selfList);
		//
		return bindingContext;
	}
}
