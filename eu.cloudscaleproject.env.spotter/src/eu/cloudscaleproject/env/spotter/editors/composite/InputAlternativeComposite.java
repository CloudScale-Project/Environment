package eu.cloudscaleproject.env.spotter.editors.composite;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.editors.InstrumentationEditor;
import org.spotter.eclipse.ui.editors.MeasurementEditor;
import org.spotter.eclipse.ui.editors.factory.ElementFactory;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class InputAlternativeComposite extends Composite{

	private static final String LABEL_CONN_OK = "Connection OK!";
	private static final String LABEL_CONN_ERR = "No connection could be established!";
	
	private final EditorInputFolder editorInput;
	private Text textHostname;
	private Text textPort;
	private Text textName;
	private Label lblCurrentStatus;
		
	public InputAlternativeComposite(Composite parent, int style, final EditorInputFolder editorInput) {
		super(parent, style);
		this.editorInput = editorInput;

		setLayout(new GridLayout(2, false));
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name:");
		
		textName = new Text(this, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				InputAlternativeComposite.this.editorInput.setName(textName.getText());
				InputAlternativeComposite.this.editorInput.save();
			}
		});
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		Label lblHostname = new Label(this, SWT.NONE);
		lblHostname.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblHostname.setText("Hostname:");
		
		textHostname = new Text(this, SWT.BORDER);
		GridData gd_textHostname = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textHostname.widthHint = 315;
		textHostname.setLayoutData(gd_textHostname);
		textHostname.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				InputAlternativeComposite.this.editorInput.setProperty("hostname", textHostname.getText());
				InputAlternativeComposite.this.editorInput.save();
			}
		});
		
		Label lblPort = new Label(this, SWT.NONE);
		lblPort.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPort.setText("Port:");
		
		textPort = new Text(this, SWT.BORDER);
		textPort.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textPort.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				InputAlternativeComposite.this.editorInput.setProperty("port", textPort.getText());
				InputAlternativeComposite.this.editorInput.save();
			}
		});
		
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Label lblStatus = new Label(this, SWT.NONE);
		lblStatus.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStatus.setText("Status:");
		
		lblCurrentStatus = new Label(this, SWT.NONE);
		lblCurrentStatus.setText(LABEL_CONN_ERR);
		new Label(this, SWT.NONE);
		
		Button btnConnect = new Button(this, SWT.NONE);
		btnConnect.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnConnect.setText("Test connection");
		btnConnect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				testConnection(editorInput);
			}
		});
		
		Label lblConfigurationEditors = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblConfigurationEditors.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		lblConfigurationEditors.setText("Configuration Editors:");
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_composite.widthHint = 57;
		composite.setLayoutData(gd_composite);
		
		Button btnIsa = new Button(composite, SWT.NONE);
		btnIsa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnIsa.setBounds(0, 0, 64, 15);
		btnIsa.setText("Open Instrumentation Satellite Adapter Editor...");
		btnIsa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IFile file = InputAlternativeComposite.this.editorInput.getResource().getFile("mEnv.xml");
				InstrumentationEditor.openInstance(
						(IEditorInput) ElementFactory.createEditorInput(InstrumentationEditor.ID, file), 
						InstrumentationEditor.ID);
			}
		});
		
		Button btnMsa = new Button(composite, SWT.NONE);
		btnMsa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnMsa.setText("Open Measurement Satellite Adapter Editor...");
		btnMsa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IFile file = InputAlternativeComposite.this.editorInput.getResource().getFile("mEnv.xml");
				MeasurementEditor.openInstance(
						(IEditorInput) ElementFactory.createEditorInput(MeasurementEditor.ID, file), 
						MeasurementEditor.ID);
			}
		});
		
		load();
	}
	
	public void load(){
		String hostname = editorInput.getProperty("hostname");
		String port = editorInput.getProperty("port");
		String name = editorInput.getName();
		
		try{
			Integer.parseInt(port);
		}
		catch(NumberFormatException e){
			port = "";
		}
		
		textName.setText(name != null ? name : "");
		textHostname.setText(hostname != null ? hostname : "");
		textPort.setText(port != null ? port : "");
	}
	
	/**
	 * Tests the connection.
	 * 
	 * @return <code>true</code> if the connection was successful,
	 *         <code>false</code> otherwise
	 */
	public boolean testConnection(eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource ei) {
		Activator activator = Activator.getDefault();
		ServiceClientWrapper client = activator.getClient(ei.getResource().getName());
		
		client.updateUrl(textHostname.getText(), textPort.getText());

		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		Cursor waitCursor = Display.getDefault().getSystemCursor(SWT.CURSOR_WAIT);
		Cursor oldCursor = shell.getCursor();
		shell.setCursor(waitCursor);
		boolean connectionOk = client.testConnection(false);
		shell.setCursor(oldCursor);

		lblCurrentStatus.setText(connectionOk ? LABEL_CONN_OK : LABEL_CONN_ERR);
		lblCurrentStatus.pack();
		
		return connectionOk;
	}
	
	@Override
	public void update() {
		editorInput.load();
		load();
		super.update();
	}
}
