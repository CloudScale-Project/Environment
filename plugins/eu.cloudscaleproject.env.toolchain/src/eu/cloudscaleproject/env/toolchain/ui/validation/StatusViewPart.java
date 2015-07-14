package eu.cloudscaleproject.env.toolchain.ui.validation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;

public class StatusViewPart {
	
	private Composite composite;
	private TreeViewer treeViewer;
	
	private IProject project;
	private List<IValidationStatusProvider> statusProviders = null;
		
	private final PropertyChangeListener startusListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if(StatusManager.PROP_STATUS_PROVIDER_ADDED.equals(evt.getPropertyName())){
				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run() { reloadProviders(project); }
				});
			}
			
			if(StatusManager.PROP_STATUS_PROVIDER_REMOVED.equals(evt.getPropertyName())){
				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run() { reloadProviders(project); }
				});
			}
			
			if(StatusManager.PROP_STATUS_CHANGED.equals(evt.getPropertyName()) 
					|| StatusManager.PROP_STATUS_ADDED.equals(evt.getPropertyName()) 
					|| StatusManager.PROP_STATUS_REMOVED.equals(evt.getPropertyName()) ){
				
				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run() { reload(); }
				});
			}
			
		}
	};

	@PostConstruct
	public void postConstruct(Composite parent) {
		
		this.composite = new Composite(parent, SWT.NONE);
		this.composite.setLayout(new FillLayout());
		
		this.treeViewer = new TreeViewer(composite);
		this.treeViewer.setContentProvider(new StatusContentProvider());
		this.treeViewer.setLabelProvider(new StatusLabelProvider());
		this.treeViewer.setInput(statusProviders);
		this.treeViewer.expandAll();
		
		StatusManager.getInstance().addPropertyChangeListener(startusListener);
	}
	
	@Inject
	private void reloadProviders(@Optional IProject project){
		
		if(project == null){
			return;
		}
		
		this.project = project;
		
		//get project statuses
		this.statusProviders = StatusManager.getInstance().getStatusProviders(project);
		//get global statuses (not bound to specific project)
		this.statusProviders.addAll(StatusManager.getInstance().getStatusProviders(null));
		
		if(this.treeViewer != null){
			this.treeViewer.setInput(this.statusProviders);
			this.treeViewer.expandAll();
		}
	}
	
	private void reload(){
		this.treeViewer.expandAll();
		this.treeViewer.refresh(true);
	}
	
	
	@PreDestroy
	public void preDestroy() {
		StatusManager.getInstance().removePropertyChangeListener(startusListener);
		ValidationDiagramService.registerDiagramFactory(null);
	}
	
	@Focus
	public void onFocus(){
	}
	
}
