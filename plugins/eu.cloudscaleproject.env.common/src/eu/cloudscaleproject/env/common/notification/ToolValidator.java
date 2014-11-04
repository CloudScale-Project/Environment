package eu.cloudscaleproject.env.common.notification;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.di.annotations.Optional;

import eu.cloudscaleproject.env.common.DIExtension;

public abstract class ToolValidator extends DIExtension{
	
	@Optional @Inject
	protected StatusManager statusManager;
	
	private static final Logger logger = Logger.getLogger(ToolValidator.class.getName());
	private static final String UNKNOWN_ERROR = "eu.cloudscaleproject.env.common.notification.ToolValidator.error";
	
	public abstract String getToolID();
	public abstract IResource[] getDependantResources(IProject project);
	
	public final boolean validate(IProject project){
		
		if(statusManager == null){
			logger.severe("Validation manager was not found! Skipping validation!");
			return false;
		}
		
		IToolStatus status = statusManager.getStatus(project, getToolID());
		if(status == null){
			logger.severe("Tool status with id '" + getToolID() + "' was not found! Skipping validation!");
			return false;
		}
		
		if(!status.hasMetRequirements()){
			status.setIsInProgress(false);
			status.setIsDone(false);
			return false;
		}
		
		try{
			return doValidate(project, status);
		}
		catch(Exception e){
			status.setIsDone(false);
			status.addWarning(UNKNOWN_ERROR, e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}
	
	protected abstract boolean doValidate(IProject project, IToolStatus status);

}
