package eu.cloudscaleproject.env.toolchain.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import eu.cloudscaleproject.env.toolchain.ui.validation.StatusViewPart;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class PinStatusHandler {

	@Execute
	public void execute(MPart part){

		Boolean isPined = (Boolean)part.getContext().get(StatusViewPart.PIN_STATUS);
		
		if(isPined != null && isPined){
			part.getContext().set(StatusViewPart.PIN_STATUS, Boolean.FALSE);
		}
		else{
			part.getContext().set(StatusViewPart.PIN_STATUS, Boolean.TRUE);
		}
		
	}
	
}
