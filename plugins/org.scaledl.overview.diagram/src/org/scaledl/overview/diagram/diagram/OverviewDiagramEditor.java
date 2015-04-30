package org.scaledl.overview.diagram.diagram;

import org.eclipse.core.resources.IProject;
import org.eclipse.graphiti.ui.editor.DiagramEditor;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.IValidationStatusProvider;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class OverviewDiagramEditor extends DiagramEditor{
	
	@Override
	public void setFocus() {
		super.setFocus();
		
		IProject project = ExplorerProjectPaths.getProject(this);
		if(project != null){
			IValidationStatusProvider resource 
				= ResourceRegistry.getInstance().getProjectUniqueResource(project, ToolchainUtils.OVERVIEW_ID);
			ValidationDiagramService.showStatus(project, resource);
		}
	}

}
