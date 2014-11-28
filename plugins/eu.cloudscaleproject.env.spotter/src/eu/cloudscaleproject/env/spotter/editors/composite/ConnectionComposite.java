package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.spotter.eclipse.ui.ServiceClientWrapper;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class ConnectionComposite extends Composite{
	
	public static final String PROP_SERVER_HOSTNAME = "dynamicSpotter.server.hostname";
	public static final String PROP_SERVER_PORT = "dynamicSpotter.server.port";

	public ConnectionComposite(final IProject project, Composite parent, int style) {
		super(parent, style);
		
		//connection tab
		GridLayout glc = new GridLayout(2, false);
		setLayout(glc);
		
		final Text textHostname;
		final Text textPort;
		final Label lblStatus;
		
		String hostname = ExplorerProjectPaths.getProjectProperty(project, PROP_SERVER_HOSTNAME);
		String port = ExplorerProjectPaths.getProjectProperty(project, PROP_SERVER_PORT);
		
		//set default values
		hostname = hostname != null ? hostname : "localhost";
		port = port != null ? port : "8080";
		
		{
			Label lblHostname = new Label(this, SWT.NONE);
			lblHostname.setLayoutData(new GridData());						
			lblHostname.setText("Hostname:");
			
			textHostname = new Text(this, SWT.NONE);
			GridData gd = new GridData();
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			textHostname.setLayoutData(gd);
			
			textHostname.setText(hostname != null ? hostname : "");
		}
		
		{
			Label lblPort = new Label(this, SWT.NONE);
			lblPort.setLayoutData(new GridData());
			lblPort.setText("Port:");
			
			textPort = new Text(this, SWT.NONE);
			GridData gd = new GridData();
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			textPort.setLayoutData(gd);
			
			textPort.setText(port != null ? port : "");
		}
		
		final ServiceClientWrapper client = org.spotter.eclipse.ui.Activator.getDefault()
				.getClient(project.getName());

		{	
			Button btnConnect = new Button(this, SWT.NONE);
			
			//btnConnect.setBackground(getSidebarBackgroundColor());
			//btnConnect.setForeground(getSidebarForegroundColor());
			
			GridData gd = new GridData();
			gd.horizontalAlignment = SWT.LEFT;
			btnConnect.setLayoutData(gd);
			
			btnConnect.setText("Connect to server");
			
			lblStatus = new Label(this, SWT.NONE);
			lblStatus.setLayoutData(new GridData());
			lblStatus.setText("Connection not established.");
			
			btnConnect.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					client.updateUrl(textHostname.getText(), textPort.getText());

					Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
					Cursor waitCursor = Display.getDefault().getSystemCursor(SWT.CURSOR_WAIT);
					Cursor oldCursor = shell.getCursor();
					shell.setCursor(waitCursor);
					boolean connectionOk = client.testConnection(false);
					shell.setCursor(oldCursor);
					
					if(connectionOk){
						ExplorerProjectPaths.setProjectProperty(project, PROP_SERVER_HOSTNAME, textHostname.getText());
						ExplorerProjectPaths.setProjectProperty(project, PROP_SERVER_PORT, textPort.getText());
						
						lblStatus.setText("Connection established.");
					}
					else{
						lblStatus.setText("Connection not established.");
					}
					
					lblStatus.redraw();
					lblStatus.pack(true);
				};
			});
		}
		
		if(hostname != null && port != null){
			client.updateUrl(hostname, port);
		}
		boolean connected = client.testConnection(false);
		if(connected){
			lblStatus.setText("Connection established.");
		}
	}

}
