 
package eu.cloudscaleproject.env.toolchain.explorer.handlers;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import eu.cloudscaleproject.env.toolchain.explorer.IExplorerConstants;

public class LinkWithEditorhandler {
	
	@Execute
	public void execute(MPart part) {
		
		IEclipseContext context = part.getContext();
		
		Boolean enabled = (Boolean)context.get(IExplorerConstants.LINK_WITH_EDITOR);
		if(enabled != null && enabled){
			context.set(IExplorerConstants.LINK_WITH_EDITOR, Boolean.FALSE);
		}
		else{
			context.set(IExplorerConstants.LINK_WITH_EDITOR, Boolean.TRUE);
		}
	}
		
}