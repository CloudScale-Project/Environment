package eu.cloudscaleproject.env.toolchain.resources.types;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.ModelType;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;

public abstract class AbstractConfigAlternative extends EditorInputEMF implements IConfigAlternative {
	private IStatus lastRunStatus;
	private CSTool tool;

	public AbstractConfigAlternative(IProject project, IFolder folder, ModelType[] modelTypes, CSTool tool) {

		super(project, folder, modelTypes, tool.getConfig().getID());

		this.tool = tool;
	}
	
	public CSTool getTool()
	{
		return tool;
	}

	protected ResourceProvider getResultResourceProvider() {
		ResourceProvider resultsResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, tool.getResult().getID());
		return resultsResourceProvider;
	}

	public void setInputAlternative(IInputAlternative input) {
		setSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE, input.getResource());
	}

	@Override
	public IInputAlternative getInputAlternative() {
		IResource res = getSubResource(ToolchainUtils.KEY_INPUT_ALTERNATIVE);

		ResourceProvider inputResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project, this.tool.getInput().getID());
		if (inputResourceProvider != null && res != null)
			return (IInputAlternative) inputResourceProvider.getResource(res);
		else
			return null;
	}

	@Override
	public IEditorInputResource getLastResult() {
		IResource res = getSubResource(IConfigAlternative.KEY_LAST_RESULT);
		ResourceProvider resultsResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project,
				tool.getResult().getID());

		if (resultsResourceProvider != null && res != null)
			return resultsResourceProvider.getResource(res);
		else
			return null;
	}

	private void setLastResult(IEditorInputResource res) {
		setSubResource(IConfigAlternative.KEY_LAST_RESULT, res.getResource());
	}

	@Override
	public List<IEditorInputResource> getResults() {
		List<IEditorInputResource> configResults = new LinkedList<>();
		List<IEditorInputResource> resources = getResultResourceProvider().getResources();
		for (IEditorInputResource res : resources) {
			if (res instanceof IResultAlternative) {
				IResultAlternative ira = (IResultAlternative) res;
				if (ira.getConfigAlternative() == null) continue;

				if (ira.getConfigAlternative().getName().equals(getName())) {
					configResults.add(ira);
				}
			}
		}

		return configResults;
	}

	@Override
	public final IStatus run(IProgressMonitor monitor) {
		save();

		try {
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
				@Override
				public void run(IProgressMonitor monitor) throws CoreException {
					lastRunStatus = doRun(monitor);
				}
			}, monitor);
		} catch (CoreException e) {
			e.printStackTrace();
			lastRunStatus = e.getStatus();
		}

		if (lastRunStatus == null || lastRunStatus.isOK()) {
			setProperty(IConfigAlternative.KEY_TIMESTAMP_LAST_SUCC_RUN, "" + System.currentTimeMillis());

			ResourceProvider resultsResourceProvider = ResourceRegistry.getInstance().getResourceProvider(project,
					tool.getResult().getID());
			List<IEditorInputResource> resources = resultsResourceProvider.getResources();
			IEditorInputResource lastResult = resources.get(resources.size() - 1);
			setLastResult(lastResult);
			save();
		}

		return lastRunStatus;
	}

	abstract protected IStatus doRun(IProgressMonitor monitor) throws CoreException;

}
