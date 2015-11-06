package eu.cloudscaleproject.env.toolchain.addons;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.GroupUpdates;
import org.eclipse.e4.core.di.annotations.Optional;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.ActiveResources;
import eu.cloudscaleproject.env.toolchain.IActiveResources;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

/**
 * 
 * Transfer objects from the active context to the CloudScale global context
 * 
 * @author vito
 *
 */
public class ToolchainAddon {
	
	private static final Logger logger = Logger.getLogger(ToolchainAddon.class.getName());
	
	@PostConstruct
	public void postConstruct(IEclipseContext context){
		//put dummy object at initialization
		
		context.declareModifiable(IActiveResources.class);
		context.modify(IActiveResources.class, new ActiveResources());
	}
	
	@Inject @GroupUpdates
	public void updateStatus(IEclipseContext context,
							 @Active @Optional @Named(CloudscaleContext.ACTIVE_ALTERNATIVE) IValidationStatusProvider alternative,
							 @Active @Optional IValidationStatusProvider statusProvider,
							 @Active @Optional IEditorInputResource editorInputResource,
							 @Active @Optional IProject project){
		
		
		IActiveResources currentActiveResources = CloudscaleContext.getGlobalContext().get(IActiveResources.class);
		ActiveResources activeResources = new ActiveResources();
		
		//handle validation status provider
		if(statusProvider == null){
			if(alternative instanceof IValidationStatusProvider){
				statusProvider = (IValidationStatusProvider)alternative;
			}
			if(editorInputResource != null){
				statusProvider = editorInputResource;
			}
		}
		activeResources.setStatusProvideer(statusProvider);
	
		//handle editor input resource
		if(editorInputResource == null){
			if(alternative instanceof IEditorInputResource){
				editorInputResource = (IEditorInputResource)alternative;
			}
			if(statusProvider instanceof IEditorInputResource){
				editorInputResource = (IEditorInputResource)statusProvider;
			}
		}
		activeResources.setAlternative(editorInputResource);

		//handle project		
		if(project != null){
			if(project.isAccessible()){
				activeResources.setProject(project);
			}
		}
		else if(currentActiveResources != null){
			
			IProject currentProject = currentActiveResources.getActiveProject();
			if(currentProject != null && currentProject.isAccessible()){
				activeResources.setProject(currentProject);
			}
		}
		
		if(!activeResources.equals(currentActiveResources)){
			logger.info("Updating active resources!");
			context.modify(IActiveResources.class, activeResources);
		}
	}

}
