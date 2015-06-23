package eu.cloudscaleproject.env.common.explorer.notification;

import java.util.HashSet;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;

public class ExplorerChangeNotifier implements IResourceChangeListener{
	
	private static ExplorerChangeNotifier instance = null;
	public static ExplorerChangeNotifier getInstance() {
		if(instance == null){
			instance = new ExplorerChangeNotifier();
		}
		return instance;
	}
	
	private HashSet<ExplorerChangeListener> listeners = new HashSet<ExplorerChangeListener>();
	
	public synchronized void addListener(ExplorerChangeListener r){
		listeners.add(r);
	}
	
	public synchronized void removeListener(ExplorerChangeListener r){
		listeners.remove(r);
	}
	
	private IResourceDelta findContainerDelta (IResource resource, IResourceDelta delta)
	{
		if (delta.getResource().equals(resource)) return delta;
		if (delta.getAffectedChildren().length == 0) return null;
		
		for (IResourceDelta d : delta.getAffectedChildren())
		{
			IResourceDelta res = findContainerDelta(resource, d);
			if (res != null) return res;
		}
		return null;
	}
	
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getDelta() == null) {
			return;
		}
				
		final IResourceDelta delta = event.getDelta();
			
			synchronized (ExplorerChangeNotifier.this) {
				for(ExplorerChangeListener listener : new HashSet<>(listeners)){
					
					IResource[] resources = listener.getResources();
					if(resources != null){
						for(IResource r : resources){
							if(r != null && delta.findMember(r.getFullPath()) != null){									
								IResourceDelta rootDelta = findContainerDelta(r, delta);
								if(rootDelta != null){
									listener.resourceChanged(rootDelta);
								}
							}
						}
					}
					
				}
			}
			
	}
	
}