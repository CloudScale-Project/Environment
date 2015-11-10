package eu.cloudscaleproject.env.analyser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
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
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ModelUtils {
	
	private static final Logger logger = Logger.getLogger(ModelUtils.class.getName());
	
	public static void executeCreateModels(final EditorInputEMF alternative, final ModelType[] types){
		
		WorkspaceJob job = new WorkspaceJob("Creating new models") {
			
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				
				monitor.beginTask("Creating new models", 2 * types.length + 1);
				
				ResourceSet modelResSet = new ResourceSetImpl();
				ResourceSet diagramResSet = new ResourceSetImpl();
				
				ModelUtils.createModels(modelResSet, diagramResSet, alternative.getResource(), types, monitor);
				for(Resource res : modelResSet.getResources()){
					alternative.addSubResource(ExplorerProjectPaths.getFileFromEmfResource(res));
				}
				
				monitor.subTask("Saving alternative");
				alternative.save();
				monitor.worked(1);

				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Creating new models done.");
			}
		};
		
		job.setUser(true);
		job.schedule();
	}
	
	public static void executeImportModels(final EditorInputEMF alternative, final Resource[] resources, final boolean copy){
		
		WorkspaceJob job = new WorkspaceJob("Copying models into alternative") {
			
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				
				monitor.beginTask("Importing models into alternative", 6 * resources.length);
				
				if (copy)
				{
					ExplorerProjectPaths.copyEMFResources(alternative.getResource(), resources, monitor);
				}

				for (Resource resource : resources)
				{
					IFile f = ExplorerProjectPaths.getFileFromEmfResource(resource);
					alternative.addSubResource(f);
				}
				
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Copying models done.");
			}
		};
		
		job.setUser(true);
		job.schedule();
	}
	
	public static void createModels(EditorInputEMF alternative, IProgressMonitor monitor, ModelType... types){
		
		ResourceSet modelResSet = new ResourceSetImpl();
		ResourceSet diagramResSet = new ResourceSetImpl();
		
		createModels(modelResSet, diagramResSet, alternative.getResource(), types, monitor);
		
		for(Resource res : modelResSet.getResources()){
			alternative.addSubResource(ExplorerProjectPaths.getFileFromEmfResource(res));
		}
		alternative.save(monitor);
	}
	
	public static void createModels(ResourceSet modelResSet, ResourceSet diagramResSet,
									   IFolder folder, ModelType[] types, IProgressMonitor monitor){
		
		for(ModelType modelType : types){
			
			//create model
			IFile modelFile = ExplorerProjectPaths.getNonexistingSubFile(folder, "model", modelType.getFileExtension());
			EObject modelRoot = createModelRootObject(modelType);
			
			Resource res = ExplorerProjectPaths.getEmfResource(modelResSet, modelFile);
			res.getContents().clear();
			res.getContents().add(modelRoot);
			
			//create diagram
			IFile diagramFile = ExplorerProjectPaths.getNonexistingSubFile(folder, "model", modelType.getDiagramFileExtension());
			if(diagramResSet != null && hasDiagram(modelType)){
				Resource resD = ExplorerProjectPaths.getEmfResource(diagramResSet, diagramFile);
				resD.getContents().clear();
				resD.getContents().add(createDiagramRootObject(modelRoot));
			}
		}
		
		//save models
		for(Resource res : modelResSet.getResources()){
			try {
				if(monitor != null){
					monitor.subTask("Saving model resource '" + res.getURI().lastSegment() + "'");
				}
				res.save(null);
				if(monitor != null){
					monitor.worked(1);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//save diagrams
		if(diagramResSet != null && diagramResSet != modelResSet){
			for(Resource res : diagramResSet.getResources()){
				try {
					if(monitor != null){
						monitor.subTask("Saving diagram resource '" + res.getURI().lastSegment() + "'");
					}
					res.save(null);
					if(monitor != null){
						monitor.worked(1);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
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
