package eu.cloudscaleproject.env.analyser.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.analyser.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.analyser.editors.result.ResultsComposite;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;


/**
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 * 
 */

public class ResultsEditor{
		
	@Inject
	MPart part;
	
	@Inject
	MDirtyable dirtyable;
	
	private ResultsComposite configComposite;
	private ResultAlternative alternative;
	
	private PropertyChangeListener alternativeListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(IEditorInputResource.PROP_DIRTY.equals(evt.getPropertyName())){
				dirtyable.setDirty(alternative.isDirty());
			}
		}
	};
	
	@Inject
	public void initAlternative(Composite composite, ResultAlternative alternative){
		
		if(this.alternative != null){
			this.alternative.removePropertyChangeListener(alternativeListener);
		}
		
		this.alternative = alternative;
		this.alternative.addPropertyChangeListener(alternativeListener);
				
		if(configComposite != null && !configComposite.isDisposed()){
			configComposite.dispose();
		}
		
		part.setLabel("Analyser results ["+ alternative.getName() +"]");
		
		if(!alternative.isLoaded()){
			alternative.load();
		}
		
		alternative.validate();
		
		this.configComposite = new ResultsComposite(alternative, composite, SWT.NONE);
		
		composite.layout();
		composite.redraw();
	}
	
	@Focus
	public void focus(){
		if(this.configComposite != null){
			this.configComposite.onSelect();
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
