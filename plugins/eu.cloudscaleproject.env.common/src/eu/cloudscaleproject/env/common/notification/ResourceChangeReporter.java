package eu.cloudscaleproject.env.common.notification;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.swt.widgets.Display;

import eu.cloudscaleproject.env.common.explorer.ExplorerProjectPaths;

public class ResourceChangeReporter implements IResourceChangeListener {
	
	private final StatusManager sm;
	
	@Inject
	public ResourceChangeReporter(StatusManager statusManager) {
		this.sm = statusManager;
	}
	
	public void runValidators(IProject project, IResourceChangeEvent event){
		
		for (final ToolValidator validator : sm.getValidators()) {
			
			IResource[] resArray = validator.getDependantResources(project);
			if (resArray == null || resArray.length == 0) {
				continue;
			}

			for (final IResource res : resArray) {
				if (res == null || !res.exists()) {
					continue;
				}

				IResourceDelta rd = event.getDelta().findMember(res.getFullPath());
				if (rd != null) {
					
					//trigger dirty state for all "forward" nodes
					IToolStatus status = sm.getStatus(res.getProject(), validator.getToolID());
					if(status != null){
						status.setIsDirtyNextRecursive(true);
					}
					
					validator.validate(res.getProject());
				}
			}
		}
	}
	
	@Override
	public void resourceChanged(final IResourceChangeEvent event) {

		if (event.getDelta() == null || event.getDelta() == null) {
			return;
		}
		
		for (IResourceDelta ird : event.getDelta().getAffectedChildren()) {

			if (ird.getResource() == null
					|| ird.getResource().getProject() == null) {
				continue;
			}

			final IProject project = ird.getResource().getProject();
			if(project == null){
				continue;
			}
			
			if(!ExplorerProjectPaths.hasPropertyFile(project)){
				return;
			}
			
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					runValidators(project, event);
				}
			});
		}
	}
}