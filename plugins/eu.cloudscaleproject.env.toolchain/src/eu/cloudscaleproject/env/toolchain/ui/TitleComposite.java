package eu.cloudscaleproject.env.toolchain.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;

public class TitleComposite extends Composite
{

	private Composite container;
	private TitledGradientComposite titlebar;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TitleComposite(Composite parent, int style, IEditorInput input)
	{
		super(parent, style);
		
		setLayout(new GridLayout(1, false));
		
		titlebar = new TitledGradientComposite(this, SWT.NONE, input);
		
		container = new Composite(this, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}
	
	public Composite getContainer()
	{
		return container;
	}
	
	public String getTitle ()
	{
		return titlebar.getTitle();
	}
		

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
