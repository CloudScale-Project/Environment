package eu.cloudscaleproject.env.toolchain.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class AlternativeEditor {
	
	@Inject
	private MDirtyable dirtyable;
	@Inject
	private Composite parentComposite;
	@Inject
	private MPart part;
	@Inject
	private EPartService partService;
	
	private IEditorInputResource alternative;
	
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
	
	protected IEditorInputResource getAlternative(){
		return this.alternative;
	}
	
	protected void setAlternative(final IEditorInputResource alternative){
		
		if(this.alternative != null){
			this.alternative.removePropertyChangeListener(alternativeListener);
		}
		
		part.getContext().set(IEditorInputResource.class, alternative);
		
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
		
		if(alternative != null){
			ValidationDiagramService.showDiagram(alternative.getProject());
		}

		for(Control control : parentComposite.getChildren()){
			if(control instanceof ISelectable){
				((ISelectable)control).onSelect();
			}
		}
	}
	
	@Persist
	public void persist(){
		if(this.alternative != null){
			this.alternative.save();
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
		}
		
		this.alternative.removePropertyChangeListener(alternativeListener);
	}
	
}
