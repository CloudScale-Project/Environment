package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.palladiosimulator.servicelevelobjective.Threshold;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.Converters;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;

public class ConfigSLOComposite extends Composite{
	
	private final EditingDomain editingDomain;
	
	private final EObjectWrapper sloWrapper;
	private final ServiceLevelObjective slo;

	private final ListViewer listViewer;
	private final Composite composite;
	private final ComboViewer comboViewer;
	
	private DataBindingContext bindingContext = null;
	
	private final ArrayList<MeasuringPoint> measuringPoints = new ArrayList<MeasuringPoint>();
	
	private Text textUpperBound;
	private Text textLowerBound;
	
	private Button btnUpperBound;
	private Button btnLowerBound;
	
	private java.util.List<MetricDescription> metricDescList = new ArrayList<MetricDescription>();
	
	public ConfigSLOComposite(ConfAlternative alt, final EObjectWrapper sloWrapper, Composite parent, int style) {
		super(parent, style);
		
		this.editingDomain = alt.getEditingDomain();
		this.sloWrapper = sloWrapper;
		this.slo = (ServiceLevelObjective)sloWrapper.getMaster();
		
		setLayout(new GridLayout(2, false));
		
		Label lblFolder = new Label(this, SWT.NONE);
		lblFolder.setText("Service level objective specifications:");
		Label lblMPoints = new Label(this, SWT.NONE);
		lblMPoints.setText("Measuring points:");
		
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
		
		comboViewer = new ComboViewer(composite, SWT.NONE);
		Combo comboMetricDesc = comboViewer.getCombo();
		comboMetricDesc.setBounds(10, 10, 222, 19);
		
		listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_list.widthHint = 220;
		list.setLayoutData(gd_list);
		listViewer.setLabelProvider(new AdapterFactoryLabelProvider(alt.getAdapterFactory()));
		
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				if(bindingContext != null){
					bindingContext.dispose();
					bindingContext = null;
				}				
			}
		});
		
		Resource res = editingDomain.getResourceSet().getResource(URI.createURI("pathmap://METRIC_SPEC_MODELS/models/commonMetrics.metricspec"), true);
		Iterator<EObject> iter = res.getAllContents();
		while(iter.hasNext()){
			EObject obj = iter.next();
			if(obj instanceof MetricDescription){
				MetricDescription md = (MetricDescription)obj;
				metricDescList.add(md);
			}
		}
		
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
			IObservableMap observeMap = PojoObservables.observeMap(listContentProvider.getKnownElements(), MetricDescription.class, "name");
			
			comboViewer.setLabelProvider(new ObservableMapLabelProvider(observeMap));
			comboViewer.setContentProvider(listContentProvider);
						IObservableList selfList = Properties.selfList(MetricDescription.class).observe(metricDescList);
			comboViewer.setInput(selfList);
			
			IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboViewer);
			IObservableValue msMetricDescriptionObserveValue = EMFEditObservables.observeValue(editingDomain, sloWrapper.getMaster(), 
					ServicelevelObjectivePackage.Literals.SERVICE_LEVEL_OBJECTIVE__METRIC_DESCRIPTION);
			bindingContext.bindValue(observeSingleSelectionComboViewer, msMetricDescriptionObserveValue, null, null);
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
		
		IObservableList mpObs = Properties.selfList(MeasuringPoint.class).observe(measuringPoints);
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		listViewer.setContentProvider(listContentProvider);
		listViewer.setInput(mpObs);
				
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
		
		measuringPoints.clear();
		
		MeasuringPoint mp = ((ServiceLevelObjective)sloWrapper.getMaster()).getMeasuringPoint();
		if(mp != null && !measuringPoints.contains(mp)){
			measuringPoints.add(mp);
		}
		
		for(EObject obj : sloWrapper.getSlaves()){
			if(obj instanceof ServiceLevelObjective){
				mp = ((ServiceLevelObjective)obj).getMeasuringPoint();
				if(mp != null && !measuringPoints.contains(mp)){
					measuringPoints.add(mp);
				}
			}
		}
	}
}
