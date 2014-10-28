package eu.cloudscaleproject.env.product.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

import javax.inject.Inject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import eu.cloudscaleproject.env.common.CloudScaleConstants;
import eu.cloudscaleproject.env.common.CommandExecutor;
import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.common.wizard.NewProjectExtension;
import eu.cloudscaleproject.env.product.Activator;

public class NewProjectWizard extends BasicNewProjectResourceWizard {

	private static final String DEFAULT_FOLDER_GENERATED = "Generated models";
	// private static final String DEFAULT_FOLDER_IMPORT = "Imported models";


	private static final String DEFAULT_FOLDER_ANALYSER = " Analyser";
	private static final String DEFAULT_FOLDER_EXTRACTOR = " Extractor";
	private static final String DEFAULT_FOLDER_STATIC_SPOTTER = " Static Spotter";
	private static final String DEFAULT_FOLDER_DYNAMIC_SPOTTER = " Dynamic Spotter";
	private static final String DEFAULT_FOLDER_SCALEDL = "ScaleDL models";

	private static final String DEFAULT_FOLDER_INPUT = " Input";
	private static final String DEFAULT_FOLDER_CONFIGURATION = "Configuration";
	private static final String DEFAULT_FOLDER_RESULTS = "Results";

	@Inject
	private CommandExecutor commandExecutor;

	public NewProjectWizard() {
		super();

		IEclipseContext context = EclipseContextFactory
				.getServiceContext(Activator.getContext());
		ContextInjectionFactory.inject(this, context);
	}

	@Override
	public void addPages() {
		// Add main page
		super.addPages();
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub

		boolean b = super.performFinish();

		if (b && getNewProject() != null) {
			try {
				IProject p = getNewProject();
				addCSProjectNature(p);

				// Create Project file
				IFile projectFile = p
						.getFile(ExplorerProjectPaths.FILE_PROJECT_PROPERTIES);
				Properties prop = createDefaultProperties();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				prop.store(out, "");

				if (!projectFile.exists())
					projectFile.create(
							new ByteArrayInputStream(out.toByteArray()), b,
							null);

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
				createFolder (p, DEFAULT_FOLDER_GENERATED);


				// call all new project extensions
				handleExtensions(p);

				// refresh project for external changes
				try {
					p.refreshLocal(IProject.DEPTH_INFINITE,
							new NullProgressMonitor());
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return b;
	}

	private IFolder createFolder(IProject p, String folderName) {

        IFolder folder = null;
		try {
			folder = p.getFolder(folderName);
			if (!folder.exists())
				folder.create(true, true, null);

			// input/results/configuration


		} catch (CoreException e) {
			e.printStackTrace();
		}

		return folder;
	}
	
	private void createToolSubfolders (IFolder toolFolder)
	{
		for (String subfolderName : new String[]{DEFAULT_FOLDER_INPUT, DEFAULT_FOLDER_CONFIGURATION, DEFAULT_FOLDER_RESULTS})
		{
			IFolder subFolder = toolFolder.getFolder(subfolderName);
			if (!subFolder.exists())
				try {
					subFolder.create(true, true, null);
				} catch (CoreException e) {
					e.printStackTrace();
				}
		}
	}

	private void handleExtensions(final IProject p) {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						"eu.cloudscaleproject.env.product.newproject");

		try {
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof NewProjectExtension) {
					ISafeRunnable runnable = new ISafeRunnable() {
						@Override
						public void handleException(Throwable e) {
							System.out.println("Exception in client");
						}

						@Override
						public void run() throws Exception {
							((NewProjectExtension) o).finalize(p);
							;
						}
					};
					SafeRunner.run(runnable);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void addCSProjectNature(IProject p) {
		try {
			IProjectDescription desc = p.getDescription();
			String[] prevNatures = desc.getNatureIds();
			String[] newNatures = new String[prevNatures.length + 1];
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			newNatures[prevNatures.length] = CloudScaleConstants.PROJECT_NATURE_ID;
			desc.setNatureIds(newNatures);
			p.setDescription(desc, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}

	private Properties createDefaultProperties() {
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

		return prop;
	}

	@Override
	protected void updatePerspective() {
		commandExecutor.execute("eu.cloudscaleproject.env.product.command.openCsPerspectiv");
	}

}
