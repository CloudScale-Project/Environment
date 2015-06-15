package org.scaledl.overview.diagram.diagram;

import java.util.EventObject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.scaledl.overview.diagram.util.OverviewDiagramUtil;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class CSMDiagramTypeProvider extends AbstractDiagramTypeProvider{
	
    private IToolBehaviorProvider[] toolBehaviorProviders;
        
	public CSMDiagramTypeProvider() {
		super();
		setFeatureProvider(new DiagramFeatureProvider(this));
	}
	
	@Override
	public void postInit() {
		super.postInit();
		
		final IProject project = ExplorerProjectPaths.getProjectFromEmfResource(getDiagram().eResource());
		IFile file = ExplorerProjectPaths.getFileFromEmfResource(OverviewDiagramUtil.getOverviewModel(getDiagram()).eResource());
		
		ResourceProvider rp = ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.OVERVIEW_ID);
		
		if(rp != null){
			final IEditorInputResource resource = rp.getResource(file);
					
			ValidationDiagramService.showStatus(project, resource);
			StatusManager.getInstance().validateAsync(project, resource);
			
			getDiagramBehavior().getEditingDomain().getCommandStack().addCommandStackListener(new CommandStackListener() {
				
				@Override
				public void commandStackChanged(EventObject event) {
					StatusManager.getInstance().validateAsync(project, resource);
				}
			});
		}
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
