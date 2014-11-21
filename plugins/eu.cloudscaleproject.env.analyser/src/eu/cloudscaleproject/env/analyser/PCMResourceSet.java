package eu.cloudscaleproject.env.analyser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;

import de.uka.ipd.sdq.pcm.allocation.AllocationFactory;
import de.uka.ipd.sdq.pcm.gmf.allocation.edit.parts.AllocationEditPart;
import de.uka.ipd.sdq.pcm.gmf.allocation.part.PalladioComponentModelAllocationDiagramEditorPlugin;
import de.uka.ipd.sdq.pcm.gmf.composite.edit.parts.ComposedProvidingRequiringEntityEditPart;
import de.uka.ipd.sdq.pcm.gmf.composite.part.PalladioComponentModelComposedStructureDiagramEditorPlugin;
import de.uka.ipd.sdq.pcm.gmf.repository.edit.parts.RepositoryEditPart;
import de.uka.ipd.sdq.pcm.gmf.repository.part.PalladioComponentModelRepositoryDiagramEditorPlugin;
import de.uka.ipd.sdq.pcm.gmf.resource.edit.parts.ResourceEnvironmentEditPart;
import de.uka.ipd.sdq.pcm.gmf.resource.part.PalladioComponentModelDiagramEditorPlugin;
import de.uka.ipd.sdq.pcm.gmf.usage.edit.parts.UsageScenarioEditPart;
import de.uka.ipd.sdq.pcm.gmf.usage.part.PalladioComponentModelUsageDiagramEditorPlugin;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentFactory;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelFactory;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class PCMResourceSet extends ResourceSetImpl{
	
	private static final Logger logger = Logger.getLogger(PCMResourceSet.class.getName());
	
	public static enum ModelType{
		
		 REPOSITORY("repository"), 
		 SYSTEM("system"),
		 RESOURCE("resourceenvironment"),
		 ALLOCATION("allocation"),
		 USAGE("usagemodel");
		 
		 private final String extension;
		 
		 ModelType(String modelExtension){
			 this.extension = modelExtension;
		 }
		 
		 public String getFileExtension(){
			 return extension;
		 }
	}
	
	private final IFile[] modelFiles;
	private final IFile[] diagramFiles;
	
	public PCMResourceSet(){
		modelFiles = new IFile[ModelType.values().length];
		diagramFiles = new IFile[ModelType.values().length];
	}
	
	public PCMResourceSet(IFolder rootFolder) {
		
		this();
		
		IFolder rootFolderModels = rootFolder.getFolder("models");
		
		//create folders if they don't exist jet
		try{
			if(!rootFolder.exists()){
				rootFolder.create(true, true, null);
			}
			if(!rootFolderModels.exists()){
				rootFolderModels.create(true, true, null);
			}
		}
		catch(CoreException e){
			e.printStackTrace();
		}
		
		for(ModelType mt : ModelType.values()){
			this.modelFiles[mt.ordinal()] = rootFolderModels.getFile("pcm." + mt.getFileExtension());
		}
		
		for(ModelType mt : ModelType.values()){
			this.diagramFiles[mt.ordinal()] = rootFolder.getFile("pcm." + mt.getFileExtension() + "_diagram");
		}
	}
	
	public void setModelFile(ModelType model, IFile file){
		IFile old = this.modelFiles[model.ordinal()];
		if(ExplorerProjectPaths.hasEmfResource(this, old)){
			Resource res = ExplorerProjectPaths.getEmfResource(this, old);
			this.getResources().remove(res);
		}
		
		this.modelFiles[model.ordinal()] = file;
	}
	
	public void setDiagramFile(ModelType model, IFile file){
		
		//TODO: if diagram exist so must model file too - set model file automatically
		IFile old = this.diagramFiles[model.ordinal()];
		if(ExplorerProjectPaths.hasEmfResource(this, old)){
			Resource res = ExplorerProjectPaths.getEmfResource(this, old);
			this.getResources().remove(res);
		}
		
		this.diagramFiles[model.ordinal()] = file;
	}
	
	public IFile getModelFile(ModelType model){
		return modelFiles[model.ordinal()];
	}
	
	public IFile getDiagramFile(ModelType model){
		return diagramFiles[model.ordinal()];
	}
	
	public EObject getModelRootObject(ModelType model){
		
		if(getModelFile(model) == null){
			return null;
		}
		
		Resource res = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
		return res.getContents().isEmpty() ? null : res.getContents().get(0);
	}
	
	public Diagram getDiagramRootObject(ModelType model){
		
		if(getDiagramFile(model) == null){
			return null;
		}
		
		Resource res = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
		return res.getContents().isEmpty() ? null : (Diagram)res.getContents().get(0);
	}
	
	public void create(ModelType model){
		
		if(getModelFile(model) != null){
			Resource res = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
			res.getContents().clear();
			res.getContents().add(createModelRootObject(model));
		}
		else{
			throw new IllegalStateException("create(): File not specified! ModelType: " + model.name());
		}
		
		if(getDiagramFile(model) != null){
			Resource resD = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
			resD.getContents().clear();
			resD.getContents().add(createDiagramRootObject(model));
		}
	}
	
	public void createAll(){
		for(ModelType mt : ModelType.values()){
			if(getModelFile(mt) != null){
				create(mt);
			}
		}
	}
	
	public void clear(ModelType model){
		if(getModelFile(model) != null){
			Resource res = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
			if(res != null){res.getContents().clear();};
		}
		else{
			throw new IllegalStateException("clear(): File not specified! ModelType: " + model.name());
		}
		
		if(getDiagramFile(model) != null){
			Resource resD = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
			if(resD != null){resD.getContents().clear();}
		}
	}
	
	public void clearAll(){
		for(ModelType mt : ModelType.values()){
			if(getModelFile(mt) != null){
				clear(mt);
			}
		}
	}
	
	public void setRootObject(ModelType model, EObject object){
		
		Resource res = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
		Resource resD = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
		
		if(res == null || resD == null){
			create(model);
			
			//retrieve resources again 
			res = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
			resD = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
		}
		
		res.getContents().clear();
		resD.getContents().clear();
		
		res.getContents().add(object);
		//recreate diagram for the new root object
		resD.getContents().add(createDiagramRootObject(model));
	}
	
	public void save(ModelType model){
		
		try{
			if(getModelFile(model) != null){
				Resource modelRes = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
				modelRes.save(null);
			}
			else{
				throw new IllegalStateException("save(): File not specified! ModelType: " + model.name());
			}
			
			if(getDiagramFile(model) != null){
				Resource diagramRes = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
				diagramRes.save(null);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void saveAll(){
		for(ModelType mt : ModelType.values()){
			if(getModelFile(mt) != null){
				save(mt);
			}
		}
	}
	
	public void delete(ModelType model){
		
		try{
			if(getModelFile(model) != null){
				Resource modelRes = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
				if(modelRes != null){
					modelRes.delete(null);
				}
			}
			else{
				throw new IllegalStateException("delete(): File not specified! ModelType: " + model.name());
			}
			
			if(getDiagramFile(model) != null){
				Resource diagramRes = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
				if(diagramRes != null){
					diagramRes.delete(null);
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
		
	public void deleteAll(){
		for(ModelType mt : ModelType.values()){
			if(getModelFile(mt) != null){
				delete(mt);
			}
		}
	}
	
	private EObject createModelRootObject(ModelType id){
		EObject model = null;
		
		switch(id){
			case REPOSITORY:
				model = RepositoryFactory.eINSTANCE.createRepository();
				break;
			case SYSTEM:
				model = de.uka.ipd.sdq.pcm.system.SystemFactory.eINSTANCE.createSystem();
				break;
			case RESOURCE:
				model = ResourceenvironmentFactory.eINSTANCE.createResourceEnvironment();
				break;
			case ALLOCATION:
				model = AllocationFactory.eINSTANCE.createAllocation();
				break;
			case USAGE:
				model = UsagemodelFactory.eINSTANCE.createUsageModel();
				break;
			default:
				logger.log(Level.SEVERE, "createModel(): invalid model ID");
				break;
		}
		
		return model;
	}
	
	private Diagram createDiagramRootObject(ModelType id){
		Diagram diagram = null;
		
		EObject model = getModelRootObject(id);
		if(model == null){
			String msg = "createDiagramRootObject(): Model object is null! Can not create diagram from null.";
			logger.severe(msg);
			throw new NullPointerException(msg);
		}
		
		switch(id){
			case REPOSITORY:
				diagram = ViewService.createDiagram(
						model, 
						RepositoryEditPart.MODEL_ID, 
						PalladioComponentModelRepositoryDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				break;
			case SYSTEM:
				diagram = ViewService.createDiagram(
						model, 
						ComposedProvidingRequiringEntityEditPart.MODEL_ID, 
						PalladioComponentModelComposedStructureDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				break;
			case RESOURCE:
				diagram = ViewService.createDiagram(
						model, 
						ResourceEnvironmentEditPart.MODEL_ID, 
						PalladioComponentModelDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				break;
			case ALLOCATION:
				diagram = ViewService.createDiagram(
						model, 
						AllocationEditPart.MODEL_ID, 
						PalladioComponentModelAllocationDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				break;
			case USAGE:
				diagram = ViewService.createDiagram(
						model, 
						UsageScenarioEditPart.MODEL_ID, 
						PalladioComponentModelUsageDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				break;
			default:
				String msg = "createDiagramRootObject(): Invalid model type!";
				logger.log(Level.SEVERE, msg);
				throw new IllegalArgumentException(msg);
		}
		
		diagram.setName(id.name());
		diagram.setElement(model);
		
		return diagram;
	}
	
	/*
	@Override
	public Resource createResource(URI uri, String contentType) {		
	
		Resource res =  super.createResource(uri, contentType);

		//TODO: Don't do this if file already set
		IFile file = ExplorerProjectPaths.getFileFromEmfResource(res);
		ModelType mt = getModelType(file);
		
		if(mt == null){
			throw new IllegalArgumentException("createResource(): Specified URI is invalid for this ResourceSet! URI: " + uri.toString());
		}
		
		if(isModelDiagram(file)){
			if(getDiagramFile(mt) == null){
				setDiagramFile(mt, file);
			}
		}
		else{
			if(getModelFile(mt) == null){
				setModelFile(mt, file);
			}
		}
		
		return res;
	}
	
	public static ModelType getModelType(IFile file){
		String ext = file.getFileExtension();
		for(ModelType mt : ModelType.values()){
			if(ext.startsWith(mt.getFileExtension())){
				return mt;
			}
		}
		
		return null;
	}
	
	public static boolean isModelDiagram(IFile file){
		ModelType mt = getModelType(file);
		if(mt == null){
			throw new IllegalArgumentException("isModelDiagram(): Specified file is invalid for this ResourceSet! URI: " 
						+ file.getRawLocationURI().toString());
		}
		
		if(file.getFileExtension().endsWith("diagram")){
			return true;
		}
		return false;
	}
	*/
}
