package eu.cloudscaleproject.env.common.notification.diagram;

import org.eclipse.emf.ecore.resource.Resource;

public interface IValidationDiagramFactory {
	
	public IValidationDiagram createDiagram(Resource diagramResource);

}
