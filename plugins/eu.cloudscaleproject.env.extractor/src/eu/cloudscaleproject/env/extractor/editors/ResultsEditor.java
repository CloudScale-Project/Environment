package eu.cloudscaleproject.env.extractor.editors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.extractor.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.extractor.editors.composites.SingleResultComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ResultsEditor extends AlternativeEditor{

	@Inject
	private MPart part;
	private SingleResultComposite resultComposite;
	
	@Inject
	@Optional
	@PostConstruct
	public void postConstruct(Composite parent, ResultAlternative alternative){
		
		if(resultComposite != null){
			resultComposite.dispose();
		}
		
		part.setLabel("Extractor result ["+ alternative.getName() +"]");
		resultComposite = new SingleResultComposite(parent, SWT.NONE, alternative);
		
		setAlternative(alternative);
		
		parent.layout();
		parent.redraw();
	}
	
}
