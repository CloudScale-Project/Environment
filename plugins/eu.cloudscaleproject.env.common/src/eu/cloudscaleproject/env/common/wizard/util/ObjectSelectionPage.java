package eu.cloudscaleproject.env.common.wizard.util;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class ObjectSelectionPage<T> extends WizardPage{

	private List<T> objects;
	
	private T selectedObject;

	public ObjectSelectionPage(String title, String description, List<T> objects)
	{
		super(title, description, null);
		setDescription(description);
		
		this.objects = objects;
	}

	@Override
	public void createControl(Composite parent)
	{

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		
		ListViewer listViewer = new ListViewer(container);
		listViewer.setContentProvider(new ArrayContentProvider());		
		listViewer.setLabelProvider(new WorkbenchLabelProvider());
		
		listViewer.setInput(objects);
		
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				if(event.getSelection() instanceof StructuredSelection){
					StructuredSelection ss = (StructuredSelection)event.getSelection();
					
					if(ss != null){
						selectedObject = (T)ss.getFirstElement();
						setPageComplete(true);
					}
					
				}
			}
		});

		setPageComplete(false);
		setControl(container);
	}
	
	public T getSelection(){
		return this.selectedObject;
	}

}
