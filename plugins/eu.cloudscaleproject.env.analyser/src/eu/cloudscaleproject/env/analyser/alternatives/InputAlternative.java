package eu.cloudscaleproject.env.analyser.alternatives;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.repository.util.RepositoryResourceImpl;

import eu.cloudscaleproject.env.analyser.Activator;
import eu.cloudscaleproject.env.analyser.PCMResourceSet;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputEMF;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputJob;

public class InputAlternative extends EditorInputEMF
{
	//private static final Logger logger = Logger.getLogger(InputAlternative.class.getName());

	public InputAlternative(IProject project, IFolder folder)
	{
		super(project, folder, ModelType.GROUP_PCM_EXTENDED, CSTool.ANALYSER_INPUT.getID());
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

	public void createEmpty(final ModelType[] types)
	{
		EditorInputJob job = new EditorInputJob("Creating empty models.", this) {
			
			@Override
			public IStatus execute(IProgressMonitor monitor) {
				
				PCMResourceSet resSet = new PCMResourceSet(getResource());
				resSet.createAll(types);

				for (ModelType type : types)
				{
					IFile file = resSet.getModelFile(type);
					EObject root = resSet.getModelRootObject(type);

					synchronized (InputAlternative.this.resSet) {
						InputAlternative.this.resSet.getResources().add(root.eResource());
					}
					
					setSubResource(type.getToolchainFileID(), file);
					
					EObject diagram = resSet.getDiagramRootObject(type);
					if(diagram != null){
						try {
							diagram.eResource().save(null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					save();
				}
				
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Empty models have been created.");
			}
		};
		
		job.schedule();		
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
			for (Resource loadedRes : new ArrayList<Resource>(resSet.getResources()))
			{
				if (loadedRes instanceof RepositoryResourceImpl)
				{
					repositoryFiles.add(ExplorerProjectPaths.getFileFromEmfResource(loadedRes));
				}
			}
			setSubResources(ToolchainUtils.KEY_FILE_REPOSITORY, repositoryFiles);
		}
	}

}
