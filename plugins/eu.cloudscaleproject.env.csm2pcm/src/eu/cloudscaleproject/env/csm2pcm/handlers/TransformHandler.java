 
package eu.cloudscaleproject.env.csm2pcm.handlers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.scaledl.overview.converter.ConverterService;

import eu.cloudscaleproject.env.common.BasicCallback;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class TransformHandler {
	
	private static final Logger logger = Logger.getLogger(TransformHandler.class.getName());
	
	@Execute
	public void execute(IFile file) {
		execute(file, null);
	}
	
	public void execute(IFile file, BasicCallback<IFolder> callback) {
		IFolder generatedFolder = ExplorerProjectPaths.getProjectFolder(file.getProject(), ExplorerProjectPaths.KEY_FOLDER_GENERATED);
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm");
		String date = df.format(new Date(System.currentTimeMillis()));
		
		IFolder outputFolder = ExplorerProjectPaths.getNonexistingSubFolder(generatedFolder, "PCM [ "+ date +" ]");
		try {
			outputFolder.create(true, true, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		execute(file, outputFolder, callback);
	}
	
	public void execute(IFile file, IFolder outputFolder, BasicCallback<IFolder> callback) {
		
		ResourceSet resSet = new ResourceSetImpl();
		Resource res = ExplorerProjectPaths.getEmfResource(resSet, file);
		
		if(res == null){
			logger.warning("Overview model is not present in the current project! Exiting command...");
			return;
		}
	
		if(res != null && !res.getContents().isEmpty()){
			ConverterJob job = new ConverterJob(res, outputFolder, callback);
			job.schedule(100);
		}
	}
	
	private class ConverterJob extends Job 
	{
		private Resource resource;
		private IFolder outputFolder;
		private BasicCallback<IFolder> callback;

		public ConverterJob(Resource resource, IFolder outputFolder, BasicCallback<IFolder> callback)
		{
			super ("ScaleDL Overview -> PCM Converter");
			
			this.outputFolder = outputFolder;
			this.resource = resource;
			this.callback = callback;
			
			// Show progress dialog
			setUser(true);
		}
		
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			
			// Infinite progress: -1
			monitor.beginTask("Transforming ScaleDL Overview model into PCM.", -1);
			
			try
			{				
				ConverterService.getInstance().convert(resource, outputFolder);
				callback.handle(outputFolder);
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