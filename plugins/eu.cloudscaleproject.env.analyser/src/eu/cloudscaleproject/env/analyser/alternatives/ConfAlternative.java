package eu.cloudscaleproject.env.analyser.alternatives;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.emf.core.internal.resources.PathmapManager;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationFactory;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationPackage;
import org.palladiosimulator.experimentautomation.abstractsimulation.FileDatasource;
import org.palladiosimulator.experimentautomation.abstractsimulation.MeasurementCountStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.SimTimeStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.StopCondition;
import org.palladiosimulator.experimentautomation.application.tooladapter.simulizar.model.SimuLizarConfiguration;
import org.palladiosimulator.experimentautomation.application.tooladapter.simulizar.model.SimulizartooladapterFactory;
import org.palladiosimulator.experimentautomation.experiments.Experiment;
import org.palladiosimulator.experimentautomation.experiments.ExperimentRepository;
import org.palladiosimulator.experimentautomation.experiments.ExperimentsFactory;
import org.palladiosimulator.experimentautomation.experiments.InitialModel;
import org.palladiosimulator.experimentautomation.experiments.NestedIntervalsDoubleValueProvider;
import org.palladiosimulator.experimentautomation.experiments.ValueProvider;
import org.palladiosimulator.experimentautomation.experiments.Variation;
import org.palladiosimulator.experimentautomation.variation.VariationRepository;
import org.palladiosimulator.experimentautomation.variation.VariationType;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricDescriptionRepository;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointFactory;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointPackage;
import org.palladiosimulator.pcmmeasuringpoint.UsageScenarioMeasuringPoint;
import org.palladiosimulator.servicelevelobjective.HardThreshold;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.palladiosimulator.simulizar.monitorrepository.Intervall;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryFactory;
import org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum;
import org.scaledl.usageevolution.UsageEvolution;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.usagemodel.ClosedWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.OpenWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.Workload;
import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfAlternative extends EditorInputEMF{
			
	private static final long serialVersionUID = 1L;
	
	public static final String KEY_NAME = "name";	
	private final Type type;
	
	public static final String PROP_INPUT_ALT_SET = ConfAlternative.class.getName() + "propInputAltSet";
	public static final String PROP_USAGE_EVOLUTION_SET = ConfAlternative.class.getName() + "propUsageEvolutionSet";

	
	public enum Type {
		NORMAL, CAPACITY, SCALABILITY;
		
		public String toString() {
			switch (ConfAlternative.Type.this) {
			case NORMAL:
				return "Normal";
			case CAPACITY:
				return "Capacity";
			case SCALABILITY:
				return "Scalability";
			default:
				return "";
			}
		};
	}
		
	public ConfAlternative(IProject project, IFolder folder, Type type, AdapterFactory factory){
		super(project, folder, factory);
		
		this.type = type;
		
		//create and set defaults
		Experiment exp = getExperiment();
		configureExperiment(exp);
		
		initializeCommon(exp);
		
		if(Type.NORMAL.equals(type)){
			initializeNormal(exp);
		}
		else if(Type.CAPACITY.equals(type)){
			initializeCapacity(exp);
		}
		else if(Type.SCALABILITY.equals(type)){
			initializeScalability(exp);
		}
	}
	
	public Type getTypeEnum(){
		String type = getType();
		if(type == null){
			return null;
		}
		return Type.valueOf(type);
	}
	
	public UsageEvolution getUsageEvolution(){
		IResource ueFile = getSubResource(ToolchainUtils.KEY_FILE_USAGEEVOLUTION);
		if(ueFile == null){
			return null;
		}
		
		Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)ueFile);
		EObject eobject = res.getContents().isEmpty() ? null : res.getContents().get(0);
		
		if(eobject instanceof UsageEvolution){
			return (UsageEvolution)eobject;
		}
		return null;
	}
	
	public void setUsageEvolution(EditorInputFolder res){
		IResource fileUsageEvo = res.getSubResource(ToolchainUtils.KEY_FILE_USAGEEVOLUTION);
		setSubResource(ToolchainUtils.KEY_FOLDER_USAGEEVOLUTION_ALT, res.getResource());
		if(fileUsageEvo == null){
			return;
		}
		
		Resource resUE = ExplorerProjectPaths.getEmfResource(resSet, (IFile)fileUsageEvo);
		
		EObject eobject = resUE.getContents().isEmpty() ? null : resUE.getContents().get(0);
		Experiment exp = getExperiment();
		if(exp.getInitialModel() != null && eobject instanceof UsageEvolution){
			exp.getInitialModel().setUsageEvolution((UsageEvolution)eobject);
		}
		
		setDirty(true);
		firePropertyChange(PROP_USAGE_EVOLUTION_SET, null, eobject);
	}
	
	public void setInitialModel(InputAlternative inputAlt){
		
		Experiment exp = getExperiment();
		
		InitialModel initialModel = exp.getInitialModel();
		if(inputAlt == null && initialModel != null){
			exp.setInitialModel(null);
			return;
		}
		
		if(initialModel == null){
			initialModel = ExperimentsFactory.eINSTANCE.createInitialModel();
			exp.setInitialModel(initialModel);
		}
		
		//set initial model
		{
			//allocation
			{
				IResource file = inputAlt.getSubResource(ToolchainUtils.KEY_FILE_ALLOCATION);
				if(file != null && file.exists()){
					Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)file);
					if(!res.getContents().isEmpty()){
						initialModel.setAllocation((Allocation)res.getContents().get(0));
					}
				}
				else{
					initialModel.setAllocation(null);
				}
			}
			
			//usage			
			{
				IResource file = inputAlt.getSubResource(ToolchainUtils.KEY_FILE_USAGE);
				if(file != null && file.exists()){
					Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)file);
					if(!res.getContents().isEmpty()){
						initialModel.setUsageModel((UsageModel)res.getContents().get(0));
					}
				}
				else{
					initialModel.setUsageModel(null);
				}
			}
			
			//usageevolution
			UsageEvolution ue = getUsageEvolution();
			if(ue != null){
				initialModel.setUsageEvolution(ue);
			}
			
		}
		
		//load and set default resources from plugin
		ResourceSet tmpResSet = new ResourceSetImpl();
		URI uMiddleware = PathmapManager.denormalizeURI(URI.createURI("pathmap://PCM_MODELS/Glassfish.repository"));
		URI uEventMiddleware = PathmapManager.denormalizeURI(URI.createURI("pathmap://PCM_MODELS/default_event_middleware.repository"));
		Resource resMRep = tmpResSet.getResource(uMiddleware, true);
		Resource resEMRep = tmpResSet.getResource(uEventMiddleware, true);
		initialModel.setMiddlewareRepository((Repository)resMRep.getContents().get(0));
		initialModel.setEventMiddleWareRepository((Repository)resEMRep.getContents().get(0));		
		
		configureInput(exp, initialModel, inputAlt);
		
		setSubResource(ToolchainUtils.KEY_FOLDER_ANALYSER_INPUT_ALT, inputAlt.getResource());
		
		setDirty(true);
		firePropertyChange(PROP_INPUT_ALT_SET, null, inputAlt);
	}
	
	private void configureInput(Experiment exp, InitialModel initialModel, InputAlternative inputAlt){
		if(Type.NORMAL.equals(type)){
			
		}
		else if(Type.CAPACITY.equals(type)){
			configureCapacity(exp, initialModel);
		}
		else if(Type.SCALABILITY.equals(type)){
			configureCapacity(exp, initialModel);
		}
	}
	
	private void configureTool(SimuLizarConfiguration simulizarConf){
		if(Type.NORMAL.equals(type)){
			
		}
		else if(Type.CAPACITY.equals(type)){
			configureToolCapacity(simulizarConf);
		}
		else if(Type.SCALABILITY.equals(type)){
			configureToolScalability(simulizarConf);
		}
	}
	
	// Helper methods for retrieving model objects ////////////////////
	
	public void createEMFResource(String newFilename, String key, EObject rootObject){
		IFile file = ((IFolder)getResource()).getFile(newFilename);
		this.setSubResource(key, file);
		Resource resMp = ExplorerProjectPaths.getEmfResource(resSet, file);
		resMp.getContents().clear();
		resMp.getContents().add(rootObject);
	}
	
	public Experiment getExperiment() {
		
		IResource expFile = getSubResource(ToolchainUtils.KEY_FILE_EXPERIMENTS);
		if(expFile == null || !expFile.exists()){
			List<IFile> files = PCMResourceSet.findResource(getResource(), PCMModelType.EXPERIMENTS.getFileExtension());			
			if(files.size() > 0){
				//TODO: for now just use fist model file found
				expFile = files.get(0);
			}
			else{
				//create new Experiment model file
				expFile = getResource().getFile(PCMModelType.EXPERIMENTS.getFullName());
				setSubResource(ToolchainUtils.KEY_FILE_EXPERIMENTS, expFile);
			}			
		}
		
		Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)expFile);
		EObject root = res.getContents().size() > 0 ? res.getContents().get(0) : null;
		if(root == null){
			ExperimentRepository expRep = ExperimentsFactory.eINSTANCE.createExperimentRepository();
			root = expRep;
			res.getContents().add(expRep);
		}
		
		if(!(root instanceof ExperimentRepository)){
			throw new IllegalArgumentException("Experiments model with root object type other then ExperimentRepository is not supported!");
		}
		
		ExperimentRepository expRep = (ExperimentRepository)root;
		Experiment firsExperiment = expRep.getExperiments().isEmpty() ? null : expRep.getExperiments().get(0);
		if(firsExperiment == null){
			firsExperiment = ExperimentsFactory.eINSTANCE.createExperiment();
			firsExperiment.setRepetitions(1);
			expRep.getExperiments().add(firsExperiment);
		}
		
		if(firsExperiment.getToolConfiguration().isEmpty()){
			configureExperiment(firsExperiment);
		}
						
		return (Experiment)firsExperiment;		
	}
	
	public InitialModel getInitialModel(){
		Experiment exp = getExperiment();
		return exp != null ? exp.getInitialModel() : null;
	}
	
	public List<MeasuringPoint> getMeasuringPointObjects(EClass clazz){

		List<MeasuringPoint> mps = new ArrayList<MeasuringPoint>();
		
		for(Resource res : resSet.getResources()){
			if(res != null && !res.getContents().isEmpty()){
				EObject root = res.getContents().get(0);
				if(clazz.equals(root.eClass())){
					mps.add((MeasuringPoint)root);
				}
			}
		}
		return mps;
	}
	
	public List<MeasuringPoint> getMeasuringPointObjects(){

		List<MeasuringPoint> mps = new ArrayList<MeasuringPoint>();
		
		for(Resource res : resSet.getResources()){
			if(res != null && !res.getContents().isEmpty()){
				EObject root = res.getContents().get(0);
				if(root instanceof MeasuringPoint){
					mps.add((MeasuringPoint)root);
				}
			}
		}
		return mps;
	}
	
	public List<Variation> getVariationObjects(){
		
		List<Variation> out = new ArrayList<Variation>();
		
		Experiment experiment = getExperiment();
		for(Variation v : experiment.getVariations()){
			out.add(v);
		}
		
		return out;
	}
	
	public List<VariationType> getVariationTypes(){
		
		List<VariationType> out = new ArrayList<VariationType>();
		
		URI variations = PathmapManager.denormalizeURI(URI.createURI("pathmap://ENVIRONMENT_ANALYSER/pcm.variation"));
		Resource resource = resSet.getResource(variations, true);
		List<EObject> content = resource.getContents();
		if(!content.isEmpty()){
			EObject object = content.get(0);
			if(object instanceof VariationRepository){
				VariationRepository vRep = (VariationRepository)object;
				for(VariationType v : vRep.getVariation()){
					out.add(v);
				}
			}
		}
		return out;
	}
	
	public VariationType getVariationType(String id){
		
		URI variations = PathmapManager.denormalizeURI(URI.createURI("pathmap://ENVIRONMENT_ANALYSER/pcm.variation"));
		Resource resource = resSet.getResource(variations, true);
		List<EObject> content = resource.getContents();
		if(!content.isEmpty()){
			EObject object = content.get(0);
			if(object instanceof VariationRepository){
				VariationRepository vRep = (VariationRepository)object;
				for(VariationType v : vRep.getVariation()){
					if(id.equals(v.getId())){
						return v;
					}
				}
			}
		}
		return null;
	}
	
	public List<ValueProvider> getVariationValueProviders(EClass clazz){
		
		List<ValueProvider> out = new ArrayList<ValueProvider>();
		
		for(Variation v : getExperiment().getVariations()){
			if(v.getValueProvider().eClass().equals(clazz)){
				out.add(v.getValueProvider());
			}
		}
		
		return out;
	}
	
	public List<MetricDescription> getMetricDescriptions(){
		
		List<MetricDescription> out = new ArrayList<MetricDescription>();
		
		Resource res = getResourceSet().getResource(URI.createURI("pathmap://METRIC_SPEC_MODELS/models/commonMetrics.metricspec"), true);
		EObject object = res.getContents().isEmpty() ? null : res.getContents().get(0);
		
		if(object instanceof MetricDescriptionRepository){
			MetricDescriptionRepository mdRep = (MetricDescriptionRepository)object;
			for(MetricDescription md : mdRep.getMetricDescriptions()){
				out.add(md);
			}
		}
		
		return out;
	}
	
	public MetricDescription getMetricDescription(){
		List<MetricDescription> metDescriptions = getMetricDescriptions();
		return metDescriptions.isEmpty() ? null : metDescriptions.get(0);
	}
	
	public List<StopCondition> getStopConditions(EClass clazz){
		List<StopCondition> out = new ArrayList<StopCondition>();
		
		for(StopCondition stopCon : getExperiment().getStopConditions()){
			if(stopCon.getClass().equals(clazz)){
				out.add(stopCon);
			}
		}
		
		return out;
	}
	
	public List<MonitorRepository> getMonitorRepositories(){
		List<MonitorRepository> out = new ArrayList<MonitorRepository>();
		
		for(IResource file : getSubResources(ToolchainUtils.KEY_FILE_MONITOR)){
			if(file instanceof IFile && file.exists()){
				Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)file);
				for(EObject eobj : res.getContents()){
					if(eobj instanceof MonitorRepository){
						MonitorRepository rep = (MonitorRepository)eobj;
						out.add(rep);
					}
				}
			}
		}
		
		return out;
	}
	
	public List<ServiceLevelObjectiveRepository> getSLORepositories(){
		List<ServiceLevelObjectiveRepository> out = new ArrayList<ServiceLevelObjectiveRepository>();
		
		for(IResource file : getSubResources(ToolchainUtils.KEY_FILE_SLO)){
			if(file instanceof IFile && file.exists()){
				Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)file);
				for(EObject eobj : res.getContents()){
					if(eobj instanceof ServiceLevelObjectiveRepository){
						ServiceLevelObjectiveRepository rep = (ServiceLevelObjectiveRepository)eobj;
						out.add(rep);
					}
				}
			}
		}
		
		return out;
	}
	///////////////////////////////////////////////////////////////////

	
	private void configureExperiment(Experiment exp){
		
		ResourceProvider resultResProvider = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.ANALYSER_RES_ID);
		IEditorInputResource resultAlternative = resultResProvider.getResource(this.getResource().getName());
		
		if(resultAlternative == null){
			resultAlternative = resultResProvider.createNewResource(getResource().getName(), getName());
			resultAlternative.save();
		}
		
		//create data source - a.k.a where the result will be saved
		FileDatasource ds = AbstractsimulationFactory.eINSTANCE.createFileDatasource();
		ds.setLocation(resultAlternative.getResource().getLocation().toString());
		
		SimuLizarConfiguration conf = SimulizartooladapterFactory.eINSTANCE.createSimuLizarConfiguration();
		conf.setDatasource(ds);
		configureTool(conf);
		
		exp.getToolConfiguration().clear();
		exp.getToolConfiguration().add(conf);
	}

	@Override
	protected void doLoad(){
		
		super.doLoad();
		
		try {
			loadModels();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	private final void loadModels() throws IOException {
		
		for (IResource f : getSubResources(ToolchainUtils.KEY_FILE_EXPERIMENTS)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)f);
			res.unload();
			res.load(null);
		}
		for (IResource f : getSubResources(ToolchainUtils.KEY_FILE_MONITOR)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)f);
			res.unload();
			res.load(null);		
		}
		for (IResource f : getSubResources(ToolchainUtils.KEY_FILE_VARIATIONS)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)f);
			res.unload();
			res.load(null);		
		}
		for (IResource f : getSubResources(ToolchainUtils.KEY_FILE_MESURPOINTS)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)f);
			res.unload();
			res.load(null);		
		}
		for (IResource f : getSubResources(ToolchainUtils.KEY_FILE_SLO)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)f);
			res.unload();
			res.load(null);		
		}
		
		//load plugin models into resource set
		URI metricDescriptions = PathmapManager.denormalizeURI(URI.createURI("pathmap://METRIC_SPEC_MODELS/models/commonMetrics.metricspec"));
		resSet.getResource(metricDescriptions, true);
	}
	
	private void initializeCommon(Experiment exp){
		
		InitialModel initialModel = getInitialModel();
		if(initialModel == null){
			initialModel = ExperimentsFactory.eINSTANCE.createInitialModel();
			exp.setInitialModel(initialModel);
		}
				
		//create and set measurement stop condition
		{
			List<StopCondition> stopConditions = getStopConditions(AbstractsimulationPackage.Literals.MEASUREMENT_COUNT_STOP_CONDITION);
			if(stopConditions.isEmpty()){
				MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
				msc.setMeasurementCount(100);
				exp.getStopConditions().add(msc);
			}
		}
		
		//create and set time stop condition
		{
			List<StopCondition> stopConditions = getStopConditions(AbstractsimulationPackage.Literals.SIM_TIME_STOP_CONDITION);
			if(stopConditions.isEmpty()){
				SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
				tsc.setSimulationTime(-1);
				exp.getStopConditions().add(tsc);
			}
		}
		
		//create measuring point repository
		//TODO: feature is not supported jet by the analyser
	
		//create monitor
		{
			List<MonitorRepository> monitorReps = getMonitorRepositories();
			MonitorRepository monitorRep = null;
			if(monitorReps.isEmpty()){
				monitorRep = MonitorrepositoryFactory.eINSTANCE.createMonitorRepository();
				createEMFResource("analyser.monitorrepository", ToolchainUtils.KEY_FILE_MONITOR, monitorRep);
				Monitor monitor = MonitorrepositoryFactory.eINSTANCE.createMonitor();
				monitorRep.getMonitors().add(monitor);
				initialModel.setMonitorRepository(monitorRep);
			}
		}
		
		//create slo
		{
			List<ServiceLevelObjectiveRepository> sloReps = getSLORepositories();
			ServiceLevelObjectiveRepository sloRep = null;
			if(sloReps.isEmpty()){
				sloRep = ServicelevelObjectiveFactory.eINSTANCE.createServiceLevelObjectiveRepository();
				createEMFResource("analyser.slo", ToolchainUtils.KEY_FILE_SLO, sloRep);
				
				ServiceLevelObjective slo = ServicelevelObjectiveFactory.eINSTANCE.createServiceLevelObjective();
				slo.setMetricDescription(MetricDescriptionConstants.RESPONSE_TIME_METRIC);
				sloRep.getServicelevelobjectives().add(slo);
				initialModel.setServiceLevelObjectives(sloRep);
			}
		}
	}
	
	private void initializeNormal(Experiment exp){
		
	}
	
	private void initializeCapacity(Experiment exp) {
		
		exp.setName("Capacity measurement");
		
		//create measuring point
		UsageScenarioMeasuringPoint measurePoint = PcmmeasuringpointFactory.eINSTANCE.createUsageScenarioMeasuringPoint();
		createEMFResource("analyser.measuringpoint", ToolchainUtils.KEY_FILE_MESURPOINTS, measurePoint);
		
		//create variation
		Variation var = ExperimentsFactory.eINSTANCE.createVariation();
		exp.getVariations().clear();
		NestedIntervalsDoubleValueProvider dvp = ExperimentsFactory.eINSTANCE.createNestedIntervalsDoubleValueProvider();
		dvp.setMinValue(0);
		dvp.setMaxValue(400);
		var.setValueProvider(dvp);
		//TODO: set type var.setType();
		exp.getVariations().clear();
		exp.getVariations().add(var);
		
		URI variations = PathmapManager.denormalizeURI(URI.createURI("pathmap://ENVIRONMENT_ANALYSER/pcm.variation"));
		resSet.getResource(variations, true);
	}
	
	private void initializeScalability(Experiment exp){
		//TODO: implement
	}
	
	private void configureCapacity(Experiment exp, InitialModel initialModel){
		//retrieve usage scenario
		EList<UsageScenario> usList = initialModel.getUsageModel().getUsageScenario_UsageModel();
		UsageScenario usageScenario = usList.size() > 0 ? usList.get(0) : null;
		
		if(usageScenario == null){
			DialogUtils.openWarning("Usage scenario can not be found. Please configure all properties regarding this model manually.");
		}
		
		List<MeasuringPoint> mps = getMeasuringPointObjects(PcmmeasuringpointPackage.Literals.USAGE_SCENARIO_MEASURING_POINT);
		UsageScenarioMeasuringPoint usmp = null;
		if(!mps.isEmpty()){
			usmp = (UsageScenarioMeasuringPoint)mps.get(0);
			usmp.setUsageScenario(usageScenario);
		}
		else{
			DialogUtils.openWarning("Usage scenario measuring point has been removed! Please create and configure it manually.");
		}
		
		List<MonitorRepository> monitorReps = getMonitorRepositories();
		if(!monitorReps.isEmpty()){
			MonitorRepository monitorRep = monitorReps.get(0);
			if(!monitorRep.getMonitors().isEmpty()){		
				Monitor monitor = monitorRep.getMonitors().get(0);

				if(usmp != null){
					monitor.setMeasuringPoint(usmp);
				}
				
				monitor.getMeasurementSpecification().clear();
				MeasurementSpecification specification = MonitorrepositoryFactory.eINSTANCE.createMeasurementSpecification();
				specification.setStatisticalCharacterization(StatisticalCharacterizationEnum.ARITHMETIC_MEAN);
				Intervall interval = MonitorrepositoryFactory.eINSTANCE.createIntervall();
				interval.setIntervall(10.0);
				
				MetricDescription metric = null;
				for(MetricDescription md : getMetricDescriptions()){
					if("_6rYmYs7nEeOX_4BzImuHbA".equals(md.getId())){
						metric = md;
					}
				}
				
				if(metric == null){
					DialogUtils.openError("Required metric description (Response time) can not be found!");
				}
				else{
					specification.setMetricDescription(metric);					
				}
				
				specification.setTemporalRestriction(interval);
				monitor.getMeasurementSpecification().add(specification);
			}
			else{
				DialogUtils.openWarning("Monitor object has been removed! Please create and configure it manually.");
			}
		}
		else{
			DialogUtils.openWarning("Monitor repository has been removed! Please create and configure it manually.");
		}
				
		List<Variation> variations = getVariationObjects();
		if(!variations.isEmpty()){
			Variation v = variations.get(0);

			if(usageScenario != null){
				Workload workload = usageScenario.getWorkload_UsageScenario();
				if(workload == null){
					DialogUtils.openWarning("Usage scenario workload can not be found! Please configure it manually.");
				}
				
				VariationType type = null;
				if(workload instanceof ClosedWorkload){
					type = getVariationType("_zUZqID5zEeCEPJs72ZSOBg");
				}
				else if(workload instanceof OpenWorkload){
					type = getVariationType("_59qtgBU-EeKgFO0nt5OPnA");
				}
				
				if(type != null){
					v.setType(type);
				}
				else{
					DialogUtils.openError("Required variation type can not be found! Please configure it manually.");
				}
			
				v.setVariedObjectId(usageScenario.getId());
			}
		}
		else{
			DialogUtils.openWarning("Variation has been removed! Please create and configure it manually.");
		}
		
		List<ServiceLevelObjectiveRepository> sloReps = getSLORepositories();
		if(!sloReps.isEmpty()){
			ServiceLevelObjectiveRepository sloRep = sloReps.get(0);
			if(!sloRep.getServicelevelobjectives().isEmpty()){
				ServiceLevelObjective slo = sloRep.getServicelevelobjectives().get(0);
				if(usmp != null){
					slo.setMeasuringPoint(usmp);
				}
				
				HardThreshold ut = ServicelevelObjectiveFactory.eINSTANCE.createHardThreshold();
				//TODO: set limit to 'HardThreshold'
				slo.setUpperThreshold(ut);
			}
			else{
				DialogUtils.openWarning("Service level objective has been removed! Please create and configure it manually.");
			}
		}
		else{
			DialogUtils.openWarning("SLO repository has been removed! Please create and configure it manually.");
		}
	}
	
	private void configureToolCapacity(SimuLizarConfiguration simulizarConf) {
		
		//create measurement stop condition
		MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
		msc.setMeasurementCount(100);
		
		//create time stop condition
		SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
		tsc.setSimulationTime(-1);
		
		simulizarConf.getStopConditions().add(msc);
		simulizarConf.getStopConditions().add(tsc);
	}
	
	private void configureToolScalability(SimuLizarConfiguration simulizarConf) {
		
		//create measurement stop condition
		MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
		msc.setMeasurementCount(100);
		
		//create time stop condition
		SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
		tsc.setSimulationTime(-1);
		
		simulizarConf.getStopConditions().add(msc);
		simulizarConf.getStopConditions().add(tsc);
	}
}
