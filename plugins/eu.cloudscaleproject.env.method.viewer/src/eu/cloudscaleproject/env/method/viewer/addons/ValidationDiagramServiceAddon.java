package eu.cloudscaleproject.env.method.viewer.addons;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.osgi.service.event.Event;

import eu.cloudscaleproject.env.common.services.IValidationDiagramService;
import eu.cloudscaleproject.env.method.viewer.ValidationDiagramService;

public class ValidationDiagramServiceAddon {

	@Inject
	@Optional
	public void applicationStarted(@EventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event) {
		
	}
	
	@PostConstruct
	void postConstruct(IEclipseContext context) {
		
		//inject diagram service into the context		
		ValidationDiagramService diagramService = ContextInjectionFactory.make(ValidationDiagramService.class, context);
		context.set(IValidationDiagramService.class, diagramService);
		context.set(ValidationDiagramService.class, diagramService);
	}
	
	@PreDestroy
	void preDestroy(IEclipseContext context) {
		
		//remove diagram service from the context
		context.remove(IValidationDiagramService.class);
		context.remove(ValidationDiagramService.class);
	}

}
