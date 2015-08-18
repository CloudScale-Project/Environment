package eu.cloudscaleproject.env.common.notification.diagram;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.Extensions;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public class ValidationDiagramService {
	
	private static HashMap<IProject, IValidationDiagram> diagrams = new HashMap<IProject, IValidationDiagram>();
	
	public static IValidationDiagram getDiagram(IProject project){
		if(project == null){
			CloudscaleContext.registerGlobal(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM, null);
			return null;
		}
		
		try{
			IValidationDiagram diagram = diagrams.get(project);
			if(diagram == null){
				
				List<IValidationDiagramFactory> factories = Extensions.getInstance().getValiadtionDiagramFactories();
				
				if(!factories.isEmpty()){
					diagram = factories.get(0).createDiagram(null);
					diagram.setProject(project);
					diagrams.put(project, diagram);
				}
			}

			return diagram;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void showDiagram(IProject project){

		IValidationDiagram diagram = getDiagram(project);
		if(diagram != null){
			CloudscaleContext.registerGlobal(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM, diagram);
		}
	}
	
	public static void showStatus(IProject project, String id, IValidationStatusProvider statusProvider){
						
		IValidationDiagram diagramProvider = getDiagram(project);
		if(diagramProvider != null){
			diagramProvider.bindStatusProvider(id, statusProvider);
		}
		
	}
	
	public static void showStatusIfEmpty(IProject project, IValidationStatusProvider statusProvider){
		
		IValidationDiagram diagramProvider = getDiagram(project);
		if(diagramProvider != null && statusProvider != null){
			if(diagramProvider.getActiveStatusProvider(statusProvider.getID()) == null){
				diagramProvider.bindStatusProvider(statusProvider.getID(), statusProvider);
			}
		}
		
	}
	
	public static void showStatus(IProject project, IValidationStatusProvider statusProvider){
				
		IValidationDiagram diagramProvider = getDiagram(project);
		if(diagramProvider != null && statusProvider != null){
			diagramProvider.bindStatusProvider(statusProvider.getID(), statusProvider);
		}
		
	}
}
