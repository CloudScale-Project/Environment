package eu.cloudscaleproject.env.csm.diagram.editor.system;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;

import eu.cloudscaleproject.env.csm.architecture.ArchitecturePackage;
import eu.cloudscaleproject.env.csm.architecture.CloudProvider;
import eu.cloudscaleproject.env.csm.core.CorePackage.Literals;

public class InstancesListComposite extends Composite {
	private DataBindingContext m_bindingContext;
	private CloudProvider cloudProvider;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InstancesListComposite(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(255, 255, 255));
		setLayout(new FormLayout());
		
		Button btnNewButton = new Button(this, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.right = new FormAttachment(100, -10);
		fd_btnNewButton.left = new FormAttachment(0, 10);
		fd_btnNewButton.bottom = new FormAttachment(100, -10);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Add new instance");
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(0, 10);
		fd_table.bottom = new FormAttachment(btnNewButton, -6);
		fd_table.right = new FormAttachment(100, -10);
		fd_table.left = new FormAttachment(0, 10);
		table.setLayoutData(fd_table);
		
		// Should not be called -- the call is auto-generated
		if (cloudProvider != null) m_bindingContext = initDataBindings();
	}
	
  private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
  
	public void setCloudProvider (CloudProvider cloudProvider)
	{
		changeSupport.firePropertyChange("cloudProvider", this.cloudProvider, this.cloudProvider = cloudProvider);
		this.cloudProvider = cloudProvider;
		if (m_bindingContext != null) m_bindingContext.dispose();
		m_bindingContext = initDataBindings();
		tableViewer.setSelection(new StructuredSelection(tableViewer.getElementAt(0)),true); 
	}
	
	
	public CloudProvider getCloudProvider() {
		return cloudProvider;
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
		IObservableMap[] observeMaps = EMFObservables.observeMaps(listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.ENTITY__NAME});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList cloudProviderInstancesObserveList = EMFObservables.observeList(Realm.getDefault(), cloudProvider, ArchitecturePackage.Literals.CLOUD_PROVIDER__INSTANCES);
		tableViewer.setInput(cloudProviderInstancesObserveList);
		//
		return bindingContext;
	}
}
