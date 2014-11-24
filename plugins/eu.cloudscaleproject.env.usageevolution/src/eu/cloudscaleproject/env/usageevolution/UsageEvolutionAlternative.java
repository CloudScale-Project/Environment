package eu.cloudscaleproject.env.usageevolution;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;

import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class UsageEvolutionAlternative extends EditorInputFolder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsageEvolutionAlternative(IProject project, IFolder folder) {
		super(project, folder);
	}

	public void setUsageEvolutionModel(Resource resource){
		
	}
	
	public Resource getUsageEvolutionModel(){
		//TODO: Implement this
		return null;
	}
}
