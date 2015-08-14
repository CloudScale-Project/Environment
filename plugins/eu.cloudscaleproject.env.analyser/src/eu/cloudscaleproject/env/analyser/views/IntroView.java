 
package eu.cloudscaleproject.env.analyser.views;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.analyser.editors.IntroComposite;

public class IntroView {
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		IntroComposite introView = new IntroComposite(parent, SWT.NONE);
		introView.layout();
		introView.redraw();
	}
	
}