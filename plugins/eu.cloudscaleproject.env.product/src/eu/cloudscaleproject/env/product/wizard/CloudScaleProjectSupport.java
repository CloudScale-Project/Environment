package eu.cloudscaleproject.env.product.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.eclipse.ui.wizards.datatransfer.ZipFileStructureProvider;
import org.osgi.framework.Bundle;

import eu.cloudscaleproject.env.common.CloudScaleConstants;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.explorer.ExplorerUtils;
import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;
import eu.cloudscaleproject.env.product.Activator;

public class CloudScaleProjectSupport
{

	public static void addProjectNature(IProject p)
	{
		try
		{
			IProjectDescription desc = p.getDescription();
			String[] prevNatures = desc.getNatureIds();
			String[] newNatures = new String[prevNatures.length + 1];
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			newNatures[prevNatures.length] = CloudScaleConstants.PROJECT_NATURE_ID;
			desc.setNatureIds(newNatures);
			p.setDescription(desc, new NullProgressMonitor());
		} catch (CoreException e)
		{
			e.printStackTrace();
		}
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

    public static IProject createExampleProject(IProject project, String archivePath) {
        try {
            addProjectNature(project);
            ZipFile file = null;
            try {
                Bundle b = Activator.getDefault().getBundle();
                URL u = b.getEntry(archivePath);
                URL ur = FileLocator.toFileURL(u);
                file = new ZipFile(ur.getFile());
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
            ZipFileStructureProvider provider = new ZipFileStructureProvider(file);
            IPath containerPath = project.getFullPath();
            Object source = provider.getRoot();
            IOverwriteQuery query = new IOverwriteQuery() {
                @Override
                public String queryOverwrite(String path) {
                    return IOverwriteQuery.ALL;
                };
            };
            ImportOperation operation = new ImportOperation(containerPath, source, provider, query);
            try {
                operation.run(null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            project = null;
        }

        return project;
    }

	public static void createDefaultProject(IProject p)
	{
		try
		{
			addProjectNature(p);

			// Create Project file
			IFile projectFile = p.getFile(ExplorerProjectPaths.FILE_PROJECT_PROPERTIES);
			Properties prop = createDefaultProperties();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			prop.store(out, "");

			if (!projectFile.exists())
				projectFile.create(new ByteArrayInputStream(out.toByteArray()), true, null);

			// projectFile.setHidden(true);

			IFolder extractorFolder = createFolder(p, DEFAULT_FOLDER_EXTRACTOR);
			IFolder analyserFolder = createFolder(p, DEFAULT_FOLDER_ANALYSER);
			IFolder dsFolder = createFolder(p, DEFAULT_FOLDER_DYNAMIC_SPOTTER);
			IFolder ssFolder = createFolder(p, DEFAULT_FOLDER_STATIC_SPOTTER);

			createToolSubfolders(extractorFolder);
			createToolSubfolders(analyserFolder);
			createToolSubfolders(dsFolder);
			createToolSubfolders(ssFolder);

			createFolder(p, DEFAULT_FOLDER_SCALEDL);
			createFolder(p, DEFAULT_FOLDER_GENERATED);

			// call all new project extensions
			handleExtensions(p);

			// refresh project for external changes
			try
			{
				p.refreshLocal(IProject.DEPTH_INFINITE, new NullProgressMonitor());

				// expand created project
				for (IResource r : p.members())
				{
					ExplorerUtils.selectAndReveal(r);
				}

			} catch (CoreException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
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

	private static Properties createDefaultProperties()
	{
		Properties prop = new Properties();

		// folders
		prop.setProperty(ExplorerProjectPaths.KEY_FOLDER_GENERATED, DEFAULT_FOLDER_GENERATED);

		prop.setProperty(ExplorerProjectPaths.KEY_FOLDER_INPUT, DEFAULT_FOLDER_INPUT);
		prop.setProperty(ExplorerProjectPaths.KEY_FOLDER_CONFIGURATION, DEFAULT_FOLDER_CONFIGURATION);
		prop.setProperty(ExplorerProjectPaths.KEY_FOLDER_RESULTS, DEFAULT_FOLDER_RESULTS);

		prop.setProperty(ExplorerProjectPaths.KEY_FOLDER_ANALYSER, DEFAULT_FOLDER_ANALYSER);
		prop.setProperty(ExplorerProjectPaths.KEY_FOLDER_EXTRACTOR, DEFAULT_FOLDER_EXTRACTOR);
		prop.setProperty(ExplorerProjectPaths.KEY_FOLDER_DYNAMIC_SPOTTER, DEFAULT_FOLDER_DYNAMIC_SPOTTER);
		prop.setProperty(ExplorerProjectPaths.KEY_FOLDER_STATIC_SPOTTER, DEFAULT_FOLDER_STATIC_SPOTTER);

		prop.setProperty(ExplorerProjectPaths.KEY_FOLDER_SCALEDL, DEFAULT_FOLDER_SCALEDL);
		prop.setProperty(ExplorerProjectPaths.KEY_FOLDER_USAGE_EVOLUTION, DEFAULT_FOLDER_USAGE_EVOLUTION);

		return prop;
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
}
