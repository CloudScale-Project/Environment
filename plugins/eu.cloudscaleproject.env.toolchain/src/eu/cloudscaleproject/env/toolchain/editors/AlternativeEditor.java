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
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
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
	
	private IEditorInputResource alternative;
	
	private PropertyChangeListener alternativeListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(IEditorInputResource.PROP_DIRTY.equals(evt.getPropertyName())){
				dirtyable.setDirty(alternative.isDirty());
			}
			if(IEditorInputResource.PROP_LOADED.equals(evt.getPropertyName())){
				
				for(Control control : parentComposite.getChildren()){
					if(control instanceof IRefreshable){
						((IRefreshable)control).refresh();
					}
				}
				
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
		
		this.alternative = alternative;
		this.alternative.addPropertyChangeListener(alternativeListener);
				
		if(!alternative.isLoaded()){
			EditorInputJob loadJob = new EditorInputJob("Loading alternative") {
				
				@Override
				public IStatus execute(IProgressMonitor monitor) {
					
					monitor.beginTask("Loading '"+alternative.getName()+"' alternative", IProgressMonitor.UNKNOWN);
					alternative.load(monitor);
					alternative.validate(monitor);
					monitor.done();
					
					return Status.OK_STATUS;
				}
			};
			loadJob.setUser(true);
			loadJob.schedule();
		}
	}
	
	@Focus
	public void focus(){
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
	public void preDestroy(){
		this.alternative.removePropertyChangeListener(alternativeListener);
	}
	
}
