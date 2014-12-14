package eu.cloudscaleproject.env.analyser.alternatives;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationFactory;
import org.palladiosimulator.experimentautomation.abstractsimulation.MeasurementCountStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.SimTimeStopCondition;
import org.palladiosimulator.experimentautomation.application.tooladapter.simulizar.model.SimuLizarConfiguration;
import org.palladiosimulator.experimentautomation.experiments.Experiment;
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

import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class CapacityAlternative extends ConfAlternative{

	private static final long serialVersionUID = 1L;
	
	public CapacityAlternative(IProject project, IFolder folder) {
		super(project, folder);
	}
	
	@Override
	protected void configureInput(Experiment exp, InitialModel initialModel, EditorInputFolder editorInput) {
		
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
		this.setResource(ToolchainUtils.KEY_FILE_MESURPOINTS, mesuringPointFile);
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
		this.setResource(ToolchainUtils.KEY_FILE_PMS, pmsFile);
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
		this.setResource(ToolchainUtils.KEY_FILE_SLO, pmsFile);
		Resource resSlo = ExplorerProjectPaths.getEmfResource(resSet, sloFile);
		resSlo.getContents().clear();
		resSlo.getContents().add(sloRep);
	}
	
	@Override
	protected void configureTool(SimuLizarConfiguration simulizarConf) {
		super.configureTool(simulizarConf);
		
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
