package eu.cloudscaleproject.env.analyser.editors.result;

import java.util.ArrayList;
import java.util.List;

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
import eu.cloudscaleproject.env.common.CommonResources;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class MonitorResultsComposite extends Composite implements IRefreshable{
	
	private final ResultAlternative alternative;
	
	private final ListViewer menuList;
	private final Composite content;
	
	public MonitorResultsComposite(ResultAlternative alternative, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = alternative;
		
		setLayout(new GridLayout(2, false));
		
		Composite sidepanel = new Composite(this, SWT.NONE);
		sidepanel.setLayout(new FillLayout());
		GridData gd_sidepanel = new GridData(SWT.FILL, SWT.FILL, false, true);
		gd_sidepanel.widthHint = 250;
		sidepanel.setLayoutData(gd_sidepanel);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(sidepanel, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		//sidepanel menu
		menuList = new ListViewer(scrolledComposite);
		org.eclipse.swt.widgets.List list = menuList.getList();
		scrolledComposite.setContent(list);
		scrolledComposite.setMinSize(list.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		menuList.setContentProvider(new ArrayContentProvider());
		menuList.setLabelProvider(new LabelProvider(){
			
			@Override
			public Image getImage(Object element) {
				return super.getImage(element);
			}
			
			@Override
			public String getText(Object element) {
				if(element instanceof MonitorItem){
					MonitorItem mi = (MonitorItem)element;
					return mi.getName();
				}
				return super.getText(element);
			}
			
		});
		
		menuList.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection ss = (IStructuredSelection)event.getSelection();
				Object element = ss.getFirstElement();
				
				if(element instanceof MonitorItem){
					MonitorItem mi = (MonitorItem)element;
					showMeasurement(mi);
				}
			}
		});
				
		content = new Composite(this, SWT.NONE);
		content.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		content.setLayout(new FillLayout());

		//content
	}
	
	private void showMeasurement(MonitorItem monitorItem){
		
		//clear content
		for(Control c : content.getChildren()){
			c.dispose();
		}
		
		monitorItem.initComposite(content, SWT.NONE);
		
		this.layout(true, true);
	}
	
	public void refresh(){
		
		List<MonitorItem> monitors = new ArrayList<>();
		
		LocalDirectoryRepository ldr = alternative.getEDP2Model();
		EList<ExperimentGroup> egList = ldr.getExperimentGroups();
		
		if (egList.isEmpty()) {
			return;
		}

		ExperimentGroup eg = egList.get(egList.size() - 1);
		
		for(ExperimentSetting setting : eg.getExperimentSettings()){
			for(ExperimentRun run : setting.getExperimentRuns()){
				for(Measurement measurement : run.getMeasurement()){
					
					monitors.add(new MonitorItem(setting, measurement));
				}
			}
		}
		
		menuList.setInput(monitors);
		
		if(!monitors.isEmpty()){
			menuList.setSelection(new StructuredSelection(monitors.get(0)));
		}
	}

	private class MonitorItem{
		
		private final Measurement measurement;
		private final MeasuringPoint measuringPoint;	
		private final ExperimentSetting expSettings;
	
		private ChartComposite chartComposite;

		public MonitorItem(final ExperimentSetting setting, final Measurement measurement) {			
			
			this.expSettings = setting;
			
			this.measurement = measurement;
			this.measuringPoint = measurement.getMeasuringType().getMeasuringPoint();			
		}
		
		public String getName(){
			return measuringPoint.getStringRepresentation() + ": " + measurement.getMeasuringType().getMetric().getName();
		}
		
		public void initComposite(Composite parent, int style){
			
			Composite composite = new Composite(parent, SWT.NONE);		
			GridLayout gridLayout = new GridLayout(2, false);
			composite.setLayout(gridLayout);
			composite.setBackground(ColorResources.COLOR_CS_BLUE_LIGHT);
			
			Label chartName = new Label(composite, SWT.WRAP);
			chartName.setText(measuringPoint.getStringRepresentation()+ " [ "+ expSettings.getDescription() +" ]");
			chartName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			
			Composite viewPanel = new Composite(composite, SWT.NONE);
			viewPanel.setLayout(new RowLayout());
			viewPanel.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false));
			
			//diagram view controls
			
			//Pie chart icon
			{
				Label icon = new Label(viewPanel, SWT.NONE);
				icon.setLayoutData(new RowData(16, 16));
				icon.setBackgroundImage(CommonResources.CHART_PIE_16);
				icon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						super.mouseUp(e);
						displayChart(0);
					}
				});
			}
			
			//Histogram chart icon
			{
				Label icon = new Label(viewPanel, SWT.NONE);
				icon.setLayoutData(new RowData(16, 16));
				icon.setBackgroundImage(CommonResources.CHART_HISTOGRAM_16);
				icon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						super.mouseUp(e);
						displayChart(2);
					}
				});
			}
			
			//Histogram chart icon
			{
				Label icon = new Label(viewPanel, SWT.NONE);
				icon.setLayoutData(new RowData(16, 16));
				icon.setBackgroundImage(CommonResources.CHART_LINE_16);
				icon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						super.mouseUp(e);
						displayChart(1);
					}
				});
			}
			
			chartComposite = new ChartComposite(composite, SWT.NONE);
			chartComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
			
			MouseListener diagramMouseListener = new MouseListener() {
				
				@Override
				public void mouseUp(MouseEvent e) {}
				@Override
				public void mouseDown(MouseEvent e) {}
				
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					openChainSelectionDialog(MonitorItem.this.measurement);	
				}
			};
			
			composite.addMouseListener(diagramMouseListener);
			chartName.addMouseListener(diagramMouseListener);
			chartComposite.addMouseListener(diagramMouseListener);
			
			displayChart(null);
		}
		
		@SuppressWarnings("unchecked")
		public void displayChart(Integer type){
			
			final RawMeasurements rawMeasurements = measurement.getMeasurementRanges().get(0).getRawMeasurements();
			if (rawMeasurements != null && !rawMeasurements.getDataSeries().isEmpty()) {
				IDataSource edp2Source = new Edp2DataTupleDataSource(rawMeasurements);
				int dataStreamSize = edp2Source.getDataStream().size();
				edp2Source.getDataStream().close();
				if (dataStreamSize > 0) {
					
					Integer persistedType = null;
					String chartType = alternative.getProperty(measurement.getId());
					if(chartType != null){
						persistedType = Integer.parseInt(chartType);
					}
					
					if(type != null && persistedType != type){
						alternative.setProperty(measurement.getId(), String.valueOf(type));
						alternative.setDirty(true);
					}
					
					if(type == null){
						type = persistedType;
					}
					
					//set default diagram chart type
					if(type == null){
						type = 1;
					}
					
					ChainDescription chainDescription = ResultUtils.getApplicableChainDescriptionsFromExtensions(edp2Source).get(type);
					
					@SuppressWarnings("rawtypes")
					IVisualisationInput input = (IVisualisationInput) chainDescription.getVisualizationInput();
			        input.addInput(input.createNewInput(chainDescription.attachRootDataSource(edp2Source)));
			        
			        final JFreeChart chart = ((JFreeChartVisualizationInput) input).createChart();
			        chart.setTitle("");
			        chartComposite.setChart(chart);
			        chartComposite.forceRedraw();
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
}
