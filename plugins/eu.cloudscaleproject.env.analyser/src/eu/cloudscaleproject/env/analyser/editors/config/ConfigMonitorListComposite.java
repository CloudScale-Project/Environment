package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffEntry;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
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
import eu.cloudscaleproject.env.common.interfaces.ISelectable;
import eu.cloudscaleproject.env.common.ui.list.ListComposite;

public class ConfigMonitorListComposite extends Composite implements IRefreshable, ISelectable{

	private final Composite stackedComposite;
	private final StackLayout stackLayout;
	
	private final ListComposite groupsComposite;
	
	private final ConfAlternative alternative;
	
	private WritableList monitorGroups = new WritableList(new ArrayList<MonitorGroup>(), MonitorGroup.class); 

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
					createMonitorGroup(dialog.getText());
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
				
				@Override
				public void postDeleteChild(Object o) {
					
					super.postDeleteChild(o);
					deleteMonitorGroup((MonitorGroup)o);
				}
				
			};
		}
		
		stackLayout.topControl = groupsComposite;
		stackedComposite.layout();
	}
	
	private void createMonitorGroup(final String name){
		
		alternative.executeRecordingModelChange(new Runnable() {
			
			@Override
			public void run() {

				MonitorRepository monitorRep = alternative.initActiveMonitorRepository();
				
				if(monitorRep == null){
					MessageDialog.openError(getShell(), "Error", "MonitorRepository does not exist!");
					return;
				}
				
				Monitor monitor = MonitorRepositoryFactory.eINSTANCE.createMonitor();
				
				MeasurementSpecification spec = MonitorRepositoryFactory.eINSTANCE.createMeasurementSpecification();
				monitor.getMeasurementSpecifications().add(spec);
				monitor.setEntityName(name);
				
				monitorRep.getMonitors().add(monitor);
				
				alternative.setDirty(true);
				calcMonitorGroups();
				
				//show it
				Control control = stackLayout.topControl;
				if(control instanceof ListComposite){
					ListComposite lc = (ListComposite)control;
					lc.showChild(getMonitorGroup(monitor));
				}
				
			}
		});
	}
	
	private void deleteMonitorGroup(final MonitorGroup mg){
		
		alternative.executeRecordingModelChange(new Runnable() {
			
			@Override
			public void run() {
				for(Monitor m : mg.getMonitors()){
					EcoreUtil.delete(m);
				}
			}
		});
	}
	
	private MonitorGroup getMonitorGroup(Monitor monitor){
		for(Object o : monitorGroups){
			if(o instanceof MonitorGroup){
				MonitorGroup mg = (MonitorGroup)o;
				for(Monitor m : mg.getMonitors()){
					if(m == monitor){
						return mg;
					}
				}
			}
		}
		return null;
	}

	private void calcMonitorGroups(){

		List<MonitorGroup> monitorGroupsNew = new ArrayList<MonitorGroup>();
		
		synchronized (alternative.getSaveLoadLock()) {
			if(alternative.getActiveMonitorRepository() == null){
				monitorGroups.clear();
				groupsComposite.refresh();
				return;
			}
					
			for(Monitor m : alternative.getActiveMonitorRepository().getMonitors()){
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
		}
		
		ListDiff diff = Diffs.computeListDiff(monitorGroups, monitorGroupsNew);
		diff.applyTo(monitorGroups);
		
		groupsComposite.refresh();
	}
	
	public void onSelect(){
		groupsComposite.initBindings(monitorGroups);
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
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			
			for(Monitor m : monitors)
			{
			    result = result * prime + m.hashCode();
			}
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			
			if(obj instanceof MonitorGroup){
				MonitorGroup that = (MonitorGroup)obj;
				
				ListDiff diff = Diffs.computeListDiff(that.monitors, monitors);
				ListDiffEntry[] lde = diff.getDifferences();
				if(lde == null || lde.length == 0){
					return true;
				}
			}
			return false;
		}
	}

}
