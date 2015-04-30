package org.scaledl.overview.diagram.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.scaledl.overview.diagram.OverviewResource;
import org.scaledl.overview.diagram.Util;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public class NewProjectWizard implements NewProjectExtension{
		
	public void finalize(IProject project){
		
		//create or retrieve ScaleDL folder
		String folder = ExplorerProjectPaths.getProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_SCALEDL);
		
		//create default overview model and diagram file paths 
		String defaultOverviewModelPath = folder + "/" + project.getName() + ".sdlo";
		String defaultOverviewDiagramPath = folder + "/" + project.getName() + ".sdlo_diagram";
		
		//retrieve or set project overview model and diagram file paths
		String modelPath = ExplorerProjectPaths.getProjectProperty(project, 
				ExplorerProjectPaths.KEY_FILE_OVERVIEW_MODEL, 
				defaultOverviewModelPath);
		String diagramPath = ExplorerProjectPaths.getProjectProperty(project, 
				ExplorerProjectPaths.KEY_FILE_OVERVIEW_DIAGRAM, 
				defaultOverviewDiagramPath);
		
		//create model/diagram
		IFile modelFile = project.getFile(modelPath);
		IFile diagramFile = project.getFile(diagramPath);
				
		URI modelURI = org.eclipse.emf.common.util.URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);
		URI diagramURI = org.eclipse.emf.common.util.URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		Util.createDiagram(diagramURI, modelURI);
		
		//TODO: remove resource from registry on delete - memory leak!
		OverviewResource resource = new OverviewResource(modelFile);
		ResourceRegistry.getInstance().registerProjectUniqueResource(resource, ToolchainUtils.OVERVIEW_ID);
		
	}
	
}
