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
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractConfigAlternative;

public class ConfigAlternative extends AbstractConfigAlternative {

	public static final String PLUGIN_FILE_DEFAULT_CATALOG = "resources/catalog/default.psc";
	public static final String PLUGIN_FILE_DEFAULT_ENGINES = "resources/catalog/default.psc.ecore";

	public static final String FILE_CATALOG = "catalog.pcs";
	public static final String FILE_ENGINES = "catalog.pcs.ecore";

	public static final String KEY_CATALOG = "catalog";
	public static final String KEY_ENGINES = "engines_ecore";

	public static final String KEY_EXTRACTOR_RESULT = "extractor_result";

	//TODO: use EditorInputEMF loader!
	public ConfigAlternative(IProject project, IFolder folder) {
		
		super (project, folder, null, CSTool.SPOTTER_STA_CONF.getID(), 
									  CSTool.SPOTTER_STA_INPUT.getID(), 
									  CSTool.SPOTTER_STA_RES.getID());
	}

	@Override
	public void doCreate(IProgressMonitor monitor) {

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
			loadModels(monitor);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		save(monitor);
	}
	
	@Override
	protected void doLoad(IProgressMonitor monitor) {
		
		//TODO: use EditorInputEMF loader!		
		try {
			loadModels(monitor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final void loadModels(IProgressMonitor monitor) throws IOException {
		
		//TODO: use EditorInputEMF loader!
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
		final DetectPatternsJob detectPaternJob = Util.createDetectPaternJob(this, (InputAlternative) getInputAlternative());
		
		IStatus status = detectPaternJob.run(m);
		if (status.isOK())
		{
			// Collect results & create result alternative
			Util.persistResults(this, detectPaternJob);
		}

		return status;
	}

	
}
