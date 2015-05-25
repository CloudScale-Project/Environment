package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
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
import org.palladiosimulator.experimentautomation.experiments.InitialModel;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.common.dialogs.TextInputDialog;
import eu.cloudscaleproject.env.common.emf.EObjectWrapper;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.common.ui.list.ListComposite;

public class ConfigSLOListComposite extends Composite implements IRefreshable{

	private final Composite stackedComposite;
	private final StackLayout stackLayout;
	private final ListComposite listComposite;
	
	private final ConfAlternative alternative;
	
	public ConfigSLOListComposite(ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		this.alternative = input;
		
		setLayout(new GridLayout(1, true));
		
		Composite toolbarComposite = new Composite(this, SWT.NONE);
		toolbarComposite.setLayout(new GridLayout(4, false));
		toolbarComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Label label = new Label(toolbarComposite, SWT.NONE);
		label.setText("Service level objectives:");
		
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
					ServiceLevelObjectiveRepository sloRep = alternative.retrieveSLORepository();
					ServiceLevelObjective slo = ServicelevelObjectiveFactory.eINSTANCE.createServiceLevelObjective();
					
					slo.setName(dialog.getText());
					sloRep.getServicelevelobjectives().add(slo);
					
					alternative.setDirty(true);
					
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
					
					EObjectWrapper<ServiceLevelObjective> wrapper 
						= new EObjectWrapper<ServiceLevelObjective>(slo, new ArrayList<ServiceLevelObjective>());
					
					ConfigSLOComposite sloComposite = new ConfigSLOComposite(alternative, wrapper, parent, SWT.NONE);
					
					//If this is the first child, set SLO repository model reference to the initial model
					if(listComposite.getChilds().isEmpty()){
						InitialModel im = alternative.getActiveInitialModel();
						//Set SLO repository found to the initial model
						if(im != null){
							im.setServiceLevelObjectives(alternative.retrieveSLORepository());
						}
					}
					//If this is the last child, remove SLO repository reference from the initial model
					sloComposite.addDisposeListener(new DisposeListener() {
						
						@Override
						public void widgetDisposed(DisposeEvent e) {
							if(listComposite.getChilds().isEmpty()){
								//remove SLO model reference from experiment
								InitialModel im = alternative.getActiveInitialModel();
								if(im != null){
									im.setServiceLevelObjectives(null);
								}
							}
						}
					});
					
					return sloComposite;
				}
				
			};
					
			IEMFEditListProperty slosProp = EMFEditProperties.list(alternative.getEditingDomain(), 
					ServicelevelObjectivePackage.Literals.SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES);
			IObservableList slosObs = slosProp.observe(alternative.retrieveSLORepository());
			listComposite.initBindings(slosObs);
		}
		
		stackLayout.topControl = listComposite;
		stackedComposite.layout();
	}

	@Override
	public void refresh() {
		if(listComposite != null){
			listComposite.refresh();
		}
	}
}
