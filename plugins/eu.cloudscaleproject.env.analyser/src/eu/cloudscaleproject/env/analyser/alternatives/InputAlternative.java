package eu.cloudscaleproject.env.analyser.alternatives;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractInputAlternative;

public class InputAlternative extends AbstractInputAlternative
{

	public InputAlternative(IProject project, IFolder folder)
	{
		super(project, folder, ModelType.GROUP_PCM_EXTENDED, CSTool.ANALYSER);
	}
	
	@Override
	protected void doDeleteSubResource(String key, IResource resource) {
		
		//delete diagram file
		ModelType type = ModelType.getModelType(resource.getFileExtension());
		String filename = ExplorerProjectPaths.removeFileExtension(resource.getName());
		
		List<IFile> diagramFiles = ExplorerProjectPaths.findFiles(getResource(), 
				filename, type.getDiagramFileExtension(), true);
		
		for(IFile diagramFile : diagramFiles){
			try {
				diagramFile.delete(true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		super.doDeleteSubResource(key, resource);
	}

}
