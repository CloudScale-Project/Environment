package eu.cloudscaleproject.env.analyser.editors.result;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.jfree.chart.JFreeChart;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.palladiosimulator.edp2.datastream.IDataSource;
import org.palladiosimulator.edp2.datastream.chaindescription.ChainDescription;
import org.palladiosimulator.edp2.datastream.edp2source.Edp2DataTupleDataSource;
import org.palladiosimulator.edp2.local.LocalDirectoryRepository;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroup;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentSetting;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.models.ExperimentData.RawMeasurements;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.visualization.IVisualisationInput;
import org.palladiosimulator.edp2.visualization.jfreechart.input.JFreeChartVisualizationInput;
import org.palladiosimulator.edp2.visualization.wizards.DefaultViewsWizard;

import eu.cloudscaleproject.env.analyser.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.analyser.editors.result.ResultUtils.ChartType;
import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class MonitorResultsComposite extends Composite implements IRefreshable
{

	private static final Logger LOGGER = Logger.getLogger(MonitorResultsComposite.MonitorItem.class.getName());

	private final ResultAlternative alternative;

	private final ListViewer menuList;

	private final Composite compositeContent;
	private final Composite compositeContentData;

	public MonitorResultsComposite(ResultAlternative alternative, Composite parent, int style)
	{
		super(parent, style);

		setLayout(new GridLayout(2, false));
		this.alternative = alternative;

		Composite sidepanel = new Composite(this, SWT.NONE);
		sidepanel.setLayout(new FillLayout());
		GridData gd_sidepanel = new GridData(SWT.FILL, SWT.FILL, false, true);
		gd_sidepanel.widthHint = 250;
		sidepanel.setLayoutData(gd_sidepanel);

		ScrolledComposite scrolledComposite = new ScrolledComposite(sidepanel, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		// sidepanel menu
		menuList = new ListViewer(scrolledComposite);
		org.eclipse.swt.widgets.List list = menuList.getList();
		scrolledComposite.setContent(list);
		scrolledComposite.setMinSize(list.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		menuList.setContentProvider(new ArrayContentProvider());
		menuList.setLabelProvider(new LabelProvider()
		{

			@Override
			public Image getImage(Object element)
			{
				return super.getImage(element);
			}

			@Override
			public String getText(Object element)
			{
				if (element instanceof MonitorItem)
				{
					MonitorItem mi = (MonitorItem) element;
					return mi.getName();
				}
				return super.getText(element);
			}

		});

		menuList.addSelectionChangedListener(new ISelectionChangedListener()
		{

			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				IStructuredSelection ss = (IStructuredSelection) event.getSelection();
				Object element = ss.getFirstElement();

				if (element instanceof MonitorItem)
				{
					MonitorItem mi = (MonitorItem) element;
					showMeasurement(mi);
				}
			}
		});

		compositeContent = new Composite(this, SWT.NONE);
		GridData gd_content = new GridData(SWT.FILL, SWT.FILL, true, true);
		compositeContent.setLayoutData(gd_content);
		compositeContent.setLayout(new FillLayout());

		compositeContentData = new Composite(compositeContent, SWT.NONE);
		compositeContentData.setLayout(new FillLayout());

		// content
	}

	private void showMeasurement(MonitorItem monitorItem)
	{

		// clear content
		for (Control c : compositeContentData.getChildren())
		{
			c.dispose();
		}

		monitorItem.initComposite(compositeContentData, SWT.NONE);

		this.layout(true, true);
	}

	private void init()
	{
		if (menuList.getInput() != null)
			return;

		List<MonitorItem> monitors = new ArrayList<>();

		LocalDirectoryRepository ldr = alternative.getEDP2Model();
		EList<ExperimentGroup> egList = ldr.getExperimentGroups();

		if (egList.isEmpty())
		{
			return;
		}

		ExperimentGroup eg = egList.get(egList.size() - 1);

		for (ExperimentSetting setting : eg.getExperimentSettings())
		{
			for (ExperimentRun run : setting.getExperimentRuns())
			{
				for (Measurement measurement : run.getMeasurement())
				{

					monitors.add(new MonitorItem(setting, measurement));
				}
			}
		}

		menuList.setInput(monitors);

		if (!monitors.isEmpty())
		{
			menuList.setSelection(new StructuredSelection(monitors.get(0)));
		}
	}

	public void refresh()
	{
		init();
	}

	private class MonitorItem
	{

		private final Measurement measurement;
		private final MeasuringPoint measuringPoint;
		private final ExperimentSetting expSettings;

		private ChartComposite chartComposite;
		private final ChartType DEFAULT_TYPE = ChartType.XY;
		private ChartType currentType = null;
		private Composite chartContainer;
		private Composite emptyComposite;
		private Label lblEmptyText;

		public MonitorItem(final ExperimentSetting setting, final Measurement measurement)
		{

			this.expSettings = setting;

			this.measurement = measurement;
			this.measuringPoint = measurement.getMeasuringType().getMeasuringPoint();
		}

		public String getName()
		{
			return measuringPoint.getStringRepresentation() + ": " + measurement.getMeasuringType().getMetric().getName();
		}

		public void initComposite(Composite parent, int style)
		{

			Composite composite = new Composite(parent, SWT.NONE);
			GridLayout gridLayout = new GridLayout(2, false);
			composite.setLayout(gridLayout);
			composite.setBackground(ColorResources.COLOR_CS_BLUE_LIGHT);

			Label chartName = new Label(composite, SWT.WRAP);
			chartName.setText(measuringPoint.getStringRepresentation() + " [ " + expSettings.getDescription() + " ]");
			chartName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			Composite viewPanel = new Composite(composite, SWT.NONE);
			viewPanel.setLayout(new RowLayout());
			viewPanel.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false));

			// diagram view controls



			// XY chart icon
			{
				Label icon = new Label(viewPanel, SWT.NONE);
				icon.setLayoutData(new RowData(16, 16));
				icon.setBackgroundImage(CommonResources.CHART_LINE_16);
				icon.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseUp(MouseEvent e)
					{
						super.mouseUp(e);
						displayChart(ChartType.XY);
					}
				});
			}

			// Histogram chart icon
			{
				Label icon = new Label(viewPanel, SWT.NONE);
				icon.setLayoutData(new RowData(16, 16));
				icon.setBackgroundImage(CommonResources.CHART_HISTOGRAM_16);
				icon.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseUp(MouseEvent e)
					{
						super.mouseUp(e);
						displayChart(ChartType.HISTOGRAM);
					}
				});
			}

			// Pie chart icon
			{
				Label icon = new Label(viewPanel, SWT.NONE);
				icon.setLayoutData(new RowData(16, 16));
				icon.setBackgroundImage(CommonResources.CHART_PIE_16);
				icon.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseUp(MouseEvent e)
					{
						super.mouseUp(e);
						displayChart(ChartType.PIE);
					}
				});
			}

			chartContainer = new Composite(composite, SWT.NONE);
			chartContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
			chartContainer.setLayout(new StackLayout());

			chartComposite = new ChartComposite(chartContainer, SWT.NONE);

			emptyComposite = new Composite(chartContainer, SWT.NONE);
			emptyComposite.setLayout(new GridLayout(1, false));
			emptyComposite.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
			emptyComposite.setBackground(getBackground());

			Composite inner = new Composite(emptyComposite, SWT.BORDER);
			inner.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			inner.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
			inner.setLayout(new GridLayout(1, false));

			lblEmptyText = new Label(inner, SWT.CENTER);
			lblEmptyText.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));

			MouseListener diagramMouseListener = new MouseListener()
			{

				@Override
				public void mouseUp(MouseEvent e)
				{
				}

				@Override
				public void mouseDown(MouseEvent e)
				{
				}

				@Override
				public void mouseDoubleClick(MouseEvent e)
				{
					openChainSelectionDialog(MonitorItem.this.measurement);
				}
			};

			composite.addMouseListener(diagramMouseListener);
			chartName.addMouseListener(diagramMouseListener);
			chartComposite.addMouseListener(diagramMouseListener);

			displayChart(null);
		}

		@SuppressWarnings("unchecked")
		public void displayChart(ChartType type)
		{
			String emptyText = null;

			final RawMeasurements rawMeasurements = measurement.getMeasurementRanges().get(0).getRawMeasurements();
			if (rawMeasurements != null 
					&& !rawMeasurements.getDataSeries().isEmpty()
					&& rawMeasurements.cdoResource() != null
					&& rawMeasurements.cdoResource().isExisting())
			{
				
				IDataSource edp2Source = new Edp2DataTupleDataSource(rawMeasurements);
				int dataStreamSize = edp2Source.getDataStream().size();
				edp2Source.getDataStream().close();
				if (dataStreamSize > 0)
				{
					// set default diagram chart type
					currentType = type != null ? type : (currentType != null ? currentType : DEFAULT_TYPE);

					ChainDescription chainDescription = ResultUtils.getChainDescriptionByChartType(currentType);

					try
					{
						@SuppressWarnings("rawtypes")
						IVisualisationInput input = (IVisualisationInput) chainDescription.getVisualizationInput();
						input.addInput(input.createNewInput(chainDescription.attachRootDataSource(edp2Source)));
						final JFreeChart chart = ((JFreeChartVisualizationInput) input).createChart();
						chart.setTitle("");
						chartComposite.setChart(chart);
					} catch (Exception e)
					{
						LOGGER.severe("Unable to show chart : type=" + currentType + "  exception=" + e.getMessage());
						e.printStackTrace();

						emptyText = "Error in chart creation.";
					}

					chartComposite.layout(true);
					chartComposite.forceRedraw();
				} else
				{
					emptyText = "Empty datasource.";
				}
			} else
			{
				emptyText = "Measruements missing.";
			}

			if (emptyText != null)
			{
				lblEmptyText.setText("Unable to show chart\n\n > " + emptyText);
				emptyComposite.layout();
				lblEmptyText.getParent().layout();
				((StackLayout) chartContainer.getLayout()).topControl = emptyComposite;
			} else
			{
				((StackLayout) chartContainer.getLayout()).topControl = chartComposite;
			}

			chartContainer.layout();
			compositeContent.redraw();
			compositeContent.layout();
		}

		// edp2 viewer copy/paste
		/*
		 * This is copy/paste from EDP2 NavigatorDoubleClickListener in a
		 * attempt to open graphs for the measurements. Unfortunately the
		 * following code do not work (edp2Source.getDataStream() throws an
		 * exception).
		 */
		private void openChainSelectionDialog(final Object selectedObject)
		{
			final Measurement measurements = (Measurement) selectedObject;
			final RawMeasurements rawMeasurements = measurements.getMeasurementRanges().get(0).getRawMeasurements();

			if (rawMeasurements != null && !rawMeasurements.getDataSeries().isEmpty())
			{
				final IDataSource edp2Source = new Edp2DataTupleDataSource(rawMeasurements);
				final int dataStreamSize = edp2Source.getDataStream().size();
				edp2Source.getDataStream().close();

				if (dataStreamSize > 0)
				{
					openWizard(edp2Source);
				}
			} else
			{
				throw new RuntimeException("Empty Measurements!");
			}
		}

		// open the wizard with reference to the selected source
		// it shows possible visualizations, which are instances of
		// DefaultSequence
		private void openWizard(final IDataSource edp2Source)
		{
			final DefaultViewsWizard wizard = new DefaultViewsWizard(edp2Source);
			final WizardDialog wdialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
			wdialog.open();

			if (wdialog.getReturnCode() == Window.OK)
			{
				openEditor(edp2Source, wizard);
			}
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		private void openEditor(final IDataSource edp2Source, final DefaultViewsWizard wizard)
		{
			final ChainDescription chainDescription = wizard.getSelectedDefault();
			final IVisualisationInput input = (IVisualisationInput) chainDescription.getVisualizationInput();
			input.addInput(input.createNewInput(chainDescription.attachRootDataSource(edp2Source)));
			try
			{
				final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				page.openEditor(input, "org.palladiosimulator.edp2.visualization.editors.JFreeChartEditor");
			} catch (final PartInitException e)
			{
				throw new RuntimeException(e);
			}
		}
	}
}
