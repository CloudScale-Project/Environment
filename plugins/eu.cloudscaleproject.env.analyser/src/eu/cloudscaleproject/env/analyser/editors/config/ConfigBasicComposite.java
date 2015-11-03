package eu.cloudscaleproject.env.analyser.editors.config;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationFactory;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationPackage.Literals;
import org.palladiosimulator.experimentautomation.abstractsimulation.MeasurementCountStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.SimTimeStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.StopCondition;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.converters.IntToString;
import eu.cloudscaleproject.env.analyser.converters.StringToInt;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;

public class ConfigBasicComposite extends Composite implements IRefreshable{
	
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	
	private Text stTextValue;
	private Text mcTextValue;
	
	private SimTimeStopCondition stStopCondition;
	private MeasurementCountStopCondition mcStopCondition;
	
	private Button btnSimulationTimeStop;
	private Button btnMeasurementCountStop;
	
	protected final ConfAlternative alternative;

	private final Composite extensionComposite;
	
	public ConfigBasicComposite(final ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = input;
		
		//retrieve stop conditions
		for(StopCondition sc : alternative.getActiveExperiment().getStopConditions()){
			if(sc instanceof SimTimeStopCondition){
				stStopCondition = (SimTimeStopCondition)sc;
			}
			else if(sc instanceof MeasurementCountStopCondition){
				mcStopCondition = (MeasurementCountStopCondition)sc;
			}
		}
		
		// create stop conditions if they do not exist
		if(stStopCondition == null){
			stStopCondition = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
			stStopCondition.setSimulationTime(-1);

			input.executeModelChange(new Runnable(){
				@Override
				public void run() {
					input.getActiveExperiment().getStopConditions().add(stStopCondition);
				}
			});
		}
		if(mcStopCondition == null){
			mcStopCondition = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
			mcStopCondition.setMeasurementCount(-1);

			input.executeModelChange(new Runnable(){
				@Override
				public void run() {
					input.getActiveExperiment().getStopConditions().add(mcStopCondition);
				}
			});
		}

		setLayout(new GridLayout(1, false));

		
		Composite basicConfigComposite = new Composite(this, SWT.NONE);
		GridLayout gl_basicConfigComposite = new GridLayout(2, false);
		gl_basicConfigComposite.marginLeft = 10;
		gl_basicConfigComposite.marginHeight = 10;
		basicConfigComposite.setLayout(gl_basicConfigComposite);
		basicConfigComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Label lblDescription = new Label(basicConfigComposite, SWT.NONE);
			lblDescription.setText("Basic measurements settings:");
			lblDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
			
			Composite settingsComposite = new Composite(basicConfigComposite, SWT.NONE);
			GridLayout gl_settingsComposite = new GridLayout(2, false);
			gl_settingsComposite.marginLeft = 10;
			settingsComposite.setLayout(gl_settingsComposite);
			settingsComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			
				btnSimulationTimeStop = new Button(settingsComposite, SWT.CHECK);
				btnSimulationTimeStop.setText("Simulation time stop condition");
				
				stTextValue = new Text(settingsComposite, SWT.BORDER);
				GridData gd_stTextValue = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_stTextValue.widthHint = 60;
				stTextValue.setLayoutData(gd_stTextValue);
				
				btnMeasurementCountStop = new Button(settingsComposite, SWT.CHECK);
				btnMeasurementCountStop.setText("Measurement count stop condition");
				
				mcTextValue = new Text(settingsComposite, SWT.BORDER);
				GridData gd_mcTextValue = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_mcTextValue.widthHint = 60;
				mcTextValue.setLayoutData(gd_mcTextValue);
		
		m_bindingContext = initDataBindings();

		extensionComposite = new Composite(this, SWT.NONE);
		GridLayout gl_extensionComposite = new GridLayout(2, false);
		gl_extensionComposite.marginLeft = 10;
		gl_extensionComposite.marginHeight = 10;
		extensionComposite.setLayout(gl_extensionComposite);
		extensionComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		initExtensions(extensionComposite);
		
		refresh();
	}
	
	protected void initExtensions(Composite parent){
		//override in subclasses
	}

	@Override
	public void refresh() {
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();

		if(stStopCondition != null){

			btnSimulationTimeStop.setEnabled(true);
			stTextValue.setEnabled(true);

			IObservableValue stStopConditionObserveValue = EMFEditObservables.observeValue(alternative.getEditingDomain(), 
																	stStopCondition, Literals.SIM_TIME_STOP_CONDITION__SIMULATION_TIME);

			//text
			IObservableValue observeText = WidgetProperties.text(new int[]{SWT.Modify, SWT.FocusOut}).observe(stTextValue);
			UpdateValueStrategy stringToInt = new UpdateValueStrategy();
			stringToInt.setConverter(new StringToInt());
			UpdateValueStrategy intToString = new UpdateValueStrategy();
			intToString.setConverter(new IntToString());
			bindingContext.bindValue(observeText, stStopConditionObserveValue, stringToInt, intToString);

			//button
			IObservableValue observeBtnSelection = WidgetProperties.selection().observe(btnSimulationTimeStop);
			UpdateValueStrategy boolToInt = new UpdateValueStrategy();
			boolToInt.setConverter(new BoolToInt());
			UpdateValueStrategy intToBool = new UpdateValueStrategy();
			intToBool.setConverter(new IntToBool());
			bindingContext.bindValue(observeBtnSelection, stStopConditionObserveValue, boolToInt, intToBool);

			//text
			IObservableValue observeTextEnabled = WidgetProperties.enabled().observe(stTextValue);
			bindingContext.bindValue(observeTextEnabled, stStopConditionObserveValue, boolToInt, intToBool);
			
		}
		else{
			btnSimulationTimeStop.setEnabled(false);
			stTextValue.setEnabled(false);
		}
		
		if(mcStopCondition != null){

			btnMeasurementCountStop.setEnabled(true);
			mcTextValue.setEnabled(true);

			IObservableValue mcStopConditionObserve = EMFEditObservables.observeValue(alternative.getEditingDomain(), 
																	mcStopCondition, Literals.MEASUREMENT_COUNT_STOP_CONDITION__MEASUREMENT_COUNT);

			//text
			IObservableValue observeText = WidgetProperties.text(new int[]{SWT.Modify, SWT.FocusOut}).observe(mcTextValue);
			UpdateValueStrategy stringToInt = new UpdateValueStrategy();
			stringToInt.setConverter(new StringToInt());
			UpdateValueStrategy intToString = new UpdateValueStrategy();
			intToString.setConverter(new IntToString());
			bindingContext.bindValue(observeText, mcStopConditionObserve, stringToInt, intToString);

			//button
			IObservableValue observeBtnSelection = WidgetProperties.selection().observe(btnMeasurementCountStop);
			UpdateValueStrategy boolToInt = new UpdateValueStrategy();
			boolToInt.setConverter(new BoolToInt());
			UpdateValueStrategy intToBool = new UpdateValueStrategy();
			intToBool.setConverter(new IntToBool());
			bindingContext.bindValue(observeBtnSelection, mcStopConditionObserve, boolToInt, intToBool);

			//text
			IObservableValue observeTextEnabled = WidgetProperties.enabled().observe(mcTextValue);
			bindingContext.bindValue(observeTextEnabled, mcStopConditionObserve, boolToInt, intToBool);
		}
		else{
			btnMeasurementCountStop.setEnabled(false);
			mcTextValue.setEnabled(false);
		}

		return bindingContext;
	}
	
	private static class BoolToInt extends Converter{

		public BoolToInt() {
			super(Boolean.class, Integer.class);
		}

		@Override
		public Object convert(Object fromObject) {
			boolean enabled = (Boolean)fromObject;
			if(enabled){
				return 10;
			}
			else{
				return -1;
			}
		}
		
	}
	
	private static class IntToBool extends Converter{

		public IntToBool() {
			super(Integer.class, Boolean.class);
		}

		@Override
		public Object convert(Object fromObject) {
			Integer i = (Integer)fromObject;
			if(i.intValue() >= 0){
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		}
		
	}
}
