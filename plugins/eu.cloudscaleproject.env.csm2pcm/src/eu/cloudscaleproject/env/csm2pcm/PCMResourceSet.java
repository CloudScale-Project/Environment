package eu.cloudscaleproject.env.csm2pcm;

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
		 REPOSITORY, 
		 SYSTEM,
		 RESOURCE,
		 ALLOCATION,
		 USAGE
	}
	
	private final IFolder rootFolder;
	private final IFolder rootFolderModels;
	
	private final IFile[] modelFiles = new IFile[5];
	private final IFile[] diagramFiles = new IFile[5];
	
	public PCMResourceSet(IFolder rootFolder) {
		this.rootFolder = rootFolder;
		this.rootFolderModels = rootFolder.getFolder("models");
		
		//create folders if they don't exist jet
		try{
			if(!this.rootFolder.exists()){
				this.rootFolder.create(true, true, null);
			}
			if(!this.rootFolderModels.exists()){
				this.rootFolderModels.create(true, true, null);
			}
		}
		catch(CoreException e){
			e.printStackTrace();
		}
		
		this.modelFiles[ModelType.USAGE.ordinal()] = rootFolderModels.getFile("pcm.usagemodel");
		this.modelFiles[ModelType.RESOURCE.ordinal()] = rootFolderModels.getFile("pcm.resourceenvironment");
		this.modelFiles[ModelType.ALLOCATION.ordinal()] = rootFolderModels.getFile("pcm.allocation");
		this.modelFiles[ModelType.REPOSITORY.ordinal()] = rootFolderModels.getFile("pcm.repository");
		this.modelFiles[ModelType.SYSTEM.ordinal()] = rootFolderModels.getFile("pcm.system");
		
		this.diagramFiles[ModelType.USAGE.ordinal()] = rootFolder.getFile("pcm.usagemodel_diagram");
		this.diagramFiles[ModelType.RESOURCE.ordinal()] = rootFolder.getFile("pcm.resourceenvironment_diagram");
		this.diagramFiles[ModelType.ALLOCATION.ordinal()] = rootFolder.getFile("pcm.allocation_diagram");
		this.diagramFiles[ModelType.REPOSITORY.ordinal()] = rootFolder.getFile("pcm.repository_diagram");
		this.diagramFiles[ModelType.SYSTEM.ordinal()] = rootFolder.getFile("pcm.system_diagram");
	}
	
	public IFolder getRootFolder(){
		return this.rootFolder;
	}
	
	public IFile getModelFile(ModelType model){
		return modelFiles[model.ordinal()];
	}
	
	public IFile getDiagramFile(ModelType model){
		return diagramFiles[model.ordinal()];
	}
	
	public EObject getModelRootObject(ModelType model){
		Resource res = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
		return res.getContents().isEmpty() ? null : res.getContents().get(0);
	}
	
	public Diagram getDiagramRootObject(ModelType model){
		Resource res = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
		return res.getContents().isEmpty() ? null : (Diagram)res.getContents().get(0);
	}
	
	public void create(ModelType model){
		
		Resource res = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
		Resource resD = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
		
		res.getContents().clear();
		resD.getContents().clear();
		
		res.getContents().add(createModelRootObject(model));
		resD.getContents().add(createDiagramRootObject(model));
	}
	
	public void createAll(){
		for(ModelType mt : ModelType.values()){
			create(mt);
		}
	}
	
	public void clear(ModelType model){
		Resource res = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
		Resource resD = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
		
		if(res != null){res.getContents().clear();};
		if(resD != null){resD.getContents().clear();}
	}
	
	public void clearAll(){
		for(ModelType mt : ModelType.values()){
			clear(mt);
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
	
	public void load(ModelType model){
		
	}
	
	public void save(ModelType model){
		Resource modelRes = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
		Resource diagramRes = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
		
		try{
			modelRes.save(null);
			diagramRes.save(null);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void saveAll(){
		for(ModelType mt : ModelType.values()){
			save(mt);
		}
	}
	
	public void delete(ModelType model){
		
		IFile modelFile = getModelFile(model);
		IFile diagramFile = getDiagramFile(model);
				
		Resource modelRes = ExplorerProjectPaths.getEmfResource(this, modelFile);
		Resource diagramRes = ExplorerProjectPaths.getEmfResource(this, diagramFile);
		
		try{
			if(modelRes != null){
				modelRes.delete(null);
			}
			if(diagramRes != null){
				diagramRes.delete(null);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
		
	public void deleteAll(){
		for(ModelType mt : ModelType.values()){
			delete(mt);
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

}
