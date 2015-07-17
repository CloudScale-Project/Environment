package eu.cloudscaleproject.env.analyser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.palladiosimulator.pcm.allocation.AllocationFactory;
import org.palladiosimulator.pcm.core.entity.NamedElement;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory;
import org.palladiosimulator.pcm.system.SystemFactory;
import org.palladiosimulator.pcm.usagemodel.UsagemodelFactory;
import org.scaledl.usageevolution.UsageevolutionFactory;

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
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ModelType;

public class PCMResourceSet extends ResourceSetImpl{
	
	private static final Logger logger = Logger.getLogger(PCMResourceSet.class.getName());
	
	private final IFile[] modelFiles;
	private final IFile[] diagramFiles;
	
	private final IFolder rootFolder;
	private final IFolder rootFolderModels;
	
	public PCMResourceSet(IFolder rootFolder) {
		
		modelFiles = new IFile[ModelType.values().length];
		diagramFiles = new IFile[ModelType.values().length];
		
		this.rootFolder = rootFolder;
		this.rootFolderModels = rootFolder.getFolder("models");
		
		//create folders if they don't exist jet
		/*
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
		*/
		
		for(ModelType mt : ModelType.values()){
			this.modelFiles[mt.ordinal()] = this.rootFolderModels.getFile("pcm." + mt.getFileExtension());
		}
		
		for(ModelType mt : ModelType.values()){
			this.diagramFiles[mt.ordinal()] = this.rootFolder.getFile("pcm." + mt.getFileExtension() + "_diagram");
		}
	}
	
	public static List<IFile> findResource(IContainer folder, String extension){
		
		List<IFile> files = new ArrayList<IFile>();
		
		if(!folder.exists()){
			return files;
		}
		
		try {
			for(IResource r : folder.members()){
				if(r instanceof IContainer){
					IContainer f = (IContainer)r;
					files.addAll(findResource(f, extension));
				}
				if(r instanceof IFile){
					IFile f = (IFile)r;
					if(f.getName().endsWith(extension)){
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
		
		if(hasDiagram(model) && getDiagramFile(model) != null){
			Resource resD = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
			resD.getContents().clear();
			resD.getContents().add(createDiagramRootObject(getModelRootObject(model)));
		}
	}
	
	public void createAll(ModelType[] models){
		for(ModelType mt : models){
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
		
		if(hasDiagram(model) && getDiagramFile(model) != null){
			Resource resD = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
			if(resD != null){resD.getContents().clear();}
		}
	}
	
	public void clearAll(ModelType[] models){
		for(ModelType mt : models){
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
		if(hasDiagram(model)){
			resD.getContents().add(createDiagramRootObject(getModelRootObject(model)));
		}
	}
	
	public void save(ModelType model){
		
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
		
		try{
			if(getModelFile(model) != null){
				Resource modelRes = ExplorerProjectPaths.getEmfResource(this, getModelFile(model));
				modelRes.save(null);
			}
			else{
				throw new IllegalStateException("save(): File not specified! ModelType: " + model.name());
			}
			
			if(hasDiagram(model) && getDiagramFile(model) != null){
				Resource diagramRes = ExplorerProjectPaths.getEmfResource(this, getDiagramFile(model));
				diagramRes.save(null);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void saveAll(ModelType[] models){
		for(ModelType mt : models){
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
			
			if(hasDiagram(model) && getDiagramFile(model) != null){
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
		
	public void deleteAll(ModelType[] models){
		for(ModelType mt : models){
			if(getModelFile(mt) != null){
				delete(mt);
			}
		}
	}
	
	public static EObject createModelRootObject(ModelType id){
		EObject model = null;
		
		switch(id){
			case REPOSITORY:
				model = RepositoryFactory.eINSTANCE.createRepository();
				break;
			case SYSTEM:
				model = SystemFactory.eINSTANCE.createSystem();
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
			case USAGE_EVOLUTION:
				model = UsageevolutionFactory.eINSTANCE.createUsageEvolution();
				break;
			default:
				String msg = "createModel(): Specified 'ModelType' not supported: " + id.name();
				logger.log(Level.SEVERE, msg);
				throw new UnsupportedOperationException(msg);
		}
		
		if(model instanceof NamedElement){
			NamedElement e = (NamedElement)model;
			e.setEntityName(model.eClass().getName() + " model");
		}
		
		return model;
	}
	
	public static Diagram createDiagramRootObject(EObject model){
		Diagram diagram = null;
		
		ModelType id = ModelType.getModelType(model.eResource().getURI().fileExtension());
		if(id == null){
			String msg = "createDiagramRootObject(): Model type can not be determined!";
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
				String msg = "createDiagramRootObject(): Specified 'ModelType' not supported: " + id.name();
				logger.log(Level.SEVERE, msg);
				throw new UnsupportedOperationException(msg);
		}
		
		diagram.setName(id.name());
		diagram.setElement(model);
		
		return diagram;
	}
	
	public static boolean hasDiagram(ModelType modelType){
		
		
		boolean hasDiagram = false;
		
		switch(modelType){
			case REPOSITORY:
				hasDiagram = true;
				break;
			case SYSTEM:
				hasDiagram = true;
				break;
			case RESOURCE:
				hasDiagram = true;
				break;
			case ALLOCATION:
				hasDiagram = true;
				break;
			case USAGE:
				hasDiagram = true;
				break;
			default:
				break;
		}
		
		return hasDiagram;
	}
}
