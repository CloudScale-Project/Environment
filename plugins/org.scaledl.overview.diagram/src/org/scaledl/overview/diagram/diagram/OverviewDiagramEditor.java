package org.scaledl.overview.diagram.diagram;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.graphiti.ui.editor.DiagramEditor;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class OverviewDiagramEditor extends DiagramEditor{
	
	@Override
	public void setFocus() {
		super.setFocus();
		
		IFile file = ExplorerProjectPaths.getFileFromEmfResource(getDiagramTypeProvider().getDiagram().eResource());
		IProject project = file.getProject();
		
		if(project != null){
			IEditorInputResource resource = ResourceRegistry.getInstance().
					getResourceProvider(project, CSTool.OVERVIEW).getResource(file);
			
			ValidationDiagramService.showStatus(project, resource);
		}
	}

}
