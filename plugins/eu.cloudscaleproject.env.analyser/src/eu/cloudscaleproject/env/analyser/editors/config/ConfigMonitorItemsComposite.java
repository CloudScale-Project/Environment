package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryFactory;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;

import de.uka.ipd.sdq.identifier.IdentifierPackage;
import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.dialogs.TextInputDialog;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;

public class ConfigMonitorItemsComposite extends Composite{
	
	private final ConfAlternative alternative;
	private List<MonitorCollection> monitorCollections = new ArrayList<MonitorCollection>();
		
	public ConfigMonitorItemsComposite(ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		this.alternative = input;
		
		setLayout(new GridLayout(1, true));
		
		Composite toolbarComposite = new Composite(this, SWT.NONE);
		toolbarComposite.setLayout(new GridLayout(4, false));
		toolbarComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Button btnRadioButton = new Button(toolbarComposite, SWT.RADIO);
		btnRadioButton.setEnabled(false);
		btnRadioButton.setText("List");
		
		Button btnRadioButton_1 = new Button(toolbarComposite, SWT.RADIO);
		btnRadioButton_1.setSelection(true);
		btnRadioButton_1.setEnabled(false);
		btnRadioButton_1.setText("Group");
		
		Composite composite = new Composite(toolbarComposite, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_composite.heightHint = 1;
		composite.setLayoutData(gd_composite);
				
		Button btnNewButton = new Button(toolbarComposite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				
				TextInputDialog dialog = new TextInputDialog(Display.getDefault().getActiveShell());
				dialog.open();
				
				if(dialog.getReturnCode() == IDialogConstants.OK_ID){
					MonitorRepository monitorRep = alternative.getUsedMonitorRepository();
					Monitor monitor = MonitorrepositoryFactory.eINSTANCE.createMonitor();
					
					MeasurementSpecification spec = MonitorrepositoryFactory.eINSTANCE.createMeasurementSpecification();
					monitor.getMeasurementSpecifications().add(spec);
					monitor.setEntityName(dialog.getText());
					
					monitorRep.getMonitors().add(monitor);
					
					alternative.setDirty(true);
					reload();
				}
			}
		});
		btnNewButton.setText("Create new Monitor");
		reload();
	}
	
	public void reload(){
				
		Control[] children = this.getChildren();
		for(int i=1; i<children.length; i++){
			children[i].dispose();
		}
		
		monitorCollections.clear();
		
		for(Monitor m : alternative.getUsedMonitorRepository().getMonitors()){
			boolean hasBeenAdded = false;
			for(MonitorCollection mc : monitorCollections){
				if(mc.add(m)){
					hasBeenAdded = true;
					break;
				}
			}
			
			if(!hasBeenAdded){
				MonitorCollection newMc = new MonitorCollection(m);
				monitorCollections.add(newMc);
			}
		}
		
		//create new composites
		for(final MonitorCollection mc : monitorCollections){
			EObjectWrapper monitorsWrapper = new EObjectWrapper(mc.getMonitors());
			
			ExpandableComposite expComposite = new ExpandableComposite(this, SWT.BORDER,
					ExpandableComposite.CLIENT_INDENT
					| ExpandableComposite.COMPACT
					| ExpandableComposite.TITLE_BAR
					| ExpandableComposite.TWISTIE);
			
			expComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			ConfigMonitorComposite cmc = new ConfigMonitorComposite(alternative, monitorsWrapper, expComposite, SWT.NONE);
			expComposite.setText(cmc.getMonitorName());
			
			Button btnDelete = new Button(expComposite, SWT.NONE);
			btnDelete.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					super.widgetSelected(e);
					for(Monitor m : mc.getMonitors()){
						EcoreUtil.remove(m);
					}
					alternative.setDirty(true);
					reload();
				}
			});
			btnDelete.setText("Delete");
			
			expComposite.setClient(cmc);
			expComposite.setTextClient(btnDelete);
			
			expComposite.addExpansionListener(new ExpansionAdapter() {
				public void expansionStateChanged(ExpansionEvent e) {
					ConfigMonitorItemsComposite.this.layout();
					ConfigMonitorItemsComposite.this.redraw();
					ConfigMonitorItemsComposite.this.pack();
				}
			});
		}
		
		this.layout();
		this.redraw();
		this.pack();
	}
	
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
	
	private static class MonitorCollection{
		private List<Monitor> monitors = new ArrayList<Monitor>();
		
		public MonitorCollection(Monitor m) {
			monitors.add(m);
		}
		
		public List<Monitor> getMonitors(){
			return monitors;
		}
		
		public boolean add(Monitor monitor){
			Monitor monitorExample = monitors.get(0);
		    EqualityHelper equalityHelper = new EqualityHelper()
		    {
				private static final long serialVersionUID = 1L;

				@Override
		    	protected boolean haveEqualAttribute(EObject eObject1, EObject eObject2, EAttribute c) {
		    		if(c == IdentifierPackage.Literals.IDENTIFIER__ID){
		    			return true;
		    		}
		    		if(c == MonitorrepositoryPackage.Literals.MONITOR__MEASURING_POINT){
		    			return true;
		    		}
		    		return super.haveEqualAttribute(eObject1, eObject2, c);
		    	}
		    };
		    
		    boolean isEqual = equalityHelper.equals(monitorExample, monitor);
			
			if(isEqual){
				monitors.add(monitor);
				return true;
			}
			
			return false;
		}
	}
}
