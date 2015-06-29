package eu.cloudscaleproject.env.csm2pcm;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;

import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.allocation.AllocationFactory;
import org.palladiosimulator.pcm.core.entity.Entity;
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
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory;
import org.palladiosimulator.pcm.system.System;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsagemodelFactory;

public class PalladioUtil {
	
	public static enum ModelID{
		 USAGE,
		 RESOURCE,
		 ALLOCATION, 
		 REPOSITORY, 
		 SYSTEM, 
	}
	
	public static final String DEFAULT_MODEL_ID = "CSMGen_";
	private static final Logger logger = Logger.getLogger(PalladioUtil.class.getSimpleName());

	public static String getName(ModelID id){
		String name = "";
		
		switch(id){
			case REPOSITORY:
				name = "Repository";
				break;
			case SYSTEM:
				name = "System";
				break;
			case RESOURCE:
				name = "Resource";
				break;
			case ALLOCATION:
				name = "Allocation";
				break;
			case USAGE:
				name = "Usage";
				break;
			default:
				logger.log(Level.SEVERE, "createModel(): invalid model ID");
				break;
		}
	
		return name;
	}
	
	public static URI createDiagramURI(ModelID id, URI folder){
		URI diagramUri = null;
		
		switch(id){
			case REPOSITORY:
				diagramUri = folder.appendSegment("pcm.repository_diagram");
				break;
			case SYSTEM:
				diagramUri = folder.appendSegment("pcm.system_diagram");
				break;
			case RESOURCE:
				diagramUri = folder.appendSegment("pcm.resourceenvironment_diagram");
				break;
			case ALLOCATION:
				diagramUri = folder.appendSegment("pcm.allocation_diagram");
				break;
			case USAGE:
				diagramUri = folder.appendSegment("pcm.usagemodel_diagram");
				break;
			default:
				logger.log(Level.SEVERE, "createDiagramURI(): invalid model ID");
				break;
		}
		return diagramUri;
	}
	
	public static Diagram createDiagram(ModelID id, EObject model){
		Diagram diagram = null;
		
		if(model == null){
			return null;
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
				logger.log(Level.SEVERE, "createDiagram(): invalid model ID");
				break;
		}
		
		if (diagram != null)
		{
			diagram.setName(getName(id));
			diagram.setElement(model);
		}
		
		return diagram;
	}
	
	public static URI createModelURI(ModelID id, URI folder){
		URI modelUri = null;
		
		switch(id){
			case REPOSITORY:
				modelUri = folder.appendSegment("pcm.repository");
				break;
			case SYSTEM:
				modelUri = folder.appendSegment("pcm.system");
				break;
			case RESOURCE:
				modelUri = folder.appendSegment("pcm.resourceenvironment");
				break;
			case ALLOCATION:
				modelUri = folder.appendSegment("pcm.allocation");
				break;
			case USAGE:
				modelUri = folder.appendSegment("pcm.usagemodel");
				break;
			default:
				logger.log(Level.SEVERE, "createModelURI(): invalid model ID");
				break;
		}
		return modelUri;
	}
	
	public static EObject createModel(ModelID id){
		EObject model = null;
		
		switch(id){
			case REPOSITORY:
				model = RepositoryFactory.eINSTANCE.createRepository();
				break;
			case SYSTEM:
				model = org.palladiosimulator.pcm.system.SystemFactory.eINSTANCE.createSystem();
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
		
		if(model instanceof Entity){
			Entity e = (Entity)model;
			e.setId(DEFAULT_MODEL_ID);
		}
		
		return model;
	}

	public static ModelID getModelId(EObject obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Repository)
			return ModelID.REPOSITORY;
		else if (obj instanceof System)
			return ModelID.SYSTEM;
		else if (obj instanceof ResourceEnvironment)
			return ModelID.RESOURCE;
		else if (obj instanceof Allocation)
			return ModelID.ALLOCATION;
		else if (obj instanceof UsageModel)
			return ModelID.USAGE;
		
		logger.log(Level.SEVERE, "getModelId(): Unknown EObject");
		return null;
	}
	
}
