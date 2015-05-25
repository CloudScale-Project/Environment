package eu.cloudscaleproject.env.extractor.alternatives;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputResource;

public class GlobalInputAlternative extends EditorInputResource
{

	private static GlobalInputAlternative globalInputAlternative;

	public static GlobalInputAlternative getInstance()
	{
		if (globalInputAlternative == null)
		{
			globalInputAlternative = new GlobalInputAlternative();
			initProjectListener();
		}

		return globalInputAlternative;
	}

	private static void initProjectListener()
	{
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.addResourceChangeListener(new ProjectListener()
		{
			@Override
			public void projectsUpdated(Set<IProject> projects)
			{
				getInstance().validate();
				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run()
					{
						if (getInstance().getProject() != null)
						{
							ValidationDiagramService.showStatus(getInstance().getProject(), GlobalInputAlternative.getInstance());
						}
					}
				});
			}
		});
	}

	private GlobalInputAlternative()
	{
		super();
	}

	private static String JAVA_NATURE_ID = "org.eclipse.jdt.core.javanature";

	public static List<IProject> getJavaProjects()
	{
		List<IProject> javaProjects = new ArrayList<>();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		for (IProject project : root.getProjects())
		{
			IProjectNature nature;
			try
			{
				nature = project.getNature(JAVA_NATURE_ID);
				if (nature != null)
					javaProjects.add(project);
			} catch (CoreException e)
			{
				System.out.println("Unable to retrieve nature: " + e.getMessage());
			}
		}

		return javaProjects;
	}

	private IProject project;

	public String getID()
	{
		return ToolchainUtils.EXTRACTOR_INPUT_ID;
	}

	public void setProject(IProject project)
	{
		this.project = project;
	}

	public IProject getProject()
	{
		return this.project;
	}

	public IResource getResource()
	{
		return null;
	}

	public String getType()
	{
		return null;
	}

	public void save()
	{
	}

	public void load()
	{
	}

	public void delete()
	{
	}

	public boolean isLoaded()
	{
		return true;
	}

	public boolean isDirty()
	{
		return false;
	}

	public String getProperty(String key)
	{
		return null;
	}

	public void setProperty(String key, String value)
	{
	}

	public void copyFrom(IResource file)
	{
	}

	public String getName()
	{
		return "Input alternative";
	}

	public void setName(String name)
	{
	}

	public static abstract class ProjectListener implements IResourceChangeListener
	{

		public abstract void projectsUpdated(Set<IProject> projects);

		public void resourceChanged(IResourceChangeEvent event)
		{
			if (event.getType() == IResourceChangeEvent.POST_CHANGE)
			{
				Set<IProject> projects = getProjects(event.getDelta());
				if (!projects.isEmpty())
					projectsUpdated(projects);
			}
		}

		private Set<IProject> getProjects(IResourceDelta delta)
		{
			final Set<IProject> projects = new HashSet<IProject>();
			try
			{
				delta.accept(new IResourceDeltaVisitor()
				{
					public boolean visit(IResourceDelta delta) throws CoreException
					{
						if (delta.getResource().getType() == IResource.PROJECT)
						{
							IProject project = (IProject) delta.getResource();
							if (delta.getKind() == IResourceDelta.ADDED && project.isAccessible())
							{
								projects.add(project);
							} 
							else if (!project.isAccessible())
							{
								projects.add(project);
							}
						}
						// only continue for the workspace root
						return delta.getResource().getType() == IResource.ROOT;
					}
				});
			} catch (CoreException e)
			{
				// handle error
			}
			return projects;
		}
	}
}
