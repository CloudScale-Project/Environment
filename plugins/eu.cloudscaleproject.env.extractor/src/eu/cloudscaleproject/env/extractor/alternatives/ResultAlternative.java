package eu.cloudscaleproject.env.extractor.alternatives;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.AbstractResultAlternative;

public class ResultAlternative extends AbstractResultAlternative {
	
	private static final String RESULT_FOLDER_SOMOX = "somox/";
	private static final String RESULT_FOLDER_MODISCO = "modisco/";

	public ResultAlternative(IProject project, IFolder folder){
		super(project, folder, ModelType.GROUP_PCM, CSTool.EXTRACTOR);
	}
	
	@Override
	public void doCreate(IProgressMonitor monitor) {		
		//
		// Prepare folder structure
		//
		IFolder modiscoFolder = getResource().getFolder(RESULT_FOLDER_MODISCO);
		IFolder somoxFolder = getResource().getFolder(RESULT_FOLDER_SOMOX);
		
		try {
			modiscoFolder.create(true, true, null);
			somoxFolder.create(true, true, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSubResource(ToolchainUtils.KEY_FOLDER_MODISCO, modiscoFolder);
		setSubResource(ToolchainUtils.KEY_FOLDER_SOMOX, somoxFolder);
		
		save(monitor);
	}
}
