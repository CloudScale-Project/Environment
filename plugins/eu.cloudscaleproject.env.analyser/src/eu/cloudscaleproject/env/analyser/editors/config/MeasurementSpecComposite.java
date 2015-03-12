package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryFactory;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage.Literals;
import org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum;
import org.palladiosimulator.simulizar.monitorrepository.TemporalCharacterization;

public class MeasurementSpecComposite extends Composite{
	
	private Text textFirst;
	private Text textSecond;
	
	private ControlDecorationSupport decorationFirst;
	private ControlDecorationSupport decorationSecond;
	
	private Binding interBinding;
	private Binding dInterBinding;
	private Binding timeStartBinding;
	private Binding timeStopBinding;
	
	private DataBindingContext bindingContext = null;
	
	private final EditingDomain ed;
	private final MeasurementSpecification ms;
	
	private Button btnInterval;
	private Button btnDInterval;
	private Button btnTimeFrame;
	
	private Label lblFirst;
	private Label lblSecond;
	
	private Composite valuesComposite;
	private Composite typeComposite;
	
	private List<MetricDescription> metricDescList = new ArrayList<MetricDescription>();
	private Combo comboMetricDesc;
	private ComboViewer comboViewer;
	private Combo comboStatType;
	private ComboViewer comboViewer_1;
	private Composite composite;
	
	public MeasurementSpecComposite(EditingDomain ed, MeasurementSpecification ms, Composite parent, int style) {
		super(parent, style);
		
		assert(ms != null);
		assert(ed != null);
		
		this.ed = ed;
		this.ms = ms;
		
		setLayout(new GridLayout(1, true));
		
		typeComposite = new Composite(this, SWT.NONE);
		typeComposite.setLayout(new GridLayout(2, false));
		typeComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Resource res = ed.getResourceSet().getResource(URI.createURI("pathmap://METRIC_SPEC_MODELS/models/commonMetrics.metricspec"), true);
		Iterator<EObject> iter = res.getAllContents();
		while(iter.hasNext()){
			EObject obj = iter.next();
			if(obj instanceof MetricDescription){
				MetricDescription md = (MetricDescription)obj;
				metricDescList.add(md);
			}
		}
		
		comboViewer = new ComboViewer(typeComposite, SWT.NONE);
		comboMetricDesc = comboViewer.getCombo();
		GridData gd_comboMetricDesc = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_comboMetricDesc.widthHint = 150;
		comboMetricDesc.setLayoutData(gd_comboMetricDesc);
		
		comboViewer_1 = new ComboViewer(typeComposite, SWT.NONE);
		comboStatType = comboViewer_1.getCombo();
		GridData gd_comboStatType = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_comboStatType.widthHint = 150;
		comboStatType.setLayoutData(gd_comboStatType);
		
		composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		composite.setLayout(new GridLayout(3, false));
		
		btnDInterval = new Button(composite, SWT.RADIO);
		btnDInterval.setText("Delayed interval");
		
		btnTimeFrame = new Button(composite, SWT.RADIO);
		btnTimeFrame.setText("Time frame");
		
		btnInterval = new Button(composite, SWT.RADIO);
		btnInterval.setText("Interval");
		
		valuesComposite = new Composite(this, SWT.NONE);
		valuesComposite.setLayout(new GridLayout(2, false));
		valuesComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		lblFirst = new Label(valuesComposite, SWT.NONE);
		GridData gd_lblFirst = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblFirst.widthHint = 50;
		lblFirst.setLayoutData(gd_lblFirst);
		lblFirst.setText("Start:");
		
		textFirst = new Text(valuesComposite, SWT.BORDER);
		GridData gd_textFirst = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textFirst.widthHint = 100;
		textFirst.setLayoutData(gd_textFirst);
		
		lblSecond = new Label(valuesComposite, SWT.NONE);
		GridData gd_lblSecond = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblSecond.widthHint = 50;
		lblSecond.setLayoutData(gd_lblSecond);
		lblSecond.setText("Stop:");
		
		textSecond = new Text(valuesComposite, SWT.BORDER);
		GridData gd_textSecond = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textSecond.widthHint = 100;
		textSecond.setLayoutData(gd_textSecond);
		
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				if(bindingContext != null){
					bindingContext.dispose();
					bindingContext = null;
				}
				
			}
		});
		
		initBindings();
		
	}
	
	private void initBindings(){
		if(bindingContext != null){
			bindingContext.dispose();
			bindingContext = null;
		}
		bindingContext = new DataBindingContext();
		
		//bind combo metric description
		{
			ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
			IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), MetricDescription.class, "name");
			
			comboViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
			comboViewer.setContentProvider(listContentProvider);
						IObservableList selfList = Properties.selfList(MetricDescription.class).observe(metricDescList);
			comboViewer.setInput(selfList);
			
			IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboViewer);
			IObservableValue msMetricDescriptionObserveValue = EMFEditObservables.observeValue(ed, ms, Literals.MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION);
			bindingContext.bindValue(observeSingleSelectionComboViewer, msMetricDescriptionObserveValue, null, null);
		}
		//bind combo metric description
		{
			ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
			IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), StatisticalCharacterizationEnum.class, "name");
			
			comboViewer_1.setLabelProvider(new ObservableMapLabelProvider(observeMap));
			comboViewer_1.setContentProvider(listContentProvider);
						IObservableList selfList = Properties.selfList(StatisticalCharacterizationEnum.class).observe(
								Arrays.asList(StatisticalCharacterizationEnum.values()));
			comboViewer_1.setInput(selfList);
			
			IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboViewer_1);
			IObservableValue statTypeObserveValue = EMFEditObservables.observeValue(ed, ms, Literals.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION);
			bindingContext.bindValue(observeSingleSelectionComboViewer, statTypeObserveValue, null, null);
		}
		
		//bind radio buttons
		{
			SelectObservableValue intervalObservable = new SelectObservableValue(TemporalCharacterization.class);
			
			IObservableValue btnIntervalObserveSelection = SWTObservables  
		            .observeSelection(btnInterval);  
			intervalObservable.addOption(MonitorrepositoryFactory.eINSTANCE.createIntervall(), btnIntervalObserveSelection);
			
			IObservableValue btnDIntervalObserveSelection = SWTObservables  
		            .observeSelection(btnDInterval);  
			intervalObservable.addOption(MonitorrepositoryFactory.eINSTANCE.createDelayedIntervall(), btnDIntervalObserveSelection);
			
			IObservableValue btnTimeFrameObserveSelection = SWTObservables  
		            .observeSelection(btnTimeFrame);  
			intervalObservable.addOption(MonitorrepositoryFactory.eINSTANCE.createTimeFrame(), btnTimeFrameObserveSelection);
			
			bindingContext.bindValue(intervalObservable, 
					EMFEditProperties.value(ed, MonitorrepositoryPackage.Literals.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION).observe(ms));
			
			intervalObservable.addChangeListener(new IChangeListener() {
				
				@Override
				public void handleChange(ChangeEvent event) {
					
					if(valuesComposite.isDisposed()){
						return;
					}
					
					if(decorationFirst != null){
						decorationFirst.dispose();
						decorationFirst = null;
					}
					if(decorationSecond != null){
						decorationSecond.dispose();
						decorationSecond = null;
					}
					
					if(btnInterval.getSelection()){
						lblFirst.setText("Interval:");
						lblSecond.setVisible(false);
						textSecond.setVisible(false);
						decorationFirst = ControlDecorationSupport.create(interBinding, SWT.TOP | SWT.LEFT);
					}
					else if(btnDInterval.getSelection()){
						lblFirst.setText("Interval:");
						lblSecond.setText("Delay:");

						lblSecond.setVisible(true);
						textSecond.setVisible(true);
						
						decorationFirst = ControlDecorationSupport.create(interBinding, SWT.TOP | SWT.LEFT);
						decorationSecond = ControlDecorationSupport.create(dInterBinding, SWT.TOP | SWT.LEFT);
					}
					else if(btnTimeFrame.getSelection()){
						lblFirst.setText("Start:");
						lblSecond.setText("Stop:");

						lblSecond.setVisible(true);
						textSecond.setVisible(true);
						
						decorationFirst = ControlDecorationSupport.create(timeStartBinding, SWT.TOP | SWT.LEFT);
						decorationSecond = ControlDecorationSupport.create(timeStopBinding, SWT.TOP | SWT.LEFT);
					}
					
					MeasurementSpecComposite.this.valuesComposite.update();
					MeasurementSpecComposite.this.valuesComposite.layout();
					MeasurementSpecComposite.this.valuesComposite.redraw();
				}
			});
		}
		
		//bind textbox
		{	
			ISWTObservableValue textFirstObservable = WidgetProperties.text(SWT.Modify).observe(textFirst);
			ISWTObservableValue textSecondObservable = WidgetProperties.text(SWT.Modify).observe(textSecond);
			
			IEMFEditValueProperty intervalIntervalProp = EMFEditProperties.value(ed, FeaturePath.fromList(
					MonitorrepositoryPackage.Literals.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, 
					MonitorrepositoryPackage.Literals.INTERVALL__INTERVALL));
			
			IEMFEditValueProperty dIntervalDelayProp = EMFEditProperties.value(ed, FeaturePath.fromList(
					MonitorrepositoryPackage.Literals.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, 
					MonitorrepositoryPackage.Literals.DELAYED_INTERVALL__DELAY));
			
			IEMFEditValueProperty timeFrameStartProp = EMFEditProperties.value(ed, FeaturePath.fromList(
					MonitorrepositoryPackage.Literals.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, 
					MonitorrepositoryPackage.Literals.TIME_FRAME__START));
			
			IEMFEditValueProperty timeFrameStopProp = EMFEditProperties.value(ed, FeaturePath.fromList(
					MonitorrepositoryPackage.Literals.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, 
					MonitorrepositoryPackage.Literals.TIME_FRAME__STOP));
			
			UpdateValueStrategy t2mStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
			t2mStrategy.setConverter(StringToNumberConverter.toDouble(true));
			
			UpdateValueStrategy m2tStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
			m2tStrategy.setConverter(NumberToStringConverter.fromDouble(true));
			
			interBinding = bindingContext.bindValue(textFirstObservable, intervalIntervalProp.observe(ms), t2mStrategy, m2tStrategy);
			dInterBinding = bindingContext.bindValue(textSecondObservable, dIntervalDelayProp.observe(ms), t2mStrategy, m2tStrategy);
			timeStartBinding = bindingContext.bindValue(textFirstObservable, timeFrameStartProp.observe(ms), t2mStrategy, m2tStrategy);
			timeStopBinding = bindingContext.bindValue(textSecondObservable, timeFrameStopProp.observe(ms), t2mStrategy, m2tStrategy);
		}
	}

	public MeasurementSpecification getMeasurementSpecification(){
		return this.ms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ms == null) ? 0 : ms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeasurementSpecComposite other = (MeasurementSpecComposite) obj;
		if (ms == null) {
			if (other.ms != null)
				return false;
		} else if (!ms.equals(other.ms))
			return false;
		return true;
	}

}
