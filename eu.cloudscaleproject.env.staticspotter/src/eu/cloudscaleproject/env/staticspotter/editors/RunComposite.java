package eu.cloudscaleproject.env.staticspotter.editors;

import org.eclipse.swt.widgets.Composite;

public class RunComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RunComposite(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
