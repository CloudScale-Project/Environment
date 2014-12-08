package eu.cloudscaleproject.env.analyser.alternatives;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.experimentautomation.abstractsimulation.AbstractsimulationFactory;
import org.palladiosimulator.experimentautomation.abstractsimulation.FileDatasource;
import org.palladiosimulator.experimentautomation.abstractsimulation.MeasurementCountStopCondition;
import org.palladiosimulator.experimentautomation.abstractsimulation.SimTimeStopCondition;
import org.palladiosimulator.experimentautomation.application.tooladapter.simulizar.model.SimuLizarConfiguration;
import org.palladiosimulator.experimentautomation.application.tooladapter.simulizar.model.SimulizartooladapterFactory;
import org.palladiosimulator.experimentautomation.experiments.Experiment;
import org.palladiosimulator.experimentautomation.experiments.InitialModel;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointFactory;
import org.palladiosimulator.pcmmeasuringpoint.UsageScenarioMeasuringPoint;
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
		exp.getToolConfiguration().clear();
		
		//create and set measurement stop condition
		MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
		msc.setMeasurementCount(100);
		exp.getStopConditions().add(msc);
		
		//create and set time stop condition
		SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
		tsc.setSimulationTime(-1);
		exp.getStopConditions().add(tsc);
		
		//create and set simulation configuration
		exp.getToolConfiguration().add(createConfiguration(getResource().getProject(), this, 1000, -1));
		
		//retrieve usage scenario
		EList<UsageScenario> usList = initialModel.getUsageModel().getUsageScenario_UsageModel();
		UsageScenario usageScenario = usList.size() > 0 ? usList.get(0) : null;
		
		//create measuring point
		UsageScenarioMeasuringPoint measurePoint = PcmmeasuringpointFactory.eINSTANCE.createUsageScenarioMeasuringPoint();
		measurePoint.setUsageScenario(usageScenario);
		IFile mesuringPointFile = ((IFolder)getResource()).getFile("analyser.measuringpoint");
		setResource(ToolchainUtils.KEY_FILE_MESURPOINTS, mesuringPointFile);
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
		setResource(ToolchainUtils.KEY_FILE_PMS, pmsFile);
		Resource resPms = ExplorerProjectPaths.getEmfResource(resSet, pmsFile);
		resPms.getContents().clear();
		resPms.getContents().add(pms);
		initialModel.setPlatformMonitoringSpecification(pms);
	}
	
	private SimuLizarConfiguration createConfiguration(IProject project, ConfAlternative ca, int mesurementCount, int timeStop){
		
		IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
		IFolder analyserResults = analyserFolder.getFolder(ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_RESULTS));
		
		//create data source
		FileDatasource ds = AbstractsimulationFactory.eINSTANCE.createFileDatasource();
		ds.setLocation(analyserResults.getLocation().toString());
	
		//create measurement stop condition
		MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
		msc.setMeasurementCount(1000);
		
		//create time stop condition
		SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
		tsc.setSimulationTime(timeStop);
		
		//tool configuration
		SimuLizarConfiguration conf = SimulizartooladapterFactory.eINSTANCE.createSimuLizarConfiguration();
		conf.setDatasource(ds);
		conf.getStopConditions().add(msc);
		conf.getStopConditions().add(tsc);
	
		return conf;
	}

}
