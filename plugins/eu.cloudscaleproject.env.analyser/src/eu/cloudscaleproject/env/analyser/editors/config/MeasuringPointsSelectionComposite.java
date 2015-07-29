package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepository;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.dialogs.ListSelectionDialog;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;

public class MeasuringPointsSelectionComposite extends Composite implements IRefreshable{
	
	private DataBindingContext bindingContext = null;
	private final ArrayList<MeasuringPoint> measuringPoints = new ArrayList<MeasuringPoint>();
	
	private final ListViewer listViewer;
	private final Button buttonAdd;
	private final Button buttonRemove;
	
	private final ConfAlternative alternative;
	private final EObjectWrapper<Monitor> monitorWrapper;

	public MeasuringPointsSelectionComposite(Composite parent, ConfAlternative alt, EObjectWrapper<Monitor> monitor, int style) {
		super(parent, style);
		
		this.alternative = alt;
		this.monitorWrapper = monitor;
		
		this.setLayout(new GridLayout(1, false));
		
		listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		listViewer.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		List list = listViewer.getList();
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_list.widthHint = 220;
		list.setLayoutData(gd_list);
		listViewer.setLabelProvider(new AdapterFactoryLabelProvider(alternative.getAdapterFactory()));
		
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)listViewer.getSelection();
				if(selection.isEmpty()){
					buttonRemove.setEnabled(false);
				}
				else{
					buttonRemove.setEnabled(true);
				}
			}
		});
		
		Composite controls = new Composite(this, SWT.NONE);
		controls.setLayout(new RowLayout());
				
		buttonAdd = new Button(controls, SWT.NONE);
		buttonAdd.setText("Add...");
		buttonAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				ListSelectionDialog selectionDialog = new ListSelectionDialog(
						"Measuring point slection", 
						"Select measuring points",
						new AdapterFactoryLabelProvider(alternative.getAdapterFactory()),
						alternative.getMeasuringPointObjects());
				selectionDialog.open();
				
				if(selectionDialog.getResult() == null){
					return;
				}
				
				for(Object selection : selectionDialog.getResult()){
					
					if(monitorWrapper.getMaster().getMeasuringPoint() == null){
						monitorWrapper.getMaster().setMeasuringPoint((MeasuringPoint)selection);
						continue;
					}
					
					Monitor monitor = EcoreUtil.copy(monitorWrapper.getMaster());
					monitor.setMeasuringPoint((MeasuringPoint)selection);
					
					MonitorRepository mr = alternative.getActiveMonitorRepository();
					if(mr != null){
						mr.getMonitors().add(monitor);
						monitorWrapper.getSlaves().add(monitor);
					}
				}
				alternative.setDirty(true);
				refresh();
			}
		});
		
		buttonRemove = new Button(controls, SWT.NONE);
		buttonRemove.setText("Remove");
		buttonRemove.setEnabled(false);
		buttonRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for(Object selection : ((IStructuredSelection)listViewer.getSelection()).toArray()){
					if(selection instanceof MeasuringPoint){
						
						MeasuringPoint mp = (MeasuringPoint)selection;
						if(monitorWrapper.getMaster().getMeasuringPoint() == mp){
							monitorWrapper.getMaster().setMeasuringPoint(null);
							continue;
						}
						
						Iterator<Monitor> iter = monitorWrapper.getSlaves().iterator();
						while(iter.hasNext()){
							Monitor monitor = iter.next();
							if(monitor.getMeasuringPoint() == mp){
								iter.remove();
								EcoreUtil.delete(monitor);
							}
						}
						
					}
				}
				alternative.setDirty(true);
				refresh();
			}
		});
		
		initBindings();
	}
	
	public void initBindings(){
		
		if(bindingContext != null){
			bindingContext.dispose();
		}
		bindingContext = new DataBindingContext();
		
		IObservableList mpObs = Properties.selfList(MeasuringPoint.class).observe(measuringPoints);
		
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		listViewer.setContentProvider(listContentProvider);
		listViewer.setInput(mpObs);
		
		bindingContext.updateTargets();
	}

	@Override
	public void refresh() {
		
		measuringPoints.clear();
		
		MeasuringPoint mp = monitorWrapper.getMaster().getMeasuringPoint();
		if(mp != null && !measuringPoints.contains(mp)){
			measuringPoints.add(mp);
		}
		
		for(Monitor monitor : monitorWrapper.getSlaves()){
			mp = monitor.getMeasuringPoint();
			if(mp != null && !measuringPoints.contains(mp)){
				measuringPoints.add(mp);
			}
		}
		
		listViewer.refresh();
	}

}
