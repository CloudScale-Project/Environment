package eu.cloudscaleproject.env.toolchain.resources.types;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;

public abstract class AbstractConfigAlternative extends EditorInputEMF implements IConfigAlternative
{
	protected ResourceProvider resultsResourceProvider;
	protected ResourceProvider inputResourceProvider;

	public AbstractConfigAlternative(IProject project, IFolder folder, String validationID, 
			ResourceProvider inputResourceProvider,
			ResourceProvider resultsResourceProvider)
	{

		super(project, folder, validationID);

		this.inputResourceProvider = inputResourceProvider;
		this.resultsResourceProvider = resultsResourceProvider;
	}

	public void setInputAlternative(IEditorInputResource input)
	{
		setSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE, input.getResource());
	}

	@Override
	public IEditorInputResource getInputAlternative()
	{
		IResource res = getSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE);

		if (res != null)
			return inputResourceProvider.getResource(res);
		else
			return null;
	}

	@Override
	public IEditorInputResource getLastResult()
	{
		IResource res = getSubResource(IConfigAlternative.KEY_LAST_RESULT);

		if (res != null)
			return resultsResourceProvider.getResource(res);
		else
			return null;
	}

	private void setLastResult(IEditorInputResource res)
	{
		setSubResource(IConfigAlternative.KEY_LAST_RESULT, res.getResource());
	}

	@Override
	public List<IEditorInputResource> getResults()
	{
		List<IEditorInputResource> configResults = new LinkedList<>();
		List<IEditorInputResource> resources = resultsResourceProvider.getResources();
		for (IEditorInputResource res : resources)
		{
			if (getName().equals(res.getProperty("CONFIG_NAME")))
			{
				configResults.add(res);
			}
		}

		return configResults;
	}

	@Override
	public final IStatus run(IProgressMonitor monitor)
	{
		IStatus status = doRun(monitor);

		if (status == null || status.isOK())
		{
			setProperty(IConfigAlternative.KEY_TIMESTAMP_LAST_SUCC_RUN, "" + System.currentTimeMillis());

			List<IEditorInputResource> resources = resultsResourceProvider.getResources();

			IEditorInputResource lastResult = resources.get(resources.size() - 1);
			// BUG : Retrieves different object!?
			//lastResult.setProperty("CONFIG_NAME", getName());
			//lastResult.save();
			setLastResult(lastResult);

			save();
		}

		return status;
	}

	abstract protected IStatus doRun(IProgressMonitor monitor);

}
