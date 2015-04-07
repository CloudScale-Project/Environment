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
	private final ComboViewer comboMeasurementSpecViewer;
	
	private DataBindingContext bindingContext = null;
		
	private Text textUpperBound;
	private Text textLowerBound;
	
	private Button btnUpperBound;
	private Button btnLowerBound;
	
	private java.util.List<MetricDescription> measuringSpecList = new ArrayList<MetricDescription>();
	private Label lblNewLabel;
	
	public ConfigSLOComposite(ConfAlternative alt, final EObjectWrapper sloWrapper, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = alt;
		this.editingDomain = alt.getEditingDomain();
		this.sloWrapper = sloWrapper;
		this.slo = (ServiceLevelObjective)sloWrapper.getMaster();
		
		setLayout(new GridLayout(2, false));
		
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("Measurement specification:");
		
		comboMeasurementSpecViewer = new ComboViewer(this, SWT.NONE);
		Combo comboMeasuremntSpec = comboMeasurementSpecViewer.getCombo();
		GridData gd_comboMeasuremntSpec = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_comboMeasuremntSpec.widthHint = 320;
		comboMeasuremntSpec.setLayoutData(gd_comboMeasuremntSpec);
		
		btnUpperBound = new Button(this, SWT.CHECK);
		btnUpperBound.setText("Upper bound:");
		
		textUpperBound = new Text(this, SWT.BORDER);
		GridData gd_textUpperBound = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textUpperBound.widthHint = 100;
		textUpperBound.setLayoutData(gd_textUpperBound);
		
		btnLowerBound = new Button(this, SWT.CHECK);
		btnLowerBound.setText("Lower bound:");
		
		textLowerBound = new Text(this, SWT.BORDER);
		GridData gd_textLowerBound = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textLowerBound.widthHint = 100;
		textLowerBound.setLayoutData(gd_textLowerBound);
		
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
