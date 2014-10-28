package eu.cloudscaleproject.env.staticspotter.editors;

import org.eclipse.swt.widgets.Composite;

public class ResultsComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResultsComposite(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
