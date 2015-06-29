package eu.cloudscaleproject.env.analyser.alternatives;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.repository.util.RepositoryResourceImpl;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;

public class InputAlternative extends EditorInputEMF
{

	private static final Logger logger = Logger.getLogger(InputAlternative.class.getName());

	public InputAlternative(IProject project, IFolder folder)
	{
		super(project, folder, ToolchainUtils.ANALYSER_INPUT_ID);
	}

	public void importFromFolder(IContainer folder)
	{

		for (ModelType type : ModelType.GROUP_PCM)
		{
			List<IFile> files = PCMResourceSet.findResource(folder, type.getFileExtension());
			setSubResources(type.getToolchainFileID(), files);
		}
	}

	public void createEmpty()
	{

		createEmpty(ModelType.GROUP_PCM);
	}

	public void createEmpty(ModelType[] types)
	{

		PCMResourceSet resSet = new PCMResourceSet(getResource());
		resSet.createAll(types);

		for (ModelType type : types)
		{
			IFile file = resSet.getModelFile(type);
			EObject root = resSet.getModelRootObject(type);

			this.resSet.getResources().add(root.eResource());
			setSubResource(type.getToolchainFileID(), file);
		}
	}

	public String getToolchainKey(IResource res)
	{

		String ext = res.getFileExtension();
		String key = null;

		for (ModelType type : ModelType.GROUP_PCM_EXTENDED)
		{
			if (type.getFileExtension().equals(ext))
				return type.getToolchainFileID();
		}

		return key;
	}

	public void addSubResourceModel(IResource res)
	{

		ExplorerProjectPaths.getEmfResource(resSet, (IFile) res);

		String key = getToolchainKey(res);
		if (key == null)
		{
			logger.info("addSubResourceModel(): Specified resource model file is not supported: " + res.getName());
			pcs.firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, "");
			return;
		}

		super.addSubResource(key, res);
	}

	public void removeSubResourceModel(IResource res)
	{

		Resource resource = ExplorerProjectPaths.getEmfResource(resSet, (IFile) res);
		resSet.getResources().remove(resource);

		String key = getToolchainKey(res);
		if (key == null)
		{
			logger.info("removeSubResourceModel(): Specified resource model file is not supported: " + res.getName());
			pcs.firePropertyChange(PROP_SUB_RESOURCE_CHANGED, null, "");
			return;
		}

		super.removeSubResource(key, res);
	}

	/**
	 * Set's Allocation model and all referenced models (Repository, System and
	 * ResourceEnv).
	 * 
	 * @param alloc
	 *            IFile that points to Allocation resource.
	 */
	public void setAllocation(IFile alloc)
	{

		setSubResource(ToolchainUtils.KEY_FILE_ALLOCATION, alloc);

		try
		{
			alloc.refreshLocal(IFile.DEPTH_ZERO, null);
		} catch (CoreException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// set other model file that are referenced inside allocation
		Resource res = ExplorerProjectPaths.getEmfResource(resSet, alloc);
		EObject eo = res.getContents().get(0);

		if (eo instanceof Allocation)
		{
			Allocation allocModel = (Allocation) eo;

			org.palladiosimulator.pcm.system.System sys = allocModel.getSystem_Allocation();
			EcoreUtil.resolveAll(sys);

			org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment resenv = allocModel.getTargetResourceEnvironment_Allocation();
			EcoreUtil.resolveAll(resenv);

			setSubResource(ToolchainUtils.KEY_FILE_SYSTEM, ExplorerProjectPaths.getFileFromEmfResource(sys.eResource()));
			setSubResource(ToolchainUtils.KEY_FILE_RESOURCEENV, ExplorerProjectPaths.getFileFromEmfResource(resenv.eResource()));

			List<IFile> repositoryFiles = new ArrayList<IFile>();
			for (Resource loadedRes : resSet.getResources())
			{
				if (loadedRes instanceof RepositoryResourceImpl)
				{
					repositoryFiles.add(ExplorerProjectPaths.getFileFromEmfResource(loadedRes));
				}
			}
			setSubResources(ToolchainUtils.KEY_FILE_REPOSITORY, repositoryFiles);
		}
	}

	@Override
	protected void doLoad()
	{

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

	private final void loadModels() throws IOException
	{
		for (ModelType type : ModelType.GROUP_PCM_EXTENDED)
		{
			for (IResource f : getSubResources(type.getToolchainFileID()))
			{
				Resource res = ExplorerProjectPaths.getEmfResource(resSet, (IFile) f);
				res.unload();
				res.load(null);
			}
		}
	}
}
