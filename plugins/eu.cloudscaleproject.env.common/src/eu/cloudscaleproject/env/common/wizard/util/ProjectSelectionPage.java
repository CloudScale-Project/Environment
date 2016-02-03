package eu.cloudscaleproject.env.common.wizard.util;

import org.eclipse.core.resources.IProject;
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

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class ProjectSelectionPage extends WizardPage{
	
	private static final String DEFAULT_TITLE = "CloudScale project selection";
	private static final String DEFAULT_DESCRIPTION = "Please select the desired Cloudscale project.";

	private IProject project;

	public ProjectSelectionPage()
	{
		super(DEFAULT_TITLE, DEFAULT_TITLE, null);
		setDescription(DEFAULT_DESCRIPTION);
	}

	@Override
	public void createControl(Composite parent)
	{

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		
		ListViewer listViewer = new ListViewer(container);
		listViewer.setContentProvider(new ArrayContentProvider());		
		listViewer.setLabelProvider(new WorkbenchLabelProvider());
		
		listViewer.setInput(ExplorerProjectPaths.getCloudScaleProjects());
		
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				if(event.getSelection() instanceof StructuredSelection){
					StructuredSelection ss = (StructuredSelection)event.getSelection();
					Object selection = ss.getFirstElement();
					
					if(selection instanceof IProject){
						project = (IProject)selection;
						setPageComplete(true);
						
						handleSelection(project);
					}
				}
			}
		});

		setPageComplete(false);
		setControl(container);
	}
	
	public IProject getProject(){
		return this.project;
	}
	
	public void handleSelection(IProject project){
		//Override
	}

}
