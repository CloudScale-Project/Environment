package eu.cloudscaleproject.env.analyser.editors.config;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;

import eu.cloudscaleproject.env.common.ui.util.ControlTabItemListProperty;

public class ConfigMonitorComposite extends Composite{
	
	private final EditingDomain editingDomain;
	private final Monitor monitorWrapper;
	private final CTabFolder folder;
	private DataBindingContext bindingContext = null;
	
	public ConfigMonitorComposite(EditingDomain editingDomain, Monitor monitorWrapper, Composite parent, int style) {
		super(parent, style);
		
		this.editingDomain = editingDomain;
		this.monitorWrapper = monitorWrapper;
		
		setLayout(new FillLayout());
		
		folder = new CTabFolder(this, SWT.NONE);
					
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
	
	public void initBindings(){
		
		if(bindingContext != null){
			bindingContext.dispose();
		}
		bindingContext = new DataBindingContext();
		
		IObservableList obsMeasurSpec = EMFProperties.list(
				MonitorrepositoryPackage.Literals.MONITOR__MEASUREMENT_SPECIFICATIONS).observe(monitorWrapper);
		
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
		bindingContext.updateTargets();
	}
	
	private MeasurementSpecComposite createMeasurementSpecComposite(MeasurementSpecification ms){
		MeasurementSpecComposite msc = new MeasurementSpecComposite(editingDomain, ms, folder, SWT.NONE);
		
		ConfigMonitorComposite.this.layout();
		ConfigMonitorComposite.this.redraw();
		
		return msc;
	}
}
