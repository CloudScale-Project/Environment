package eu.cloudscaleproject.env.toolchain.addons;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.services.IValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSToolResource;
import eu.cloudscaleproject.env.toolchain.IActiveResources;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;
import eu.cloudscaleproject.env.toolchain.resources.types.IConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IInputAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.IResultAlternative;

public class ValidationDiagramServiceAddon {
	
	@SuppressWarnings("unused")
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
					
					if(statusProvider instanceof IResultAlternative){
						IProject project = statusProvider.getProject();
						
						IResultAlternative ra = (IResultAlternative)statusProvider;
						IConfigAlternative ca = ra.getConfigAlternative();
						
						if(ca != null){
							ValidationDiagramServiceAddon.validate(ca);
							diagramService.showStatus(project, ca);
						}
						else{
							//TODO: clear status
							//diagramService.clearStatus(project, ra.getInputAlternativeID());
						}
					}
					
					if(statusProvider instanceof IConfigAlternative){
						IConfigAlternative confAlternative = (IConfigAlternative)statusProvider;
						
						IProject project = statusProvider.getProject();
						IInputAlternative inputAlt = confAlternative.getInputAlternative();
						IEditorInputResource resultAlt = confAlternative.getLastResult();
	
						if(inputAlt != null && !inputAlt.isLoaded()){
							inputAlt.load();
						}
						
						if(resultAlt != null && !resultAlt.isLoaded()){
							resultAlt.load();
						}
						
						if(inputAlt != null){
							diagramService.showStatus(project, inputAlt);
						}
						else{
							diagramService.clearStatus(project, confAlternative.getTool().getInput().getID());
						}
						
						if(resultAlt != null){
							diagramService.showStatus(project, resultAlt);
						}
						else{
							diagramService.clearStatus(project, confAlternative.getTool().getResult().getID());
						}
						
					}
					
					if(statusProvider instanceof IInputAlternative){
						IProject project = statusProvider.getProject();
						IInputAlternative inputAlternative = (IInputAlternative)statusProvider;
						
						IValidationStatusProvider activeAlternative = diagramService.getActiveStatusProvider(
																					inputAlternative.getTool().getConfig().getID());
						List<IConfigAlternative> configAlternatives = inputAlternative.getConfigAlternatives();
						
						if(!configAlternatives.isEmpty()){
							
							//check if the new configuration alternative should be set
							boolean hasValidConfigAlternative = false;
							if(activeAlternative instanceof IConfigAlternative){
								IConfigAlternative ca = (IConfigAlternative)activeAlternative;
								if(inputAlternative.equals(ca.getInputAlternative())){
									hasValidConfigAlternative = true;
								}
							}
							
							//show the new configuration alternative only if needed
							if(!hasValidConfigAlternative){
								IValidationStatusProvider sp = configAlternatives.get(0);
								ValidationDiagramServiceAddon.validate(sp);
								diagramService.showStatus(project, sp);
							}
						}
						else{
							diagramService.clearStatus(project, inputAlternative.getTool().getId());
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
					
					if(CSToolResource.ANALYSER_CONF.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSToolResource.ANALYSER_RES.getID());
					}
					if(CSToolResource.EXTRACTOR_CONF.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSToolResource.EXTRACTOR_RES.getID());
					}
					if(CSToolResource.SPOTTER_STA_CONF.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSToolResource.SPOTTER_STA_RES.getID());
					}
					if(CSToolResource.SPOTTER_DYN_CONF.getID().equals(statusProviderID)){
						diagramService.clearStatus(diagramService.getActiveProject(), CSToolResource.SPOTTER_DYN_RES.getID());
					}
				}
				
				if(IValidationDiagramService.PROP_INIT_DIAGRAM.equals(evt.getPropertyName())){
					
					//make initial statuses visible
					IValidationDiagramService diagramService = (IValidationDiagramService)evt.getSource();
					IProject project = (IProject)evt.getNewValue();
										
					//bind all global status providers
					for(IValidationStatusProvider sp : StatusManager.getInstance().getStatusProviders(null)){
						ValidationDiagramServiceAddon.validate(sp);
						diagramService.showStatus(project, sp);
					}
					
					HashSet<String> alreadyShownSet = new HashSet<String>(); 
					for(IValidationStatusProvider sp :StatusManager.getInstance().getStatusProviders(project)){
						if(!alreadyShownSet.contains(sp.getID())){
							diagramService.showStatus(project, sp);
							alreadyShownSet.add(sp.getID());
							
							ValidationDiagramServiceAddon.validate(sp);
						}
					}
				}
			}
			finally{
				executingPropertyChange = false;
			}
		}
	};
	
	private static void validate(IValidationStatusProvider sp){
		
		if(sp instanceof IEditorInputResource){
			final IEditorInputResource eir = (IEditorInputResource)sp;
			
			if(!eir.isLoaded()){
				EditorInputJob loadJob = new EditorInputJob("Loading '"+ eir.getName() +"'") {
					
					@Override
					public IStatus execute(IProgressMonitor monitor) {
						monitor.beginTask("Loading '"+ eir.getName() +"'", IProgressMonitor.UNKNOWN);
						if(!eir.isLoaded()){
							eir.load(monitor);
						}
						monitor.done();
						
						return Status.OK_STATUS;
					}
				};
				loadJob.setUser(false);
				loadJob.schedule();
			}
			
		}
		
	}
	
	private IActiveResources lastActiveResources = null;
	
	@Inject
	@Optional
	public void postConstruct(IEclipseContext context, IValidationDiagramService diagramService) {
		
		diagramService.addPropertyChangeListener(diagramServiceListener);
	}
	
	@Inject
	public void updateStatus(@Optional IValidationDiagramService diagramService,
							 @Active @Optional IActiveResources activeResources){
		
		if(diagramService == null){
			return;
		}
		
		// TODO: find better solution
		// This is needed in case, when the status provider is removed and the active context is changed.
		// IActiveResources in that case contains deleted and already removed (from the diagram) status provider.
		if(lastActiveResources == activeResources){
			return;
		}

		IValidationStatusProvider statusProvider = activeResources.getActiveStatusProvider();
		
		if(statusProvider != null){
			ValidationDiagramServiceAddon.validate(statusProvider);
			diagramService.showStatus(statusProvider.getProject(), statusProvider);
		}
		
		IProject project = activeResources.getActiveProject();
		diagramService.showDiagram(project);
		
		lastActiveResources = activeResources;
	}
	
	@PreDestroy
	public void preDestroy(IEclipseContext context, IValidationDiagramService diagramService) {
		
		diagramService.removePropertyChangeListener(diagramServiceListener);
	}
	
}
