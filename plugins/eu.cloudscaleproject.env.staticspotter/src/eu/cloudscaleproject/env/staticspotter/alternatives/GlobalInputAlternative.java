package eu.cloudscaleproject.env.staticspotter.alternatives;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.ResourceProvider;
import eu.cloudscaleproject.env.toolchain.resources.ResourceRegistry;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputResource;
import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInputResource;

public class GlobalInputAlternative extends EditorInputResource
{
	public static final String KEY_PROJECT_URL = "project_url";

	private static GlobalInputAlternative globalInputAlternative;

	public static GlobalInputAlternative getInstance()
	{
		if (globalInputAlternative == null)
		{
			globalInputAlternative = new GlobalInputAlternative();
			initExtractorResultsListener();
		}

		return globalInputAlternative;
	}

	private static void initExtractorResultsListener()
	{
	}

	private GlobalInputAlternative()
	{
		super();
	}
	
	public List<IEditorInputResource> getExtractorResults()
	{
		ResourceProvider resourceProvider = ResourceRegistry.getInstance().
				getResourceProvider(getProject(), ToolchainUtils.EXTRACTOR_RES_ID);
		
		return resourceProvider.getResources();
	}
	

	private IProject project;
	public String getID() { return ToolchainUtils.SPOTTER_STA_INPUT_ID; }
	public void setProject(IProject project) { this.project = project; }
	public IProject getProject() { return this.project; }
	public IResource getResource() { return null; }
	public String getType() { return null; }
	public void save() { } 
	public void load() { } 
	public void delete() { }
	public boolean isLoaded() { return true; }
	public boolean isDirty() { return false; }
	public String getProperty(String key) { return null; }
	public void setProperty(String key, String value) { }
	public void copyFrom(IResource file) { } 
	public String getName() { return "Input alternative"; }
	public void setName(String name) { } 
}
