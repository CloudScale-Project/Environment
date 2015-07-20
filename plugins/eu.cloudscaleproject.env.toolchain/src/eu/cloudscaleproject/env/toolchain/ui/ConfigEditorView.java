package eu.cloudscaleproject.env.toolchain.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
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
import org.eclipse.swt.widgets.ProgressBar;

import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.ui.widgets.ResultWiget;
import eu.cloudscaleproject.env.toolchain.ui.widgets.TitleWidget;
import eu.cloudscaleproject.env.toolchain.ui.widgets.ValidationWidget;

public abstract class ConfigEditorView extends AbstractEditorView
{
	private IConfigAlternative alternative;

	private Composite stackedContainer;
	private Button btnRun;
	private ValidationWidget validationComposite;
	private ResultWiget resultsComposite;
	private Composite progressComposite;
	private AlternativeRunJob lastJob;

	private final PropertyChangeListener propertyChangeListener = new PropertyChangeListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			if (evt.getNewValue() == alternative && !isRunning())
			{
				updateControls();
			}
		}
	};

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ConfigEditorView(Composite parent, int style, IConfigAlternative alternative)
	{
		super(parent, style, alternative);

		this.alternative = alternative;

		new TitleWidget(getHeader(), SWT.NONE, alternative);

		if (alternative == null)
			return; // For WindowBuilder

		initFooterContent();
		initListeners();
	}

	private void initFooterContent()
	{
		Composite composite = new Composite(getFooter(), SWT.NONE);
		GridLayout gl = new GridLayout(2, false);
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		composite.setLayout(gl);

		stackedContainer = new Composite(composite, SWT.NONE);
		stackedContainer.setLayout(new StackLayout());
		stackedContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		resultsComposite = new ResultWiget(stackedContainer, SWT.NONE, alternative);
		validationComposite = new ValidationWidget(stackedContainer, SWT.NONE, alternative);
		progressComposite = new Composite(stackedContainer, SWT.NONE);

		progressComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		new ProgressBar(progressComposite, SWT.HORIZONTAL | SWT.INDETERMINATE);

		((StackLayout) stackedContainer.getLayout()).topControl = validationComposite;
		stackedContainer.layout();

		btnRun = new Button(composite, SWT.NONE);
		btnRun.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if (isRunning())
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

		updateControls();
	}

	private void initListeners()
	{

		this.addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
				StatusManager.getInstance().removePropertyChangeListener(StatusManager.PROP_VALIDATION_COMPLETED, propertyChangeListener);
			}
		});

		StatusManager.getInstance().addPropertyChangeListener(StatusManager.PROP_VALIDATION_COMPLETED, propertyChangeListener);
	}

	private void run()
	{
		AlternativeRunJob job = new AlternativeRunJob();

		job.addJobChangeListener(new JobChangeAdapter()
		{
			@Override
			public void done(IJobChangeEvent event)
			{
				updateControls();
				setEnabledRecursive(getContainer(), true);
			}
		});

		job.schedule();
		lastJob = job;

		updateControls();
		setEnabledRecursive(getContainer(), false);
	}

	private void stop()
	{
		lastJob.cancel();
	}

	private boolean isRunning()
	{
		return lastJob != null && lastJob.isRunning();
	}

	private void updateControls()
	{
		Display.getDefault().asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				if (stackedContainer == null || stackedContainer.isDisposed())
				{
					return;
				}

				if (isRunning())
				{
					btnRun.setText("Stop");
					((StackLayout) stackedContainer.getLayout()).topControl = progressComposite;
					stackedContainer.layout();
				} else
				{
					// WORKAROUND : if before 3s after job is finished, than show result
					if (lastJob != null && System.currentTimeMillis() < 3000+lastJob.getEndTimestamp())
					{
						resultsComposite.setStatus(lastJob.getResult());
						((StackLayout) stackedContainer.getLayout()).topControl = resultsComposite;

						//TODO: If last results is null nothing will happen.
						//		It should clear the status from the Workbench, but the ID is unknown...
						ValidationDiagramService.showStatus(alternative.getProject(), alternative.getLastResult());

					} else
					{
						((StackLayout) stackedContainer.getLayout()).topControl = validationComposite;
					}

					btnRun.setText("Run");
					stackedContainer.layout();
					stackedContainer.redraw();

				}
			}
		});
	}

	private HashMap<Control, Boolean> mapOriginalEnableSettings = new HashMap<>();

	private void setEnabledRecursive(final Control ctrl, final boolean enabled)
	{
		Display.getDefault().syncExec(new Runnable()
		{

			@Override
			public void run()
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

		});
	}


	private class AlternativeRunJob extends Job
	{
		private long startTimestamp;
		private long endTimestamp;

		public AlternativeRunJob()
		{
			super("CloudScale Run [" + alternative.getName() + "]");

		}

		private RunProgressMonitor internalMonitor;

		@Override
		protected IStatus run(IProgressMonitor monitor)
		{
			try
			{
				startTimestamp = System.currentTimeMillis();
				internalMonitor = new RunProgressMonitor();
				return alternative.run(internalMonitor);
			} finally
			{
				endTimestamp = System.currentTimeMillis();
			}
		}

		@Override
		protected void canceling()
		{
			internalMonitor.setCanceled(true);
			super.canceling();
		}
		
		public boolean isRunning ()
		{
			return startTimestamp>0 && endTimestamp==0;
		}

		@SuppressWarnings("unused")
		public long getStartTimestamp()
		{
			return startTimestamp;
		}

		public long getEndTimestamp()
		{
			return endTimestamp;
		}
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
					// not acctually used
					// progressBarDeterminate.setSelection(work);
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
		}
	}
}
