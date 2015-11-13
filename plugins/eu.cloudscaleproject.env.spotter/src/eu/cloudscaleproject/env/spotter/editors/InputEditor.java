package eu.cloudscaleproject.env.spotter.editors;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.spotter.alternatives.InputAlternative;
import eu.cloudscaleproject.env.spotter.editors.composite.InputAlternativeComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;


/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class InputEditor extends AlternativeEditor{
	
	@Inject
	@Optional
	public void setAlternative(MPart part, Composite parent, InputAlternative alternative){
		part.setLabel("Dynamic spotter input ["+ alternative.getName() +"]");
	}

	@Override
	public Composite createComposite(Composite composite, IEditorInputResource resource) {
		InputAlternative alternative = (InputAlternative)resource;
		return new InputAlternativeComposite(alternative.getProject(), composite, SWT.NONE, alternative);
	}
	
}
