package eu.cloudscaleproject.env.toolchain.ui;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusListener;
import eu.cloudscaleproject.env.toolchain.Activator;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;

public abstract class RunComposite extends Composite
{
	private IConfigAlternative alternative;

	private Composite container;
	private TitledGradientComposite titlebar;
	private ProgressBar progressBarIndeterminate;
	private Composite progressbarContainer;
	private Button btnRun;
	private Job currentJob;
	private ProgressBar progressBarDeterminate;
	private Composite progressBarNone;
	private Label lblStatus;

	private IValidationStatusListener validationListener;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public RunComposite(Composite parent, int style, IConfigAlternative alternative)
	{
		super(parent, style);

		setLayout(new GridLayout(1, false));
		this.alternative = alternative;

		initValidationStatusListener();

		// TODO: enable renaming
		titlebar = new TitledGradientComposite(this, SWT.NONE);
		updteTitle();

		container = new Composite(this, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite = new Composite(this, SWT.BORDER);
		composite.setLayout(new GridLayout(2, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 48;
		composite.setLayoutData(gd_composite);

		progressbarContainer = new Composite(composite, SWT.NONE);
		progressbarContainer.setLayout(new StackLayout());
		progressbarContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		progressBarNone = new Composite(progressbarContainer, SWT.NONE);
		progressBarIndeterminate = new ProgressBar(progressbarContainer, SWT.HORIZONTAL | SWT.INDETERMINATE);
		progressBarDeterminate = new ProgressBar(progressbarContainer, SWT.NONE);
		((StackLayout) progressbarContainer.getLayout()).topControl = progressBarNone;

		lblStatus = new Label(progressBarNone, SWT.NONE);
		lblStatus.setBounds(10, 10, 379, 15);
		lblStatus.setText("");

		btnRun = new Button(composite, SWT.NONE);
		btnRun.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if (currentJob != null && currentJob.getResult() == null)
				{
					stop();
				} else
				{
					run();
				}

			}
		});
		GridData gd_btnNewButton = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_btnNewButton.widthHint = 80;
		btnRun.setLayoutData(gd_btnNewButton);
		btnRun.setText("Run");
	}

	private void updteTitle()
	{
		String title = this.alternative.getName();
		if (this.alternative.getType() != null)
		{
			title = String.format("%s [%s]",this.alternative.getName(), this.alternative.getType().toLowerCase()) ;
		}

		titlebar.setTitle(title);
	}

	private void initValidationStatusListener()
	{
		this.validationListener = new IValidationStatusListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				if (evt.getPropertyName().equals(IValidationStatus.PROP_VALID))
				{
					// TODO
					
				}
			}
		};
		
		this.addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
				alternative.getSelfStatus().removeListener(validationListener);
			}
		});

		alternative.getSelfStatus().addListener(validationListener);
	}

	public Composite getContainer()
	{
		return container;
	}

	private void run()
	{
		if (!alternative.getSelfStatus().isDone())
		{
			showValidationWarnings();
			return;
		}

		this.currentJob = new Job("CloudScale Run [" + alternative.getName() + "]")
		{
			private RunProgressMonitor internalMonitor;

			@Override
			protected IStatus run(IProgressMonitor monitor)
			{
				internalMonitor = new RunProgressMonitor();
				return alternative.run(internalMonitor);
			}

			@Override
			protected void canceling()
			{
				internalMonitor.setCanceled(true);
				super.canceling();
			}
		};

		currentJob.addJobChangeListener(new JobChangeAdapter()
		{
			@Override
			public void done(IJobChangeEvent event)
			{
				updateControls();
			}
		});

		currentJob.schedule();
		updateControls();
	}

	private void stop()
	{
		this.currentJob.cancel();
	}

	private void updateControls()
	{
		Display.getDefault().syncExec(new Runnable()
		{
			@Override
			public void run()
			{
				if (currentJob != null && currentJob.getResult() == null)
				{
					btnRun.setText("Stop");
					((StackLayout) progressbarContainer.getLayout()).topControl = progressBarIndeterminate;
					progressbarContainer.layout();
					setEnabledRecursive(getContainer(), false);
				} else
				{
					if (currentJob.getResult() != null)
					{
						if (currentJob.getResult().isOK())
							lblStatus.setText("Status of last run : SUCCESSFUL");
						else
							lblStatus.setText("Status of last run : FAILED : " + currentJob.getResult().getMessage());
					} else
					{
						lblStatus.setText("");
					}

					btnRun.setText("Run");
					((StackLayout) progressbarContainer.getLayout()).topControl = progressBarNone;
					progressbarContainer.layout();

					setEnabledRecursive(getContainer(), true);
				}

			}
		});
	}

	private void showValidationWarnings()
	{
		int numOfwarnings = alternative.getSelfStatus().getWarningIDs().size();
		
		LinkedList<Status> statuses = new LinkedList<>();
		for (String id : alternative.getSelfStatus().getWarningIDs())
		{
			String warning = alternative.getSelfStatus().getWarningMessage(id);
			Status s = new Status (IStatus.ERROR, Activator.PLUGIN_ID, String.format("> %s", warning));
			statuses.add(s);
		}
		
		String msg = String.format("Unable to run alternative '%s'.", alternative.getName());

		String reason = String.format("Alternative validation failed. There are %s validation errors.", numOfwarnings);
		if (numOfwarnings == 1)
		{
			reason = String.format("Alternative validation failed. There is %s validation error.", numOfwarnings);
		}
		else if (numOfwarnings == 0)
		{
			reason = String.format("Alternative validation failed. Validation of input alternative failed.", numOfwarnings);
			statuses.add( new Status (IStatus.ERROR, Activator.PLUGIN_ID, String.format("> %s", "Input alternative has validation errors.")));
		}

		MultiStatus ms = new MultiStatus(Activator.PLUGIN_ID, IStatus.ERROR, statuses.toArray(new Status[0]), reason, null);
		ErrorDialog.openError(Display.getDefault().getActiveShell(), "Run failed", msg, ms);
	}

	private HashMap<Control, Boolean> mapOriginalEnableSettings = new HashMap<>();

	private void setEnabledRecursive(Control ctrl, boolean enabled)
	{
		if (ctrl instanceof Composite)
		{
			Composite comp = (Composite) ctrl;
			for (Control c : comp.getChildren())
				setEnabledRecursive(c, enabled);
		} else
		{
			if (enabled == ctrl.getEnabled())
			{
				mapOriginalEnableSettings.put(ctrl, enabled);
			} else
			{
				if (mapOriginalEnableSettings.containsKey(ctrl))
				{
					ctrl.setEnabled(mapOriginalEnableSettings.get(ctrl));
				} else
				{
					ctrl.setEnabled(enabled);
				}

			}
		}
	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

	private class RunProgressMonitor implements IProgressMonitor
	{
		private boolean isCanceled = false;

		@Override
		public void worked(final int work)
		{
			Display.getDefault().syncExec(new Runnable()
			{
				@Override
				public void run()
				{
					progressBarDeterminate.setSelection(work);
				}
			});
		}

		@Override
		public void subTask(String name)
		{
		}

		@Override
		public void setTaskName(String name)
		{
		}

		@Override
		public void setCanceled(boolean value)
		{
			isCanceled = value;
		}

		@Override
		public boolean isCanceled()
		{
			return isCanceled;
		}

		@Override
		public void internalWorked(double work)
		{
		}

		@Override
		public void done()
		{
		}

		@Override
		public void beginTask(final String name, final int totalWork)
		{
			Display.getDefault().syncExec(new Runnable()
			{
				@Override
				public void run()
				{
					if (totalWork > 0)
					{
						progressBarDeterminate.setMinimum(0);
						progressBarDeterminate.setSelection(0);
						progressBarDeterminate.setMaximum(totalWork);

						((StackLayout) progressbarContainer.getLayout()).topControl = progressBarDeterminate;
						progressbarContainer.layout();
					}
				}
			});
		}
	}
}
