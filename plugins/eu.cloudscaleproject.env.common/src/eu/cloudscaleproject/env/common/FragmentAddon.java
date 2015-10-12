 
package eu.cloudscaleproject.env.common;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.common.notification.StatusManager;

public class FragmentAddon {
	
	@PostConstruct
	void hookListeners(IEclipseContext context) {
		
		CloudscaleContext.registerGlobal(CommandExecutor.class);
		CloudscaleContext.registerGlobal(StatusManager.class);
		
	}
	
	@PreDestroy
	void unhookListeners() {
	}
}