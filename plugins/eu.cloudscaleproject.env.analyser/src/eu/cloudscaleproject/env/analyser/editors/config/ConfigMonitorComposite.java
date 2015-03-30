package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryFactory;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;
import eu.cloudscaleproject.env.common.ui.util.ControlTabItemListProperty;

public class ConfigMonitorComposite extends Composite{
	
	private final EditingDomain editingDomain;
	private final EObjectWrapper monitorWrapper;

	private final ListViewer listViewer;
	private final CTabFolder folder;
	
	private DataBindingContext bindingContext = null;
	
	private final ArrayList<MeasuringPoint> measuringPoints = new ArrayList<MeasuringPoint>();
	
	public ConfigMonitorComposite(ConfAlternative alt, final EObjectWrapper monitorWrapper, Composite parent, int style) {
		super(parent, style);
		
		this.editingDomain = alt.getEditingDomain();
		this.monitorWrapper = monitorWrapper;
		
		setLayout(new GridLayout(2, false));
		
		Label lblFolder = new Label(this, SWT.NONE);
		lblFolder.setText("Measuring specifications:");
		Label lblMPoints = new Label(this, SWT.NONE);
		lblMPoints.setText("Measuring points:");
		
		folder = new CTabFolder(this, SWT.BORDER);
		folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_list.widthHint = 220;
		list.setLayoutData(gd_list);
		listViewer.setLabelProvider(new AdapterFactoryLabelProvider(alt.getAdapterFactory()));
		
		Composite folderControlsComposite = new Composite(folder, SWT.NONE);
		folderControlsComposite.setLayout(new FillLayout());
		
		Button btnAddNew = new Button(folderControlsComposite, SWT.NONE);
		btnAddNew.setText("+");
		btnAddNew.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MeasurementSpecification ms = MonitorrepositoryFactory.eINSTANCE.createMeasurementSpecification();
				Monitor masterMonitor = (Monitor)monitorWrapper.getMaster();
				masterMonitor.getMeasurementSpecifications().add(ms);
			};
		});
		
		/*
		Composite expand = new Composite(folderControlsComposite, SWT.NONE);
		GridData gd = new GridData(SWT.FILL, SWT.TOP, true, false);
		gd.heightHint = 0;
		expand.setLayoutData(gd);
		*/
		
		Button btnRemove = new Button(folderControlsComposite, SWT.NONE);
		btnRemove.setText("-");
		btnRemove.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int selection = folder.getSelectionIndex();
				if(selection >= 0){
					Monitor masterMonitor = (Monitor)monitorWrapper.getMaster();
					masterMonitor.getMeasurementSpecifications().remove(selection);
				}
			};
		});
		folder.setTopRight(folderControlsComposite, SWT.RIGHT);
		
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
		
		if(folder.getItemCount() > 0 && folder.getSelectionIndex() == -1){
			folder.setSelection(0);
		}
	}
	
	public String getMonitorName(){
		Monitor monitor = (Monitor)monitorWrapper.getMaster();
		String entityName = monitor.getEntityName();
		//String description = monitor.getMeasurementSpecifications().isEmpty() != null ? monitor.getMeasurementSpecifications().get(0).getMetricDescription()
		return entityName;
	}
	
	public void initBindings(){
		
		if(bindingContext != null){
			bindingContext.dispose();
		}
		bindingContext = new DataBindingContext();
		
		IObservableList obsMeasurSpec = EMFProperties.list(
				MonitorrepositoryPackage.Literals.MONITOR__MEASUREMENT_SPECIFICATIONS).observe(monitorWrapper.getMaster());
		
		UpdateListStrategy i2mStrategy = new UpdateListStrategy(UpdateListStrategy.POLICY_UPDATE);
		i2mStrategy.setConverter(new IConverter() {
			
			@Override
			public Object getToType() {
				return MeasurementSpecification.class;
			}
			
			@Override
			public Object getFromType() {
				return Composite.class;
			}
			
			@Override
			public Object convert(Object fromObject) {
				MeasurementSpecComposite ms = (MeasurementSpecComposite)fromObject;
				return ms.getMeasurementSpecification();
			}
		});
		
		UpdateListStrategy m2iStrategy = new UpdateListStrategy(UpdateListStrategy.POLICY_UPDATE);
		m2iStrategy.setConverter(new IConverter() {
			
			@Override
			public Object getToType() {
				return Composite.class;
			}
			
			@Override
			public Object getFromType() {
				return MeasurementSpecification.class;
			}
			
			@Override
			public Object convert(Object fromObject) {
				if(fromObject == null){
					return null;
				}
				for(CTabItem item : folder.getItems()){
					if(fromObject.equals(((MeasurementSpecComposite)item.getControl()).getMeasurementSpecification())){
						return item.getControl();
					}
				}
				return createMeasurementSpecComposite((MeasurementSpecification)fromObject);
			}
		});
		
		ControlTabItemListProperty controlListProp = new ControlTabItemListProperty();
		IObservableList obsList = controlListProp.observe(folder);
				
		bindingContext.bindList(obsList, obsMeasurSpec, i2mStrategy, m2iStrategy);
		
		/*
		IEMFEditValueProperty mpProp = EMFEditProperties.value(editingDomain, MonitorrepositoryPackage.Literals.MONITOR__MEASURING_POINT);
		IObservableList wrappedMonitorsObs = PojoObservables.observeList(monitorWrapper, "slaves", Monitor.class);
		IObservableList mpObs = mpProp.observeDetail(wrappedMonitorsObs);
		*/
		
		IObservableList mpObs = Properties.selfList(MeasuringPoint.class).observe(measuringPoints);
		//IObservableList mpObs = PojoObservables.observeList(this, "measuringPoints", MeasuringPoint.class);
		
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		listViewer.setContentProvider(listContentProvider);
		listViewer.setInput(mpObs);
		
		bindingContext.updateTargets();
	}
	
	public void update(){
		
		measuringPoints.clear();
		
		MeasuringPoint mp = ((Monitor)monitorWrapper.getMaster()).getMeasuringPoint();
		if(mp != null && !measuringPoints.contains(mp)){
			measuringPoints.add(mp);
		}
		
		for(EObject obj : monitorWrapper.getSlaves()){
			mp = ((Monitor)obj).getMeasuringPoint();
			if(mp != null && !measuringPoints.contains(mp)){
				measuringPoints.add(mp);
			}
		}
	}
	
	private MeasurementSpecComposite createMeasurementSpecComposite(MeasurementSpecification ms){
		MeasurementSpecComposite msc = new MeasurementSpecComposite(editingDomain, ms, folder, SWT.NONE);
		
		ConfigMonitorComposite.this.layout();
		ConfigMonitorComposite.this.redraw();
		
		return msc;
	}
}
