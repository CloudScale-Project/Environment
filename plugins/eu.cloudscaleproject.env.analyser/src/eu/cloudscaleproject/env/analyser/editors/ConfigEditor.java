package eu.cloudscaleproject.env.analyser.editors;

import javax.inject.Inject;

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
	public void initialize(Composite composite, ConfAlternative alternative){
		
		ConfigComposite editorComposite = new ConfigComposite(alternative, composite, SWT.NONE);
		configure(editorComposite, alternative);
	}
	
}
