package eu.cloudscaleproject.env.analyser.editors;

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
	@Optional
	public void setAlternative(MPart part, Composite parent, InputAlternative alternative){
				
		setAlternative(alternative);
		setControl(new InputComposite(alternative, parent, SWT.NONE));
		
		part.setLabel("Analyser input ["+ alternative.getName() +"]");
	}
}
