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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.notification.StatusManager;
import eu.cloudscaleproject.env.common.notification.diagram.ValidationDiagramService;
import eu.cloudscaleproject.env.toolchain.CSTool;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInput;

public class GlobalInputAlternative extends EditorInput
{

	private static GlobalInputAlternative globalInputAlternative;

	public static GlobalInputAlternative getInstance()
	{
		if (globalInputAlternative == null)
		{
			globalInputAlternative = new GlobalInputAlternative();
			StatusManager.getInstance().addStatusProvider(null, globalInputAlternative);
			initProjectListener();
		}

		return globalInputAlternative;
	}
	
	@Override
	public String getName() {
		return "Extractor input";
	}

	private List<IProject> javaProjects;

	private static void initProjectListener()
	{
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.addResourceChangeListener(new ProjectListener()
		{
			@Override
			public void projectsUpdated(Set<IProject> projects)
			{
				//////////////////////////
				// HACK - delayed validation, if project is not yet accessible (when opening projects)
				// TODO: Probably there is better solution
				//
				boolean delayed = false;
				for (IProject iProject : projects)
				{
					if (!iProject.isAccessible())
					{
						delayed = true; break;
					}

				}

				if (delayed)
				{
					Job job = new Job ("Delayed validation. [Workaround]"){
						@Override
						protected IStatus run(IProgressMonitor monitor)
						{
							getInstance().validate();

							GlobalInputAlternative.getInstance().setJavaProjects(findJavaProjects());
							return Status.OK_STATUS;
						}
					};
					job.schedule(1000);
				}
				
				//
				////////////////////////
				
				GlobalInputAlternative.getInstance().setJavaProjects(findJavaProjects());
				getInstance().validate();

				Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run()
					{
						if (getInstance().getProject() != null)
						{
							ValidationDiagramService.showStatus(getInstance().getProject(), 
																CSTool.EXTRACTOR_INPUT.getID(), 
																GlobalInputAlternative.getInstance());
						}
					}
				});
			}
		});
	}

	private GlobalInputAlternative()
	{
		super("Input alternative", CSTool.EXTRACTOR_INPUT.getID());
		setJavaProjects(findJavaProjects());
	}

	private static String JAVA_NATURE_ID = "org.eclipse.jdt.core.javanature";
	
	private void setJavaProjects(List<IProject> javaProjects)
	{
		List<IProject> old = this.javaProjects;
		this.javaProjects = javaProjects;
		firePropertyChange("javaProjects", old, this.javaProjects);
	}
	
	public List<IProject> getJavaProjects ()
	{
		return this.javaProjects;
	}
	
	private static List<IProject> findJavaProjects()
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

	public void setProject(IProject project)
	{
		this.project = project;
	}

	public IProject getProject()
	{
		return this.project;
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
							if (delta.getKind() == IResourceDelta.ADDED)
							{
								projects.add(project);
							}

							if (delta.getKind() == IResourceDelta.REMOVED)
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
