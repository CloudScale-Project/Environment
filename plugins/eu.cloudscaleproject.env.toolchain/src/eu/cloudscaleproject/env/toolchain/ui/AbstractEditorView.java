package eu.cloudscaleproject.env.toolchain.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;

public class AbstractEditorView extends Composite
{

	private Composite container;
	private Composite footer;
	private Composite header;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AbstractEditorView(Composite parent, int style, IEditorInput input)
	{
		super(parent, style);
		
		setLayout(new GridLayout(1, false));
		
		header = new Composite(this, SWT.NONE);
		header.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_header = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_header.heightHint = 40;
		header.setLayoutData(gd_header);
		
		container = new Composite(this, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		footer = new Composite(this, SWT.BORDER);
		footer.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_footer = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_footer.heightHint = 48;
		footer.setLayoutData(gd_footer);
	}
	
	protected Composite getHeader()
	{
		return header;
	}

	protected Composite getContainer()
	{
		return container;
	}
	
	protected Composite getFooter()
	{
		return footer;
	}
}
