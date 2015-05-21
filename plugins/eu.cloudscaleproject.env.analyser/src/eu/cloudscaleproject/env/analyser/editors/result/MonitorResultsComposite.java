package eu.cloudscaleproject.env.analyser.editors.result;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.jfree.chart.JFreeChart;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.palladiosimulator.edp2.datastream.IDataSource;
import org.palladiosimulator.edp2.datastream.chaindescription.ChainDescription;
import org.palladiosimulator.edp2.datastream.edp2source.Edp2DataTupleDataSource;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroup;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentSetting;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.models.ExperimentData.RawMeasurements;
import org.palladiosimulator.edp2.models.Repository.LocalDirectoryRepository;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.visualization.IVisualisationInput;
import org.palladiosimulator.edp2.visualization.jfreechart.input.JFreeChartVisualizationInput;
import org.palladiosimulator.edp2.visualization.wizards.DefaultViewsWizard;

import eu.cloudscaleproject.env.analyser.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.common.ColorResources;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;

@SuppressWarnings("deprecation")
public class MonitorResultsComposite extends Composite implements IRefreshable{
	
	private final ResultAlternative alternative;
	private final Composite resultItemComposite;

	public MonitorResultsComposite(ResultAlternative alternative, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = alternative;
		setLayout(new FillLayout());
		
		final ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.V_SCROLL);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setAlwaysShowScrollBars(true);
		
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
	    layout.wrap = true;
		
		this.resultItemComposite = new Composite(scrolledComposite, SWT.NONE);
		this.resultItemComposite.setLayout(layout);
		
		scrolledComposite.setContent(this.resultItemComposite);
		
		/**
		 * Scrolled composite fix!
		 */
		scrolledComposite.addControlListener(new ControlAdapter() {
		      public void controlResized(ControlEvent e) {
		        Rectangle r = scrolledComposite.getClientArea();
		        scrolledComposite.setMinSize(resultItemComposite.computeSize(r.width,
		            SWT.DEFAULT));
		      }
		    });
		Rectangle r = scrolledComposite.getClientArea();
        scrolledComposite.setMinSize(resultItemComposite.computeSize(r.width,
            SWT.DEFAULT));
        
		refresh();
	}
	
	public void clearResults(){
		for(Control control : this.resultItemComposite.getChildren()){
			((Composite)control).dispose();
		}
	}
	
	private static class ResultItem extends Composite{
		
		private final Measurement measurement;
		private final MeasuringPoint measuringPoint;
		//private final MetricDescription metricDescription;

		@SuppressWarnings("unchecked")
		public ResultItem(final ExperimentSetting setting, final Measurement measurement, Composite parent, int style) {
			super(parent, style);
			
			this.measurement = measurement;
			this.measuringPoint = measurement.getMeasuringType().getMeasuringPoint();
			//this.metricDescription = measurement.getMeasuringType().getMetric();
			
			GridLayout gridLayout = new GridLayout(1, true);
			setLayout(gridLayout);
			
			Label chartName = new Label(this, SWT.WRAP);
			chartName.setBackground(ColorResources.COLOR_CS_BLUE_LIGHT);
			chartName.setText(measuringPoint.getStringRepresentation()+ " [ "+ setting.getDescription() +" ]");
			chartName.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false));
			
			ChartComposite chartImage = new ChartComposite(this, SWT.NONE);
			chartImage.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			this.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseUp(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseDown(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					openChainSelectionDialog(ResultItem.this.measurement);	
				}
			});
			
			final RawMeasurements rawMeasurements = measurement.getMeasurementRanges().get(0).getRawMeasurements();
			if (rawMeasurements != null && !rawMeasurements.getDataSeries().isEmpty()) {
				IDataSource edp2Source = new Edp2DataTupleDataSource(rawMeasurements);
				int dataStreamSize = edp2Source.getDataStream().size();
				edp2Source.getDataStream().close();
				if (dataStreamSize > 0) {
					
					ChainDescription chainDescription = ResultUtils.getApplicableChainDescriptionsFromExtensions(edp2Source).get(1);
					
					@SuppressWarnings("rawtypes")
					IVisualisationInput input = (IVisualisationInput) chainDescription.getVisualizationInput();
			        input.addInput(input.createNewInput(chainDescription.attachRootDataSource(edp2Source)));
			        
			        final JFreeChart chart = ((JFreeChartVisualizationInput) input).createChart();
			        chart.setTitle("");
			        chartImage.setChart(chart);
			        chartImage.forceRedraw();
				}
			}
			else{
				throw new RuntimeException("Empty Measurements!");
			}
		}
		
		//edp2 viewer copy/paste
		/*
		 * This is copy/paste from EDP2 NavigatorDoubleClickListener in a attempt to open graphs for the measurements.
		 * Unfortunately the following code do not work (edp2Source.getDataStream() throws an exception).    
		 * 
		 * 
		 */
	    private void openChainSelectionDialog(final Object selectedObject) {
	        final Measurement measurements = (Measurement) selectedObject;
	        final RawMeasurements rawMeasurements = measurements.getMeasurementRanges().get(0).getRawMeasurements();

	        if (rawMeasurements != null && !rawMeasurements.getDataSeries().isEmpty()) {
	            final IDataSource edp2Source = new Edp2DataTupleDataSource(rawMeasurements);
	            final int dataStreamSize = edp2Source.getDataStream().size();
	            edp2Source.getDataStream().close();

	            if (dataStreamSize > 0) {
	                openWizard(edp2Source);
	            }
	        } else {
	            throw new RuntimeException("Empty Measurements!");
	        }
	    }

	    // open the wizard with reference to the selected source
	    // it shows possible visualizations, which are instances of
	    // DefaultSequence
	    private void openWizard(final IDataSource edp2Source) {
			final DefaultViewsWizard wizard = new DefaultViewsWizard(edp2Source);
	        final WizardDialog wdialog = new WizardDialog(PlatformUI.getWorkbench()
	                .getActiveWorkbenchWindow().getShell(), wizard);
	        wdialog.open();

	        if (wdialog.getReturnCode() == Window.OK) {
	            openEditor(edp2Source, wizard);
	        }
	    }

	    @SuppressWarnings({ "rawtypes", "unchecked" })
		private void openEditor(final IDataSource edp2Source, final DefaultViewsWizard wizard) {
	        final ChainDescription chainDescription = wizard.getSelectedDefault();
	        final IVisualisationInput input = (IVisualisationInput) chainDescription.getVisualizationInput();
	        input.addInput(input.createNewInput(chainDescription.attachRootDataSource(edp2Source)));
	        try {
	            final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
	                    .getActivePage();
	            page.openEditor(input, "org.palladiosimulator.edp2.visualization.editors.JFreeChartEditor");
	        } catch (final PartInitException e) {
	            throw new RuntimeException(e);
	        }
	    }
	}
	
	@Override
	public void refresh() {
		
		clearResults();
		
		LocalDirectoryRepository ldr = alternative.getEDP2Model();
		EList<ExperimentGroup> egList = ldr.getExperimentGroups();
		
		if (egList.isEmpty()) {
			return;
		}

		ExperimentGroup eg = egList.get(egList.size() - 1);
		
		for(ExperimentSetting setting : eg.getExperimentSettings()){
			for(ExperimentRun run : setting.getExperimentRuns()){
				for(Measurement measurement : run.getMeasurement()){
					ResultItem ri = new ResultItem(setting, measurement, resultItemComposite, SWT.NONE);
					ri.setBackground(ColorResources.COLOR_CS_BLUE_LIGHT);
					ri.setLayoutData(new RowData(400, 250));
					ri.layout(true);
					ri.pack();
				}
			}
		}

		resultItemComposite.pack(true);
		resultItemComposite.layout(true);
		resultItemComposite.redraw();
	}
}
