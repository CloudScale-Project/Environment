package eu.cloudscaleproject.env.toolchain.resources.types;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import eu.cloudscaleproject.env.toolchain.CSTool;


public interface IConfigAlternative extends IEditorInputResource{

	public final String KEY_LAST_RESULT = "last_result";
	public final String KEY_TIMESTAMP_LAST_SUCC_RUN = "last_run";
	
	public CSTool getTool();
	
	public IInputAlternative getInputAlternative();
	public IEditorInputResource getLastResult();
	public List<IEditorInputResource> getResults();
	public IStatus run(IProgressMonitor monitor);
}
