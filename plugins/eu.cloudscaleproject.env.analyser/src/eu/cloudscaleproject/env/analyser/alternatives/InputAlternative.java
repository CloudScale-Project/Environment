package eu.cloudscaleproject.env.analyser.alternatives;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.util.RepositoryResourceImpl;
import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;

public class InputAlternative extends EditorInputFolder{
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InputAlternative(IProject project, IFolder folder){
		super(project, folder);
	}
	
	public IFile getRepository(){
		return getFileResource(ToolchainUtils.KEY_FILE_REPOSITORY);
	}
	
	public IFile getSystem(){
		return getFileResource(ToolchainUtils.KEY_FILE_SYSTEM);
	}
	
	public IFile getResourceEnv(){
		return getFileResource(ToolchainUtils.KEY_FILE_RESOURCEENV);
	}
	
	public IFile getAllocation() {
		return getFileResource(ToolchainUtils.KEY_FILE_ALLOCATION);
	}

	public IFile getUsage() {
		return getFileResource(ToolchainUtils.KEY_FILE_USAGE);
	}
	
	public void setRepository(IFile repository) {
		setResource(ToolchainUtils.KEY_FILE_REPOSITORY, repository);
	}
	
	public void setSystem(IFile system) {
		setResource(ToolchainUtils.KEY_FILE_SYSTEM, system);
	}
	
	public void setResourceEnv(IFile resourceEnv) {
		setResource(ToolchainUtils.KEY_FILE_RESOURCEENV, resourceEnv);
	}
	
	public void setUsage(IFile usage) {
		setResource(ToolchainUtils.KEY_FILE_USAGE, usage);
	}
	
	public void importFromFolder(IFolder folder){
		
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.REPOSITORY.getFileExtension());
			if(!files.isEmpty()){
				setRepository(files.get(0));
			}
		}
		
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.SYSTEM.getFileExtension());
			if(!files.isEmpty()){
				setSystem(files.get(0));
			}
		}
		
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.RESOURCE.getFileExtension());
			if(!files.isEmpty()){
				setResourceEnv(files.get(0));
			}
		}
		
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.ALLOCATION.getFileExtension());
			if(!files.isEmpty()){
				setAllocation(files.get(0));
			}
		}
		
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.USAGE.getFileExtension());
			if(!files.isEmpty()){
				setUsage(files.get(0));
			}
		}
	}
	
	/**
	 * Set's Allocation model and all referenced models (Repository, System and ResourceEnv).
	 * 
	 * @param alloc IFile that points to Allocation resource.
	 */
	public void setAllocation(IFile alloc) {
		
		setResource(ToolchainUtils.KEY_FILE_ALLOCATION, alloc);

		try {
			alloc.refreshLocal(IFile.DEPTH_ZERO, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//set other model file that are referenced inside allocation
		ResourceSet resSet = new ResourceSetImpl();
		Resource res = ExplorerProjectPaths.getEmfResource(resSet, alloc);
		
		EObject eo = res.getContents().get(0);
		
		if(eo instanceof Allocation){
			Allocation allocModel = (Allocation)eo;
			
			de.uka.ipd.sdq.pcm.system.System sys = allocModel.getSystem_Allocation();
			EcoreUtil.resolveAll(sys);

			de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment resenv = allocModel.getTargetResourceEnvironment_Allocation();
			EcoreUtil.resolveAll(resenv);
			
			setSystem(ExplorerProjectPaths.getFileFromEmfResource(sys.eResource()));
			setResourceEnv(ExplorerProjectPaths.getFileFromEmfResource(resenv.eResource()));
			
			for(Resource loadedRes : resSet.getResources()){
				if(loadedRes instanceof RepositoryResourceImpl){
					setRepository(ExplorerProjectPaths.getFileFromEmfResource(loadedRes));
				}
			}
		}
	}
}
