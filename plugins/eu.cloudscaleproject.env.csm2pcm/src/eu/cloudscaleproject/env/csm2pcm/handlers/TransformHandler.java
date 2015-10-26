 
package eu.cloudscaleproject.env.csm2pcm.handlers;

import org.eclipse.e4.core.di.annotations.Execute;

import eu.cloudscaleproject.env.analyser.alternatives.InputAlternative;
import eu.cloudscaleproject.env.csm2pcm.OverviewConverterNew;
import eu.cloudscaleproject.env.overview.OverviewAlternative;

public class TransformHandler {
	
	//private static final Logger logger = Logger.getLogger(TransformHandler.class.getName());
	
	@Execute
	public void execute(OverviewAlternative overviewAlt, InputAlternative inputAlt) {
		OverviewConverterNew.getInstance().transform(overviewAlt, inputAlt);
	}
	
	//OLD Staff
	/*
	public void execute(IFile file, BasicCallback<IFolder> callback) {
		
		try{
			IProject project = file.getProject();
			
			String generatedFolderName = ExplorerProjectPaths.getProjectProperty(project, 
					ExplorerProjectPaths.FOLDER_GENERATED_KEY, 
					ExplorerProjectPaths.FOLDER_GENERATED_DEFAULT);
	
			IFolder generatedFolder = project.getFolder(generatedFolderName);
			ExplorerProjectPaths.prepareFolder(generatedFolder);
			
			DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm");
			String date = df.format(new Date(System.currentTimeMillis()));
			
			IFolder outputFolder = ExplorerProjectPaths.getNonexistingSubFolder(generatedFolder, "PCM [ "+ date +" ]");
			ExplorerProjectPaths.prepareFolder(outputFolder);
			
			execute(file, outputFolder, callback);
		}
		catch(CoreException e){
			e.printStackTrace();
		}
		
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
	*/
		
}