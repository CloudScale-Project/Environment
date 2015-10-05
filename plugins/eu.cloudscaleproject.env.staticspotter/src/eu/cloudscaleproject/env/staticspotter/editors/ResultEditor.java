package eu.cloudscaleproject.env.staticspotter.editors;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.staticspotter.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.staticspotter.editors.composites.SingleResultComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ResultEditor extends AlternativeEditor{
	
	@Inject
	@Optional
	public void setAlternative(MPart part, Composite parent, ResultAlternative alternative){
		
		setAlternative(alternative);
		setControl(new SingleResultComposite(parent, SWT.NONE, alternative));
		
		part.setLabel("Static spotter result ["+ alternative.getName() +"]");
	}
	
}
