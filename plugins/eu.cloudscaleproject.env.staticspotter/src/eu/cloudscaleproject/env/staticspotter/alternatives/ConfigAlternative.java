package eu.cloudscaleproject.env.staticspotter.alternatives;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.reclipse.structure.inference.DetectPatternsJob;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;
import eu.cloudscaleproject.env.staticspotter.util.Util;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputFolder;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class ConfigAlternative extends AbstractConfigAlternative {

	public static final String KEY_INPUT_ALTERNATIVE= "input_alternative";
	
	public static final String PLUGIN_FILE_DEFAULT_CATALOG = "resources/catalog/default.psc";
	public static final String PLUGIN_FILE_DEFAULT_ENGINES = "resources/catalog/default.psc.ecore";

	public static final String FILE_CATALOG = "catalog.pcs";
	public static final String FILE_ENGINES = "catalog.pcs.ecore";

	public static final String KEY_CATALOG = "catalog";
	public static final String KEY_ENGINES = "engines_ecore";

	public static final String KEY_EXTRACTOR_RESULT = "extractor_result";

	
	public ConfigAlternative(IProject project, IFolder folder) {
		super (project, folder, ToolchainUtils.SPOTTER_STA_CONF_ID,
				ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_STA_INPUT_ID),
				ResourceRegistry.getInstance().getResourceProvider(project, ToolchainUtils.SPOTTER_STA_RES_ID)
				);
	}
	
	@Override
	public IEditorInputResource getInputAlternative()
	{
		return GlobalInputAlternative.getInstance();
	}

	public IEditorInputResource getExtractorResult()
	{
		ResourceProvider resourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(getProject(),
				ToolchainUtils.EXTRACTOR_RES_ID);
		
		String resourceName = getProperty(KEY_EXTRACTOR_RESULT);
		return resourceProvider.getResource(resourceName);
	}

	public void setExtractorResult(IEditorInputResource project)
	{
		setProperty(KEY_EXTRACTOR_RESULT, project.getResource().getName());
	}
	
	@Override
	public void create() {
		super.create();

		try {
			IFile catalog = getResource().getFile(FILE_CATALOG);
			if (!catalog.exists()) {
				
				InputStream in = getClass().getClassLoader().getResourceAsStream(PLUGIN_FILE_DEFAULT_CATALOG);
				catalog.create(in, false, null);
				in.close();
			}

			IFile engines = getResource().getFile(FILE_ENGINES);
			if (!engines.exists()) {
				
				InputStream in = getClass().getClassLoader().getResourceAsStream(PLUGIN_FILE_DEFAULT_ENGINES);
				engines.create(in, false, null);
				in.close();
			}
			
			setSubResource(KEY_CATALOG, catalog);
			setSubResource(KEY_ENGINES, engines);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		save();
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
		
		IResource catalog = getSubResource(ConfigAlternative.KEY_CATALOG);
		IResource engines = getSubResource(ConfigAlternative.KEY_ENGINES);
		
		for (IResource res : new IResource[]{catalog, engines})
		{
			if (res != null)
			{
				Resource emfRes = ExplorerProjectPaths.getEmfResource(resSet, (IFile)res);
				emfRes.unload();
				emfRes.load(null);
			}
			
		}
	}

	@Override
	protected IStatus doRun(IProgressMonitor m)
	{
		final DetectPatternsJob detectPaternJob = Util.createDetectPaternJob(this, (EditorInputFolder) getExtractorResult());
		
		IStatus status = detectPaternJob.run(m);
		if (status.isOK())
		{
			// Collect results
			Util.saveAnnotations(this, detectPaternJob);
		}

		return status;
	}

	
}
