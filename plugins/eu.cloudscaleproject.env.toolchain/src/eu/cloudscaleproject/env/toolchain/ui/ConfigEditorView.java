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
	private Job currentJob;
	private ValidationWidget validationComposite;
	private ResultWiget resultsComposite;
	private Composite progressComposite;
	
	private final IValidationStatusListener propertyChangeListener = new IValidationStatusListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			if (!isRunning()) updateControls();
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

		if (alternative == null) return; // For WindowBuilder

		initFooterContent();
		initListeners();
	}
	
	private void initFooterContent()
	{
		Composite composite = new Composite(getFooter(), SWT.None);
		composite.setLayout(new GridLayout(2, false));

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

	private boolean isRunning ()
	{
		return currentJob != null && currentJob.getResult() == null;
	}

	private void updateControls()
	{
		Display.getDefault().syncExec(new Runnable()
		{
			@Override
			public void run()
			{
				if (isRunning())
				{
					btnRun.setText("Stop");
					((StackLayout) stackedContainer.getLayout()).topControl = progressComposite;
					stackedContainer.layout();
					setEnabledRecursive(getContainer(), false);
				} else
				{
					if (currentJob != null && currentJob.getResult() != null)
					{
						resultsComposite.setStatus(currentJob.getResult());
						((StackLayout) stackedContainer.getLayout()).topControl = resultsComposite;

						ValidationDiagramService.showStatus(alternative.getProject(), alternative.getLastResult());

					} else
					{
						((StackLayout) stackedContainer.getLayout()).topControl = validationComposite;
					}

					btnRun.setText("Run");
					stackedContainer.layout();
					stackedContainer.redraw();

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
					//progressBarDeterminate.setSelection(work);
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
