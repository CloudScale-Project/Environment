package eu.cloudscaleproject.env.toolchain.ui.validation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.IEclipseContext;
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

import eu.cloudscaleproject.env.common.BatchExecutor;
import eu.cloudscaleproject.env.common.notification.IValidationStatus.Warning;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.toolchain.IActiveResources;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;

public class StatusViewPart {
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(StatusViewPart.class.getName());
	
	public static final String PIN_STATUS = "pinStatusView";
	
	private Composite composite;
	private TreeViewer treeViewer;
	
	private List<IValidationStatusProvider> statusProviders = new ArrayList<IValidationStatusProvider>();
	
	private IActiveResources activeResources = null;
	private boolean pinStatus = false;
	
	@Inject
	private MPart part;
		
	private final PropertyChangeListener startusListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
						
			if(StatusManager.PROP_STATUS_PROVIDER_ADDED.equals(evt.getPropertyName())){
				reload();
			}
			
			if(StatusManager.PROP_STATUS_PROVIDER_REMOVED.equals(evt.getPropertyName())){
				IValidationStatusProvider sp = part.getContext().getLocal(IValidationStatusProvider.class);
				if(evt.getOldValue() == sp){
					part.getContext().remove(IValidationStatusProvider.class);
				}
				reload();
			}
			
			if(StatusManager.PROP_STATUS_CHANGED.equals(evt.getPropertyName()) 
					|| StatusManager.PROP_STATUS_ADDED.equals(evt.getPropertyName()) 
					|| StatusManager.PROP_STATUS_REMOVED.equals(evt.getPropertyName()) ){
				
				reload();
			}
			
		}
	};

	@PostConstruct
	public void postConstruct(IEclipseContext context, Composite parent, IActiveResources activeResources) {
		
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
				
				if(object instanceof WarningNode){
					Warning warning = ((WarningNode)object).getWarning();
					if(warning.handleWarning()){
						return;
					}
				}
				
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
		updateStatus(activeResources);
	}
	
	@Inject
	@Optional
	private void pinStatus(IEclipseContext context, @Named(PIN_STATUS) Boolean pinStatus){
		
		this.pinStatus = pinStatus;
		
		if(!pinStatus){
			if(this.treeViewer != null && !treeViewer.getTree().isDisposed()){
				if(!pinStatus){
					this.treeViewer.setInput(this.statusProviders);
				}
				this.treeViewer.expandAll();
			}
		}
	}
	
	@Inject
	public void updateStatus(IActiveResources activeResources){
		
		if(composite == null || composite.isDisposed()){
			return;
		}
		
		IValidationStatusProvider statusProvider = activeResources.getActiveStatusProvider();
		IProject project = activeResources.getActiveProject();
				
		this.statusProviders.clear();
		
		if(statusProvider != null){
			project = statusProvider.getProject();
			statusProviders.add(statusProvider);
		}
		
		if(project != null && !project.isAccessible()){
			project = null;
		}
		
		if(this.statusProviders.isEmpty()){
			this.statusProviders.addAll(StatusManager.getInstance().getStatusProviders(project));
		}
		
		if(this.treeViewer != null && !treeViewer.getTree().isDisposed()){
			if(!pinStatus){
				this.treeViewer.setInput(this.statusProviders);
			}
			this.treeViewer.expandAll();
		}
		
		this.activeResources = activeResources;
		part.getContext().set(IValidationStatusProvider.class, statusProvider);
		part.getContext().set(IProject.class, project);
	}
	
	private void reload(){
		
		BatchExecutor.getInstance().addUITask(this, "reload", new Runnable() {
			
			@Override
			public void run() {

				if(activeResources != null){
					updateStatus(activeResources);
				}
				
				if(!StatusViewPart.this.treeViewer.getTree().isDisposed()){
					StatusViewPart.this.treeViewer.expandAll();
					StatusViewPart.this.treeViewer.refresh(true);
				}
			}
		});
		
	}
	
	@PreDestroy
	public void preDestroy() {
		StatusManager.getInstance().removePropertyChangeListener(startusListener);
	}
	
	@Focus
	public void onFocus(){
	}
	
}
