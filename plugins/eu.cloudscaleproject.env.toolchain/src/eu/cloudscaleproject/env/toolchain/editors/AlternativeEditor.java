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
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Composite;

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
	MPart part;
	
	@Inject
	MDirtyable dirtyable;
	
	private Composite editorComposite;
	private IEditorInputResource alternative;
	
	private PropertyChangeListener alternativeListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(IEditorInputResource.PROP_DIRTY.equals(evt.getPropertyName())){
				dirtyable.setDirty(alternative.isDirty());
			}
			if(IEditorInputResource.PROP_LOADED.equals(evt.getPropertyName())){
				if(editorComposite instanceof IRefreshable){
					((IRefreshable)editorComposite).refresh();
				}
			}
		}
	};
	
	public void configure(Composite composite, final IEditorInputResource alternative){
		
		if(this.alternative != null){
			this.alternative.removePropertyChangeListener(alternativeListener);
		}
		
		this.alternative = alternative;
		this.alternative.addPropertyChangeListener(alternativeListener);
				
		if(editorComposite != null && !editorComposite.isDisposed()){
			editorComposite.dispose();
		}
		
		part.setLabel("Analyser results ["+ alternative.getName() +"]");
		
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
		
		this.editorComposite = composite;
		
		composite.getParent().layout();
		composite.getParent().redraw();
	}
	
	@Focus
	public void focus(){
		if(this.editorComposite != null && (this.editorComposite instanceof ISelectable)){
			((ISelectable)this.editorComposite).onSelect();
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
