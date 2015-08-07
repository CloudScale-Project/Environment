package eu.cloudscaleproject.env.method.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagram;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class PinToSelectionHandler {
	
	@Execute
	public void execute(MPart part, @Named(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM) IValidationDiagram diagram){

		//keep current Workflow diagram by adding it into the local context
		IValidationDiagram pinedDiagram = (IValidationDiagram)part.getContext().getLocal(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM);
		if(pinedDiagram == null){
			part.getContext().set(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM, diagram);
		}
		else{
			part.getContext().remove(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM);
		}
		
	}
	
	@CanExecute
	public boolean canExecute(MPart part){
		
		IValidationDiagram pinedDiagram = (IValidationDiagram)part.getContext().get(CloudscaleContext.ACTIVE_VALIDATION_DIAGRAM);
		return pinedDiagram != null ? true : false;
	}

}
