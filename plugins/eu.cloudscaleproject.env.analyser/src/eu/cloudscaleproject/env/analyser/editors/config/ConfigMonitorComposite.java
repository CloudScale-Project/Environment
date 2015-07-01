package eu.cloudscaleproject.env.analyser.editors.config;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.edit.domain.EditingDomain;
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
import org.eclipse.uml2.common.edit.command.ChangeCommand;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepositoryFactory;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.ui.util.ControlTabItemListProperty;

public class ConfigMonitorComposite extends Composite implements IRefreshable{
	
	private final EditingDomain editingDomain;
	private final EObjectWrapper<Monitor> monitorWrapper;

	private final CTabFolder folder;
	private final MeasuringPointsSelectionComposite measuringPointsComposite;
	
	private DataBindingContext bindingContext = null;
		
	public ConfigMonitorComposite(ConfAlternative alt, final EObjectWrapper<Monitor> monitorWrapper, Composite parent, int style) {
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
		
		measuringPointsComposite = new MeasuringPointsSelectionComposite(this, alt, monitorWrapper, SWT.NONE);
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_list.widthHint = 220;
		measuringPointsComposite.setLayoutData(gd_list);
		
		Composite folderControlsComposite = new Composite(folder, SWT.NONE);
		folderControlsComposite.setLayout(new FillLayout());
		
		Button btnAddNew = new Button(folderControlsComposite, SWT.NONE);
		btnAddNew.setText("+");
		btnAddNew.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
				//add measurement specification
				CommandStack cs = ConfigMonitorComposite.this.editingDomain.getCommandStack();
				cs.execute(new ChangeCommand(editingDomain, new Runnable() {
					
					@Override
					public void run() {
						MeasurementSpecification ms = MonitorRepositoryFactory.eINSTANCE.createMeasurementSpecification();
						Monitor masterMonitor = (Monitor)monitorWrapper.getMaster();
						masterMonitor.getMeasurementSpecifications().add(ms);
					}
				}));
				
				folder.setSelection(folder.getItemCount()-1);
			};
		});
		
		Button btnRemove = new Button(folderControlsComposite, SWT.NONE);
		btnRemove.setText("-");
		btnRemove.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
				//remove measurement specification
				CommandStack cs = ConfigMonitorComposite.this.editingDomain.getCommandStack();
				cs.execute(new ChangeCommand(editingDomain, new Runnable() {
					
					@Override
					public void run() {
						int selection = folder.getSelectionIndex();
						if(selection >= 0){
							Monitor masterMonitor = (Monitor)monitorWrapper.getMaster();
							masterMonitor.getMeasurementSpecifications().remove(selection);
						}
					}
				}));
				
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

		refresh();
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
				MonitorRepositoryPackage.Literals.MONITOR__MEASUREMENT_SPECIFICATIONS).observe(monitorWrapper.getMaster());
		
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
	
	public void refresh(){
		measuringPointsComposite.refresh();
	}
	
	private MeasurementSpecComposite createMeasurementSpecComposite(MeasurementSpecification ms){
		MeasurementSpecComposite msc = new MeasurementSpecComposite(editingDomain, ms, folder, SWT.NONE);
		
		ConfigMonitorComposite.this.layout();
		ConfigMonitorComposite.this.redraw();
		
		return msc;
	}
}
