package eu.cloudscaleproject.env.extractor.util;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public abstract class ProjectListener implements IResourceChangeListener {

	public abstract void projectsUpdated(Set<IProject> projects);

	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
			final Set<IProject> projects = getProjects(event.getDelta());
			if (!projects.isEmpty()) {
				//////////////////////////
				// HACK - delayed validation, if project is not yet accessible (when opening projects)
				//
				// TODO: Probably there is better solution
				//
				Job job = new Job("Delayed validation. [Workaround]") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						projectsUpdated(projects);
						return Status.OK_STATUS;
					}
				};
				job.schedule(1000);

				//
				////////////////////////

			}
		}
	}

	private Set<IProject> getProjects(IResourceDelta delta) {
		final Set<IProject> projects = new HashSet<IProject>();
		try {
			delta.accept(new IResourceDeltaVisitor() {
				public boolean visit(IResourceDelta delta) throws CoreException {
					if (delta.getResource().getType() == IResource.PROJECT) {
						IProject project = (IProject) delta.getResource();
						if (delta.getKind() == IResourceDelta.ADDED) {
							projects.add(project);
						}

						if (delta.getKind() == IResourceDelta.REMOVED) {
							projects.add(project);
						}
					}
					// only continue for the workspace root
					return delta.getResource().getType() == IResource.ROOT;
				}
			});
		} catch (CoreException e) {
			// handle error
		}
		return projects;
	}
}
