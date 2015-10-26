package eu.cloudscaleproject.env.help;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class ProjectIntro extends Composite
{

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProjectIntro(Composite parent, int style)
	{
		super(parent, style);
		setLayout(new GridLayout(5, false));

		{
		Composite composite = new Composite(this, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_composite.widthHint = 11;
		composite.setLayoutData(gd_composite);
		}
		{
		Composite composite = new Composite(this, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_composite.widthHint = 100;
		composite.setLayoutData(gd_composite);
		composite.setBackground(SWTResourceManager.getColor(51, 204, 0));
		}
		{
		Composite composite = new Composite(this, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_composite.widthHint = 11;
		composite.setLayoutData(gd_composite);
		}
		{
		Composite composite = new Composite(this, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_composite.widthHint = 100;
		composite.setLayoutData(gd_composite);
		composite.setBackground(SWTResourceManager.getColor(51, 204, 0));
		}
		{
		Composite composite = new Composite(this, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_composite.widthHint = 11;
		composite.setLayoutData(gd_composite);
		}
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		
		for (int i = 0; i < 15; i++) {
			//createComposite();
		}
	}
	
	private void createComposite ()
	{
		Composite composite = new Composite(this, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_composite.widthHint = 33;
		composite.setLayoutData(gd_composite);
		composite.setBackground(new Color(getDisplay(), (int)(Math.random()*256), (int)(Math.random()*256), getOrientation()));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
