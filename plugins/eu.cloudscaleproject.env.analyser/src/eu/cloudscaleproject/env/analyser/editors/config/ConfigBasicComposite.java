package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.common.edit.command.ChangeCommand;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationFactory;
import org.palladiosimulator.experimentautomation.abstractsimulation.MeasurementCountStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.SimTimeStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.StopCondition;
import org.palladiosimulator.experimentautomation.experiments.Experiment;
import org.palladiosimulator.experimentautomation.experiments.InitialModel;
import org.palladiosimulator.pcm.core.entity.NamedElement;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.scaledl.usageevolution.UsageEvolution;

import eu.cloudscaleproject.env.analyser.Activator;
import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;

public class ConfigBasicComposite extends Composite implements IRefreshable{
	
	private static final Logger logger = Logger.getLogger(ConfigBasicComposite.class.getName());
	
	protected final ConfAlternative alternative;
		
	private final Composite extensionComposite;
	
	private ComboViewer usageList;
	private Button btnCheckSimTime;
	private Button btnCheckMeasureCount;	
	private Text textSimTime;
	private Text textMeasureCount;
	
	private SimTimeStopCondition simTime = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
	private MeasurementCountStopCondition measureCount = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
		
	private boolean disableListeners = false;
	
	public ConfigBasicComposite(ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = input;
				
		setLayout(new GridLayout(1, false));
		
		Label lblConfiguration = new Label(this, SWT.NONE);
		lblConfiguration.setText("Configuration:");
		
		Composite compositeConf = new Composite(this, SWT.NONE);
		compositeConf.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_compositeConf = new GridLayout(2, false);
		gl_compositeConf.marginLeft = 10;
		compositeConf.setLayout(gl_compositeConf);
		
		//usage list drop-down
		initUsageUI(compositeConf);
		
		// simulation time UI
		initSimTimeUI(compositeConf);
		
		// measure count UI
		initMeaCountUI(compositeConf);
		
		extensionComposite = new Composite(this, SWT.NONE);
		extensionComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		initExtensions(extensionComposite);
		
		refresh();
	}

	protected void initExtensions(Composite parent){
		//override in subclasses
	}

	public void refresh() {
		
		Experiment exp = alternative.getActiveExperiment();
		
		if(exp == null){
			logger.warning("Active experiment is NULL! Skipping refresh().");
			return;
		}
		
		boolean needToSave = false;
		
		for(StopCondition sc : exp.getStopConditions()){
			if(sc instanceof SimTimeStopCondition){
				simTime = (SimTimeStopCondition)sc;
			}
			else if(sc instanceof MeasurementCountStopCondition){
				measureCount = (MeasurementCountStopCondition)sc;
			}
		}
		
		if(simTime == null){
			simTime = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
			simTime.setSimulationTime(-1);
			exp.getStopConditions().add(simTime);
			needToSave = true;
		}
		
		if(measureCount == null){
			measureCount = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
			measureCount.setMeasurementCount(-1);
			exp.getStopConditions().add(measureCount);
			needToSave = true;
		}
		
		if(needToSave){
			EditorInputJob job = new EditorInputJob("Alternative save") {
				@Override
				public IStatus execute(IProgressMonitor monitor) {
					alternative.save();
					return new Status(IStatus.OK, Activator.PLUGIN_ID, "Alternative saved");
				}
			};
			job.setUser(false);
			job.schedule();
		}
		
		updateUsageUI();
		updateSimTimeUI();
		updateMeasureCountUI();
	}
	
	private void initUsageUI(Composite composite){
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Select usage: ");
		
		usageList = new ComboViewer(composite);
		GridData gd = new GridData(SWT.NONE, SWT.CENTER, false, false, 1, 1);
		gd.widthHint = 180;
		usageList.getCombo().setLayoutData(gd);
		usageList.setContentProvider(new ArrayContentProvider());
		usageList.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				if(element instanceof NamedElement){
					return ((NamedElement)element).getEntityName();
				}
				return element.toString();
			}
		});
		
		usageList.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				if(disableListeners){
					return;
				}
				
				StructuredSelection ss = (StructuredSelection)event.getSelection();
				final Object element = ss.getFirstElement();
				final EditingDomain ed = alternative.getEditingDomain();
				
				CommandStack cs = ed.getCommandStack();
				
				cs.execute(new ChangeCommand(ed, new Runnable() {
					
					public void run() {
						
						InitialModel im = alternative.getActiveInitialModel();
						if(im == null){
							return;
						}
						
						if(element instanceof UsageEvolution){
							UsageEvolution ue = (UsageEvolution)element;
							
							if(!ue.getUsages().isEmpty()){
								UsageScenario us = ue.getUsages().get(0).getScenario();
								if(us != null && us.eContainer() != null){
									im.setUsageModel((UsageModel)us.eContainer());
								}
							}
							
							im.setUsageEvolution(ue);
						}
						if(element instanceof UsageModel){
							UsageModel um = (UsageModel)element;
							im.setUsageEvolution(null);
							im.setUsageModel(um);
						}
					}
					
				}));
				
			}
		});
	}
	
	private void initSimTimeUI(Composite composite){
		
		//check box
		btnCheckSimTime = new Button(composite, SWT.CHECK);
		btnCheckSimTime.setText("Simulation time stop condition:");
		btnCheckSimTime.setSelection(true);
		btnCheckSimTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(disableListeners){
					return;
				}
				
				if(btnCheckSimTime.getSelection()){
					textSimTime.setEnabled(true);
					textSimTime.setText("10");
				}
				else{
					textSimTime.setEnabled(false);

					alternative.getEditingDomain().getCommandStack().execute(new ChangeCommand(alternative.getEditingDomain(), 
							new Runnable() {
						@Override
						public void run() {
							if(simTime.getSimulationTime() != -1){
								simTime.setSimulationTime(-1);
							}
						}
					}));
				}
			}
		});
		
		//text
		textSimTime  = new Text(composite, SWT.BORDER);
		GridData gd = new GridData(SWT.NONE, SWT.CENTER, false, false, 1, 1);
		gd.widthHint = 80;
		textSimTime.setLayoutData(gd);
		final ControlDecoration warningDecorationNoJavaProjects = new ControlDecoration(textSimTime, SWT.LEFT | SWT.TOP);
		warningDecorationNoJavaProjects.setDescriptionText("Not a number");
		warningDecorationNoJavaProjects.setImage(FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR	)
                .getImage());
		warningDecorationNoJavaProjects.hide();
		
		textSimTime.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				
				if(disableListeners){
					return;
				}
				
				try {
					disableListeners = true;
					if(simTime != null){
						try{
							String s = textSimTime.getText();
							int i = Integer.parseInt(s);
							simTime.setSimulationTime(i);
							warningDecorationNoJavaProjects.hide();
						}
						catch(NumberFormatException exc){
							warningDecorationNoJavaProjects.show();
						}
					}
				}
				finally{
					disableListeners = false;
				}
				
			}
		});
	}
	
	private void initMeaCountUI(Composite composite){
		
		//check box
		btnCheckMeasureCount = new Button(composite, SWT.CHECK);
		btnCheckMeasureCount.setText("Measurement count stop condition:");
		btnCheckMeasureCount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(disableListeners){
					return;
				}
				
				if(btnCheckMeasureCount.getSelection()){
					textMeasureCount.setEnabled(true);
					textMeasureCount.setText("100");
				}
				else{
					textMeasureCount.setEnabled(false);

					alternative.getEditingDomain().getCommandStack().execute(new ChangeCommand(alternative.getEditingDomain(), 
							new Runnable() {
						@Override
						public void run() {
							if(measureCount.getMeasurementCount() != -1){
								measureCount.setMeasurementCount(-1);
							}
						}
					}));
				}
			}
		});
		
		//text
		textMeasureCount = new Text(composite, SWT.BORDER);
		GridData gd = new GridData(SWT.NONE, SWT.CENTER, false, false, 1, 1);
		gd.widthHint = 80;
		textMeasureCount.setLayoutData(gd);
		final ControlDecoration warningDecorationNoJavaProjects = new ControlDecoration(textMeasureCount, SWT.RIGHT | SWT.TOP);
		warningDecorationNoJavaProjects.setDescriptionText("Not a number");
		warningDecorationNoJavaProjects.setImage(FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR	)
                .getImage());
		warningDecorationNoJavaProjects.hide();
		
		textMeasureCount.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				
				if(disableListeners){
					return;
				}
				
				try {
					disableListeners = true;
					if(measureCount != null){
						try{
							String s = textMeasureCount.getText();
							int i = Integer.parseInt(s);
							measureCount.setMeasurementCount(i);
							warningDecorationNoJavaProjects.hide();
						}
						catch(NumberFormatException exc){
							warningDecorationNoJavaProjects.show();					
						}
					}
				}
				finally{
					disableListeners = false;
				}
			}
		});
	}
	
	private void updateUsageUI(){
		
		if(usageList.getCombo().isDisposed()){
			return;
		}
		
		InputAlternative ia = alternative.getInputAlternative();
		if(ia == null){
			return;
		}
		
		List<EObject> usages = ia.getModelRoot(alternative.getResourceSet(), ToolchainUtils.KEY_FILE_USAGE);
				
		if(alternative.getTypeEnum() == ConfAlternative.Type.NORMAL){
			List<EObject> usageEvolutions = ia.getModelRoot(alternative.getResourceSet(), ToolchainUtils.KEY_FILE_USAGEEVOLUTION);
			usages.addAll(usageEvolutions);
		}
		
		try{
			disableListeners = true;
			usageList.setInput(usages);
			
			if(alternative.getActiveUsageEvolutionModel() != null){
				usageList.setSelection(new StructuredSelection(alternative.getActiveUsageEvolutionModel()));
			}
			else if(alternative.getActiveUsageModel() != null){
				usageList.setSelection(new StructuredSelection(alternative.getActiveUsageModel()));
			}
		}
		finally{
			disableListeners = false;
		}
	}
	
	private void updateSimTimeUI(){
		
		if(btnCheckSimTime.isDisposed()){
			return;
		}
		if(textSimTime.isDisposed()){
			return;
		}
		
		if(simTime.getSimulationTime() == -1){
			btnCheckSimTime.setSelection(false);
			textSimTime.setEnabled(false);
		}
		else{
			btnCheckSimTime.setSelection(true);
			textSimTime.setEnabled(true);
			
			try{
				disableListeners = true;
				textSimTime.setText(String.valueOf(simTime.getSimulationTime()));
			}
			finally{
				disableListeners = false;
			}
		}
	}
	
	private void updateMeasureCountUI(){
		
		if(btnCheckMeasureCount.isDisposed()){
			return;
		}
		if(textMeasureCount.isDisposed()){
			return;
		}
		
		if(measureCount.getMeasurementCount() == -1){
			btnCheckMeasureCount.setSelection(false);
			textMeasureCount.setEnabled(false);
		}
		else{
			btnCheckMeasureCount.setSelection(true);
			textMeasureCount.setEnabled(true);
			
			try{
				disableListeners = true;
				textMeasureCount.setText(String.valueOf(measureCount.getMeasurementCount()));
			}
			finally{
				disableListeners = false;
			}
		}
	}
}
