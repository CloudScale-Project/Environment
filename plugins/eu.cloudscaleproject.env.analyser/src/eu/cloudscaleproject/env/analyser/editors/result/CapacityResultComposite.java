package eu.cloudscaleproject.env.analyser.editors.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.measure.Measure;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.palladiosimulator.edp2.datastream.IDataSource;
import org.palladiosimulator.edp2.datastream.edp2source.Edp2DataTupleDataSource;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroup;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroupRun;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.models.ExperimentData.RawMeasurements;
import org.palladiosimulator.edp2.models.Repository.LocalDirectoryRepository;
import org.palladiosimulator.measurementframework.measureprovider.IMeasureProvider;

import eu.cloudscaleproject.env.analyser.alternatives.ResultAlternative;
import eu.cloudscaleproject.env.common.ui.IRefreshable;

public class CapacityResultComposite extends Composite implements IRefreshable{
	
	private TableViewer tableViewer;
	private MonitorResultsComposite monitorResults;
	
	private final ResultAlternative alternative;
	private Table table;
	
	private static class CapacityResult{
		
		public final String name;
		public double value;
		
		public CapacityResult(String name) {
			this.name = name;
		}
	}
	
	private final List<CapacityResult> capacityResults = new ArrayList<CapacityResult>();

	public CapacityResultComposite(ResultAlternative alternative, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = alternative;
		setLayout(new GridLayout(1, false));
		
		Label lblResults = new Label(this, SWT.NONE);
		lblResults.setText("Results:");
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		GridData gd_table = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_table.heightHint = 50;
		table.setLayoutData(gd_table);
		table.setHeaderVisible(true);
	    table.setLinesVisible(true);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDate = tableViewerColumn.getColumn();
		tblclmnDate.setWidth(200);
		tblclmnDate.setText("Date");
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				CapacityResult cr = (CapacityResult)element;
				return cr.name;
			}
		});
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCapacity = tableViewerColumn_1.getColumn();
		tblclmnCapacity.setWidth(100);
		tblclmnCapacity.setText("Capacity");
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				CapacityResult cr = (CapacityResult)element;
				return String.valueOf(cr.value);
			}
		});
		
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(capacityResults);
		
		this.monitorResults = new MonitorResultsComposite(alternative, this, SWT.NONE);
		this.monitorResults.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	
	@Override
	public void refresh() {
		
		capacityResults.clear();
		
		LocalDirectoryRepository ldr = alternative.getEDP2Model();
		EList<ExperimentGroup> egList = ldr.getExperimentGroups();
		
		if (egList.isEmpty()) {
			Collections.reverse(capacityResults);
			tableViewer.setInput(capacityResults);
			return;
		}

		ExperimentGroup eg = egList.get(egList.size() - 1);
		for (ExperimentGroupRun run : eg.getReports()) {
			
			System.out.println("Group run: " + run.getId());
			
			CapacityResult result = new CapacityResult("System capacity");
			
			for (int i = 0; i < run.getMeasurement().size(); i++) {
				Measurement measurement = run.getMeasurement().get(i);
				
				System.out.println("	Measurement counter: " + i);
				
				for(int j=0; j<measurement.getMeasurementRanges().size(); j++){
					RawMeasurements rawMeasurements = measurement
							.getMeasurementRanges().get(j).getRawMeasurements();
					
					System.out.println("		Measurement range counter: " + j);
	
					if (rawMeasurements != null && !rawMeasurements.getDataSeries().isEmpty()) {
						final IDataSource edp2Source = new Edp2DataTupleDataSource(rawMeasurements);
						// final int dataStreamSize =
						// edp2Source.getDataStream().size();
	
						Iterator<IMeasureProvider> iter = edp2Source.getDataStream().iterator();
						while (iter.hasNext()) {
							System.out.println("			Retrieving measures");

							List<Measure<?,?>> measures = iter.next().asList();
							for(int m=0; m<measures.size(); m++){
								Measure<?,?> measure = measures.get(m);
								
								if(m == 1){
									result.value = Double.parseDouble(measure.getValue().toString());
									capacityResults.add(result);
								}
								
								System.out.println("				Measure value " + m + " : " + measure.getValue().toString());
							}
						}
	
						edp2Source.getDataStream().close();
					}
				}
			}
		}
		
		Collections.reverse(capacityResults);
		tableViewer.setInput(capacityResults);
		
		monitorResults.refresh();
	}
}