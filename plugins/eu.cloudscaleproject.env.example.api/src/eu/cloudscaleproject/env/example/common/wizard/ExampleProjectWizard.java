package eu.cloudscaleproject.env.example.common.wizard;

import java.util.List;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.eclipse.ui.wizards.datatransfer.ZipFileStructureProvider;

import eu.cloudscaleproject.env.common.CloudscaleContext;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.example.common.Example;
import eu.cloudscaleproject.env.example.common.Example.Resource;
import eu.cloudscaleproject.env.example.common.Example.Resource.Type;
import eu.cloudscaleproject.env.example.common.ExampleService;
import eu.cloudscaleproject.env.product.wizard.CloudScaleProjectSupport;
import eu.cloudscaleproject.env.toolchain.services.IExplorerService;

public class ExampleProjectWizard extends Wizard implements INewWizard, IExecutableExtension
{

	private ResourceSelectionPage _pageOne;
	private Example example;
	@SuppressWarnings("unused")
	private IConfigurationElement _configurationElement;

	public ExampleProjectWizard(Example example)
	{
		this.example = example;

		setWindowTitle("Example");
	}

	public Example getExample()
	{
		return example;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection)
	{
	}

	@Override
	public boolean performFinish()
	{

		final List<Resource> selectedResources = _pageOne.getSelectedResources();
		
		WorkspaceJob job = new WorkspaceJob("Creating Example projects")
		{
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {

				IProject cseProject = null;

				for (Resource resource : selectedResources)
				{
					try
					{
						IProject project = ExampleService.getInstance().createExampleResourceProject(resource);

						IFile propertyFile = ExplorerProjectPaths.getDashboardFile(project);
						if (propertyFile != null && propertyFile.exists()) // TODO: check nature
						{
							cseProject = project;
						}
						

					} catch (Exception e)
					{
						e.printStackTrace();
					}

				}

				if (cseProject != null)
				{
					selectExampleProject(cseProject);
				}


				return Status.OK_STATUS;
			}
		};
		
		job.setUser(true);
		job.schedule();

		openMainPerspective();

		return true;
	}
	
	private static void openMainPerspective(){

		CommandExecutor.getInstance().execute("eu.cloudscaleproject.env.product.command.openperspective", 
								"eu.cloudscaleproject.env.product.perspective.main");
	}
	
	private static void selectExampleProject (final IProject project)
	{
		IExplorerService explorerService = CloudscaleContext.getGlobalContext().get(IExplorerService.class);
		int counter = 0;
		while (!explorerService.isAvailable(project))
		{
			try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
			if (++counter>25) break; // Break after 5s
		}

		//select and expand node in explorer
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				IExplorerService explorerService = CloudscaleContext.getGlobalContext().get(IExplorerService.class);
				explorerService.setSelection(project);
			}
		});
	}

	public static IProject createExampleProject(IProject project, Example.Resource resource)
	{
		try
		{

			if (resource.getType() == Type.ENVIRONMENT)
				CloudScaleProjectSupport.addProjectNature(project);

			ZipFile file = new ZipFile(resource.getArchive().getFile());
			ZipFileStructureProvider provider = new ZipFileStructureProvider(file);
			IPath containerPath = project.getFullPath();
			Object source = provider.getRoot();

			IOverwriteQuery query = new IOverwriteQuery()
			{
				@Override
				public String queryOverwrite(String path)
				{
					return IOverwriteQuery.ALL;
				};
			};
			ImportOperation operation = new ImportOperation(containerPath, source, provider, query);
			try
			{
				operation.run(null);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			project = null;
		}

		return project;
	}

	@Override
	public void addPages()
	{
		super.addPages();

		_pageOne = new ResourceSelectionPage(example);

		addPage(_pageOne);
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException
	{
		_configurationElement = config;

	}

}
