package eu.cloudscaleproject.env.analyser;

import java.beans.PropertyChangeSupport;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.palladiosimulator.experimentautomation.experiments.Experiment;
import org.palladiosimulator.experimentautomation.experiments.ExperimentsFactory;
import org.palladiosimulator.experimentautomation.experiments.InitialModel;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfAlternative extends PropertyChangeSupport implements IEditorInputResource{
			
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ConfAlternative.class.getName());
	
	private static final String PROP_FILENAME = "prop.alt";
	
	public static final String KEY_NAME = "name";
	public static final String KEY_INPUT = "input";
	
	public static final String MODEL_EXPERIMENTS = "experiments";
	public static final String MODEL_USAGE = "usagemodel";
	public static final String MODEL_PMS = "pms";
	public static final String MODEL_VARIATIONS = "variations";
	public static final String MODEL_MESURPOINTS = "pcmmeasuringpoint";
	public static final String MODEL_SLO = "slo";

	
	private final IProject project;
	private final IFolder folder;
	
	private final IFile file;
	protected Properties source = new Properties();
	
	private final ResourceSet resSet = new ResourceSetImpl();
	
	public ConfAlternative(IProject project, IFolder folder){
		super(folder);
		this.project = project;
		this.folder = folder;
		
		file = folder.getFile(PROP_FILENAME);
		if(folder.exists()){
			load();
		}
	}
	
	@Override
	public IResource getResource() {
		return folder;
	}
	
	public ResourceSet getResourceSet(){
		return this.resSet;
	}
	
	public String getProjectPath(){
		return folder.getProjectRelativePath().toString();
	}
	
	public boolean hasPropFile(){
		return folder.getFile(PROP_FILENAME).exists();
	}

	@Override
	public String getName() {
		String prop = source.getProperty(KEY_NAME);
		if(prop == null){
			return file.getName();
		}
		return prop;
	}


	@Override
	public void setName(String name) {
		String old = getName();
		source.setProperty(KEY_NAME, name);
		firePropertyChange(KEY_NAME, old, name);
	}
	
	public InputAlternative getInput(){
		String prop = source.getProperty(KEY_INPUT);
		if(prop == null){
			return null;
		}
		return (InputAlternative)ResourceRegistry.getInstance()
				.getResourceProvider(project, ToolchainUtils.ANALYSER_INPUT_ID).getResource(prop);
	}
	
	public void setInput(InputAlternative ia){
		
		Resource expResource = getExperiments();
		EObject root = expResource.getContents().size() > 0 ? expResource.getContents().get(0) : null;
		if(root == null){
			Experiment exp = ExperimentsFactory.eINSTANCE.createExperiment();
			root = exp;
			expResource.getContents().add(exp);
		}
		
		if(!(root instanceof Experiment)){
			throw new IllegalArgumentException("Experiments model with root object type other then Experiment is not supported!");
		}
		Experiment exp = (Experiment)root;
		
		InitialModel initialModel = exp.getInitialModel();
		if(initialModel != null && ia == null){
			exp.setInitialModel(null);
			
			InputAlternative old = getInput();
			source.setProperty(KEY_INPUT, "");
			firePropertyChange(KEY_INPUT, old, ia);
			
			return;
		}
		
		if(initialModel == null){
			initialModel = ExperimentsFactory.eINSTANCE.createInitialModel();
			exp.setInitialModel(initialModel);
		}
		
		//set initial model
		{
			IFile file = ia.getAllocation();
			if(file != null && file.exists()){
				Resource res = ExplorerProjectPaths.getEmfResource(resSet, file);
				if(!res.getContents().isEmpty()){
					initialModel.setAllocation((Allocation)res.getContents().get(0));
				}
			}
			else{
				initialModel.setAllocation(null);
			}
		}
		
		InputAlternative old = getInput();
		source.setProperty(KEY_INPUT, ia == null ? null : ia.getResource().getName());
		firePropertyChange(KEY_INPUT, old, ia);
	}
	
	public Resource getExperiments() {
		List<IFile> exp = findResource(folder, MODEL_EXPERIMENTS);
		if(exp.size() > 0){
			return ExplorerProjectPaths.getEmfResource(resSet, exp.get(0));
		}
		return createEmfResource(MODEL_EXPERIMENTS);
	}
	
	public boolean hasModel(String extension){
		return getModels(extension).size() > 0 ? true : false;
	}
	
	public List<Resource> getModels(String type) {
		
		List<Resource> out = new ArrayList<Resource>();
		for(Resource res : resSet.getResources()){
			if(res.getURI().lastSegment().endsWith(type)){
				out.add(res);
			}
		}
		return out;
	}
	
	public Resource createModel(String type) {
		return createEmfResource(type);
	}

	@Override
	public synchronized final void save() {

		if(!folder.exists()){
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (!file.exists()) {
			try (InputStream is = new ByteArrayInputStream(new byte[0])) {
				file.create(is, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try (OutputStream os = new FileOutputStream(new File(
				file.getLocationURI()))) {
			source.storeToXML(os, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Resource res : resSet.getResources()){
			try {
				if(!res.getContents().isEmpty()){
					res.save(null);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			folder.refreshLocal(IFile.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized  final void copyFrom(IResource folder) {

		if(!(folder instanceof IFolder)){
			logger.severe("copyFrom("+ folder.toString() +"): input attribute must be instance of IFolder!");
			return;
		}
		
		if (!folder.exists()) {
			return;
		}
		
		try {
			if(this.folder.exists()){
				this.folder.delete(true, null);
			}
			
			folder.copy(this.folder.getFullPath(), true, null);
		} catch (CoreException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			folder.refreshLocal(IFile.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		load();
	}

	@Override
	public synchronized  final void load(){

		if (!folder.exists()) {
			return;
		}
		
		try {
			loadModels();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if(file.exists()){
			try (InputStream is = file.getContents(true)) {
				source.loadFromXML(is);

			} catch (InvalidPropertiesFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private final void loadModels() throws IOException {
		// find and load resources
		for (IFile f : findResource(folder, MODEL_EXPERIMENTS)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, f);
			res.unload();
			res.load(null);
		}
		for (IFile f : findResource(folder, MODEL_USAGE)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, f);
			res.unload();
			res.load(null);		
		}
		for (IFile f : findResource(folder, MODEL_PMS)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, f);
			res.unload();
			res.load(null);		
		}
		for (IFile f : findResource(folder, MODEL_VARIATIONS)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, f);
			res.unload();
			res.load(null);		
		}
		for (IFile f : findResource(folder, MODEL_MESURPOINTS)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, f);
			res.unload();
			res.load(null);		
		}
		for (IFile f : findResource(folder, MODEL_SLO)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, f);
			res.unload();
			res.load(null);		
		}
	}

	@Override
	public synchronized  void delete() {
		if (folder.exists()) {
			try {
				folder.delete(true, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private List<IFile> findResource(IFolder folder, String extension){
		
		List<IFile> files = new ArrayList<IFile>();
		
		try {
			for(IResource r : folder.members()){
				if(r instanceof IFolder){
					IFolder f = (IFolder)r;
					files.addAll(findResource(f, extension));
				}
				if(r instanceof IFile){
					IFile f = (IFile)r;
					if(extension.equals(f.getFileExtension())){
						files.add(f);
					}
				}
			}
		}
		catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return files;
	}
	
	private Resource createEmfResource(String extension){
		IFile f = folder.getFile(project.getName()+"." + extension);
		return ExplorerProjectPaths.getEmfResource(resSet, f);
	}

	@Override
	public String getProperty(String key) {
		return source.getProperty(key);
	}

	@Override
	public void setProperty(String key, String value) {
		this.source.setProperty(key, value);
	}
}
