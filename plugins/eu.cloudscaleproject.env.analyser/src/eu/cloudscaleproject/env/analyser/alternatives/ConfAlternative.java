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
import org.palladiosimulator.experimentautomation.abstractsimulation.FileDatasource;
import org.palladiosimulator.experimentautomation.abstractsimulation.MeasurementCountStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.SimTimeStopCondition;
import org.palladiosimulator.experimentautomation.application.tooladapter.simulizar.model.SimuLizarConfiguration;
import org.palladiosimulator.experimentautomation.application.tooladapter.simulizar.model.SimulizartooladapterFactory;
import org.palladiosimulator.experimentautomation.experiments.Experiment;
import org.palladiosimulator.experimentautomation.experiments.ExperimentRepository;
import org.palladiosimulator.experimentautomation.experiments.ExperimentsFactory;
import org.palladiosimulator.experimentautomation.experiments.InitialModel;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointFactory;
import org.palladiosimulator.pcmmeasuringpoint.UsageScenarioMeasuringPoint;
import org.palladiosimulator.servicelevelobjective.HardThreshold;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.pms.PerformanceMeasurement;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;
import org.palladiosimulator.simulizar.pms.PmsFactory;
import org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum;
import org.palladiosimulator.simulizar.pms.impl.PmsFactoryImpl;
import org.scaledl.usageevolution.UsageEvolution;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;
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
	}
	
	public InitialModel getInitialModel(){
		Experiment exp = getExperiment();
		if(exp != null){
			return exp.getInitialModel();
		}
		return null;
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
	}
	
	private void configureInput(Experiment exp, InitialModel initialModel, InputAlternative inputAlt){
		if(Type.NORMAL.equals(type)){
			
		}
		else if(Type.CAPACITY.equals(type)){
			configureInputCapacity(exp, initialModel, inputAlt);
		}
		else if(Type.SCALABILITY.equals(type)){
			
		}
	}
	
	private void configureTool(SimuLizarConfiguration simulizarConf){
		if(Type.NORMAL.equals(type)){
			
		}
		else if(Type.CAPACITY.equals(type)){
			configureToolCapacity(simulizarConf);
		}
		else if(Type.SCALABILITY.equals(type)){
			
		}
	}
	
	// Helper methods for retrieving model objects ////////////////////
	
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
			expRep.getExperiments().add(firsExperiment);
		}
		
		if(firsExperiment.getToolConfiguration().isEmpty()){
			configureExperiment(firsExperiment);
		}
				
		return (Experiment)firsExperiment;		
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
	
	/*
	public IFile getPMS(){
		return ()getSubResource(ToolchainUtils.KEY_FILE_PMS);
	}
	
	public List<IFile> getMeasuringPoints(){
		return getFileResources(ToolchainUtils.KEY_FILE_MESURPOINTS);
	}
	
	public IFile getSLO(){
		return getFileResource(ToolchainUtils.KEY_FILE_SLO);
	}
	*/

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
		for (IResource f : getSubResources(ToolchainUtils.KEY_FILE_PMS)) {
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
	
	//
	// Capacity measuring specifics
	//
	
	protected void configureInputCapacity(Experiment exp, InitialModel initialModel, InputAlternative inputAlt) {
		
		exp.setRepetitions(1);
		exp.setName("Capacity measurement");
		exp.getStopConditions().clear();
				
		//create and set measurement stop condition
		MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
		msc.setMeasurementCount(100);
		exp.getStopConditions().add(msc);
		
		//create and set time stop condition
		SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
		tsc.setSimulationTime(-1);
		exp.getStopConditions().add(tsc);
		
		//retrieve usage scenario
		EList<UsageScenario> usList = initialModel.getUsageModel().getUsageScenario_UsageModel();
		UsageScenario usageScenario = usList.size() > 0 ? usList.get(0) : null;
		
		//create measuring point
		UsageScenarioMeasuringPoint measurePoint = PcmmeasuringpointFactory.eINSTANCE.createUsageScenarioMeasuringPoint();
		measurePoint.setUsageScenario(usageScenario);
		
		IFile mesuringPointFile = ((IFolder)getResource()).getFile("analyser.measuringpoint");
		this.setSubResource(ToolchainUtils.KEY_FILE_MESURPOINTS, mesuringPointFile);
		Resource resMp = ExplorerProjectPaths.getEmfResource(resSet, mesuringPointFile);
		resMp.getContents().clear();
		resMp.getContents().add(measurePoint);
		
		//create pms
		PMSModel pms = PmsFactoryImpl.eINSTANCE.createPMSModel();
		PerformanceMeasurement pm = PmsFactoryImpl.eINSTANCE.createPerformanceMeasurement();
		pm.setMeasuringPoint(measurePoint);
		MeasurementSpecification ms = PmsFactory.eINSTANCE.createMeasurementSpecification();
		ms.setPerformanceMetric(PerformanceMetricEnum.RESPONSE_TIME);
		ms.setStatisticalCharacterization(StatisticalCharacterizationEnum.ARITHMETIC_MEAN);
		Intervall intervall = PmsFactory.eINSTANCE.createIntervall();
		intervall.setIntervall(10.0);
		ms.setTemporalRestriction(intervall);
		pm.getMeasurementSpecification().add(ms);
		pms.getPerformanceMeasurements().add(pm);
		
		IFile pmsFile = ((IFolder)getResource()).getFile("analyser.pms");
		this.setSubResource(ToolchainUtils.KEY_FILE_PMS, pmsFile);
		Resource resPms = ExplorerProjectPaths.getEmfResource(resSet, pmsFile);
		resPms.getContents().clear();
		resPms.getContents().add(pms);
		initialModel.setPlatformMonitoringSpecification(pms);
		
		//create slo
		ServiceLevelObjectiveRepository sloRep = ServicelevelObjectiveFactory.eINSTANCE.createServiceLevelObjectiveRepository();
		ServiceLevelObjective slo = ServicelevelObjectiveFactory.eINSTANCE.createServiceLevelObjective();
		slo.setMeasuringPoint(measurePoint);
		slo.setMetricDescription(MetricDescriptionConstants.RESPONSE_TIME_METRIC);
		
		HardThreshold ht = ServicelevelObjectiveFactory.eINSTANCE.createHardThreshold();
		//TODO: set limit to 'HardThreshold'
		slo.setUpperThreshold(ht);
		
		IFile sloFile = ((IFolder)getResource()).getFile("analyser.slo");
		this.setSubResource(ToolchainUtils.KEY_FILE_SLO, pmsFile);
		Resource resSlo = ExplorerProjectPaths.getEmfResource(resSet, sloFile);
		resSlo.getContents().clear();
		resSlo.getContents().add(sloRep);
	}
	
	protected void configureToolCapacity(SimuLizarConfiguration simulizarConf) {
		
		//create measurement stop condition
		MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
		msc.setMeasurementCount(100);
		
		//create time stop condition
		SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
		tsc.setSimulationTime(-1);
		
		//create variations
		//TODO: create variation
		//Variation var = VariationFactory.eINSTANCE.c
		
		simulizarConf.getStopConditions().add(msc);
		simulizarConf.getStopConditions().add(tsc);
	}
	
	//
	// Scalability measuring specifics
	//
	
	//TODO: implement scalability measurement
}
