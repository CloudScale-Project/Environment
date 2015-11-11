package eu.cloudscaleproject.env.extractor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.contexts.IEclipseContext;

import eu.cloudscaleproject.env.common.BatchExecutor;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeListener;
import eu.cloudscaleproject.env.common.explorer.notification.ExplorerChangeNotifier;
import eu.cloudscaleproject.env.toolchain.explorer.ExplorerContentRetriever;

public class ExplorerProjectProvider extends ExplorerContentRetriever{
	

	private final ExplorerChangeListener ecl = new ExplorerChangeListener() {
		
		public void resourceChanged(IResourceDelta delta) {
			
			for (IResourceDelta rootDelta : delta.getAffectedChildren()) {
				
				boolean refresh = false;
				
				IResource resource = rootDelta.getResource();
				
				if(rootDelta.getKind() == IResourceDelta.ADDED){
					if(resource instanceof IProject){
						refresh = true;
					}
				}
				if(rootDelta.getKind() == IResourceDelta.REMOVED){
					if(resource instanceof IProject){
						refresh = true;
					}
				}

				if(rootDelta.getKind() == IResourceDelta.CHANGED){
					if(resource instanceof IProject){
						refresh = true;
					}
				}
				
				if(refresh){
					BatchExecutor.getInstance().addTask(ExplorerProjectProvider.this, "refresh", new Runnable() {
						
						@Override
						public void run() {
							refresh();
						}
					});
				}
			}
		};
		
		public IResource[] getResources() {
			return new IResource[]{ResourcesPlugin.getWorkspace().getRoot()};
		};
	};

	@Override
	public List<Object> getChildren() {
		
		List<Object> out = new ArrayList<Object>();
		for(IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()){
			try {
				if(project.hasNature("org.eclipse.jdt.core.javanature")){
					out.add(project);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return out;
	}

	@Override
	public void initialize(String nodeID, IEclipseContext context) {
		ExplorerChangeNotifier.getInstance().addListener(ecl);
	}

	@Override
	public void dispose() {
		ExplorerChangeNotifier.getInstance().removeListener(ecl);
	}

}
