package eu.cloudscaleproject.env.toolchain.ui.validation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagram;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;

public class StatusViewPart {
	
	private Composite composite;
	private TreeViewer treeViewer;
	
	private List<IValidationStatusProvider> statusProviders = null;
	
	@Inject
	private MPart part;
	private IProject currentProject = null;
		
	private final PropertyChangeListener startusListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
						
			if(StatusManager.PROP_STATUS_PROVIDER_ADDED.equals(evt.getPropertyName())){
				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run() { doReloadProviders(currentProject); }
				});
			}
			
			if(StatusManager.PROP_STATUS_PROVIDER_REMOVED.equals(evt.getPropertyName())){
				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run() { doReloadProviders(currentProject); }
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
		
		final StatusContentProvider statusContentProvider = new StatusContentProvider();
		final StatusLabelProvider statusLabelProvider = new StatusLabelProvider();
		
		this.treeViewer = new TreeViewer(composite);
		this.treeViewer.setContentProvider(statusContentProvider);
		this.treeViewer.setLabelProvider(statusLabelProvider);
		this.treeViewer.setInput(statusProviders);
		this.treeViewer.expandAll();
		
		this.treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				Object object = selection.getFirstElement();
				
				IValidationStatusProvider statusProvider = null;
				
				while(statusProvider == null && object != null){
					if(object instanceof IValidationStatusProvider){
						statusProvider = (IValidationStatusProvider)object;
					}
					else{
						object = statusContentProvider.getParent(object);
					}
				}
				
				if(statusProvider instanceof IEditorInputResource){
					OpenAlternativeUtil.openAlternative((IEditorInputResource)statusProvider);
				}
			}
		});
		
		StatusManager.getInstance().addPropertyChangeListener(startusListener);
	}
	
	@Inject
	private void reloadProviders(@Named(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM) @Optional IValidationDiagram diagram){
						
		this.currentProject = diagram != null ? diagram.getProject() : null;		
		doReloadProviders(this.currentProject);
	}
	
	private void doReloadProviders(IProject project){
		if(project == null){
			part.setLabel("Validation status");
			part.setDescription("Open Dashboard editor to show current project status");
			
			this.statusProviders = new ArrayList<IValidationStatusProvider>();
		}
		else{
			part.setLabel("Validation status [" + project.getName() + "]");
			part.setDescription("Current project validation status");
			
			//get project statuses
			this.statusProviders = StatusManager.getInstance().getStatusProviders(project);
		}
		
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
