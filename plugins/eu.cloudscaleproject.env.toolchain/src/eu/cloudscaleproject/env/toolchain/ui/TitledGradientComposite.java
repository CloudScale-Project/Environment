package eu.cloudscaleproject.env.toolchain.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.common.ui.GradientComposite;
import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;

public class TitledGradientComposite extends GradientComposite
{

	private Label lblTitle;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TitledGradientComposite(Composite parent, int style)
	{
		super(parent, style);

		this.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		this.setLayout(new GridLayout(1, false));
		
		this.setGradientDirection(false);
		this.setGradientColorStart(ColorResources.COLOR_CS_BLUE);
		this.setGradientColorEnd(ColorResources.COLOR_CS_BLUE_LIGHT);

		lblTitle = new Label(this, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblTitle.setFont(SWTResourceManager.getFont("Sans", 14, SWT.NORMAL));
		lblTitle.setForeground(ColorResources.COLOR_CS_BLUE_DARK);
		lblTitle.setText("<Title>");

	}
	
	public void setTitle(String title)
	{
		lblTitle.setText(title);
	}
	
	public String getTitle ()
	{
		return lblTitle.getText();
	}
	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

}
