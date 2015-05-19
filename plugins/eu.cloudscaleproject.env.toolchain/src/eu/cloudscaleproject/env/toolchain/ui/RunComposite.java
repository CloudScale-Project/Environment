package eu.cloudscaleproject.env.toolchain.ui;

import java.beans.PropertyChangeEvent;
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

import eu.cloudscaleproject.env.common.notification.IValidationStatusListener;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;

public abstract class RunComposite extends Composite
{
	private IConfigAlternative alternative;

	private Composite container;
	private ProgressBar progressBarIndeterminate;
	private Composite footerContainer;
	private Button btnRun;
	private Job currentJob;
	private ProgressBar progressBarDeterminate;
	private FooterValidationComposite validationComposite;
	private FooterResultComposite resultsComposite;
	
	private final IValidationStatusListener propertyChangeListener = new IValidationStatusListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			updateControls();
		}
	};

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

		initListeners();

		new TitledGradientComposite(this, SWT.NONE, alternative);

		container = new Composite(this, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite = new Composite(this, SWT.BORDER);
		composite.setLayout(new GridLayout(2, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 48;
		composite.setLayoutData(gd_composite);

		footerContainer = new Composite(composite, SWT.NONE);
		footerContainer.setLayout(new StackLayout());
		footerContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		resultsComposite = new FooterResultComposite(footerContainer, style, alternative);

		validationComposite = new FooterValidationComposite(footerContainer, style, alternative);

		progressBarIndeterminate = new ProgressBar(footerContainer, SWT.HORIZONTAL | SWT.INDETERMINATE);
		progressBarDeterminate = new ProgressBar(footerContainer, SWT.NONE);

		((StackLayout) footerContainer.getLayout()).topControl = validationComposite;

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
		
		updateControls();
	}

	private void initListeners()
	{
		this.addDisposeListener(new DisposeListener()
		{
			@Override
			public void widgetDisposed(DisposeEvent e)
			{
				alternative.getSelfStatus().removeListener(propertyChangeListener);
				alternative.removePropertyChangeListener(propertyChangeListener);
			}
		});

		alternative.addPropertyChangeListener(propertyChangeListener);
		alternative.getSelfStatus().addListener(propertyChangeListener);
	}

	public Composite getContainer()
	{
		return container;
	}

	private void run()
	{
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
				currentJob = null;
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
					((StackLayout) footerContainer.getLayout()).topControl = progressBarIndeterminate;
					footerContainer.layout();
					setEnabledRecursive(getContainer(), false);
				} else
				{
					if (currentJob != null && currentJob.getResult() != null)
					{
						resultsComposite.setStatus(currentJob.getResult());
						((StackLayout) footerContainer.getLayout()).topControl = resultsComposite;

					} else
					{
						((StackLayout) footerContainer.getLayout()).topControl = validationComposite;
					}

					btnRun.setText("Run");
					footerContainer.layout();
					footerContainer.redraw();

					setEnabledRecursive(getContainer(), true);
				}
			}
		});
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

						((StackLayout) footerContainer.getLayout()).topControl = progressBarDeterminate;
						footerContainer.layout();
					}
				}
			});
		}
	}
}
