package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryFactory;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;

import de.uka.ipd.sdq.identifier.IdentifierPackage;
import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.dialogs.TextInputDialog;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.ui.list.ListComposite;

public class ConfigMonitorListComposite extends Composite implements IRefreshable{

	private final Composite stackedComposite;
	private final StackLayout stackLayout;
	
	private final ListComposite groupsComposite;
	
	private final ConfAlternative alternative;
	private List<MonitorGroup> monitorGroups = new ArrayList<MonitorGroup>();
	
	public ConfigMonitorListComposite(ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		this.alternative = input;
		
		setLayout(new GridLayout(1, true));
		
		Composite toolbarComposite = new Composite(this, SWT.NONE);
		toolbarComposite.setLayout(new GridLayout(4, false));
		toolbarComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Label label = new Label(toolbarComposite, SWT.NONE);
		label.setText("Measurement monitors:");
		
		//used as expander
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
					Monitor monitor = MonitorRepositoryFactory.eINSTANCE.createMonitor();
					
					MeasurementSpecification spec = MonitorRepositoryFactory.eINSTANCE.createMeasurementSpecification();
					monitor.getMeasurementSpecifications().add(spec);
					monitor.setEntityName(dialog.getText());
					
					monitorRep.getMonitors().add(monitor);
					
					alternative.setDirty(true);
					calcMonitorGroups();
					
					//show it
					Control control = stackLayout.topControl;
					if(control instanceof ListComposite){
						ListComposite lc = (ListComposite)control;
						lc.showChild(monitor);
					}
				}
			}
		});
		btnNewButton.setText("Create new Monitor");
		
		stackedComposite = new Composite(this, SWT.NONE);
		stackedComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		stackLayout = new StackLayout();
		stackedComposite.setLayout(stackLayout);
		
		//group view
		{
			groupsComposite = new ListComposite(stackedComposite, SWT.NONE){

				@Override
				protected Composite createComposite(ExpandableComposite parent, Object source) {
					MonitorGroup group = (MonitorGroup)source;
					
					String name = group.getMonitors().get(0).getEntityName();
					if(name == null || name.isEmpty()){
						name = "No name";
					}
					parent.setText("Monitor group: " + name);
					
					EObjectWrapper<Monitor> wrapper = new EObjectWrapper<Monitor>(group.getMonitors());
					ConfigMonitorComposite monitorComposite = new ConfigMonitorComposite(alternative, wrapper, parent, SWT.NONE);
					return monitorComposite;
				}
				
			};

			IObservableList monitorsGroupsObs = PojoObservables.observeList(this, "monitorGroups");
			groupsComposite.initBindings(monitorsGroupsObs);
		}
		
		stackLayout.topControl = groupsComposite;
		stackedComposite.layout();
		
		calcMonitorGroups();
	}
	
	public List<MonitorGroup> getMonitorGroups() {
		return monitorGroups;
	}

	public void setMonitorGroups(List<MonitorGroup> monitorGroups) {
		this.monitorGroups = monitorGroups;
	}

	private void calcMonitorGroups(){
		
		List<MonitorGroup> monitorGroupsNew = new ArrayList<MonitorGroup>();
				
		for(Monitor m : alternative.getUsedMonitorRepository().getMonitors()){
			boolean hasBeenAdded = false;
			for(MonitorGroup mc : monitorGroupsNew){
				if(mc.add(m)){
					hasBeenAdded = true;
					break;
				}
			}
			
			if(!hasBeenAdded){
				MonitorGroup newMc = new MonitorGroup(m);
				monitorGroupsNew.add(newMc);
			}
		}
		
		ListDiff diff = Diffs.computeListDiff(monitorGroups, monitorGroupsNew);
		diff.applyTo(monitorGroups);
		
		groupsComposite.updateTarget();
	}
	
	public void refresh(){
		calcMonitorGroups();
	}
	
	private static class MonitorGroup{
		private List<Monitor> monitors = new ArrayList<Monitor>();
		
		public MonitorGroup(Monitor m) {
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
		    		if(c == MonitorRepositoryPackage.Literals.MONITOR__MEASURING_POINT){
		    			return true;
		    		}
		    		return super.haveEqualAttribute(eObject1, eObject2, c);
		    	}
				
				@Override
				protected boolean haveEqualReference(EObject eObject1, EObject eObject2, EReference reference) {
					if(reference == MonitorRepositoryPackage.Literals.MONITOR__MEASURING_POINT){
		    			return true;
		    		}
					return super.haveEqualReference(eObject1, eObject2, reference);
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
