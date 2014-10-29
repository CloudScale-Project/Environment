package eu.cloudscaleproject.env.spotter;

import java.util.LinkedList;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.model.xml.HierarchyFactory;
import org.spotter.eclipse.ui.model.xml.MeasurementEnvironmentFactory;
import org.spotter.eclipse.ui.util.SpotterProjectSupport;
import org.spotter.eclipse.ui.util.SpotterUtils;
import org.spotter.shared.configuration.FileManager;
import org.spotter.shared.configuration.JobDescription;
import org.spotter.shared.environment.model.XMeasurementEnvObject;
import org.spotter.shared.environment.model.XMeasurementEnvironment;
import org.spotter.shared.hierarchy.model.XPerformanceProblem;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class Util {
	
	public static final String KEY_SELECTED_INPUT = "SelectedInputAlternative";
	
	public static JobDescription createJobDescription(EditorInputFolder editorInput) throws UICoreException {
		JobDescription jobDescription = new JobDescription();

		IFile spotterFile = editorInput.getResource().getFile(FileManager.SPOTTER_CONFIG_FILENAME);
		Properties dynamicSpotterConfig = SpotterProjectSupport.getSpotterConfig(spotterFile);
		jobDescription.setDynamicSpotterConfig(dynamicSpotterConfig);

		MeasurementEnvironmentFactory envFactory = MeasurementEnvironmentFactory.getInstance();
		String envFile = editorInput.getResource().getFile(FileManager.ENVIRONMENT_FILENAME).getLocation().toString();
		XMeasurementEnvironment measurementEnvironment = envFactory.parseXMLFile(envFile);
		jobDescription.setMeasurementEnvironment(measurementEnvironment);

		HierarchyFactory hierFactory = HierarchyFactory.getInstance();
		String hierFile = editorInput.getResource().getFile(FileManager.HIERARCHY_FILENAME).getLocation().toString();
		XPerformanceProblem hierarchy = hierFactory.parseHierarchyFile(hierFile);
		jobDescription.setHierarchy(hierarchy);

		return jobDescription;
	}
	
	public static void bindEditorInputs(EditorInputFolder inputAlternative, EditorInputFolder runAlternative){
		
		IFile inputEnvFile = ((IFolder)inputAlternative.getResource()).getFile("mEnv.xml");
		IFile confEnvFile = ((IFolder)runAlternative.getResource()).getFile("mEnv.xml");
		
		MeasurementEnvironmentFactory factory = MeasurementEnvironmentFactory.getInstance();
		try {			
			//input model
			XMeasurementEnvironment inputEnv = factory.parseXMLFile(inputEnvFile.getLocation().toString());
			if(!confEnvFile.exists()){
				try {
					SpotterUtils.writeElementToFile(confEnvFile, inputEnv);
					return;
				} catch (JAXBException | CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//run conf model
			XMeasurementEnvironment confEnv = factory.parseXMLFile(confEnvFile.getLocation().toString());
			
			confEnv.setInstrumentationController(inputEnv.getInstrumentationController() != null ? 
					new LinkedList<XMeasurementEnvObject>(inputEnv.getInstrumentationController()) : new LinkedList<XMeasurementEnvObject>());
			confEnv.setMeasurementController(inputEnv.getMeasurementController() != null ? 
					new LinkedList<XMeasurementEnvObject>(inputEnv.getMeasurementController()) : new LinkedList<XMeasurementEnvObject>());
			
			try {
				SpotterUtils.writeElementToFile(confEnvFile, confEnv);
			} catch (JAXBException | CoreException e) {
				e.printStackTrace();
			}
			
		} catch (UICoreException e) {
			e.printStackTrace();
		}
		
		//save to properties
		runAlternative.setProperty(KEY_SELECTED_INPUT, inputAlternative.getResource().getName());
		runAlternative.save();
	}
}
