package eu.cloudscaleproject.env.extractor.editors;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.extractor.alternatives.InputAlternative;
import eu.cloudscaleproject.env.extractor.editors.composites.InputAlternativeComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class InputEditor extends AlternativeEditor{

	@Inject
	@Optional
	public void setAlternative(MPart part, Composite parent, InputAlternative alternative){
				
		setAlternative(alternative);
		setControl(new InputAlternativeComposite(parent, SWT.NONE, alternative));
		
		part.setLabel("Extractor input ["+ alternative.getName() +"]");
	}
}
