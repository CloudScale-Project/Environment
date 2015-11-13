package eu.cloudscaleproject.env.usageevolution.editors;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.usageevolution.UsageEvolutionAlternative;
import eu.cloudscaleproject.env.usageevolution.editors.composite.UsageEvolutionComposite;

public class UsageEvolutionEditor extends AlternativeEditor{
	
	@Inject
	@Optional
	public void setAlternative(MPart part, Composite parent, UsageEvolutionAlternative alternative){
		part.setLabel("Usage evolution ["+ alternative.getName() +"]");
	}

	@Override
	public Composite createComposite(Composite composite, IEditorInputResource resource) {
		return new UsageEvolutionComposite(composite, SWT.NONE, (UsageEvolutionAlternative)resource);
	}
	
}
