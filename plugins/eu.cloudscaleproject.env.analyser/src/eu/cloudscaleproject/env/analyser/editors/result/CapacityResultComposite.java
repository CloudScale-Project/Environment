package eu.cloudscaleproject.env.analyser.editors.result;

import java.util.Iterator;
import java.util.List;

import javax.measure.Measure;

import org.eclipse.swt.widgets.Composite;
import org.palladiosimulator.edp2.datastream.IDataSource;
import org.palladiosimulator.edp2.datastream.edp2source.Edp2DataTupleDataSource;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroup;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroupRun;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.models.ExperimentData.RawMeasurements;
import org.palladiosimulator.measurementframework.measureprovider.IMeasureProvider;

import eu.cloudscaleproject.env.analyser.alternatives.ResultAlternative;

public class CapacityResultComposite extends ReportResultComposite{
	
	public CapacityResultComposite(ResultAlternative alternative, Composite parent, int style) {
		super(alternative, parent, style);
	}

	@Override
	protected void fillTable(ExperimentGroup experimentGroup, int counter, List<TableRow> tableRows) {
		
		for (ExperimentGroupRun run : experimentGroup.getReports()) {			
			for (int i = 0; i < run.getMeasurement().size(); i++) {
				Measurement measurement = run.getMeasurement().get(i);
								
				for(int j=0; j<measurement.getMeasurementRanges().size(); j++){
					RawMeasurements rawMeasurements = measurement.getMeasurementRanges().get(j).getRawMeasurements();
					
					if (rawMeasurements != null && !rawMeasurements.getDataSeries().isEmpty()){
						
						final IDataSource edp2Source = new Edp2DataTupleDataSource(rawMeasurements);
	
						Iterator<IMeasureProvider> iter = edp2Source.getDataStream().iterator();
						while (iter.hasNext()) {

							List<Measure<?,?>> measures = iter.next().asList();
							for(int m=0; m<measures.size(); m++){
								Measure<?,?> measure = measures.get(m);
								
								if(m == 1){
									TableRow result = new TableRow("System capacity");
									result.value = Double.parseDouble(measure.getValue().toString());
									tableRows.add(result);
								}
							}
						}
						edp2Source.getDataStream().close();
					}
				}
			}
		}
	}

}
