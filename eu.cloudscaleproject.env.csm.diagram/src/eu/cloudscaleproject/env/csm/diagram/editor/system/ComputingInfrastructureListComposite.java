package eu.cloudscaleproject.env.csm.diagram.editor.system;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.CloudEnvironment;
import eu.cloudscaleproject.env.csm.architecture.ComputingInfrastructureService;
import eu.cloudscaleproject.env.csm.core.CorePackage;
import eu.cloudscaleproject.env.csm.diagram.SWTResourceManager;
import eu.cloudscaleproject.env.csm.util.CsmUtil;

public class ComputingInfrastructureListComposite extends Composite {
	private DataBindingContext m_bindingContext;
	private CloudEnvironment cloudEnvironement;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ComputingInfrastructureListComposite(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(255, 255, 255));
		setLayout(new FormLayout());
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(cloudEnvironement.eResource().getResourceSet());
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						CsmUtil.createDefaultComputingInfrastructureService(cloudEnvironement);
					}
				});
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(100, -47);
		fd_btnNewButton.right = new FormAttachment(100, -4);
		fd_btnNewButton.top = new FormAttachment(0, 10);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("+");
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(btnNewButton, -6);
		fd_table.bottom = new FormAttachment(100, -10);
		fd_table.top = new FormAttachment(0, 10);
		fd_table.left = new FormAttachment(0, 10);
		fd_table.left = new FormAttachment(0, 10);
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				if (!selection.isEmpty())
				{
					final ComputingInfrastructureService cis = (ComputingInfrastructureService) selection.getFirstElement();
					
					TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(cloudEnvironement.eResource().getResourceSet());
					editingDomain.getCommandStack().execute(
						new RecordingCommand(editingDomain) {
							
							@Override
							protected void doExecute() {
								
								cis.getInfrastructureLayer().getServices().remove(cis);
								cis.getPlatformServices().clear();
							}
						});
				}
			}
		});
		button.setText("-");
		FormData fd_button = new FormData();
		fd_button.left = new FormAttachment(btnNewButton, 0, SWT.LEFT);
		fd_button.top = new FormAttachment(btnNewButton, 6);
		fd_button.right = new FormAttachment(btnNewButton, 0, SWT.RIGHT);
		button.setLayoutData(fd_button);
		
		fd_table.bottom = new FormAttachment(100, -10);
		table.setLayoutData(fd_table);
		
		// Should not be called -- the call is auto-generated
		if (cloudEnvironement != null) m_bindingContext = initDataBindings();
	}
	
  private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
  
	public void setCloudProvider (CloudEnvironment cloudProvider)
	{
		changeSupport.firePropertyChange("cloudProvider", this.cloudEnvironement, this.cloudEnvironement = cloudProvider);
		this.cloudEnvironement = cloudProvider;
		if (m_bindingContext != null) m_bindingContext.dispose();
		m_bindingContext = initDataBindings();
		tableViewer.setSelection(new StructuredSelection(tableViewer.getElementAt(0)),true); 
	}
	
	
	public CloudEnvironment getCloudProvider() {
		return cloudEnvironement;
	}
	
	public void addPropertyChangeListener (PropertyChangeListener pcl)
	{
		changeSupport.addPropertyChangeListener(pcl);
	}
	
	public TableViewer getTableViewer() {
		return tableViewer;
	}
	
	
	
	private Table table;
	private TableViewer tableViewer;
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = EMFObservables.observeMaps(listContentProvider.getKnownElements(), new EStructuralFeature[]{CorePackage.Literals.ENTITY__NAME});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		
		//
		IObservableList cloudProviderInstancesObserveList = EMFObservables.observeList(Realm.getDefault(), cloudEnvironement.getInfrastructureLayer(), ArchitecturePackage.Literals.INFRASTRUCTURE_LAYER__SERVICES);
		tableViewer.setInput(cloudProviderInstancesObserveList);
		//
		return bindingContext;
	}
}
