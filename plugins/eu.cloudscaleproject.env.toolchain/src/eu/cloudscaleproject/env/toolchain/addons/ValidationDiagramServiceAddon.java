package eu.cloudscaleproject.env.toolchain.addons;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.logging.Logger;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.osgi.service.event.Event;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.diagram.IValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.IActiveResources;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IInputAlternative;

public class ValidationDiagramServiceAddon {
	
	private static final Logger logger = Logger.getLogger(ValidationDiagramServiceAddon.class.getName());
	
	private static final PropertyChangeListener diagramServiceListener = new PropertyChangeListener() {
		
		private boolean executingPropertyChange = false;
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			//dead lock check
			if(executingPropertyChange){
				return;
			}
			executingPropertyChange = true;
			
			try{
				if(IValidationDiagramService.PROP_SHOW_STATUS.equals(evt.getPropertyName())){
					
					IValidationDiagramService diagramService = (IValidationDiagramService)evt.getSource();
					IValidationStatusProvider statusProvider = (IValidationStatusProvider)evt.getNewValue();
					
					if(statusProvider instanceof IConfigAlternative){
						IConfigAlternative confAlternative = (IConfigAlternative)statusProvider;
						
						IProject project = statusProvider.getProject();
						IInputAlternative inputAlt = confAlternative.getInputAlternative();
						IEditorInputResource resultAlt = confAlternative.getLastResult();
	
						if(inputAlt != null && !inputAlt.isLoaded()){
							inputAlt.load();
							inputAlt.validate();
						}
						
						if(resultAlt != null && !resultAlt.isLoaded()){
							resultAlt.load();
							resultAlt.validate();
						}
						
						if(inputAlt != null){
							diagramService.showStatus(project, inputAlt);
						}
						else{
							diagramService.clearStatus(project, confAlternative.getInputAlternativeID());
						}
						
						if(resultAlt != null){
							diagramService.showStatus(project, resultAlt);
						}
						else{
							diagramService.clearStatus(project, confAlternative.getResultAlternativeID());
						}
						
					}
					
					if(statusProvider instanceof IInputAlternative){
						IProject project = statusProvider.getProject();
						IInputAlternative inputAlternative = (IInputAlternative)statusProvider;
						
						IConfigAlternative configAlternative = inputAlternative.getConfigAlternative();
						
						if(configAlternative != null){
							diagramService.showStatus(project, configAlternative);
						}
						else{
							diagramService.clearStatus(project, inputAlternative.getConfigAlternativeID());
						}
					}
				}
				
				if(IValidationDiagramService.PROP_CLEAR_STATUS.equals(evt.getPropertyName())){
					
					IValidationDiagramService diagramService = (IValidationDiagramService)evt.getSource();
					String statusProviderID = (String)evt.getNewValue();
					
					/*
					if(CSTool.ANALYSER_INPUT.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSTool.ANALYSER_CONF.getID());
					}
					if(CSTool.EXTRACTOR_INPUT.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSTool.EXTRACTOR_CONF.getID());
					}
					if(CSTool.SPOTTER_STA_INPUT.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSTool.SPOTTER_STA_CONF.getID());
					}
					if(CSTool.SPOTTER_DYN_INPUT.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSTool.SPOTTER_DYN_CONF.getID());
					}
					*/
					
					if(CSTool.ANALYSER_CONF.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSTool.ANALYSER_RES.getID());
					}
					if(CSTool.EXTRACTOR_CONF.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSTool.EXTRACTOR_RES.getID());
					}
					if(CSTool.SPOTTER_STA_CONF.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSTool.SPOTTER_STA_RES.getID());
					}
					if(CSTool.SPOTTER_DYN_CONF.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSTool.SPOTTER_DYN_RES.getID());
					}
				}
				
				if(IValidationDiagramService.PROP_INIT_DIAGRAM.equals(evt.getPropertyName())){
					
					//make initial statuses visible
					IValidationDiagramService diagramService = (IValidationDiagramService)evt.getSource();
					IProject project = (IProject)evt.getNewValue();
					
					
					//bind all global status providers
					for(IValidationStatusProvider sp : StatusManager.getInstance().getStatusProviders(null)){
						diagramService.showStatus(project, sp);
					}
					
					HashSet<String> alreadyShownSet = new HashSet<String>(); 
					for(IValidationStatusProvider sp :StatusManager.getInstance().getStatusProviders(project)){
						if(!alreadyShownSet.contains(sp.getID())){
							diagramService.showStatus(project, sp);
							alreadyShownSet.add(sp.getID());
						}
					}
				}
			}
			finally{
				executingPropertyChange = false;
			}
		}
	};

	@Inject
	@Optional
	public void applicationStarted(@EventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event) {
		
	}
	
	@Inject
	@Optional
	public void postConstruct(IEclipseContext context, IValidationDiagramService diagramService) {
		
		diagramService.addPropertyChangeListener(diagramServiceListener);
	}
	
	@Inject
	public void updateStatus(@Optional IValidationDiagramService diagramService,
							 @Active @Optional IActiveResources activeResources){
		
		if(diagramService == null){
			logger.warning("IValidationDiagramService can not be found! Status diagram will not be updated!");
			return;
		}
		
		IValidationStatusProvider statusProvider = activeResources.getActiveStatusProvider();
		
		if(statusProvider != null){
			diagramService.showStatus(statusProvider.getProject(), statusProvider);
		}
		
		IProject project = activeResources.getActiveProject();
		diagramService.showDiagram(project);
	}
	
	@PreDestroy
	public void preDestroy(IEclipseContext context, IValidationDiagramService diagramService) {
		
		diagramService.removePropertyChangeListener(diagramServiceListener);
	}
	
}
