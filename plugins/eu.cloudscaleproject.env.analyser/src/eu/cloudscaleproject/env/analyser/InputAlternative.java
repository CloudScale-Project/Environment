package eu.cloudscaleproject.env.analyser;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.util.RepositoryResourceImpl;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFile;

public class InputAlternative extends EditorInputFile{
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String KEY_REPOSITORY = "repository";
	public static final String KEY_SYSTEM = "system";
	public static final String KEY_RESOURCES = "resources";
	public static final String KEY_ALLOCATION = "allocation";
	public static final String KEY_USAGE = "usage";
	
	public InputAlternative(IProject project, IFile file){
		super(project, file);
	}
	
	public String getProjectPath(){
		return file.getProjectRelativePath().toString();
	}
	
	public IFile getRepository(){
		String prop = source.getProperty(KEY_REPOSITORY);
		if(prop == null){
			return null;
		}
		return project.getFile(new Path(prop));
	}
	
	public IFile getSystem(){
		String prop = source.getProperty(KEY_SYSTEM);
		if(prop == null){
			return null;
		}
		return project.getFile(new Path(prop));
	}
	
	public IFile getResourceEnv(){
		String prop = source.getProperty(KEY_RESOURCES);
		if(prop == null){
			return null;
		}
		return project.getFile(new Path(prop));
	}
	
	public IFile getAllocation() {
		String prop = source.getProperty(KEY_ALLOCATION);
		if(prop == null){
			return null;
		}
		return project.getFile(new Path(prop));
	}

	public IFile getUsage() {
		String prop = source.getProperty(KEY_USAGE);
		if(prop == null){
			return null;
		}
		return project.getFile(new Path(prop));
	}
	
	public void setRepository(IFile repository) {
		IFile old = getRepository();
		source.setProperty(KEY_REPOSITORY, repository.getProjectRelativePath().toString());
		firePropertyChange(KEY_REPOSITORY, old, repository);
	}
	
	public void setSystem(IFile system) {
		IFile old = getSystem();
		source.setProperty(KEY_SYSTEM, system.getProjectRelativePath().toString());
		firePropertyChange(KEY_SYSTEM, old, system);
	}
	
	public void setResourceEnv(IFile resourceEnv) {
		IFile old = getResourceEnv();
		source.setProperty(KEY_RESOURCES, resourceEnv.getProjectRelativePath().toString());
		firePropertyChange(KEY_RESOURCES, old, resourceEnv);
	}
	
	public void setUsage(IFile usage) {
		IFile old = getResourceEnv();
		source.setProperty(KEY_USAGE, usage.getProjectRelativePath().toString());
		firePropertyChange(KEY_USAGE, old, usage);
	}
	
	/**
	 * Set's Allocation model and all referenced models (Repository, System and ResourceEnv).
	 * 
	 * @param alloc IFile that points to Allocation resource.
	 */
	public void setAllocation(IFile alloc) {
		IFile old = getAllocation();
		source.setProperty(KEY_ALLOCATION, alloc.getProjectRelativePath().toString());
		firePropertyChange(KEY_ALLOCATION, old, alloc);

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
