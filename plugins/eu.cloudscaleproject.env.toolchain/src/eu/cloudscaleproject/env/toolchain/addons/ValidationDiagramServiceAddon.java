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
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;

import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.services.IValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.IActiveResources;
import eu.cloudscaleproject.env.toolchain.resources.ProjectResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
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
						IInputAlternative ia = ra.getInputAlternative();
						
						if(ca != null){
							ValidationDiagramServiceAddon.validate(ca);
							diagramService.showStatus(project, ca);
							
						}
						else{
							diagramService.clearStatus(project, ra.getTool().getConfig().getID());
						}

						if(ia != null){
							diagramService.showStatus(project, ia);
						}
						else{
							diagramService.clearStatus(project, ra.getTool().getInput().getID());
						}
					}
					
					if(statusProvider instanceof IConfigAlternative){
						IConfigAlternative confAlternative = (IConfigAlternative)statusProvider;
						
						IProject project = statusProvider.getProject();
						
						IInputAlternative inputAlt = confAlternative.getInputAlternative();
						IEditorInputResource resultAlt = confAlternative.getLastResult();
						
						if(inputAlt != null){
							validate(inputAlt);
							diagramService.showStatus(project, inputAlt);
						}
						else{
							diagramService.clearStatus(project, confAlternative.getTool().getInput().getID());
						}
						
						if(resultAlt != null){
							validate(resultAlt);
							diagramService.showStatus(project, resultAlt);
						}
						else{
							diagramService.clearStatus(project, confAlternative.getTool().getResult().getID());
						}
						
					}
					
					if(statusProvider instanceof IInputAlternative){
						IProject project = statusProvider.getProject();
						IInputAlternative inputAlternative = (IInputAlternative)statusProvider;
						
						IValidationStatusProvider activeConfigAlternative = diagramService.getActiveStatusProvider(
																					inputAlternative.getTool().getConfig().getID());

						IValidationStatusProvider activeResultAlternative = diagramService.getActiveStatusProvider(
																					inputAlternative.getTool().getResult().getID());

						List<IConfigAlternative> configAlternatives = inputAlternative.getConfigAlternatives();
						List<IResultAlternative> resultAlternatives = inputAlternative.getResultAlternatives();
						
						//handle configuration alternative status
						if(!configAlternatives.isEmpty()){
							
							//check if the new configuration alternative should be set
							boolean hasValidConfigAlternative = false;
							if(activeConfigAlternative instanceof IConfigAlternative){
								IConfigAlternative ca = (IConfigAlternative)activeConfigAlternative;
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
							diagramService.clearStatus(project, inputAlternative.getTool().getConfig().getID());
						}

						//handle result alternative status
						if(!resultAlternatives.isEmpty()){
							
							//check if the new configuration alternative should be set
							boolean hasValidResultAlternative = false;
							if(activeResultAlternative instanceof IResultAlternative){
								IResultAlternative ra = (IResultAlternative)activeResultAlternative;
								if(inputAlternative.equals(ra.getInputAlternative())){
									hasValidResultAlternative = true;
								}
							}
							
							//show the new configuration alternative only if needed
							if(!hasValidResultAlternative){
								IValidationStatusProvider sp = resultAlternatives.get(0);
								ValidationDiagramServiceAddon.validate(sp);
								diagramService.showStatus(project, sp);
							}
						}
						else{
							diagramService.clearStatus(project, inputAlternative.getTool().getResult().getID());
						}
					}
				}
				
				if(IValidationDiagramService.PROP_CREATE_DIAGRAM.equals(evt.getPropertyName())){
					
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

	@Inject
	@Optional
	public void postConstruct(IEclipseContext context, IValidationDiagramService diagramService) {
		
		// On initialization create diagrams and bind initial statuses... 
		
		for(ProjectResourceRegistry prr : ResourceRegistry.getInstance().getProjectResourceRegistries()){
			IProject project = prr.getProject();
			diagramService.createDiagram(project);

			//bind all global status providers
			for(IValidationStatusProvider sp : StatusManager.getInstance().getStatusProviders(null)){
				validate(sp);
				diagramService.showStatus(project, sp);
			}
			
			HashSet<String> alreadyShownSet = new HashSet<String>(); 
			for(IValidationStatusProvider sp :StatusManager.getInstance().getStatusProviders(project)){
				if(!alreadyShownSet.contains(sp.getID())){
					diagramService.showStatus(project, sp);
					alreadyShownSet.add(sp.getID());
					
					validate(sp);
				}
			}
		}

		diagramService.addPropertyChangeListener(diagramServiceListener);
	}
	
	@Inject
	@Optional
	public void updateStatus(IValidationDiagramService diagramService, IActiveResources activeResources){
		
		if(diagramService == null){
			return;
		}
		
		IValidationStatusProvider statusProvider = activeResources.getActiveStatusProvider();
		
		if(statusProvider != null){
			validate(statusProvider);
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
