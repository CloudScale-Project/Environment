package eu.cloudscaleproject.env.analyser.editors.config;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationFactory;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationPackage.Literals;
import org.palladiosimulator.experimentautomation.abstractsimulation.MeasurementCountStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.SimTimeStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.StopCondition;
import org.palladiosimulator.experimentautomation.experiments.ExperimentsPackage;
import org.palladiosimulator.experimentautomation.experiments.InitialModel;
import org.palladiosimulator.pcm.core.entity.NamedElement;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;

import eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative;
import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.analyser.converters.IntToString;
import eu.cloudscaleproject.env.analyser.converters.StringToInt;
import eu.cloudscaleproject.env.common.interfaces.IRefreshable;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public class ConfigBasicComposite extends Composite implements IRefreshable{
	
	private DataBindingContext m_bindingContext;
	
	private Text stTextValue;
	private Text mcTextValue;

	private ComboViewer comboUsage;
	private ComboViewer comboUsageEvo;
	
	private InitialModel initialModel;
	private SimTimeStopCondition stStopCondition;
	private MeasurementCountStopCondition mcStopCondition;
	
	private Button btnSimulationTimeStop;
	private Button btnMeasurementCountStop;
	
	protected final ConfAlternative alternative;
	
	private List<Usage> usageModels = new ArrayList<Usage>();
	private List<UsageEvolution> usageEvolutionModels = new ArrayList<UsageEvolution>();
	
	private IObservableList usageModelsObs = Properties.selfList(Usage.class).observe(usageModels);
	private IObservableList usageEvolutionModelsObs = Properties.selfList(UsageEvolution.class).observe(usageEvolutionModels);

	private final Composite extensionComposite;
	
	public ConfigBasicComposite(final ConfAlternative input, Composite parent, int style) {
		super(parent, style);
		
		this.alternative = input;
		this.initialModel = input.getActiveInitialModel();
		
		//retrieve stop conditions
		for(StopCondition sc : alternative.getActiveExperiment().getStopConditions()){
			if(sc instanceof SimTimeStopCondition){
				stStopCondition = (SimTimeStopCondition)sc;
			}
			else if(sc instanceof MeasurementCountStopCondition){
				mcStopCondition = (MeasurementCountStopCondition)sc;
			}
		}
		
		// create stop conditions if they do not exist
		if(stStopCondition == null){
			stStopCondition = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
			stStopCondition.setSimulationTime(-1);

			input.executeModelChange(new Runnable(){
				@Override
				public void run() {
					input.getActiveExperiment().getStopConditions().add(stStopCondition);
				}
			});
		}
		if(mcStopCondition == null){
			mcStopCondition = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
			mcStopCondition.setMeasurementCount(-1);

			input.executeModelChange(new Runnable(){
				@Override
				public void run() {
					input.getActiveExperiment().getStopConditions().add(mcStopCondition);
				}
			});
		}

		setLayout(new GridLayout(1, false));

		
		Composite basicConfigComposite = new Composite(this, SWT.NONE);
		GridLayout gl_basicConfigComposite = new GridLayout(2, false);
		gl_basicConfigComposite.marginLeft = 10;
		gl_basicConfigComposite.marginHeight = 10;
		basicConfigComposite.setLayout(gl_basicConfigComposite);
		basicConfigComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		{
			Label lblDescription = new Label(basicConfigComposite, SWT.NONE);
			lblDescription.setText("Basic measurements settings:");
			lblDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
			
			Composite settingsComposite = new Composite(basicConfigComposite, SWT.NONE);
			GridLayout gl_settingsComposite = new GridLayout(2, false);
			gl_settingsComposite.marginLeft = 10;
			settingsComposite.setLayout(gl_settingsComposite);
			settingsComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

			{
				//usage combo selection
				Label labelUsage = new Label(settingsComposite, SWT.NONE);
				labelUsage.setText("Select usage: ");
				
				comboUsage = new ComboViewer(settingsComposite);
				GridData gdUsage = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gdUsage.widthHint = 180;
				comboUsage.getCombo().setLayoutData(gdUsage);
				comboUsage.setContentProvider(new ObservableListContentProvider());
				comboUsage.setLabelProvider(new LabelProvider(){
					@Override
					public String getText(Object element) {
						if(element instanceof NamedElement){
							return ((NamedElement)element).getEntityName();
						}
						return element.toString();
					}
				});
				comboUsage.setInput(usageModelsObs);

				//usage evolution combo selection
				Label labelUsageEvo = new Label(settingsComposite, SWT.NONE);
				labelUsageEvo.setText("Select usage evolution: ");
				
				comboUsageEvo = new ComboViewer(settingsComposite);
				GridData gdUsageEvo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gdUsageEvo.widthHint = 180;
				comboUsageEvo.getCombo().setLayoutData(gdUsageEvo);
				comboUsageEvo.setContentProvider(new ObservableListContentProvider());
				comboUsageEvo.setLabelProvider(new LabelProvider(){
					@Override
					public String getText(Object element) {
						if(element instanceof NamedElement){
							return ((NamedElement)element).getEntityName();
						}
						return element.toString();
					}
				});
				comboUsageEvo.setInput(usageEvolutionModelsObs);
			
				//stop time condition
				btnSimulationTimeStop = new Button(settingsComposite, SWT.CHECK);
				btnSimulationTimeStop.setText("Simulation time stop condition");
				
				stTextValue = new Text(settingsComposite, SWT.BORDER);
				GridData gd_stTextValue = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_stTextValue.widthHint = 60;
				stTextValue.setLayoutData(gd_stTextValue);
				
				//stop m. count condition
				btnMeasurementCountStop = new Button(settingsComposite, SWT.CHECK);
				btnMeasurementCountStop.setText("Measurement count stop condition");
				
				mcTextValue = new Text(settingsComposite, SWT.BORDER);
				GridData gd_mcTextValue = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_mcTextValue.widthHint = 60;
				mcTextValue.setLayoutData(gd_mcTextValue);
			}
		}
		
		m_bindingContext = initDataBindings();

		extensionComposite = new Composite(this, SWT.NONE);
		GridLayout gl_extensionComposite = new GridLayout(2, false);
		gl_extensionComposite.marginLeft = 10;
		gl_extensionComposite.marginHeight = 10;
		extensionComposite.setLayout(gl_extensionComposite);
		extensionComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		initExtensions(extensionComposite);
		
		refresh();
	}
	
	protected void initExtensions(Composite parent){
		//override in subclasses
	}

	@Override
	public void refresh() {
		
		List<EObject> usages = new ArrayList<EObject>();
		List<EObject> usageEvolutions = new ArrayList<EObject>();
		
		InputAlternative ia = alternative.getInputAlternative();
		
		if(ia != null){

			List<IResource> usageFileList = ia.getSubResources(ToolchainUtils.KEY_FILE_USAGE);
			List<IResource> usageEvoFileList = ia.getSubResources(ToolchainUtils.KEY_FILE_USAGEEVOLUTION);
			
			for(IResource usageFile : usageFileList){
				alternative.loadExternalModel((IFile)usageFile);
				usages.addAll(alternative.getModelRootObjects((IFile)usageFile));
			}
			
			for(IResource usageEvoFile : usageEvoFileList){
				alternative.loadExternalModel((IFile)usageEvoFile);
				for(EObject o : alternative.getModelRootObjects((IFile)usageEvoFile)){
					if(o instanceof UsageEvolution){
						UsageEvolution ue = (UsageEvolution)o;
						usageEvolutions.addAll(ue.getUsages());
					}
				}
			}
		}
		
		//update usage and usage evolution selection choices
		Diffs.computeLazyListDiff(usageModelsObs, usages).applyTo(usageModelsObs);
		Diffs.computeLazyListDiff(usageEvolutionModelsObs, usageEvolutions).applyTo(usageEvolutionModelsObs);

		if(m_bindingContext != null){
			m_bindingContext.updateTargets();
		}
		
		if(comboUsage.getCombo().isDisposed()){
			comboUsage.refresh();
		}
		if(comboUsageEvo.getCombo().isDisposed()){
			comboUsageEvo.refresh();
		}
		
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		
		// Usage selection combo
		IObservableValue usageEmfObs = EMFEditObservables.observeValue(alternative.getEditingDomain(), 
																	   initialModel, 
																	   ExperimentsPackage.Literals.INITIAL_MODEL__USAGE_MODEL);
		IViewerObservableValue usageSelectionObs = ViewerProperties.singleSelection().observe(comboUsage);
		bindingContext.bindValue(usageSelectionObs, usageEmfObs);
		
		// Usage evolution selection combo
		IObservableValue usageEvoEmfObs = EMFEditObservables.observeValue(alternative.getEditingDomain(), 
																		  initialModel, 
																		  ExperimentsPackage.Literals.INITIAL_MODEL__USAGE_EVOLUTION);
		IViewerObservableValue usageEvoSelectionObs = ViewerProperties.singleSelection().observe(comboUsageEvo);
		bindingContext.bindValue(usageEvoSelectionObs, usageEvoEmfObs);

		if(stStopCondition != null){

			btnSimulationTimeStop.setEnabled(true);
			stTextValue.setEnabled(true);

			IObservableValue stStopConditionObserveValue = EMFEditObservables.observeValue(alternative.getEditingDomain(), 
																	stStopCondition, Literals.SIM_TIME_STOP_CONDITION__SIMULATION_TIME);

			//text
			IObservableValue observeText = WidgetProperties.text(new int[]{SWT.Modify, SWT.FocusOut}).observe(stTextValue);
			UpdateValueStrategy stringToInt = new UpdateValueStrategy();
			stringToInt.setConverter(new StringToInt());
			UpdateValueStrategy intToString = new UpdateValueStrategy();
			intToString.setConverter(new IntToString());
			bindingContext.bindValue(observeText, stStopConditionObserveValue, stringToInt, intToString);

			//button
			IObservableValue observeBtnSelection = WidgetProperties.selection().observe(btnSimulationTimeStop);
			UpdateValueStrategy boolToInt = new UpdateValueStrategy();
			boolToInt.setConverter(new BoolToInt());
			UpdateValueStrategy intToBool = new UpdateValueStrategy();
			intToBool.setConverter(new IntToBool());
			bindingContext.bindValue(observeBtnSelection, stStopConditionObserveValue, boolToInt, intToBool);

			//text
			IObservableValue observeTextEnabled = WidgetProperties.enabled().observe(stTextValue);
			bindingContext.bindValue(observeTextEnabled, stStopConditionObserveValue, boolToInt, intToBool);
			
		}
		else{
			btnSimulationTimeStop.setEnabled(false);
			stTextValue.setEnabled(false);
		}
		
		if(mcStopCondition != null){

			btnMeasurementCountStop.setEnabled(true);
			mcTextValue.setEnabled(true);

			IObservableValue mcStopConditionObserve = EMFEditObservables.observeValue(alternative.getEditingDomain(), 
																	mcStopCondition, Literals.MEASUREMENT_COUNT_STOP_CONDITION__MEASUREMENT_COUNT);

			//text
			IObservableValue observeText = WidgetProperties.text(new int[]{SWT.Modify, SWT.FocusOut}).observe(mcTextValue);
			UpdateValueStrategy stringToInt = new UpdateValueStrategy();
			stringToInt.setConverter(new StringToInt());
			UpdateValueStrategy intToString = new UpdateValueStrategy();
			intToString.setConverter(new IntToString());
			bindingContext.bindValue(observeText, mcStopConditionObserve, stringToInt, intToString);

			//button
			IObservableValue observeBtnSelection = WidgetProperties.selection().observe(btnMeasurementCountStop);
			UpdateValueStrategy boolToInt = new UpdateValueStrategy();
			boolToInt.setConverter(new BoolToInt());
			UpdateValueStrategy intToBool = new UpdateValueStrategy();
			intToBool.setConverter(new IntToBool());
			bindingContext.bindValue(observeBtnSelection, mcStopConditionObserve, boolToInt, intToBool);

			//text
			IObservableValue observeTextEnabled = WidgetProperties.enabled().observe(mcTextValue);
			bindingContext.bindValue(observeTextEnabled, mcStopConditionObserve, boolToInt, intToBool);
		}
		else{
			btnMeasurementCountStop.setEnabled(false);
			mcTextValue.setEnabled(false);
		}

		return bindingContext;
	}
	
	private static class BoolToInt extends Converter{

		public BoolToInt() {
			super(Boolean.class, Integer.class);
		}

		@Override
		public Object convert(Object fromObject) {
			boolean enabled = (Boolean)fromObject;
			if(enabled){
				return 10;
			}
			else{
				return -1;
			}
		}
		
	}
	
	private static class IntToBool extends Converter{

		public IntToBool() {
			super(Integer.class, Boolean.class);
		}

		@Override
		public Object convert(Object fromObject) {
			Integer i = (Integer)fromObject;
			if(i.intValue() >= 0){
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		}
		
	}
}
