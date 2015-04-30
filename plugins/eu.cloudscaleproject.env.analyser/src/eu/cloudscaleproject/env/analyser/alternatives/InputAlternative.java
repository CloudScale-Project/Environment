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
					
	public InputAlternative(IProject project, IFolder folder, AdapterFactory factory){
		super(project, folder, factory);
	}
	
	@Override
	public String getID() {
		return ToolchainUtils.ANALYSER_INPUT_ID;
	}
	
	public void importFromFolder(IContainer folder){
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.REPOSITORY.getFileExtension());
			setSubResources(PCMModelType.REPOSITORY.getToolchainFileID(), files);
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.SYSTEM.getFileExtension());
			setSubResources(PCMModelType.SYSTEM.getToolchainFileID(), files);
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.RESOURCE.getFileExtension());
			setSubResources(PCMModelType.RESOURCE.getToolchainFileID(), files);
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.ALLOCATION.getFileExtension());
			setSubResources(PCMModelType.ALLOCATION.getToolchainFileID(), files);
		}
		{
			List<IFile> files = PCMResourceSet.findResource(folder, PCMModelType.USAGE.getFileExtension());
			setSubResources(PCMModelType.USAGE.getToolchainFileID(), files);
		}
	}
	
	public void createEmpty(){
		
		PCMModelType[] types = new PCMModelType[]{
				PCMModelType.REPOSITORY,
				PCMModelType.SYSTEM,
				PCMModelType.ALLOCATION,
				PCMModelType.RESOURCE,
				PCMModelType.USAGE};
		
		createEmpty(types);
	}
	
	public void createEmpty(PCMModelType[] types){
		
		PCMResourceSet resSet = new PCMResourceSet(getResource());
		resSet.createAll(types);
		
		for(PCMModelType type : types){
			IFile file = resSet.getModelFile(type);
			EObject root = resSet.getModelRootObject(type);
			
			this.resSet.getResources().add(root.eResource());
			setSubResource(type.getToolchainFileID(), file);
		}
	}
	
	public void addSubResourceModel(IResource res) {
		
		String ext = res.getFileExtension();
		String key = null;
		
		if (PCMModelType.REPOSITORY.getFileExtension().equals(ext)) {
			key = ToolchainUtils.KEY_FILE_REPOSITORY;
		} else if (PCMModelType.SYSTEM.getFileExtension().equals(ext)) {
			key = ToolchainUtils.KEY_FILE_SYSTEM;
		} else if (PCMModelType.RESOURCE.getFileExtension().equals(ext)) {
			key = ToolchainUtils.KEY_FILE_RESOURCEENV;
		} else if (PCMModelType.ALLOCATION.getFileExtension().equals(ext)) {
			key = ToolchainUtils.KEY_FILE_ALLOCATION;
		} else if (PCMModelType.USAGE.getFileExtension().equals(ext)) {
			key = ToolchainUtils.KEY_FILE_USAGE;
		}
		
		if(key == null){
			throw new UnsupportedOperationException("Specified resource model file is not supported!");
		}
		
		ExplorerProjectPaths.getEmfResource(resSet, (IFile)res);
		super.addSubResource(key, res);
	}
	
	public void removeSubResourceModel(IResource res){
		String ext = res.getFileExtension();
		String key = null;
		
		if (PCMModelType.REPOSITORY.getFileExtension().equals(ext)) {
			key = ToolchainUtils.KEY_FILE_REPOSITORY;
		} else if (PCMModelType.SYSTEM.getFileExtension().equals(ext)) {
			key = ToolchainUtils.KEY_FILE_SYSTEM;
		} else if (PCMModelType.RESOURCE.getFileExtension().equals(ext)) {
			key = ToolchainUtils.KEY_FILE_RESOURCEENV;
		} else if (PCMModelType.ALLOCATION.getFileExtension().equals(ext)) {
			key = ToolchainUtils.KEY_FILE_ALLOCATION;
		} else if (PCMModelType.USAGE.getFileExtension().equals(ext)) {
			key = ToolchainUtils.KEY_FILE_USAGE;
		}
		
		if(key == null){
			throw new UnsupportedOperationException("Specified resource model file is not supported!");
		}
		
		Resource resource = ExplorerProjectPaths.getEmfResource(resSet, (IFile)res);
		resSet.getResources().remove(resource);
		
		super.removeSubResource(key, res);
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
