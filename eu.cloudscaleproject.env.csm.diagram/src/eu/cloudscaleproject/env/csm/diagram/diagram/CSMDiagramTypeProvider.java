package eu.cloudscaleproject.env.csm.diagram.diagram;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

import eu.cloudscaleproject.env.csm.converter.ConverterService;
import eu.cloudscaleproject.env.csm.util.CsmResourceImpl;

public class CSMDiagramTypeProvider extends AbstractDiagramTypeProvider {
	
    private IToolBehaviorProvider[] toolBehaviorProviders;
    
	public CSMDiagramTypeProvider() {
		super();
		setFeatureProvider(new DiagramFeatureProvider(this));
	}
	
	@Override
    public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
        if (toolBehaviorProviders == null) {
            toolBehaviorProviders =
                new IToolBehaviorProvider[] { new ToolBehaviorProvider(this) };
        }
        return toolBehaviorProviders;
    }
	
	@Override
	public void resourceReloaded(Diagram diagram) {
		// TODO Auto-generated method stub
		super.resourceReloaded(diagram);
	}
	
	@Override
	public void resourcesSaved(Diagram diagram, Resource[] savedResources) {
		super.resourcesSaved(diagram, savedResources);
		for (Resource resource : savedResources) {
			
			if (resource instanceof CsmResourceImpl)
			{
				// TODO: isModified flag is always false --> has just been cleared
				new ConverterJob((CsmResourceImpl)resource).schedule(100);
			}
		}
	}
	
	
	private class ConverterJob extends Job 
	{
		private CsmResourceImpl resource;

		public ConverterJob(CsmResourceImpl resource)
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
