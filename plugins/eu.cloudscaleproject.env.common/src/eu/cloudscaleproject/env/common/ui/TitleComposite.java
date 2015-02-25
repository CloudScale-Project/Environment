package eu.cloudscaleproject.env.common.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;

public class TitleComposite extends Composite
{

	private Label lblTitle;
	private Composite container;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TitleComposite(Composite parent, int style)
	{
		super(parent, style);
		
		setLayout(new GridLayout(1, false));
		
		GradientComposite titleComposite = new GradientComposite(this, SWT.NONE);
		titleComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		titleComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		titleComposite.setLayout(new GridLayout(1, false));
		
		titleComposite.setGradientDirection(false);
		titleComposite.setGradientColorStart(ColorResources.COLOR_CS_BLUE);
		titleComposite.setGradientColorEnd(ColorResources.COLOR_CS_BLUE_LIGHT);

		lblTitle = new Label(titleComposite, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblTitle.setFont(SWTResourceManager.getFont("Sans", 14, SWT.NORMAL));
		lblTitle.setForeground(ColorResources.COLOR_CS_BLUE_DARK);
		lblTitle.setText("<Title>");
		
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
