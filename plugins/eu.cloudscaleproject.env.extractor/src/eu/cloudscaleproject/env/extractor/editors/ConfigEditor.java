package eu.cloudscaleproject.env.extractor.editors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.extractor.alternatives.ConfingAlternative;
import eu.cloudscaleproject.env.extractor.editors.composites.ConfigAlternativeComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ConfigEditor extends AlternativeEditor{

	@Inject
	private MPart part;
	private ConfigAlternativeComposite configComposite;
	
	@Inject
	@Optional
	@PostConstruct
	public void postConstruct(Composite parent, ConfingAlternative alternative){
		
		if(configComposite != null){
			configComposite.dispose();
		}
		
		part.setLabel("Extractor input ["+ alternative.getName() +"]");
		configComposite = new ConfigAlternativeComposite(parent, SWT.NONE, alternative);
		
		setAlternative(alternative);
		
		parent.layout();
		parent.redraw();
	}
}
