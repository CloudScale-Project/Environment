package org.scaledl.overview.diagram.editor.system;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.scaledl.overview.Overview;
import org.scaledl.overview.architecture.Architecture;
import org.scaledl.overview.architecture.ArchitecturePackage;
import org.scaledl.overview.core.CorePackage.Literals;
import org.scaledl.overview.diagram.SWTResourceManager;

public class CloudProviderListComposite extends Composite {
	private DataBindingContext m_bindingContext;
	private Overview overview;
	private TransactionalEditingDomain editingDomain;
	private Architecture architecture;
	private CloudProviderModel cloudProviderModel;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CloudProviderListComposite(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -10);
		fd_table.top = new FormAttachment(0, 10);
		fd_table.right = new FormAttachment(100, -10);
		fd_table.left = new FormAttachment(0, 10);
		table.setLayoutData(fd_table);
		
		// Should not be called -- the call is auto-generated
		if (overview != null) m_bindingContext = initDataBindings();
	}
	
	public void setCloudProviderModel(CloudProviderModel cloudProviderModel) {
		this.cloudProviderModel = cloudProviderModel;
	}
	
	public void setOverview (Overview overview)
	{
		this.overview = overview;
		this.architecture = this.overview.getArchitecture();
		this.editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(overview.eResource().getResourceSet());
		
		if (m_bindingContext != null) m_bindingContext.dispose();
		m_bindingContext =  initDataBindings();
		
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				tableViewer.setSelection(new StructuredSelection(tableViewer.getElementAt(0)),true); 
			}
		});
	}
	
	public TableViewer getTableViewer()
	{
		return tableViewer;
	}
	
	
	private Table table;
	private TableViewer tableViewer;
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = EMFEditObservables.observeMaps(editingDomain, listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.ENTITY__NAME});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList architectureCloudProvidersObserveList = EMFEditObservables.observeList(Realm.getDefault(), editingDomain, architecture, ArchitecturePackage.Literals.ARCHITECTURE__CLOUD_ENVIRONMENTS);
		tableViewer.setInput(architectureCloudProvidersObserveList);
		//
		IObservableValue observeSingleSelectionTableViewer = ViewerProperties.singleSelection().observe(tableViewer);
		IObservableValue cloudProviderCloudProviderModelObserveValue = BeanProperties.value("cloudProvider").observe(cloudProviderModel);
		bindingContext.bindValue(observeSingleSelectionTableViewer, cloudProviderCloudProviderModelObserveValue, null, null);
		//
		return bindingContext;
	}
}
