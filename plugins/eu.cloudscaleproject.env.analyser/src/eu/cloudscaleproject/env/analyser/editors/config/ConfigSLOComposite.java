package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
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
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;

public class ConfigSLOComposite extends Composite{
	
	private final EditingDomain editingDomain;
	private final EObjectWrapper sloWrapper;

	private final ListViewer listViewer;
	private final Composite composite;
	
	private DataBindingContext bindingContext = null;
	
	private final ArrayList<MeasuringPoint> measuringPoints = new ArrayList<MeasuringPoint>();
	private Text textUpperBound;
	private Text textLowerBound;
	
	public ConfigSLOComposite(ConfAlternative alt, final EObjectWrapper sloWrapper, Composite parent, int style) {
		super(parent, style);
		
		this.editingDomain = alt.getEditingDomain();
		this.sloWrapper = sloWrapper;
		
		setLayout(new GridLayout(2, false));
		
		Label lblFolder = new Label(this, SWT.NONE);
		lblFolder.setText("Service level objective specifications:");
		Label lblMPoints = new Label(this, SWT.NONE);
		lblMPoints.setText("Measuring points:");
		
		composite = new Composite(this, SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Button btnUpperBound = new Button(composite, SWT.CHECK);
		btnUpperBound.setBounds(10, 35, 93, 19);
		btnUpperBound.setText("Upper bound:");
		
		textUpperBound = new Text(composite, SWT.BORDER);
		textUpperBound.setBounds(109, 35, 123, 19);
		
		Button btnLowerBound = new Button(composite, SWT.CHECK);
		btnLowerBound.setBounds(10, 60, 93, 19);
		btnLowerBound.setText("Lower bound:");
		
		textLowerBound = new Text(composite, SWT.BORDER);
		textLowerBound.setBounds(109, 60, 123, 19);
		
		ComboViewer comboViewer = new ComboViewer(composite, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		combo.setBounds(10, 10, 222, 19);
		
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
		
		update();
		initBindings();
		
	}
	
	public String getMonitorName(){
		Monitor monitor = (Monitor)sloWrapper.getMaster();
		String entityName = monitor.getEntityName();
		//String description = monitor.getMeasurementSpecifications().isEmpty() != null ? monitor.getMeasurementSpecifications().get(0).getMetricDescription()
		return entityName;
	}
	
	public void initBindings(){
		
		if(bindingContext != null){
			bindingContext.dispose();
		}
		bindingContext = new DataBindingContext();
		
		//bind combo metric description
		/*
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
		*/
		IObservableList mpObs = Properties.selfList(MeasuringPoint.class).observe(measuringPoints);
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		listViewer.setContentProvider(listContentProvider);
		listViewer.setInput(mpObs);
		
		bindingContext.updateTargets();
	}
	
	public void update(){
		
		measuringPoints.clear();
		
		for(EObject obj : sloWrapper.getSlaves()){
			if(obj instanceof ServiceLevelObjective){
				MeasuringPoint mp = ((ServiceLevelObjective)obj).getMeasuringPoint();
				if(mp != null && !measuringPoints.contains(mp)){
					measuringPoints.add(mp);
				}
			}
		}
	}
}
