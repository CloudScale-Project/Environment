package eu.cloudscaleproject.env.analyser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.emf.core.internal.resources.PathmapManager;
import org.eclipse.ui.PlatformUI;
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

import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelFactory;
import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.dialogs.TextInputDialog;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class AnalyserUtil {

	private static final String INPUT_PROP_PREFIX = "tools.analyser.input.";
	private static final String CONF_PROP_PREFIX = "tools.analyser.input.";
	
	public static final String ALTERNATIVE_FOLDER = "Alternatives";
	public static final String ALTERNATIVE_SUFIX = "alt";
	
	public static final String DEFAULT_CAPACITY_ALTERNATIVE_FOLDER = "Default capacity";

	public static IFolder getInputFolder(IProject project) {
		IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(
				project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
		String inputFolderString = ExplorerProjectPaths
				.getProjectProperty(project,
						ExplorerProjectPaths.KEY_FOLDER_INPUT);
		IFolder inputFolder = analyserFolder
				.getFolder(inputFolderString);

		return inputFolder;
	}
	
	public static IFolder getConfFolder(IProject project) {
		IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(
				project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
		String configurationFolderString = ExplorerProjectPaths
				.getProjectProperty(project,
						ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION);
		IFolder configurationFolder = analyserFolder
				.getFolder(configurationFolderString);

		return configurationFolder;
	}
	
	/////////////////////////INPUT ALTERNATIVES////////////////////////////////
	
	public static InputAlternative getCurrentInputAlternative(IProject project){
		Properties prop = ExplorerProjectPaths.getProjectProperties(project);
		String projectPath = prop.getProperty(INPUT_PROP_PREFIX+"default", null);
		if(projectPath == null){
			return null;
		}
		
		InputAlternative ia = new InputAlternative(project, project.getFile(new Path(projectPath)));
		return ia;
	}
	
	public static void setCurrentInputAlternative(IProject project, InputAlternative alternative){
		ExplorerProjectPaths.setProjectProperty(project, INPUT_PROP_PREFIX+"default", alternative.getProjectPath());
	}
	
	public static InputAlternative getTransformInputAlternative(IProject project){
		InputAlternative ia = AnalyserUtil.getInputAlternative(project, "transform."+AnalyserUtil.ALTERNATIVE_SUFIX);
		if(ia == null){
			ia = AnalyserUtil.createNewInputAlternative(project, "transform."+AnalyserUtil.ALTERNATIVE_SUFIX, "From transformation");
			ia.save();
		}
		return ia;
	}
	
	public static boolean hasInputAlternative(IProject project, String filename){
		IFolder analyserInputFolder = getInputFolder(project);
		IFolder alternatives = analyserInputFolder.getFolder(ALTERNATIVE_FOLDER);
		
		if(!alternatives.exists()){
			return false;
		}
		
		IFile alternativeFile = alternatives.getFile(filename);
		return alternativeFile.exists();
	}
	
	public static void createNewInputAlternative(final IProject project, final String filename, 
															 final BasicCallback<InputAlternative> callback){
		
		TextInputDialog dialog = new TextInputDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
				new BasicCallback<String>() {
			
			@Override
			public void handle(String text) {
				callback.handle(createNewInputAlternative(project, filename, text));
			}
		});
		dialog.open();
	}
	
	public static InputAlternative createNewInputAlternative(IProject project, String filename, String name){
		
		IFolder analyserInputFolder = getInputFolder(project);
		IFolder alternatives = analyserInputFolder.getFolder(ALTERNATIVE_FOLDER);
		
		if(!alternatives.exists()){
			//create alternative folder
			try {
				alternatives.create(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		else if(AnalyserUtil.hasInputAlternative(project, filename)){
			//check and fix duplicated filenames
			int i = 1;
			String filenameNew = filename;
			
			while(AnalyserUtil.hasInputAlternative(project, filenameNew)){
				filenameNew = filename.replaceFirst("[0-9]*\\.", (i++) + ".");
			}
			filename = filenameNew;
		}
		
		InputAlternative ia = new InputAlternative(project, alternatives.getFile(filename));
		ia.setName(name);
		ia.save();
		return ia;
	}
	
	public static InputAlternative getInputAlternative(IProject project, String filename){
		
		IFolder analyserInputFolder = getInputFolder(project);
		IFolder alternatives = analyserInputFolder.getFolder(ALTERNATIVE_FOLDER);
		
		if(!alternatives.exists()){
			return null;
		}
		
		if(!alternatives.getFile(filename).exists()){
			return null;
		}
		
		return new InputAlternative(project, alternatives.getFile(filename));
	}
	
	public static void deleteInputAlternative(IProject project, String filename){
		List<InputAlternative> alt = getInputAlternatives(project);
		for(InputAlternative ia : alt){
			if(filename.equals(ia.getName())){
				ia.delete();
			}
		}
	}
	
	public static List<InputAlternative> getInputAlternatives(IProject project){
		List<InputAlternative> out = new ArrayList<InputAlternative>();
		
		IFolder analyserInputFolder = getInputFolder(project);
		IFolder alternatives = analyserInputFolder.getFolder(ALTERNATIVE_FOLDER);
		
		if(alternatives.exists()){
			try {
				for(IResource res : alternatives.members()){
					if(res instanceof IFile && ALTERNATIVE_SUFIX.equals(res.getFileExtension())){
						InputAlternative alt = new InputAlternative(project, (IFile)res);
						alt.load();
						out.add(alt);
					}
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return out;
	}
	
	////////////////////////////RUN ALTERNATIVES///////////////////////////////////
	
	public static InputAlternative getCurrentConfAlternative(IProject project){
		Properties prop = ExplorerProjectPaths.getProjectProperties(project);
		String projectPath = prop.getProperty(CONF_PROP_PREFIX+"default", null);
		if(projectPath == null){
			return null;
		}
		
		InputAlternative ia = new InputAlternative(project, project.getFile(new Path(projectPath)));
		return ia;
	}
	
	public static void setCurrentConfAlternative(IProject project, InputAlternative alternative){
		ExplorerProjectPaths.setProjectProperty(project, CONF_PROP_PREFIX+"default", alternative.getProjectPath());
	}
	
	public static ConfAlternative getTransformConfAlternative(IProject project){
		ConfAlternative ia = AnalyserUtil.getConfAlternative(project, "transform."+AnalyserUtil.ALTERNATIVE_SUFIX);
		if(ia == null){
			ia = AnalyserUtil.createNewConfAlternative(project, "transform."+AnalyserUtil.ALTERNATIVE_SUFIX, "From transformation");
			ia.save();
		}
		return ia;
	}
	
	public static boolean hasConfAlternative(IProject project, String filename){
		IFolder analyserConfFolder = getConfFolder(project);
		IFolder alternativeFolder = analyserConfFolder.getFolder(filename);
		return alternativeFolder.exists();
	}
	
	public static void createNewConfAlternative(final IProject project, final String filename, 
															 final BasicCallback<ConfAlternative> callback){
		
		TextInputDialog dialog = new TextInputDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
				new BasicCallback<String>() {
			
			@Override
			public void handle(String text) {
				callback.handle(createNewConfAlternative(project, filename, text));
			}
		});
		dialog.open();
	}
	
	public static ConfAlternative createNewConfAlternative(IProject project, String filename, String name){
		
		
		IFolder analyserConfFolder = getConfFolder(project);		
		
		if(AnalyserUtil.hasConfAlternative(project, filename)){
			//check and fix duplicated filenames
			int i = 1;
			String filenameNew = filename;
			
			while(AnalyserUtil.hasConfAlternative(project, filenameNew)){
				filenameNew = filename.replaceFirst("[0-9]*\\.", (i++) + ".");
			}
			filename = filenameNew;
		}
		
		ConfAlternative ia = new ConfAlternative(project, analyserConfFolder.getFolder(filename));
		ia.setName(name);
		ia.save();
		return ia;
	}
	
	public static ConfAlternative getConfAlternative(IProject project, String filename){
		
		IFolder analyserConfFolder = getConfFolder(project);
		
		if(!analyserConfFolder.getFolder(filename).exists()){
			return null;
		}
		
		return new ConfAlternative(project, analyserConfFolder.getFolder(filename));
	}
	
	public static void deleteConfAlternative(IProject project, String filename){
		List<ConfAlternative> alt = getConfAlternatives(project);
		for(ConfAlternative ia : alt){
			if(filename.equals(ia.getName())){
				ia.delete();
			}
		}
	}
	
	public static List<ConfAlternative> getConfAlternatives(IProject project){
		List<ConfAlternative> out = new ArrayList<ConfAlternative>();
		
		IFolder analyserConfFolder = getConfFolder(project);
			
		try {
			for(IResource res : analyserConfFolder.members()){
				if(res instanceof IFolder){
					ConfAlternative alt = new ConfAlternative(project, (IFolder)res);
					if(alt.hasPropFile()){
						alt.load();
						out.add(alt);
					}
				}
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return out;
	}
	
	///////////////////////// DEFAULT CONFIGURATIONS ////////////////////////////////////
	
	public static SimuLizarConfiguration createConfiguration(IProject project, ConfAlternative ca, int mesurementCount, int timeStop){
		
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
	
	public static ConfAlternative retrieveCapacityExperiment(IProject project, boolean forceRecreate) throws IOException{
		
		ConfAlternative ca = AnalyserUtil.getConfAlternative(project, DEFAULT_CAPACITY_ALTERNATIVE_FOLDER);
		if(ca != null){
			if(forceRecreate){
				ca.delete();
			}
			else{
				return ca;
			}
		}
		ca = AnalyserUtil.createNewConfAlternative(project, DEFAULT_CAPACITY_ALTERNATIVE_FOLDER, "User capacity");
		
		Resource resExp = ca.getExperiments();
		ExperimentRepository expRep = resExp.getContents().size() > 0 ? (ExperimentRepository)resExp.getContents().get(0) : null;
		if(expRep == null){
			expRep = ExperimentsFactory.eINSTANCE.createExperimentRepository();
			resExp.getContents().add(expRep);
		}
		
		Experiment exp = expRep.getExperiments().size() > 0 ? (Experiment)expRep.getExperiments().get(0) : null;
		if(exp == null){
			exp = ExperimentsFactory.eINSTANCE.createExperiment();
			expRep.getExperiments().add(exp);
		}
		exp.setRepetitions(1);
		exp.setName("Capacity measurement");
		
		//create and set measurement stop condition
		MeasurementCountStopCondition msc = AbstractsimulationFactory.eINSTANCE.createMeasurementCountStopCondition();
		msc.setMeasurementCount(100);
		exp.getStopConditions().add(msc);
		
		//create and set time stop condition
		SimTimeStopCondition tsc = AbstractsimulationFactory.eINSTANCE.createSimTimeStopCondition();
		tsc.setSimulationTime(-1);
		exp.getStopConditions().add(tsc);
		
		//create and set simulation configuration
		exp.getToolConfiguration().add(createConfiguration(project, ca, 1000, -1));
		
		InitialModel initialModel = ExperimentsFactory.eINSTANCE.createInitialModel();
		exp.setInitialModel(initialModel);
		
		//create default usage scenario
		UsageModel usageModel = UsagemodelFactory.eINSTANCE.createUsageModel();
		UsageScenario usageScenario = UsagemodelFactory.eINSTANCE.createUsageScenario();
		usageModel.getUsageScenario_UsageModel().add(usageScenario);
		Resource resUsage = ExplorerProjectPaths.createEmfResource(ca.getResourceSet(), ((IFolder)ca.getResource()).getFile("analyser.usagemodel"));
		resUsage.getContents().add(usageModel);
		resUsage.save(null);
		
		//create measuring point
		UsageScenarioMeasuringPoint measurePoint = PcmmeasuringpointFactory.eINSTANCE.createUsageScenarioMeasuringPoint();
		measurePoint.setUsageScenario(usageScenario);
		Resource resMp = ExplorerProjectPaths.createEmfResource(ca.getResourceSet(), ((IFolder)ca.getResource()).getFile("analyser.measuringpoint"));
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
		Resource resPms = ExplorerProjectPaths.createEmfResource(ca.getResourceSet(), ((IFolder)ca.getResource()).getFile("analyser.pms"));
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
		
		return ca;
	}

	/*
	public static void createRunAlternative(IProject project, String filename) {

		IFolder configurationFolder = getConfigurationFolder(project);

		InputStream is = null;
		FileInputStream fis = null;
		try {
			IFile analyserConfig = configurationFolder.getFile(confFilename);
			if (!analyserConfig.exists()) {
				
				if(LAUNCH_TYPE_SIMULIZAR.equals(confType)){
					is = AnalyserUtil.class.getClassLoader().getResourceAsStream(PLUGIN_FOLDER + PLUGIN_SIMULIZAR_FILENAME);
				}
				else if(LAUNCH_TYPE_EXPERIMENT.equals(confType)){
					is = AnalyserUtil.class.getClassLoader().getResourceAsStream(PLUGIN_FOLDER + PLUGIN_EXPERIMENT_FILENAME);
				}
				
				if(is != null){
					analyserConfig.create(is, false, null);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String[] getRunAlternatives (IProject project, String type)
	{
		IFolder configurationFolder = getConfigurationFolder(project);
		LinkedList<String> l = new LinkedList<String>();
		
		try {
			for (IResource r : configurationFolder.members())
			{
				if (r instanceof IFile && r.exists())
				{
					ILaunchConfiguration lc = getLaunchConfiguration(project, r.getName());
					if(type.equals(lc.getType().getIdentifier())){
						l.add(r.getName());
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return l.toArray(new String[l.size()]);
	}
	
	public static void deleteRunAlternative (IProject project, String configFilename)
	{
		IFolder configurationFolder = getConfigurationFolder(project);
		IFile alternativeFile = configurationFolder.getFile(configFilename);
		
		if (alternativeFile.exists())
		{
			try {
				alternativeFile.delete(true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void runAlternative(IProject project, String configFilename) {

        ILaunchConfiguration launchAnalyserConf = getLaunchConfiguration(project, configFilename);
        try {
			launchAnalyserConf.getWorkingCopy().launch(ILaunchManager.DEBUG_MODE, new NullProgressMonitor());
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////
	
	public static List<String> getInputs (IProject project)
	{
		List<String> inputs = new ArrayList<String>();
		
		IFolder analyserFolder = ExplorerProjectPaths.getProjectFolder(project,
				ExplorerProjectPaths.KEY_FOLDER_ANALYSER);
		IFolder analyserInputFolder = analyserFolder.getFolder(ExplorerProjectPaths.getProjectProperty(project,
						ExplorerProjectPaths.KEY_FOLDER_INPUT));
		
		try {
			for(IResource resource : analyserInputFolder.members()){
				if(resource instanceof IFolder && ((IFolder)resource).getFolder("models").exists()){
					inputs.add(resource.getName());
				}
			}
		}
		catch (CoreException e1) {
			e1.printStackTrace();
		}
		
		return inputs;
	}
	
	public static String getLaunchConfigurationType (IProject project, String configFileName)
	{
		IFile configFile = getConfigurationFile(project, configFileName);
		ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfiguration launchConf = mgr.getLaunchConfiguration(configFile);
		try {
			return launchConf.getType().getIdentifier();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static ILaunchConfiguration getLaunchConfiguration (IProject project, String configFileName)
	{
		IFile configFile = getConfigurationFile(project, configFileName);
		ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfiguration launchConf = mgr.getLaunchConfiguration(configFile);
		
		return launchConf;
	}

	public static void showConfigurationFile(IProject project, String configFileName) {
			ILaunchConfiguration launchConf = getLaunchConfiguration(project, configFileName);
			DebugUITools.openLaunchConfigurationDialog(

			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
            	launchConf, DebugUITools.getLaunchGroup(launchConf, "run")
                .getIdentifier(), null);
		// TODO: open "Run Configurations..." editor
	}

	public static IFile getConfigurationFile(IProject project, String configFileName) {
		IFolder configurationFolder = getConfigurationFolder(project);
		return configurationFolder.getFile(configFileName);
	}
	
	*/
}
