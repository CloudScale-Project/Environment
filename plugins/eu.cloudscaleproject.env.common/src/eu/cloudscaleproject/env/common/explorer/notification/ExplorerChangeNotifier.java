package eu.cloudscaleproject.env.common.explorer.notification;

import java.util.HashSet;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.swt.widgets.Display;

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
	
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getDelta() == null) {
			return;
		}
		
		final IResourceDelta delta = event.getDelta();
			
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				synchronized (ExplorerChangeNotifier.this) {
					for(ExplorerChangeListener listener : new HashSet<>(listeners)){
						boolean update = false;
						
						IResource[] resources = listener.getResources();
						if(resources != null){
							for(IResource r : resources){
								if(r != null && delta.findMember(r.getFullPath()) != null){									
									update = true;
								}
							}
						}
						
						if(update){
							listener.resourceChanged(delta);
						}
					}
				}
			}
		});
	}
	
}