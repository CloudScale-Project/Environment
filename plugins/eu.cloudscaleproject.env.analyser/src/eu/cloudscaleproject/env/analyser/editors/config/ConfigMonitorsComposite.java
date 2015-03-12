package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;

import de.uka.ipd.sdq.identifier.IdentifierPackage;
import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;

public class ConfigMonitorsComposite extends Composite{
	
	private final ConfAlternative alternative;
	private List<MonitorCollection> monitorCollections = new ArrayList<MonitorCollection>();
	
	public ConfigMonitorsComposite(ConfAlternative input, Composite parent, int style) {
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
		btnNewButton.setText("Create new Monitor");
		
		
		update();
	}
	
	public void update(){
		
		for(int i=1; i<this.getChildren().length; i++){
			this.getChildren()[i].dispose();
		}
		
		monitorCollections.clear();
		
		for(MonitorRepository mr : alternative.getMonitorRepositories()){
			for(Monitor m : mr.getMonitors()){
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
		}
		
		//create new composites
		
		for(MonitorCollection mc : monitorCollections){
			EObjectWrapper monitorsWrapper = new EObjectWrapper(mc.getMonitors());
			
			ExpandableComposite expComposite = new ExpandableComposite(this, SWT.BORDER,
					ExpandableComposite.CLIENT_INDENT
					| ExpandableComposite.COMPACT
					| ExpandableComposite.TITLE_BAR
					| ExpandableComposite.TWISTIE);
			
			expComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			ConfigMonitorComposite cmc = new ConfigMonitorComposite(alternative, monitorsWrapper, expComposite, SWT.NONE);
			expComposite.setText(cmc.getName());
			
			Button btnDelete = new Button(expComposite, SWT.NONE);
			btnDelete.setText("Delete");
			expComposite.setClient(cmc);
			expComposite.setTextClient(btnDelete);
			
			expComposite.addExpansionListener(new ExpansionAdapter() {
				public void expansionStateChanged(ExpansionEvent e) {
					ConfigMonitorsComposite.this.layout();
					ConfigMonitorsComposite.this.redraw();
					ConfigMonitorsComposite.this.pack();
				}
			});
		}
		
		this.layout();
		this.redraw();
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
		    		if(c.getFeatureID() == IdentifierPackage.IDENTIFIER__ID){
		    			return true;
		    		}
		    		return super.haveEqualAttribute(eObject1, eObject2, c);
		    	}
		    };

			@SuppressWarnings("unchecked")
			boolean isEqual = equalityHelper.equals((List<EObject>)(List<? extends EObject>)monitorExample.getMeasurementSpecifications(), 
					(List<EObject>)(List<? extends EObject>)monitor.getMeasurementSpecifications());
			
			if(isEqual){
				monitors.add(monitor);
				return true;
			}
			
			return false;
		}
	}
}
