package eu.cloudscaleproject.env.analyser.alternatives;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.emf.core.internal.resources.PathmapManager;
import org.palladiosimulator.experimentautomation.experiments.Experiment;
import org.palladiosimulator.experimentautomation.experiments.ExperimentRepository;
import org.palladiosimulator.experimentautomation.experiments.ExperimentsFactory;
import org.palladiosimulator.experimentautomation.experiments.InitialModel;
import org.scaledl.usageevolution.UsageEvolution;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class ConfAlternative extends EditorInputFolder{
			
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ConfAlternative.class.getName());
	
	public static final String KEY_NAME = "name";
	
	protected final ResourceSet resSet = new ResourceSetImpl();
	
	public ConfAlternative(IProject project, IFolder folder){
		super(project, folder);
	}
	
	public ResourceSet getResourceSet(){
		return this.resSet;
	}
	
	public UsageEvolution getUsageEvolution(){
		IFile ueFile = getFileResource(ToolchainUtils.KEY_FILE_USAGEEVOLUTION);
		if(ueFile == null){
			return null;
		}
		
		Resource res = ExplorerProjectPaths.getEmfResource(resSet, ueFile);
		EObject eobject = res.getContents().isEmpty() ? null : res.getContents().get(0);
		
		if(eobject instanceof UsageEvolution){
			return (UsageEvolution)eobject;
		}
		return null;
	}
	
	public void setUsageEvolution(EditorInputFolder res){
		IFile fileUsageEvo = res.getFileResource(ToolchainUtils.KEY_FILE_USAGEEVOLUTION);
		setResource(ToolchainUtils.KEY_FOLDER_USAGEEVOLUTION_ALT, res.getResource());
		if(fileUsageEvo == null){
			return;
		}
		
		Resource resUE = ExplorerProjectPaths.getEmfResource(resSet, fileUsageEvo);
		
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
	
	public void setInitialModel(EditorInputFolder editorInput){
		
		Experiment exp = getExperiment();
		
		InitialModel initialModel = exp.getInitialModel();
		if(editorInput == null && initialModel != null){
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
				IFile file = editorInput.getFileResource(ToolchainUtils.KEY_FILE_ALLOCATION);
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
			
			//usage			
			{
				IFile file = editorInput.getFileResource(ToolchainUtils.KEY_FILE_USAGE);
				if(file != null && file.exists()){
					Resource res = ExplorerProjectPaths.getEmfResource(resSet, file);
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
		
		configureInput(exp, initialModel, editorInput);
		
		setResource(ToolchainUtils.KEY_FOLDER_ANALYSER_INPUT_ALT, editorInput.getResource());
	}
	
	protected void configureInput(Experiment exp, InitialModel initialModel, EditorInputFolder editorInput){
		//override in sub-classes
	}
	
	public Experiment getExperiment() {
		
		IFile expFile = getFileResource(ToolchainUtils.KEY_FILE_EXPERIMENTS);
		if(expFile == null || !expFile.exists()){
			List<IFile> files = PCMResourceSet.findResource(getResource(), PCMModelType.EXPERIMENTS.getFileExtension());			
			if(files.size() > 0){
				//TODO: for now just use fist model file found
				expFile = files.get(0);
			}
			else{
				//create new Experiment model file
				expFile = getResource().getFile(PCMModelType.EXPERIMENTS.getFullName());
				setResource(ToolchainUtils.KEY_FILE_EXPERIMENTS, expFile);
			}			
		}
		
		Resource res = ExplorerProjectPaths.getEmfResource(resSet, expFile);
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
		
		return (Experiment)firsExperiment;		
	}
	
	public IFile getPMS(){
		return getFileResource(ToolchainUtils.KEY_FILE_PMS);
	}
	
	public IFile getMeasuringPoints(){
		return getFileResource(ToolchainUtils.KEY_FILE_MESURPOINTS);
	}
	
	public IFile getSLO(){
		return getFileResource(ToolchainUtils.KEY_FILE_SLO);
	}

	@Override
	public void doSave() {

		super.doSave();
		for(Resource res : resSet.getResources()){
			try {
				if(!res.getContents().isEmpty()){
					res.save(null);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.severe("Conf alternative: "+ getResource().getLocation().toString() 
								+" Can not save resource: "+ res.getURI().toString());
				e.printStackTrace();
			}
		}

		try {
			getResource().refreshLocal(IFile.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doLoad(){
		
		super.doLoad();
		
		try {
			loadModels();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	private final void loadModels() throws IOException {
		// find and load resources
		for (IFile f : PCMResourceSet.findResource(getResource(), PCMModelType.EXPERIMENTS.getFileExtension())) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, f);
			res.unload();
			res.load(null);
		}
		for (IFile f : PCMResourceSet.findResource(getResource(), PCMModelType.PMS.getFileExtension())) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, f);
			res.unload();
			res.load(null);		
		}
		for (IFile f : PCMResourceSet.findResource(getResource(), PCMModelType.VARIATIONS.getFileExtension())) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, f);
			res.unload();
			res.load(null);		
		}
		for (IFile f : PCMResourceSet.findResource(getResource(), PCMModelType.MEASURING_POINT.getFileExtension())) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, f);
			res.unload();
			res.load(null);		
		}
		for (IFile f : PCMResourceSet.findResource(getResource(), PCMModelType.SLO.getFileExtension())) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, f);
			res.unload();
			res.load(null);		
		}
	}
}
