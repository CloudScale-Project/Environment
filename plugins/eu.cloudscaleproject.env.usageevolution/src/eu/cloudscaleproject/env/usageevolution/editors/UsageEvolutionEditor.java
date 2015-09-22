package eu.cloudscaleproject.env.usageevolution.editors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;
import eu.cloudscaleproject.env.usageevolution.editors.composite.UsageEvolutionComposite;

public class UsageEvolutionEditor extends AlternativeEditor{

	@Inject
	private MPart part;
	private UsageEvolutionComposite configComposite;
	
	@Inject
	@Optional
	@PostConstruct
	public void postConstruct(Composite parent, UsageEvolutionAlternative alternative){
		
		if(configComposite != null){
			configComposite.dispose();
		}
		
		part.setLabel("Extractor input ["+ alternative.getName() +"]");
		configComposite = new UsageEvolutionComposite(parent, SWT.NONE, alternative);
		
		setAlternative(alternative);
		
		parent.layout();
		parent.redraw();
	}
	
}
