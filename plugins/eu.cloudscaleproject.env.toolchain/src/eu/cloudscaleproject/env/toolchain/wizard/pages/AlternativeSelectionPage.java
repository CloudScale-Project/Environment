package eu.cloudscaleproject.env.toolchain.wizard.pages;

import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.property.Properties;
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
	
	private String name = "";
	private ResourceProvider resourceProvider = null;
	
	private ListViewer listViewer;
	private ObservableListContentProvider listContentProvider;
	private ObservableMapLabelProvider listLabelProvider;
	
	private IEditorInputResource selectedAlternative = null;
	
	public AlternativeSelectionPage() {
		this(DEFAULT_TITLE, DEFAULT_DESCRIPTION);
	}
	
	public AlternativeSelectionPage(String name, String description) {
		super(name, name, null);
		
		setTitle(name);
		setDescription(description);
	}
	
	public AlternativeSelectionPage(String name, String description, ResourceProvider rp) {
		super(name, name, null);
		
		setTitle(name);
		setDescription(description);
		
		this.resourceProvider = rp;
		
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setResourceProvider(ResourceProvider rp){
		
		if(listViewer != null && !listViewer.getList().isDisposed()){
			
			if(rp != null){
				IObservableList resourcesResourceProviderObserveList = PojoProperties.list("resources").observe(rp);
				listViewer.setInput(resourcesResourceProviderObserveList);			
			}
			
		}
		
		this.resourceProvider = rp;
	}

	@Override
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
		
		setPageComplete(false);
		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		listContentProvider = new ObservableListContentProvider();
		IObservableMap observeMap = Properties.observeEach(listContentProvider.getKnownElements(), PojoProperties.values(new String[] { "name" }))[0];
		listLabelProvider = new ObservableMapLabelProvider(observeMap);
		
		listViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		listViewer.setContentProvider(listContentProvider);
		listViewer.setLabelProvider(listLabelProvider);
		listViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				
				Object selection = ((StructuredSelection)listViewer.getSelection()).getFirstElement();
				if(selection instanceof IEditorInputResource){
					selectedAlternative = (IEditorInputResource)selection;
					handleSelection((IEditorInputResource)selection);
				}
				
				checkComplete();

			}
		});

		setResourceProvider(resourceProvider);
		
		//auto-select first element
		Object e = listViewer.getElementAt(0);
		if (e != null){
			listViewer.setSelection(new StructuredSelection(e));
		}

		checkComplete();
	}
	
	public IEditorInputResource getSelection ()
	{
		return selectedAlternative;
	}
	
	public void handleSelection(IEditorInputResource eir){
		//Override
	}
	
	private void checkComplete()
	{
		this.setPageComplete(selectedAlternative != null);
	}
}
