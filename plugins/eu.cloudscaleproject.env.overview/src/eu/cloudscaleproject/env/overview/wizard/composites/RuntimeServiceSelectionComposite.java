package eu.cloudscaleproject.env.overview.wizard.composites;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.scaledl.overview.Overview;
import org.scaledl.overview.architecture.PlatformRuntimeService;

import eu.cloudscaleproject.env.overview.wizard.util.OverviewHelper;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.property.Properties;

public class RuntimeServiceSelectionComposite extends Composite
{
	private DataBindingContext m_bindingContext;

	private java.util.List<PlatformRuntimeService> runtimeServices;

	private ListViewer listViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RuntimeServiceSelectionComposite(Composite parent, int style)
	{
		super(parent, style);

		setLayout(new GridLayout(1, false));
		Label lblSelect = new Label(this, SWT.NONE);
		lblSelect.setText("Select platform runtime service");
		
		listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), PlatformRuntimeService.class, "name");
		listViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		listViewer.setContentProvider(listContentProvider);
		//
		IObservableList selfList = Properties.selfList(PlatformRuntimeService.class).observe(runtimeServices);
		listViewer.setInput(selfList);
		//
		return bindingContext;
	}
	
	public void setOverviewModel (Overview overview)
	{
		this.runtimeServices = OverviewHelper.getPlatformRuntimeServices(overview);
		if (m_bindingContext != null) m_bindingContext.dispose();
		m_bindingContext = initDataBindings();
	}
	
	public PlatformRuntimeService getPlatformRuntimeService ()
	{
		return (PlatformRuntimeService) ((StructuredSelection)listViewer.getSelection()).getFirstElement();
	}
	
	public boolean isComplete()
	{
		return getPlatformRuntimeService() != null;
	}
}
