package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.domain.EditingDomain;
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
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.palladiosimulator.servicelevelobjective.Threshold;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.Converters;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;

public class ConfigSLOComposite extends Composite{
	
	private final ConfAlternative alternative;
	private final EditingDomain editingDomain;
	
	private final EObjectWrapper sloWrapper;
	private final ServiceLevelObjective slo;
	private final Composite composite;
	private final ComboViewer comboMeasurementSpecViewer;
	
	private DataBindingContext bindingContext = null;
		
	private Text textUpperBound;
	private Text textLowerBound;
	
	private Button btnUpperBound;
	private Button btnLowerBound;
	
	private java.util.List<MetricDescription> measuringSpecList = new ArrayList<MetricDescription>();
	
	public ConfigSLOComposite(ConfAlternative alt, final EObjectWrapper sloWrapper, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = alt;
		this.editingDomain = alt.getEditingDomain();
		this.sloWrapper = sloWrapper;
		this.slo = (ServiceLevelObjective)sloWrapper.getMaster();
		
		setLayout(new GridLayout(1, false));
		
		Label lblFolder = new Label(this, SWT.NONE);
		lblFolder.setText("Service level objective specifications:");
		
		composite = new Composite(this, SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		btnUpperBound = new Button(composite, SWT.CHECK);
		btnUpperBound.setBounds(10, 35, 93, 19);
		btnUpperBound.setText("Upper bound:");
		
		textUpperBound = new Text(composite, SWT.BORDER);
		textUpperBound.setBounds(109, 35, 123, 19);
		
		btnLowerBound = new Button(composite, SWT.CHECK);
		btnLowerBound.setBounds(10, 60, 93, 19);
		btnLowerBound.setText("Lower bound:");
		
		textLowerBound = new Text(composite, SWT.BORDER);
		textLowerBound.setBounds(109, 60, 123, 19);
		
		comboMeasurementSpecViewer = new ComboViewer(composite, SWT.NONE);
		Combo comboMeasuremntSpec = comboMeasurementSpecViewer.getCombo();
		comboMeasuremntSpec.setBounds(10, 10, 222, 19);
		
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				if(bindingContext != null){
					bindingContext.dispose();
					bindingContext = null;
				}				
			}
		});
		
		update();
		initBindings();
	}
	
	public String getSLOName(){
		String entityName = slo.getName() == null ? "No name" : slo.getName();
		return entityName;
	}
	
	public void initBindings(){
		
		if(bindingContext != null){
			bindingContext.dispose();
		}
		bindingContext = new DataBindingContext();
		
		//bind combo metric description
		{
			ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
			IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), MeasurementSpecification.class, "name");
			
			comboMeasurementSpecViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
			comboMeasurementSpecViewer.setContentProvider(listContentProvider);
						
			IObservableList selfList = Properties.selfList(MeasurementSpecification.class).observe(measuringSpecList);
			comboMeasurementSpecViewer.setInput(selfList);
			
			IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboMeasurementSpecViewer);
			IObservableValue measurementSpecObserveValue = EMFEditObservables.observeValue(editingDomain, sloWrapper.getMaster(), 
					ServicelevelObjectivePackage.Literals.SERVICE_LEVEL_OBJECTIVE__MEASUREMENT_SPECIFICATION);
			bindingContext.bindValue(observeSingleSelectionComboViewer, measurementSpecObserveValue, null, null);
		}
		
		Binding upperThresholdBinding = null;
		Binding lowerThresholdBinding = null;
		
		//bind upper bound checkbox
		{
			upperThresholdBinding = bindCreateThresholdCheck(btnUpperBound, FeaturePath.fromList(
					ServicelevelObjectivePackage.Literals.SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD), 
					ServicelevelObjectiveFactory.eINSTANCE.createHardThreshold());
		}
		
		//bind lower bound checkbox
		{
			lowerThresholdBinding = bindCreateThresholdCheck(btnLowerBound, FeaturePath.fromList(
					ServicelevelObjectivePackage.Literals.SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD), 
					ServicelevelObjectiveFactory.eINSTANCE.createHardThreshold());
		}
		
		//bind upper bound
		{	
			bindThresholdMeasure(textUpperBound, FeaturePath.fromList(
							ServicelevelObjectivePackage.Literals.SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD,
							ServicelevelObjectivePackage.Literals.THRESHOLD__THRESHOLD_LIMIT
					));
		}
		
		//bind lower bound
		{
			bindThresholdMeasure(textLowerBound, FeaturePath.fromList(
					ServicelevelObjectivePackage.Literals.SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD,
					ServicelevelObjectivePackage.Literals.THRESHOLD__THRESHOLD_LIMIT
			));
		}
				
		//bind GUI components		
		IObservableValue btnUpperCheckObs = (IObservableValue)upperThresholdBinding.getTarget();
		IObservableValue textUpperObs = WidgetProperties.enabled().observe(textUpperBound);
		bindingContext.bindValue(textUpperObs, btnUpperCheckObs);
		
		IObservableValue btnLowerCheckObs = (IObservableValue)lowerThresholdBinding.getTarget();
		IObservableValue textLowerObs = WidgetProperties.enabled().observe(textLowerBound);
		bindingContext.bindValue(textLowerObs, btnLowerCheckObs);
		
		bindingContext.updateTargets();
	}
	
	private Binding bindCreateThresholdCheck(Button button, FeaturePath path, Threshold tr){
		
		IObservableValue thresholdObserve = EMFEditProperties.value(editingDomain, path).observe(slo);
		IObservableValue textObserve = WidgetProperties.selection().observe(button);
		
		UpdateValueStrategy t2mStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
		t2mStrategy.setConverter(Converters.getBoolEObjectConverter(tr)[0]);
		UpdateValueStrategy m2tStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
		m2tStrategy.setConverter(Converters.getBoolEObjectConverter(tr)[1]);
		return bindingContext.bindValue(textObserve, thresholdObserve, t2mStrategy, m2tStrategy);
	}
	
	private Binding bindThresholdMeasure(Text text, FeaturePath path){
		
		IObservableValue thresholdMeasureObserve = EMFEditProperties.value(editingDomain, path).observe(slo);
		IObservableValue textObserve = WidgetProperties.text(SWT.FocusOut).observe(text);
		
		UpdateValueStrategy t2mStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
		t2mStrategy.setConverter(Converters.getStringMeasureSecondsConverter()[0]);
		UpdateValueStrategy m2tStrategy = new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE);
		m2tStrategy.setConverter(Converters.getStringMeasureSecondsConverter()[1]);
		return bindingContext.bindValue(textObserve, thresholdMeasureObserve, t2mStrategy, m2tStrategy);
	}
	
	public void update(){
		
		ListDiff diff = Diffs.computeListDiff(measuringSpecList, alternative.getMeasurementSpecifications());
		diff.applyTo(measuringSpecList);
	}
}
