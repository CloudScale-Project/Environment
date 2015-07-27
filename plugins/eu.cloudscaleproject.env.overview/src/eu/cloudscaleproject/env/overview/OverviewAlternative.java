package eu.cloudscaleproject.env.overview;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.scaledl.overview.diagram.Util;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

/**
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class OverviewAlternative extends EditorInputEMF{
	
	private static final String OVERVIEW_MODEL_FILENAME = "overview.sdlo";
	private static final String OVERVIEW_MODEL_DIAGRAM_FILENAME = "overview.sdlo_diagram";
		
	public OverviewAlternative(IProject project, IFolder folder) {
		super(project, folder, new ModelType[]{ModelType.OVERVIEW}, CSTool.OVERVIEW.getID());
	}
	
	@Override
	public void doCreate(IProgressMonitor monitor) {		
		IFile modelFile = getResource().getFile(OVERVIEW_MODEL_FILENAME);
		IFile diagramFile = getResource().getFile(OVERVIEW_MODEL_DIAGRAM_FILENAME);
				
		URI modelURI = org.eclipse.emf.common.util.URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);
		URI diagramURI = org.eclipse.emf.common.util.URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		Util.createDiagram(diagramURI, modelURI);
		
		setSubResource(ToolchainUtils.KEY_FILE_OVERVIEW, modelFile);
		setSubResource(ToolchainUtils.KEY_FILE_OVERVIEW_DIAGRAM, diagramFile);
	}
}
