package eu.cloudscaleproject.env.staticspotter.editors;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.staticspotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.staticspotter.editors.composites.InputAlternativeComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class InputEditor extends AlternativeEditor{

	private InputAlternativeComposite composite;
	
	@Inject
	@Optional
	public void setAlternative(MPart part, Composite parent, InputAlternative alternative){
		
		if(composite != null){
			composite.dispose();
		}
		
		part.setLabel("Static spotter input ["+ alternative.getName() +"]");
		composite = new InputAlternativeComposite(parent, SWT.NONE, alternative);
		
		setAlternative(alternative);
		
		parent.layout();
		parent.redraw();
	}
	
}
