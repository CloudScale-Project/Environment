package eu.cloudscaleproject.env.analyser.editors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.editors.config.ConfigComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;


/**
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 * 
 */

public class ConfigEditor extends AlternativeEditor{

	@Inject
	private MPart part;
	private ConfigComposite configComposite;
	
	@Inject
	@Optional
	@PostConstruct
	public void postConstruct(Composite composite, ConfAlternative alternative){
		
		if(configComposite != null){
			configComposite.dispose();
		}
		
		part.setLabel("Analyser config ["+ alternative.getName() +"]");
		configComposite = new ConfigComposite(alternative, composite, SWT.NONE);
		
		setAlternative(alternative);
	}
	
}
