package eu.cloudscaleproject.env.analyser.alternatives;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.eclipse.gmf.runtime.emf.core.internal.resources.PathmapManager;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPointRepository;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
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
import org.palladiosimulator.experimentautomation.experiments.NestedIntervalsLongValueProvider;
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
import org.scaledl.usageevolution.UsageevolutionFactory;

import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.toolchain.CSTool;
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
		super(project, folder, ModelType.GROUP_EXPERIMENTS, CSTool.ANALYSER_CONF.getID(),
				ResourceRegistry.getInstance().getResourceProvider(project,
						CSTool.ANALYSER_INPUT.getID()), 
				ResourceRegistry.getInstance().getResourceProvider(project,
						CSTool.ANALYSER_RES.getID())
						);

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

	private void configureTool(SimuLizarConfiguration simulizarConf)
	{
		if (Type.NORMAL.equals(type))
		{
		} else if (Type.CAPACITY.equals(type))
		{
			configureToolCapacity(simulizarConf);
		} else if (Type.SCALABILITY.equals(type))
		{
			configureToolScalability(simulizarConf);
		}
	}

	public InputAlternative getInputAlternative()
	{
		IResource res = getSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE);
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_INPUT);
		return (InputAlternative) rp.getResource(res);
	}

	public boolean setInputAlternative(InputAlternative inputAlt)
	{
		Experiment exp = retrieveExperimentModel();
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

		for (Resource res : resSet.getResources())
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

		for (Resource res : resSet.getResources())
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

		Experiment experiment = retrieveExperimentModel();
		for (Variation v : experiment.getVariations())
		{
			out.add(v);
		}

		return out;
	}

	public List<VariationType> getVariationTypes()
	{

		List<VariationType> out = new ArrayList<VariationType>();

		URI variations = PathmapManager.denormalizeURI(URI.createURI("pathmap://ENVIRONMENT_ANALYSER/pcm.variation"));
		Resource resource = resSet.getResource(variations, true);
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
		Resource resource = resSet.getResource(variations, true);
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

		for (Variation v : retrieveExperimentModel().getVariations())
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

		for (StopCondition stopCon : retrieveExperimentModel().getStopConditions())
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

	public List<MeasuringPointRepository> getMeasuringPointRepositories()
	{
		List<MeasuringPointRepository> out = new ArrayList<MeasuringPointRepository>();

		for (IResource file : getSubResources(ToolchainUtils.KEY_FILE_MESURPOINTS))
		{
			if (file instanceof IFile && file.exists())
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
			if (file instanceof IFile && file.exists())
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
			if (file instanceof IFile && file.exists())
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

	public List<UsageEvolution> getUsageEvolutions()
	{
		List<UsageEvolution> out = new ArrayList<UsageEvolution>();

		for (IResource file : getSubResources(ToolchainUtils.KEY_FILE_USAGEEVOLUTION))
		{
			if (file instanceof IFile && file.exists())
			{
				Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) file);
				for (EObject eobj : res.getContents())
				{
					if (eobj instanceof UsageEvolution)
					{
						UsageEvolution root = (UsageEvolution) eobj;
						out.add(root);
					}
				}
			}
		}

		return out;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Helper methods for creating and returning base models

	public Experiment retrieveExperimentModel()
	{
		IResource expFile = getSubResource(ToolchainUtils.KEY_FILE_EXPERIMENTS);
		if (expFile == null || !expFile.exists())
		{
			List<IFile> files = PCMResourceSet.findResource(getResource(), ModelType.EXPERIMENTS.getFileExtension());
			if (files.size() > 0)
			{
				// TODO: for now just use fist model file found
				expFile = files.get(0);
			} else
			{
				// create new Experiment model file
				expFile = getResource().getFile(ModelType.EXPERIMENTS.getFullName());
				setSubResource(ToolchainUtils.KEY_FILE_EXPERIMENTS, expFile);
			}
		}

		Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) expFile);
		EObject root = res.getContents().size() > 0 ? res.getContents().get(0) : null;
		if (root == null)
		{
			ExperimentRepository expRep = ExperimentsFactory.eINSTANCE.createExperimentRepository();
			root = expRep;
			res.getContents().add(expRep);
		}

		if (!(root instanceof ExperimentRepository))
		{
			throw new IllegalArgumentException("Experiments model with root object type other then ExperimentRepository is not supported!");
		}

		ExperimentRepository expRep = (ExperimentRepository) root;
		Experiment firstExperiment = expRep.getExperiments().isEmpty() ? null : expRep.getExperiments().get(0);
		if (firstExperiment == null)
		{
			firstExperiment = ExperimentsFactory.eINSTANCE.createExperiment();
			firstExperiment.setRepetitions(1);
			expRep.getExperiments().add(firstExperiment);
		}

		return (Experiment) firstExperiment;
	}

	public MeasuringPointRepository retrieveMeasuringPointRepository()
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

	public ServiceLevelObjectiveRepository retrieveSLORepository()
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

	public MonitorRepository retrieveMonitorRepository()
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

	public UsageEvolution retrieveUsageEvolution()
	{
		if (getUsageEvolutions().isEmpty())
		{
			UsageEvolution usageEvolution = UsageevolutionFactory.eINSTANCE.createUsageEvolution();
			createEMFResource("analyser.usageevolution", ToolchainUtils.KEY_FILE_USAGEEVOLUTION, usageEvolution);
			return usageEvolution;
		} else
		{
			return getUsageEvolutions().get(0);
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Helper methods for retrieving referenced models from the Experiments
	// model

	public InitialModel getActiveInitialModel()
	{
		Experiment exp = retrieveExperimentModel();
		return exp != null ? exp.getInitialModel() : null;
	}

	public MonitorRepository getActiveMonitorRepository()
	{
		InitialModel im = getActiveInitialModel();
		if (im == null)
		{
			return null;
		}

		return im.getMonitorRepository();
	}

	public ServiceLevelObjectiveRepository getActiveSLORepository()
	{
		InitialModel im = getActiveInitialModel();
		if (im == null)
		{
			return null;
		}

		return im.getServiceLevelObjectives();
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

		Experiment exp = retrieveExperimentModel();
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
				DialogUtils.openError("Required metric description (Response time) can not be found!");
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

		// create usage evolution
		{
			UsageEvolution ue = retrieveUsageEvolution();
			if (!ue.getUsages().isEmpty())
			{
				getActiveInitialModel().setUsageEvolution(ue);
			} else
			{
				getActiveInitialModel().setUsageEvolution(null);
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
		resSet.getResource(variations, true);

		// create SLO
		{
			ServiceLevelObjectiveRepository sloRep = retrieveSLORepository();
			ServiceLevelObjective slo = ServicelevelObjectiveFactory.eINSTANCE.createServiceLevelObjective();
			slo.setName("Usage response time SLO");
			sloRep.getServicelevelobjectives().add(slo);
		}
	}

	private void initializeScalability(Experiment exp)
	{
		exp.setName("Scalability measurement");

		// create variation
		Variation var = ExperimentsFactory.eINSTANCE.createVariation();
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
		resSet.getResource(variations, true);

		// create SLO
		{
			ServiceLevelObjectiveRepository sloRep = retrieveSLORepository();
			ServiceLevelObjective slo = ServicelevelObjectiveFactory.eINSTANCE.createServiceLevelObjective();
			slo.setName("Usage response time SLO");
			sloRep.getServicelevelobjectives().add(slo);
		}
	}

	private void configureNormal(Experiment exp, InitialModel initialModel)
	{
		UsageEvolution ue = retrieveUsageEvolution();
		if (!ue.getUsages().isEmpty())
		{
			getActiveInitialModel().setUsageEvolution(ue);
		}
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
			DialogUtils.openWarning("Usage scenario can not be found. Please configure all properties regarding this model manually.");
		}

		List<MeasuringPoint> mps = getMeasuringPointObjects(PcmmeasuringpointPackage.Literals.USAGE_SCENARIO_MEASURING_POINT);
		if (!mps.isEmpty())
		{
			usmp = (UsageScenarioMeasuringPoint) mps.get(0);
			usmp.setUsageScenario(usageScenario);
		} else
		{
			DialogUtils.openWarning("Usage scenario measuring point has been removed! Please create and configure it manually.");
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
				DialogUtils.openError("Required metric description (Response time) can not be found!");
			} else
			{
				specification.setMetricDescription(metric);
			}

			specification.setTemporalRestriction(interval);
			monitor.getMeasurementSpecifications().add(specification);
			usageMeasurementSpec = specification;
		} else
		{
			DialogUtils.openWarning("Monitor object has been removed! Please create and configure it manually.");
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
					DialogUtils.openWarning("Usage scenario workload can not be found! Please configure it manually.");
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
					DialogUtils.openError("Required variation type can not be found! Please configure it manually.");
				}

				v.setVariedObjectId(usageScenario.getId());
			}
		} else
		{
			DialogUtils.openWarning("Variation has been removed! Please create and configure it manually.");
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
			DialogUtils.openWarning("Service level objective has been removed! Please create and configure it manually.");
		}
	}

	private void configureScalability(Experiment exp, InitialModel initialModel)
	{
		configureCapacity(exp, initialModel);
	}

	private void configureToolCapacity(SimuLizarConfiguration simulizarConf)
	{

		// create measurement stop condition
		MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
		msc.setMeasurementCount(100);

		// create time stop condition
		SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
		tsc.setSimulationTime(-1);

		simulizarConf.getStopConditions().add(msc);
		simulizarConf.getStopConditions().add(tsc);
	}

	private void configureToolScalability(SimuLizarConfiguration simulizarConf)
	{

		// create measurement stop condition
		MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
		msc.setMeasurementCount(100);

		// create time stop condition
		SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
		tsc.setSimulationTime(-1);

		simulizarConf.getStopConditions().add(msc);
		simulizarConf.getStopConditions().add(tsc);
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Standard alternative load/save/delete methods

	@Override
	public void doCreate()
	{
		Experiment exp = retrieveExperimentModel();
		
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
	protected void doLoad()
	{
		super.doLoad();

		// load plugin models into resource set
		URI metricDescriptions = PathmapManager.denormalizeURI(URI
				.createURI("pathmap://METRIC_SPEC_MODELS/models/commonMetrics.metricspec"));
		resSet.getResource(metricDescriptions, true);
		
	}

	@Override
	protected void doDelete()
	{
		ResourceProvider resultResProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_RES);
		IEditorInputResource resultAlternative = resultResProvider.getResource(this.getResource().getName());

		if (resultAlternative != null)
		{
			resultAlternative.delete();
		}

		super.doDelete();
	}

	/*
	private final void loadModels() throws IOException
	{
		//unload existing models
		for(Resource res : new ArrayList<Resource>(resSet.getResources())){
			res.unload();
		}
		
		//load registered models
		for (ModelType type : ModelType.GROUP_EXPERIMENTS)
		{
			for (IResource f : getSubResources(type.getToolchainFileID()))
			{
				Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) f);
				res.unload();
				res.load(null);
			}
		}

		// load plugin models into resource set
		URI metricDescriptions = PathmapManager.denormalizeURI(URI
				.createURI("pathmap://METRIC_SPEC_MODELS/models/commonMetrics.metricspec"));
		resSet.getResource(metricDescriptions, true);
	}
	*/

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
		lcwc.setAttribute("Experiment Automation", this.retrieveExperimentModel().eResource().getURI().toString());
		lcwc.setAttribute("de.uka.ipd.sdq.workflowengine.debuglevel", 2);
		lcwc.setAttribute("outpath", "org.palladiosimulator.temporary");
		lcwc.doSave();

		lcwc.launch(ILaunchManager.DEBUG_MODE, monitor);
		return Status.OK_STATUS;
	}

	private SimpleDateFormat sdf_name = new SimpleDateFormat("hh:mm:ss");

	public void configureResults()
	{
		Experiment exp = retrieveExperimentModel();
		ResourceProvider resultResProvider = ResourceRegistry.getInstance().getResourceProvider(project, CSTool.ANALYSER_RES);
		// IEditorInputResource resultAlternative =
		// resultResProvider.getResource(this.getResource().getName());

		String name = getName() + " [" + sdf_name.format(new Date()) + "]";
		ResultAlternative resultAlternative = (ResultAlternative) resultResProvider.createNewResource(name, type.name());
		resultAlternative.setSubResource(ToolchainUtils.KEY_CONFIG_ALTERNATIVE, getResource());
		resultAlternative.save();

		// create data source - a.k.a where the result will be saved
		FileDatasource ds = AbstractsimulationFactory.eINSTANCE.createFileDatasource();
		ds.setLocation(resultAlternative.getResource().getLocation().toString());

		SimuLizarConfiguration conf = SimulizartooladapterFactory.eINSTANCE.createSimuLizarConfiguration();
		conf.setDatasource(ds);
		configureTool(conf);

		exp.getToolConfiguration().clear();
		exp.getToolConfiguration().add(conf);
	}
}
