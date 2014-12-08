package eu.cloudscaleproject.env.analyser.experiments;


public class CapacityExperiment {
	
	/*
	public static void init(ConfAlternative ca) throws IOException{
	
		ca.setName("User capacity");
		Experiment exp = ca.getExperiment();
		
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
		exp.getToolConfiguration().add(createConfiguration(ca.getResource().getProject(), ca, 1000, -1));
		
		InitialModel initialModel = ExperimentsFactory.eINSTANCE.createInitialModel();
		exp.setInitialModel(initialModel);
		
		//retrieve usage scenario
		UsageModel usageModel = UsagemodelFactory.eINSTANCE.createUsageModel();
		UsageScenario usageScenario = UsagemodelFactory.eINSTANCE.createUsageScenario();
		usageModel.getUsageScenario_UsageModel().add(usageScenario);
		Resource resUsage = ExplorerProjectPaths.getEmfResource(ca.getResourceSet(), ((IFolder)ca.getResource()).getFile("analyser.usagemodel"));
		resUsage.getContents().clear();
		resUsage.getContents().add(usageModel);
		resUsage.save(null);
		
		//create measuring point
		UsageScenarioMeasuringPoint measurePoint = PcmmeasuringpointFactory.eINSTANCE.createUsageScenarioMeasuringPoint();
		measurePoint.setUsageScenario(usageScenario);
		Resource resMp = ExplorerProjectPaths.getEmfResource(ca.getResourceSet(), ((IFolder)ca.getResource()).getFile("analyser.measuringpoint"));
		resMp.getContents().clear();
		resMp.getContents().add(measurePoint);
		resMp.save(null);
		
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
		Resource resPms = ExplorerProjectPaths.getEmfResource(ca.getResourceSet(), ((IFolder)ca.getResource()).getFile("analyser.pms"));
		resPms.getContents().clear();
		resPms.getContents().add(pms);
		resPms.save(null);
		
		//load and set default resources from plugin	
		URI uMiddleware = PathmapManager.denormalizeURI(URI.createURI("pathmap://PCM_MODELS/Glassfish.repository"));
		URI uEventMiddleware = PathmapManager.denormalizeURI(URI.createURI("pathmap://PCM_MODELS/default_event_middleware.repository"));
		
		Resource resMRep = ca.getResourceSet().getResource(uMiddleware, true);
		Resource resEMRep = ca.getResourceSet().getResource(uEventMiddleware, true);
		
		initialModel.setMiddlewareRepository((Repository)resMRep.getContents().get(0));
		initialModel.setEventMiddleWareRepository((Repository)resEMRep.getContents().get(0));
		
		initialModel.setUsageModel(usageModel);
		initialModel.setPlatformMonitoringSpecification(pms);		
		
		resExp.save(null);		
	}
	
	//TIKAJ OSTAL V TOREK - TO SPODNJO METODO JE TREBA NEKAM DAT

	private static SimuLizarConfiguration createConfiguration(IProject project, ConfAlternative ca, int mesurementCount, int timeStop){
		
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
	*/
}
