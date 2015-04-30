 
package eu.cloudscaleproject.env.csm2pcm.handlers;

import java.io.IOException;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.ecore.resource.Resource;
import org.scaledl.overview.converter.ConverterService;

import eu.cloudscaleproject.env.common.dialogs.DialogUtils;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class RunTransformationHandler {
	
	private static final Logger logger = Logger.getLogger(RunTransformationHandler.class.getName());
		
	@Execute
	public void execute() {
		
		IProject project = ExplorerProjectPaths.getProjectFromActiveEditor();
		if(project == null){
			logger.warning("Can't find active project! Exiting command..." );
			return;
		}
		
		IValidationStatusProvider statusProvider = ResourceRegistry.getInstance().getProjectUniqueResource(project, ToolchainUtils.OVERVIEW_ID);
		statusProvider.validate();
		if(!statusProvider.getSelfStatus().isDone()){
			DialogUtils.openError("Overview transformation", "Unable to execute transformation. Overview model is not valid!");
			return;
		}
		
		Resource res = ExplorerProjectPaths.getProjectEmfResource(project, ExplorerProjectPaths.KEY_FILE_OVERVIEW_MODEL);
		
		if(res == null){
			logger.warning("Overview model is not present in the current project! Exiting command...");
			return;
		}
		
		try {
			res.load(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(res != null && !res.getContents().isEmpty()){
			new ConverterJob(res).schedule(100);
		}
		
		/*
		IValidationStatus status = statusManager.getStatus(project, StatusManager.Tool.TRANSFORM_OVERVIEW.getID());
		if(status != null){
			status.setIsDirty(false);
			status.setIsDone(true);
		}
		*/
	}
	
	private class ConverterJob extends Job 
	{
		private Resource resource;

		public ConverterJob(Resource resource)
		{
			super ("ScaleDL Overview -> PCM Converter");
			this.resource = resource;
			
			// Show progress dialog
			setUser(true);
		}
		
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			
			// Infinite progress: -1
			monitor.beginTask("Transforming ScaleDL Overview model into PCM.", -1);
			
			try
			{
				
				ConverterService.getInstance().convert(resource);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return new Status (Status.ERROR, "", "Failed to convert SDLO model to PCM.");
			}
			monitor.done();
						
			return Status.OK_STATUS;
		}
	}
		
}