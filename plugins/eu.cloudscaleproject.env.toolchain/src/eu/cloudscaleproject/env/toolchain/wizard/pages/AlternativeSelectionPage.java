package eu.cloudscaleproject.env.toolchain.wizard.pages;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class AlternativeSelectionPage extends WizardPage{
	private static final String DEFAULT_TITLE = "Select alternative";
	private static final String DEFAULT_DESCRIPTION = "Please select alternative from a list.";


	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	
	private String name = "";
	private ResourceProvider resourceProvider;
	private ListViewer listViewer;

	public AlternativeSelectionPage(ResourceProvider resourceProvider) {
		super(DEFAULT_TITLE, DEFAULT_TITLE, null);
		setDescription(DEFAULT_DESCRIPTION);
		
		this.resourceProvider = resourceProvider;
	}
	
	public String getName(){
		return this.name;
	}

	@Override
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
		
		setPageComplete(false);
		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		listViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		
		listViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				checkComplete();
			}
		});
		m_bindingContext = initDataBindings();
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = BeansObservables.observeMap(listContentProvider.getKnownElements(), IEditorInputResource.class, "name");
		listViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
		listViewer.setContentProvider(listContentProvider);
		//
		IObservableList resourcesResourceProviderObserveList = PojoProperties.list("resources").observe(resourceProvider);
		listViewer.setInput(resourcesResourceProviderObserveList);
		//
		return bindingContext;
	}
	
	public IEditorInputResource getSelection ()
	{
		return (IEditorInputResource) ((StructuredSelection)listViewer.getSelection()).getFirstElement();
	}
	
	private void checkComplete()
	{
		this.setPageComplete(getSelection()!=null);
	}
}
