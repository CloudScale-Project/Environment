package eu.cloudscaleproject.env.analyser.editors;

import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.editors.input.InputComposite;


/**
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 * 
 */

public class InputEditor{
		
	@Inject
	MPart part;
	
	@Inject
	MDirtyable dirtyable;
	
	private InputComposite inputComposite;
	private InputAlternative alternative;
	
	@Inject
	public void initAlternative(Composite composite, InputAlternative alternative){
		
		this.alternative = alternative;
		
		if(inputComposite != null && !inputComposite.isDisposed()){
			inputComposite.dispose();
		}
		
		part.setLabel("Analyser input ["+ alternative.getName() +"]");
		
		if(!alternative.isLoaded()){
			alternative.load();
		}
		
		alternative.validate();
		
		this.inputComposite = new InputComposite(alternative, composite, SWT.NONE);
		
		composite.layout();
		composite.redraw();
	}
	
	@Focus
	public void focus(){
		if(this.inputComposite != null){
			this.inputComposite.onSelect();
		}
	}
	
	@Persist
	public void persist(){
		if(this.alternative != null){
			this.alternative.save();
		}
	}

}
