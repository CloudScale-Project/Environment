package eu.cloudscaleproject.env.extractor.editors;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.extractor.alternatives.ConfingAlternative;
import eu.cloudscaleproject.env.extractor.editors.composites.ConfigAlternativeComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ConfigEditor extends AlternativeEditor{

	@Inject
	@Optional
	public void setAlternative(MPart part, Composite parent, ConfingAlternative alternative){
		part.setLabel("Extractor config ["+ alternative.getName() +"]");
	}

	@Override
	public Composite createComposite(Composite composite, IEditorInputResource resource) {
		return new ConfigAlternativeComposite(composite, SWT.NONE, (ConfingAlternative)resource);
	}
}
