package eu.cloudscaleproject.env.staticspotter.editors;

import javax.annotation.PostConstruct;
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
	private MPart part;
	private SingleResultComposite composite;
	
	@Inject
	@Optional
	@PostConstruct
	public void postConstruct(Composite parent, ResultAlternative alternative){
		
		if(composite != null){
			composite.dispose();
		}
		
		part.setLabel("Extractor input ["+ alternative.getName() +"]");
		composite = new SingleResultComposite(parent, SWT.NONE, alternative);
		
		setAlternative(alternative);
		
		parent.layout();
		parent.redraw();
	}
	
}
