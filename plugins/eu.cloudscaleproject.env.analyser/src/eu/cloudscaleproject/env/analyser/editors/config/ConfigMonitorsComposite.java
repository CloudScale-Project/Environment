package eu.cloudscaleproject.env.analyser.editors.config;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;

public class ConfigMonitorsComposite extends Composite{
	
	private final ConfAlternative alternative;
	
	//private final List<MonitorWrapper> monitorWrappers = new ArrayList<MonitorWrapper>();
	//private final List<MonitorWrapperComposite> monitorWrapperComposites = new ArrayList<MonitorWrapperComposite>();

	public ConfigMonitorsComposite(ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		this.alternative = input;
		
		setLayout(new GridLayout(1, true));
		
		for(MonitorRepository mr : alternative.getMonitorRepositories()){
		
			if(!mr.getMonitors().isEmpty()){
				Monitor m = mr.getMonitors().get(0);
				ConfigMonitorComposite cmc = new ConfigMonitorComposite(input.getEditingDomain(), m, this, SWT.NONE);
				cmc.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
			}
			
			/*
			for(Monitor m : mr.getMonitors()){
				MonitorWrapper mw = findWrapper(m);
				if(mw != null){
					mw.add(m);
				}
				else{
					MonitorWrapper mwNew = new MonitorWrapper(alternative);
					mwNew.add(m);

					//MonitorWrapperComposite composite = new MonitorWrapperComposite(mwNew, this, SWT.NONE);
					
					monitorWrappers.add(mwNew);
					//monitorWrapperComposites.add(composite);
				}
			}
			*/
		}
		
		
		/*
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				for(MonitorWrapper mw : monitorWrappers){
					mw.dispose();
				}
				monitorWrappers.clear();
				monitorWrapperComposites.clear();
			}
		});
		*/
	}
	
	/*
	private MonitorWrapper findWrapper(Monitor monitor){
		for(MonitorWrapper mw : monitorWrappers){
			if(mw.canWrapp(monitor)){
				return mw;
			}
		}
		return null;
	}
	*/
	
	/*
	private void initBindings(){
		
		if(bindingContext != null){
			bindingContext.dispose();
		}
		bindingContext = new DataBindingContext();
		
		UpdateValueStrategy t2mStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
		t2mStrategy.setConverter(StringToNumberConverter.toInteger(true));
		
		UpdateValueStrategy m2tStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
		m2tStrategy.setConverter(NumberToStringConverter.fromInteger(true));
		
		IObservableValue average = EMFEditProperties.list(alternative.getEditingDomain(), )
		
		IObservableValue simTimeObs = EMFEditProperties.value(alternative.getEditingDomain(),
				AbstractsimulationPackage.Literals.SIM_TIME_STOP_CONDITION__SIMULATION_TIME).observe(simTime);
		
		Binding simTimeBind = bindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(textSimTime),
		        simTimeObs, t2mStrategy, m2tStrategy);
		
		
		ControlDecorationSupport.create(simTimeBind, SWT.TOP | SWT.LEFT);
	}
	*/
	
	/*
	private static class MonitorWrapper extends MonitorImpl{
				
		private DataBindingContext bindingContext = new DataBindingContext();
		private final ConfAlternative alt;
		
		private final Map<Monitor, Binding> bindings = new HashMap<Monitor, Binding>();
		private final List<Monitor> monitors  = new ArrayList<>();
		
		private final IObservableList observable;
		
		public MonitorWrapper(ConfAlternative alternative) {
			alt = alternative;
			
			IEMFListProperty propSample = EMFProperties.list(
					MonitorrepositoryPackage.Literals.MONITOR__MEASUREMENT_SPECIFICATION);
			observable = propSample.observe(this);
		}
		
		public void add(Monitor m){
			
			IEMFEditListProperty propSpecList = EMFEditProperties.list(alt.getEditingDomain(),
					MonitorrepositoryPackage.Literals.MONITOR__MEASUREMENT_SPECIFICATION);
			
			
			IEMFEditValueProperty propSpecMetD = EMFEditProperties.value(alt.getEditingDomain(),
					MonitorrepositoryPackage.Literals.MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION);
			
			IEMFEditValueProperty propSpecStatChar = EMFEditProperties.value(alt.getEditingDomain(),
					MonitorrepositoryPackage.Literals.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION);
			
			IObservableList obsSpecList = propSpecList.observe(m);
			//IObservableList obsSpecMetD = propSpecMetD.observeDetail(obsSpecList);

			bindings.put(m, bindingContext.bindList(observable, obsSpecList,
					new UpdateListStrategy(UpdateListStrategy.POLICY_UPDATE), 
					new UpdateListStrategy(UpdateListStrategy.POLICY_ON_REQUEST)));
			monitors.add(m);
			
			bindingContext.updateTargets();
		}
		
		public void remove(Monitor m){
			bindingContext.removeBinding(bindings.get(m));
			monitors.remove(m);
		}
				
		public boolean canWrapp(Monitor monitor){
			Monitor monitorExample = monitors.get(0);
		    EqualityHelper equalityHelper = new EqualityHelper()
		    {
		    	@Override
		    	protected boolean haveEqualAttribute(EObject eObject1, EObject eObject2, EAttribute c) {
		    		if(c.getFeatureID() == IdentifierPackage.IDENTIFIER__ID){
		    			return true;
		    		}
		    		return super.haveEqualAttribute(eObject1, eObject2, c);
		    	}
		    };

			return equalityHelper.equals((List<EObject>)(List<? extends EObject>)monitorExample.getMeasurementSpecification(), 
					(List<EObject>)(List<? extends EObject>)monitor.getMeasurementSpecification());
		}
		
		public void dispose(){
			if(bindingContext != null){
				bindingContext.dispose();
				bindingContext = null;
			}
		}
	}
	*/
}
