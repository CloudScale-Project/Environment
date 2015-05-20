package eu.cloudscaleproject.env.toolchain.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.widgets.TitleWidget;
import eu.cloudscaleproject.env.toolchain.ui.widgets.ValidationWidget;

public class InputEditorView extends AbstractEditorView
{
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InputEditorView(Composite parent, int style, IEditorInputResource input)
	{
		super(parent, style, input);
		
		new TitleWidget(getHeader(), SWT.NONE, input);
		
		new ValidationWidget(getFooter(), style, input);
	}
}
