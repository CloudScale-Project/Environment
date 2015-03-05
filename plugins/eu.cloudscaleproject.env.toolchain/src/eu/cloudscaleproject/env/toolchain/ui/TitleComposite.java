package eu.cloudscaleproject.env.toolchain.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class TitleComposite extends Composite
{

	private Composite container;
	private TitledGradientComposite titlebar;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TitleComposite(Composite parent, int style)
	{
		super(parent, style);
		
		setLayout(new GridLayout(1, false));
		
		titlebar = new TitledGradientComposite(this, SWT.NONE);
		
		container = new Composite(this, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}
	
	public Composite getContainer()
	{
		return container;
	}
	
	public void setTitle(String title)
	{
		titlebar.setTitle(title);
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
