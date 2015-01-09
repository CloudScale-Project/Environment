package eu.cloudscaleproject.env.spotter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.spotter.eclipse.ui.UICoreException;
import org.spotter.eclipse.ui.model.xml.MeasurementEnvironmentFactory;
import org.spotter.eclipse.ui.util.SpotterUtils;
import org.spotter.shared.environment.model.XMeasurementEnvObject;
import org.spotter.shared.environment.model.XMeasurementEnvironment;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ResourceUtils {
	
	public static final String KEY_PARENT_EDITOR_RESOURCE = "parent.editor.resource";
	
	public static void createDefaultFile(IFile file, String string){
		if (!file.exists()) {
		    byte[] bytes = string.getBytes();
		    
		    java.io.InputStream source = new java.io.ByteArrayInputStream(bytes);
		    try {
				file.create(source, IResource.NONE, null);
			} catch (CoreException e1) {
				try {
					source.close();
				} catch (java.io.IOException e2) {
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
		}
	}
	
	public static List<EditorInputFolder> getConfAlternatives(IProject project, EditorInputFolder inputAlt){
		
		ResourceProvider confResourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(project, ToolchainUtils.SPOTTER_DYN_CONF_ID);
		
		List<EditorInputFolder> out = new ArrayList<EditorInputFolder>();
		
		for(IEditorInputResource res : confResourceProvider.getResources()){
			if(res instanceof EditorInputFolder){
				String iaResName = ((EditorInputFolder)res).getProperty(KEY_PARENT_EDITOR_RESOURCE);
				if(iaResName != null && !iaResName.isEmpty() && inputAlt.getResource().getName().equals(iaResName)){
					out.add((EditorInputFolder)res);
				}
			}
		}
		
		return out;
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
		runAlternative.setProperty(KEY_PARENT_EDITOR_RESOURCE, inputAlternative.getResource().getName());
		runAlternative.save();
	}
	
	public static void registerResourceFactories(){
		
	}

}
