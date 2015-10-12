package eu.cloudscaleproject.env.product.wizard;

import java.io.ByteArrayInputStream;
import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;

import eu.cloudscaleproject.env.common.CloudScaleConstants;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;

public class CloudScaleProjectSupport
{

	public static void addNature (IProject p, String nature)
	{
		try
		{
			IProjectDescription desc = p.getDescription();
			String[] prevNatures = desc.getNatureIds();
			String[] newNatures = new String[prevNatures.length + 1];
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			newNatures[prevNatures.length] = nature;
			desc.setNatureIds(newNatures);
			p.setDescription(desc, new NullProgressMonitor());
		} catch (CoreException e)
		{
			e.printStackTrace();
		}
	}
	public static void addProjectNature(IProject p)
	{
		addNature(p, CloudScaleConstants.PROJECT_NATURE_ID);
	}

    /**
     * Just do the basics: create a basic project.
     * 
     * @param location
     * @param projectName
     */
    public static IProject createBaseProject(String projectName, URI location) {
        // it is acceptable to use the ResourcesPlugin class
        IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

        if (!newProject.exists()) {
            URI projectLocation = location;
            IProjectDescription desc = newProject.getWorkspace().newProjectDescription(newProject.getName());
            if (location != null && ResourcesPlugin.getWorkspace().getRoot().getLocationURI().equals(location)) {
                projectLocation = null;
            }

            desc.setLocationURI(projectLocation);
            try {
                newProject.create(desc, null);
                if (!newProject.isOpen()) {
                    newProject.open(null);
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }

        return newProject;
    }


	public static void createDefaultProject(IProject p)
	{
		try
		{
			addProjectNature(p);
			
			//IFolder extractorFolder = createFolder(p, DEFAULT_FOLDER_EXTRACTOR);
			//IFolder analyserFolder = createFolder(p, DEFAULT_FOLDER_ANALYSER);
			//IFolder dsFolder = createFolder(p, DEFAULT_FOLDER_DYNAMIC_SPOTTER);
			//IFolder ssFolder = createFolder(p, DEFAULT_FOLDER_STATIC_SPOTTER);

			//createToolSubfolders(extractorFolder);
			//createToolSubfolders(analyserFolder);
			//createToolSubfolders(dsFolder);
			//createToolSubfolders(ssFolder);

			//createFolder(p, DEFAULT_FOLDER_SCALEDL);
			//createFolder(p, DEFAULT_FOLDER_GENERATED);
			
			// Create Project file
			//createDefaultProperties(p);

			// projectFile.setHidden(true);

			IFile dashboardFile = p.getFile(ExplorerProjectPaths.FILE_PROJECT_DASHBOARD);
			if (!dashboardFile.exists())
				dashboardFile.create(new ByteArrayInputStream(new byte[]{}), true, null);
			
			// call all new project extensions
			handleExtensions(p);

			// refresh project for external changes
			try
			{
				p.refreshLocal(IProject.DEPTH_INFINITE, new NullProgressMonitor());
				
				//This operation causes thread-lock
				/*
				final IFile propertyFile = ExplorerProjectPaths.getDashboardFile(p);
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						ExplorerUtils.selectAndReveal(propertyFile);
						ExplorerUtils.openFile(propertyFile);
					}
				});
				*/
				
			} 
			catch (CoreException e)
			{
				e.printStackTrace();
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private static void handleExtensions(final IProject p)
	{
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				"eu.cloudscaleproject.env.product.newproject");

		try
		{
			for (IConfigurationElement e : config)
			{
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof NewProjectExtension)
				{
					ISafeRunnable runnable = new ISafeRunnable()
					{
						@Override
						public void handleException(Throwable e)
						{
							System.out.println("Exception in client");
						}

						@Override
						public void run() throws Exception
						{
							((NewProjectExtension) o).finalize(p);
							;
						}
					};
					SafeRunner.run(runnable);
				}
			}
		} catch (CoreException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	/*
	private static final String DEFAULT_FOLDER_GENERATED = "Generated models";
	// private static final String DEFAULT_FOLDER_IMPORT = "Imported models";

	private static final String DEFAULT_FOLDER_ANALYSER = " Analyser";
	private static final String DEFAULT_FOLDER_EXTRACTOR = " Extractor";
	private static final String DEFAULT_FOLDER_STATIC_SPOTTER = " Static Spotter";
	private static final String DEFAULT_FOLDER_DYNAMIC_SPOTTER = " Dynamic Spotter";
	private static final String DEFAULT_FOLDER_SCALEDL = "ScaleDL models";
	private static final String DEFAULT_FOLDER_USAGE_EVOLUTION = "Usage Evolution";

	private static final String DEFAULT_FOLDER_INPUT = " Input";
	private static final String DEFAULT_FOLDER_CONFIGURATION = "Configuration";
	private static final String DEFAULT_FOLDER_RESULTS = "Results";

	private static void createDefaultProperties(IProject project)
	{
		ExplorerProjectPaths.setProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_GENERATED, DEFAULT_FOLDER_GENERATED);
		
		ExplorerProjectPaths.setProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_INPUT, DEFAULT_FOLDER_INPUT);
		ExplorerProjectPaths.setProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION, DEFAULT_FOLDER_CONFIGURATION);
		ExplorerProjectPaths.setProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_RESULTS, DEFAULT_FOLDER_RESULTS);

		ExplorerProjectPaths.setProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_ANALYSER, DEFAULT_FOLDER_ANALYSER);
		ExplorerProjectPaths.setProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_EXTRACTOR, DEFAULT_FOLDER_EXTRACTOR);
		ExplorerProjectPaths.setProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_DYNAMIC_SPOTTER, DEFAULT_FOLDER_DYNAMIC_SPOTTER);
		ExplorerProjectPaths.setProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_STATIC_SPOTTER, DEFAULT_FOLDER_STATIC_SPOTTER);

		ExplorerProjectPaths.setProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_SCALEDL, DEFAULT_FOLDER_SCALEDL);
		ExplorerProjectPaths.setProjectProperty(project, ExplorerProjectPaths.KEY_FOLDER_USAGE_EVOLUTION, DEFAULT_FOLDER_USAGE_EVOLUTION);
	}

	private static IFolder createFolder(IProject p, String folderName)
	{

		IFolder folder = null;
		try
		{
			folder = p.getFolder(folderName);
			if (!folder.exists())
				folder.create(true, true, null);

			// input/results/configuration

		} catch (CoreException e)
		{
			e.printStackTrace();
		}

		return folder;
	}

	private static void createToolSubfolders(IFolder toolFolder)
	{
		for (String subfolderName : new String[] { DEFAULT_FOLDER_INPUT, DEFAULT_FOLDER_CONFIGURATION, DEFAULT_FOLDER_RESULTS })
		{
			IFolder subFolder = toolFolder.getFolder(subfolderName);
			if (!subFolder.exists())
				try
				{
					subFolder.create(true, true, null);
				} catch (CoreException e)
				{
					e.printStackTrace();
				}
		}
	}
	*/
}
