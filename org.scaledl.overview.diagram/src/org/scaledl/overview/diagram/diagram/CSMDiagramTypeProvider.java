package org.scaledl.overview.diagram.diagram;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

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
	}
	
	@Override
	public boolean isAutoUpdateAtStartup() {
		return true;
	}

}
