package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.cloudscaleproject.env.spotter.editors.ServerClientComposite.ISpotterServer;

public class ExternalServerComposite extends Composite implements ISpotterServer
{
	private Text txtHostname;
	private Text txtPort;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ExternalServerComposite(Composite parent, int style)
	{
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		Label lblHostname = new Label(this, SWT.NONE);
		lblHostname.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblHostname.setText("Hostname");
		
		txtHostname = new Text(this, SWT.BORDER);
		GridData gd_txtHostname = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_txtHostname.widthHint = 300;
		txtHostname.setLayoutData(gd_txtHostname);
		
		Label lblPort = new Label(this, SWT.NONE);
		lblPort.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPort.setText("Port");
		
		txtPort = new Text(this, SWT.BORDER);
		GridData gd_txtPort = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_txtPort.widthHint = 60;
		txtPort.setLayoutData(gd_txtPort);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		composite.setLayout(new GridLayout(1, false));
		
		Button btnTestConnection = new Button(composite, SWT.NONE);
		btnTestConnection.setText("Test connection");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

	public String getHostname()
	{
		return txtHostname.getText();
	}
	public String getPort()
	{
		return txtPort.getText();
	}

}
