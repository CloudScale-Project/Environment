 
package eu.cloudscaleproject.env.common;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import eu.cloudscaleproject.env.common.notification.StatusManager;

public class FragmentAddon {
	
	@PostConstruct
	void hookListeners() {
		
		//Tukaj ostal pred prazniki!
		//poglej zakaj se command executor ne inject v CommandFeature ce tukaj podas application context
		//zrihti commande - IProject se mora setati v aktiven context in commandexecutor dela na aktivnem branchu... 
		
		CloudscaleContext.registerGlobal(CommandExecutor.class);
		CloudscaleContext.registerGlobal(StatusManager.class);
		
		/*
		IEclipseContext context = application.getContext();
		IEclipseContext parent = context.getParent();
		
		//bug fix...
		context.set(TranslationService.LOCALE, "en");
		
		CloudscaleContext.setParentContext(parent);
		context.setParent(CloudscaleContext.getContext());
		
		CloudscaleContext.register(CommandExecutor.class);
		CloudscaleContext.register(StatusManager.class);
		
		CloudscaleContext.getContext().set("Vito", new Object(){
			@Override
			public String toString() {
				return "Jaz sem vito";
			}
		});
		*/
	}
	
	@PreDestroy
	void unhookListeners() {
		//CloudscaleContext.setParentContext(null);
	}
}