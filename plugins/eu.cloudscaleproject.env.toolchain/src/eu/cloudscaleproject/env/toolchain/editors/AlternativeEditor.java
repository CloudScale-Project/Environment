package eu.cloudscaleproject.env.toolchain.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.PersistState;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeEditor {
	
	private static final String ALTERNATIVE_RESOURCE = "alternative_resource";
	
	@Inject
	private MDirtyable dirtyable;
	@Inject
	private Composite parentComposite;
	@Inject
	private MPart part;
	@Inject
	private EPartService partService;
	
	private IEditorInputResource alternative;
	
	private Composite composite;
	private Composite loadingComposite;
	private StackLayout stackLayout;
	
	private PropertyChangeListener alternativeListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(IEditorInputResource.PROP_DIRTY.equals(evt.getPropertyName())){
				dirtyable.setDirty(alternative.isDirty());
			}
			if(IEditorInputResource.PROP_LOADED.equals(evt.getPropertyName())){
				
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						if(!parentComposite.isDisposed()){
							for(Control control : parentComposite.getChildren()){
								if(control instanceof IRefreshable){
									((IRefreshable)control).refresh();
								}
							}
						}
					}
				});
				
			}
			if(IEditorInputResource.PROP_DELETED.equals(evt.getPropertyName())){
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						partService.hidePart(part, true);
					}
				});
			}
		}
	};
	
	@PostConstruct
	public void postConstruct(IEclipseContext context){
		
		String resourcePath = part.getPersistedState().get(ALTERNATIVE_RESOURCE);
		if(resourcePath != null){
			setInput(context, resourcePath);
		}
	}
	
	@Inject
	public void setInput(IEclipseContext context, @Named("input") String resourcePath){
		
		IEditorInputResource eir = ResourceRegistry.getInstance().getResource(resourcePath);
		if(eir != null){
			context.set(IResource.class, eir.getResource());
			context.set(eir.getClass().getName(), eir);
		}
	}
	
	protected IEditorInputResource getAlternative(){
		
		return this.alternative;
	}
	
	protected void setControl(Composite composite){
		
		if(this.stackLayout == null){
			stackLayout = new StackLayout();
			parentComposite.setLayout(stackLayout);
		}
		
		if(this.loadingComposite == null){
			loadingComposite = new Composite(parentComposite, SWT.NONE);
			GridLayout gl = new GridLayout();
			loadingComposite.setLayout(gl);
			Label lblLoading = new Label(loadingComposite, SWT.NONE);
			lblLoading.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
			lblLoading.setText("Loading alternative...");			
		}
		
		this.stackLayout.topControl = loadingComposite;
		this.parentComposite.layout();
		this.parentComposite.redraw();

		if(this.composite != null){
			this.composite.dispose();
		}
		this.composite = composite;
		this.stackLayout.topControl = composite;
		
		this.parentComposite.layout();
		this.parentComposite.redraw();
	}
	
	protected void setAlternative(final IEditorInputResource alternative){
		
		if(this.alternative == alternative){
			return;
		}
				
		if(this.alternative != null){
			this.alternative.removePropertyChangeListener(alternativeListener);
			if(this.alternative.isDirty()){
				boolean keep = DialogUtils.openQuestion("Save changes?", "Alternative has unsaved changes. Do you want to keep them?");
				if(keep){
					this.alternative.save();
				}
				else{
					this.alternative.load();
				}
			}
		}
		
		part.getContext().set(IProject.class, alternative.getProject());
		part.getContext().set(IEditorInputResource.class, alternative);
		part.getContext().set(IValidationStatusProvider.class, alternative);

		this.alternative = alternative;
		this.alternative.addPropertyChangeListener(alternativeListener);
				
		EditorInputJob loadJob = new EditorInputJob("Loading alternative") {
			
			@Override
			public IStatus execute(IProgressMonitor monitor) {
				
				monitor.beginTask("Loading '"+alternative.getName()+"' alternative", IProgressMonitor.UNKNOWN);
				if(!alternative.isLoaded()){
					alternative.load(monitor);
				}
				alternative.validate(monitor);
				monitor.done();
				
				return Status.OK_STATUS;
			}
		};
		loadJob.setUser(true);
		loadJob.schedule();
		
		focus();
		
		dirtyable.setDirty(alternative.isDirty());		
	}
	
	@Focus
	public void focus(){

		for(Control control : parentComposite.getChildren()){
			if(control instanceof ISelectable){
				((ISelectable)control).onSelect();
			}
		}
		
		parentComposite.forceFocus();
	}
	
	@Persist
	public void persist(){
		if(this.alternative != null){
			this.alternative.save();
		}
	}
	
	@PersistState
	public void persistState(){
		if(this.alternative != null){
			part.getPersistedState().put(ALTERNATIVE_RESOURCE, alternative.getResource().getLocation().toPortableString());
		}
	}
	
	@PreDestroy
	public void preDestroy(EModelService modelService, MApplication app){
		
		part.getContext().remove(IEditorInputResource.class);
		
		if(alternative != null){
			boolean openedElsewhere = ToolchainUtils.getOpenedAlternatives().contains(alternative);
			if(!openedElsewhere && alternative.isDirty()){
				EditorInputJob job = new EditorInputJob("Discarding changes...") {
					
					@Override
					public IStatus execute(IProgressMonitor monitor) {
						monitor.beginTask("Discarding changes", IProgressMonitor.UNKNOWN);
						alternative.load();
						monitor.done();
						return Status.OK_STATUS;
					}
				};
				job.setUser(false);
				job.schedule();				
			}
			
			this.alternative.removePropertyChangeListener(alternativeListener);
		}
		
	}
	
}
