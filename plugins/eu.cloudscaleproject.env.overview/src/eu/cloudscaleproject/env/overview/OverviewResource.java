package eu.cloudscaleproject.env.overview;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.scaledl.overview.diagram.Util;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

/**
 * 
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class OverviewResource extends EditorInputEMF{
	
	private static final String OVERVIEW_MODEL_FILENAME = "overview.sdlo";
	private static final String OVERVIEW_MODEL_DIAGRAM_FILENAME = "overview.sdlo_diagram";
		
	public OverviewResource(IProject project, IFolder folder) {
		super(project, folder, ToolchainUtils.OVERVIEW_ID);
	}
	
	@Override
	public void create() {
		
		super.create();
		
		IFile modelFile = getResource().getFile(OVERVIEW_MODEL_FILENAME);
		IFile diagramFile = getResource().getFile(OVERVIEW_MODEL_DIAGRAM_FILENAME);
				
		URI modelURI = org.eclipse.emf.common.util.URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);
		URI diagramURI = org.eclipse.emf.common.util.URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		Util.createDiagram(diagramURI, modelURI);
		
		setSubResource(ToolchainUtils.KEY_FILE_OVERVIEW, modelFile);
		setSubResource(ToolchainUtils.KEY_FILE_OVERVIEW_DIAGRAM, diagramFile);
	}

	@Override
	protected void doLoad() {
		super.doLoad();
		
		try
		{
			loadModels();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadModels() throws IOException{
		
		for (IResource f : getSubResources(ModelType.OVERVIEW.getToolchainFileID()))
		{
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) f);
			res.unload();
			res.load(null);
		}
	}
}
