package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.dialogs.TextInputDialog;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;
import eu.cloudscaleproject.env.common.ui.IRefreshable;
import eu.cloudscaleproject.env.common.ui.list.ListComposite;

public class ConfigSLOListComposite extends Composite implements IRefreshable{

	private final Composite stackedComposite;
	private final StackLayout stackLayout;
	
	private final ListComposite groupsComposite = null;
	private final ListComposite listComposite;
	
	private final ConfAlternative alternative;
	//private List<SloGroup> sloGroups = new ArrayList<SloGroup>();
	
	public ConfigSLOListComposite(ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		this.alternative = input;
		
		setLayout(new GridLayout(1, true));
		
		Composite toolbarComposite = new Composite(this, SWT.NONE);
		toolbarComposite.setLayout(new GridLayout(4, false));
		toolbarComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Button btnRadioList = new Button(toolbarComposite, SWT.RADIO);
		btnRadioList.setText("List");
		btnRadioList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				stackLayout.topControl = listComposite;
				stackedComposite.layout();
			}
		});
		
		Button btnRadioGroup = new Button(toolbarComposite, SWT.RADIO);
		btnRadioGroup.setText("Group");
		btnRadioGroup.addSelectionListener(new SelectionAdapter() {	
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				calcSLOGroups();
				stackLayout.topControl = groupsComposite;
				stackedComposite.layout();
			}
		});
		
		//Disable grouping - wait for further PCM model changes
		//TODO: remove grouping support or reimplement it!
		btnRadioGroup.setEnabled(false);
		btnRadioList.setEnabled(false);
		
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
					ServiceLevelObjectiveRepository sloRep = alternative.getUsedSloRepository();
					ServiceLevelObjective slo = ServicelevelObjectiveFactory.eINSTANCE.createServiceLevelObjective();
					
					slo.setName(dialog.getText());
					sloRep.getServicelevelobjectives().add(slo);
					
					alternative.setDirty(true);
					calcSLOGroups();
					
					//show it
					Control control = stackLayout.topControl;
					if(control instanceof ListComposite){
						ListComposite lc = (ListComposite)control;
						lc.showChild(slo);
					}
				}
			}
		});
		btnNewButton.setText("Create new SLO");
		
		stackedComposite = new Composite(this, SWT.NONE);
		stackedComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		stackLayout = new StackLayout();
		stackedComposite.setLayout(stackLayout);
		
		//group view
		{
			/*
			groupsComposite = new ListComposite(stackedComposite, SWT.NONE){

				@Override
				protected Composite createComposite(ExpandableComposite parent, Object source) {
					SloGroup group = (SloGroup)source;
					
					String name = group.getSLOs().get(0).getName();
					if(name == null || name.isEmpty()){
						name = "No name";
					}
					parent.setText("SLO group: " + name);
					
					EObjectWrapper wrapper = new EObjectWrapper(group.getSLOs());
					ConfigSLOComposite sloComposite = new ConfigSLOComposite(alternative, wrapper, parent, SWT.NONE);
					return sloComposite;
				}
				
			};

			IObservableList monitorsGroupsObs = PojoObservables.observeList(this, "sloGroups");
			groupsComposite.initBindings(monitorsGroupsObs);
			*/
		}
		
		//list view
		{
			listComposite = new ListComposite(stackedComposite, SWT.NONE){
	
				@Override
				protected Composite createComposite(ExpandableComposite parent, Object source) {
					ServiceLevelObjective slo = (ServiceLevelObjective)source;
					
					//TODO: find out how to bind ExpandableComposite name with the source name!
					String name = slo.getName();
					if(name == null || name.isEmpty()){
						name = "No name";
					}
					parent.setText(name);
					
					EObjectWrapper wrapper = new EObjectWrapper(slo, new ArrayList<EObject>());
					ConfigSLOComposite sloComposite = new ConfigSLOComposite(alternative, wrapper, parent, SWT.NONE);
					return sloComposite;
				}
				
			};
					
			IEMFEditListProperty slosProp = EMFEditProperties.list(alternative.getEditingDomain(), 
					ServicelevelObjectivePackage.Literals.SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES);
			IObservableList slosObs = slosProp.observe(alternative.getUsedSloRepository());
			listComposite.initBindings(slosObs);
		}
		
		btnRadioList.setSelection(true);
		stackLayout.topControl = listComposite;
		stackedComposite.layout();
		
		calcSLOGroups();
	}
	
	/*
	public List<SloGroup> getSloGroups() {
		return sloGroups;
	}

	public void setSloGroups(List<SloGroup> sloGroups) {
		this.sloGroups = sloGroups;
	}
	*/

	public void calcSLOGroups(){
		
		/*
		List<SloGroup> sloGroupsNew = new ArrayList<SloGroup>();
				
		for(ServiceLevelObjective slo : alternative.getUsedSloRepository().getServicelevelobjectives()){
			boolean hasBeenAdded = false;
			for(SloGroup mc : sloGroupsNew){
				if(mc.add(slo)){
					hasBeenAdded = true;
					break;
				}
			}
			
			if(!hasBeenAdded){
				SloGroup newMc = new SloGroup(slo);
				sloGroupsNew.add(newMc);
			}
		}
		
		ListDiff diff = Diffs.computeListDiff(sloGroups, sloGroupsNew);
		diff.applyTo(sloGroups);
		
		groupsComposite.updateTarget();
		*/
	}

	@Override
	public void refresh() {
		if(listComposite != null){
			listComposite.refresh();
		}
		if(groupsComposite != null){
			groupsComposite.refresh();
		}
	}
	
	/*
	private static class SloGroup{
		private List<ServiceLevelObjective> sloList = new ArrayList<ServiceLevelObjective>();
		
		public SloGroup(ServiceLevelObjective slo) {
			sloList.add(slo);
		}
		
		public List<ServiceLevelObjective> getSLOs(){
			return sloList;
		}
		
		public boolean add(ServiceLevelObjective monitor){
			ServiceLevelObjective sloExample = sloList.get(0);
		    EqualityHelper equalityHelper = new EqualityHelper()
		    {
				private static final long serialVersionUID = 1L;

				@Override
		    	protected boolean haveEqualAttribute(EObject eObject1, EObject eObject2, EAttribute c) {
		    		if(c == IdentifierPackage.Literals.IDENTIFIER__ID){
		    			return true;
		    		}
		    		
		    		//if(c == ServicelevelObjectivePackage.Literals.SERVICE_LEVEL_OBJECTIVE__MEASURING_POINT){
		    		//	return true;
		    		//}
		    		
		    		return super.haveEqualAttribute(eObject1, eObject2, c);
		    	}
		    };
		    
		    boolean isEqual = equalityHelper.equals(sloExample, monitor);
			
			if(isEqual){
				sloList.add(monitor);
				return true;
			}
			
			return false;
		}
	}
	*/

}
