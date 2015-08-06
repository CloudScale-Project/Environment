package eu.cloudscaleproject.env.common.notification.diagram;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.Extensions;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public class ValidationDiagramService {
	
	private static HashMap<IProject, IValidationDiagram> diagrams = new HashMap<IProject, IValidationDiagram>();
	
	public static void showDiagram(IProject project){

		if(project == null){
			CloudscaleContext.registerGlobal(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM, null);
			return;
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
			
			CloudscaleContext.registerGlobal(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM, diagram);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void showStatus(IProject project, String id, IValidationStatusProvider statusProvider){
				
		showDiagram(project);
		
		IValidationDiagram diagramProvider = diagrams.get(project);
		if(diagramProvider != null){
			diagramProvider.bindStatusProvider(id, statusProvider);
		}
		
	}
	
	public static void showStatus(IProject project, IValidationStatusProvider statusProvider){
		
		showDiagram(project);
		
		IValidationDiagram diagramProvider = diagrams.get(project);
		if(diagramProvider != null && statusProvider != null){
			diagramProvider.bindStatusProvider(statusProvider.getID(), statusProvider);
		}
		
	}
}
