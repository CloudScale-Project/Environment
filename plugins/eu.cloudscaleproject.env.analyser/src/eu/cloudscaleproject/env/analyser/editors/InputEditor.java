package eu.cloudscaleproject.env.analyser.editors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.editors.input.InputComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;


/**
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 * 
 */

public class InputEditor extends AlternativeEditor{
		
	@Inject
	private MPart part;
	private InputComposite composite;
	
	@Inject
	@Optional
	@PostConstruct
	public void postConstruct(Composite parent, InputAlternative alternative){
		
		if(composite != null){
			composite.dispose();
		}
		
		part.setLabel("Analyser input ["+ alternative.getName() +"]");
		composite = new InputComposite(alternative, parent, SWT.NONE);
		
		setAlternative(alternative);
	}

}
