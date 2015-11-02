package eu.cloudscaleproject.env.analyser.editors.config;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationFactory;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationPackage.Literals;
import org.palladiosimulator.experimentautomation.abstractsimulation.MeasurementCountStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.SimTimeStopCondition;
import org.palladiosimulator.experimentautomation.experiments.Experiment;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.converters.IntToString;
import eu.cloudscaleproject.env.analyser.converters.StringToInt;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;

public class ConfigBasicCompositeNew extends Composite implements IRefreshable{
	
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	
	private Text stTextValue;
	private Text mcTextValue;
	
	private final SimTimeStopCondition stStopCondition = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
	private final MeasurementCountStopCondition mcStopCondition = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
	private Button btnSimulationTimeStop;
	private Button btnMeasurementCountStop;
	
	public ConfigBasicCompositeNew(final ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		
		input.executeModelChange(new Runnable() {
			
			@Override
			public void run() {
				Experiment experiment = input.getActiveExperiment();
				experiment.getStopConditions().clear();
				experiment.getStopConditions().add(stStopCondition);
			}
		});
		setLayout(new GridLayout(1, false));
		
		Composite basicConfigComposite = new Composite(this, SWT.NONE);
		basicConfigComposite.setLayout(new GridLayout(2, false));
		
		btnSimulationTimeStop = new Button(basicConfigComposite, SWT.CHECK);
		btnSimulationTimeStop.setText("Simulation time stop condition");
		
		stTextValue = new Text(basicConfigComposite, SWT.BORDER);
		GridData gd_stTextValue = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_stTextValue.widthHint = 60;
		stTextValue.setLayoutData(gd_stTextValue);
		
		btnMeasurementCountStop = new Button(basicConfigComposite, SWT.CHECK);
		btnMeasurementCountStop.setText("Measurement count stop condition");
		
		mcTextValue = new Text(basicConfigComposite, SWT.BORDER);
		GridData gd_mcTextValue = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_mcTextValue.widthHint = 60;
		mcTextValue.setLayoutData(gd_mcTextValue);
		
		Composite extendedConfigComposite = new Composite(this, SWT.NONE);
		extendedConfigComposite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		
		m_bindingContext = initDataBindings();
	}
	
	protected void initExtensions(Composite parent){
		//override in subclasses
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextStTextValueObserveWidget = WidgetProperties.text(new int[]{SWT.Modify, SWT.FocusOut}).observeDelayed(200, stTextValue);
		IObservableValue stStopConditionSimulationTimeObserveValue = EMFObservables.observeValue(stStopCondition, Literals.SIM_TIME_STOP_CONDITION__SIMULATION_TIME);
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setConverter(new StringToInt());
		UpdateValueStrategy strategy_1 = new UpdateValueStrategy();
		strategy_1.setConverter(new IntToString());
		bindingContext.bindValue(observeTextStTextValueObserveWidget, stStopConditionSimulationTimeObserveValue, strategy, strategy_1);
		//
		IObservableValue observeTextMcTextValueObserveWidget = WidgetProperties.text(new int[]{SWT.Modify, SWT.FocusOut}).observeDelayed(200, mcTextValue);
		IObservableValue mcStopConditionMeasurementCountObserveValue = EMFObservables.observeValue(mcStopCondition, Literals.MEASUREMENT_COUNT_STOP_CONDITION__MEASUREMENT_COUNT);
		UpdateValueStrategy strategy_2 = new UpdateValueStrategy();
		strategy_2.setConverter(new StringToInt());
		UpdateValueStrategy strategy_3 = new UpdateValueStrategy();
		strategy_3.setConverter(new IntToString());
		bindingContext.bindValue(observeTextMcTextValueObserveWidget, mcStopConditionMeasurementCountObserveValue, strategy_2, strategy_3);
		//
		IObservableValue observeSelectionBtnSimulationTimeStopObserveWidget = WidgetProperties.selection().observe(btnSimulationTimeStop);
		IObservableValue observeEnabledStTextValueObserveWidget = WidgetProperties.enabled().observe(stTextValue);
		bindingContext.bindValue(observeSelectionBtnSimulationTimeStopObserveWidget, observeEnabledStTextValueObserveWidget, null, null);
		//
		IObservableValue observeSelectionBtnMeasurementCountStopObserveWidget = WidgetProperties.selection().observe(btnMeasurementCountStop);
		IObservableValue observeEnabledMcTextValueObserveWidget = WidgetProperties.enabled().observe(mcTextValue);
		bindingContext.bindValue(observeSelectionBtnMeasurementCountStopObserveWidget, observeEnabledMcTextValueObserveWidget, null, null);
		//
		IObservableValue observeSelectionBtnSimulationTimeStopObserveWidget_1 = WidgetProperties.selection().observeDelayed(200, btnSimulationTimeStop);
		UpdateValueStrategy strategy_4 = new UpdateValueStrategy();
		strategy_2.setConverter(new BoolToInt());
		UpdateValueStrategy strategy_5 = new UpdateValueStrategy();
		strategy_3.setConverter(new IntToBool());
		bindingContext.bindValue(observeSelectionBtnSimulationTimeStopObserveWidget_1, stStopConditionSimulationTimeObserveValue, strategy_4, strategy_5);
		//
		IObservableValue observeSelectionBtnMeasurementCountStopObserveWidget_1 = WidgetProperties.selection().observeDelayed(200, btnMeasurementCountStop);
		UpdateValueStrategy strategy_6 = new UpdateValueStrategy();
		strategy_2.setConverter(new BoolToInt());
		UpdateValueStrategy strategy_7 = new UpdateValueStrategy();
		strategy_3.setConverter(new IntToBool());
		bindingContext.bindValue(observeSelectionBtnMeasurementCountStopObserveWidget_1, mcStopConditionMeasurementCountObserveValue, strategy_6, strategy_7);
		//
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
				return null;
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
