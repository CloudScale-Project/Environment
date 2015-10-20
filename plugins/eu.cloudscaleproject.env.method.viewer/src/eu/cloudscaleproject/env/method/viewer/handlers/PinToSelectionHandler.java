package eu.cloudscaleproject.env.method.viewer.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import eu.cloudscaleproject.env.common.services.IValidationDiagramService;
import eu.cloudscaleproject.env.method.viewer.diagram.DiagramViewPart;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class PinToSelectionHandler {
	
	@Execute
	public void execute(MPart part, IValidationDiagramService diagramService){

		Boolean pin = (Boolean)part.getContext().getLocal(DiagramViewPart.PIN_DIAGRAM);
		if(pin != null && pin){
			part.getContext().set(DiagramViewPart.PIN_DIAGRAM, Boolean.FALSE);
		}
		else{
			part.getContext().set(DiagramViewPart.PIN_DIAGRAM, Boolean.TRUE);
		}
	}

}
