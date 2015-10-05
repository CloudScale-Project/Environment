package eu.cloudscaleproject.env.overview.editors;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.overview.OverviewAlternative;
import eu.cloudscaleproject.env.overview.editors.composites.OverviewComposite;
import eu.cloudscaleproject.env.toolchain.editors.AlternativeEditor;

public class OverviewEditor extends AlternativeEditor{
	
	@Inject
	@Optional
	public void setAlternative(MPart part, Composite parent, OverviewAlternative alternative){
		
		setAlternative(alternative);
		setControl(new OverviewComposite(parent, SWT.NONE, alternative));
		
		part.setLabel("Overview ["+ alternative.getName() +"]");
	}
}
