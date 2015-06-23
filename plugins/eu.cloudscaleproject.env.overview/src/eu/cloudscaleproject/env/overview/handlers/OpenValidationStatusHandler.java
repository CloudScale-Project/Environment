package eu.cloudscaleproject.env.overview.handlers;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Execute;

import eu.cloudscaleproject.env.common.notification.IValidationStatus;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.util.OpenAlternativeUtil;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class OpenValidationStatusHandler {
	
	@Execute
	public void execute(@Active IValidationStatus status){
		
		IValidationStatusProvider statusProvider = status.getProvider();
		
		if(statusProvider instanceof IEditorInputResource){
			IEditorInputResource eir = (IEditorInputResource)statusProvider;
			OpenAlternativeUtil.openAlternative(eir);
		}
	}

}
