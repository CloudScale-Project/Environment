 
package eu.cloudscaleproject.env.common;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.core.services.translation.TranslationService;
import org.eclipse.e4.ui.model.application.MApplication;

import eu.cloudscaleproject.env.common.notification.StatusManager;

public class FragmentAddon {
	
	@Inject
	IEventBroker eventBroker;
	
	@Inject
	MApplication application;
	
	@Inject
	ECommandService commandService;
	
	@Inject
	EHandlerService handlerService;
	
	@PostConstruct
	void hookListeners() {
		IEclipseContext context = application.getContext();
		context.set(TranslationService.LOCALE, "en");
		CloudscaleContext.setParentContext(context);
		CloudscaleContext.register(CommandExecutor.class);
		CloudscaleContext.register(StatusManager.class);
	}
	
	@PreDestroy
	void unhookListeners() {
		CloudscaleContext.setParentContext(null);
	}
}