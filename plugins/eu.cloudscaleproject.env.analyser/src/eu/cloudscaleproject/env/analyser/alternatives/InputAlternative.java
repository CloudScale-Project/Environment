package eu.cloudscaleproject.env.analyser.alternatives;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.util.RepositoryResourceImpl;
import eu.cloudscaleproject.env.analyser.PCMModelType;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class InputAlternative extends EditorInputEMF{
			
	private static final long serialVersionUID = 1L;
	
	public InputAlternative(IProject project, IFolder folder, AdapterFactory factory){
		super(project, folder, factory);
	}
	
	public void importFromFolder(IContainer folder){
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.REPOSITORY.getFileExtension());
			setSubResources(ToolchainUtils.KEY_FILE_REPOSITORY, files);
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.SYSTEM.getFileExtension());
			setSubResources(ToolchainUtils.KEY_FILE_SYSTEM, files);
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.RESOURCE.getFileExtension());
			setSubResources(ToolchainUtils.KEY_FILE_RESOURCEENV, files);
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.ALLOCATION.getFileExtension());
			setSubResources(ToolchainUtils.KEY_FILE_ALLOCATION, files);
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.USAGE.getFileExtension());
			setSubResources(ToolchainUtils.KEY_FILE_USAGE, files);
		}
	}
	
	public void createEmpty(){
		
		PCMModelType[] types = new PCMModelType[]{
				PCMModelType.REPOSITORY,
				PCMModelType.SYSTEM,
				PCMModelType.ALLOCATION,
				PCMModelType.RESOURCE,
				PCMModelType.USAGE};
		
		PCMResourceSet resSet = new PCMResourceSet(getResource());
		resSet.createAll(types);
		resSet.saveAll(types);
		
		importFromFolder(getResource());		
		
		save();
		load();
	}
	
	/**
	 * Set's Allocation model and all referenced models (Repository, System and ResourceEnv).
	 * 
	 * @param alloc IFile that points to Allocation resource.
	 */
	public void setAllocation(IFile alloc) {
		
		setSubResource(ToolchainUtils.KEY_FILE_ALLOCATION, alloc);

		try {
			alloc.refreshLocal(IFile.DEPTH_ZERO, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//set other model file that are referenced inside allocation
		Resource res = ExplorerProjectPaths.getEmfResource(resSet, alloc);
		EObject eo = res.getContents().get(0);
		
		if(eo instanceof Allocation){
			Allocation allocModel = (Allocation)eo;
			
			de.uka.ipd.sdq.pcm.system.System sys = allocModel.getSystem_Allocation();
			EcoreUtil.resolveAll(sys);

			de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment resenv = allocModel.getTargetResourceEnvironment_Allocation();
			EcoreUtil.resolveAll(resenv);
			
			setSubResource(ToolchainUtils.KEY_FILE_SYSTEM, ExplorerProjectPaths.getFileFromEmfResource(sys.eResource()));
			setSubResource(ToolchainUtils.KEY_FILE_RESOURCEENV, ExplorerProjectPaths.getFileFromEmfResource(resenv.eResource()));
			
			List<IFile> repositoryFiles = new ArrayList<IFile>();
			for(Resource loadedRes : resSet.getResources()){
				if(loadedRes instanceof RepositoryResourceImpl){
					repositoryFiles.add(ExplorerProjectPaths.getFileFromEmfResource(loadedRes));
				}
			}
			setSubResources(ToolchainUtils.KEY_FILE_REPOSITORY, repositoryFiles);
		}
	}
	
	@Override
	protected void doLoad() {
		
		super.doLoad();
		
		try {
			loadModels();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final void loadModels() throws IOException {
		
		for (IResource f : getSubResources(ToolchainUtils.KEY_FILE_REPOSITORY)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)f);
			res.unload();
			res.load(null);
		}
		for (IResource f : getSubResources(ToolchainUtils.KEY_FILE_SYSTEM)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)f);
			res.unload();
			res.load(null);		
		}
		for (IResource f : getSubResources(ToolchainUtils.KEY_FILE_ALLOCATION)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)f);
			res.unload();
			res.load(null);		
		}
		for (IResource f : getSubResources(ToolchainUtils.KEY_FILE_RESOURCEENV)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)f);
			res.unload();
			res.load(null);		
		}
		for (IResource f : getSubResources(ToolchainUtils.KEY_FILE_USAGE)) {
			Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile)f);
			res.unload();
			res.load(null);		
		}
	}
}
