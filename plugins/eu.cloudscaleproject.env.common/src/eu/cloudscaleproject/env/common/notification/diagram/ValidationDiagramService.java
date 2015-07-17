package eu.cloudscaleproject.env.common.notification.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IProject;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;

public class ValidationDiagramService {
	
	private static IValidationDiagramFactory diagramFactory = null;
	private static HashMap<IProject, IValidationDiagram> diagrams = new HashMap<IProject, IValidationDiagram>();
	
	private static List<IProject> deferredRequests = new ArrayList<IProject>();

	public static void showDiagram(IProject project){
		
		assert(project != null);
		
		try{
			IValidationDiagram diagram = diagrams.get(project);
			if(diagram == null){
				if(diagramFactory != null){
					diagram = diagramFactory.createDiagram(null);
					diagram.setProject(project);
					diagrams.put(project, diagram);
				}
				else{
					deferredRequests.add(project);
				}
			}
			
			if(diagram != null){
				diagram.show();
			}
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
	
	public static void registerDiagramFactory(IValidationDiagramFactory factory){
		diagramFactory = factory;
		
		for(IProject project : deferredRequests){
			showDiagram(project);
		}
		deferredRequests.clear();
	}
}
