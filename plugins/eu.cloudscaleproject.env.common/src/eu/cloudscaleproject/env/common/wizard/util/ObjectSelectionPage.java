package eu.cloudscaleproject.env.common.wizard.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
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
	
	//TODO: Organize code in this class... Make two classes?
	//		Selection handles have different meaning, depending on the 'multipleSelection' boolean argument.

	private boolean multipleSelection = false;
	private List<T> objects;
	
	private List<T> selectedObjects = new ArrayList<T>();
	
	private StructuredViewer listViewer;
	private boolean selectAll = false;

	public ObjectSelectionPage(String title, String description, boolean multipleSelection, boolean selectAll)
	{
		super(title, description, null);
		setDescription(description);
		
		this.multipleSelection = multipleSelection;
		this.selectAll = selectAll;
	}
	
	public void setItems(List<T> items){
		
		this.objects = items;
		
		if(listViewer instanceof TableViewer){
			TableViewer tv = (TableViewer)listViewer;
			if(!tv.getTable().isDisposed()){
				listViewer.setInput(objects);
			}
		}
		if(listViewer instanceof ListViewer){
			ListViewer tv = (ListViewer)listViewer;
			if(!tv.getList().isDisposed()){
				listViewer.setInput(objects);
			}
		}
	}
	
	private void selectAll(){
				
		if(listViewer instanceof CheckboxTableViewer){
			CheckboxTableViewer tv = (CheckboxTableViewer)listViewer;
			if(!tv.getTable().isDisposed()){
				tv.setAllChecked(true);
				selectedObjects.clear();
				
				for(T o : objects){
					if(handleSelection(o)){
						selectedObjects.add(o);
					}
				}
				handleSelectionList(selectedObjects);
				
			}
		}
		if(listViewer instanceof ListViewer){
			ListViewer lv = (ListViewer)listViewer;
			if(!lv.getList().isDisposed()){
				lv.getList().selectAll();
			}
		}
		
	}

	@Override
	public void createControl(Composite parent)
	{

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		
		if(multipleSelection){
			
			final CheckboxTableViewer tableViewer = CheckboxTableViewer.newCheckList(container, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL);
			tableViewer.addCheckStateListener(new ICheckStateListener() {

				@SuppressWarnings("unchecked")
				@Override
				public void checkStateChanged(CheckStateChangedEvent event) {

					if(event.getChecked() && handleSelection((T)event.getElement())){
						selectedObjects.add((T)event.getElement());
					}
					else{
						tableViewer.setChecked((T)event.getElement(), false);
						selectedObjects.remove((T)event.getElement());
					}
					
					if(!handleSelectionList(getSelectionList())){
						setPageComplete(false);
					}
					else{
						if (selectedObjects.isEmpty()) {
							setPageComplete(false);
						} else {
							setPageComplete(true);
						}
					}

				}
			});
			
			listViewer = tableViewer;
		}
		else{
			
			listViewer = new ListViewer(container);
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
			
		}
		
		listViewer.setContentProvider(new ArrayContentProvider());		
		listViewer.setLabelProvider(createLabelProvider());
		
		if(objects != null){
			listViewer.setInput(objects);
		}
		
		if(selectAll){
			selectAll();
		}
		
		if (selectedObjects.isEmpty()) {
			setPageComplete(false);
		} 
		else {
			setPageComplete(true);
		}

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
