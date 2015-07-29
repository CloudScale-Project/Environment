package eu.cloudscaleproject.env.example.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.eclipse.ui.wizards.datatransfer.ZipFileStructureProvider;

import eu.cloudscaleproject.env.common.CloudScaleConstants;
import eu.cloudscaleproject.env.example.common.Example.Resource.Type;
import eu.cloudscaleproject.env.product.wizard.CloudScaleProjectSupport;

public class ExampleService
{

	private static final String EP_EXAMPLE = "eu.cloudscaleproject.env.example";
	private static ExampleService instance;
	private List<Example> examples;

	public static ExampleService getInstance()
	{
		if (instance == null)
		{
			instance = new ExampleService();
			instance.examples = retrieveExamples();
		}
		return instance;
	}

	public List<Example> getExamples()
	{
		return examples;
	}
	
	public IProject getProject (Example.Resource r)
	{
		IProject project;
		try
		{
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName(r));
			if (project.exists())
			{
				return project;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public IProject createExampleResourceProject(Example.Resource r) throws Exception
	{
		// Prepare project
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName(r));

		if (!project.exists())
		{
			project.create(new NullProgressMonitor());
			project.open(new NullProgressMonitor());

			// Extract archive to newly prepared project
			unpackResourceProject(project, r);

			CloudScaleProjectSupport.addNature(project, CloudScaleConstants.EXAMPLE_NATURE_ID);
		}
		else
		{
			Logger.getLogger(ExampleService.class.getName()).info("Example project already exists : "+project.getName());
		}
		
		return project;
	}

	public static IProject unpackResourceProject(IProject project, Example.Resource resource) throws Exception
	{
		if (resource.getType() == Type.ENVIRONMENT)
			CloudScaleProjectSupport.addProjectNature(project);

		ZipFile file = new ZipFile(resource.getArchive().getFile());
		ZipFileStructureProvider provider = new ZipFileStructureProvider(file);
		IPath containerPath = project.getFullPath();
		Object source = provider.getRoot();

		IOverwriteQuery query = new IOverwriteQuery()
		{
			@Override
			public String queryOverwrite(String path)
			{
				return IOverwriteQuery.ALL;
			};
		};
		ImportOperation operation = new ImportOperation(containerPath, source, provider, query);
		operation.run(null);

		return project;
	}

	private String getProjectName(Example.Resource r) throws IOException
	{
		URL entryUrl = new URL("jar:" + r.getArchive() + "!/.project");
		BufferedReader br = new BufferedReader(new InputStreamReader(entryUrl.openStream()));
		StringWriter writer = new StringWriter();
		
		String line = "";
		while((line = br.readLine()) != null){
			writer.append(line);
		}
		br.close();
		
		String theString = writer.toString();

		int from = theString.indexOf("<name>") + 6;
		int to = theString.indexOf("</name>");
		return theString.substring(from, to);
	}

	private static List<Example> retrieveExamples()
	{

		List<Example> examples = new LinkedList<>();

		IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor(EP_EXAMPLE);

		for (IConfigurationElement el : elements)
		{
			if (!el.isValid())
			{
				continue;
			}

			Example e = new Example(el);
			examples.add(e);
		}

		return examples;
	}
}
