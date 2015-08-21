package eu.cloudscaleproject.env.spotter.editors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.spotter.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.spotter.editors.composite.ResultAlternativeComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ResultEditor extends AlternativeEditor{

	@Inject
	private MPart part;
	private ResultAlternativeComposite composite;
	
	@Inject
	@Optional
	@PostConstruct
	public void postConstruct(Composite parent, ResultAlternative alternative){
		
		if(composite != null){
			composite.dispose();
		}
		
		part.setLabel("Extractor input ["+ alternative.getName() +"]");
		composite = new ResultAlternativeComposite(parent, SWT.NONE, alternative);
		
		setAlternative(alternative);
		
		parent.layout();
		parent.redraw();
	}
	
}