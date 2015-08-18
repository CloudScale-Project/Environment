package eu.cloudscaleproject.env.analyser.editors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.analyser.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.analyser.editors.result.ResultsComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;


/**
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 * 
 */

public class ResultsEditor extends AlternativeEditor{
		
	@Inject
	private MPart part;
	private ResultsComposite resultsComposite;
	
	@Inject
	@Optional
	@PostConstruct
	public void postConstruct(Composite parent, ResultAlternative alternative){
		
		if(resultsComposite != null){
			resultsComposite.dispose();
		}
		
		part.setLabel("Analyser results ["+ alternative.getName() +"]");
		resultsComposite = new ResultsComposite(alternative, parent, SWT.NONE);
		
		setAlternative(alternative);
		
		parent.layout();
		parent.redraw();
	}

}
