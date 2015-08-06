package eu.cloudscaleproject.env.method.viewer;

import org.eclipse.emf.ecore.resource.Resource;

import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagram;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagramFactory;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class ValidationDiagramFactory implements IValidationDiagramFactory{

	@Override
	public IValidationDiagram createDiagram(Resource diagramResource) {
		return new ValidationDiagram(diagramResource);
	}

}
