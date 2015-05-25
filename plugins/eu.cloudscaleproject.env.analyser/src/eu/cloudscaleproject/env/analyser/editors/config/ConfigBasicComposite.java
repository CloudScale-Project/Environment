package eu.cloudscaleproject.env.analyser.editors.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.common.edit.command.ChangeCommand;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationFactory;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationPackage;
import org.palladiosimulator.experimentautomation.abstractsimulation.MeasurementCountStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.SimTimeStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.StopCondition;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;

public class ConfigBasicComposite extends Composite implements IRefreshable{
	
	protected final ConfAlternative alternative;
		
	private final Composite extensionComposite;
	private final Button btnCheckSimTime;
	private final Button btnCheckMeasureCount;
		
	private Text textSimTime;
	private Text textMeasureCount;
	
	private SimTimeStopCondition simTime = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
	private MeasurementCountStopCondition measureCount = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
	
	private DataBindingContext bindingContext = null;
	
	private PropertyChangeListener alternativeListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(ConfAlternative.PROP_INPUT_ALT_SET.equals(evt.getPropertyName())){
				refresh();
			}
		}
	};

	public ConfigBasicComposite(ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = input;
				
		setLayout(new GridLayout(1, false));
		
		/*
		Label lblUsage = new Label(this, SWT.NONE);
		lblUsage.setText("Usage:");
		
		Composite compositeUsageEvolution = new Composite(this, SWT.NONE);
		compositeUsageEvolution.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_compositeMetDesc = new GridLayout(2, false);
		gl_compositeMetDesc.marginLeft = 10;
		compositeUsageEvolution.setLayout(gl_compositeMetDesc);
		*/
		
		Label lblConfiguration = new Label(this, SWT.NONE);
		lblConfiguration.setText("Configuration:");
		
		Composite compositeConf = new Composite(this, SWT.NONE);
		compositeConf.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_compositeConf = new GridLayout(2, false);
		gl_compositeConf.marginLeft = 10;
		compositeConf.setLayout(gl_compositeConf);
		
		btnCheckSimTime = new Button(compositeConf, SWT.CHECK);
		btnCheckSimTime.setText("Simulation time stop condition:");
		btnCheckSimTime.setSelection(true);
		
		{
		textSimTime  = new Text(compositeConf, SWT.BORDER);
		GridData gd = new GridData(SWT.NONE, SWT.CENTER, false, false, 1, 1);
		gd.widthHint = 80;
		textSimTime.setLayoutData(gd);
		}
		
		btnCheckMeasureCount = new Button(compositeConf, SWT.CHECK);
		btnCheckMeasureCount.setText("Measurement count stop condition:");
		
		{
		textMeasureCount = new Text(compositeConf, SWT.BORDER);
		GridData gd = new GridData(SWT.NONE, SWT.CENTER, false, false, 1, 1);
		gd.widthHint = 80;
		textMeasureCount.setLayoutData(gd);
		}
		
		extensionComposite = new Composite(this, SWT.NONE);
		extensionComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		initExtensions(extensionComposite);
		
		btnCheckSimTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnCheckSimTime.getSelection()){
					textSimTime.setEnabled(true);
				}
				else{
					textSimTime.setEnabled(false);

					alternative.getEditingDomain().getCommandStack().execute(new ChangeCommand(alternative.getEditingDomain(), 
							new Runnable() {
						@Override
						public void run() {
							if(simTime.getSimulationTime() != -1){
								simTime.setSimulationTime(-1);
							}
						}
					}));
				}
			}
		});
		btnCheckMeasureCount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnCheckMeasureCount.getSelection()){
					textMeasureCount.setEnabled(true);
				}
				else{
					textMeasureCount.setEnabled(false);

					alternative.getEditingDomain().getCommandStack().execute(new ChangeCommand(alternative.getEditingDomain(), 
							new Runnable() {
						@Override
						public void run() {
							if(measureCount.getMeasurementCount() != -1){
								measureCount.setMeasurementCount(-1);
							}
						}
					}));
				}
			}
		});
		
		this.alternative.addPropertyChangeListener(alternativeListener);
		
		this.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				
				alternative.removePropertyChangeListener(alternativeListener);
				
				if(bindingContext != null){
					bindingContext.dispose();
					bindingContext = null;
				}
			}
		});
		
		refresh();
	}

	protected void initExtensions(Composite parent){
		//override in subclasses
	}

	public void refresh() {
		
		for(StopCondition sc : alternative.retrieveExperimentModel().getStopConditions()){
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
			alternative.retrieveExperimentModel().getStopConditions().add(simTime);
			alternative.save();
		}
		
		if(measureCount == null){
			measureCount = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
			measureCount.setMeasurementCount(-1);
			alternative.retrieveExperimentModel().getStopConditions().add(measureCount);
			alternative.save();
		}
		
		//data binding
		if(bindingContext != null){
			bindingContext.dispose();
		}
		bindingContext = new DataBindingContext();
		
		UpdateValueStrategy t2mStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
		t2mStrategy.setConverter(StringToNumberConverter.toInteger(true));
		
		UpdateValueStrategy m2tStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
		m2tStrategy.setConverter(NumberToStringConverter.fromInteger(true));
		
		IObservableValue simTimeObs = EMFEditProperties.value(alternative.getEditingDomain(),
				AbstractsimulationPackage.Literals.SIM_TIME_STOP_CONDITION__SIMULATION_TIME).observe(simTime);
		IObservableValue measureCountObs = EMFEditProperties.value(alternative.getEditingDomain(),
				AbstractsimulationPackage.Literals.MEASUREMENT_COUNT_STOP_CONDITION__MEASUREMENT_COUNT).observe(measureCount);
		
		Binding simTimeBind = bindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(textSimTime),
		        simTimeObs, t2mStrategy, m2tStrategy);
		
		Binding measureCountBind = bindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(textMeasureCount),
		        measureCountObs, t2mStrategy, m2tStrategy);
		
		ControlDecorationSupport.create(simTimeBind, SWT.TOP | SWT.LEFT);
		ControlDecorationSupport.create(measureCountBind, SWT.TOP | SWT.LEFT);
		
		simTimeObs.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent event) {
				if(!isDisposed()){
					updateSimTimeGUI();
				}
			}
		});
		
		measureCountObs.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent event) {
				if(!isDisposed()){
					updateMeasureCountGUI();
				}
			}
		});
		
		updateSimTimeGUI();
		updateMeasureCountGUI();
		
		bindingContext.updateTargets();
	}
	
	private void updateSimTimeGUI(){
		if(simTime.getSimulationTime() == -1){
			btnCheckSimTime.setSelection(false);
			textSimTime.setEnabled(false);
		}
		else{
			btnCheckSimTime.setSelection(true);
			textSimTime.setEnabled(true);
		}
	}
	
	private void updateMeasureCountGUI(){
		if(measureCount.getMeasurementCount() == -1){
			btnCheckMeasureCount.setSelection(false);
			textMeasureCount.setEnabled(false);
		}
		else{
			btnCheckMeasureCount.setSelection(true);
			textMeasureCount.setEnabled(true);
		}
	}
}
