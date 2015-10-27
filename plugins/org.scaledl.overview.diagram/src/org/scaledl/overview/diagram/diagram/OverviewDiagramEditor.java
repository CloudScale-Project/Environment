package org.scaledl.overview.diagram.diagram;

import org.eclipse.graphiti.ui.editor.DiagramEditor;

public class OverviewDiagramEditor extends DiagramEditor{
	
	@Override
	public void setFocus() {
		super.setFocus();
		
		/*
		IFile file = ExplorerProjectPaths.getFileFromEmfResource(getDiagramTypeProvider().getDiagram().eResource());
		IProject project = file.getProject();
		
		if(project != null){
			IEditorInputResource resource = ResourceRegistry.getInstance().
					getResourceProvider(project, CSToolResource.OVERVIEW).getResource(file);
			
		}
		*/
		
		//TODO:
		//add alternative into this part context, so the validation diagram updates accordingly...
	}

}
