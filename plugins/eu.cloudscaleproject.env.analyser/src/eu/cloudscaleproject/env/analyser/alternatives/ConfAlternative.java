package eu.cloudscaleproject.env.analyser.alternatives;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.measure.Measure;
import javax.measure.unit.Unit;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.emf.core.internal.resources.PathmapManager;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPointRepository;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationFactory;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationPackage;
import org.palladiosimulator.experimentautomation.abstractsimulation.FileDatasource;
import org.palladiosimulator.experimentautomation.abstractsimulation.MeasurementCountStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.MemoryDatasource;
import org.palladiosimulator.experimentautomation.abstractsimulation.SimTimeStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.StopCondition;
import org.palladiosimulator.experimentautomation.application.tooladapter.simulizar.model.SimuLizarConfiguration;
import org.palladiosimulator.experimentautomation.application.tooladapter.simulizar.model.SimulizartooladapterFactory;
import org.palladiosimulator.experimentautomation.experiments.Experiment;
import org.palladiosimulator.experimentautomation.experiments.ExperimentDesign;
import org.palladiosimulator.experimentautomation.experiments.ExperimentRepository;
import org.palladiosimulator.experimentautomation.experiments.ExperimentsFactory;
import org.palladiosimulator.experimentautomation.experiments.InitialModel;
import org.palladiosimulator.experimentautomation.experiments.NestedIntervalsLongValueProvider;
import org.palladiosimulator.experimentautomation.experiments.ResponseMeasurement;
import org.palladiosimulator.experimentautomation.experiments.ValueProvider;
import org.palladiosimulator.experimentautomation.experiments.Variation;
import org.palladiosimulator.experimentautomation.variation.VariationRepository;
import org.palladiosimulator.experimentautomation.variation.VariationType;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricDescriptionRepository;
import org.palladiosimulator.monitorrepository.Intervall;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryFactory;
import org.palladiosimulator.monitorrepository.StatisticalCharacterizationEnum;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.Workload;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointFactory;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointPackage;
import org.palladiosimulator.pcmmeasuringpoint.UsageScenarioMeasuringPoint;
import org.palladiosimulator.servicelevelobjective.HardThreshold;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.scaledl.usageevolution.UsageEvolution;

import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfAlternative extends AbstractConfigAlternative
{
	public static final String KEY_NAME = "name";
	private final Type type;

	public static final String PROP_INPUT_ALT_SET = ConfAlternative.class.getName() + "propInputAltSet";
	public static final String PROP_USAGE_EVOLUTION_SET = ConfAlternative.class.getName() + "propUsageEvolutionSet";
	
	private static final String ERROR_DIALOG_TITLE = "Analyser alternative error";
	private static final String WARNING_DIALOG_TITLE = "Analyser alternative warning";

	private final String ERR_INVALID_INPUT_ALTERNATIVE = "eu.cloudscaleproject.env.analyser.alternatives.ConfAlternative.invalidInputAlt";

	public enum Type
	{
		NORMAL, CAPACITY, SCALABILITY;

		public String toString()
		{
			switch (ConfAlternative.Type.this)
			{
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

	public ConfAlternative(IProject project, IFolder folder, Type type)
	{
		super(project, folder, ModelType.GROUP_EXPERIMENTS, CSTool.ANALYSER);

		this.type = type;
	}

	public Type getTypeEnum()
	{
		String type = getType();
		if (type == null)
		{
			return null;
		}
		return Type.valueOf(type);
	}

	private void configureInput(Experiment exp, InitialModel initialModel, InputAlternative inputAlt)
	{
		if (Type.NORMAL.equals(type))
		{
			configureNormal(exp, initialModel);
		} else if (Type.CAPACITY.equals(type))
		{
			configureCapacity(exp, initialModel);
		} else if (Type.SCALABILITY.equals(type))
		{
			configureScalability(exp, initialModel);
		}
	}

	private void configureTool(Experiment exp, SimuLizarConfiguration simulizarConf)
	{
		simulizarConf.getStopConditions().clear();
		
		for(StopCondition sc : exp.getStopConditions()){
			simulizarConf.getStopConditions().add(EcoreUtil.copy(sc));
		}
		
	}

	public InputAlternative getInputAlternative()
	{
		IResource res = getSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE);
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.ANALYSER_INPUT);
		if(rp != null){
			return (InputAlternative)rp.getResource(res);
		}
		return null;
	}

	public boolean setInputAlternative(InputAlternative inputAlt)
	{
		Experiment exp = getActiveExperiment();
		if(exp == null){
			return false;
		}
		
		InitialModel initialModel = exp.getInitialModel();

		if (inputAlt == null && initialModel != null)
		{
			exp.setInitialModel(null);
			return true;
		}

		inputAlt.validate();
		
		if (inputAlt.getSelfStatus().isDone())
		{
			getSelfStatus().removeWarning(ERR_INVALID_INPUT_ALTERNATIVE);
		} else
		{
			getSelfStatus().addWarning(ERR_INVALID_INPUT_ALTERNATIVE, IValidationStatus.SEVERITY_ERROR, "Invalid input alternative!");
			setSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE, inputAlt.getResource());
			setDirty(true);
			firePropertyChange(PROP_INPUT_ALT_SET, null, inputAlt);
			return false;
		}

		if (initialModel == null)
		{
			initialModel = ExperimentsFactory.eINSTANCE.createInitialModel();
			exp.setInitialModel(initialModel);
		}

		// set initial model
		{
			// allocation
			{
				IResource file = inputAlt.getSubResource(ToolchainUtils.KEY_FILE_ALLOCATION);
				if (file != null && file.exists())
				{
					Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) file);
					if (!res.getContents().isEmpty())
					{
						initialModel.setAllocation((Allocation) res.getContents().get(0));
					}
				} else
				{
					initialModel.setAllocation(null);
				}
			}

			// usage
			{
				IResource file = inputAlt.getSubResource(ToolchainUtils.KEY_FILE_USAGE);
				if (file != null && file.exists())
				{
					Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) file);
					if (!res.getContents().isEmpty())
					{
						initialModel.setUsageModel((UsageModel) res.getContents().get(0));
					}
				} else
				{
					initialModel.setUsageModel(null);
				}
			}
		}

		// set usage measuring point
		{
			IResource file = inputAlt.getSubResource(ToolchainUtils.KEY_FILE_USAGE);
			if (file != null && file.exists())
			{
				Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) file);
				if (!res.getContents().isEmpty())
				{
					UsageModel usageModel = (UsageModel) res.getContents().get(0);
					List<UsageScenario> usageScenarios = usageModel.getUsageScenario_UsageModel();
					if (usageScenarios != null && !usageScenarios.isEmpty())
					{
						UsageScenario us = usageScenarios.get(0);
						for (MeasuringPoint mp : getMeasuringPointObjects(PcmmeasuringpointPackage.Literals.USAGE_SCENARIO_MEASURING_POINT))
						{
							((UsageScenarioMeasuringPoint) mp).setUsageScenario(us);
						}
					}
				}
			}
		}

		// load and set default resources from plugin
		ResourceSet tmpResSet = new ResourceSetImpl();
		URI uMiddleware = PathmapManager.denormalizeURI(URI.createURI("pathmap://PCM_MODELS/Glassfish.repository"));
		URI uEventMiddleware = PathmapManager.denormalizeURI(URI.createURI("pathmap://PCM_MODELS/default_event_middleware.repository"));
		Resource resMRep = tmpResSet.getResource(uMiddleware, true);
		Resource resEMRep = tmpResSet.getResource(uEventMiddleware, true);
		initialModel.setMiddlewareRepository((Repository) resMRep.getContents().get(0));
		initialModel.setEventMiddleWareRepository((Repository) resEMRep.getContents().get(0));

		configureInput(exp, initialModel, inputAlt);

		setSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE, inputAlt.getResource());

		setDirty(true);
		firePropertyChange(PROP_INPUT_ALT_SET, null, inputAlt);

		return true;
	}

	// Helper methods for retrieving model objects ////////////////////

	public void createEMFResource(String newFilename, String key, EObject rootObject)
	{
		IFile file = ((IFolder) getResource()).getFile(newFilename);
		this.setSubResource(key, file);
		Resource resMp = ExplorerProjectPaths.getEmfResource(resSet, file);
		resMp.getContents().clear();
		resMp.getContents().add(rootObject);
	}

	public List<MeasuringPoint> getMeasuringPointObjects(EClass clazz)
	{

		List<MeasuringPoint> mps = new ArrayList<MeasuringPoint>();

		for (Resource res : new ArrayList<Resource>(resSet.getResources()))
		{
			if (res != null && !res.getContents().isEmpty())
			{
				EObject root = res.getContents().get(0);
				if (clazz.equals(root.eClass()))
				{
					mps.add((MeasuringPoint) root);
				} else if (root instanceof MeasuringPointRepository)
				{
					MeasuringPointRepository mpr = (MeasuringPointRepository) root;
					for (MeasuringPoint mp : mpr.getMeasuringPoints())
					{
						if (clazz.equals(mp.eClass()))
						{
							mps.add((MeasuringPoint) mp);
						}
					}
				}
			}
		}
		return mps;
	}

	public List<MeasuringPoint> getMeasuringPointObjects()
	{

		List<MeasuringPoint> mps = new ArrayList<MeasuringPoint>();

		for (Resource res : new ArrayList<Resource>(resSet.getResources()))
		{
			if (res != null && !res.getContents().isEmpty())
			{
				EObject root = res.getContents().get(0);
				if (root instanceof MeasuringPoint)
				{
					mps.add((MeasuringPoint) root);
				}
			}
		}

		for (MeasuringPointRepository mpr : getMeasuringPointRepositories())
		{
			mps.addAll(mpr.getMeasuringPoints());
		}

		return mps;
	}

	public List<Variation> getVariationObjects()
	{
		List<Variation> out = new ArrayList<Variation>();
		
		Experiment exp = getActiveExperiment();
		if(exp == null){
			return out;
		}

		for (Variation v : exp.getVariations())
		{
			out.add(v);
		}

		return out;
	}

	public List<VariationType> getVariationTypes()
	{

		List<VariationType> out = new ArrayList<VariationType>();

		URI variations = PathmapManager.denormalizeURI(URI.createURI("pathmap://ENVIRONMENT_ANALYSER/pcm.variation"));
		
		Resource resource = null;
		synchronized (resSet) {
			resource = resSet.getResource(variations, true);
		}
		
		List<EObject> content = resource.getContents();
		if (!content.isEmpty())
		{
			EObject object = content.get(0);
			if (object instanceof VariationRepository)
			{
				VariationRepository vRep = (VariationRepository) object;
				for (VariationType v : vRep.getVariation())
				{
					out.add(v);
				}
			}
		}
		return out;
	}

	public VariationType getVariationType(String id)
	{

		URI variations = PathmapManager.denormalizeURI(URI.createURI("pathmap://ENVIRONMENT_ANALYSER/pcm.variation"));
		
		Resource resource = null;
		synchronized (resSet) {
			resource = resSet.getResource(variations, true);
		}
		
		List<EObject> content = resource.getContents();
		if (!content.isEmpty())
		{
			EObject object = content.get(0);
			if (object instanceof VariationRepository)
			{
				VariationRepository vRep = (VariationRepository) object;
				for (VariationType v : vRep.getVariation())
				{
					if (id.equals(v.getId()))
					{
						return v;
					}
				}
			}
		}
		return null;
	}

	public List<ValueProvider> getVariationValueProviders(EClass clazz)
	{

		List<ValueProvider> out = new ArrayList<ValueProvider>();
		
		Experiment exp = getActiveExperiment();
		if(exp == null){
			return out;
		}

		for (Variation v : exp.getVariations())
		{
			if (v.getValueProvider().eClass().equals(clazz))
			{
				out.add(v.getValueProvider());
			}
		}

		return out;
	}

	public List<MetricDescription> getMetricDescriptions()
	{

		List<MetricDescription> out = new ArrayList<MetricDescription>();

		Resource res = getResourceSet().getResource(URI.createURI("pathmap://METRIC_SPEC_MODELS/models/commonMetrics.metricspec"), true);
		EObject object = res.getContents().isEmpty() ? null : res.getContents().get(0);

		if (object instanceof MetricDescriptionRepository)
		{
			MetricDescriptionRepository mdRep = (MetricDescriptionRepository) object;
			for (MetricDescription md : mdRep.getMetricDescriptions())
			{
				out.add(md);
			}
		}

		return out;
	}

	public MetricDescription getMetricDescription()
	{
		List<MetricDescription> metDescriptions = getMetricDescriptions();
		return metDescriptions.isEmpty() ? null : metDescriptions.get(0);
	}

	public List<StopCondition> getStopConditions(EClass clazz)
	{
		List<StopCondition> out = new ArrayList<StopCondition>();
		
		Experiment exp = getActiveExperiment();
		if(exp == null){
			return out;
		}

		for (StopCondition stopCon : exp.getStopConditions())
		{
			if (stopCon.getClass().equals(clazz))
			{
				out.add(stopCon);
			}
		}

		return out;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Methods for retrieving sub-resources
	
	public List<ExperimentRepository> getExperimentRepositories()
	{
		List<ExperimentRepository> out = new ArrayList<ExperimentRepository>();

		for (IResource file : getSubResources(ToolchainUtils.KEY_FILE_EXPERIMENTS))
		{
			if (file instanceof IFile)
			{
				Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) file);
				for (EObject eobj : res.getContents())
				{
					if (eobj instanceof ExperimentRepository)
					{
						ExperimentRepository rep = (ExperimentRepository) eobj;
						out.add(rep);
					}
				}
			}
		}

		return out;
	}

	public List<MeasuringPointRepository> getMeasuringPointRepositories()
	{
		List<MeasuringPointRepository> out = new ArrayList<MeasuringPointRepository>();

		for (IResource file : getSubResources(ToolchainUtils.KEY_FILE_MESURPOINTS))
		{
			if (file instanceof IFile)
			{
				Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) file);
				for (EObject eobj : res.getContents())
				{
					if (eobj instanceof MeasuringPointRepository)
					{
						MeasuringPointRepository rep = (MeasuringPointRepository) eobj;
						out.add(rep);
					}
				}
			}
		}

		return out;
	}

	public List<MonitorRepository> getMonitorRepositories()
	{
		List<MonitorRepository> out = new ArrayList<MonitorRepository>();

		for (IResource file : getSubResources(ToolchainUtils.KEY_FILE_MONITOR))
		{
			if (file instanceof IFile)
			{
				Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) file);
				for (EObject eobj : res.getContents())
				{
					if (eobj instanceof MonitorRepository)
					{
						MonitorRepository rep = (MonitorRepository) eobj;
						out.add(rep);
					}
				}
			}
		}

		return out;
	}

	public List<ServiceLevelObjectiveRepository> getSLORepositories()
	{
		List<ServiceLevelObjectiveRepository> out = new ArrayList<ServiceLevelObjectiveRepository>();

		for (IResource file : getSubResources(ToolchainUtils.KEY_FILE_SLO))
		{
			if (file instanceof IFile)
			{
				Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) file);
				for (EObject eobj : res.getContents())
				{
					if (eobj instanceof ServiceLevelObjectiveRepository)
					{
						ServiceLevelObjectiveRepository rep = (ServiceLevelObjectiveRepository) eobj;
						out.add(rep);
					}
				}
			}
		}

		return out;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Helper methods for creating and returning base models

	private ExperimentRepository retrieveExperimentRepository()
	{
		if (getExperimentRepositories().isEmpty())
		{
			ExperimentRepository expRep = ExperimentsFactory.eINSTANCE.createExperimentRepository();
			createEMFResource("analyser.experiments", ToolchainUtils.KEY_FILE_EXPERIMENTS, expRep);
			return expRep;
		} else
		{
			return getExperimentRepositories().get(0);
		}
	}

	private MeasuringPointRepository retrieveMeasuringPointRepository()
	{
		if (getMeasuringPointRepositories().isEmpty())
		{
			MeasuringPointRepository measureRep = MeasuringpointFactory.eINSTANCE.createMeasuringPointRepository();
			createEMFResource("analyser.measuringpoint", ToolchainUtils.KEY_FILE_MESURPOINTS, measureRep);
			return measureRep;
		} else
		{
			return getMeasuringPointRepositories().get(0);
		}
	}

	private ServiceLevelObjectiveRepository retrieveSLORepository()
	{
		if (getSLORepositories().isEmpty())
		{
			ServiceLevelObjectiveRepository sloRep = ServicelevelObjectiveFactory.eINSTANCE.createServiceLevelObjectiveRepository();
			createEMFResource("analyser.slo", ToolchainUtils.KEY_FILE_SLO, sloRep);
			return sloRep;
		} else
		{
			return getSLORepositories().get(0);
		}
	}

	private MonitorRepository retrieveMonitorRepository()
	{
		if (getMonitorRepositories().isEmpty())
		{
			MonitorRepository monitorRep = MonitorRepositoryFactory.eINSTANCE.createMonitorRepository();
			createEMFResource("analyser.monitorrepository", ToolchainUtils.KEY_FILE_MONITOR, monitorRep);
			return monitorRep;
		} else
		{
			return getMonitorRepositories().get(0);
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Helper methods for retrieving referenced models from the Experiments
	// model
	
	public Experiment getActiveExperiment(){
		ExperimentRepository er = retrieveExperimentRepository();
		if(er.getExperiments().isEmpty()){
			return null;
		}
		return er.getExperiments().get(0);
	}

	public InitialModel getActiveInitialModel()
	{
		Experiment exp = getActiveExperiment();
		return exp != null ? exp.getInitialModel() : null;
	}

	public MonitorRepository getActiveMonitorRepository()
	{
		InitialModel im = getActiveInitialModel();
		if (im == null)
		{
			return null;
		}

		MonitorRepository mr = im.getMonitorRepository();
		if(mr != null && mr.eResource() != null){
			return mr;
		}
		return null;
	}
	
	public MonitorRepository initActiveMonitorRepository(){
		
		MonitorRepository mr = null;
		
		boolean needToSave = false;
		
		synchronized (getSaveLoadLock()) {
			if(getActiveMonitorRepository() == null){
				mr = retrieveMonitorRepository();
				Experiment experiment = getActiveExperiment();
				if(experiment != null && experiment.getInitialModel() != null){
					experiment.getInitialModel().setMonitorRepository(mr);
				}
				needToSave = true;
			}
			else{
				mr = getActiveMonitorRepository();
			}
		}
		
		if(needToSave){
			save();
		}
		
		return mr;
	}

	public ServiceLevelObjectiveRepository getActiveSLORepository()
	{
		InitialModel im = getActiveInitialModel();
		if (im == null)
		{
			return null;
		}

		ServiceLevelObjectiveRepository sloRep = im.getServiceLevelObjectives();
		if(sloRep != null && sloRep.eResource() != null){
			return sloRep;
		}
		return null;
	}
	
	public ServiceLevelObjectiveRepository initActiveSLORepository(){
		
		ServiceLevelObjectiveRepository sloRep = null;
		
		boolean needToSave = false;
		
		synchronized (getSaveLoadLock()) {
			if(getActiveSLORepository() == null){
				sloRep = retrieveSLORepository();
				Experiment experiment = getActiveExperiment();
				if(experiment != null && experiment.getInitialModel() != null){
					experiment.getInitialModel().setServiceLevelObjectives(sloRep);
				}
				needToSave = true;
			}
			else{
				sloRep = getActiveSLORepository();
			}
		}
		
		if(needToSave){
			save();
		}
		
		return sloRep;
	}

	public UsageEvolution getActiveUsageEvolutionModel()
	{
		InitialModel im = getActiveInitialModel();
		if (im == null)
		{
			return null;
		}

		return im.getUsageEvolution();
	}

	public List<UsageScenario> getActiveScenarios()
	{
		List<UsageScenario> scenarios = new ArrayList<UsageScenario>();

		Experiment exp = getActiveExperiment();
		if (exp.getInitialModel() != null)
		{
			UsageModel usageModel = exp.getInitialModel().getUsageModel();
			if (usageModel != null)
			{
				scenarios.addAll(usageModel.getUsageScenario_UsageModel());
			}
		}
		return scenarios;
	}

	public List<MeasurementSpecification> getActiveMeasurementSpecifications()
	{
		List<MeasurementSpecification> out = new ArrayList<MeasurementSpecification>();

		MonitorRepository mr = getActiveInitialModel() != null ? getActiveInitialModel().getMonitorRepository() : null;
		if (mr == null)
		{
			return out;
		}
		for (Monitor monitor : mr.getMonitors())
		{
			out.addAll(monitor.getMeasurementSpecifications());
		}
		return out;
	}

	public UsageModel getActiveUsageModel()
	{
		InitialModel initialModel = getActiveInitialModel();
		if (initialModel == null)
		{
			return null;
		}
		return initialModel.getUsageModel();
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Alternative initialization and configuration methods

	private void initializeCommon(Experiment exp)
	{	
		
		InitialModel initialModel = getActiveInitialModel();
		if (initialModel == null)
		{
			initialModel = ExperimentsFactory.eINSTANCE.createInitialModel();
			exp.setInitialModel(initialModel);
		}

		// create and set measurement stop condition
		{
			List<StopCondition> stopConditions = getStopConditions(AbstractsimulationPackage.Literals.MEASUREMENT_COUNT_STOP_CONDITION);
			if (stopConditions.isEmpty())
			{
				MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
				msc.setMeasurementCount(100);
				exp.getStopConditions().add(msc);
			}
		}

		// create and set time stop condition
		{
			List<StopCondition> stopConditions = getStopConditions(AbstractsimulationPackage.Literals.SIM_TIME_STOP_CONDITION);
			if (stopConditions.isEmpty())
			{
				SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
				tsc.setSimulationTime(-1);
				exp.getStopConditions().add(tsc);
			}
		}

		UsageScenarioMeasuringPoint usageMeasurePoint = null;
		// create measuring point repository
		{
			MeasuringPointRepository measureRep = retrieveMeasuringPointRepository();
			
			//TODO: If the usage scenario is null, Palladio throws null pointer exception
			// Wait until the problem is fixed in Palladio 
			
			if (measureRep.getMeasuringPoints().isEmpty())
			{
				usageMeasurePoint = PcmmeasuringpointFactory.eINSTANCE.createUsageScenarioMeasuringPoint();
				measureRep.getMeasuringPoints().add(usageMeasurePoint);
			}
		}

		// create monitor
		{
			MonitorRepository monitorRep = retrieveMonitorRepository();

			Monitor monitor = MonitorRepositoryFactory.eINSTANCE.createMonitor();
			monitor.setEntityName("Usage response time monitor");

			// create default specification
			MeasurementSpecification specification = MonitorRepositoryFactory.eINSTANCE.createMeasurementSpecification();
			specification.setStatisticalCharacterization(StatisticalCharacterizationEnum.ARITHMETIC_MEAN);
			Intervall interval = MonitorRepositoryFactory.eINSTANCE.createIntervall();
			interval.setIntervall(10.0);

			MetricDescription metric = null;
			for (MetricDescription md : getMetricDescriptions())
			{
				if ("_6rYmYs7nEeOX_4BzImuHbA".equals(md.getId()))
				{
					metric = md;
				}
			}

			if (metric == null)
			{
				DialogUtils.openError(ERROR_DIALOG_TITLE, "Required metric description (Response time) can not be found!");
			} else
			{
				specification.setMetricDescription(metric);
			}
			specification.setTemporalRestriction(interval);
			monitor.getMeasurementSpecifications().add(specification);
			//

			monitor.setMeasuringPoint(usageMeasurePoint);
			monitorRep.getMonitors().add(monitor);
			initialModel.setMonitorRepository(monitorRep);
		}

		// create slo
		{
			ServiceLevelObjectiveRepository sloRep = retrieveSLORepository();
			if (!sloRep.getServicelevelobjectives().isEmpty())
			{
				getActiveInitialModel().setServiceLevelObjectives(sloRep);
			} else
			{
				getActiveInitialModel().setServiceLevelObjectives(null);
			}
		}

	}

	private void initializeNormal(Experiment exp)
	{

	}

	private void initializeCapacity(Experiment exp)
	{

		exp.setName("Capacity measurement");

		// create variation
		Variation var = ExperimentsFactory.eINSTANCE.createVariation();
		var.setName("Default variation model");
		
		exp.getVariations().clear();
		NestedIntervalsLongValueProvider dvp = ExperimentsFactory.eINSTANCE.createNestedIntervalsLongValueProvider();
		dvp.setMinValue(1);
		dvp.setMaxValue(100);

		var.setMinValue(1);
		var.setMaxValue(100);
		var.setMaxVariations(10);

		var.setValueProvider(dvp);
		// TODO: set type var.setType();
		exp.getVariations().clear();
		exp.getVariations().add(var);

		URI variations = PathmapManager.denormalizeURI(URI.createURI("pathmap://ENVIRONMENT_ANALYSER/pcm.variation"));
		
		synchronized (resSet) {
			resSet.getResource(variations, true);
		}

		// create SLO
		{
			ServiceLevelObjectiveRepository sloRep = retrieveSLORepository();
			ServiceLevelObjective slo = ServicelevelObjectiveFactory.eINSTANCE.createServiceLevelObjective();
			slo.setName("Usage response time SLO");
			sloRep.getServicelevelobjectives().add(slo);
			
			getActiveInitialModel().setServiceLevelObjectives(sloRep);
		}
	}

	private void initializeScalability(Experiment exp)
	{
		exp.setName("Scalability measurement");

		// create variation
		Variation var = ExperimentsFactory.eINSTANCE.createVariation();
		var.setName("Default variation model");

		exp.getVariations().clear();
		NestedIntervalsLongValueProvider dvp = ExperimentsFactory.eINSTANCE.createNestedIntervalsLongValueProvider();
		dvp.setMinValue(1);
		dvp.setMaxValue(100);

		var.setMinValue(1);
		var.setMaxValue(100);
		var.setMaxVariations(10);

		var.setValueProvider(dvp);
		// TODO: set type var.setType();
		exp.getVariations().clear();
		exp.getVariations().add(var);

		exp.getModifications().add(ExperimentsFactory.eINSTANCE.createSchedulingPolicy2DelayModification());

		URI variations = PathmapManager.denormalizeURI(URI.createURI("pathmap://ENVIRONMENT_ANALYSER/pcm.variation"));
		
		synchronized (resSet) {
			resSet.getResource(variations, true);
		}

		// create SLO
		{
			ServiceLevelObjectiveRepository sloRep = retrieveSLORepository();
			ServiceLevelObjective slo = ServicelevelObjectiveFactory.eINSTANCE.createServiceLevelObjective();
			slo.setName("Usage response time SLO");
			sloRep.getServicelevelobjectives().add(slo);
			
			getActiveInitialModel().setServiceLevelObjectives(sloRep);
		}
	}

	private void configureNormal(Experiment exp, InitialModel initialModel)
	{
		/*
		UsageEvolution ue = retrieveUsageEvolution();
		if (!ue.getUsages().isEmpty())
		{
			getActiveInitialModel().setUsageEvolution(ue);
		}
		*/
	}

	private void configureCapacity(Experiment exp, InitialModel initialModel)
	{

		UsageScenarioMeasuringPoint usmp = null;
		MeasurementSpecification usageMeasurementSpec = null;

		// retrieve usage scenario
		EList<UsageScenario> usList = initialModel.getUsageModel().getUsageScenario_UsageModel();
		UsageScenario usageScenario = usList.size() > 0 ? usList.get(0) : null;

		if (usageScenario == null)
		{
			DialogUtils.openWarning(WARNING_DIALOG_TITLE, "Usage scenario can not be found. Please configure all properties regarding this model manually.");
		}

		List<MeasuringPoint> mps = getMeasuringPointObjects(PcmmeasuringpointPackage.Literals.USAGE_SCENARIO_MEASURING_POINT);
		if (!mps.isEmpty())
		{
			usmp = (UsageScenarioMeasuringPoint) mps.get(0);
			usmp.setUsageScenario(usageScenario);
		} else
		{
			DialogUtils.openWarning(WARNING_DIALOG_TITLE, "Usage scenario measuring point has been removed! Please create and configure it manually.");
		}

		MonitorRepository monitorRep = retrieveMonitorRepository();
		if (!monitorRep.getMonitors().isEmpty())
		{
			Monitor monitor = monitorRep.getMonitors().get(0);
			if (usmp != null)
			{
				monitor.setMeasuringPoint(usmp);
			}

			monitor.getMeasurementSpecifications().clear();
			MeasurementSpecification specification = MonitorRepositoryFactory.eINSTANCE.createMeasurementSpecification();
			specification.setStatisticalCharacterization(StatisticalCharacterizationEnum.ARITHMETIC_MEAN);
			Intervall interval = MonitorRepositoryFactory.eINSTANCE.createIntervall();
			interval.setIntervall(10.0);

			MetricDescription metric = null;
			for (MetricDescription md : getMetricDescriptions())
			{
				if ("_6rYmYs7nEeOX_4BzImuHbA".equals(md.getId()))
				{
					metric = md;
				}
			}

			if (metric == null)
			{
				DialogUtils.openError(ERROR_DIALOG_TITLE, "Required metric description (Response time) can not be found!");
			} else
			{
				specification.setMetricDescription(metric);
			}

			specification.setTemporalRestriction(interval);
			monitor.getMeasurementSpecifications().add(specification);
			usageMeasurementSpec = specification;
		} else
		{
			DialogUtils.openWarning(WARNING_DIALOG_TITLE, "Monitor object has been removed! Please create and configure it manually.");
		}

		List<Variation> variations = getVariationObjects();
		if (!variations.isEmpty())
		{
			Variation v = variations.get(0);

			if (usageScenario != null)
			{
				Workload workload = usageScenario.getWorkload_UsageScenario();
				if (workload == null)
				{
					DialogUtils.openWarning(WARNING_DIALOG_TITLE, "Usage scenario workload can not be found! Please configure it manually.");
				}

				VariationType type = null;
				if (workload instanceof ClosedWorkload)
				{
					type = getVariationType("_zUZqID5zEeCEPJs72ZSOBg");
				} else if (workload instanceof OpenWorkload)
				{
					type = getVariationType("_59qtgBU-EeKgFO0nt5OPnA");
				}

				if (type != null)
				{
					v.setType(type);
				} else
				{
					DialogUtils.openError(ERROR_DIALOG_TITLE, "Required variation type can not be found! Please configure it manually.");
				}

				v.setVariedObjectId(usageScenario.getId());
			}
		} else
		{
			DialogUtils.openWarning(WARNING_DIALOG_TITLE, "Variation has been removed! Please create and configure it manually.");
		}

		ServiceLevelObjectiveRepository sloRep = retrieveSLORepository();
		if (!sloRep.getServicelevelobjectives().isEmpty())
		{
			ServiceLevelObjective slo = sloRep.getServicelevelobjectives().get(0);
			if (usageMeasurementSpec != null)
			{
				slo.setMeasurementSpecification(usageMeasurementSpec);
			}

			HardThreshold ut = ServicelevelObjectiveFactory.eINSTANCE.createHardThreshold();
			ut.setThresholdLimit(Measure.valueOf(500.0, Unit.valueOf("s")));
			// TODO: set limit to 'HardThreshold'
			slo.setUpperThreshold(ut);
		} else
		{
			DialogUtils.openWarning(WARNING_DIALOG_TITLE, "Service level objective has been removed! Please create and configure it manually.");
		}
	}

	private void configureScalability(Experiment exp, InitialModel initialModel)
	{
		configureCapacity(exp, initialModel);
	}	

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Standard alternative load/save/delete methods

	@Override
	public void doCreateModels()
	{		
		Experiment exp = null;
		
		//create experiment
		{
			ExperimentRepository expRep = retrieveExperimentRepository();
			exp = ExperimentsFactory.eINSTANCE.createExperiment();
			exp.setId(UUID.randomUUID().toString());
			exp.setName("CloudScale experiments model");
			exp.setDescription("Alternative experiment");
			
			{
				// set memory data source
				// this data source is later replaced with the file data source.
				MemoryDatasource ds = AbstractsimulationFactory.eINSTANCE.createMemoryDatasource();
				ds.setId(UUID.randomUUID().toString());
	
				SimuLizarConfiguration conf = SimulizartooladapterFactory.eINSTANCE.createSimuLizarConfiguration();
				conf.setName("SimuLizar default configuration");
				conf.setDatasource(ds);
				
				//set two dummy stop conditions
				//TODO: find out which container is correct (SimulizarConfiguration or Experiment model).
				MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
				msc.setMeasurementCount(10);
				SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
				tsc.setSimulationTime(10);
				conf.getStopConditions().add(msc);
				conf.getStopConditions().add(tsc);
				
				exp.getToolConfiguration().add(conf);
			}
			
			exp.setRepetitions(1);
			
			ResponseMeasurement rm = ExperimentsFactory.eINSTANCE.createSimulationDurationMeasurement();
			exp.setResponseMeasurement(rm);
			
			ExperimentDesign ed = ExperimentsFactory.eINSTANCE.createFullFactorialDesign();
			exp.setExperimentDesign(ed);
			
			expRep.getExperiments().add(exp);
		}
		
		initializeCommon(exp);

		if (Type.NORMAL.equals(type))
		{
			initializeNormal(exp);
		} else if (Type.CAPACITY.equals(type))
		{
			initializeCapacity(exp);
		} else if (Type.SCALABILITY.equals(type))
		{
			initializeScalability(exp);
		}
		
	}

	@Override
	protected void doLoad(IProgressMonitor monitor)
	{
		super.doLoad(monitor);
		
		workOn(monitor, "Loading metric description.");
		// load plugin models into resource set
		URI metricDescriptions = PathmapManager.denormalizeURI(URI
				.createURI("pathmap://METRIC_SPEC_MODELS/models/commonMetrics.metricspec"));
		
		synchronized (resSet) {
			resSet.getResource(metricDescriptions, true);
		}
		
		work(monitor);
	}
	
	@Override
	public int getLoadWork() {
		return super.getLoadWork() + 1;
	}

	@Override
	protected void doDelete(IProgressMonitor monitor)
	{
		ResourceProvider resultResProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.ANALYSER_RES);
		IEditorInputResource resultAlternative = resultResProvider.getResource(this.getResource().getName());

		if (resultAlternative != null)
		{
			resultAlternative.delete();
		}

		super.doDelete(monitor);
	}

	@Override
	protected IStatus doRun(IProgressMonitor monitor) throws CoreException
	{
		this.getInputAlternative().save();

		this.configureResults();
		this.save();

		ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType lct = mgr
				.getLaunchConfigurationType("org.palladiosimulator.experimentautomation.application.launchConfigurationType");

		ILaunchConfigurationWorkingCopy lcwc = lct.newInstance((IFolder) this.getResource(), this.getResource().getName());
		lcwc.setAttribute("Experiment Automation", getActiveExperiment().eResource().getURI().toString());
		lcwc.setAttribute("de.uka.ipd.sdq.workflowengine.debuglevel", 2);
		lcwc.setAttribute("outpath", "org.palladiosimulator.temporary");
		lcwc.doSave();

		lcwc.launch(ILaunchManager.RUN_MODE, monitor);
		return Status.OK_STATUS;
	}

	private SimpleDateFormat sdf_name = new SimpleDateFormat("hh:mm:ss");

	public void configureResults()
	{
		Experiment exp = getActiveExperiment();
		ResourceProvider resultResProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSToolResource.ANALYSER_RES);
		// IEditorInputResource resultAlternative =
		// resultResProvider.getResource(this.getResource().getName());

		String name = getName() + " [" + sdf_name.format(new Date()) + "]";
		ResultAlternative resultAlternative = (ResultAlternative) resultResProvider.createNewResource(name, type.name());
		resultAlternative.setConfigAlternative(this);
		resultAlternative.save();

		// create data source - a.k.a where the result will be saved
		FileDatasource ds = AbstractsimulationFactory.eINSTANCE.createFileDatasource();
		ds.setLocation(resultAlternative.getResource().getLocation().toString());

		SimuLizarConfiguration conf = SimulizartooladapterFactory.eINSTANCE.createSimuLizarConfiguration();
		conf.setName("SimuLizar default configuration");
		conf.setDatasource(ds);
		configureTool(exp, conf);

		exp.getToolConfiguration().clear();
		exp.getToolConfiguration().add(conf);
	}
}
