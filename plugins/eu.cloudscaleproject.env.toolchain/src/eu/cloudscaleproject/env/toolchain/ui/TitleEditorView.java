package eu.cloudscaleproject.env.toolchain.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.ui.widgets.TitleWidget;

public class TitleEditorView extends AbstractEditorView
{
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TitleEditorView(Composite parent, int style, IEditorInput input)
	{
		super(parent, style, input);
		
		setLayout(new GridLayout(1, false));
		
		new TitleWidget(getHeader(), SWT.NONE, input);
		
		getFooter().dispose();
	}
}
