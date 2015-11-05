package eu.cloudscaleproject.env.usageevolution.editors;

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
	@Optional
	public void setAlternative(MPart part, Composite parent, UsageEvolutionAlternative alternative){
		
		setAlternative(alternative);
		setControl(new UsageEvolutionComposite(parent, SWT.NONE, alternative));
		
		part.setLabel("Usage evolution ["+ alternative.getName() +"]");
	}
	
}
