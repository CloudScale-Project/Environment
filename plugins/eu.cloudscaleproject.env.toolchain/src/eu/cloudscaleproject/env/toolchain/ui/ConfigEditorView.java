package eu.cloudscaleproject.env.toolchain.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

import eu.cloudscaleproject.env.common.IconSetResources;
import eu.cloudscaleproject.env.common.IconSetResources.COLOR;
import eu.cloudscaleproject.env.common.IconSetResources.SIZE;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.ui.dialogs.ShowResultAlternativesDialog;
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

	private Composite configContainer;
	private CLabel lblLastResultValue;
	private CLabel lblResultsValue;
	private CLabel lblLastChangeValue;
	private Label lblInputValue;

	private final PropertyChangeListener propertyChangeListener = new PropertyChangeListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			if (evt.getNewValue() == alternative && !isRunning()) {
				updateControls();
			}
		}
	};

	private final PropertyChangeListener inputAlternativeListener = new PropertyChangeListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			Display.getDefault().asyncExec(new Runnable()
			{
				@Override
				public void run()
				{
					if (!isDisposed()) updateMetaData();
				}
			});
		}
	};

	private Group metaContainer;


	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ConfigEditorView(Composite parent, int style, final IConfigAlternative alternative)
	{
		super(parent, style, alternative);

		this.alternative = alternative;

		initTitleWidget();
		initContainer();

		if (alternative == null)
			return; // For WindowBuilder

		initFooterContent();
		initListeners();
	}

	private void initContainer()
	{
		Composite mainContainer = super.getContainer();
		GridLayout layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		mainContainer.setLayout(layout);

		metaContainer = new Group(mainContainer, SWT.NONE);
		metaContainer.setLayout(new GridLayout(4, false));
		GridData gd_grpDetails = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_grpDetails.heightHint = 60;
		metaContainer.setLayoutData(gd_grpDetails);
		metaContainer.setText("Details");

		CLabel lblInputTitle = new CLabel(metaContainer, SWT.NONE);
		lblInputTitle.setText("Input:");

		lblInputValue = new Label(metaContainer, SWT.NONE);
		lblInputValue.setCursor(new Cursor(getDisplay(), SWT.CURSOR_HAND));

		CLabel lblResultsTitle = new CLabel(metaContainer, SWT.NONE);
		lblResultsTitle.setText("  #Results:");

		lblResultsValue = new CLabel(metaContainer, SWT.NONE);

		CLabel lblLastChangeTitle = new CLabel(metaContainer, SWT.NONE);
		lblLastChangeTitle.setText("Last change:");

		lblLastChangeValue = new CLabel(metaContainer, SWT.NONE);

		CLabel lblLastResultTitle = new CLabel(metaContainer, SWT.NONE);
		lblLastResultTitle.setText("  Last result:");

		lblLastResultValue = new CLabel(metaContainer, SWT.NONE);
		lblLastResultValue.setCursor(new Cursor(getDisplay(), SWT.CURSOR_HAND));

		this.configContainer = new Composite(mainContainer, SWT.NONE);
		configContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		lblInputValue.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				ResourceRegistry.getInstance().openResourceEditor(alternative.getInputAlternative());
			}
		});
		
		lblLastResultValue.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				if (alternative.getLastResult() != null)
					ResourceRegistry.getInstance().openResourceEditor(alternative.getLastResult());
			}
		});

		updateMetaData();
	}

	private void updateMetaData()
	{
		if(alternative.getInputAlternative() != null){
			lblInputValue.setText(this.alternative.getInputAlternative().getName());
			lblLastChangeValue.setText(getModifiedString(this.alternative.getInputAlternative()));
		}
		
		List<IEditorInputResource> results = this.alternative.getResults();

		if (results != null && !results.isEmpty()) {
			lblResultsValue.setText("" + results.size());
			lblLastResultValue.setText(getModifiedString(alternative.getLastResult()));
		} else {
			lblResultsValue.setText("0");
			lblLastResultValue.setText("n/a");
		}

		metaContainer.layout();
	}
	
	private SimpleDateFormat sdf_name = new SimpleDateFormat("d/MM, hh:mm:ss");
	private String getModifiedString (IEditorInputResource eir)
	{
		try {
			String time = eir.getProperty("modified");
			Date date = new Date(Long.parseLong(time));
			return sdf_name.format(date);
		} catch (Exception e) {
			return "n/a";
		}
	}

	@Override
	protected Composite getContainer()
	{
		return this.configContainer;
	}

	private void initTitleWidget()
	{
		new TitleWidget(getHeader(), SWT.NONE, alternative)
		{
			@Override
			protected void initButtons()
			{
				CLabel lblUp = createContextButton("Input",
						IconSetResources.getImage("go_out", COLOR.BLUE, SIZE.SIZE_24));
				CLabel lblResults = createContextButton("Results",
						IconSetResources.getImage("stats_3", COLOR.BLUE, SIZE.SIZE_24));
				createSeparator();

				lblResults.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseUp(MouseEvent e)
					{
						new ShowResultAlternativesDialog(alternative.getResults()).open();
					}
				});

				lblUp.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseUp(MouseEvent e)
					{

						ResourceRegistry.getInstance().openResourceEditor(alternative.getInputAlternative());
						// TODO Auto-generated method stub
						super.mouseUp(e);
					}
				});

				super.initButtons();
			}
		};
	}

	private void initFooterContent()
	{
		Composite composite = new Composite(getFooter(), SWT.NONE);
		GridLayout gl = new GridLayout(2, false);
		gl.marginWidth = 2;
		gl.marginHeight = 2;
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
				if (isRunning()) {
					stop();
				} else {
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
				StatusManager.getInstance().removePropertyChangeListener(StatusManager.PROP_VALIDATION_COMPLETED,
						propertyChangeListener);
				if(alternative.getInputAlternative() != null){
					alternative.getInputAlternative().removePropertyChangeListener(IEditorInputResource.PROP_SAVED, inputAlternativeListener);
				}
			}
		});
		
		this.alternative.getInputAlternative().addPropertyChangeListener(IEditorInputResource.PROP_SAVED, inputAlternativeListener);
		
		StatusManager.getInstance().addPropertyChangeListener(StatusManager.PROP_VALIDATION_COMPLETED,
				propertyChangeListener);
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
			}
		});

		job.schedule();
		lastJob = job;

		updateControls();
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
				if (stackedContainer == null || stackedContainer.isDisposed()) {
					return;
				}

				if (isRunning()) {
					btnRun.setEnabled(false);
					((StackLayout) stackedContainer.getLayout()).topControl = progressComposite;
					stackedContainer.layout();
				} else {
					// WORKAROUND : if before 3s after job is finished, than
					// show result
					if (lastJob != null && System.currentTimeMillis() < 3000 + lastJob.getEndTimestamp()) {
						resultsComposite.setStatus(lastJob.getResult());
						((StackLayout) stackedContainer.getLayout()).topControl = resultsComposite;
					} else {
						((StackLayout) stackedContainer.getLayout()).topControl = validationComposite;
					}

					btnRun.setEnabled(true);
					stackedContainer.layout();
					stackedContainer.redraw();
					
					updateMetaData();
					ConfigEditorView.this.forceFocus();
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
			try {
				startTimestamp = System.currentTimeMillis();
				internalMonitor = new RunProgressMonitor();
				return alternative.run(internalMonitor);
			} finally {
				endTimestamp = System.currentTimeMillis();
			}
		}

		@Override
		protected void canceling()
		{
			internalMonitor.setCanceled(true);
			super.canceling();
		}

		public boolean isRunning()
		{
			return startTimestamp > 0 && endTimestamp == 0;
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
