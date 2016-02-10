package eu.cloudscaleproject.env.common.wizard.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class ObjectSelectionPage<T> extends WizardPage{

	private boolean multipleSelection = false;
	private List<T> objects;
	
	private List<T> selectedObjects = new ArrayList<T>();
	
	private StructuredViewer listViewer;

	public ObjectSelectionPage(String title, String description, boolean multipleSelection)
	{
		super(title, description, null);
		setDescription(description);
		
		this.multipleSelection = multipleSelection;
	}
	
	public void setItems(List<T> items){
		
		this.objects = items;
		
		if(listViewer instanceof TableViewer){
			TableViewer tv = (TableViewer)listViewer;
			if(!tv.getTable().isDisposed()){
				listViewer.setInput(objects);
			}
		}
		if(listViewer instanceof TableViewer){
			ListViewer tv = (ListViewer)listViewer;
			if(!tv.getList().isDisposed()){
				listViewer.setInput(objects);
			}
		}
	}

	@Override
	public void createControl(Composite parent)
	{

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		
		if(multipleSelection){
			listViewer = new TableViewer(container, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.SINGLE);
		}
		else{
			listViewer = new ListViewer(container);
		}
		
		listViewer.setContentProvider(new ArrayContentProvider());		
		listViewer.setLabelProvider(createLabelProvider());
		
		if(objects != null){
			listViewer.setInput(objects);
		}
		
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				if(event.getSelection() instanceof StructuredSelection){
					StructuredSelection ss = (StructuredSelection)event.getSelection();
					
					if(ss != null){
						
						selectedObjects.clear();
						selectedObjects.addAll(ss.toList());
						
						if(!handleSelectionList(getSelectionList())){
							selectedObjects.clear();
						}
						if(!handleSelection(getSelection())){
							selectedObjects.clear();
						}
						
						if(selectedObjects.isEmpty()){
							setPageComplete(false);
						}
						else{
							setPageComplete(true);
						}
						
					}
					
				}
			}
		});

		setPageComplete(false);
		setControl(container);
	}
	
	protected ILabelProvider createLabelProvider(){
		return new WorkbenchLabelProvider();
	}
	
	public boolean handleSelectionList(List<T> selectedItems){
		return true;
	}
	
	public boolean handleSelection(T selectedItem){
		return true;
	}
	
	public T getSelection(){
		
		if(this.selectedObjects.isEmpty()){
			return null;
		}
		
		return this.selectedObjects.get(0);		
	}
	
	public List<T> getSelectionList(){
		
		return this.selectedObjects;		
	}

}
