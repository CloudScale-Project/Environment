package eu.cloudscaleproject.env.spotter.editors.composite;

import java.io.IOException;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.common.ui.resources.SWTResourceManager;
import eu.cloudscaleproject.env.spotter.Activator;
import eu.cloudscaleproject.env.spotter.BuiltinServerController;
import eu.cloudscaleproject.env.spotter.Util;
import eu.cloudscaleproject.env.spotter.editors.composite.ServerClientComposite.ISpotterServer;

public class BuiltinServerComposite extends Composite implements ISpotterServer
{

	private IProject project;
	private StyledText txtServerLog;
	private Button btnConnect;
	private Composite composite;
	private Label lblStatus;
	private Label lblServerLog;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public BuiltinServerComposite(IProject project, Composite parent, int style)
	{
		super(parent, style);

		this.project = project;
		setLayout(new GridLayout(2, false));
		
		composite = new Composite(this, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);
		gd_composite.heightHint = 48;
		composite.setLayoutData(gd_composite);
		composite.setLayout(new GridLayout(2, false));
		
				btnConnect = new Button(composite, SWT.NONE);
				GridData gd_btnConnect = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_btnConnect.heightHint = 36;
				btnConnect.setLayoutData(gd_btnConnect);
				
				lblStatus = new Label(composite, SWT.NONE);
				lblStatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				lblStatus.setText("New Label");
				btnConnect.addSelectionListener(new SelectionAdapter()
				{
					@Override
					public void widgetSelected(SelectionEvent e)
					{
						toggleServerState();
					}
				});
		
		lblServerLog = new Label(this, SWT.NONE);
		GridData gd_lblServerLog = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_lblServerLog.verticalIndent = 10;
		lblServerLog.setLayoutData(gd_lblServerLog);
		lblServerLog.setFont(SWTResourceManager.getFont("Sans", 12, SWT.NORMAL));
		lblServerLog.setText("--- Server Log ---");
		
		Composite grpServerLog = new Composite(this, SWT.NONE);
		grpServerLog.setLayout(new FillLayout(SWT.HORIZONTAL));
		grpServerLog.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		txtServerLog = new StyledText(grpServerLog, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		txtServerLog.setBottomMargin(8);
		txtServerLog.setRightMargin(8);
		txtServerLog.setTopMargin(8);
		txtServerLog.setLeftMargin(8);
		txtServerLog.append("");

		if (project == null) return;
		updateState();
		initLog();
	}
	
	public String getHostname()
	{
		return "localhost";
		
	}
	public String getPort()
	{
		return ""+BuiltinServerController.getInstance(project).getServerPort();
		
	}
	public String getServerUrl ()
	{
		return "localhost:"+BuiltinServerController.getInstance(project).getServerPort();
	}

	private void initLog()
	{
		final Document doc = BuiltinServerController.getInstance(this.project).getDocument();
		try
		{
			String existingLog = doc.getText(0, doc.getLength());
			txtServerLog.setText(existingLog);
		}
		catch (BadLocationException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		final DocumentListener documentListener = new DocumentListener()
		{

			@Override
			public void removeUpdate(DocumentEvent e) { }

			@Override
			public void changedUpdate(DocumentEvent e) { }

			@Override
			public void insertUpdate(final DocumentEvent e)
			{
				Display.getDefault().asyncExec(new Runnable()
				{

					@Override
					public void run()
					{
						String newLog;
						try
						{
							newLog = doc.getText(e.getOffset(), e.getLength());
							
							int start = txtServerLog.getCharCount()-1;
							txtServerLog.append(newLog);
							txtServerLog.setTopIndex(txtServerLog.getLineCount() - 1);

							if (newLog.contains(" WARN "))
							{
								StyleRange styleRange = new StyleRange();
								styleRange.start = start;
								styleRange.length = newLog.length();
								styleRange.foreground = ColorResources.COLOR_RED;
								txtServerLog.setStyleRange(styleRange);
							}
						}
						catch (BadLocationException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		};
		
		doc.addDocumentListener(documentListener);
		
		addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
				doc.removeDocumentListener(documentListener);
			}
		});
	}

	private void toggleServerState()
	{
		Job job = new Job("Start/Stop Spotter server")
		{

			@Override
			protected IStatus run(IProgressMonitor monitor)
			{
				try
				{
					if (!BuiltinServerController.getInstance(project).isServerRunning())
						BuiltinServerController.getInstance(project).startServer(Util.findFreePort());
					else
						BuiltinServerController.getInstance(project).stopServer();
				}
				catch (IOException e)
				{
					e.printStackTrace();
					if (BuiltinServerController.getInstance(project).isServerRunning())
						return new Status(Status.ERROR, Activator.PLUGIN_ID, "Failed to start server: " + e.getMessage());
					else
						return new Status(Status.ERROR, Activator.PLUGIN_ID, "Failed to stop server: " + e.getMessage());
				}

				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run()
					{
						updateState();
					}
				});

				return Status.OK_STATUS;
			}
		};

		job.schedule();
	}

	public void updateState()
	{
		BuiltinServerController bsc = BuiltinServerController.getInstance(this.project);
		boolean running = bsc.isServerRunning();

		String btnText = running ? "Stop server" : "Start server";
		this.btnConnect.setText(btnText);
		
		
		String statusText = running ? 
				"<Server is running on localhost:"+bsc.getServerPort()+">" : 
				"<Server is not running>";
		lblStatus.setText(statusText);
	}
}
