package eu.cloudscaleproject.env.spotter.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.common.ui.GradientComposite;
import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;
import eu.cloudscaleproject.env.spotter.SpotterClientController;
import eu.cloudscaleproject.env.spotter.editors.composite.BuiltinServerComposite;
import eu.cloudscaleproject.env.spotter.editors.composite.ExternalServerComposite;

public class ServerClientComposite extends Composite
{


	private IProject project;
	private Button rbBuiltinServer;
	private Button rbExternalServer;
	private Composite serverStackComposite;
	private Group grpBuiltinServer;
	private Group grpExternalServer;
	private BuiltinServerComposite builtinServerComposite;
	private ExternalServerComposite externalServerComposite;
	private Label lblStatus;
	private Button btnConnectClient;
	private GradientComposite composite_2;
	private GradientComposite composite_3;
	private Composite serverComposite;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ServerClientComposite(IProject project, Composite parent, int style)
	{
		super(parent, style);

		this.project = project;

		setLayout(new GridLayout(1, false));
		
		composite_2 = new GradientComposite(this, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		composite_2.setLayout(new GridLayout(1, false));
		
		composite_2.setGradientDirection(false);
		composite_2.setGradientColorStart(ColorResources.COLOR_CS_BLUE);
		composite_2.setGradientColorEnd(ColorResources.COLOR_CS_BLUE_LIGHT);
		
		Label lblSpotterClient = new Label(composite_2, SWT.NONE);
		lblSpotterClient.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblSpotterClient.setFont(SWTResourceManager.getFont("Sans", 14, SWT.NORMAL));
		lblSpotterClient.setForeground(ColorResources.COLOR_CS_BLUE_DARK);
		lblSpotterClient.setText("Client");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.heightHint = 80;
		composite_1.setLayoutData(gd_composite_1);
		
		btnConnectClient = new Button(composite_1, SWT.NONE);
		btnConnectClient.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				toggleClientConnection();
			}
		});
		btnConnectClient.setBounds(10, 10, 120, 27);
		btnConnectClient.setText("Connect client");
		
		lblStatus = new Label(composite_1, SWT.NONE);
		lblStatus.setText("Status");
		lblStatus.setBounds(20, 43, 470, 27);
		
		composite_3 = new GradientComposite(this, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		composite_3.setLayout(new GridLayout(1, false));

		composite_3.setGradientDirection(false);
		composite_3.setGradientColorStart(ColorResources.COLOR_CS_BLUE);
		composite_3.setGradientColorEnd(ColorResources.COLOR_CS_BLUE_LIGHT);
		
		Label lblSpotterServer = new Label(composite_3, SWT.NONE);
		lblSpotterServer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		lblSpotterServer.setFont(SWTResourceManager.getFont("Sans", 14, SWT.NORMAL));
		lblSpotterServer.setForeground(ColorResources.COLOR_CS_BLUE_DARK);
		lblSpotterServer.setText("Server");
		
		serverComposite = new Composite(this, SWT.NONE);
		serverComposite.setLayout(new GridLayout(2, false));
		serverComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		

		rbBuiltinServer = new Button(serverComposite, SWT.RADIO);
		GridData gd_rbBuiltinServer = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_rbBuiltinServer.heightHint = 32;
		rbBuiltinServer.setLayoutData(gd_rbBuiltinServer);
		rbBuiltinServer.setText("Built-in Spotter server");
		
		rbExternalServer = new Button(serverComposite, SWT.RADIO);
		GridData gd_rbExternalServer = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_rbExternalServer.heightHint = 32;
		rbExternalServer.setLayoutData(gd_rbExternalServer);
		rbExternalServer.setText("External Spotter server");

		SelectionListener selectionListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateServerComposite();
			}
		};
		rbBuiltinServer.addSelectionListener(selectionListener);
		rbExternalServer.addSelectionListener(selectionListener);
		
		serverStackComposite = new Composite(serverComposite, SWT.NONE);
		serverStackComposite.setLayout(new StackLayout());
		serverStackComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		this.grpBuiltinServer = new Group(serverStackComposite, SWT.NONE);
		this.grpBuiltinServer.setLayout(new FillLayout());
		builtinServerComposite = new BuiltinServerComposite(project, this.grpBuiltinServer, SWT.NONE);
		
		this.grpExternalServer = new Group(serverStackComposite, SWT.NONE);
		this.grpExternalServer.setLayout(new FillLayout());
		externalServerComposite = new ExternalServerComposite(this.grpExternalServer, SWT.NONE);
		
		rbBuiltinServer.setSelection(true);
		
		updateClient();
		updateServerComposite();
	}
	
	private void toggleClientConnection ()
	{
		Control topControl = ((StackLayout)serverStackComposite.getLayout()).topControl;
		final String hostname = topControl == grpBuiltinServer ? 
				builtinServerComposite.getHostname() : externalServerComposite.getHostname();
		
		final String port = topControl == grpBuiltinServer ? 
				builtinServerComposite.getPort() : externalServerComposite.getPort();

		
		Job job = new Job("Spotter client connection to server")
		{

			@Override
			protected IStatus run(IProgressMonitor monitor)
			{
				SpotterClientController clientController = SpotterClientController.getController(project);
				
				IStatus status;

				if (clientController.isConnected())
				{
					clientController.disconnect();
					status = Status.OK_STATUS;
				}
				else
				{
					boolean isConnected =clientController.connect(hostname, port);
					status = isConnected ? 
							Status.OK_STATUS : 
							Status.OK_STATUS;
							
								//new Status(Status.ERROR, Activator.PLUGIN_ID, "Unable to connect client");
				}
					
				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run()
					{
						updateClient();
					}
				});
				
				return status;
			}
		};
		
		job.schedule();
	}
	
	private ISpotterServer getSelectedSpotterServer()
	{
		Composite topControl = (Composite) ((StackLayout)serverStackComposite.getLayout()).topControl;
		
		ISpotterServer spotterServer = (ISpotterServer) topControl.getChildren()[0];
		
		return spotterServer;
	}

	private void updateClient ()
	{
		SpotterClientController clientController = SpotterClientController.getController(project);
		boolean connected = clientController.isConnected();
		
		if (connected)
		{
			ISpotterServer selectedSpotterServer = getSelectedSpotterServer();
			lblStatus.setText(String.format("Client is connected to %s:%s.",
							selectedSpotterServer.getHostname(), 
							selectedSpotterServer.getPort()));

			btnConnectClient.setText("Disconnect client");

			setEnabledRecursive(serverComposite, false);
		}
		else
		{
			setEnabledRecursive(serverComposite, true);
			lblStatus.setText("Client is not connected. Please configure server properties.");
			btnConnectClient.setText("Connect client");
		}
	}

	private void updateServerComposite ()
	{
		if (rbBuiltinServer.getSelection())
		{
			((StackLayout)serverStackComposite.getLayout()).topControl = this.grpBuiltinServer;
		}
		else
		{
			((StackLayout)serverStackComposite.getLayout()).topControl = this.grpExternalServer;
		}
				
		serverStackComposite.layout();
	}
	
	public static void setEnabledRecursive(final Composite composite, final boolean enabled)
	{
	    Control[] children = composite.getChildren();

	    for (int i = 0; i < children.length; i++)
	    {
	        if (children[i] instanceof Composite)
	        {
	            setEnabledRecursive((Composite) children[i], enabled);
	        }
	        else if (children[i] instanceof Button)
	        {
	            children[i].setEnabled(enabled);
	        }
	    }
	}

	public static interface ISpotterServer
	{
		String getHostname();
		String getPort();
	}
}
