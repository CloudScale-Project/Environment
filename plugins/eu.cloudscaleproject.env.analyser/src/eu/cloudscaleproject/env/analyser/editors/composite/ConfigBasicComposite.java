package eu.cloudscaleproject.env.analyser.editors.composite;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationFactory;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationPackage;
import org.palladiosimulator.experimentautomation.abstractsimulation.MeasurementCountStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.SimTimeStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.StopCondition;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.toolchain.util.ISaveableComposite;

public class ConfigBasicComposite extends Composite implements ISaveableComposite{
	
	private final ConfAlternative alternative;
	
	private Text textSimTime;
	private Text textMeasureCount;
	
	SimTimeStopCondition simTime = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
	MeasurementCountStopCondition measureCount = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
	
	DataBindingContext bindingContext = new DataBindingContext();

	public ConfigBasicComposite(ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = input;
		
		setLayout(new GridLayout(1, false));
		
		Label lblMetricDescription = new Label(this, SWT.NONE);
		lblMetricDescription.setText("Metric description:");
		
		Composite compositeMetDesc = new Composite(this, SWT.NONE);
		compositeMetDesc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_compositeMetDesc = new GridLayout(1, false);
		gl_compositeMetDesc.marginLeft = 10;
		compositeMetDesc.setLayout(gl_compositeMetDesc);
		
		//Currently not in use - analyser does not support those jet...
		//TODO: Wire those when the Analyser is ready
		Button btnCheckNumContainers = new Button(compositeMetDesc, SWT.CHECK);
		btnCheckNumContainers.setText("Number of containers");
		
		Button btnCheckRespTime = new Button(compositeMetDesc, SWT.CHECK);
		btnCheckRespTime.setText("Response time");
		
		Button btnCheckCost = new Button(compositeMetDesc, SWT.CHECK);
		btnCheckCost.setText("Cost");
		//
		
		Label lblConfiguration = new Label(this, SWT.NONE);
		lblConfiguration.setText("Configuration:");
		
		Composite compositeConf = new Composite(this, SWT.NONE);
		compositeConf.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_compositeConf = new GridLayout(2, false);
		gl_compositeConf.marginLeft = 10;
		compositeConf.setLayout(gl_compositeConf);
		
		final Button btnCheckSimTime = new Button(compositeConf, SWT.CHECK);
		btnCheckSimTime.setText("Simulation time stop condition:");
		btnCheckSimTime.setSelection(true);
		
		textSimTime = new Text(compositeConf, SWT.BORDER);
		textSimTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		final Button btnCheckMeasureCount = new Button(compositeConf, SWT.CHECK);
		btnCheckMeasureCount.setText("Measurement count stop condition:");
		
		textMeasureCount = new Text(compositeConf, SWT.BORDER);
		textMeasureCount.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnCheckSimTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnCheckSimTime.getSelection()){
					textSimTime.setEditable(true);
				}
				else{
					textSimTime.setEditable(false);
					simTime.setSimulationTime(-1);
				}
			}
		});
		btnCheckMeasureCount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnCheckMeasureCount.getSelection()){
					textMeasureCount.setEditable(true);
				}
				else{
					textMeasureCount.setEditable(false);
					measureCount.setMeasurementCount(-1);
				}
			}
		});
	}


	@Override
	public void save() {
		alternative.save();
	}

	@Override
	public void load() {
		alternative.load();
		
		for(StopCondition sc : alternative.getExperiment().getStopConditions()){
			if(sc instanceof SimTimeStopCondition){
				simTime = (SimTimeStopCondition)sc;
			}
			else if(sc instanceof MeasurementCountStopCondition){
				measureCount = (MeasurementCountStopCondition)sc;
			}
		}
		
		if(simTime == null){
			simTime = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
			simTime.setSimulationTime(-1);
			alternative.getExperiment().getStopConditions().add(simTime);
		}
		
		if(measureCount == null){
			measureCount = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
			measureCount.setMeasurementCount(-1);
			alternative.getExperiment().getStopConditions().add(measureCount);
		}
		alternative.save();
		
		bindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(textSimTime),
		        EMFProperties.value(AbstractsimulationPackage.Literals.SIM_TIME_STOP_CONDITION__SIMULATION_TIME)
		            .observe(simTime));
		
		bindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(textMeasureCount),
		        EMFProperties.value(AbstractsimulationPackage.Literals.MEASUREMENT_COUNT_STOP_CONDITION__MEASUREMENT_COUNT)
		            .observe(measureCount));
	}


	@Override
	public boolean isDirty() {
		return alternative.isDirty();
	}

}
